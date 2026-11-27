package SFire.expand.blocks;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class BeamCrusher extends BeamDrill{

    public Attribute attribute = Attribute.sand;
    public Item output = Items.sand;

    public float liquidBoostIntensity = optionalBoostIntensity = 1;
    public float itemBoostIntensity = 1f;
    public float boostItemUseTime = 120f;

    public @Nullable Consume itemConsumer;
    public boolean hasLiquidBooster;

    //public Effect updateEffect = Fx.mineWallSmall;
    //public float updateEffectChance = 0.02f;

    public final int timerUse = timers ++;

    public BeamCrusher(String name){
        super(name);

        drillTime = 150f;
        updateClipRadius((range + 2) * tilesize);
    }

    @Override
    public void init(){
        super.init();

        hasLiquidBooster = findConsumer(f -> f instanceof ConsumeLiquidBase && f.booster) != null;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.drillTier);
        stats.remove(Stat.drillSpeed);

        stats.add(Stat.output, output);
        stats.add(Stat.tiles, StatValues.blocks(attribute, floating, 1f, true, false));
        stats.add(Stat.drillSpeed, 60f / getDrillTime(output), StatUnit.itemsSecond);

        boolean consItems = itemConsumer != null;

        if(consItems) stats.timePeriod = boostItemUseTime;

        if(consItems && itemConsumer instanceof ConsumeItems coni){
            stats.remove(Stat.booster);
            stats.add(Stat.booster, StatValues.itemBoosters("{0}" + StatUnit.timesSpeed.localized(), stats.timePeriod, itemBoostIntensity, 0f, coni.items));
        }

        if(liquidBoostIntensity != 1 && findConsumer(f -> f instanceof ConsumeLiquidBase && f.booster) instanceof ConsumeLiquidBase consBase){
            stats.remove(Stat.booster);
            stats.add(Stat.booster,
                    StatValues.speedBoosters("{0}" + StatUnit.timesSpeed.localized(),
                            consBase.amount,
                            liquidBoostIntensity, false, consBase::consumes)
            );
        }
    }

    public float getEfficiency(int tx, int ty, int rotation, @Nullable Cons<Tile> ctile, @Nullable Intc2 cpos){
        float eff = 0f;
        int dx = Geometry.d4x(rotation), dy = Geometry.d4y(rotation);

        for(int i = 0; i < size; i++){
            nearbySide(tx, ty, rotation, i, Tmp.p1);

            if(cpos != null){
                cpos.get(Tmp.p1.x, Tmp.p1.y);
            }

            for(int j = 0; j < range; j++){
                int rx = Tmp.p1.x + dx*j, ry = Tmp.p1.y + dy*j;
                Tile other = world.tile(rx, ry);
                if(other != null && other.solid()){
                    float at = other.block().attributes.get(attribute);
                    eff += at;
                    if(at > 0 && ctile != null){
                        ctile.get(other);
                    }
                    break;
                }
            }
        }
        return eff;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return getEfficiency(tile.x, tile.y, rotation, null, null) > 0f;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        float eff = getEfficiency(x, y, rotation, null, null);
        int dx = Geometry.d4x(rotation), dy = Geometry.d4y(rotation);

        for(int i = 0; i < size; i++){
            nearbySide(x, y, rotation, i, Tmp.p1);

            int j = 0;
            boolean mineable = false;
            for(; j < range; j++){
                int rx = Tmp.p1.x + dx*j, ry = Tmp.p1.y + dy*j;
                Tile other = world.tile(rx, ry);
                if(other != null && other.solid()){
                    mineable = other.block().attributes.get(attribute) > 0f;
                    break;
                }
            }

            int len = Math.min(j, range - 1);
            Drawf.dashLine(mineable ? Pal.placing : Pal.remove,
                    Tmp.p1.x * tilesize,
                    Tmp.p1.y * tilesize,
                    (Tmp.p1.x + dx*len) * tilesize,
                    (Tmp.p1.y + dy*len) * tilesize
            );
        }

        if(eff > 0f){
            float width = drawPlaceText(Core.bundle.formatFloat("bar.drillspeed", 60f / getDrillTime(output) * eff, 2), x, y, valid);
            float dx2 = x * tilesize + offset - width/2f - 4f, dy2 = y * tilesize + offset + size * tilesize / 2f + 5, s = iconSmall / 4f;
            Draw.mixcol(Color.darkGray, 1f);
            Draw.rect(output.fullIcon, dx2, dy2 - 1, s, s);
            Draw.reset();
            Draw.rect(output.fullIcon, dx2, dy2, s, s);
        }
    }

    public class BeamCrusherBuild extends BeamDrillBuild{
        public float wallEfficiency;

        @Override
        public void updateTile(){
            if(lasers[0] == null) updateLasers();

            updateFacing();

            warmup = Mathf.approachDelta(warmup, Mathf.num(efficiency > 0), 1f / 60f);

            boolean cons = shouldConsume();
            boolean itemValid = itemConsumer != null && itemConsumer.efficiency(this) > 0;

            float liquidBoost = Mathf.lerp(1f, liquidBoostIntensity, hasLiquidBooster ? optionalEfficiency : 0f);
            float itemBoost = itemValid ? itemBoostIntensity : 1f;

            float eff = wallEfficiency * liquidBoost * itemBoost;
            float dt = getDrillTime(output);

            boostWarmup = Mathf.lerpDelta(boostWarmup, optionalEfficiency, 0.1f);

            if(itemValid && eff * efficiency > 0 && timer(timerUse, boostItemUseTime / timeScale)){
                consume();
            }

            lastDrillSpeed = eff * timeScale * efficiency / dt;

            if(cons && (time += edelta() * eff) >= dt){
                offload(output);
                time %= dt;
            }

            if(timer(timerDump, dumpTime / timeScale)){
                dump(output);
            }
        }

        @Override
        protected void updateFacing(){
            lastItem = null;
            facingAmount = 0;
            wallEfficiency = 0f;

            int dx = Geometry.d4x(rotation), dy = Geometry.d4y(rotation);

            for(int p = 0; p < size; p++){
                Point2 l = lasers[p];
                Tile dest = null;

                for(int i = 0; i < range; i++){
                    int rx = l.x + dx*i, ry = l.y + dy*i;
                    Tile other = world.tile(rx, ry);
                    if(other != null && other.solid()){
                        float at = other.block().attributes.get(attribute);
                        if(at > 0f){
                            dest = other;
                            wallEfficiency += at;
                            facingAmount++;
                        }
                        break;
                    }
                }

                facing[p] = dest;
            }

            if(facingAmount > 0){
                lastItem = output;
            }
        }

        @Override
        public boolean shouldConsume(){
            return items.get(output) < itemCapacity;
        }

        @Override
        public void draw(){
            Draw.rect(block.region, x, y);
            Draw.rect(topRegion, x, y, rotdeg());

            if(isPayload()) return;

            var dir = Geometry.d4(rotation);
            int ddx = Geometry.d4x(rotation + 1), ddy = Geometry.d4y(rotation + 1);

            for(int i = 0; i < size; i++){
                Tile face = facing[i];
                if(face == null) continue;

                Point2 p = lasers[i];
                float lx = face.worldx() - (dir.x/2f)*tilesize, ly = face.worldy() - (dir.y/2f)*tilesize;

                float width = (laserWidth + Mathf.absin(Time.time + i*5 + (id % 9)*9, glowScl, pulseIntensity)) * warmup;

                Draw.z(Layer.power - 1);
                Draw.mixcol(glowColor, Mathf.absin(Time.time + i*5 + id*9, glowScl, glowIntensity));
                if(Math.abs(p.x - face.x) + Math.abs(p.y - face.y) == 0){
                    Draw.scl(width);

                    if(boostWarmup < 0.99f){
                        Draw.alpha(1f - boostWarmup);
                        Draw.rect(laserCenter, lx, ly);
                    }

                    if(boostWarmup > 0.01f){
                        Draw.alpha(boostWarmup);
                        Draw.rect(laserCenterBoost, lx, ly);
                    }

                    Draw.scl();
                }else{
                    float lsx = (p.x - dir.x/2f) * tilesize, lsy = (p.y - dir.y/2f) * tilesize;

                    if(boostWarmup < 0.99f){
                        Draw.alpha(1f - boostWarmup);
                        Drawf.laser(laser, laserEnd, lsx, lsy, lx, ly, width);
                    }

                    if(boostWarmup > 0.001f){
                        Draw.alpha(boostWarmup);
                        Drawf.laser(laserBoost, laserEndBoost, lsx, lsy, lx, ly, width);
                    }
                }
                Draw.color();
                Draw.mixcol();

                if(Lod.l2){
                    Draw.z(Layer.effect);
                    Lines.stroke(warmup);
                    rand.setState(i, id);
                    Color col = output.color;
                    Color spark = Tmp.c3.set(sparkColor).lerp(boostHeatColor, boostWarmup);
                    for(int j = 0; j < sparks; j++){
                        float fin = (Time.time / sparkLife + rand.random(sparkRecurrence + 1f)) % sparkRecurrence;
                        float or = rand.range(2f);
                        Tmp.v1.set(sparkRange * fin, 0).rotate(rotdeg() + rand.range(sparkSpread));

                        Color result = Tmp.c1.set(spark).lerp(col, fin);
                        Draw.color(result.r, result.g, result.b, result.a * Lod.alpha2);
                        float px = Tmp.v1.x, py = Tmp.v1.y;
                        if(fin <= 1f) Lines.lineAngle(lx + px + or * ddx, ly + py + or * ddy, Angles.angle(px, py), Mathf.slope(fin) * sparkSize);
                    }
                    Draw.reset();
                }
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
    /*怎么是tm硬编码的啊，根本改不了你妈的
    public static String suffix = "ozone-drill-beam";
    public static void apply(BeamDrill drill){
        drill.laser = find(suffix, drill.laser);
        drill.laserEnd = find(suffix + "-end", drill.laserEnd);
        drill.laserCenter = find(suffix + "-center", drill.laserCenter);

        public @Load(value = "@-beam-boost", fallback = "drill-laser-boost") TextureRegion laserBoost;
        public @Load(value = "@-beam-boost-end", fallback = "drill-laser-boost-end") TextureRegion laserEndBoost;
        public @Load(value = "@-beam-boost-center", fallback = "drill-laser-boost-center") TextureRegion laserCenterBoost;

        drill.laserBoost = find(suffix + "-boost", drill.laserBoost);
        drill.laserEndBoost = find(suffix + "-boost-end", drill.laserEndBoost);
        drill.laserCenterBoost = find(suffix + "-boost-center", drill.laserCenterBoost);


    }
    static TextureRegion find(String name, @Nullable TextureRegion fallback){
        return Core.atlas != null && Core.atlas.has(name) ? Core.atlas.find(name) : fallback;
    }*/
}