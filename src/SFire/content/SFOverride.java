package SFire.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.effect.WrapEffect;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.*;
import mindustry.world.blocks.distribution.BufferedItemBridge;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.Junction;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Pump;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.consumers.ConsumeItemRadioactive;

import static mindustry.type.ItemStack.with;

public class SFOverride {
    public static void load() {

        /*
        ((Drill) Blocks.mechanicalDrill).drillTime = 540;
        ((Drill) Blocks.pneumaticDrill).drillTime = 360;
        */
        ((Drill) Blocks.laserDrill).drillTime = 252;
        ((Drill) Blocks.laserDrill).updateEffect = Fx.hitLancer;
        ((Drill) Blocks.blastDrill).drillTime = 252;
        ((Drill) Blocks.blastDrill).updateEffect = Fx.explosion;
        ((Drill) Blocks.blastDrill).hardnessDrillMultiplier = 40;
        Blocks.blastDrill.requirements(Category.production, ItemStack.with(Items.copper, 120, Items.titanium, 60, Items.silicon, 80, Items.thorium, 90));
        ((Pump) Blocks.impulsePump).pumpAmount = 1/3f;
        Blocks.impulsePump.consumePower(1.5f);
        Blocks.impulsePump.details = "加量不加价，效率更高60%！";

        //power! unlimitted! POWER!!
        ((ConsumeGenerator) Blocks.steamGenerator).powerProduction = 6f;
        ((ConsumeGenerator) Blocks.differentialGenerator).powerProduction = 19.8f;
        Blocks.differentialGenerator.destroyBullet = new BulletType() {{
            instantDisappear = true;
            hitShake = 5;
            hitSound = Sounds.explosionbig;
            hitEffect = new MultiEffect(
                    new ParticleEffect() {{
                        particles = 16;
                        sizeFrom = 9;
                        length = 66;
                        baseLength = 30;
                        lifetime = 45;
                        colorTo = colorFrom = Color.valueOf("737373");
                    }},
                    new ParticleEffect() {{
                        particles = 12;
                        line = true;
                        lenFrom = 16;
                        lenTo = 6;
                        strokeFrom = 2;
                        strokeTo = 0;
                        length = 70;
                        baseLength = 8;
                        lifetime = 22;
                        colorTo = Color.valueOf("737373");
                    }},
                    new WaveEffect() {{
                        sizeTo = 70;
                        strokeFrom = 6;
                        lifetime = 10;
                        colorFrom = Color.valueOf("FFE176");
                    }}
            );
            despawnEffect = Fx.none;
            makeFire = true;
            status = StatusEffects.burning;
            statusDuration = 360;
            splashDamage = 80;
            splashDamageRadius = 60;
        }};
        Blocks.differentialGenerator.details = "得益于材料进步，我们能够用更少的材料控制更强大的燃烧、进行更大面积的换热、产出更多的电力……以及发生更危险的生产安全事故！";

        //faster than faste
        /*
        ((Conveyor) Blocks.conveyor).speed = 0.08f;
        ((Conveyor) Blocks.conveyor).displayedSpeed = 8;
        ((Junction) Blocks.junction).speed = 20;
        ((Junction) Blocks.junction).capacity = 9;
        ((BufferedItemBridge) Blocks.itemBridge).speed = 20;
        ((BufferedItemBridge) Blocks.itemBridge).bufferCapacity = 16;
        ((Conveyor) Blocks.titaniumConveyor).speed = 0.15f;
        ((Conveyor) Blocks.titaniumConveyor).displayedSpeed = 15;
        ((Conveyor) Blocks.armoredConveyor).speed = 0.15f;
        ((Conveyor) Blocks.armoredConveyor).displayedSpeed = 15;
        ((Unloader)Blocks.unloader).speed = 3;
        */
        Blocks.armoredConveyor.placeableLiquid = true;

        //crafting
        ((GenericCrafter)Blocks.siliconSmelter).craftTime = 40;
        Blocks.siliconSmelter.consumePower(0.25f);
        ((AttributeCrafter)Blocks.siliconCrucible).craftTime = 90;
        ((AttributeCrafter)Blocks.siliconCrucible).minEfficiency = 1;
        ((AttributeCrafter)Blocks.siliconCrucible).boostScale = 1/3f;
        ((AttributeCrafter)Blocks.siliconCrucible).maxBoost = 2.5f;
        ((AttributeCrafter)Blocks.siliconCrucible).outputItem = new ItemStack(Items.silicon,10);
        Blocks.siliconCrucible.consumePower(3.5f);
        Blocks.siliconCrucible.floating = true;
        Blocks.siliconCrucible.itemCapacity = 40;
        Blocks.siliconCrucible.details = "升级高耐热外壳，优良热传导底座。得以更高的热转化效率。\n可以安置在液体上。";

        //cores
        Blocks.coreShard.health = 2000;
        Blocks.coreShard.armor = 3;
        Blocks.coreFoundation.health = 5000;
        Blocks.coreFoundation.armor = 8;
        Blocks.coreNucleus.health = 8000;
        Blocks.coreNucleus.armor = 12;

        //status
        StatusEffects.boss.damageMultiplier = 1.5f;
        StatusEffects.boss.healthMultiplier = 2f;

        StatusEffects.fast.effectChance = 0.05f;
        StatusEffects.fast.effect = new MultiEffect(new WrapEffect(){{effect=Fx.colorSparkBig;color=Color.orange;rotation=45;}},
                                                    new WrapEffect(){{effect=Fx.colorSparkBig;color=Color.orange;rotation=-135;}});
        StatusEffects.slow.healthMultiplier = 2f;
        StatusEffects.slow.effect = new MultiEffect(new WrapEffect(){{effect=Fx.colorSparkBig;color=Color.gray;rotation=135;}},
                                                    new WrapEffect(){{effect=Fx.colorSparkBig;color=Color.gray;rotation=-45;}});

        StatusEffects.sapped.speedMultiplier = 0.7f;
        StatusEffects.sapped.healthMultiplier = 0.75f;
        StatusEffects.sapped.damageMultiplier = 0.9f;
        StatusEffects.sapped.effectChance = 0.1f;
        StatusEffects.sapped.effect = new MultiEffect(new ParticleEffect() {{
            particles = 1;
            baseLength = 10;
            length = 5;
            region = "sfire-mod-lozenge";
            sizeInterp = Interp.pow3;
            sizeFrom = 1;
            sizeTo = 5;
            colorFrom = Color.valueOf("A278E1");
            colorTo = Color.valueOf("A278E100");
        }});

        //units
        ((UnitFactory)Blocks.airFactory).plans.add(new UnitFactory.UnitPlan(SFUnitTypes.air1, 60 * 25f, with(Items.silicon,15,Items.titanium,20)));
        ((UnitFactory)Blocks.groundFactory).plans.add(new UnitFactory.UnitPlan(SFUnitTypes.tank1, 60 * 40f, with(Items.silicon,30,Items.titanium,25,Items.lead,25)));
        ((UnitFactory)Blocks.navalFactory).plans.add(new UnitFactory.UnitPlan(SFUnitTypes.naval1, 60 * 50f, with(Items.silicon,35,Items.titanium,35,Items.metaglass,25)));

        ((Reconstructor)Blocks.additiveReconstructor).upgrades.addAll(
                new UnitType[]{SFUnitTypes.tank1, SFUnitTypes.tank2},
                new UnitType[]{SFUnitTypes.air1, SFUnitTypes.air2},
                new UnitType[]{SFUnitTypes.naval1, SFUnitTypes.naval2}
        );
        ((Reconstructor)Blocks.multiplicativeReconstructor).upgrades.addAll(
                new UnitType[]{SFUnitTypes.tank2, SFUnitTypes.tank3},
                new UnitType[]{SFUnitTypes.air2, SFUnitTypes.air3},
                new UnitType[]{SFUnitTypes.naval2, SFUnitTypes.naval3}
        );
        ((Reconstructor)Blocks.exponentialReconstructor).upgrades.addAll(
                new UnitType[]{SFUnitTypes.tank3, SFUnitTypes.tank4},
                new UnitType[]{SFUnitTypes.air3, SFUnitTypes.air4},
                new UnitType[]{SFUnitTypes.naval3, SFUnitTypes.naval4}
        );
        ((Reconstructor)Blocks.tetrativeReconstructor).upgrades.addAll(
                new UnitType[]{SFUnitTypes.tank4, SFUnitTypes.tank5},
                new UnitType[]{SFUnitTypes.air4, SFUnitTypes.air5},
                new UnitType[]{SFUnitTypes.naval4, SFUnitTypes.naval5}
        );


    }
}
