package SFire.expand.blocks;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.Bar;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class IonDrill extends BeamDrill{

    //物品强化
    public float itemRangeBoost = 1f;
    public float speedBoost = 0.75f;
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
        regionRotated1 = 0;
        ignoreLineRotation = false;
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

        addBar("drillspeed",(IonDrillBuild e) -> new Bar(
                () -> Core.bundle.format("bar.drillspeed", Strings.fixed(e.lastDrillSpeed * 60 * e.timeScale(), 2)),
                () -> Pal.ammo,
                () -> e.warmup
        ));

    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region};
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

        @Override
        public void updateTile(){
            //物品强化扫描半径变大
            updateFacing();
            warmup = Mathf.approachDelta(warmup, Mathf.num(efficiency > 0), 1f / 60f);

            float multiplier = Mathf.lerp(1f, optionalBoostIntensity, speedBoost);
            float drillTime = getDrillTime(lastItem);

            boolean itemValid = itemConsumer != null && itemConsumer.efficiency(this) > 0;
            boostingRange = range + (itemValid ? itemRangeBoost : 0f);

            boostWarmup = Mathf.lerpDelta(boostWarmup, speedBoost, 0.1f);
            lastDrillSpeed = (targets.size * multiplier * timeScale) / drillTime * efficiency;

            time += edelta() * multiplier;

            if(time >= drillTime){
                for(Tile tile : targets){
                    Item drop = tile == null ? null : tile.wallDrop();
                    if(items.total() < itemCapacity && drop != null){
                        items.add(drop, 1);
                        produced(drop);
                    }
                }
                time %= drillTime;
            }

            if(itemValid && targets.size > 0 && timer(timerUse, boostItemUseTime / timeScale)){
                consume();
            }

            if(timer(timerDump, dumpTime / timeScale)){
                dump();
            }
        }

        @Override
        protected void updateFacing(){
            lastItem = null;
            facingAmount = 0;
            targets.clear();

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

                    if(lastItem != drop && lastItem != null) {
                        multiple = true;
                    }else multiple = false;
                    lastItem = drop;
                }
            }

            if(multiple) lastItem = null;
        }

        @Override
        public boolean shouldConsume(){
            return items.total() < itemCapacity && facingAmount > 0 && enabled;
        }

        public void drawSelect() {
            super.drawSelect();
            Lines.stroke(1f);
            Drawf.dashCircle(x, y,boostingRange * tilesize, Pal.accent);
            Draw.reset();
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
                float width = (laserWidth + Mathf.absin(Time.time + i*5 + (id % 9)*9, glowScl, pulseIntensity)) * warmup;

                Draw.z(Layer.power - 1);
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
            }

            if(glowRegion.found()){
                Draw.z(Layer.blockAdditive);
                Draw.blend(Blending.additive);
                Draw.color(Tmp.c1.set(heatColor).lerp(boostHeatColor, boostWarmup), warmup * (heatColor.a * (1f - heatPulse + Mathf.absin(heatPulseScl, heatPulse))));
                Draw.rect(glowRegion, x, y, rotdeg());
                Draw.blend();
                Draw.color();
            }

            Draw.blend();
            Draw.reset();
        }
    }
}
