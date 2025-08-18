package SFire.expand.blocks;

import arc.math.Mathf;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.consumers.ConsumeLiquidFilter;

import static mindustry.Vars.tilesize;

public class AccelTurret extends PowerTurret {

    public AccelTurret(String name) {
        super(name);
    }

    //开火时每秒加速50%，最大300%
    //也就是reload从1缩减到1/3
    public float accelMount = 0.5f;
    public float maxBoost = 3.0f;


    @Override
    public void load() {
        super.load();
    }

    public class AccelTurretBuild extends PowerTurretBuild {

        public float maxBoost = 0f;
        public float boost = 0f;
        public float reloadCounter;

        @Override
        protected void updateCooling(){
            if(reloadCounter < reload && coolant != null && coolant.efficiency(this) > 0 && efficiency > 0){
                float capacity = coolant instanceof ConsumeLiquidFilter filter ? filter.getConsumed(this).heatCapacity : (coolant.consumes(liquids.current()) ? liquids.current().heatCapacity : 0.4f);
                float amount = coolant.amount * coolant.efficiency(this);
                coolant.update(this);
                reloadCounter += amount * edelta() * capacity * coolantMultiplier * ammoReloadMultiplier();

                if(Mathf.chance(0.06 * amount)){
                    coolEffect.at(x + Mathf.range(size * tilesize / 2f), y + Mathf.range(size * tilesize / 2f));
                }
            }
        }

}