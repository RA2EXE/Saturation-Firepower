package SFire.expand.blocks;

import SFire.content.SFColor;
import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.Fx;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class IonDrill extends BeamDrill{

    //物品强化
    public float itemRangeBoost = 1f;
    public float speedBoost = 0.8f;
    public @Nullable Consume itemConsumer;
    public float boostItemUseTime = 120f;

    //本来是要硬编码的，还是留一个方便日后整活//激光多了有点诡异，先留3个
    public int laserCount = 3;
    public int boostLaserCount = 2;

    public final int timerUse = timers ++;

    public IonDrill(String name){
        super(name);
        rotate = false;
        drawArrow = false;
        updateClipRadius((range + 4) * tilesize);
    }

    @Override
    public void setStats(){
        super.setStats();

        if(findConsumer(c -> c instanceof ConsumeItems) instanceof ConsumeItems cons) {
            stats.remove(Stat.booster);
            stats.add(Stat.booster, StatValues.itemBoosters(
                    "{0}" + StatUnit.timesSpeed.localized(),
                    stats.timePeriod, speedBoost,
                    itemRangeBoost * tilesize,
                    cons.items
                    )
            );
        }
    }
    @Override
    public void setBars(){
        super.setBars();
    }

    public int countOres(int cx, int cy, float r){
        int count = 0;
        int ri = Mathf.round(r), r2 = ri * ri;

        for(int dx = -ri; dx <= ri; dx++){
            for(int dy = -ri; dy <= ri; dy++){
                if(dx*dx + dy*dy > r2) continue;

                Tile other = world.tile(cx + dx, cy + dy);
                if(other == null || !other.solid()) continue;

                Item drop = other.wallDrop();
                if(drop == null || drop.hardness > tier) continue;
                if(blockedItems != null && blockedItems.contains(drop)) continue;

                count++;
            }
        }
        return count;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return countOres(tile.x, tile.y, range) > 0;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        Lines.stroke(1f);
        Draw.color(Pal.placing);
        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, (range+itemRangeBoost) * tilesize, Pal.placing);
        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range * tilesize, Pal.placing);

        Draw.reset();

        int count = countOres(x, y, range);
        drawPlaceText(Core.bundle.formatFloat("bar.drillspeed", 60f / getDrillTime(null) * count, 2), x, y, valid);
    }

    public class IonDrillBuild extends BeamDrillBuild{
        
        public Seq<Tile> targets = new Seq<>();
        public float boostingRange;
        public boolean multiple = false;
        public ObjectIntMap<Item> oreTypes = new ObjectIntMap<>();

        @Override
        public void updateTile(){
            //物品强化扫描半径变大
            warmup = Mathf.approachDelta(warmup, Mathf.num(efficiency > 0), 1f / 60f);

            float nowSpeed = Mathf.lerp(1f, optionalBoostIntensity * speedBoost, 0.001f);
            float drillTime = getDrillTime(lastItem);

            boolean itemValid = itemConsumer != null && itemConsumer.efficiency(this) > 0;
            boostingRange = range + (itemValid ? itemRangeBoost : 0f);
            updateFacing();

            boostWarmup = Mathf.lerpDelta(boostWarmup, nowSpeed, 0.1f);
            lastDrillSpeed = (targets.size * nowSpeed * timeScale) / drillTime * efficiency;

            time += edelta() * nowSpeed;

            if(time >= drillTime){
                for(Tile tile : targets){
                    Item drop = tile == null ? null : tile.wallDrop();
                    if(items.total() - boosterCount() < itemCapacity && drop != null){
                        items.add(drop, 1);
                        produced(drop);
                    }
                }
                time %= drillTime;
            }

            if(efficiency > 0 && itemValid && targets.size > 0 && timer(timerUse, boostItemUseTime / timeScale)){
                consume();
            }

            if(timer(timerDump, dumpTime / timeScale)){
                for(Item item : oreTypes.keys()){
                    int keep = boosterReserve(item);
                    if(keep > 0 && items.get(item) > 0){
                        int real = Math.min(keep, items.get(item));
                        items.remove(item, real);
                        dump(item);
                        items.add(item, real);
                    }else{
                        dump(item);
                    }
                }
            }
        }

        @Override
        protected void updateFacing(){
            lastItem = null;
            facingAmount = 0;
            targets.clear();
            oreTypes.clear();

            int r = Mathf.round(boostingRange), r2 = r * r;
            int cx = tileX(), cy = tileY();

            for(int dx = -r; dx <= r; dx++){
                for(int dy = -r; dy <= r; dy++){
                    if(dx*dx + dy*dy > r2) continue;

                    Tile other = world.tile(cx + dx, cy + dy);
                    if(other == null || !other.solid()) continue;

                    Item drop = other.wallDrop();
                    if(drop == null || drop.hardness > tier) continue;
                    if(blockedItems != null && blockedItems.contains(drop)) continue;

                    targets.add(other);
                    facingAmount++;
                    oreTypes.increment(drop, 1);

                    if(lastItem != drop && lastItem != null) {
                        multiple = true;
                    }
                    lastItem = drop;
                }
            }

            if(multiple) lastItem = null;
        }

        public @Nullable Item boosterType(){
            if(itemConsumer instanceof ConsumeItems cons && cons.items.length > 0){
                return cons.items[0].item;
            }
            return null;
        }
        public int boosterCount(){
            if(itemConsumer instanceof ConsumeItems cons){
                int count = 0;
                for(ItemStack stack : cons.items){
                    count += items.get(stack.item);
                }
                return count;
            }
            return 0;
        }//尝试修复加速物品被输出的问题
        public int boosterReserve(Item item){
            if(itemConsumer instanceof ConsumeItems cons){
                for(ItemStack stack : cons.items){
                    if(stack.item == item) return stack.amount;
                }
            }
            return 0;
        }

        @Override
        public boolean shouldConsume(){
            return items.total() - boosterCount() < itemCapacity && facingAmount > 0 && enabled;
        }

        public void drawSelect() {
            super.drawSelect();
            Lines.stroke(1f);
            Drawf.dashCircle(x, y,boostingRange * tilesize, Pal.accent);
            Draw.reset();
        }

        public float orbitAngle(int index, int count){
            float orbitSpeed = 0.5f;//死去的极坐标还在攻击我
            return Time.time/60f * orbitSpeed + index * Mathf.PI * 2f / Math.max(count, 1);
        }
        @Override
        public void draw(){
            Draw.rect(block.region, x, y);

            if(isPayload()) return;

            //int count = Math.min(multiple ? laserCount + boostLaserCount : laserCount, targets.size);
            int count = targets.size;
            for(int i = 0; i < count; i++){
                Tile face = targets.get(i);
                if(face == null) continue;

                Item drop = face.wallDrop();
                if(drop == null) continue;

                float lx = face.worldx(), ly = face.worldy();
                float angle = orbitAngle(i, count);
                float orbitRadius = 0.4f;
                lx += Mathf.cos(angle) * orbitRadius * tilesize;
                ly += Mathf.sin(angle) * orbitRadius * tilesize;

                float width = (laserWidth + Mathf.absin(Time.time + i*5 + (id % 9)*9, glowScl, pulseIntensity)) * warmup;

                Draw.z(Layer.blockAdditive - 1);
                Draw.mixcol(glowColor, Mathf.absin(Time.time + i*5 + id*9, glowScl, glowIntensity));

                if(boostWarmup < 0.99f){
                    Draw.alpha(1f - boostWarmup);
                    Drawf.laser(laser, laserEnd, x, y, lx, ly, width);
                }

                if(boostWarmup > 0.001f){
                    Draw.alpha(boostWarmup);
                    Drawf.laser(laserBoost, laserEndBoost, x, y, lx, ly, width);
                }

                Draw.color();
                Draw.mixcol();

                //if(Lod.l2 && Mathf.chance(0.035f * Time.delta)){
                if(efficiency > 0 && Mathf.chance(0.035f * Time.delta)){
                    Fx.unitMine.at(lx + Mathf.range(2f), ly + Mathf.range(2f), 0f, glowColor);
                }
            }

            Draw.rect(topRegion, x, y);

            if(glowRegion.found()){
                Draw.z(Layer.blockAdditive);
                Draw.blend(Blending.additive);
                Draw.color(Tmp.c1.set(heatColor).lerp(boostHeatColor, boostWarmup), warmup * (heatColor.a * (1f - heatPulse + Mathf.absin(heatPulseScl, heatPulse))));
                Draw.rect(glowRegion, x, y);
                Draw.blend();
                Draw.color();
            }

            Draw.blend();
            Draw.reset();
        }
    }
}
