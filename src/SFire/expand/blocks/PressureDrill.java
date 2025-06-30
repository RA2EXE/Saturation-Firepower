package SFire.expand.blocks;


import arc.math.Mathf;
import mindustry.type.Item;
import mindustry.world.blocks.production.Drill;

public class PressureDrill extends Drill {
    
    public PressureDrill(String name) {
        super(name);
    }

    /*
    float lastPower;
    public float getLastPowerProduced(){return lastPower;}

    //实时硬度惩罚 = 基础硬度惩罚 - 5 *（电网余电/钻头本体耗电）
    //draw转速同步提升

    @Override
    public float getDrillTime(Item item){
        float totalHardness = hardnessDrillMultiplier - 5 * (lastPower / 1.75f);
        return (drillTime + totalHardness * item.hardness) / drillMultipliers.get(item, 1f);
    }*/


    public class PressureDrillBuild extends Drill.DrillBuild {

        public void updateTile(){
            super.updateTile();
            float delay = getDrillTime(dominantItem);

            if(items.total() < itemCapacity && dominantItems > 0 && efficiency > 0){
                float powerFactor = Math.min((power.graph.getLastPowerProduced()-power.graph.getLastPowerNeeded())/5/consPower.requestedPower(this), 2);
                float speed = Mathf.lerp(1f, liquidBoostIntensity, optionalEfficiency) * efficiency * Math.max(1,powerFactor);

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
            }
        }
    }
}

