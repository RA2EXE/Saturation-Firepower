package SFire.expand.blocks;

import arc.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.*;
import mindustry.world.meta.*;

public class GasTurbineGenerator extends ConsumeGenerator {

    public final int timerUse = timers++;
    public float warmupSpeed = 0.001f, extraPower = 1f, powerUpSpeed = 0.001f;

    public GasTurbineGenerator(String name) {
        super(name);
        hasPower = true;
        outputsPower = true;
        flags = EnumSet.of(BlockFlag.generator);
    }

    @Override
    public void setBars() {
        super.setBars();
        addBar("power", (GeneratorBuild entity) -> new Bar(() ->
                Core.bundle.format("bar.poweroutput",
                        Strings.fixed(entity.getPowerProduction() * 60 * entity.timeScale(), 1)),
                () -> Pal.powerBar,
                () -> entity.productionEfficiency));
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(new Stat("extrapowermul", StatCat.power),"x"+(int)(extraPower*100)+"%");
    }

    //public @Nullable ConsumeLiquidFilter filterLiquid;
    /*public ObjectFloatMap<Liquid> liquidDurationMultipliers = new ObjectFloatMap<>();

    @Override
    public void init(){
        filterLiquid = findConsumer(c -> c instanceof ConsumeLiquidFilter);

        if(filterLiquid instanceof ConsumeLiquidFlammable eff){
            eff.liquidDurationMultipliers = liquidDurationMultipliers;

        }
    }*/

    public class GasTurbineGeneratorBuild extends GeneratorBuild {
        public float warmup, totalProgress, efficiencyMultiplier=1, exPower=1;

        @Override
        public void updateEfficiencyMultiplier() {
            if (filterLiquid != null) {
                float m = filterLiquid.efficiencyMultiplier(this);
                if (m > 0) efficiencyMultiplier = m;
            }
        }

        @Override
        public void updateTile() {

            if (efficiency >= 0.9999f) {
                warmup = Mathf.lerpDelta(warmup, 1f, warmupSpeed * timeScale);
                if (Mathf.equal(warmup, 1f, 0.001f)) {
                    warmup = 1f;
                    exPower = Mathf.lerpDelta(exPower,extraPower,powerUpSpeed * timeScale);
                }
                if (timer(timerUse, 60 / timeScale)) {
                    consume();
                }

            } else {
                warmup = Mathf.lerpDelta(warmup, 0f, 0.001f);
            }

            totalProgress += warmup * Time.delta;

            productionEfficiency = Mathf.pow(warmup, 5f) * efficiency * efficiencyMultiplier * exPower;
        }

        @Override
        public float warmup() {
            return warmup;
        }

        @Override
        public float totalProgress() {
            return totalProgress;
        }

        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.heat) return warmup;
            return super.sense(sensor);
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(warmup);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            warmup = read.f();
        }

    }


}
