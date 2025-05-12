package SFire.content;

import SFire.world.blocks.SFCore;
import arc.graphics.Color;
import arc.math.*;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.entities.UnitSorts;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.abilities.StatusFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.gen.*;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.ArmoredConduit;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static SFire.SFireMod.name;
import static arc.math.Mathf.random;
import static mindustry.type.ItemStack.*;

public class SFBlocks {
    public static Block
    //environment + wall + ores
    snowSand, rareEarth, rareEarthWater, calrareEarth, calrareEarthWall, magshaleFloor, magshaleWall, magshaleStone, celestiteFloor, celestiteWall, celestiteStone, combinationFloor, combinationWall, combinationStone,
    radiquartzFloor,radiquartzWall,radiquartzStone,
    radiamphiboleFloor,radiamphiboleWall,radiamphiboleStone,radigabbroFloor,radigabbroWall,radigabbroStone,radimacadam1Floor,radimacadam2Floor,

    induFloor, induFloorSupplyer, induFloorHeater, induHeatBroken, induFloorCover, induFloorWall, induFloorNano, induFloorNanowall,
            lightRed, lightYellow, lightBlue, lightGreen,
    reforcedFloor, reforcedFloor1, reforcedFloor2, reforcedFloor3, perimeter,

    discEngine, oilDrums, oilDrumsLarge, oilDrumsArmor, radarBig,

    strontium, rubidium, fermium, chromium,

    //crafting
    crusher, sporeCompressor, flywheelCentrifuge,
    pyraBlender, blastBlender, clusBlender, cryoCentrifuge, plasMultiCompresser, surgeTheSmelter, surgeElesmelter,

    silisteelSmelter, silisteelSmelterLarge, silisteelSmelterHuge, silisteelCrucible, wavesteelCompresseor, wavesteelForger, metalAnalyzer,
    nanoConstructor, nanoPrinter, lensAtomizer, airCollector, nitrateMixer, fractionator,
    discPhaseWaver, discPhaseKnitter, chemicalSiSmelter, blastSiSmelter, nitrReactor, nitrCentrifuge, nitrPrecipitator, nanoActivator, blastReagentMixer, clusMaker,
    tayriumSlelter, tayriumCrucible, leippiumSmelter, leippiumCrucible,
    //primaryLab, seniorLab, warfareLab

    //wall
    steelWall, steelWallLarge, influxWall, influxWallLarge, discWall,
    fermWall, fermWallLarge, fermDoor, leipWall, leipWallLarge,
    discContainmentUnit, armorContainmentUnit, tayrContainmentUnit,

    //defense
    nanoMendProjector, nanoRegenProjector, ironCurtain, ironDome,

    //transport
    waveConveyor, rearmoredConveyor, silisteelConveyor, waveJunction, waveBridge,
    discConveyor, discMassDriver,
    //liquid
    tidalPump, silisteelConduit, reArmoredConduit, silisteelTank, discConduit,

    //power
    armorBattery, armorNode, discNodeTower,
    gasSmoker, coalPyrolyzer, heatGenerator, radiGenerator, fermReactor, fissionReactor, arcFissionReactor,
    // hypermagneticReactor,

    //production
    energyDrill, heavyDrill, blastWell, quantumOreExtractor,
    waterExtractor, /*slagExtractor,*/
    oilPressurePump, sporeCultivator,

    //storage
    frontCore, industryCore, finalCommandCenter, hyperUnloader, molecularDatabase,

    //turrets
    xianqu, huojian, dianguang, bingfengbao, gaosi, liebao, zuodao, longxi, mengma, zheyue,
    mini, chuanyun, kuodao, longjuan, tieliu, yanglizi, changqiang, namifengbao, manyou, woliu, cijian, chuifa,
    fangtian, qingning, juhua0,
    lizipao, sizhao, liemei, cuowei0,
    dingdaer, guangyin, cimai, fengmang, zuli,
    poxiao, fenqing, kuosan, zhulin, yuanling, luolong,

    lingding, yuwen,
    defensePlatform1, defensePlatform2, defensePlatform3, defensePlatform4, defensePlatform5,


    //units
    terrAssembler, hoveAssembler, payloadConstrustor, specFactory, pentativeReconstrustor,
    nanoUnitRegener
    //campaign
    ;

