package SFire.expand.blocks;


import arc.Core;
import arc.math.Mathf;
import arc.util.Strings;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.OverdriveProjector;
import mindustry.world.blocks.production.Drill;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatValue;

public class PressureDrill extends Drill {

    public float maxFactor = 1f;
    public float minPowerNeed = 1f;

    public PressureDrill(String name) {
        super(name);
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.speedIncrease, "x" + (int)(maxFactor*100) + "%");
        stats.add(new Stat("minpowerneed", StatCat.function), (int)(minPowerNeed*100) + "%");
    }

    public class PressureDrillBuild extends Drill.DrillBuild {

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
                float finalFactor = (float) Math.sqrt(Math.min(powerFactor > 1 ? powerFactor : 1, maxFactor));

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

