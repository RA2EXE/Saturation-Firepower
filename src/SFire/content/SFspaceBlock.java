package SFire.content;

import SFire.expand.blocks.*;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.struct.EnumSet;
import mindustry.content.*;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeLiquidFlammable;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockFlag;

import static mindustry.type.ItemStack.with;

public class SFspaceBlock {
    public static Block
    //storage两个核心+两种容器
    //transport
    tiDuct, tiRouter, tiUnloader,
    spBridge, spBridgeLong,
    tiConduit, tiLiquidRouter, tiLiquidJunction,
    spLquidBridge, spLquidBridgeLong,
    //drill大小离子钻、万向离子钻、墙钻
    //crafter
    vacuumFurnace, irradiatedRefiner, arcSynthesizer, phasesConverter,
    electrolyzer, gravityAssembler, ionPlanter, spectrumWaver,
    //power？不考虑？或者用dp做修改
    oxiGenerator, chemGenerator, solarArray,
    //turret
    //defense

    //unit
    spaceFactory, spaceUpgrate, spaceUpgratePro

    ;

    public static void load(){
        //region transport
        tiDuct = new Duct("space-duct"){{
            health = 250;
            requirements(Category.distribution, with(Items.titanium, 1));
            speed = 60/ 20f;
        }};
        tiRouter = new DuctRouter("space-router"){{
            requirements(Category.distribution, with(Items.titanium, 3, Items.graphite, 1));
            health = 250;
            speed = 60/ 20f;
            regionRotated1 = 1;
            solid = false;
        }};
        tiUnloader = new DirectionalUnloader("space-unloader"){{
            requirements(Category.distribution, with(SFItems.waveSteel, 10, Items.silicon, 10));
            health = 250;
            speed = 60/ 20f;
            regionRotated1 = 1;
            squareSprite = false;
            solid = false;
            underBullets = true;
            allowCoreUnload = true;
        }};
        spBridge = new OmniBridge("space-bridge"){{
            health = 500;
            requirements(Category.distribution, with(Items.lead,3, SFItems.waveSteel,3));
            //envRequired = Env.space;
            hasPower = false;
            range = 13;
            transportTime = 60/ 20f;
            bridgeWidth = 8f;
            arrowSpacing = 6f;
            ((Duct) tiDuct).bridgeReplacement = this;
            unloadable = true;
        }};
        spBridgeLong = new OmniBridge("space-bridge-long"){{
            health = 1100;
            armor = 8;
            requirements(Category.distribution, with(SFItems.siliSteel,3, SFItems.waveSteel,6, SFItems.discFabric,3));
            //envRequired = Env.space;
            hasPower = false;
            pulse = true;
            consumePower(3/60f);
            range = 33;
            transportTime = 60/ 40f;
            bridgeWidth = 8f;
            arrowSpacing = 8;
            arrowOffset = 4;
            arrowTimeScl = 12;
            //((Duct) tiDuct).bridgeReplacement = this;
            unloadable = true;
        }};

        tiConduit = new ArmoredConduit("space-conduit"){{
            requirements(Category.liquid, with(Items.graphite,1, Items.titanium, 1));
            health = 250;
            liquidCapacity = 100;
            liquidPressure = 3f;
            explosivenessScale = flammabilityScale = 0;
            botColor = Color.valueOf("3C3E45");
            underBullets = true;
        }};
        tiLiquidRouter = new LiquidRouter("space-liquid-router"){{
            requirements(Category.liquid, with(Items.graphite,4, Items.titanium, 4));
            health = 250;
            liquidCapacity = 200;
            explosivenessScale = flammabilityScale = 0;
            underBullets = true;
            solid = false;
        }};
        tiLiquidJunction = new LiquidJunction("space-liquid-junction"){{
            requirements(Category.liquid, with(Items.graphite,6, Items.titanium, 4));
            health = 250;
            solid = false;
            ((Conduit) tiConduit).junctionReplacement = this;
        }};
        spLquidBridge = new OmniLiquidBridge("space-liquid-bridge"){{
            health = 500;
            requirements(Category.liquid, with(Items.lead,3, SFItems.siliSteel,3));
            //envRequired = Env.space;
            range = 13;
            hasPower = false;

            liquidCapacity = 100f;
            explosivenessScale = flammabilityScale = 0;

            bridgeWidth = 8f;
            arrowSpacing = 6f;
            ((Conduit) tiConduit).bridgeReplacement = this;
        }};
        spLquidBridgeLong = new OmniLiquidBridge("space-liquid-bridge-long"){{
            health = 1100;
            armor = 8;
            requirements(Category.liquid, with(SFItems.siliSteel,6, SFItems.waveSteel,3, SFItems.discFabric,3));
            //envRequired = Env.space;
            range = 33;
            consumePower(3/60f);

            liquidCapacity = 200f;
            explosivenessScale = flammabilityScale = 0;

            bridgeWidth = 8f;
            arrowSpacing = 8;
            arrowOffset = 4;
            arrowTimeScl = 12;
            fadeIn = moveArrows = false;
            //((Conduit) tiConduit).bridgeReplacement = this;
        }};
        //endregion
        //region power
        oxiGenerator = new ConsumeGenerator("oixden-generator"){{
            health = 500;
            size = 2;
            requirements(Category.power, with(Items.lead,60, Items.silicon,80, SFItems.rubidium,25));
            flags = EnumSet.of(BlockFlag.battery);

            powerProduction = 3;
            consumePowerBuffered(10000);
            consume(new ConsumeLiquidFlammable(0.3f));
            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());
        }};
        chemGenerator = new GasTurbineGenerator("chemcal-generator"){{
            size = 3;
            health = 1500;
            hasLiquids = true;
            liquidCapacity = 20;
            hasItems = false;
            canOverdrive = false;
            requirements(Category.power, with(SFItems.rubidium,60, SFItems.crystalGallium,80, SFItems.waveSteel,35));

            powerProduction = 10f;
            extraPower = 1.75f;
            warmupSpeed = 0.002f;
            powerUpSpeed = 0.005f;
            consumeLiquid(Liquids.ozone,0.1f);
            consume(new ConsumeLiquidFlammable(0.3f){{minFlammability=1.1f;}});

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.ozone){{alpha=0.3f;}},
                    new DrawRegion("-rot2",8f){{rotation=20;spinSprite=true;}},
                    new DrawRegion("-rot1",-8f){{rotation=-10;spinSprite=true;}},
                    new DrawDefault()
            );
        }};
        solarArray = new SolarArray("solar-array"){{
            health = 250;
            armor = 8;
            size = 1;
            requirements(Category.power, with(SFItems.chromium,20, SFItems.crystalGallium,35));
            baseExplosiveness = 1.5f;
            powerProduction = 1;
        }};

        //endregion
        //region crafter
        irradiatedRefiner = new GenericCrafter("irradiated-refiner"){{
            size = 2;
            health = 500;
            requirements(Category.crafting, with(Items.lead,30, SFItems.chromium,30));
            hasPower = false;
            hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 50;

            craftTime = 70;
            consumeItem(Items.sand,3);
            outputItem = new ItemStack(Items.silicon, 3);
            outputLiquid = new LiquidStack(Liquids.ozone,0.2f);

            drawer = new DrawMulti(new DrawDefault(),new DrawLiquidRegion(Liquids.ozone));
        }};
        arcSynthesizer = new GenericCrafter("arc-synthesizer"){{
            size = 3;
            health = 660;
            requirements(Category.crafting, with(Items.titanium,80, SFItems.siliSteel,80));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 15;
            liquidCapacity = 80;

            craftTime = 30;
            consumePower(4.5f);
            consumeItem(Items.graphite, 1);
            consumeLiquid(Liquids.hydrogen, 20f / 60f);
            outputLiquid = new LiquidStack(SFLiquids.mixGas, 10f / 60f);

            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.hydrogen),
                    new DrawLiquidTile(SFLiquids.mixGas),
                    new DrawArcSmelt(){{midColor=Pal.lighterOrange;flameColor=SFLiquids.mixGas.color;}},
                    new DrawDefault());
        }};
        phasesConverter = new GenericCrafter("phases-converter"){{
            size = 3;
            requirements(Category.crafting, with(Items.titanium,60, SFItems.rubidium,80, SFItems.chromium,150));
            hasPower = false;
            hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 60;
            rotate = true;
            invertFlip = true;

            craftTime = 30;
            consumeItem(SFItems.solidFuel, 1);
            outputLiquids = LiquidStack.with(Liquids.water, 10/60f, SFLiquids.mixGas,3/60f);

            regionRotated1 = 3;
            liquidOutputDirections = new int[]{1, 3};
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawPistons(){{
                        sinMag = 2.75f;
                        sinScl = 5f;
                        sides = 8;
                        sideOffset = Mathf.PI / 2f;
                    }},
                    new DrawLiquidTile(SFLiquids.mixGas),
                    new DrawRegion(),
                    new DrawLiquidRegion(Liquids.water),
                    new DrawLiquidOutputs()
            );
        }};
        electrolyzer = new GenericCrafter("electrolyzer"){{
            size = 3;
            requirements(Category.crafting, with(SFItems.siliSteel,75, SFItems.siliSteel,110, SFItems.waveSteel,80));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 15;
            liquidCapacity = 100;
            rotate = true;
            invertFlip = true;

            craftTime = 60;
            consumeLiquid(Liquids.water, 30f / 60f);
            consumePower(300 / 60f);
            outputLiquids = LiquidStack.with(Liquids.ozone, 10f / 60, Liquids.hydrogen, 30f / 60);

            regionRotated1 = 3;
            liquidOutputDirections = new int[]{1, 3};
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.water),
                    new DrawBubbles(Color.valueOf("7693e3")){{
                        sides = 10;
                        recurrence = 3f;
                        spread = 6;
                        radius = 1.5f;
                        amount = 20;
                    }},
                    new DrawRegion(),
                    new DrawLiquidOutputs(),
                    new DrawGlowRegion(){{
                        alpha = 0.7f;
                        color = Pal.techBlue;
                        glowIntensity = 0.3f;
                        glowScale = 6f;
                    }}
            );
        }};
        //endregion

         /*spaceFactory = new UnitFactory("space-factory"){{
            health = 11200;
            requirements(Category.units, with(Items.silicon,200, SFItems.waveSteel,360, SFItems.nanoCore,250));
            size = 7;
            consumePower(5f);
            plans = Seq.with(
            new UnitPlan(SFUnitTypes.arma, 60* 45f, SFItems.waveSteel,1),
            new UnitPlan(SFUnitTypes.piercer, 60* 45f, SFItems.waveSteel,1),
            new UnitPlan(SFUnitTypes.vagris, 60* 45f, SFItems.waveSteel,1),
            new UnitPlan(SFUnitTypes.shatter, 60* 45f, SFItems.waveSteel,1)
            )
        }};*/

    }
}