    public static void load() {
        snowSand = new Floor("snow-sand") {{
            itemDrop = Items.sand;
            playerUnmineable = true;
            attributes.set(Attribute.oil, 0.35f);
            attributes.set(Attribute.water, 0.2f);
            variants = 2;
        }};
        rareEarth = new Floor("rare-earth-floor",4) {{
            itemDrop = SFItems.rareEarth;
            playerUnmineable = true;
            dragMultiplier = 1.08f;
        }};
        rareEarthWater = new Floor("rare-earth-water",4){{
            speedMultiplier = 0.8f;
            statusDuration = 50f;
            albedo = 0.9f;
            supportsOverlay = true;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            overlayAlpha = 0.35f;
        }};
        calrareEarth = new Floor("calrare-earth-floor",4) {{
            itemDrop = SFItems.rareEarth;
            playerUnmineable = true;
            dragMultiplier = 1.08f;
            attributes.set(Attribute.water, -0.1f);
        }};
        calrareEarthWall = new StaticWall("calrare-earth-wall"){{variants = 2;
            unitMoveBreakable = true;
        }};

        magshaleFloor = new Floor("magshale-floor",3){{
            wall = magshaleWall;
            decoration = magshaleWall;
            dragMultiplier = 2.25f;
            attributes.set(Attribute.oil, 1.88f);
            walkSound = Sounds.spark;
        }};
        celestiteFloor = new Floor("celestite-floor",3){{
            wall = celestiteWall;
            decoration = celestiteStone;
            dragMultiplier = 0.9f;
        }};
        combinationFloor = new Floor("combination-floor",4);

        radiquartzFloor = new Floor("radiquartz-floor",4){{
            wall = radiquartzWall;
            decoration = radiquartzStone;
            attributes.set(Attribute.water, -0.1f);
            attributes.set(Attribute.spores, -1f);
            attributes.set(SFAttribute.radioactivity, 0.3f);
        }};
        radiamphiboleFloor = new Floor("radiamphibole-floor",4){{
            wall = radiamphiboleWall;
            decoration = radiamphiboleStone;
            attributes.set(Attribute.water, -0.3f);
            attributes.set(Attribute.spores, -1f);
            attributes.set(SFAttribute.radioactivity, 0.75f);
        }};
        radigabbroFloor = new Floor("radigabbro-floor",4){{
            wall = radigabbroWall;
            decoration = radigabbroStone;
            attributes.set(Attribute.water, -0.5f);
            attributes.set(Attribute.spores, -1f);
            attributes.set(SFAttribute.radioactivity, 1.3f);
        }};
        radimacadam1Floor = new Floor("radimacadam1-floor",3){{
            blendGroup = radiamphiboleFloor;
            attributes.set(Attribute.water, -0.6f);
            attributes.set(Attribute.heat, 0.15f);
            attributes.set(Attribute.spores, -1f);
            emitLight = true;
            attributes.set(SFAttribute.radioactivity, 1.5f);
            lightColor = SFColor.discDark.cpy().a(0.4f);
            lightRadius = 6f;
        }};
        radimacadam2Floor = new Floor("radimacadam2-floor",3){{
            blendGroup = radigabbroFloor;
            attributes.set(Attribute.water, -0.75f);
            attributes.set(Attribute.heat, 0.3f);
            attributes.set(Attribute.spores, -1f);
            attributes.set(SFAttribute.radioactivity, 2.6f);
            emitLight = true;
            lightColor = SFColor.discDark.cpy().a(0.6f);
            lightRadius = 8f;
        }};

        magshaleWall = new StaticWall("magshale-wall"){{variants = 2;}};
        celestiteWall = new StaticWall("celestite-wall"){{variants = 2;}};
        radiquartzWall = new StaticWall("radiquartz-wall"){{variants = 2;}};
        radiamphiboleWall = new StaticWall("radiamphibole-wall"){{variants = 2;}};
        radigabbroWall = new StaticWall("radigabbro-wall"){{variants = 2;}};
        combinationWall = new StaticWall("combination-wall"){{variants = 2;}};

        magshaleStone = new Prop("magshale-stone"){{variants = 2;}};
        celestiteStone = new Prop("celestite-stone"){{variants = 2;}};
        radiquartzStone = new TallBlock("radiquartz-stone"){{
            variants = 2;
            clipSize = 120f;
            breakable = true;
            breakSound = Sounds.rockBreak;
            buildCostMultiplier = 10;
        }};
        radiamphiboleStone = new Prop("radiamphibole-stone"){{variants = 2;}};
        radigabbroStone = new Prop("radigabbro-stone"){{variants = 2;}};
        combinationStone = new Prop("combination-stone"){{variants = 2;}};

        induFloor = new Floor("industry-floor", 0) {{
            speedMultiplier = 1.125f;
            dragMultiplier = 0.95f;
            wall = induFloorWall;
            decoration = induFloorCover;
            attributes.set(Attribute.oil, 1f);
            attributes.set(Attribute.water, 0.25f);
            attributes.set(Attribute.spores, 0.25f);
        }};
        induFloorSupplyer = new Floor("industry-supplyer",7) {{
            speedMultiplier = 1.125f;
            dragMultiplier = 0.95f;
            attributes.set(Attribute.oil, 2);
            attributes.set(Attribute.water, 2);
            attributes.set(Attribute.spores, 2);
            blendGroup = SFBlocks.induFloor;
        }};
        induFloorHeater = new Floor("industry-heater",2) {{
            speedMultiplier = 0.85f;
            dragMultiplier = 1.35f;
            decoration = induFloorCover;
            attributes.set(Attribute.oil, -2);
            attributes.set(Attribute.water, -2);
            attributes.set(Attribute.spores, -2);
            attributes.set(Attribute.heat, 2);
            attributes.set(SFAttribute.radioactivity, 2);
            emitLight = true;
            lightRadius = 16;
            lightColor = Color.valueOf("F7A77EA0");
            walkEffect = new ParticleEffect() {{
                particles = 3;
                sizeFrom = 2.5f;
                length = 17;
                lifetime = 30;
                sizeInterp = Interp.pow5In;
                interp = Interp.pow5Out;
                colorFrom = Color.valueOf("FFF1D2");
                colorTo = Color.valueOf("FFD197");
            }};
            damageTaken = 8;
            status = StatusEffects.melting;
            blendGroup = SFBlocks.induFloor;
        }};
        induHeatBroken = new Floor("industry-heater-broken", 4){{
            speedMultiplier = 0.85f;
            dragMultiplier = 1.35f;
            decoration = induFloorCover;
            blendGroup = SFBlocks.induFloor;
        }};
        induFloorCover = new Prop("industry-floor-cover") {{
            variants = 6;
            breakSound = Sounds.breaks;
        }};
        induFloorWall = new StaticWall("industry-wall") {{variants = 0;}};
        induFloorNano = new Floor("nano-panel",16) {{
            emitLight = true;
            lightRadius = 10;
            lightColor = Color.valueOf("7CF38980");
            useColor = true;
            wall = induFloorNanowall;
            speedMultiplier = 1.125f;
            dragMultiplier = 0.95f;
            statusDuration = 10;
            status = SFStatusEffects.repair;
        }};
        induFloorNanowall = new StaticWall("industry-nanowall") {{variants = 4;}};

        lightRed = new Floor("light-red",0){{
            useColor = false;
            emitLight = true;
            blendGroup = induFloor;
            lightRadius = 40;
            lightColor = Color.red.cpy().a(0.6f);
        }};
        lightYellow = new Floor("light-yellow",0){{
            useColor = false;
            emitLight = true;
            blendGroup = induFloor;
            lightRadius = 40;
            lightColor = Color.yellow.cpy().a(0.6f);
        }};
        lightBlue = new Floor("light-blue",0){{
            useColor = false;
            emitLight = true;
            blendGroup = induFloor;
            lightRadius = 40;
            lightColor = Color.blue.cpy().a(0.6f);
        }};
        lightGreen = new Floor("light-green",0){{
            useColor = false;
            emitLight = true;
            blendGroup = induFloor;
            lightRadius = 40;
            lightColor = Color.green.cpy().a(0.6f);
        }};

        reforcedFloor = new Floor("reforced-floor",0) {{
            speedMultiplier = 1.3f;
            dragMultiplier = 1.15f;
            attributes.set(Attribute.oil, -0.125f);
            attributes.set(Attribute.water, -0.125f);
        }};
        reforcedFloor1 = new Floor("reforced-floor-1",0) {{
            speedMultiplier = 1.3f;
            dragMultiplier = 1.15f;
            attributes.set(Attribute.oil, -0.125f);
            attributes.set(Attribute.water, -0.125f);
            blendGroup = SFBlocks.reforcedFloor;
        }};
        reforcedFloor2 = new Floor("reforced-floor-2",0) {{
            speedMultiplier = 1.3f;
            dragMultiplier = 1.15f;
            attributes.set(Attribute.oil, -0.125f);
            attributes.set(Attribute.water, -0.125f);
            blendGroup = SFBlocks.reforcedFloor;
        }};
        reforcedFloor3 = new OverlayFloor("reforced-floor-3") {{variants=0;}};
        perimeter = new Floor("perimeter", 0) {{
            placeableOn=false;
            speedMultiplier = 1.3f;
            dragMultiplier = 1.15f;
            attributes.set(Attribute.oil, -0.125f);
            attributes.set(Attribute.water, -0.125f);
        }};

        discEngine = new Thruster("disc-engine") {{
            health = 4000;
            size = 5;
            requirements(Category.defense, with(Items.scrap,5000, SFItems.discFabric,650, SFItems.fermium,1500));
            buildVisibility = BuildVisibility.editorOnly;
            buildCostMultiplier = 0.5f;
        }};
        oilDrums = new Wall("oil-drums") {{
            solid = false;
            targetable = false;
            underBullets = true;
            variants = 5;
            size = 1;
            buildVisibility = BuildVisibility.editorOnly;
            rebuildable = false;
            category = Category.defense;
            destroyBullet = new ExplosionBulletType(300,50){{
                buildingDamageMultiplier = 0.05f;
                status = StatusEffects.blasted;
                hitSound = Sounds.explosionbig;
                hitSoundVolume = 3;
                hitShake = 5;
                hitEffect = new MultiEffect(
                        Fx.massiveExplosion,
                        new ParticleEffect(){{
                            particles = 12;
                            length = 44;
                            sizeFrom = 10;
                            lifetime = 65;
                            layer = 70;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = SFColor.smoke;
                            colorTo = SFColor.smoke.cpy().a(0.3f);
                        }}
                );
                despawnEffect = new WaveEffect(){{
                    interp = Interp.circleOut;
                    lifetime = 22;
                    sizeTo = 50;
                    strokeFrom = 8;
                    colorFrom = colorTo = Color.valueOf("d97c7c");
                }};
                fragBullets = 5;
                fragLifeMin = 0.8f;
                fragVelocityMax = 1.2f;
                fragVelocityMin = 0.6f;
                fragBullet = new BasicBulletType(10,820){{
                    buildingDamageMultiplier = 45.5f;
                    hitSize = 16f;
                    hittable = absorbable = false;
                    pierce = true;
                    lifetime = 13.5f;
                    hitShake = 3.5f;
                    width = height = 0;
                    hitSound = Sounds.boom;
                    hitSoundVolume = 3;
                    hitEffect = despawnEffect = Fx.blastExplosion;
                    intervalDelay = 1;
                    bulletInterval = 0.12f;
                    intervalBullets = 1;
                    intervalBullet = new FireBulletType(4,280){{
                        lifetime = 10;
                        splashDamage = 220;
                        splashDamageRadius = 25;
                        buildingDamageMultiplier = 45.5f;
                        hitEffect = despawnEffect = Fx.blastExplosion;
                    }};
                }};
            }};
        }};
        oilDrumsLarge = new Wall("oil-drums-large") {{
            solid = false;
            targetable = false;
            underBullets = true;
            variants = 3;
            size = 2;
            buildVisibility = BuildVisibility.editorOnly;
            rebuildable = false;
            category = Category.defense;
            destroyBullet = new ExplosionBulletType(390,80){{
                buildingDamageMultiplier = 0.05f;
                status = StatusEffects.blasted;
                hitSound = Sounds.explosionbig;
                hitSoundVolume = 3;
                hitShake = 5;
                hitEffect = new MultiEffect(
                        Fx.massiveExplosion,
                        new ParticleEffect(){{
                            particles = 12;
                            length = 60;
                            sizeFrom = 13;
                            lifetime = 75;
                            layer = 70;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = SFColor.smoke;
                            colorTo = SFColor.smoke.cpy().a(0.3f);
                        }}
                );
                despawnEffect = new WaveEffect(){{
                    interp = Interp.circleOut;
                    lifetime = 30;
                    sizeTo = 80;
                    strokeFrom = 10;
                    colorFrom = colorTo = Color.valueOf("d97c7c");
                }};
                fragBullets = 16;
                fragLifeMin = 0.8f;
                fragVelocityMax = 1.2f;
                fragVelocityMin = 0.6f;
                fragBullet = new BasicBulletType(10,820){{
                    buildingDamageMultiplier = 45.5f;
                    hitSize = 16f;
                    hittable = absorbable = false;
                    pierce = true;
                    lifetime = 13.5f;
                    hitShake = 3.5f;
                    width = height = 0;
                    hitSound = Sounds.boom;
                    hitSoundVolume = 3;
                    hitEffect = despawnEffect = Fx.blastExplosion;
                    intervalDelay = 1;
                    bulletInterval = 0.12f;
                    intervalBullets = 1;
                    intervalBullet = new FireBulletType(4,280){{
                        lifetime = 10;
                        splashDamage = 220;
                        splashDamageRadius = 25;
                        buildingDamageMultiplier = 45.5f;
                        hitEffect = despawnEffect = Fx.blastExplosion;
                    }};
                }};
            }};
        }};
        oilDrumsArmor = new LiquidRouter("oil-drums-armor") {{
            size = 4;
            health = 4200;
            armor = 24;
            liquidCapacity = 8400;
            buildVisibility = BuildVisibility.sandboxOnly;
            insulated = absorbLasers = true;
        }};
        radarBig = new Radar("big-radar") {{
            size = 3;
            buildCostMultiplier = 0.5f;
            fogRadius = 82;
            health = 1500;
            outlineColor = SFColor.darkOutline;
            discoveryTime = 480f;
            consumePower(1.8f);
            rotateSpeed = -0.75f;
            glowColor = SFColor.tayr;
            requirements(Category.effect,with(Items.titanium,100, Items.silicon,150, SFItems.tayrAlloy,50));
            buildVisibility = BuildVisibility.fogOnly;
        }};

        strontium = new OreBlock(SFItems.strontium) {{
            oreDefault = true;
            oreThreshold = 0.85f;
            oreScale = 28.702f;
        }};
        rubidium = new OreBlock(SFItems.rubidium) {{
            oreDefault = true;
            oreThreshold = 0.864f;
            oreScale = 24.904f;
        }};
        fermium = new OreBlock(SFItems.fermium) {{
            oreDefault = true;
            oreThreshold = 0.882f;
            oreScale = 33.206f;
        }};
        chromium = new OreBlock(SFItems.chromium) {{
            oreDefault = true;
            oreThreshold = 0.864f;
            oreScale = 28.255f;
        }};

        crusher = new GenericCrafter("crusher") {{
            size = 3;
            requirements(Category.crafting, with(Items.lead, 50, Items.graphite, 15, SFItems.siliSteel, 30));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 20;

            craftTime = 12;
            outputItem = new ItemStack(Items.sand, 12);
            consumePower(1 / 3f);
            consumeItem(Items.scrap, 8);
            consumeLiquid(Liquids.water, 0.11f);

            craftEffect = Fx.pulverize;
            updateEffect = Fx.pulverizeSmall;
            drawer = new DrawMulti(new DrawDefault(),
                    new DrawRegion("-rotator") {{
                        spinSprite = true;
                        rotateSpeed = -0.85f;
                    }}, new DrawRegion("-top"));
            ambientSound = Sounds.grinding;
            ambientSoundVolume = 0.5f;
        }};
        sporeCompressor = new GenericCrafter("spore-compressor") {{
            size = 3;
            health = 460;
            requirements(Category.crafting, with(Items.graphite, 110, Items.silicon, 40, Items.metaglass, 80, SFItems.siliSteel, 60, Items.plastanium, 30));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 100;

            craftTime = 30;
            outputLiquid = new LiquidStack(Liquids.oil, 0.9f);
            consumePower(7);
            consumeItem(Items.sporePod, 3);

            craftEffect = new ParticleEffect() {{
                particles = 3;
                sizeFrom = 3;
                length = 5;
                lifetime = 15;
                sizeInterp = Interp.pow2Out;
            }};
            drawer = new DrawMulti(
                    new DrawPistons() {{
                        sinMag = 1f;
                    }},
                    new DrawDefault(),
                    new DrawLiquidRegion(),
                    new DrawRegion("-top")
            );

        }};
        flywheelCentrifuge = new GenericCrafter("flywheel-centrifuge") {{
            size = 3;
            health = 460;
            requirements(Category.crafting, with(Items.graphite, 80, SFItems.waveSteel, 30, Items.silicon, 30));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 15;
            liquidCapacity = 60;

            craftTime = 20;
            outputItem = new ItemStack(Items.coal, 2);
            consumePower(6);
            consumeLiquid(Liquids.oil, 0.3f);

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawRegion("-rotator") {{
                        rotateSpeed = 6;
                    }}
            );
        }};
        pyraBlender = new GenericCrafter("pyratite-blender") {{
            size = 3;
            health = 460;
            requirements(Category.crafting, with(Items.copper, 60, Items.titanium, 40, Items.silicon, 30));
            hasPower = hasItems = true;
            itemCapacity = 20;

            craftTime = 80;
            outputItem = new ItemStack(Items.pyratite, 5);
            consumePower(1.2f);
            consumeItems(with(Items.coal, 5, Items.lead, 10, Items.sand, 10));
        }};
        blastBlender = new GenericCrafter("blast-blender") {{
            size = 3;
            health = 460;
            requirements(Category.crafting, with(Items.titanium, 60, SFItems.siliSteel, 30));
            hasPower = hasItems = true;
            itemCapacity = 20;

            craftTime = 80;
            outputItem = new ItemStack(Items.blastCompound, 5);
            consumePower(1.2f);
            consumeItems(with(Items.pyratite, 5, Items.sporePod, 5));
        }};
        clusBlender = new GenericCrafter("clus-blender") {{
            size = 2;
            health = 220;
            requirements(Category.crafting, with(Items.lead, 60, SFItems.waveSteel, 40, SFItems.siliSteel, 30));
            hasPower = hasItems = true;
            itemCapacity = 20;

            craftTime = 40;
            outputItem = new ItemStack(SFItems.clusBomb, 2);
            consumePower(2f);
            consumeItems(with(Items.pyratite, 1, Items.blastCompound, 4));
        }};
        cryoCentrifuge = new GenericCrafter("cryofluid-centrifuge") {{
            size = 3;
            health = 460;
            requirements(Category.crafting, with(Items.thorium, 80, Items.metaglass, 40, SFItems.siliSteel, 80, SFItems.rubidium, 80));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 14;
            liquidCapacity = 80;

            craftTime = 60;
            outputLiquid = new LiquidStack(Liquids.cryofluid, 36.1f / 60f);
            consumePower(2.8f);
            consumeItem(Items.titanium, 2);
            consumeLiquid(Liquids.water, 36.1f / 60f);

            lightLiquid = Liquids.cryofluid;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.cryofluid) {{
                drawLiquidLight = true;
            }}, new DrawDefault());
        }};
        plasMultiCompresser = new GenericCrafter("plastanium-mutilcompresser") {{
            size = 3;
            health = 460;
            requirements(Category.crafting, with(Items.graphite, 100, Items.thorium, 80, SFItems.waveSteel, 50, SFItems.siliSteel, 90));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 90;

            craftTime = 60;
            outputItem = new ItemStack(Items.plastanium, 3);
            consumePower(4);
            consumeItem(Items.titanium, 5);
            consumeLiquid(Liquids.oil, 0.5f);

            craftEffect = Fx.formsmoke;
            updateEffect = Fx.plasticburn;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPistons() {{
                sinMag = 1;
                sinScl = 1;
            }}, new DrawDefault(), new DrawFade());
        }};
        surgeTheSmelter = new GenericCrafter("surge-thermal-smelter") {{
            size = 3;
            requirements(Category.crafting, with(Items.lead, 90, Items.graphite, 70, Items.metaglass, 110, Items.thorium, 65));
            hasPower = hasItems = true;
            itemCapacity = 18;

            craftTime = 85;
            outputItem = new ItemStack(Items.surgeAlloy, 1);
            consumePower(7.5f);
            consumeItems(with(Items.scrap, 6, Items.silicon, 4));

            craftEffect = Fx.smeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame());
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.28f;
        }};
        surgeElesmelter = new GenericCrafter("surge-electric-smelter") {{
            size = 4;
            requirements(Category.crafting, with(Items.thorium, 110, SFItems.siliSteel, 60, SFItems.tayrAlloy, 30));
            hasPower = hasItems = true;
            itemCapacity = 30;

            craftTime = 85;
            outputItem = new ItemStack(Items.surgeAlloy, 3);
            consumePower(9f);
            consumeItems(with(Items.copper, 8, Items.lead, 10, Items.silicon, 8, Items.titanium, 6));

            craftEffect = new ParticleEffect() {{
                particles = 5;
                baseLength = 2;
                length = 20;
                lifetime = 25;
                sizeFrom = 10;
                region = "sfire-mod-loz";
                colorFrom = Pal.surge.cpy().a(0.06f);
                colorTo = Pal.surge;
            }};
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame() {{
                flameRadius = 6.3f;
                flameRadiusIn = 6;
                flameColor = Pal.surge.cpy().a(0.5f);
            }});
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.88f;
        }};

        silisteelSmelter = new GenericCrafter("silistell-smelter") {{
            size = 2;
            requirements(Category.crafting, with(Items.lead, 40, Items.titanium, 30));
            hasPower = hasItems = true;

            craftTime = 50;
            outputItem = new ItemStack(SFItems.siliSteel, 1);
            consumePower(1.25f);
            consumeItems(with(Items.silicon, 1, Items.titanium, 1));

            craftEffect = Fx.smeltsmoke;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.3f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame() {{
                flameColor = Color.valueOf("E1E4C8");
            }});
        }};
        silisteelSmelterLarge = new GenericCrafter("silistell-smelter-large") {{
            size = 3;
            requirements(Category.crafting, with(Items.plastanium, 55, SFItems.siliSteel, 100, SFItems.rubidium, 80));
            hasPower = hasItems = true;
            itemCapacity = 12;

            craftTime = 45;
            outputItem = new ItemStack(SFItems.siliSteel, 3);
            consumePower(5.5f);
            consumeItems(with(Items.silicon, 3, Items.titanium, 3));

            craftEffect = new ParticleEffect() {{
                particles = 5;
                baseLength = 6;
                length = 6;
                lifetime = 15;
                sizeFrom = 3;
                sizeInterp = Interp.pow3Out;
                region = "sfire-mod-loz";
                colorFrom = SFItems.siliSteel.color.cpy().a(0.66f);
                colorTo = SFItems.siliSteel.color;
            }};
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.65f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame() {{
                flameColor = Color.valueOf("E1E4C8");
            }});
        }};
        silisteelSmelterHuge = new AttributeCrafter("silistell-smelter-heater") {{
            size = 4;
            requirements(Category.crafting, with(Items.metaglass, 120, SFItems.siliSteel, 150, SFItems.chromium, 150, SFItems.discFabric, 40));
            hasPower = hasItems = true;
            itemCapacity = 24;
            attribute = Attribute.heat;
            baseEfficiency = 0.75f;
            maxBoost = 9.25f;
            boostScale = 1/16f;
            minEfficiency = 0;

            craftTime = 120;
            outputItem = new ItemStack(SFItems.siliSteel, 5);
            consumePower(10f);
            consumeItems(with(Items.silicon, 4, Items.titanium, 4));

            craftEffect = new ParticleEffect() {{
                particles = 3;
                baseLength = 6;
                length = 10;
                lifetime = 30;
                sizeFrom = 5;
                sizeInterp = Interp.pow5In;
                region = "sfire-mod-loz";
                colorTo = SFItems.siliSteel.color.cpy().a(0.66f);
            }};
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.88f;
            drawer = new DrawMulti(new DrawDefault(), new DrawGlowRegion() {{
                glowScale = 8;
                alpha = 0.8f;
            }},
                    new DrawFlame() {{
                        flameRadius = 2.5f;
                        flameRadiusIn = 1.5f;
                        flameRadiusMag = 1;
                        flameRadiusInMag = 0.2f;
                        flameColor = Color.valueOf("a8baf2A8");
                    }});
        }};
        silisteelCrucible = new GenericCrafter("silistell-crucible") {{
            size = 3;
            requirements(Category.crafting, with(SFItems.rubidium, 50, Items.silicon, 40, Items.titanium, 55));
            hasPower = hasItems = true;

            craftTime = 80;
            outputItem = new ItemStack(SFItems.siliSteel, 3);
            consumePower(2.5f);
            consumeItems(with(SFItems.rubidium, 2, SFItems.chromium, 2));

            craftEffect = new ParticleEffect() {{
                particles = 6;
                baseLength = 2;
                length = 10;
                lifetime = 30;
                sizeFrom = 5;
                interp = Interp.fastSlow;
                sizeInterp = Interp.pow2In;
                region = "sfire-mod-loz";
                colorFrom = SFItems.siliSteel.color.cpy().a(0.66f);
                colorTo = SFItems.siliSteel.color;
            }};
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.3f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame() {{
                flameColor = Color.valueOf("E1E4C8");
            }});
        }};
        wavesteelCompresseor = new GenericCrafter("wavesteel-compresser") {{
            size = 4;
            requirements(Category.crafting, with(Items.graphite, 100, SFItems.rubidium, 35, SFItems.chromium, 60, SFItems.siliSteel, 40));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 60;

            craftTime = 60;
            outputItem = new ItemStack(SFItems.waveSteel, 3);
            consumePower(4);
            consumeItems(with(Items.lead, 2, SFItems.chromium, 3));
            consumeLiquid(Liquids.water, 0.3f);

            craftEffect = new ParticleEffect() {{
                particles = 5;
                baseLength = 1.5f;
                length = 13;
                lifetime = 30;
                sizeFrom = 3.3f;
                sizeInterp = Interp.pow2In;
                region = "sfire-mod-loz";
                colorFrom = Color.valueOf("b3e6ff");
                colorTo = Color.valueOf("71a5bf");
            }};
            drawer = new DrawMulti(
                    new DrawPistons() {{
                        sinMag = 3;
                        sinScl = 5;
                        sideOffset = 2;
                        angleOffset = 45;
                        lenOffset = 8;
                        sides = 4;
                    }},
                    new DrawDefault(),
                    new DrawLiquidRegion(Liquids.water),
                    new DrawGlowRegion() {{
                        color = Color.valueOf("B3E6FF");
                    }}
            );
        }};
        wavesteelForger = new GenericCrafter("wavesteel-forger") {{
            size = 4;
            requirements(Category.crafting, with(Items.plastanium, 100, SFItems.waveSteel, 60, SFItems.rubidium, 180, SFItems.siliSteel, 45));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 60;

            craftTime = 75;
            outputItem = new ItemStack(SFItems.waveSteel, 6);
            consumePower(4.25f);
            consumeItems(with(Items.lead, 6, SFItems.chromium, 3));
            consumeLiquid(SFLiquids.nitrate, 0.3f);

            craftEffect = new ParticleEffect() {{
                particles = 5;
                baseLength = 1.5f;
                length = 13;
                lifetime = 30;
                sizeFrom = 3.3f;
                sizeInterp = Interp.pow2In;
                region = "sfire-mod-loz";
                colorFrom = Color.valueOf("b3e6ff");
                colorTo = Color.valueOf("71a5bf");
            }};
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(SFLiquids.nitrate),
                    new DrawPistons() {{
                        sinMag = 3;
                        lenOffset = -1.8f;
                    }},
                    new DrawDefault()
            );
        }};
        metalAnalyzer = new Separator("metal-analyzer") {{
            size = 3;
            requirements(Category.crafting, with(Items.silicon, 80, Items.metaglass, 100, Items.titanium, 50, SFItems.siliSteel, 35));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 24;
            liquidCapacity = 36;

            craftTime = 20;
            results = with(SFItems.strontium, 3, SFItems.rubidium, 5, SFItems.chromium, 5);
            consumePower(1.25f);
            //consumeItem(SFItems.rareEarth, 1);
            consumeItem(Items.sand, 2);
            consumeLiquid(Liquids.water, 0.21f);

            ambientSound = Sounds.hum;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawRegion("-rot1") {{
                rotateSpeed = 1.36f;
            }}, new DrawRegion("-rot2") {{
                rotateSpeed = -0.8f;
            }}, new DrawDefault());
        }};
        nanoConstructor = new GenericCrafter("nano-constructor") {{
            size = 4;
            requirements(Category.crafting, with(Items.titanium, 140, Items.silicon, 80, SFItems.siliSteel, 60, SFItems.rubidium, 35));
            hasPower = hasItems = true;
            itemCapacity = 15;

            craftTime = 40;
            outputItem = new ItemStack(SFItems.nanoCore, 2);
            consumePower(10f);
            consumeItems(with(Items.silicon, 3, SFItems.rubidium, 3));

            ambientSound = Sounds.techloop;
            ambientSoundVolume = 0.3f;
            drawer = new DrawMulti(new DrawFlame() {{
                flameColor = Color.valueOf("7CF389B6");
            }}, new DrawDefault(), new DrawFade() {{
                alpha = 0.98f;
                scale = 3;
            }});
        }};
        nanoPrinter = new GenericCrafter("nano-printer") {{
            size = 3;
            health = 650;
            requirements(Category.crafting, with(SFItems.chromium, 200, SFItems.nanoCore, 220, SFItems.tayrAlloy, 35));
            hasPower = hasItems = true;

            craftTime = 20;
            outputItem = new ItemStack(SFItems.nanoCore, 1);
            consumePower(15f);
            consumeItems(with(Items.silicon, 1, SFItems.rubidium, 1));

            ambientSound = Sounds.techloop;
            ambientSoundVolume = 0.3f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawFade() {{
                alpha = 0.99f;
                scale = 1.13f;
            }}, new DrawWeave(), new DrawDefault());
        }};
        lensAtomizer = new GenericCrafter("lens-atomizer") {{
            size = 3;
            requirements(Category.crafting, with(Items.lead, 200, Items.metaglass, 110, SFItems.tayrAlloy, 80, SFItems.discFabric, 35));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 5;

            craftTime = 120;
            outputItem = new ItemStack(SFItems.lens, 1);
            consumePower(6f);
            consumeItems(with(Items.silicon, 1, SFItems.strontium, 3));
            consumeLiquid(Liquids.nitrogen, 1 / 30f);

            ambientSound = Sounds.techloop;
            ambientSoundVolume = 0.3f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCrucibleFlame() {{
                midColor = Color.valueOf("97A5F7");
                flameColor = Color.valueOf("D1E4FF");
                flameRad = 4.45f;
                circleSpace = 1.2f;
                flameRadiusScl = 8;
                flameRadiusMag = 3;
                circleStroke = 0.1f;
                particles = 18;
                particleLife = 120;
                particleRad = 10;
                particleSize = 1.2f;
                rotateScl = 0.7f;
            }}, new DrawLiquidTile(Liquids.nitrogen) {{
                alpha = 0.65f;
            }}, new DrawDefault());
        }};
        discPhaseWaver = new AttributeCrafter("discfabric-phase-waver") {{
            size = 4;
            attribute = SFAttribute.radioactivity;
            baseEfficiency = 1f;
            maxBoost = 2f;
            boostScale = 1/16f;
            minEfficiency = 0;
            requirements(Category.crafting, with(Items.lead, 300, SFItems.leipAlloy, 220, SFItems.tayrAlloy, 150, SFItems.nanoCore, 220));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 40;
            liquidCapacity = 80;

            craftTime = 90;
            outputItem = new ItemStack(SFItems.discFabric, 12);
            consumePower(18);
            consumeItems(with(SFItems.fermium, 1, Items.phaseFabric, 8));
            consumeLiquid(SFLiquids.nanoFluid, 0.45f);

            ambientSound = Sounds.pulse;
            ambientSoundVolume = 0.68f;
            updateEffect = new WaveEffect() {{
                interp = Interp.circleOut;
                lifetime = 50;
                sizeFrom = 0;
                sizeTo = 12;
                strokeFrom = 10;
                colorFrom = colorTo = Color.valueOf("FFF1D2");
            }};
            craftEffect = new MultiEffect(
                    new WaveEffect() {{
                        interp = Interp.circleOut;
                        lifetime = 50;
                        sizeFrom = 0;
                        sizeTo = 46;
                        strokeFrom = 9;
                        colorFrom = Color.valueOf("FFF1D2A8");
                        colorTo = Color.valueOf("FFD197A8");
                    }},
                    new WaveEffect() {{
                        interp = Interp.circleOut;
                        startDelay = 8;
                        lifetime = 30;
                        sizeFrom = 0;
                        sizeTo = 46;
                        strokeFrom = 9;
                        colorFrom = Color.valueOf("FFF1D2A8");
                        colorTo = Color.valueOf("FFD197A8");
                    }});
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(SFLiquids.nanoFluid) {{
                drawLiquidLight = true;
                alpha = 0.75f;
            }},
                    new DrawArcSmelt() {{
                        midColor = Color.valueOf("FFF1D2");
                        flameColor = Color.valueOf("EEC591");
                        flameRad = 8;
                        circleSpace = 2;
                        flameRadiusScl = 8;
                        flameRadiusMag = 0.6f;
                        circleStroke = 1.5f;
                        alpha = 0.6f;
                        particles = 13;
                        particleLife = 13;
                        particleRad = 15;
                        particleStroke = 0.5f;
                        particleLen = 6;
                    }},
                    new DrawSpikes() {{
                        rotateSpeed = 16;
                        amount = 5;
                        stroke = 0.6f;
                        length = 14;
                        radius = 1;
                        color = Color.valueOf("FFD197");
                    }},
                    new DrawSpikes() {{
                        rotateSpeed = -6;
                        amount = 6;
                        stroke = 0.9f;
                        length = 14;
                        radius = 1;
                        color = Color.valueOf("FFD197");
                    }},
                    new DrawDefault(),
                    new DrawLiquidRegion(SFLiquids.nanoFluid),
                    new DrawFade() {{
                        scale = 8;
                        alpha = 0.8f;
                    }}
            );
        }};
        discPhaseKnitter = new AttributeCrafter("discfabric-phase-knitter") {{
            size = 3;
            attribute = SFAttribute.radioactivity;
            baseEfficiency = 0.75f;
            maxBoost = 2.5f;
            boostScale = 1/9f;
            minEfficiency = 0;
            requirements(Category.crafting, with(Items.silicon, 100, SFItems.waveSteel, 110, SFItems.fermium, 90, SFItems.rubidium, 180));
            hasPower = hasItems = true;
            itemCapacity = 30;

            craftTime = 24;
            outputItem = new ItemStack(SFItems.discFabric, 1);
            consumePower(8);
            consumeItems(with(SFItems.fermium, 1, SFItems.rareEarth, 3));

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.5f;
            craftEffect = new ParticleEffect() {{
                particles = 3;
                sizeFrom = 2.25f;
                length = 15;
                interp = Interp.fastSlow;
                sizeInterp = Interp.pow2In;
                lifetime = 60;
                colorFrom = colorTo = Color.valueOf("EEC591A8");
            }};
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawSpikes() {{
                        rotateSpeed = 8;
                        amount = 5;
                        stroke = 0.8f;
                        length = 8;
                        radius = 1;
                        color = Color.valueOf("FFD197");
                    }},
                    new DrawDefault(),
                    new DrawGlowRegion() {{
                        color = Color.valueOf("EEC951");
                    }}
            );
        }};
        airCollector = new AttributeCrafter("air-collector") {{
            size = 2;
            requirements(Category.crafting, with(Items.copper, 40, Items.metaglass, 20, Items.silicon, 40));
            hasPower = hasItems = hasLiquids = true;
            liquidCapacity = 30;
            attribute = Attribute.water;
            baseEfficiency = 1;
            maxBoost = 1;
            boostScale = 0.25f;
            minEfficiency = 0.5f;

            craftTime = 133f;
            outputItem = new ItemStack(Items.sporePod, 1);
            outputLiquid = new LiquidStack(Liquids.nitrogen, 0.025f);
            consumePower(1.75f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.nitrogen, 2.1f), new DrawDefault(),
                    new DrawParticles() {{
                        particles = 8;
                        particleSize = 1.5f;
                        particleRad = 12;
                        particleLife = 120;
                        alpha = 0.4f;
                        color = Items.sporePod.color;
                    }});
        }};
        nitrateMixer = new GenericCrafter("nitrate-mixer") {{
            size = 5;
            requirements(Category.crafting, with(Items.metaglass, 150, Items.silicon, 80, Items.plastanium, 50, SFItems.chromium, 80));
            hasPower = hasLiquids = true;
            liquidCapacity = 300;

            craftTime = 60;
            outputLiquid = new LiquidStack(SFLiquids.nitrate, 2f);
            consumePower(33.5f);
            consumeLiquids(LiquidStack.with(Liquids.water, 3f, Liquids.nitrogen, 1f));

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.water) {{
                suffix = "-liquid1";
            }}, new DrawLiquidRegion(Liquids.nitrogen) {{
                suffix = "-liquid2";
            }}, new DrawLiquidTile(SFLiquids.nitrate),
                    new DrawArcSmelt() {{
                        midColor = Color.valueOf("eeffc7");
                        flameColor = Color.valueOf("8c996dd8");
                        flameRad = 2;
                        circleSpace = 4;
                        flameRadiusScl = 10;
                        flameRadiusMag = 1;
                        circleStroke = 0.6f;
                        alpha = 0.6f;
                        particleRad = 16;
                        particles = 23;
                        particleLife = 37;
                        particleLen = 2;
                    }},
                    new DrawDefault());
        }};
        fractionator = new GenericCrafter("fractionator") {{
            size = 5;
            armor = 4;
            health = 3100;
            requirements(Category.crafting, with(Items.silicon,200, Items.plastanium, 150, Items.surgeAlloy, 180, SFItems.chromium, 100));
            hasPower = hasLiquids = hasItems = true;
            itemCapacity = 40;
            liquidCapacity = 300;

            craftTime = 60;
            consumePower(25f);
            consumeLiquid(Liquids.oil,3.6f);
            outputItem = new ItemStack(Items.coal, 3);
            outputLiquid = new LiquidStack(SFLiquids.mixGas, 5/3f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawLiquidTile(SFLiquids.mixGas){{alpha=0.35f;}}, new DrawDefault());
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.3f;
            craftEffect = Fx.smokeCloud;
        }};
        chemicalSiSmelter = new GenericCrafter("chemical-silicon-smelter") {{
            size = 4;
            health = 660;
            requirements(Category.crafting, with(Items.thorium, 70, Items.plastanium, 80, SFItems.waveSteel, 50));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 60;

            craftTime = 60;
            outputItem = new ItemStack(Items.silicon, 8);
            consumePower(8f);
            consumeItems(new ItemStack(Items.sand, 8));
            consumeLiquid(SFLiquids.mixGas, 0.2f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(SFLiquids.mixGas),
                    new DrawCrucibleFlame() {{
                        midColor = Color.valueOf("D97C7CA8");
                        flameColor = Color.valueOf("FFD0D0");
                        flameRad = 4.5f;
                        circleSpace = 3;
                        circleStroke = 0.6f;
                        flameRadiusScl = 16;
                        flameRadiusMag = 3;
                        particles = 33;
                        particleLife = 107;
                        particleRad = 16;
                        particleSize = 2.68f;
                        rotateScl = 1.7f;
                    }},
                    new DrawDefault());
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.5f;
        }};
        blastSiSmelter = new GenericCrafter("blast-silicon-smelter") {{
            size = 4;
            health = 660;
            requirements(Category.crafting, with(Items.thorium, 70, Items.silicon, 80, Items.plastanium, 50, SFItems.discFabric, 30));
            hasPower = hasItems = true;
            itemCapacity = 40;

            craftTime = 30;
            outputItem = new ItemStack(Items.silicon, 10);
            consumePower(10);
            consumeItems(with(Items.sand, 10, Items.blastCompound, 1));

            craftEffect = new MultiEffect(
                    new ParticleEffect() {{
                        particles = 8;
                        sizeFrom = 8;
                        length = 35;
                        baseLength = 13;
                        lifetime = 35;
                        colorFrom = colorTo = Color.valueOf("73737390");
                    }},
                    new ParticleEffect() {{
                        particles = 10;
                        line = true;
                        length = 43;
                        baseLength = 3;
                        lifetime = 20;
                        sizeInterp = Interp.pow2In;
                        colorTo = Color.valueOf("FFE176");
                    }},
                    new ParticleEffect() {{
                        particles = 1;
                        lifetime = 10;
                        length = 0;
                        sizeFrom = 0;
                        sizeTo = 45;
                        colorFrom = Color.valueOf("FFFFFF80");
                        colorTo = Color.valueOf("FFFFFF00");
                    }},
                    new WaveEffect() {{
                        lifetime = 10;
                        sizeFrom = 0;
                        sizeTo = 45;
                        strokeFrom = 3;
                        colorTo = Color.valueOf("FFFFFF00");
                    }}
            );
            ambientSound = Sounds.explosion;
            ambientSoundVolume = 0.5f;
        }};
        nitrReactor = new GenericCrafter("nitrification-reactor") {{
            size = 3;
            armor = 4;
            health = 660;
            requirements(Category.crafting, with(Items.metaglass, 110, Items.surgeAlloy, 20, SFItems.nanoCore, 80, SFItems.waveSteel, 90));
            hasPower = hasItems = hasLiquids = true;
            liquidCapacity = 60;

            craftTime = 30;
            outputLiquid = new LiquidStack(SFLiquids.nitratedOil, 0.3f);
            consumePower(2.24f);
            consumeItem(Items.sporePod, 1);
            consumeLiquid(Liquids.oil, 0.3f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawLiquidTile(SFLiquids.nitratedOil), new DrawDefault());
        }};
        nitrCentrifuge = new GenericCrafter("nitrification-centrifuge") {{
            size = 3;
            armor = 4;
            health = 460;
            requirements(Category.crafting, with(Items.metaglass, 150, Items.surgeAlloy, 60, SFItems.siliSteel, 80, SFItems.rubidium, 100));
            hasPower = hasLiquids = true;
            liquidCapacity = 90;

            craftTime = 60;
            outputLiquid = new LiquidStack(SFLiquids.nitratedOil, 0.5f);
            consumePower(1.1f);
            consumeLiquids(LiquidStack.with(Liquids.oil, 0.3f, SFLiquids.nitrate, 0.2f));

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(SFLiquids.nitratedOil), new DrawDefault(), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = -0.2f;
            }});
        }};
        nitrPrecipitator = new Separator("nitratedoil-precipitator") {{
            size = 4;
            armor = 8;
            health = 1100;
            requirements(Category.crafting, with(Items.plastanium, 90, SFItems.nanoCore, 100, SFItems.rubidium, 150));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 180;

            craftTime = 7.5f;
            results = with(Items.pyratite, 2, Items.blastCompound, 3);
            consumePower(4.5f);
            consumeLiquid(SFLiquids.nitratedOil, 0.9f);

            ambientSound = Sounds.grinding;
            ambientSoundVolume = 0.13f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCultivator() {{
                plantColor = Color.valueOf("36312D");
                plantColorLight = Color.valueOf("7CF389A8");
                bottomColor = Color.darkGray;
                bubbles = 8;
                sides = 4;
                recurrence = 6;
                radius = 5;
                timeScl = 120;
            }}, new DrawRegion("-rot1") {{
                rotateSpeed = 0.15f;
            }}, new DrawRegion("-rot2") {{
                rotateSpeed = -0.217f;
            }}, new DrawDefault());
        }};
        nanoActivator = new GenericCrafter("nanofluid-activator") {{
            size = 2;
            requirements(Category.crafting, with(Items.plastanium, 50, SFItems.nanoCore, 40));
            hasPower = hasItems = hasLiquids = true;
            liquidCapacity = 30;

            craftTime = 60;
            outputLiquid = new LiquidStack(SFLiquids.nanoFluid, 0.2f);
            consumePower(1.35f);
            consumeItems(new ItemStack(SFItems.nanoCore, 2));

            craftEffect = new WaveEffect() {{
                lifetime = 120;
                strokeFrom = 0.8f;
                strokeTo = 0;
                sizeFrom = 0;
                sizeTo = 24;
                sides = 4;
                colorTo = colorFrom = Color.valueOf("7CF389A8");
            }};
            lightLiquid = SFLiquids.nanoFluid;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(SFLiquids.nanoFluid) {{
                drawLiquidLight = true;
            }}, new DrawDefault());
        }};
        blastReagentMixer = new GenericCrafter("blastreagent-mixer") {{
            size = 4;
            armor = 5;
            health = 660;
            requirements(Category.crafting, with(Items.metaglass, 60, SFItems.waveSteel, 80, SFItems.chromium, 40, SFItems.rubidium, 80));
            hasPower = hasItems = hasLiquids = true;
            liquidCapacity = 90;

            craftTime = 60;
            outputLiquid = new LiquidStack(SFLiquids.blastReagent, 1.25f);
            consumePower(2.25f);
            consumeItem(SFItems.rareEarth, 1);
            consumeLiquid(SFLiquids.nitratedOil, 1f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(SFLiquids.nitratedOil), new DrawLiquidTile(SFLiquids.blastReagent), new DrawRegion("-rotator") {{
                rotateSpeed = -0.8f;
                rotation = 45;
                spinSprite = true;
            }}, new DrawDefault());
        }};
        clusMaker = new GenericCrafter("clus-maker") {{
            size = 2;
            health = 220;
            requirements(Category.crafting, with(Items.metaglass, 20, SFItems.waveSteel, 40, SFItems.rubidium, 20));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 15;
            liquidCapacity = 60;

            craftTime = 20;
            outputItem = new ItemStack(SFItems.clusBomb, 1);
            consumePower(2f);
            consumeItem(SFItems.strontium, 1);
            consumeLiquid(SFLiquids.blastReagent, 15 / 60f);
            drawer = new DrawMulti(new DrawDefault(), new DrawLiquidRegion(SFLiquids.blastReagent));
        }};
        tayriumSlelter = new GenericCrafter("tayrium-smelter") {{
            size = 3;
            requirements(Category.crafting, with(Items.lead, 100, SFItems.siliSteel, 60, Items.surgeAlloy, 45));
            hasPower = hasItems = true;

            craftTime = 80;
            outputItem = new ItemStack(SFItems.tayrAlloy, 1);
            consumePower(4.25f);
            consumeItems(with(SFItems.siliSteel, 1, Items.surgeAlloy, 1));

            craftEffect = new ParticleEffect() {{
                particles = 5;
                baseLength = 1.5f;
                length = 13;
                sizeFrom = 2.5f;
                lifetime = 30;
                region = "sfire-mod-loz";
                interp = Interp.fastSlow;
                sizeInterp = Interp.pow2In;
                colorFrom = colorTo = Color.valueOf("2CCDB1");
            }};
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.3f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame() {{
                flameColor = Color.valueOf("2CCDB1");
            }});
        }};
        tayriumCrucible = new AttributeCrafter("tayrium-crucible") {{
            size = 4;
            requirements(Category.crafting, with(Items.plastanium, 80, SFItems.strontium, 80, SFItems.waveSteel, 90, SFItems.fermium, 65));
            hasPower = hasItems = true;
            itemCapacity = 20;
            attribute = Attribute.heat;
            baseEfficiency = 1;
            maxBoost = 9f;
            boostScale = 1/16f;

            craftTime = 80;
            outputItem = new ItemStack(SFItems.tayrAlloy, 1);
            consumePower(5.25f);
            consumeItems(with(SFItems.strontium, 1, SFItems.siliSteel, 1, Items.silicon, 1));

            craftEffect = new RadialEffect() {{
                amount = 4;
                lengthOffset = 8.5f;
                effect = new ParticleEffect() {{
                    particles = 2;
                    baseLength = 1f;
                    length = 2.3f;
                    sizeFrom = 3.5f;
                    lifetime = 62;
                    region = "sfire-mod-loz";
                    interp = Interp.pow3Out;
                    sizeInterp = Interp.pow3In;
                    colorFrom = colorTo = Color.valueOf("2CCDB1");
                }};
            }};
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.3f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame() {{
                flameColor = Color.valueOf("2CCDB1");
                flameRadius = 0;
                flameRadiusIn = 0;
            }});
        }};
        leippiumSmelter = new GenericCrafter("leippium-smelter") {{
            size = 3;
            requirements(Category.crafting, with(SFItems.chromium, 80, SFItems.discFabric, 120, Items.silicon, 80));
            hasPower = hasItems = true;
            itemCapacity = 20;

            craftTime = 200;
            outputItem = new ItemStack(SFItems.leipAlloy, 2);
            consumePower(18);
            consumeItems(with(SFItems.fermium, 3, SFItems.chromium, 3));

            craftEffect = new RadialEffect() {{
                amount = 4;
                lengthOffset = 8;
                effect = new ParticleEffect() {{
                    particles = 2;
                    baseLength = 1f;
                    length = 3.3f;
                    sizeFrom = 2.5f;
                    lifetime = 62;
                    region = "sfire-mod-loz";
                    interp = Interp.pow3Out;
                    sizeInterp = Interp.pow5In;
                    colorFrom = colorTo = Color.valueOf("9B9DCF");
                }};
            }};
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.3f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame() {{
                flameColor = Color.valueOf("9B9DCF");
            }});
        }};
        leippiumCrucible = new GenericCrafter("leippium-crucible") {{
            size = 3;
            requirements(Category.crafting, with(SFItems.chromium, 50, SFItems.discFabric, 30, SFItems.waveSteel, 130));
            hasPower = hasItems = hasLiquids = true;

            craftTime = 50;
            outputItem = new ItemStack(SFItems.leipAlloy, 1);
            consumePower(2.35f);
            consumeItems(with(SFItems.fermium, 1, SFItems.chromium, 1));
            consumeLiquid(SFLiquids.blastReagent, 0.1f);

            craftEffect = new MultiEffect(
                    new ParticleEffect() {{
                        particles = 8;
                        sizeFrom = 10;
                        length = 35;
                        baseLength = 5;
                        lifetime = 35;
                        interp = interp.fastSlow;
                        sizeInterp = Interp.pow3In;
                        colorFrom = colorTo = Color.valueOf("737373");
                    }},
                    new ParticleEffect() {{
                        particles = 12;
                        line = true;
                        lenFrom = 16;
                        lenTo = 0;
                        strokeFrom = 2;
                        length = 46;
                        baseLength = 3;
                        lifetime = 12;
                        interp = interp.fastSlow;
                        sizeInterp = Interp.pow2In;
                        colorTo = Color.valueOf("FFE176");
                    }},
                    new WaveEffect() {{
                        lifetime = 10;
                        sizeFrom = 0;
                        sizeTo = 48;
                        strokeFrom = 15;
                        layer = 50;
                        interp = Interp.circleOut;
                        colorTo = Color.valueOf("737373A8");
                    }}
            );
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.3f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(SFLiquids.blastReagent), new DrawDefault());
        }};

        int largeHealth = 4;
        steelWall = new Wall("steel-wall") {{
            size = 1;
            requirements(Category.defense, with(Items.lead, 6, SFItems.waveSteel, 6));
            health = 700;
            armor = 12;
        }};
        steelWallLarge = new Wall("steel-wall-large") {{
            size = 2;
            requirements(Category.defense, ItemStack.mult(steelWall.requirements, 4));
            health = 700 * largeHealth;
            armor = 12;
        }};
        influxWall = new Wall("influx-wall") {{
            size = 1;
            requirements(Category.defense, with(Items.plastanium, 2, Items.surgeAlloy, 3, SFItems.siliSteel, 3));
            health = 1900;
            armor = 13;
            lightningChance = 0.1f;
            lightningDamage = 50;
            lightningLength = 25;
            insulated = true;
            absorbLasers = true;
        }};
        influxWallLarge = new Wall("influx-wall-large") {{
            size = 2;
            requirements(Category.defense, ItemStack.mult(influxWall.requirements, 4));
            health = 1900 * largeHealth;
            armor = 13;
            lightningChance = 0.1f;
            lightningDamage = 50;
            lightningLength = 25;
            insulated = true;
            absorbLasers = true;
        }};
        discWall = new ShieldWall("discfabric-wall") {{
            size = 2;
            requirements(Category.defense, with(SFItems.siliSteel, 24, SFItems.fermium, 32, SFItems.discFabric, 20));
            consumePower(0.5f);
            health = 1600 * largeHealth;

            shieldHealth = 1000;
            regenSpeed = 1.15f;
            breakCooldown = 180;

            outputsPower = false;
            hasPower = true;
            consumesPower = true;
            conductivePower = true;
            absorbLasers = true;
            chanceDeflect = 180;
            flashHit = true;
        }};
        fermWall = new Wall("fermium-wall") {{
            size = 1;
            requirements(Category.defense, with(SFItems.fermium, 16));
            health = 2200;
            armor = 14;
            absorbLasers = true;
        }};
        fermWallLarge = new Wall("fermium-wall-large") {{
            size = 2;
            requirements(Category.defense, with(SFItems.fermium, 64));
            health = 2200 * largeHealth;
            armor = 14;
            absorbLasers = true;
        }};
        fermDoor = new AutoDoor("fermium-auto-door") {{
            size = 2;
            requirements(Category.defense, with(Items.silicon, 30, SFItems.siliSteel, 30, SFItems.fermium, 50));
            health = 2000 * largeHealth;
            armor = 14;
            absorbLasers = true;
        }};
        leipWall = new Wall("complex-armor-wall") {{
            size = 1;
            requirements(Category.defense, with(Items.metaglass, 10, Items.plastanium, 16, SFItems.siliSteel, 16, SFItems.leipAlloy, 50));
            health = 6800;
            armor = 32;
            insulated = true;
            absorbLasers = true;
            placeableLiquid = true;
        }};
        leipWallLarge = new Wall("complex-armor-wall-large") {{
            size = 2;
            requirements(Category.defense, ItemStack.mult(leipWall.requirements, 4));
            health = 6800 * largeHealth;
            armor = 32;
            insulated = true;
            absorbLasers = true;
            placeableLiquid = true;
        }};
        discContainmentUnit = new Wall("energy-containment-unit") {{
            size = 4;
            requirements(Category.defense, with(SFItems.fermium, 85, SFItems.discFabric, 300));
            buildCostMultiplier = 15 / 62f;
            health = 1000;
            //destroyBullet = SFDeadBullet.discContainmentUnit;
        }};
        armorContainmentUnit = new Wall("armor-containment-unit") {{
            size = 4;
            requirements(Category.defense, with(SFItems.fermium, 85, Items.plastanium, 200, Items.surgeAlloy, 200));
            buildCostMultiplier = 15 / 67f;
            health = 1000;
        }};
        tayrContainmentUnit = new Wall("tayralloy-containment-unit") {{
            size = 4;
            requirements(Category.defense, with(SFItems.tayrAlloy, 500));
            buildCostMultiplier = 0.5f;
            health = 1000;
        }};

        nanoMendProjector = new MendProjector("nano-mend-projector") {{
            health = 450;
            size = 3;
            requirements(Category.effect, with(Items.plastanium, 90, Items.surgeAlloy, 60, SFItems.nanoCore, 80));
            range = 280;
            reload = 120;
            healPercent = 8;
            phaseBoost = 5;
            phaseRangeBoost = 0;
            canOverdrive = false;
            consumePower(15f);
            consumeItem(SFItems.nanoCore).boost();
        }};
        nanoRegenProjector = new RegenProjector("nano-regen-projector") {{
            health = 1000;
            size = 4;
            requirements(Category.effect, with(Items.plastanium, 50, Items.surgeAlloy, 100, SFItems.siliSteel, 120, SFItems.nanoCore, 150));
            range = 70;
            healPercent = 0.025f;
            canOverdrive = false;

            optionalUseTime = 360;
            optionalMultiplier = 6;
            consumePower(15f);
            consumeItem(SFItems.nanoCore, 5).boost();

            effectChance = 0.5f;
            effect = new ParticleEffect() {{
                particles = 1;
                baseLength = 30;
                length = -30;
                lifetime = 30;
                spin = 6;
                interp = Interp.pow3Out;
                sizeInterp = Interp.pow3In;
                region = "sfire-mod-tri";
                colorFrom = colorTo = Color.valueOf("97FFA8");
            }};
            drawer = new DrawMulti(new DrawDefault(),
                    new DrawPulseShape() {{
                        layer = 110;
                        stroke = 3;
                        timeScl = 120;
                        color = Color.valueOf("97FFA8");
                    }},
                    new DrawShape() {{
                        layer = 110;
                        radius = 5;
                        useWarmupRadius = true;
                        timeScl = 1.22f;
                        color = Color.valueOf("97FFA8");
                    }}
            );
        }};
        ironCurtain = new ForceProjector("iron-curtain") {{
            health = 1600;
            armor = 8;
            size = 2;
            requirements(Category.effect, with(Items.silicon, 240, Items.plastanium, 90, SFItems.fermium, 200, SFItems.tayrAlloy, 80));

            shieldHealth = 6000;
            radius = 96;
            phaseRadiusBoost = 26;
            phaseShieldBoost = 6000;
            itemConsumer = consumeItem(SFItems.tayrAlloy, 5).boost();
            cooldownNormal = 8;
            cooldownBrokenBase = 3;
            coolantConsumption = 0.4f;
            consumePower(10f);
            absorbEffect = new ParticleEffect() {{
                particles = 1;
                length = baseLength = 0;
                sizeFrom = 0;
                sizeTo = 13;
                sizeInterp = Interp.pow10Out;
                lifetime = 20;
                layer = 120;
                colorFrom = Color.black;
                colorTo = Color.black.cpy().a(0);
            }};
        }};
        ironDome = new ForceProjector("iron-dome") {{
            health = 13500;
            armor = 8;
            size = 5;
            requirements(Category.effect, with(Items.silicon, 240, Items.plastanium, 90, SFItems.fermium, 200, SFItems.tayrAlloy, 80));
            itemCapacity = 21;
            liquidCapacity = 80;

            shieldHealth = 25000;
            radius = 360;
            phaseRadiusBoost = 0;
            phaseShieldBoost = 15000;
            itemConsumer = consumeItem(SFItems.discFabric, 5).boost();
            cooldownNormal = 8;
            cooldownLiquid = 2.5f;
            cooldownBrokenBase = 5;
            coolantConsumption = 0.5f;
            consumePower(16f);
            absorbEffect = new ParticleEffect() {{
                particles = 1;
                length = baseLength = 0;
                sizeFrom = 5;
                lifetime = 180;
                sizeInterp = Interp.pow3In;
                colorFrom = Color.valueOf("FEEBB3");
                colorTo = Color.valueOf("FEEBB300");
            }};
        }};

        waveConveyor = new Conveyor("wavesteel-conveyor") {{
            health = 220;
            requirements(Category.distribution, with(Items.lead, 1, SFItems.waveSteel, 1));
            junctionReplacement = waveJunction;
            bridgeReplacement = waveBridge;
            speed = 0.19f;
            displayedSpeed = 20;
            placeableLiquid = true;
        }};
        rearmoredConveyor = new ArmoredConveyor("complex-armored-conveyor") {{
            health = 560;
            armor = 8;
            requirements(Category.distribution, with(Items.titanium, 1, Items.metaglass, 1, SFItems.siliSteel, 1, SFItems.fermium, 1));
            junctionReplacement = waveJunction;
            bridgeReplacement = discConveyor;
            speed = 0.19f;
            displayedSpeed = 20;
            insulated = absorbLasers = placeableLiquid = true;
        }};
        silisteelConveyor = new StackConveyor("silisteel-conveyor") {{
            requirements(Category.distribution, with(Items.lead, 2, Items.plastanium, 1, SFItems.siliSteel, 1));
            health = 130;
            speed = 8 / 60f;
            itemCapacity = 30;

            outputRouter = true;
            hasPower = consumesPower = conductivePower = true;

            underBullets = true;
            baseEfficiency = 0.5f;
            glowColor = Color.valueOf("B9C0EBA8");
            consumePower(1 / 60f);
            placeableLiquid = true;
        }};
        waveJunction = new Junction("wavesteel-junction") {{
            requirements(Category.distribution, with(Items.lead, 2, SFItems.waveSteel, 1));
            ((Conveyor) waveConveyor).junctionReplacement = this;
            ((Conveyor) rearmoredConveyor).junctionReplacement = this;
            speed = 14;
            health = 220;
            capacity = 7;
            buildCostMultiplier = 6f;
            placeableLiquid = true;
        }};
        waveBridge = new ItemBridge("wavesteel-bridge") {{
            health = 220;
            requirements(Category.distribution, with(Items.lead, 6, SFItems.waveSteel, 4));
            ((Conveyor) waveConveyor).bridgeReplacement = this;
            hasPower = false;
            range = 6;
            transportTime = 3f;
            bridgeWidth = 8f;
            arrowSpacing = 6f;
            placeableLiquid = true;
        }};
        discConveyor = new ItemBridge("discfabric-bridge") {{
            health = 560;
            armor = 8;
            requirements(Category.distribution, with(Items.thorium, 20, Items.silicon, 15, SFItems.siliSteel, 5, SFItems.discFabric, 5, SFItems.fermium, 5));
            ((Conveyor) rearmoredConveyor).bridgeReplacement = this;
            range = 26;
            transportTime = 40 / 60f;
            arrowSpacing = 8;
            arrowOffset = 4;
            arrowTimeScl = 12;
            bridgeWidth = 8;
            pulse = true;
            consumePower(0.5f);
        }};
        discMassDriver = new MassDriver("discfabric-massdriver") {{
            size = 4;
            health = 2500;
            armor = 8;
            requirements(Category.distribution, with(Items.plastanium, 80, SFItems.siliSteel, 120, SFItems.fermium, 200, SFItems.discFabric, 110));
            hasPower = hasItems = true;
            itemCapacity = 300;

            minDistribute = 60;
            reload = 100;
            rotateSpeed = 2.5f;
            bulletSpeed = 8.8f;
            range = 800;

            consumePower(13f);
            shootSound = Sounds.laser;
            shootEffect = Fx.instShoot;
            smokeEffect = new ParticleEffect() {{
                particles = 19;
                line = true;
                sizeInterp = interp = Interp.circleOut;
                lenFrom = 30;
                strokeFrom = 3;
                length = 95;
                lifetime = 80;
                colorFrom = colorTo = Color.valueOf("FEEBB3");
                cone = 10;
            }};
        }};

        tidalPump = new Pump("tidal-pump") {{
            size = 4;
            requirements(Category.liquid, with(Items.metaglass, 300, Items.silicon, 200, Items.plastanium, 130, SFItems.siliSteel, 150, Items.surgeAlloy, 90));
            hasLiquids = hasPower = true;
            squareSprite = false;
            liquidCapacity = 800f;
            pumpAmount = 1;
            consumePower(12f);
            drawer = new DrawMulti(new DrawPumpLiquid(), new DrawDefault());
        }};
        silisteelConduit = new Conduit("silisteel-conduit") {{
            health = 220;
            requirements(Category.liquid, with(Items.lead, 1, Items.metaglass, 2, SFItems.siliSteel, 1));
            liquidCapacity = 35;
            liquidPressure = 2.25f;
            bridgeReplacement = Blocks.phaseConduit;
            explosivenessScale = flammabilityScale = 16f/50f;
        }};
        reArmoredConduit = new ArmoredConduit("complex-armored-conduit") {{
            health = 560;
            armor = 8;
            requirements(Category.liquid, with(Items.titanium, 1, Items.metaglass, 2, SFItems.siliSteel, 1, SFItems.fermium, 1));
            liquidCapacity = 150;
            liquidPressure = 2.25f;
            bridgeReplacement = discConduit;
            insulated = absorbLasers = placeableLiquid = true;
            explosivenessScale = flammabilityScale = 16f/50f;
        }};
        silisteelTank = new LiquidRouter("silisteel-tank") {{
            size = 4;
            health = 3600;
            armor = 8;
            requirements(Category.liquid, with(Items.plastanium, 70, Items.metaglass, 220, SFItems.siliSteel, 60, SFItems.chromium, 110));
            liquidCapacity = 6000;
            absorbLasers = true;
        }};
        discConduit = new LiquidBridge("discfabric-liquid-bridge") {{
            health = 560;
            armor = 8;
            requirements(Category.liquid, with(Items.metaglass, 20, Items.silicon, 15, SFItems.siliSteel, 5, SFItems.discFabric, 5, SFItems.fermium, 5));
            ((Conduit) reArmoredConduit).bridgeReplacement = this;
            range = 26;
            arrowSpacing = 8;
            arrowOffset = 4;
            arrowTimeScl = 12;
            bridgeWidth = 8;
            liquidCapacity = 150;
            explosivenessScale = flammabilityScale = 20f/100f;
            consumePower(0.5f);
        }};

        armorBattery = new Battery("armor-battery") {{
            size = 4;
            health = 4400;
            armor = 16;
            requirements(Category.power, with(Items.silicon, 360, Items.plastanium, 160, SFItems.chromium, 200, SFItems.tayrAlloy, 160, SFItems.discFabric, 110));
            consumePowerBuffered(937500f);
            drawer = new DrawMulti(new DrawDefault(),
                    new DrawPower() {{
                        emptyLightColor = Color.valueOf("18473F");
                        fullLightColor = Color.valueOf("FFD197");
                    }},
                    new DrawRegion("-top")
            );
            destroySound = Sounds.release;
            destroyEffect = new MultiEffect(
                    new ParticleEffect() {{
                        particles = 16;
                        baseLength = 12f;
                        length = 35f;
                        sizeFrom = 6f;
                        lifetime = 86;
                        sizeInterp = Interp.pow3In;
                        colorFrom = colorTo = Color.valueOf("2CCDB1");
                    }},
                    new RadialEffect() {{
                        amount = 4;
                        lengthOffset = 16f;
                        effect = new ParticleEffect() {{
                            particles = 16;
                            baseLength = 12f;
                            length = 22.4f;
                            sizeFrom = 4.5f;
                            lifetime = 110;
                            region = "sfire-mod-loz";
                            interp = Interp.pow5Out;
                            sizeInterp = Interp.pow10In;
                            colorFrom = Color.valueOf("FFD197");
                            colorTo = Color.valueOf("FFD197");
                            cone = 10;
                        }};
                    }},
                    new WaveEffect() {{
                        lifetime = 12;
                        sizeTo = 66;
                        strokeFrom = 9;
                        strokeTo = 0;
                        interp = Interp.circleOut;
                        colorFrom = Color.valueOf("FFD197");
                        colorTo = Color.valueOf("18473F");
                    }}
            );
        }};
        armorNode = new PowerNode("armor-node") {{
            size = 3;
            health = 1800;
            armor = 22;
            requirements(Category.power, with(Items.silicon, 15, Items.metaglass, 10, SFItems.chromium, 10, SFItems.discFabric, 7));
            buildCostMultiplier = 0.3f;

            maxNodes = 25;
            laserRange = 22;
            laserScale = 0.5f;
            laserColor2 = Color.valueOf("5F6A89");

            placeableLiquid = true;
            destroyBullet = new BulletType(0, 1600) {{
                splashDamageRadius = 20;
                splashDamage = 60;
                lightning = 4;
                lightColor = Color.valueOf("EEC591");
                lightningDamage = 25;
                absorbable = false;
                instantDisappear = true;
                hitShake = 4;
                hitSound = Sounds.plasmaboom;
                hitEffect = Fx.instBomb;
            }};
        }};
        discNodeTower = new PowerNode("discfabric-node-tower") {{
            size = 3;
            health = 360;
            requirements(Category.power, with(Items.titanium, 35, SFItems.tayrAlloy, 10, SFItems.discFabric, 15));
            buildCostMultiplier = 0.3f;

            maxNodes = 3;
            laserRange = 80;
            laserScale = 0.5f;
            laserColor2 = Color.valueOf("5F6A89");
        }};
        gasSmoker = new ConsumeGenerator("gas-generator") {{
            size = 1;
            requirements(Category.power, with(Items.metaglass, 30, Items.titanium, 40));
            hasPower = hasLiquids = true;

            powerProduction = 10 / 6f;
            consumeLiquid(SFLiquids.mixGas, 0.1f);

            generateEffect = Fx.steam;
            effectChance = 0.01f;
            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());
            ambientSound = Sounds.steam;
            ambientSoundVolume = 0.02f;
        }};
        coalPyrolyzer = new ConsumeGenerator("coal-pyrolyzer") {{
            size = 3;
            health = 460;
            requirements(Category.power, with(Items.metaglass, 100, Items.thorium, 90, SFItems.siliSteel, 70, SFItems.rubidium, 80));
            hasPower = hasItems = hasLiquids = true;
            liquidCapacity = 60;
            itemCapacity = 15;

            itemDuration = 30f;
            powerProduction = 8;
            consumeLiquid(Liquids.water, 1);
            consumeItem(Items.coal, 2);
            outputLiquid = new LiquidStack(SFLiquids.mixGas, 0.6f);

            generateEffect = new MultiEffect(Fx.steam, Fx.generatespark);
            effectChance = 0.02f;
            drawer = new DrawMulti(new DrawDefault(),
                    new DrawRegion("-rot1") {{
                        rotation = 60;
                        rotateSpeed = 9f;
                    }},
                    new DrawRegion("-rot2") {{
                        rotation = 45;
                        rotateSpeed = -1.25f;
                        spinSprite = true;
                    }},
                    new DrawRegion("-top"),
                    new DrawLiquidRegion(Liquids.water),
                    new DrawLiquidRegion(SFLiquids.mixGas));
            ambientSound = Sounds.steam;
            ambientSoundVolume = 0.04f;
        }};
        heatGenerator = new ThermalGenerator("heat-generator") {{
            size = 3;
            health = 660;
            requirements(Category.power, with(Items.graphite, 200, Items.metaglass, 150, SFItems.siliSteel, 130, SFItems.rubidium, 80));
            buildCostMultiplier = 0.9f;
            attribute = Attribute.heat;

            powerProduction = 2.7f;
            generateEffect = Fx.redgeneratespark;
            effectChance = 0.08f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade() {{
                scale = 10;
            }});
            floating = true;
            ambientSound = Sounds.hum;
        }};
        radiGenerator = new ThermalGenerator("radiation-generator") {{
            size = 2;
            health = 400;
            requirements(Category.power, with(Items.surgeAlloy, 50, SFItems.waveSteel, 200, SFItems.discFabric, 60));
            buildCostMultiplier = 0.9f;
            attribute = SFAttribute.radioactivity;

            powerProduction = 2f;
            generateEffect = new ParticleEffect(){{
                particles = 5;
                sizeFrom = 3;
                sizeTo = 3;
                length = 8;
                lifetime = 90;
                interp = Interp.pow3Out;
                sizeInterp = Interp.pow10In;
                colorFrom = SFColor.discDark.cpy().a(0.8f);
                colorTo = SFColor.disc.cpy().a(0f);
            }};
            effectChance = 0.04f;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade() {{
                scale = 10;
            }});
            ambientSound = Sounds.hum;
        }};
        fermReactor = new NuclearReactor("fermium-reactor") {{
            size = 3;
            health = 1600;
            requirements(Category.power, with(Items.lead, 300, Items.graphite, 90, Items.metaglass, 60, SFItems.siliSteel, 130, SFItems.fermium, 110));
            buildCostMultiplier = 0.8f;
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 60;

            powerProduction = 24;
            consumeLiquid(Liquids.cryofluid, 0.18f);
            consumeItem(SFItems.fermium, 1);
            heating = 0.006f;
            coolantPower = 3f;
            itemDuration = 300;
            fuelItem = SFItems.fermium;

            explosionShake = 9;
            explosionShakeDuration = 120f;
            explosionDamage = 2300;
            explosionRadius = 35;
            explodeSound = SFSounds.hugeExplosion;
            explodeEffect = new MultiEffect(
                    new ExplosionEffect(){{
                        waveLife = 20f;
                        waveRad = 320;
                        waveStroke = 18;
                        waveColor = Pal.bulletYellow;
                        smokes = 0;
                        sparks = 25;
                        sparkStroke = 3f;
                        sparkLen = 120f;
                        sparkRad = 220f;
                        sparkColor = Color.valueOf("DEDEDE70");
                    }},
                    new ParticleEffect(){{
                        particles = 25;
                        sizeInterp = Interp.pow5In;
                        interp = Interp.pow10Out;
                        sizeFrom = 35f;
                        length = 280f;
                        lifetime = 250f;
                        colorFrom = colorTo = Color.valueOf("DEDEDE70");
                    }},
                    new ParticleEffect(){{
                        particles = 35;
                        sizeInterp = Interp.pow5In;
                        interp = Interp.pow10Out;
                        sizeFrom = 30f;
                        length = 290f;
                        lifetime = 300f;
                        colorFrom = colorTo = Color.valueOf("DEDEDE70");
                    }},
                    new ParticleEffect(){{
                        particles = 40;
                        sizeInterp = Interp.pow5In;
                        interp = Interp.pow10Out;
                        sizeFrom = 20;
                        length = 300f;
                        lifetime = 350f;
                        colorFrom = colorTo = Color.valueOf("DEDEDE70");
                    }}
            );
        }};
        fissionReactor = new ImpactReactor("fission-reactor") {{
            size = 6;
            health = 10500;
            requirements(Category.power, with(SFItems.waveSteel, 1200, Items.metaglass, 600, SFItems.fermium, 780, SFItems.siliSteel, 800, SFItems.tayrAlloy, 700));
            buildCostMultiplier = 0.8f;
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 600;
            warmupSpeed = 0.0005f;

            powerProduction = 180;
            consumePower(28f);
            consumeLiquid(Liquids.cryofluid, 1.8f);
            consumeItems(with(Items.blastCompound, 5, SFItems.fermium, 3));
            itemDuration = 300;

            explosionMinWarmup = 0.8f;
            explosionShake = 8;
            explosionShakeDuration = 22f;
            explosionDamage = 18000;
            explosionRadius = 35;
            explodeSound = SFSounds.hugeExplosion;
            //explodeEffect = SFFx.fissionExplosion;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawPlasma() {{
                        plasma1 = SFColor.tayrLight;
                        plasma2 = SFColor.tayr;
                    }},
                    new DrawDefault(),
                    new DrawFlame() {{flameColor = Color.valueOf("BFFFDB");}},
                    new DrawLiquidRegion(Liquids.cryofluid)) {{lightLiquid = Liquids.cryofluid;}};
            destroyBullet = new ExplosionBulletType(1000,480){{
                buildingDamageMultiplier = 6f;
                makeFire = true;
                hitSound = SFSounds.hugeExplosion;
                hitSoundVolume = 3;
                hitShake = 50f;
                hitEffect = new WaveEffect(){{
                    lifetime = 60f;
                    sizeFrom = 20f;
                    sizeTo = 530f;
                    strokeFrom = 30;
                    interp = Interp.circleOut;
                    colorTo = SFColor.disc;
                }};
                status = SFStatusEffects.breakdown;
                statusDuration = 1200f;
                despawnEffect = new MultiEffect(
                        Fx.impactReactorExplosion,
                        new ParticleEffect(){{
                            particles = 1;
                            sizeFrom = 10;
                            sizeTo = 480f;
                            length = 0;
                            lifetime = 22f;
                            sizeInterp = Interp.pow10Out;
                            colorTo = SFColor.disc;
                        }},
                        new ParticleEffect(){{
                            particles = 1;
                            sizeFrom = 480;
                            startDelay = 19f;
                            length = 0;
                            lifetime = 60;
                            colorFrom = colorTo = SFColor.disc;
                        }}
                );
                fragBullets = 60;
                fragLifeMin = 0.15f;
                fragLifeMax = 1.1f;
                fragBullet = new BasicBulletType(30, 150,"circle-bullet") {{
                    hittable = false;
                    absorbable = false;
                    collides = false;
                    drag = 0.1f;
                    lifetime = 240f;
                    splashDamage = 100f;
                    splashDamageRadius = 180f;
                    buildingDamageMultiplier = 8f;
                    makeFire = true;
                    hitSound = Sounds.explosionbig;
                    hitSoundVolume = 10;
                    hitShake = 20f;
                    width = height = 30f;
                    shrinkY = 0f;
                    trailColor = backColor = SFColor.disc;
                    trailLength = 18;
                    trailWidth = 2.25f;
                    hitEffect = new WaveEffect(){{
                        lifetime = 35;
                        sizeFrom = 40f;
                        sizeTo = 230f;
                        strokeFrom = 30;
                        interp = Interp.circleOut;
                        colorFrom = colorTo = SFColor.disc;
                    }};
                    status = SFStatusEffects.breakdown;
                    statusDuration = 1000f;
                    despawnEffect = new MultiEffect(
                            new ParticleEffect(){{
                                particles = 1;
                                sizeFrom = 10;
                                sizeTo = 180;
                                length = 0;
                                lifetime = 12f;
                                sizeInterp = Interp.pow10Out;
                                colorTo = SFColor.disc;
                            }},
                            new ParticleEffect(){{
                                particles = 1;
                                sizeFrom = 180;
                                startDelay = 9f;
                                length = 0;
                                lifetime = 60;
                                colorFrom = colorTo = SFColor.disc;
                            }}
                    );
                }};
            }};
        }};
        arcFissionReactor = new ConsumeGenerator("arc-fission-reactor") {{
            size = 10;
            armor = 18;
            health = 33000;
            requirements(Category.power, with(Items.metaglass, 1100, Items.plastanium, 600, SFItems.fermium, 1700, SFItems.nanoCore, 1200, SFItems.discFabric, 800));
            buildCostMultiplier = 0.8f;
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 300;
            warmupSpeed = 0.006f;

            powerProduction = 550f;
            itemDuration = 20f;
            consumeLiquid(SFLiquids.nanoFluid, 0.9f);
            consume(new ConsumeItemRadioactive(0.75f));
            consume(new ConsumeItemExplode() {{
                baseChance = 0.1f;
                damage = 50f;
            }});

            generateEffect = Fx.generatespark;
            ambientSound = Sounds.techloop;
            ambientSoundVolume = 0.5f;
            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawArcSmelt() {{
                        midColor = Color.valueOf("FFF1D2");
                        flameColor = Color.valueOf("EEC591");
                        flameRad = 11.62f;
                        flameRadiusScl = 16.6f;
                        flameRadiusMag = 1.6f;
                        circleSpace = 4.98f;
                        circleStroke = 1.66f;
                        alpha = 0.48f;
                        particles = 54;
                        particleLife = 28.22f;
                        particleRad = 33.2f;
                        particleStroke = 0.83f;
                        particleLen = 3.32f;
                    }},
                    new DrawArcSmelt() {{
                        midColor = Color.valueOf("EEC591");
                        flameColor = Color.valueOf("EEC591");
                        flameRad = 0;
                        flameRadiusScl = 14.94f;
                        flameRadiusMag = 1.66f;
                        circleSpace = 16f;
                        circleStroke = 3.32f;
                        alpha = 0.78f;
                        particles = 26;
                        particleLife = 77.19f;
                        particleRad = 33.2f;
                        particleStroke = 1.66f;
                        particleLen = 9.96f;
                    }},
                    new DrawArcSmelt() {{
                        midColor = Color.valueOf("FEEBB3");
                        flameColor = Color.valueOf("EEC591");
                        flameRad = 4.98f;
                        flameRadiusScl = 33.2f;
                        flameRadiusMag = 0.92f;
                        circleSpace = 28.22f;
                        circleStroke = 1.49f;
                        alpha = 0.8f;
                        particles = 18;
                        particleLife = 162.68f;
                        particleRad = 33.2f;
                        particleStroke = 3f;
                        particleLen = 14.94f;
                    }},
                    new DrawDefault(),
                    new DrawLiquidRegion(SFLiquids.nanoFluid) {{
                        suffix = "-liquid";
                    }}
            );
        }};
        //hypermagneticReactor = new ConsumeGenerator("hypermagnetic-reactor") {{}};

        energyDrill = new Drill("energe-drill") {{
            size = 4;
            requirements(Category.production, with(Items.copper, 45, Items.lead, 35, Items.graphite, 45));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 20;
            drillTime = 360;
            tier = 3;

            consumePower(1.75f);
            consumeLiquid(Liquids.water, 0.3f).boost();
            liquidBoostIntensity = 2;
            drillEffect = Fx.mineBig;
            updateEffect = Fx.pulverizeMedium;
        }};
        heavyDrill = new Drill("heavy-drill") {{
            size = 4;
            health = 1200;
            requirements(Category.production, with(Items.silicon,160, Items.plastanium,45, Items.surgeAlloy,80, SFItems.siliSteel,70));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 30;
            drillTime = 252;
            tier = 10;
            hardnessDrillMultiplier = 10;

            consumePower(11f);
            consumeLiquid(Liquids.cryofluid, 0.3f).boost();
            liquidBoostIntensity = 1.8f;

            rotateSpeed = 1.5f;
            warmupSpeed = 0.002f;
            drawRim = false;
            drillEffect = Fx.mineHuge;
            updateEffect = new ParticleEffect() {{
                particles = 3;
                interp = Interp.fastSlow;
                sizeFrom = 1;
                sizeTo = 9;
                length = 60;
                lifetime = 300;
                colorFrom = Color.valueOf("EEC591");
                colorTo = Color.valueOf("EEC59100");
                cone = 20;
            }};
        }};
        blastWell = new BurstDrill("blast-ore-well") {{
            size = 5;
            requirements(Category.production, with(Items.graphite,180, Items.thorium,110, Items.phaseFabric,80, SFItems.waveSteel,80));
            hasLiquids = hasItems = true;
            itemCapacity = 50;
            liquidCapacity = 20;
            drillTime = 80;
            tier = 10;
            arrows = 4;
            arrowSpacing = 1.5f;
            arrowOffset = 0;
            arrowColor = Color.valueOf("FEC59E80");
            drillMultipliers.put(Items.sand, 2f);
            blockedItem = SFItems.fermium;

            consumeLiquid(SFLiquids.blastReagent, 0.1f);
            liquidBoostIntensity = 0;
            drillEffect = new MultiEffect(
                    new WrapEffect() {{
                        effect = Fx.dynamicExplosion;
                        color = Color.valueOf("FEC59EF1");
                        rotation = 1.5f;
                    }},
                    Fx.mineImpactWave.wrap(Items.blastCompound.color, 45f)
            );
        }};
        quantumOreExtractor = new BurstDrill("quantum-extractor") {{
            size = 5;
            health = 3000;
            requirements(Category.production, with( SFItems.siliSteel,800, SFItems.discFabric,400, SFItems.leipAlloy,500));
            hasLiquids = hasItems = true;
            itemCapacity = 100;
            liquidCapacity = 60;
            drillTime = 5;
            tier = 20;
            arrows = 0;

            consumePower(20);
            consumeLiquid(SFLiquids.nanoFluid, 0.6f);
            liquidBoostIntensity = 0;
            drillEffect = new ParticleEffect(){{
                particles = 1;
                interp = Interp.fastSlow;
                sizeFrom = 1;
                sizeTo = 3;
                length = 25;
                baseLength = 30;
                lifetime = 300;
                colorFrom = SFColor.discDark;
                colorTo = Color.white.cpy().a(0);
            }};
        }};
        waterExtractor = new SolidPump("water-extractor") {{
            size = 3;
            health = 460;
            requirements(Category.production, with(Items.thorium, 110, Items.metaglass, 80, SFItems.siliSteel, 50));
            hasPower = hasLiquids = true;
            liquidCapacity = 120;
            warmupSpeed = 0.008f;

            result = Liquids.water;
            pumpAmount = 36.1f / 60f;
            baseEfficiency = 1;
            attribute = Attribute.water;
            consumePower(56 / 6f);
            rotateSpeed = 1.3f;
        }};
        oilPressurePump = new Fracker("oil-pressure-pump") {{
            size = 3;
            health = 550;
            requirements(Category.production, with(Items.graphite, 160, Items.plastanium, 180, Items.surgeAlloy, 80, SFItems.rubidium, 110));
            hasPower = hasLiquids = true;
            hasItems = false;
            liquidCapacity = 300;

            result = Liquids.oil;
            pumpAmount = 0.8f;
            baseEfficiency = 0;
            attribute = Attribute.oil;
            consumePower(4f);
            consumeLiquid(Liquids.nitrogen, 0.4f);

            updateEffect = Fx.smokeCloud;
            updateEffectChance = 0.08f;
            drawer = new DrawMulti(new DrawDefault(), new DrawLiquidRegion(Liquids.oil) {{
                suffix = "-liq1";
            }}, new DrawRegion("-rotator") {{
                rotateSpeed = -6.6f;
                spinSprite = true;
            }}, new DrawRegion("-top"), new DrawLiquidRegion(Liquids.nitrogen) {{
                suffix = "-liq2";
            }});
        }};
        sporeCultivator = new AttributeCrafter("spore-cultivator") {{
            size = 4;
            requirements(Category.production, with(Items.metaglass, 130, Items.titanium, 50, SFItems.waveSteel, 40));
            hasPower = hasLiquids = hasItems = true;
            liquidCapacity = 110;
            attribute = Attribute.spores;
            boostScale = 0.125f;
            maxBoost = 2f;

            craftTime = 20;
            outputItem = new ItemStack(Items.sporePod, 3);
            consumeLiquid(SFLiquids.mixGas, 1.2f);
            consumePower(10 / 3f);

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawCultivator(),
                    new DrawLiquidTile(SFLiquids.mixGas) {{
                        alpha = 0.5f;
                    }},
                    new DrawRegion("-top")
            );
        }};
        frontCore = new SFCore("front-line-core") {{
            size = 3;
            health = 1000;
            armor = 8;
            requirements(Category.effect, with(SFItems.waveSteel, 800, Items.silicon, 800));
            alwaysUnlocked = true;
            itemCapacity = 800;
            unitCapModifier = 3;
        }};
        industryCore = new CoreBlock("industry-core") {{
            size = 6;
            requirements(Category.effect, with(Items.silicon, 3000, Items.thorium, 2000, SFItems.waveSteel, 3000));
            health = 20000;
            armor = 25;
            itemCapacity = 30000;
            unitCapModifier = 20;
            researchCostMultiplier = 0.5f;
            unitType = SFUnitTypes.tau;
        }};
        finalCommandCenter = new CoreBlock("final-command-core") {{
            size = 8;
            requirements(Category.effect, with(Items.silicon, 8000, SFItems.siliSteel, 3500, SFItems.fermium, 5000));
            health = 48000;
            armor = 48;
            itemCapacity = 20000;
            unitCapModifier = 50;
            researchCostMultiplier = 0.5f;
            unitType = SFUnitTypes.omega;
        }};
        hyperUnloader = new Unloader("hyper-unloader") {{
            health = 220;
            requirements(Category.effect, with(Items.silicon, 25, SFItems.waveSteel, 20));
            group = BlockGroup.transportation;
            speed = 2;
        }};
        molecularDatabase = new StorageBlock("molecular-database") {{
            size = 3;
            health = 3400;
            armor = 16;
            requirements(Category.effect, with(Items.silicon, 1000, SFItems.waveSteel, 1800, SFItems.nanoCore, 1000));
            hasItems = true;
            itemCapacity = 10000;
        }};

        /*
    xianqu, huojian, dianguang, bingfengbao, gaosi, liebao, zuodao, longxi, mengma, zheyue,
    mini, chuanyun, kuodao, longjuan, tieliu, yanglizi, changqiang, namifengbao, manyou, woliu, cijian, chuifa,
    fangtian, qingning, juhua0,
    lizipao, sizhao, liemei, cuowei0,
    dingdaer, guangyin, cimai, fengmang, zuli,
    poxiao, fenqing, kuosan, zhulin, yuanling, luolong,
        */
        xianqu = new PowerTurret("xianqu") {{
            size = 1;
            health = 350;
            unitSort = UnitSorts.weakest;
            recoil = 0.2f;
            recoilTime = 10;
            shootSound = Sounds.bolt;
            shake = 0.2f;
            heatColor = Color.valueOf("FF4040");
            drawer = new DrawTurret() {{
                parts.add(new RegionPart("-front") {{
                    mirror = false;
                    under = true;
                    progress = PartProgress.recoil;
                    moveY = -0.8f;
                }});
            }};
            researchCostMultiplier = 0.05f;
            requirements(Category.turret, with(Items.copper,70, Items.lead, 40));

            reload = 20;
            rotateSpeed = 13;
            range = 120;
            consumePower(1.55f);
            coolant = consumeCoolant(0.1f);
            shootType = new BasicBulletType(6,11,"circle-bullet"){{
                lifetime = 20;
                ammoMultiplier = 1;
                status = SFStatusEffects.scrambled;
                statusDuration = 20;

                frontColor = Color.white;
                backColor = SFColor.tayrLight;
                shrinkY = 0;
                width = height = 3;
                hitColor = SFColor.tayr;
                shootEffect = hitEffect = despawnEffect = Fx.hitLaserColor;
            }};
        }};

        defensePlatform1 = new PowerTurret("defense-platform-cannon"){{
            size = 6;
            health = 50000;
            armor = 16;
            insulated = absorbLasers = true;
            breakable = rebuildable = false;
            category = Category.turret;
            buildVisibility = BuildVisibility.sandboxOnly;
            drawer = new DrawTurret("heavy-") {{
                parts.add(
                        new RegionPart("-front") {{
                            mirror = false;
                            moveY = -2f;
                            under = true;
                            progress = PartProgress.reload;
                        }},
                        new RegionPart("-barrel") {{
                            mirror = true;
                            x = 12;
                            moveX = 1.5f;
                            moveY = 3f;
                            moves.add(new PartMove() {{
                                y = -7.5f;
                                progress = PartProgress.recoil;
                            }});
                        }},
                        new RegionPart("-back") {{
                            mirror = true;
                            x = 12;
                            moveX = 1.5f;
                            moveY = -4.5f;
                        }}
                );
            }};
            shootWarmupSpeed = 0.08f;
            warmupMaintainTime = 300;
            minWarmup = 0.9f;

            coolantMultiplier = 0.75f;
            consumeCoolant(2);
            liquidCapacity = 200;
            recoilTime = 25;
            recoil = 4;
            shootY = 25.5f;
            shoot = new ShootAlternate(23f){{shots=2;}};
            shootSound = Sounds.laser;
            shootCone = 5;
            shake = 5;
            rotateSpeed = 3;
            reload = 22;
            range = 526;
            shootType = new BasicBulletType(17,155){{
                lifetime = 28;
                splashDamage = damage;
                splashDamageRadius = 60;
                status = SFStatusEffects.breakdown;
                statusDuration = 160f;

                lightningDamage = 50;
                lightning = 2;
                lightningLength = 7;
                lightningColor = frontColor = backColor = SFColor.disc;

                knockback = 16;
                ammoMultiplier = 1;
                hitShake = 5;
                width = 16;
                height = 23;
                trailLength = 32;
                trailWidth = 3.5f;
                trailColor = backColor;
                shootEffect = Fx.shootBig2;
                smokeEffect = new ParticleEffect(){{
                    particles = 7;
                    sizeFrom = 4.4f;
                    lifetime = 55;
                    interp = Interp.pow10Out;
                    sizeInterp = Interp.pow3In;
                    length = 60;
                    cone = 13;
                    colorFrom = SFColor.disc.cpy().a(0.5f);
                    colorTo = SFColor.disc;
                }};
                despawnEffect = Fx.instBomb;
                hitSound = Sounds.explosionbig;
                hitSoundVolume = 0.6f;
                hitEffect = new ExplosionEffect(){{
                    lifetime = 30;
                    waveLife = 15;
                    waveStroke = 8;
                    waveRad = 66;
                    waveColor = sparkColor = SFColor.disc;
                    smokeColor = SFColor.disc.cpy().a(0.45f);
                    sparks = smokes = 8;
                    sparkRad = 70;
                    sparkStroke = 2.6f;
                    sparkLen = 36;
                }};
            }};
        }};
        defensePlatform2 = new PowerTurret("defense-platform-torpedo"){{
            size = 6;
            health = 50000;
            armor = 16;
            insulated = absorbLasers = true;
            breakable = rebuildable = false;
            category = Category.turret;
            buildVisibility = BuildVisibility.sandboxOnly;
            drawer = new DrawTurret("water-"){{
                parts.add(
                        new RegionPart("-barrel") {{
                            mirror = true;
                            under = true;
                            x = 8;
                            moveX = 12;
                            moveY = 4f;
                            moveRot = -15f;
                            children.add(new RegionPart("-side") {{
                                mirror = true;
                                under = false;
                                x = 8;
                                moveY = -16f;
                            }});
                        }}
                );
            }};
            shootWarmupSpeed = 0.08f;
            warmupMaintainTime = 300;
            minWarmup = 0.9f;

            solid = false;
            placeableLiquid = requiresWater = true;
            hasShadow = false;
            underBullets = true;
            targetAir = false;

            recoilTime = 50;
            recoil = 2.25f;
            shootY = 0;
            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        30, 31, -15,
                        0, 36, 0,
                        -30, 31, 15
                };
            }};
            shootSound = Sounds.mineDeploy;
            shootCone = 60;
            shake = 5;
            rotateSpeed = 3;
            reload = 80;
            range = 620;
            shootType = new BasicBulletType(4,1260,"sfire-mod-missile1"){{
                drag = -0.01f;
                shrinkY = 0;
                width = 20;
                height = 72;
                lifetime = 90;
                ammoMultiplier = 1;
                makeFire = true;
                splashDamageRadius = 80;
                splashDamage = 350;
                status = StatusEffects.blasted;
                homingDelay = 15;
                homingRange = 100;
                homingPower = 0.042f;
                reflectable = false;
                collideFloor = true;
                collidesAir = false;
                absorbable = hittable = false;
                layer = 49;
                smokeEffect = new ParticleEffect(){{
                    particles = 9;
                    interp = Interp.pow10Out;
                    sizeInterp = Interp.pow5In;
                    sizeFrom = 5;
                    length = 45;
                    lifetime = 65;
                    cone = 23;
                    colorFrom = SFColor.smoke;
                    colorTo = Color.white.cpy().a(0.4f);
                }};
                shootEffect = Fx.none;
                hitEffect = new MultiEffect(
                        new ExplosionEffect(){{
                            waveRad = 88;
                            waveStroke = 12;
                            waveLife = 18;
                            sparks = 30;
                            sparkLen = 35;
                            sparkStroke = 2;
                            sparkRad = 90;
                            lifetime = 60;
                            smokes = 28;
                            smokeRad = 68;
                            smokeSize = 9;
                            smokeColor = Color.valueOf("596ab890");
                            waveColor = sparkColor = Pal.bulletYellowBack;
                        }},
                        new WaveEffect(){{
                            lifetime = 18;
                            sizeTo = 96;
                            strokeFrom = 9;
                            colorFrom = colorTo = Pal.bulletYellowBack;
                        }}
                );
                despawnEffect = Fx.none;
                hitSound = Sounds.explosionbig;
                hitSoundVolume = 5;
                hitShake = 12;
                trailWidth = 2.6f;
                trailLength = 10;
                trailRotation = true;
                trailInterval = 3;
                trailEffect = new MultiEffect(
                        new ParticleEffect(){{
                            particles = 5;
                            sizeFrom = 6;
                            lifetime = 95;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            cone = 20;
                            length = -75;
                            colorFrom = colorTo = Color.valueOf("596ab8A8");
                            layer = 49.9f;
                        }},
                        new WaveEffect(){{
                            lifetime = 42f;
                            startDelay = 2;
                            sizeTo = 53f;
                            strokeFrom = 3;
                            colorFrom = Color.valueOf("596ab8");
                            colorTo = Color.valueOf("596ab8A8");
                            layer = 49.9f;
                        }},
                        new ParticleEffect(){{
                            particles = 4;
                            sizeFrom = 6;
                            sizeTo = 2;
                            lifetime = 95;
                            region = "sfire-mod-loz";
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            cone = 20;
                            length = -75;
                            colorFrom = Color.white.cpy().a(0.3f);
                            colorTo = Color.white.cpy().a(0);
                            layer = 100;
                        }}
                );
                frontColor = backColor = trailColor = Color.valueOf("596ab890");
            }};
        }};
        defensePlatform3 = new PowerTurret("defense-platform-nuke"){{
            size = 6;
            health = 50000;
            armor = 16;
            unitSort = UnitSorts.strongest;
            insulated = absorbLasers = true;
            breakable = rebuildable = false;
            category = Category.turret;
            buildVisibility = BuildVisibility.sandboxOnly;
            drawer = new DrawTurret("heavy-") {{
                parts.add(
                        new RegionPart("-missile") {{
                            progress = PartProgress.reload.curve(Interp.pow2In);
                            colorTo = new Color(1f, 1f, 1f, 0f);
                            color = Color.white;
                            mixColorTo = SFColor.discDark;
                            mixColor = new Color(1f, 1f, 1f, 0f);
                            outline = false;
                            under = true;
                            layerOffset = -0.01f;
                            moves.add(new PartMove(PartProgress.warmup.curve(Interp.pow2Out), 0f, 5f, 0f));
                        }},
                        new RegionPart("-blade") {{
                            mirror = true;
                            under = true;
                            x = 12f;
                            moveX = 3;
                            moveY = 8;
                        }},
                        new RegionPart("-side") {{
                            mirror = true;
                            under = true;
                            x = 12f;
                            moveX = 4;
                            moveY = -2;
                            moveRot = -15;
                            progress = PartProgress.warmup.curve(Interp.pow5Out);
                        }},
                        new RegionPart("-front") {{
                            mirror = false;
                            under = true;
                            y = 12;
                            moveY = 4.5f;
                            progress = PartProgress.warmup.curve(Interp.pow5Out);
                        }}
                );
            }};
            shootWarmupSpeed = 0.08f;
            warmupMaintainTime = 600;
            minWarmup = 0.9f;
            accurateDelay = false;
            recoilTime = 50;
            recoil = 2.25f;
            shootY = 8;
            shootSound = Sounds.missileLaunch;
            shootCone = 30;
            shake = 8;
            rotateSpeed = 1.25f;
            reload = 550;
            range = 940;
            shootEffect = new ParticleEffect(){{
                particles = 32;
                interp = Interp.fastSlow;
                sizeFrom = 12;
                length = 125;
                lifetime = 123;
                colorFrom = colorTo = SFColor.disc;
                cone = 40;
            }};
            smokeEffect = new ParticleEffect(){{
                particles = 25;
                interp = Interp.fastSlow;
                sizeInterp = Interp.pow3In;
                sizeFrom = 9;
                length = -88;
                lifetime = 65;
                colorFrom = SFColor.disc;
                colorTo = SFColor.disc.cpy().a(0.5f);
                cone = 35;
            }};
            shootType = new BulletType(){{
                ammoMultiplier = 1;
                smokeEffect = Fx.bigShockwave;
                speed = 0;
                spawnUnit = new MissileUnitType("defense-platform-nuke-missile"){{
                    outlineColor = SFColor.darkOutline;
                    missileAccelTime = 100;
                    homingDelay = 70;
                    speed = 4;
                    lifetime = 290;
                    rotateSpeed = 0.5f;
                    hitSize = 16;
                    health = 3000;
                    targetAir = true;
                    collidesAir = true;
                    deathSound = Sounds.explosionbig;
                    engineColor = trailColor = SFColor.disc;
                    trailLength = 80;
                    engineLayer = 110;
                    engineOffset = 12;
                    engineSize = 4.5f;
                    abilities.add(new MoveEffectAbility() {{
                        rotateEffect = true;
                        interval = 4;
                        y = -8;
                        effect = new ParticleEffect() {{
                            particles = 4;
                            sizeFrom = 9;
                            lifetime = 100;
                            layer = 99;
                            length = 32;
                            interp = Interp.pow3Out;
                            sizeInterp = Interp.pow10In;
                            colorFrom = colorTo = SFColor.disc.cpy().a(0.8f);
                        }};
                    }});
                    abilities.add(new StatusFieldAbility(StatusEffects.overdrive, 300,110,-1){{applyEffect=activeEffect=Fx.none;}});
                    maxRange = 80;
                    weapons.add(new Weapon(name("defense-nuke")) {{
                        reload = 60;
                        x = 0;
                        mirror = false;
                        rotate = true;
                        shake = 10;
                        shootSound = Sounds.explosion;
                        shootOnDeath = true;
                        shootCone = 360;
                        bullet = new BulletType(0, 100) {{
                            killShooter = true;
                            instantDisappear = true;
                            maxRange = 80f;
                            splashDamageRadius = 280;
                            splashDamage = 2700;
                            splashDamagePierce = true;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 600;
                            hitSound = SFSounds.hugeExplosion;
                            hitSoundVolume = 8;
                            hitShake = 30;
                            shootEffect = smokeEffect = Fx.none;
                            hitEffect = new MultiEffect(
                                    new WaveEffect() {{
                                        interp = Interp.circleOut;
                                        lifetime = 20;
                                        sizeTo = 300;
                                        strokeFrom = 22;
                                        strokeTo = 10;
                                        colorFrom = SFColor.disc;
                                        colorTo = SFColor.discDark.cpy().a(0.5f);
                                    }},
                                    new WaveEffect() {{
                                        interp = Interp.circleOut;
                                        startDelay = 10;
                                        lifetime = 20;
                                        sizeTo = 300;
                                        strokeFrom = 22;
                                        strokeTo = 10;
                                        colorFrom = SFColor.disc;
                                        colorTo = SFColor.discDark.cpy().a(0.5f);
                                    }},
                                    new ParticleEffect() {{
                                        particles = 82;
                                        line = true;
                                        strokeFrom = 5.2f;
                                        lenFrom = 94;
                                        length = 360;
                                        lifetime = 25;
                                        interp = Interp.pow5Out;
                                        colorTo = colorFrom = SFColor.disc;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 1;
                                        sizeFrom = 9999;
                                        sizeTo = 0;
                                        colorFrom = Color.black.cpy().a(0.1f);
                                        colorTo = Color.black.cpy().a(0);
                                        lifetime = 66;
                                        length = 0;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 1;
                                        sizeFrom = 80;
                                        sizeTo = 240;
                                        sizeInterp = Interp.pow5Out;
                                        colorFrom = SFColor.disc;
                                        colorTo = SFColor.discDark.cpy().a(0.8f);
                                        lifetime = 22f;
                                        length = 0;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 1;
                                        sizeFrom = 240;
                                        sizeInterp = Interp.pow10In;
                                        colorFrom = colorTo = SFColor.discDark.cpy().a(0.8f);
                                        startDelay = 20;
                                        lifetime = 105;
                                        length = 0;
                                    }}
                            );
                            despawnEffect = new WaveEffect() {{
                                interp = Interp.circleOut;
                                lifetime = 20;
                                sizeTo = 500;
                                strokeFrom = 30;
                                strokeTo = 11;
                                colorFrom = SFColor.disc;
                                colorTo = SFColor.discDark.cpy().a(0.02f);
                            }};
                        }};
                    }});
                }};
            }};
        }};
        defensePlatform4 = new PowerTurret("defense-platform-laser"){{
            size = 6;
            health = 50000;
            armor = 16;
            unitSort = UnitSorts.weakest;
            insulated = absorbLasers = true;
            breakable = rebuildable = false;
            category = Category.turret;
            buildVisibility = BuildVisibility.sandboxOnly;
            drawer = new DrawTurret("heavy-"){{
                parts.add(
                        new RegionPart("-barrel-r"){{
                            mirror = false;
                            under = true;
                            x = 8;
                            moveY = 8;
                            recoilIndex = 0;
                            progress = PartProgress.warmup.curve(Interp.pow2Out);
                            moves.add(new PartMove(PartProgress.recoil,0,-7,0));
                        }},
                        new RegionPart("-barrel-l"){{
                            mirror = false;
                            under = true;
                            x = -8;
                            moveY = 8;
                            recoilIndex = 1;
                            progress = PartProgress.warmup.curve(Interp.pow2Out);
                            moves.add(new PartMove(PartProgress.recoil,0,-7,0));
                        }},
                        new RegionPart("-side"){{
                            mirror = true;
                            under = true;
                            x = 12;
                            moveX = 4;
                            progress = PartProgress.warmup.curve(Interp.pow5Out);
                        }}
                );
            }};
            shootWarmupSpeed = 0.08f;
            warmupMaintainTime = 360;
            minWarmup = 0.8f;
            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        8, 3, 0,
                        -8, 3, 0
                };
                shots = 3;
                shotDelay = 8;
            }};
            accurateDelay = false;
            recoilTime = 40;
            recoil = 2;
            recoils = 2;
            shootY = 24;
            shootSound = Sounds.laser;
            soundPitchMax = soundPitchMin = 1.5f;
            shootCone = 8;
            shake = 6;
            rotateSpeed = 3.8f;
            reload = 88;
            range = 450;
            shootType = new LaserBulletType(500) {{
                width = 28;
                length = 450;
                pierceArmor = true;
                status = SFStatusEffects.breakdown;
                statusDuration = 60;
                hitEffect = new MultiEffect(Fx.hitLaserBlast,
                        new ParticleEffect(){{
                                lifetime = 30;
                                line = true;
                                lenFrom = 25;
                                strokeFrom = 4.2f;
                                colorFrom = colorTo = SFColor.energyYellow;
                                sizeInterp = interp = Interp.circleOut;
                                length = 56;
                                baseLength = -16;
                                cone = 40;
                            }});
                shootEffect = smokeEffect = Fx.none;
                colors = new Color[]{Pal.accent.cpy().a(.5f), Pal.accent.cpy().mul(1.2f), Color.white};
            }};
        }};
        defensePlatform5 = new PowerTurret("defense-platform-plasma"){{
            size = 8;
            health = 80000;
            armor = 33;
            insulated = absorbLasers = true;
            breakable = rebuildable = false;
            category = Category.turret;
            unitSort = UnitSorts.strongest;
            buildVisibility = BuildVisibility.sandboxOnly;
            heatColor = Color.valueOf("FF4040");
            drawer = new DrawTurret("heavy-"){{
                parts.add(
                        new RegionPart("-barrel"){{
                            mirror = true;
                            under = true;
                            moveY = 24;
                            heatColor = Color.valueOf("FF4040");
                            children.add(new RegionPart("-warm"){{
                                mirror = false;
                                drawRegion = false;
                                heatProgress = PartProgress.warmup;
                                heatColor = Color.valueOf("FF4040");
                            }});
                        }},
                        new RegionPart("-load"){{
                            mirror = false;
                            drawRegion = false;
                            heatProgress = PartProgress.warmup;
                            heatColor = Color.valueOf("FF4040");
                        }}
                );
            }};
            shootWarmupSpeed = 0.0125f;
            warmupMaintainTime = 300;
            minWarmup = 0.9f;
            accurateDelay = false;
            shake = 20;
            recoil = 8;
            recoilTime = 80;
            reload = 150;
            cooldownTime = 280;
            rotateSpeed = 1.92f;
            range = 880;
            shootY = 8;
            shootSound = Sounds.laserblast;
            shootEffect = new ParticleEffect(){{
                particles = 13;
                line = true;
                interp = Interp.pow5Out;
                sizeInterp = Interp.pow5In;
                lenFrom = 36;
                strokeFrom = strokeTo = 6;
                length = 100;
                lifetime = 20;
                colorFrom = colorTo = SFColor.energyGreen;
                cone = 19;
            }};
            smokeEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 11;
                        sizeFrom = 10;
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.pow5In;
                        length = 80;
                        lifetime = 120;
                        colorFrom = SFColor.energyGreen;
                        colorTo = SFColor.energyGreen.cpy().a(0.8f);
                        cone = 15;
                    }},
                    new WaveEffect(){{
                        interp = Interp.circleOut;
                        lifetime = 40;
                        sizeTo = 100;
                        strokeFrom = strokeTo = 16;
                        colorFrom = SFColor.energyGreen;
                        colorTo = SFColor.energyGreen.cpy().a(0);
                    }},
                    new ParticleEffect(){{
                        particles = 1;
                        sizeFrom = 12;
                        length = 0;
                        baseLength = -4f;
                        sizeInterp = Interp.pow10In;
                        lifetime = 60;
                        colorFrom = colorTo = SFColor.energyGreen;
                    }}
            );
            shootType = new PointBulletType(){{
                lifetime = 90;
                speed = 100;
                splashDamage = damage = 6000;
                splashDamageRadius = 80;
                status = SFStatusEffects.breakdown;
                statusDuration = 260;
                hitSound = Sounds.largeExplosion;
                hitSoundVolume = 3;
                despawnEffect = new ParticleEffect(){{
                    particles = 16;
                    region = "sfire-mod-tri";
                    spin = 16;
                    sizeFrom = 22;
                    length = 120;
                    lifetime = 30;
                    colorFrom = colorTo = SFColor.energyGreen;
                }};
                hitEffect = new MultiEffect(
                        new WaveEffect(){{
                            lifetime = 12;
                            sizeTo = 150;
                            strokeFrom = 50;
                            strokeTo = 24;
                            colorFrom = SFColor.energyGreen;
                            colorTo = SFColor.energyGreen.cpy().a(0);
                        }},
                        new ParticleEffect(){{
                            particles = 1;
                            sizeFrom = 20;
                            length = 0;
                            sizeInterp = Interp.pow5In;
                            lifetime = 45;
                            colorFrom = colorTo = SFColor.energyGreen;
                        }},
                        Fx.scatheExplosion
                );
                hitColor = SFColor.energyGreen;
                trailSpacing = 12;
                trailEffect = new MultiEffect(
                        new ParticleEffect(){{
                            particles = 1;
                            line = true;
                            randLength = false;
                            sizeInterp = Interp.pow3In;
                            lenFrom = lenTo = 13;
                            strokeTo = 15;
                            length = 0;
                            baseLength = 0.2f;
                            lifetime = 20;
                            colorFrom = colorTo = SFColor.energyGreen;
                            cone = 0;
                        }},
                        new ParticleEffect(){{
                            particles = 1;
                            line = true;
                            randLength = false;
                            sizeInterp = Interp.pow3In;
                            startDelay = 18;
                            lenFrom = lenTo = 13;
                            strokeFrom = 15;
                            length = 0;
                            baseLength = 0.2f;
                            lifetime = 42;
                            colorFrom = colorTo = SFColor.energyGreen;
                            cone = 0;
                        }}
                );
                fragBullets = 15;
                fragLifeMin = 0.4f;
                fragBullet = new BasicBulletType(4,500,"circle-bullet"){{
                    lifetime = 40;
                    width = height = 16;
                    shrinkY = 0;
                    backColor = trailColor = SFColor.energyGreen;
                    frontColor = Color.white;
                    trailChance = 1;
                    trailEffect = Fx.artilleryTrail;
                    status = SFStatusEffects.echoFlame;
                    statusDuration = 30;
                    splashDamage = damage;
                    splashDamageRadius = 50;
                    hitShake = 16;
                    hitSound = Sounds.explosionbig;
                    hitSoundVolume = 3;
                    hitEffect = new ExplosionEffect(){{
                        smokes = 8;
                        sparks = 19;
                        sparkLen = 33;
                        sparkStroke = 4;
                        sparkRad = 70;
                        lifetime = 45;
                        sparkColor = smokeColor = SFColor.energyGreen;
                        waveColor = SFColor.energyGreen.cpy().a(0.8f);
                        waveLife = 30;
                        waveRad = 50;
                        waveStroke = 25;
                    }};
                    despawnEffect = new ParticleEffect(){{
                        particles = 1;
                        sizeFrom = 26;
                        length = 0;
                        sizeInterp = Interp.pow5In;
                        lifetime = 60;
                        colorTo = SFColor.energyGreen.cpy().a(0.8f);
                    }};
                }};

                for(int i = 0; i < 5; i++) {
                    float randspeed = random(10) + 8;
                    spawnBullets.add(new BasicBulletType(randspeed,150,"circle-bullet"){{
                        lifetime = 40;
                        inaccuracy = 15;
                        width = height = 9;
                        shrinkY = 0;
                        weaveMag = 3;
                        weaveScale = 5;
                        backColor = trailColor = SFColor.energyGreen.cpy().a(0.5f);
                        frontColor = Color.white.cpy().a(0.5f);
                        trailLength = 16;
                        trailWidth = 2;
                        status = SFStatusEffects.echoFlame;
                        statusDuration = 30;
                        splashDamage = damage;
                        splashDamageRadius = 50;
                        hitShake = 16;
                        hitSound = Sounds.explosionbig;
                        hitSoundVolume = 3;
                        hitEffect = new ExplosionEffect(){{
                            smokes = 8;
                            sparks = 19;
                            sparkLen = 33;
                            sparkStroke = 4;
                            sparkRad = 70;
                            lifetime = 45;
                            sparkColor = smokeColor = SFColor.energyGreen;
                            waveColor = SFColor.energyGreen.cpy().a(0.8f);
                            waveLife = 30;
                            waveRad = 50;
                            waveStroke = 25;
                        }};
                        despawnEffect = new ParticleEffect(){{
                            particles = 1;
                            sizeFrom = 26;
                            length = 0;
                            sizeInterp = Interp.pow5In;
                            lifetime = 60;
                            colorTo = SFColor.energyGreen.cpy().a(0.8f);
                        }};
                    }});
                }
            }};
        }};


        terrAssembler = new UnitAssembler("terr-assembler") {{
            health = 80000;
            size = 10;
            liquidCapacity = 600;

            category = Category.units;
            buildVisibility = BuildVisibility.sandboxOnly;

            consumePower(600);
            consumeLiquid(SFLiquids.nanoFluid, 4f);
            areaSize = 28;
            plans.add(
                    new AssemblerUnitPlan(SFUnitTypes.electrodile, 60f * 360f, PayloadStack.list(discContainmentUnit,60,armorContainmentUnit,100))
            );
            droneType = SFUnitTypes.assemblerDrone2;
        }};
        hoveAssembler = new UnitAssembler("elec-assembler") {{
            health = 8000;
            size = 6;
            liquidCapacity = 180;

            category = Category.units;
            buildVisibility = BuildVisibility.sandboxOnly;

            consumePower(15);
            consumeLiquid(Liquids.cryofluid, 1.5f);
            areaSize = 9;
            plans.add(
                    new AssemblerUnitPlan(SFUnitTypes.electrodile, 60f * 200f, PayloadStack.list(discContainmentUnit,1,armorContainmentUnit,1))
            );
            droneType = SFUnitTypes.assemblerDrone;
        }};
        payloadConstrustor = new Constructor("payload-construstor") {{
            size = 6;
            health = 2000;

            category = Category.units;
            buildVisibility = BuildVisibility.sandboxOnly;

            minBlockSize = maxBlockSize = 4;
            buildSpeed = 0.8f;
            liquidCapacity = 60;
            consumePower(8);
            consumeLiquid(Liquids.cryofluid, 0.9f);
            filter = Seq.with(discContainmentUnit, armorContainmentUnit);
        }};
        ItemStack[] T3Item = with(Items.lead, 30, Items.silicon, 180, Items.titanium, 100, Items.metaglass, 50);
        specFactory = new UnitFactory("special-factory") {{
            health = 3000;
            size = 5;
            requirements(Category.units, with(Items.thorium, 360, Items.plastanium, 100, SFItems.siliSteel, 400, SFItems.nanoCore, 200));
            hasLiquids = true;
            liquidCapacity = 60;
            consumePower(8.5f);
            consumeLiquid(Liquids.cryofluid, 0.6f);
            plans = Seq.with(
                    new UnitPlan(UnitTypes.fortress, 60f * 33, T3Item),
                    new UnitPlan(UnitTypes.zenith, 60f * 33, T3Item),
                    new UnitPlan(UnitTypes.bryde, 60f * 51, T3Item),
                    new UnitPlan(UnitTypes.spiroct, 60f * 30, T3Item),
                    new UnitPlan(UnitTypes.quasar, 60f * 48, T3Item),
                    new UnitPlan(UnitTypes.mega, 60f * 45, T3Item),
                    new UnitPlan(UnitTypes.cyerce, 60f * 54, T3Item),
                    new UnitPlan(SFUnitTypes.farmer, 60f * 50, with(Items.titanium,300, Items.silicon,250, SFItems.siliSteel,100, SFItems.nanoCore,80)),
                    new UnitPlan(SFUnitTypes.tank1, 60f * 42, T3Item),
                    new UnitPlan(SFUnitTypes.air3, 60f * 39, T3Item),
                    new UnitPlan(SFUnitTypes.naval3, 60f * 60, T3Item),
                    new UnitPlan(SFUnitTypes.hammer, 60f * 35, with(Items.titanium,80, Items.silicon,160, Items.graphite,160, SFItems.siliSteel,80)),
                    new UnitPlan(SFUnitTypes.flamer, 60f * 50, with(Items.graphite,90, Items.silicon,180, SFItems.waveSteel,80, SFItems.strontium,80)),
                    new UnitPlan(SFUnitTypes.thunder, 60f * 50, with(SFItems.siliSteel,80, Items.silicon,220, Items.plastanium,130, Items.phaseFabric,60)),
                    new UnitPlan(SFUnitTypes.banisher, 60f * 67, with(SFItems.waveSteel,120, Items.silicon,230, Items.plastanium,80, Items.surgeAlloy,60))
            );
        }};
        pentativeReconstrustor = new Reconstructor("pentative-reconstructor") {{
            requirements(Category.units, with(Items.plastanium, 2200, SFItems.nanoCore, 5000, SFItems.tayrAlloy, 1100, SFItems.discFabric, 750));
            size = 11;
            liquidCapacity = 300;
            upgrades.addAll(
                    new UnitType[]{UnitTypes.reign, SFUnitTypes.liXian},
                    new UnitType[]{UnitTypes.corvus, SFUnitTypes.diXing},
                    new UnitType[]{UnitTypes.toxopid, SFUnitTypes.panLong},
                    new UnitType[]{UnitTypes.oct, SFUnitTypes.guangHan},
                    new UnitType[]{UnitTypes.eclipse, SFUnitTypes.yuHui},
                    new UnitType[]{UnitTypes.omura, SFUnitTypes.tengWang},
                    new UnitType[]{UnitTypes.navanax, SFUnitTypes.luoHan},
                    new UnitType[]{SFUnitTypes.tank5, SFUnitTypes.tank6},
                    new UnitType[]{SFUnitTypes.air5, SFUnitTypes.air6},
                    new UnitType[]{SFUnitTypes.naval5, SFUnitTypes.naval6}
            );
            researchCostMultiplier = 0.2f;
            buildCostMultiplier = 0.7f;
            constructTime = 21600f;

            consumePower(30f);
            consumeItems(with(Items.silicon, 1300, SFItems.waveSteel, 800, SFItems.tayrAlloy, 600, SFItems.discFabric, 550));
            consumeLiquid(Liquids.cryofluid, 4.8f);
        }};
        nanoUnitRegener = new RepairTower("nano-unit-regener") {{
            requirements(Category.units, with(Items.plastanium,400,Items.surgeAlloy,300,SFItems.nanoCore,400));
            size = 4;
            health = 1200;
            liquidCapacity = 128f;
            range = 224;
            healAmount = 12f;

            squareRad = 6;
            squareSpinScl = 1.2f;
            circleSpeed = 75;
            circleStroke = 8;
            glowMag = 0.4f;
            glowScl = 12;

            consumePower(18f);
            consumeLiquid(SFLiquids.nanoFluid, 0.6f);
        }};

    }
}