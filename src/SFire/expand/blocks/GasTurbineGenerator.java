package SFire.expand.blocks;

import arc.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.game.EventType;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.type.Item;
import mindustry.type.LiquidStack;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.*;
import mindustry.world.consumers.ConsumeItemFilter;
import mindustry.world.meta.*;

public class GasTurbineGenerator extends ConsumeGenerator {

    public final int timerUse = timers++;
    public float warmupSpeed = 0.001f, extraPower = 1f, powerUpSpeed = 0.001f;

  //  public @Nullable ConsumeItemFilter filterItem;
 //   public @Nullable LiquidStack outputLiquid;
  //  public ObjectFloatMap<Item> itemDurationMultipliers = new ObjectFloatMap<>();

    public GasTurbineGenerator(String name) {
        super(name);
        hasPower = true;
        outputsPower = true;
        flags = EnumSet.of(BlockFlag.generator);
    }

    @Override
    public void setBars() {
        super.setBars();
        addBar("power", (GeneratorBuild entity) -> new Bar(
                () -> Core.bundle.format("bar.poweroutput", Strings.fixed(entity.getPowerProduction() * 60 * entity.timeScale(), 1)),
                () -> Pal.powerBar,
                () -> entity.productionEfficiency));
        addBar("boost", (GasTurbineGeneratorBuild e) -> new Bar(
                () -> Core.bundle.format("bar.boost", Strings.fixed((e.exPower-1f)*100 ,0)),
                () -> Pal.accent,
                () -> ((e.exPower>1 ? e.exPower : 0)-1) / (extraPower-1) ));

        /*if(outputLiquid != null){
            addLiquidBar(outputLiquid.liquid);
        }*/
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(new Stat("extrapowermul", StatCat.power),"x"+(int)(extraPower*100)+"%");

        /*if(outputLiquid != null){
            stats.add(Stat.output, StatValues.liquid(outputLiquid.liquid, outputLiquid.amount * 60f, true));
        }*/
    }

    //public @Nullable ConsumeLiquidFilter filterLiquid;
    /*public ObjectFloatMap<Liquid> liquidDurationMultipliers = new ObjectFloatMap<>();

    @Override
    public void init(){
        if(outputLiquid != null){
            outputsLiquid = true;
        }
    }*/

    //public class GasTurbineGeneratorBuild extends GeneratorBuild {
    public class GasTurbineGeneratorBuild extends ConsumeGeneratorBuild {
        public float warmup, totalProgress, efficiencyMultiplier=1, exPower=1;
        public float itemDurationMultiplier = 1;

        @Override
        public void updateEfficiencyMultiplier() {
            if (filterLiquid != null) {
                float m = filterLiquid.efficiencyMultiplier(this);
                if (m > 0) efficiencyMultiplier = m;
            }
        }

        @Override
        public void updateTile() {
            boolean valid = efficiency > 0;

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

            if(outputLiquid != null){
                float added = Math.min(productionEfficiency * delta() * outputLiquid.amount, liquidCapacity - liquids.get(outputLiquid.liquid));
                liquids.add(outputLiquid.liquid, added);
                dumpLiquid(outputLiquid.liquid);
            }
            generateTime -= delta() / (itemDuration * itemDurationMultiplier);
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
            write.f(exPower);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            warmup = read.f();
            exPower = read.f();
        }

    }


}
