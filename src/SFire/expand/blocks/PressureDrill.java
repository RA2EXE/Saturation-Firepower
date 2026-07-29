package SFire.expand.blocks;


import arc.Core;
import arc.math.Mathf;
import arc.struct.EnumSet;
import arc.util.*;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.Drill;
import mindustry.world.meta.*;

public class PressureDrill extends Drill {

    public float maxFactor = 1f;
    public float minPowerNeed = 1f;

    public PressureDrill(String name) {
        super(name);
        flags = EnumSet.of(BlockFlag.drill);
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.speedIncrease, "x" + (int)(maxFactor*100) + "%");
        stats.add(new Stat("minpowerneed", StatCat.function), (int)(minPowerNeed*100) + "%");
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("drillspeed", (PressureDrillBuild e) -> new Bar(
                () -> Core.bundle.format("bar.drillspeed", Strings.fixed(e.lastDrillSpeed * 60 * e.timeScale(), 2)),
                () -> Pal.ammo,
                () -> e.warmup
        ));

        addBar("boost", (PressureDrillBuild e) -> new Bar(
                () -> Core.bundle.format("bar.boost",Strings.fixed((e.finalFactor*e.finalFactor-1) * 100,0)),
                () -> Pal.accent,
                () -> (e.finalFactor*e.finalFactor-1) / (maxFactor-1)
               // () -> ((e.finalFactor>1 ? e.finalFactor*e.finalFactor : 0) -1) / (maxFactor-1)
        ));

    }

    public class PressureDrillBuild extends Drill.DrillBuild {
        public float finalFactor = 1;

        public PressureDrillBuild(){
            super();
        }

        @Override
        public void updateTile(){
            if(timer(timerDump, dumpTime)){
                dump(dominantItem != null && items.has(dominantItem) ? dominantItem : null);
            }

            if(dominantItem == null){
                return;
            }

            timeDrilled += warmup * delta();

            float delay = getDrillTime(dominantItem);


            if(items.total() < itemCapacity && dominantItems > 0 && efficiency > 0){

                float powerFactor = power.graph.getPowerBalance() / (minPowerNeed * consPower.requestedPower(this));
                finalFactor = (float) Math.sqrt(Math.min(Math.max(1,powerFactor), maxFactor));

                float speed = Mathf.lerp(1f, liquidBoostIntensity, optionalEfficiency) * efficiency * finalFactor;
                //float speed = Mathf.lerp(1f, liquidBoostIntensity, optionalEfficiency) * efficiency;

                lastDrillSpeed = (speed * dominantItems * warmup) / delay;
                warmup = Mathf.approachDelta(warmup, speed, warmupSpeed);
                progress += delta() * dominantItems * speed * warmup;

                if(Mathf.chanceDelta(updateEffectChance * warmup))
                    updateEffect.at(x + Mathf.range(size * 2f), y + Mathf.range(size * 2f));
            }else{
                lastDrillSpeed = 0f;
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
                return;
            }

            if(dominantItems > 0 && progress >= delay && items.total() < itemCapacity){
                int amount = (int)(progress / delay);
                for(int i = 0; i < amount; i++){
                    offload(dominantItem);
                }
                progress %= delay;
                if(wasVisible && Mathf.chanceDelta(drillEffectChance * warmup)) drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
            }
        }
    }
}

