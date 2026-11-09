package SFire.expand.blocks;

import SFire.content.*;
import arc.graphics.Color;
import arc.math.*;
import arc.struct.*;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.io.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

public class ArcFissionReactor extends ConsumeGenerator {

    //基础发电量 * 物品属性 * 倍率 = 单次发电量
    public float powerProduction;
    //电容，塞不下就停产，桑白万够吗
    public float powerCapacity = 3000000f;

    //发热比例 * 单次发电量 = 本次发电产出热量
    public float heating = 0.01f;
    //最大热量
    public float maxHeat = 100000f;
    //液冷冷却倍率
    public float coolantMul = 1f;
    public float coolantRate = 1f;

    public ObjectFloatMap<Item> itemDurationMultipliers = new ObjectFloatMap<>();
    public @Nullable ConsumeItemFilter itemConsumer;
    public @Nullable ConsumeLiquidFilter liquidConsumer;

    public ArcFissionReactor(String name) {
        super(name);
        hasPower = true;
        hasItems = true;
        hasLiquids = true;
        outputsPower = true;
        consumesPower = true;
        flags = EnumSet.of(BlockFlag.reactor);

        consume(new ConsumeItemRadioactive(0.8f));
        consumePowerBuffered(powerCapacity);
        liquidConsumer = consume(new ConsumeLiquidFilter(liquid -> true, coolantRate));
        liquidConsumer.optional(true, false);
        liquidConsumer.update(false);//红了，这尼玛有个自动消耗得关，我说怎么吃双倍/我怎么感觉臭猫的核电也是这个bug吃多倍

        emitLight = true;
        lightRadius = 80f;
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("charge", (ArcFissionReactorBuild e) -> new Bar(
                () -> Strings.fixed(e.charge, 0) + " / " + Strings.fixed(powerCapacity, 0),
                () -> Pal.powerBar,
                () -> powerCapacity > 0f ? e.charge / powerCapacity : 0f
        ));
        //热量条
        addBar("heat", (ArcFissionReactorBuild e) -> new Bar(
                () -> Strings.fixed(e.heat, 0) + " / " + Strings.fixed(maxHeat, 0),
                () -> e.heat > maxHeat * 0.8f ? Color.red : Pal.powerBar,
                () -> e.heat / maxHeat
        ));

    }

    public float itemEnergy(Item item){
        return powerProduction * itemDurationMultipliers.get(item, 1f) * item.radioactivity;
    }

    public void setItemMul(Item item, float mul){
        itemDurationMultipliers.put(item, mul);
    }

    @Override
    public void init(){
        super.init();
        if(liquidConsumer != null){
            liquidConsumer.amount = coolantRate;
        }
    }
    @Override
    public void afterPatch(){
        super.afterPatch();
        if(liquidConsumer != null){
            liquidConsumer.amount = coolantRate;
        }
    }


    public class ArcFissionReactorBuild extends ConsumeGeneratorBuild {
        //存电
        public float charge;
        //热量
        public float heat;
        //本帧实际输出到电网的电力
        public float output;

        @Override
        public void updateTile(){
            //super.updateTile();
            float rate = powerProduction;
            float out = Math.min(rate * delta(), charge);
            charge -= out;//草了不能直接做成蓄电池，还是得搞个什么东西输出
            output = delta() > 0f ? out / delta() : 0f;
            productionEfficiency = rate > 0f ? Mathf.clamp(output / rate) : 0f;

            consumeItems();
            consumeCoolant();
            /*if(heat > 0){
                Liquid liquid = liquidConsumer.getConsumed(this);
                float want = liquidConsumer.amount * edelta() * liquidConsumer.multiplier.get(this);
                float maxUsed = Math.min(Math.min(liquids.get(liquid), want), heat);
            }*/

            if(heat > maxHeat){
                onExplosion();
                kill();
            }

            /*开机算热
            int fuel = items.get(fuelItem);
            if(fuel > 0 && enabled){
                heat += heatLastFrame = fullness * heating * Math.min(delta(), 4f);

                if(timer(timerFuel, itemDuration / (timeScale + (heat > heatLastFrame ? 1f * heat * heatConsumeRate : 0f)))){
                    consume();
                }
            }else{停产？风冷？？
                productionEfficiency = 0f;
                heat = Math.max(0f, heat - Time.delta / ambientCooldownTime);
            }

            if(heat > 0){
                float maxUsed = Math.min(liquids.currentAmount(), heat / coolantPower);
                heat -= maxUsed * coolantPower;
                liquids.remove(liquids.current(), maxUsed);
            }*/
        }

        @Override
        public float getPowerProduction(){
            return enabled ? output : 0f;
        }

        public int consumeItems(){
            if(itemConsumer == null) return 0;
            int count = 0;
            while(count < itemCapacity * 0.5f){//秒吃全仓有点离谱了，贴核杀手
                Item item = itemConsumer.getConsumed(this);
                if(item == null) break;

                //产电 = 基础 * 物品倍率 * 物品放射性
                float energy = itemEnergy(item);

                //满仓停机
                if(charge > powerCapacity && charge + energy >= powerCapacity)break;

                items.remove(item, 1);//先吃后产防逻辑白嫖
                charge += energy;
                heat += heating * energy;
                count++;
            }
            return count;
        }

        public float consumeCoolant() {
            if(liquidConsumer == null) return 0f;
            Liquid liquid = liquidConsumer.getConsumed(this);
            if(liquid == null) return 0f;
            //这一帧最多能用掉多少冷却液
            float want = liquidConsumer.amount * edelta() * liquidConsumer.multiplier.get(this);
            if(want <= 0f) return 0f;
            float take = Math.min(Math.min(liquids.get(liquid), want), heat);
            if(take <= 0f) return 0f;
            liquids.remove(liquid, take);
            heat -= take * coolantMul;
            if(heat < 0f) heat = 0f;
            return take;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(charge);
            write.f(heat);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            if(revision >= 2){
                charge = read.f();
                heat = read.f();
            }
        }
        @Override
        public byte version(){return 2;}
    }
}
