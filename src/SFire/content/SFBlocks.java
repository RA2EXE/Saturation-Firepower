package SFire.content;

import SFire.expand.blocks.PressureDrill;
import SFire.expand.bullets.PowerupBulletType;
import SFire.expand.blocks.SFCore;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.*;
import arc.struct.Seq;
import arc.util.Tmp;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.UnitSorts;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.abilities.StatusFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
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
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.*;
import static arc.math.Mathf.random;
import static mindustry.type.ItemStack.*;

public class SFBlocks {
    public static Block
    //environment + wall + ores
    snowSand, sporeSand, quartzSand, rareEarth, rareEarthWater, calrareEarth, calrareEarthWall,
    magstoneFloor, magstoneWall, magstoneStone, magbasaltFloor, magbasaltWall, magbasaltStone, magshaleFloor, magshaleWall, magshaleStone,
    calciteFloor, calciteWall, calciteStone, celestiteFloor, celestiteVent, celestiteWall, celestiteStone,
    crackrockFloor, crackrockWall, crackrockStone, combinationFloor, combinationVent, combinationWall, combinationStone,
    radiquartzFloor, radiquartzWall, radiquartzStone, radiamphiboleFloor, radiamphiboleWall, radiamphiboleStone,
    radigabbroFloor, radigabbroWall, radigabbroStone, radimacadam1Floor, radimacadam2Floor,

    induFloor, induFloorSupplyer, induFloorBroken, induFloorHeater, induHeatBroken, induFloorCover, induFloorWall, induFloorNano, induFloorNanowall,
            lightRed, lightYellow, lightBlue, lightGreen,
    reforcedFloor, reforcedFloor1, reforcedFloor2, reforcedFloor3, perimeter,

    discEngine, oilDrums, oilDrumsLarge, oilDrumsArmor, radarBig,

    strontium, rubidium, fermium, chromium,

    //crafting
    crusher, sporeCompressor, flywheelCentrifuge,
    pyraBlender, blastBlender, clusBlender, cryoCentrifuge, plasMultiCompresser, surgeTheSmelter, surgeElesmelter,

    silisteelSmelter, silisteelSmelterLarge, silisteelSmelterHuge, silisteelCrucible, wavesteelCompresseor, wavesteelForger, metalAnalyzer,
    nanoConstructor, nanoPrinter, lensAtomizer, airCollector, airCooler, nitrateMixer, fractionator,
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
    coalPyrolyzer, gasSmoker, heatGenerator, radiGenerator, fermReactor, fissionReactor, arcFissionReactor,
    // hypermagneticReactor,

    //production
    energyDrill, heavyDrill, blastWell, quantumOreExtractor,
    waterExtractor, /*slagExtractor,*/
    oilPressurePump, sporeCultivator,

    //storage
    frontCore, industryCore, finalCommandCenter, hyperUnloader, molecularDatabase,

    //turrets
    xianqu, huojian, dianguang, bingfengbao, gaosi, liebao, cuodao, longxi, mengma, zheyue,
    kuodao, mini, woliu, tieliu, dianji, changqiang, longjuan, manyou, chuanyun, cijian, chuifa,
    fangtian, leizhenyu, qingning, chongchao,
    relang, sizhao, liemei, cuowei,
    dingdaer, guangyin, cimai, fengmang,
    yuanling, kuosan, poxiao, fenqing, zhulin, luolong,

    finalWeapon1, finalWeapon2,
    defensePlatform1, defensePlatform2, defensePlatform3, defensePlatform4, defensePlatform5, defensePlatform6,

    //units
    terrAssembler, hoveAssembler, payloadConstrustor, specFactory, pentativeReconstrustor,
    nanoUnitRegener
    //campaign
    ;

    public static void load() {
        //region envblcoks
        snowSand = new Floor("snow-sand") {{
            itemDrop = Items.sand;
            playerUnmineable = true;
            attributes.set(Attribute.oil, 0.35f);
            attributes.set(Attribute.water, 0.2f);
        }};
        sporeSand = new Floor("spore-sand") {{
            itemDrop = Items.sand;
            playerUnmineable = true;
            attributes.set(Attribute.oil, 0.4f);
            attributes.set(Attribute.spores, 0.2f);
        }};
        quartzSand = new Floor("quartz-sand") {{
            itemDrop = Items.sand;
            playerUnmineable = true;
            attributes.set(SFAttribute.radioactivity, 0.08f);
        }};
        rareEarth = new Floor("rare-earth-floor", 4) {{
            itemDrop = SFItems.rareEarth;
            playerUnmineable = true;
            dragMultiplier = 1.08f;
        }};
        rareEarthWater = new Floor("rare-earth-water", 4) {{
            speedMultiplier = 0.8f;
            statusDuration = 50f;
            albedo = 0.9f;
            supportsOverlay = true;
            liquidDrop = Liquids.water;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            overlayAlpha = 0.35f;
        }};
        calrareEarth = new Floor("calrare-earth-floor", 4) {{
            itemDrop = SFItems.rareEarth;
            playerUnmineable = true;
            dragMultiplier = 1.08f;
            attributes.set(Attribute.water, -0.1f);
        }};
        calrareEarthWall = new StaticWall("calrare-earth-wall") {{
            variants = 2;
            unitMoveBreakable = true;
        }};

        magstoneFloor = new Floor("magstone-floor") {{
            wall = magstoneWall;
            decoration = magshaleWall;
            dragMultiplier = 1.25f;
            walkSound = Sounds.spark;
        }};
        magbasaltFloor = new Floor("magbasalt-floor") {{
            wall = magbasaltWall;
            decoration = magbasaltStone;
            dragMultiplier = 1.1f;
            attributes.set(Attribute.oil, 1.88f);
            walkSound = Sounds.spark;
        }};
        magshaleFloor = new Floor("magshale-floor") {{
            wall = magshaleWall;
            decoration = magshaleStone;
            dragMultiplier = 1.1f;
            attributes.set(Attribute.oil, 1.88f);
            walkSound = Sounds.spark;
        }};
        calciteFloor = new Floor("calcite-floor") {{
            wall = calciteWall;
            decoration = calciteStone;
        }};
        celestiteFloor = new Floor("celestite-floor") {{
            wall = celestiteWall;
            decoration = celestiteStone;
            dragMultiplier = 0.9f;
        }};
        celestiteVent = new SteamVent("celestite-vent"){{
            parent = blendGroup = celestiteFloor;
            attributes.set(Attribute.steam, 1f);
            attributes.set(Attribute.water, 0.5f);
            effectColor = Color.white.cpy().a(0.55f);
        }};
        crackrockFloor = new Floor("crackrock-floor", 4) {{
            wall = crackrockWall;
            decoration = crackrockStone;
        }};
        combinationFloor = new Floor("combination-floor", 4) {{
            wall = combinationWall;
            decoration = combinationStone;
        }};
        combinationVent = new SteamVent("combination-vent"){{
            parent = blendGroup = combinationFloor;
            attributes.set(Attribute.steam, 1f);
            attributes.set(Attribute.water, 0.5f);
            effectColor = Color.white.cpy().a(0.55f);
        }};
        radiquartzFloor = new Floor("radiquartz-floor", 4) {{
            wall = radiquartzWall;
            decoration = radiquartzStone;
            attributes.set(Attribute.water, -0.1f);
            attributes.set(Attribute.spores, -1f);
            attributes.set(SFAttribute.radioactivity, 0.3f);
        }};
        radiamphiboleFloor = new Floor("radiamphibole-floor", 4) {{
            wall = radiamphiboleWall;
            decoration = radiamphiboleStone;
            attributes.set(Attribute.water, -0.3f);
            attributes.set(Attribute.spores, -1f);
            attributes.set(SFAttribute.radioactivity, 0.75f);
        }};
        radigabbroFloor = new Floor("radigabbro-floor", 4) {{
            wall = radigabbroWall;
            decoration = radigabbroStone;
            attributes.set(Attribute.water, -0.5f);
            attributes.set(Attribute.spores, -1f);
            attributes.set(SFAttribute.radioactivity, 1.3f);
        }};
        radimacadam1Floor = new Floor("radimacadam1-floor") {{
            blendGroup = radiamphiboleFloor;
            attributes.set(Attribute.water, -0.6f);
            attributes.set(Attribute.heat, 0.15f);
            attributes.set(Attribute.spores, -1f);
            emitLight = true;
            attributes.set(SFAttribute.radioactivity, 1.5f);
            lightColor = SFColor.discDark.cpy().a(0.4f);
            lightRadius = 6f;
        }};
        radimacadam2Floor = new Floor("radimacadam2-floor") {{
            blendGroup = radigabbroFloor;
            attributes.set(Attribute.water, -0.75f);
            attributes.set(Attribute.heat, 0.3f);
            attributes.set(Attribute.spores, -1f);
            attributes.set(SFAttribute.radioactivity, 2.6f);
            emitLight = true;
            lightColor = SFColor.discDark.cpy().a(0.6f);
            lightRadius = 8f;
        }};

        magstoneWall = new StaticWall("magstone-wall") {{
            variants = 2;
        }};
        magbasaltWall = new StaticWall("magbasalt-wall") {{
            variants = 2;
        }};
        magshaleWall = new StaticWall("magshale-wall") {{
            variants = 2;
        }};
        calciteWall = new StaticWall("calcite-wall") {{
            variants = 2;
        }};
        celestiteWall = new StaticWall("celestite-wall") {{
            variants = 2;
        }};
        crackrockWall = new StaticWall("crackrock-wall") {{
            variants = 2;
        }};
        combinationWall = new StaticWall("combination-wall") {{
            variants = 2;
        }};
        radiquartzWall = new StaticWall("radiquartz-wall") {{
            variants = 2;
        }};
        radiamphiboleWall = new StaticWall("radiamphibole-wall") {{
            variants = 2;
        }};
        radigabbroWall = new StaticWall("radigabbro-wall") {{
            variants = 2;
        }};

        magstoneStone = new Prop("magstone-stone") {{
            variants = 2;
        }};
        magbasaltStone = new Prop("magbasalt-stone") {{
            variants = 2;
        }};
        magshaleStone = new Prop("magshale-stone") {{
            variants = 2;
        }};
        calciteStone = new Prop("calcite-stone") {{
            variants = 2;
        }};
        celestiteStone = new Prop("celestite-stone") {{
            variants = 2;
        }};
        crackrockStone = new Prop("crackrock-stone") {{
            variants = 2;
        }};
        combinationStone = new Prop("combination-stone") {{
            variants = 2;
        }};
        radiquartzStone = new TallBlock("radiquartz-stone") {{
            variants = 2;
            clipSize = 120f;
            breakable = true;
            breakSound = Sounds.rockBreak;
            buildCostMultiplier = 10;
        }};
        radiamphiboleStone = new Prop("radiamphibole-stone") {{
            variants = 2;
        }};
        radigabbroStone = new Prop("radigabbro-stone") {{
            variants = 2;
        }};

        induFloor = new Floor("industry-floor", 0) {{
            speedMultiplier = 1.125f;
            dragMultiplier = 0.95f;
            wall = induFloorWall;
            decoration = induFloorCover;
            attributes.set(Attribute.oil, 1f);
            attributes.set(Attribute.water, 0.25f);
            attributes.set(Attribute.spores, 0.25f);
        }};
        induFloorSupplyer = new Floor("industry-supplyer", 7) {{
            speedMultiplier = 1.125f;
            dragMultiplier = 0.95f;
            attributes.set(Attribute.oil, 2);
            attributes.set(Attribute.water, 2);
            attributes.set(Attribute.spores, 2);
            blendGroup = SFBlocks.induFloor;
        }};
        induFloorBroken = new Floor("industry-floor-broken", 9) {{
            speedMultiplier = 0.95f;
            dragMultiplier = 1.125f;
            wall = induFloorWall;
            decoration = induFloorCover;
            blendGroup = SFBlocks.induFloor;
        }};
        induFloorHeater = new Floor("industry-heater", 2) {{
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
        induHeatBroken = new Floor("industry-heater-broken", 4) {{
            speedMultiplier = 0.85f;
            dragMultiplier = 1.35f;
            decoration = induFloorCover;
            blendGroup = SFBlocks.induFloor;
        }};
        induFloorCover = new Prop("industry-floor-cover") {{
            variants = 6;
            breakSound = Sounds.breaks;
        }};
        induFloorWall = new StaticWall("industry-wall") {{
            variants = 0;
        }};
        induFloorNano = new Floor("nano-panel", 16) {{
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
        induFloorNanowall = new StaticWall("industry-nanowall") {{
            variants = 4;
        }};

        lightRed = new Floor("light-red", 0) {{
            useColor = false;
            emitLight = true;
            blendGroup = induFloor;
            lightRadius = 50;
            lightColor = Color.red.cpy().a(0.6f);
        }};
        lightYellow = new Floor("light-yellow", 0) {{
            useColor = false;
            emitLight = true;
            blendGroup = induFloor;
            lightRadius = 50;
            lightColor = Color.yellow.cpy().a(0.6f);
        }};
        lightBlue = new Floor("light-blue", 0) {{
            useColor = false;
            emitLight = true;
            blendGroup = induFloor;
            lightRadius = 50;
            lightColor = Color.blue.cpy().a(0.6f);
        }};
        lightGreen = new Floor("light-green", 0) {{
            useColor = false;
            emitLight = true;
            blendGroup = induFloor;
            lightRadius = 50;
            lightColor = Color.green.cpy().a(0.6f);
        }};

        reforcedFloor = new Floor("reforced-floor", 0) {{
            speedMultiplier = 1.3f;
            dragMultiplier = 1.15f;
            attributes.set(Attribute.oil, -0.125f);
            attributes.set(Attribute.water, -0.125f);
        }};
        reforcedFloor1 = new Floor("reforced-floor-1", 0) {{
            speedMultiplier = 1.3f;
            dragMultiplier = 1.15f;
            attributes.set(Attribute.oil, -0.125f);
            attributes.set(Attribute.water, -0.125f);
            blendGroup = SFBlocks.reforcedFloor;
        }};
        reforcedFloor2 = new Floor("reforced-floor-2", 0) {{
            speedMultiplier = 1.3f;
            dragMultiplier = 1.15f;
            attributes.set(Attribute.oil, -0.125f);
            attributes.set(Attribute.water, -0.125f);
            blendGroup = SFBlocks.reforcedFloor;
        }};
        reforcedFloor3 = new OverlayFloor("reforced-floor-3") {{
            variants = 0;
        }};
        perimeter = new Floor("perimeter", 0) {{
            placeableOn = false;
            speedMultiplier = 1.3f;
            dragMultiplier = 1.15f;
            attributes.set(Attribute.oil, -0.125f);
            attributes.set(Attribute.water, -0.125f);
        }};

        discEngine = new Thruster("disc-engine") {{
            health = 4000;
            size = 5;
            requirements(Category.defense, with(Items.scrap, 5000, SFItems.discFabric, 650, SFItems.fermium, 1500));
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
            destroyBullet = new ExplosionBulletType(300, 50) {{
                buildingDamageMultiplier = 0.05f;
                status = StatusEffects.blasted;
                hitSound = Sounds.explosionbig;
                hitSoundVolume = 3;
                hitShake = 5;
                hitEffect = new MultiEffect(
                        Fx.massiveExplosion,
                        new ParticleEffect() {{
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
                despawnEffect = new WaveEffect() {{
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
                fragBullet = new BasicBulletType(10, 820) {{
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
                    intervalBullet = new FireBulletType(4, 280) {{
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
            destroyBullet = new ExplosionBulletType(390, 80) {{
                buildingDamageMultiplier = 0.05f;
                status = StatusEffects.blasted;
                hitSound = Sounds.explosionbig;
                hitSoundVolume = 3;
                hitShake = 5;
                hitEffect = new MultiEffect(
                        Fx.massiveExplosion,
                        new ParticleEffect() {{
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
                despawnEffect = new WaveEffect() {{
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
                fragBullet = new BasicBulletType(10, 820) {{
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
                    intervalBullet = new FireBulletType(4, 280) {{
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
            category = Category.liquid;
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
            glowColor = SFColor.tayrDark;
            requirements(Category.effect, with(Items.titanium, 100, Items.silicon, 150, SFItems.tayrAlloy, 50));
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
        //endregion
        //region crafting
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
            requirements(Category.crafting, with(Items.metaglass, 40, Items.titanium, 60, SFItems.siliSteel, 30));
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
            requirements(Category.crafting, with(Items.lead, 60, SFItems.chromium, 70, SFItems.siliSteel, 30));
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
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water),
                    new DrawLiquidTile(Liquids.cryofluid) {{drawLiquidLight = true;}}, new DrawDefault());
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
            boostScale = 3 / 16f;
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
            consumeItem(SFItems.rareEarth, 1);
            //consumeItem(Items.sand, 2);  test only
            consumeLiquid(Liquids.water, 0.2f);

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
            boostScale = 1 / 16f;
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
            boostScale = 1 / 9f;
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
            requirements(Category.production, with(Items.copper, 40, Items.metaglass, 20, Items.silicon, 40));
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
        airCooler = new GenericCrafter("air-cooler") {{
            size = 3;
            requirements(Category.crafting, with(Items.lead,120, Items.metaglass,80, Items.surgeAlloy,50, SFItems.rubidium, 80));
            hasPower = hasLiquids = true;
            liquidCapacity = 120;

            craftTime = 60;
            outputLiquid = new LiquidStack(Liquids.nitrogen, 12/60f);
            consumePower(2.5f);
            consumeLiquid(Liquids.cryofluid, 0.1f);

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.cryofluid){{alpha=0.9f;}},
                    new DrawLiquidRegion(Liquids.nitrogen),
                    new DrawRegion("-rotator"){{rotateSpeed = -1f;}},
                    new DrawDefault()
            );
        }};
        nitrateMixer = new GenericCrafter("nitrate-mixer") {{
            size = 5;
            requirements(Category.crafting, with(Items.lead, 200, Items.metaglass, 150, Items.silicon, 110, Items.plastanium, 50, SFItems.rubidium, 90));
            hasPower = hasLiquids = true;
            liquidCapacity = 300;

            craftTime = 60;
            outputLiquid = new LiquidStack(SFLiquids.nitrate, 2f);
            consumePower(33.5f);
            consumeLiquids(LiquidStack.with(Liquids.water, 3f, Liquids.nitrogen, 1f));

            drawer = new DrawMulti(new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.water),
                    new DrawLiquidTile(Liquids.nitrogen,0.5f),
                    new DrawLiquidTile(SFLiquids.nitrate),
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
            requirements(Category.crafting, with(Items.silicon, 200, Items.thorium,100, Items.plastanium, 150, Items.surgeAlloy, 180, SFItems.chromium, 100));
            hasPower = hasLiquids = hasItems = true;
            itemCapacity = 40;
            liquidCapacity = 300;

            craftTime = 60;
            consumePower(25f);
            consumeLiquid(Liquids.oil, 3.6f);
            outputItem = new ItemStack(Items.coal, 3);
            outputLiquid = new LiquidStack(SFLiquids.mixGas, 2f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawLiquidTile(SFLiquids.mixGas) {{
                alpha = 0.35f;
            }}, new DrawDefault());
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
            requirements(Category.crafting, with(Items.metaglass, 110, Items.surgeAlloy, 20, SFItems.nanoCore, 65, SFItems.waveSteel, 90));
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
            requirements(Category.crafting, with(Items.titanium, 90, Items.metaglass, 150, Items.surgeAlloy, 60, SFItems.siliSteel, 80, SFItems.rubidium, 100));
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
            requirements(Category.crafting, with(Items.plastanium, 90, Items.thorium,120, SFItems.nanoCore, 100, SFItems.rubidium, 150));
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
            requirements(Category.crafting, with(Items.plastanium, 20, Items.silicon,45, SFItems.nanoCore, 30));
            hasPower = hasItems = hasLiquids = true;
            liquidCapacity = 30;

            craftTime = 40;
            outputLiquid = new LiquidStack(SFLiquids.nanoFluid, 0.2f);
            consumePower(1.35f);
            consumeItems(new ItemStack(SFItems.nanoCore, 1));

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
            requirements(Category.crafting, with(Items.metaglass,160, SFItems.waveSteel,80, SFItems.chromium,+0, SFItems.rubidium,80));
            hasPower = hasItems = hasLiquids = true;
            liquidCapacity = 180;

            craftTime = 60;
            outputLiquid = new LiquidStack(SFLiquids.blastReagent, 1.25f*2);
            consumePower(2.25f);
            consumeItem(SFItems.rareEarth, 3);
            consumeLiquid(SFLiquids.nitratedOil, 2f);

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
            boostScale = 1 / 16f;

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
        //endregion
        //region wall + defense
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
            requirements(Category.effect, with(Items.lead,180, Items.plastanium,90, Items.surgeAlloy,75, SFItems.nanoCore,80));
            range = 100f;
            phaseRangeBoost = 100;
            reload = 120;
            healPercent = 8;
            phaseBoost = 4;
            useTime = 350f;
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
            requirements(Category.effect, with(Items.silicon,340, SFItems.fermium,400, SFItems.tayrAlloy,300, SFItems.discFabric,150));
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
        //endregion
        //region transport + liquid
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
            liquidCapacity = 40;
            liquidPressure = 2f;
            bridgeReplacement = Blocks.phaseConduit;
            explosivenessScale = flammabilityScale = 16f / 50f;
        }};
        reArmoredConduit = new ArmoredConduit("complex-armored-conduit") {{
            health = 560;
            armor = 8;
            requirements(Category.liquid, with(Items.titanium, 1, Items.metaglass, 2, SFItems.siliSteel, 1, SFItems.fermium, 1));
            liquidCapacity = 150;
            liquidPressure = 2f;
            bridgeReplacement = discConduit;
            insulated = absorbLasers = placeableLiquid = true;
            explosivenessScale = flammabilityScale = 16f / 50f;
        }};
        silisteelTank = new LiquidRouter("silisteel-tank") {{
            size = 4;
            health = 3600;
            armor = 8;
            requirements(Category.liquid, with(Items.plastanium, 70, Items.metaglass, 250, SFItems.siliSteel, 60, SFItems.chromium, 110));
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
            explosivenessScale = flammabilityScale = 20f / 100f;
            consumePower(0.5f);
        }};
        //endregion
        //region power
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
            requirements(Category.power, with(Items.silicon,15, Items.metaglass,10, SFItems.chromium,15, SFItems.discFabric,7));
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
            requirements(Category.power, with(Items.titanium,85, SFItems.tayrAlloy,15, SFItems.discFabric,20));
            buildCostMultiplier = 0.3f;

            maxNodes = 3;
            laserRange = 80;
            laserScale = 0.5f;
            laserColor2 = Color.valueOf("5F6A89");
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
        gasSmoker = new ConsumeGenerator("gas-generator") {{
            size = 1;
            requirements(Category.power, with(Items.metaglass, 30, Items.titanium, 40));
            hasPower = hasLiquids = true;

            powerProduction = 13 / 6f;
            consumeLiquid(SFLiquids.mixGas, 0.1f);

            generateEffect = Fx.steam;
            effectChance = 0.01f;
            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());
            ambientSound = Sounds.steam;
            ambientSoundVolume = 0.02f;
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
            requirements(Category.power, with(Items.surgeAlloy,30,SFItems.chromium,150, SFItems.waveSteel,200, Items.phaseFabric,60));
            buildCostMultiplier = 0.9f;
            attribute = SFAttribute.radioactivity;

            powerProduction = 2f;
            generateEffect = new ParticleEffect() {{
                particles = 5;
                sizeFrom = 3;
                sizeTo = 3;
                length = 8;
                lifetime = 90;
                interp = Interp.pow3Out;
                sizeInterp = Interp.pow10In;
                colorFrom = SFColor.discDark.cpy().a(0.8f);
                colorTo = SFColor.discLight.cpy().a(0f);
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
            requirements(Category.power, with(Items.lead,300, Items.graphite,90, Items.metaglass,90, SFItems.siliSteel,130, SFItems.fermium,110));
            buildCostMultiplier = 0.8f;
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 60;

            powerProduction = 29.7f;
            consumeLiquid(Liquids.cryofluid, 0.18f);
            consumeItem(SFItems.fermium, 1);
            heating = 0.01f;
            coolantPower = 3f;
            itemDuration = 300;
            fuelItem = SFItems.fermium;

            explosionShake = 9;
            explosionShakeDuration = 120f;
            explosionDamage = 2300;
            explosionRadius = 35;
            explodeSound = SFSounds.hugeExplosion;
            explodeEffect = new MultiEffect(
                    new ExplosionEffect() {{
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
                    new ParticleEffect() {{
                        particles = 25;
                        sizeInterp = Interp.pow5In;
                        interp = Interp.pow10Out;
                        sizeFrom = 35f;
                        length = 280f;
                        lifetime = 250f;
                        colorFrom = colorTo = Color.valueOf("DEDEDE70");
                    }},
                    new ParticleEffect() {{
                        particles = 35;
                        sizeInterp = Interp.pow5In;
                        interp = Interp.pow10Out;
                        sizeFrom = 30f;
                        length = 290f;
                        lifetime = 300f;
                        colorFrom = colorTo = Color.valueOf("DEDEDE70");
                    }},
                    new ParticleEffect() {{
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
            requirements(Category.power, with(SFItems.waveSteel,1200, Items.metaglass,600, SFItems.rubidium,560, SFItems.fermium,780, SFItems.siliSteel,800, SFItems.tayrAlloy,700));
            buildCostMultiplier = 0.8f;
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 30;
            liquidCapacity = 600;
            warmupSpeed = 0.0005f;

            powerProduction = 267 + 333f;
            consumePower(28f);
            consumeLiquid(Liquids.cryofluid, 2.4f);
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
                        plasma2 = SFColor.tayrDark;
                    }},
                    new DrawDefault(),
                    new DrawFlame() {{
                        flameColor = Color.valueOf("BFFFDB");
                    }},
                    new DrawLiquidRegion(Liquids.cryofluid)) {{
                lightLiquid = Liquids.cryofluid;
            }};
            destroyBullet = new ExplosionBulletType(1000, 480) {{
                buildingDamageMultiplier = 6f;
                makeFire = true;
                hitSound = SFSounds.hugeExplosion;
                hitSoundVolume = 3;
                hitShake = 50f;
                hitEffect = new WaveEffect() {{
                    lifetime = 60f;
                    sizeFrom = 20f;
                    sizeTo = 530f;
                    strokeFrom = 30;
                    interp = Interp.circleOut;
                    colorTo = SFColor.discLight;
                }};
                status = SFStatusEffects.breakdown;
                statusDuration = 1200f;
                despawnEffect = new MultiEffect(
                        Fx.impactReactorExplosion,
                        new ParticleEffect() {{
                            particles = 1;
                            sizeFrom = 10;
                            sizeTo = 480f;
                            length = 0;
                            lifetime = 22f;
                            sizeInterp = Interp.pow10Out;
                            colorTo = SFColor.discLight;
                        }},
                        new ParticleEffect() {{
                            particles = 1;
                            sizeFrom = 480;
                            startDelay = 19f;
                            length = 0;
                            lifetime = 60;
                            colorFrom = colorTo = SFColor.discLight;
                        }}
                );
                fragBullets = 60;
                fragLifeMin = 0.15f;
                fragLifeMax = 1.1f;
                fragBullet = new BasicBulletType(30, 150, "circle-bullet") {{
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
                    trailColor = backColor = SFColor.discLight;
                    trailLength = 18;
                    trailWidth = 2.25f;
                    hitEffect = new WaveEffect() {{
                        lifetime = 35;
                        sizeFrom = 40f;
                        sizeTo = 230f;
                        strokeFrom = 30;
                        interp = Interp.circleOut;
                        colorFrom = colorTo = SFColor.discLight;
                    }};
                    status = SFStatusEffects.breakdown;
                    statusDuration = 1000f;
                    despawnEffect = new MultiEffect(
                            new ParticleEffect() {{
                                particles = 1;
                                sizeFrom = 10;
                                sizeTo = 180;
                                length = 0;
                                lifetime = 12f;
                                sizeInterp = Interp.pow10Out;
                                colorTo = SFColor.discLight;
                            }},
                            new ParticleEffect() {{
                                particles = 1;
                                sizeFrom = 180;
                                startDelay = 9f;
                                length = 0;
                                lifetime = 60;
                                colorFrom = colorTo = SFColor.discLight;
                            }}
                    );
                }};
            }};
        }};
        arcFissionReactor = new ConsumeGenerator("arc-fission-reactor") {{
            size = 10;
            armor = 18;
            health = 33000;
            requirements(Category.power, with(SFItems.chromium,1100, Items.metaglass,1100, Items.plastanium,600, SFItems.fermium,1700, SFItems.nanoCore,1200, SFItems.discFabric,800));
            buildCostMultiplier = 0.8f;
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 300;
            warmupSpeed = 0.006f;

            powerProduction = 550f;
            itemDuration = 35f;
            consumeLiquid(SFLiquids.nanoFluid, 2.7f);
            consume(new ConsumeItemRadioactive(0.75f));
            consume(new ConsumeItemExplode() {{
                baseChance = 0.05f;
                damage = 150f;
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
        //endregion
        //region drill
        energyDrill = new PressureDrill("energe-drill") {{
            size = 4;
            requirements(Category.production, with(Items.copper,75, Items.silicon,40, Items.graphite,40));
            hasPower = hasItems = true;
            hasLiquids = false;
            itemCapacity = 20;
            drillTime = 360;
            tier = 3;

            consumePower(1.75f);
            //consumeLiquid(Liquids.water, 0.3f).boost();
            liquidBoostIntensity = 1;

            drillEffect = Fx.mineBig;
            updateEffect = Fx.pulverizeMedium;
        }};
        heavyDrill = new Drill("heavy-drill") {{
            size = 4;
            health = 1200;
            requirements(Category.production, with(Items.silicon, 160, Items.plastanium, 45, Items.surgeAlloy, 80, SFItems.siliSteel, 70));
            hasPower = hasItems = hasLiquids = true;
            itemCapacity = 20;
            liquidCapacity = 30;
            drillTime = 252 * 0.7f;
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
            requirements(Category.production, with(Items.graphite, 180, Items.thorium, 110, Items.phaseFabric, 80, SFItems.waveSteel, 80));
            hasLiquids = hasItems = true;
            itemCapacity = 50;
            liquidCapacity = 20;
            drillTime = 80;
            tier = 10;
            arrows = 4;
            arrowSpacing = 1.5f;
            arrowOffset = 0;
            arrowColor = Color.valueOf("FEC59E80");
            drillMultipliers.put(Items.sand,2);
            drillMultipliers.put(Items.scrap,2);
            drillMultipliers.put(SFItems.rareEarth,2);
            blockedItem = SFItems.fermium;

            consumeLiquid(SFLiquids.blastReagent, 0.1f);
            liquidBoostIntensity = 1;
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
            requirements(Category.production, with(SFItems.siliSteel, 800, SFItems.discFabric, 400, SFItems.leipAlloy, 500));
            hasLiquids = hasItems = true;
            itemCapacity = 100;
            liquidCapacity = 60;
            drillTime = 5;
            tier = 20;
            arrows = 0;

            consumePower(20);
            consumeLiquid(SFLiquids.nanoFluid, 0.6f);
            liquidBoostIntensity = 1;
            drillEffect = new ParticleEffect() {{
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
        //endregion
        //region storage
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
            requirements(Category.effect, with(Items.silicon,4000, Items.thorium,3000, SFItems.waveSteel,3000, SFItems.siliSteel,3000));
            health = 20000;
            armor = 25;
            itemCapacity = 30000;
            unitCapModifier = 20;
            researchCostMultiplier = 0.5f;
            unitType = SFUnitTypes.tau;
        }};
        finalCommandCenter = new CoreBlock("final-command-core") {{
            size = 8;
            requirements(Category.effect, with(Items.titanium,8000, Items.silicon,6000, SFItems.waveSteel,3500, SFItems.fermium,5000));
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
        //endregion
        //region turrets
    //2*2//
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
            requirements(Category.turret, with(Items.copper, 70, Items.lead, 40));

            reload = 20;
            rotateSpeed = 13;
            range = 120;
            consumePower(1.55f);
            coolant = consumeCoolant(0.1f);
            shootType = new BasicBulletType(6, 11, "circle-bullet") {{
                lifetime = 20;
                ammoMultiplier = 1;
                status = SFStatusEffects.scrambled;
                statusDuration = 20;

                frontColor = Color.white;
                backColor = SFColor.tayrLight;
                shrinkY = 0;
                width = height = 3;
                hitColor = SFColor.tayrDark;
                shootEffect = hitEffect = despawnEffect = Fx.hitLaserColor;
                smokeEffect = Fx.none;
            }};
        }};
        huojian = new ItemTurret("huojian") {{
            size = 1;
            health = 220;
            recoil = 1f;
            shootSound = Sounds.missile;
            shake = 1;
            inaccuracy = 5;
            researchCostMultiplier = 0.05f;
            requirements(Category.turret, with(Items.copper, 35, Items.graphite, 30));

            reload = 28;
            rotateSpeed = 10;
            range = 200;
            maxAmmo = 24;
            coolant = consumeCoolant(0.1f);
            shootEffect = Fx.shootSmallFlame;
            ammo(
                    Items.graphite, new MissileBulletType(8, 10, "sfire-mod-missile1") {{
                        lifetime = 25;
                        homingPower = 0.03f;
                        homingRange = 25;
                        splashDamage = 23;
                        splashDamageRadius = 16;
                        trailColor = backColor = Pal.graphiteAmmoFront;
                        frontColor = SFColor.missileGray;
                        width = 4;
                        height = 16;
                        hitEffect = Fx.explosion;
                    }},
                    Items.pyratite, new MissileBulletType(8, 12, "sfire-mod-missile1") {{
                        lifetime = 25;
                        homingPower = 0.03f;
                        homingRange = 25;
                        splashDamage = 33;
                        splashDamageRadius = 20;
                        trailColor = backColor = Pal.lightPyraFlame;
                        frontColor = SFColor.missileGray;
                        width = 4;
                        height = 16;
                        hitEffect = Fx.flakExplosion;
                        makeFire = true;
                        status = StatusEffects.burning;
                        statusDuration = 100;
                    }},
                    Items.blastCompound, new MissileBulletType(8, 14, "sfire-mod-missile1") {{
                        ammoMultiplier = 4;
                        lifetime = 25;
                        homingPower = 0.03f;
                        homingRange = 25;
                        splashDamage = 40;
                        splashDamageRadius = 25;
                        trailColor = backColor = Pal.blastAmmoFront;
                        frontColor = SFColor.missileGray;
                        width = 4;
                        height = 16;
                        hitEffect = Fx.blastExplosion;
                        status = StatusEffects.blasted;
                    }}
            );
        }};
        dianguang = new PowerTurret("dianguang") {{
            size = 2;
            health = 1100;
            recoil = 1f;
            recoilTime = 10;
            shootSound = Sounds.laser;
            shake = 2f;
            heatColor = Color.valueOf("FF4040");
            researchCostMultiplier = 0.05f;
            requirements(Category.turret, with(Items.lead, 90, Items.silicon, 80, SFItems.siliSteel, 45));

            reload = 35;
            rotateSpeed = 6;
            range = 200;
            consumePower(8);
            coolant = consumeCoolant(0.2f);
            shootType = new BasicBulletType(10, 60) {{
                lifetime = 17f;
                splashDamage = 50;
                splashDamageRadius = 45;
                ammoMultiplier = 1;
                status = StatusEffects.burning;
                statusDuration = 600;
                knockback = 2.25f;

                frontColor = Color.white;
                trailColor = backColor = Pal.lightPyraFlame;
                trailWidth = 2.5f;
                trailLength = 16;
                width = 8;
                height = 26;
                hitColor = Pal.lightPyraFlame;
                shootEffect = Fx.shootBigColor;
                smokeEffect = Fx.none;
                hitEffect = new ExplosionEffect() {{
                    sparks = 0;
                    smokes = 16;
                    smokeSize = 6;
                    smokeRad = 35;
                    smokeColor = Pal.lightPyraFlame.cpy().a(0.4f);
                    lifetime = 25;
                    waveRad = 45;
                    waveLife = 15;
                    waveStroke = 4;
                    waveColor = Pal.lightPyraFlame;
                }};
                despawnEffect = Fx.none;
            }};
        }};
        bingfengbao = new PowerTurret("bingfengbao") {{
            size = 2;
            health = 1200;
            recoil = 1f;
            recoilTime = 10;
            shootSound = Sounds.missile;
            soundPitchMax = soundPitchMin = 0.5f;
            heatColor = Color.valueOf("7090EA80");
            shake = 0.2f;
            researchCostMultiplier = 0.05f;
            requirements(Category.turret, with(Items.copper, 90, Items.lead, 100, Items.silicon, 80));

            reload = 5f;
            rotateSpeed = 13;
            range = 200;
            consumePower(7);
            coolant = consumeCoolant(0.2f);
            shoot = new ShootAlternate(4);
            inaccuracy = 3;
            shootType = new BasicBulletType(10, 10, "circle-bullet") {{
                lifetime = 20;
                ammoMultiplier = 1;
                status = StatusEffects.freezing;
                statusDuration = 60;
                pierce = true;
                pierceCap = 2;

                frontColor = Color.white;
                trailColor = backColor = SFColor.energySky;
                trailWidth = 1.3f;
                trailLength = 2;
                width = 2;
                height = 6;
                hitColor = SFColor.energySky;
                shootEffect = Fx.shootSmallColor;
                smokeEffect = Fx.none;
                hitEffect = Fx.hitBulletColor;
                despawnEffect = Fx.hitLaserColor;
            }};
        }};
        gaosi = new PowerTurret("gaosi") {{
            size = 2;
            health = 990;
            recoil = 2f;
            recoilTime = cooldownTime = 60;
            shootSound = Sounds.spark;
            soundPitchMax = soundPitchMin = 2.2f;
            shootY = 12;
            shake = 2f;
            heatColor = Color.valueOf("FF4040");
            researchCostMultiplier = 0.05f;
            requirements(Category.turret, with(Items.titanium, 100, SFItems.rubidium, 90));

            reload = 135;
            reloadWhileCharging = false;
            moveWhileCharging = false;
            accurateDelay = false;
            rotateSpeed = 3;
            chargeSound = Sounds.lasercharge;
            shoot.firstShotDelay = 60;
            range = 200;
            consumePower(11);
            shootType = new PowerupBulletType(2, 10, "circle-bullet") {{
                damageUp = 0.5f;
                maxDamageMultiplier = 3f;

                lifetime = 120;
                ammoMultiplier = 1;
                status = SFStatusEffects.magnStrif;
                statusDuration = 10;
                pierceBuilding = false;
                buildingDamageMultiplier = 0.25f;
                homingPower = 0.02f;
                homingRange = 35;

                frontColor = Color.white;
                backColor = Pal.lancerLaser;
                trailInterval = 30 / 4f;
                trailEffect = despawnEffect = new ParticleEffect() {{
                    lifetime = 80;
                    length = 0;
                    sizeFrom = 12 / 2f;
                    sizeTo = 1;
                    sizeInterp = Interp.pow2In;
                    colorFrom = backColor;
                    colorTo = backColor.cpy().a(0);
                }};
                width = height = 12;
                shrinkY = 0;
                shootEffect = Fx.shootBigColor;
                smokeEffect = Fx.sparkShoot;
                hitSound = Sounds.spark;
                hitEffect = new ExplosionEffect() {{
                    sparks = 16;
                    smokes = 0;
                    sparkLen = 6;
                    sparkRad = 35;
                    sparkColor = Pal.lancerLaser.cpy().a(0.4f);
                    lifetime = 25;
                    waveRad = 45;
                    waveLife = 15;
                    waveStroke = 4;
                    waveColor = Pal.lancerLaser;
                }};
                lightningDamage = damage / 3f;
                lightning = 3;
                lightningLength = 14;
                lightningLengthRand = 6;
                lightningColor = Pal.lancerLaser;
                lightningType = new BulletType(0.00001f, 0f) {{
                    hitEffect = Fx.hitLancer;
                    despawnEffect = Fx.none;
                    lightColor = Color.white;
                    status = StatusEffects.shocked;
                    statusDuration = 10f;
                    hittable = false;
                    buildingDamageMultiplier = 0.25f;
                }};
                chargeEffect = new MultiEffect(
                        new ParticleEffect() {{
                            particles = 9;
                            lifetime = 50;
                            sizeFrom = 0;
                            sizeTo = 3;
                            length = -30;
                            baseLength = 30;
                            colorTo = Pal.lancerLaser;
                            sizeInterp = Interp.bounceIn;
                        }},
                        new ParticleEffect() {{
                            lifetime = 61;
                            sizeTo = 6;
                            length = 0;
                            colorTo = Pal.lancerLaser;
                            sizeInterp = Interp.bounceIn;
                        }}
                );
            }};
        }};
        liebao = new PowerTurret("liebao") {{
            size = 2;
            health = 1000;
            recoil = 2f;
            recoilTime = 30;
            shootSound = Sounds.laser;
            chargeSound = Sounds.lasercharge2;
            shake = 2f;
            heatColor = Color.valueOf("FF4040");
            moveWhileCharging = false;
            shoot.firstShotDelay = 50;
            drawer = new DrawTurret() {{
                parts.add(new HaloPart() {{
                    progress = PartProgress.recoil;
                    haloRadius = haloRotation = 0;
                    rotateSpeed = shapeRotation = 0;
                    y = 8;
                    sides = 3;
                    shapes = 4;
                    color = SFColor.tayrLight;
                    tri = hollow = true;
                    radius = triLength = 0;
                    radiusTo = 5;
                    triLengthTo = 60;
                    haloRotateSpeed = 4;
                    layer = 110;
                }});
            }};
            researchCostMultiplier = 0.05f;
            requirements(Category.turret, with(Items.lead, 90, SFItems.siliSteel, 40));

            reload = 120;
            rotateSpeed = 8;
            range = 200;
            consumePower(6);
            inaccuracy = 1;
            shootType = new BasicBulletType(5, 25, "circle-bullet") {{
                lifetime = 30;
                ammoMultiplier = 1;
                status = SFStatusEffects.scrambled;
                statusDuration = 40;
                splashDamage = damage;
                splashDamageRadius = 65;

                frontColor = Color.white;
                trailColor = backColor = hitColor = SFColor.tayrLight;
                trailLength = 15;
                trailWidth = 4;
                shrinkY = 0;
                width = height = 13;
                hitSound = Sounds.plasmaboom;
                hitEffect = new ExplosionEffect() {{
                    sparks = smokes = 8;
                    smokeSize = 6;
                    smokeRad = 35;
                    smokeColor = hitColor.cpy().a(0.4f);
                    sparkColor = hitColor;
                    sparkLen = 11;
                    sparkRad = 50;
                    lifetime = 45;
                    waveRad = 65;
                    waveLife = 20;
                    waveStroke = 3;
                    waveColor = hitColor;
                }};
                despawnEffect = Fx.bigShockwave;
                shootEffect = Fx.shootBigColor;
                smokeEffect = Fx.none;
                chargeEffect = new MultiEffect(
                        new ParticleEffect() {{
                            lifetime = 50;
                            sizeFrom = 0;
                            sizeTo = 2;
                            length = -80;
                            baseLength = 80;
                            interp = Interp.pow2In;
                            colorTo = hitColor;
                            cone = 90;
                        }},
                        new ParticleEffect() {{
                            lifetime = 51;
                            sizeFrom = 0;
                            sizeTo = 6;
                            length = 0;
                            colorTo = hitColor;
                            sizeInterp = Interp.pow2In;
                        }}
                );
            }};
        }};
        cuodao = new PowerTurret("cuodao") {{
            size = 2;
            health = 360;
            recoil = 2f;
            recoilTime = 10;
            targetGround = false;
            shootSound = Sounds.shootBig;
            shoot.shots = 4;
            shootEffect = Fx.shootBig;
            shake = 2f;
            researchCostMultiplier = 0.05f;
            requirements(Category.turret, with(Items.lead, 85, Items.titanium, 60, Items.silicon, 40));

            reload = 35;
            rotateSpeed = 7;
            range = 200;
            consumePower(4);
            consumeCoolant(0.2f);
            inaccuracy = 7;
            velocityRnd = 0.05f;
            shootType = new PointBulletType() {{
                smokeEffect = new ParticleEffect() {{
                    particles = 3;
                    line = true;
                    lenFrom = 24;
                    lenTo = 0;
                    strokeFrom = strokeTo = 0.4f;
                    length = 60;
                    lifetime = 12;
                    cone = 10;
                    colorFrom = Pal.bulletYellow;
                    colorTo = Pal.bulletYellowBack;
                }};
                collidesGround = false;
                despawnEffect = hitEffect = Fx.flakExplosion;
                trailEffect = Fx.none;
                splashDamage = 16;
                splashDamageRadius = 20;
                status = StatusEffects.blasted;
                speed = 20;
            }};
        }};
        longxi = new ItemTurret("longxi") {{
            size = 2;
            health = 1420;
            recoil = 2f;
            cooldownTime = recoilTime = 105;
            heatColor = Color.valueOf("f68021");
            shootSound = Sounds.flame;
            researchCostMultiplier = 0.5f;
            drawer = new DrawTurret() {{
                parts.add(
                        new RegionPart("-back") {{
                            mirror = false;
                            under = true;
                            moveY = -1.5f;
                        }},
                        new RegionPart("-front") {{
                            mirror = true;
                            heatColor = Color.valueOf("f68021");
                            under = true;
                            moveRot = -5;
                            moveY = 0.25f;
                        }}
                );
            }};
            requirements(Category.turret, with(Items.copper, 135, Items.graphite, 80, Items.silicon, 30));

            reload = 3;
            rotateSpeed = 9;
            range = 87.8f;
            maxAmmo = 60;
            shootY = 3.5f;
            consumePower(0.1f);
            ammo(
                    Items.pyratite, new BulletType(8, 58) {{
                        lifetime = 11;
                        hitSize = 10;
                        ammoMultiplier = 4;
                        pierce = pierceBuilding = true;
                        hittable = false;
                        absorbable = false;
                        reflectable = false;
                        makeFire = true;
                        status = StatusEffects.burning;
                        statusDuration = 360;
                        shootEffect = new Effect(33f, 80f, e -> {
                            color(Pal.lightPyraFlame, Pal.darkPyraFlame, Color.gray, e.fin());

                            randLenVectors(e.id, 13, e.finpow() * 88f, e.rotation, 10f, (x, y) -> {
                                Fill.circle(e.x + x, e.y + y, 0.65f + e.fout() * 1.6f);
                            });
                        });
                        hitEffect = Fx.hitFlameSmall;
                        despawnEffect = new ParticleEffect() {{
                            particles = 3;
                            lifetime = 10;
                            sizeFrom = 3;
                            colorFrom = colorTo = Color.valueOf("FF8C00");
                        }};
                    }},
                    SFItems.strontium, new BulletType(8, 66) {{
                        lifetime = 11;
                        hitSize = 10;
                        ammoMultiplier = 8;
                        rangeChange = 16;
                        pierce = pierceBuilding = true;
                        hittable = false;
                        absorbable = false;
                        reflectable = false;
                        makeFire = true;
                        status = StatusEffects.burning;
                        statusDuration = 600;
                        shootEffect = new Effect(33, 80f, e -> {
                            color(SFColor.strontiumLight, SFColor.strontiumDark, Color.gray, e.fin());

                            randLenVectors(e.id, 16, e.finpow() * 116, e.rotation, 10f, (x, y) -> {
                                Fill.circle(e.x + x, e.y + y, 0.65f + e.fout() * 1.6f);
                            });
                        });
                        smokeEffect = new ParticleEffect() {{
                            particles = 3;
                            line = true;
                            strokeFrom = 1.8f;
                            strokeTo = 0;
                            lenFrom = 20;
                            lenTo = 8;
                            length = 86f;
                            lifetime = 12;
                            colorFrom = Color.valueOf("FFFFFF");
                            colorTo = SFColor.strontiumLight;
                            cone = 10;
                        }};
                        hitEffect = new ParticleEffect() {{
                            particles = 2;
                            line = true;
                            strokeFrom = 1.3f;
                            strokeTo = 0;
                            lenFrom = lenTo = 8;
                            length = 24;
                            lifetime = 15;
                            colorFrom = SFColor.strontiumLight;
                            colorTo = Color.valueOf("D97C7C");
                            cone = 23;
                        }};
                        despawnEffect = Fx.none;
                        fragBullets = 2;
                        fragBullet = new FireBulletType(8, 36) {{
                            lifetime = 8;
                            drag = 0.01f;
                            status = StatusEffects.burning;
                            statusDuration = 360;
                            makeFire = true;
                        }};
                    }}
            );
        }};
        mengma = new ItemTurret("mengma") {{
            size = 2;
            health = 1420;
            recoil = 0.5f;
            cooldownTime = recoilTime = 20;
            shootSound = Sounds.artillery;
            targetAir = false;
            shake = 1.7f;
            shoot.shots = 2;
            shoot.shotDelay = 15;
            drawer = new DrawTurret() {{
                parts.add(new RegionPart("-barrel") {{
                    progress = PartProgress.recoil;
                    moveY = -4;
                }});
            }};
            requirements(Category.turret, with(Items.copper, 95, Items.graphite, 55, Items.titanium, 65));

            reload = 60;
            rotateSpeed = 6;
            range = 280;
            inaccuracy = 3;
            ammoPerShot = 2;
            ammoUseEffect = Fx.casing3;
            shootEffect = Fx.shootBig;
            smokeEffect = Fx.shootBigSmoke;
            ammo(
                    Items.graphite, new ArtilleryBulletType(4, 15) {{
                        lifetime = 64;
                        splashDamage = 33;
                        splashDamageRadius = 20;
                        width = height = 12;
                        collidesTiles = false;
                        hitEffect = Fx.explosion;
                        backColor = hitColor = trailColor = Pal.graphiteAmmoBack;
                        frontColor = Pal.graphiteAmmoFront;
                        despawnEffect = Fx.hitBulletColor;
                    }},
                    Items.pyratite, new ArtilleryBulletType(4, 15) {{
                        lifetime = 64;
                        splashDamage = 45;
                        splashDamageRadius = 20;
                        width = height = 12;
                        collidesTiles = false;
                        hitEffect = Fx.blastExplosion;
                        status = StatusEffects.burning;
                        statusDuration = 360;
                        frontColor = Pal.lightishOrange;
                        backColor = hitColor = trailColor = Pal.lightOrange;
                        makeFire = true;
                        trailEffect = Fx.incendTrail;
                        ammoMultiplier = 4;
                    }},
                    Items.blastCompound, new ArtilleryBulletType(4, 15) {{
                        lifetime = 64;
                        splashDamage = 55;
                        splashDamageRadius = 35;
                        width = height = 12;
                        collidesTiles = false;
                        hitEffect = Fx.blastExplosion;
                        status = StatusEffects.blasted;
                        backColor = hitColor = trailColor = Pal.blastAmmoBack;
                        frontColor = Pal.blastAmmoFront;
                        ammoMultiplier = 4;
                    }},
                    SFItems.clusBomb, new ArtilleryBulletType(3, 15) {{
                        lifetime = 83f;
                        splashDamage = 50;
                        splashDamageRadius = 28;
                        width = height = 12;
                        collidesTiles = false;
                        hitEffect = Fx.flakExplosionBig;
                        status = StatusEffects.blasted;
                        backColor = trailColor = SFColor.clusRedDark;
                        frontColor = SFColor.clusRed;
                        ammoMultiplier = 6;
                        fragBullets = 5;
                        fragBullet = new ArtilleryBulletType(1.5f, 8) {{
                            lifetime = 20;
                            splashDamage = 50;
                            splashDamageRadius = 28;
                            scaledSplashDamage = true;
                            width = height = 9;
                            hitEffect = Fx.blastExplosion;
                            status = StatusEffects.blasted;
                            backColor = trailColor = SFColor.clusRedDark;
                            frontColor = SFColor.clusRed;
                        }};
                    }}
            );
        }};
    //3*3//
        kuodao = new ItemTurret("kuodao") {{
            size = 3;
            health = 2750;
            armor = 8;
            recoil = 2f;
            recoilTime = 25;
            shootSound = Sounds.shotgun;
            shake = 3;
            inaccuracy = 25;
            shoot = new ShootSpread(35, 0.77f);
            shootY = 8;
            drawer = new DrawTurret() {{
                parts.add(new RegionPart("-mid") {{
                    mirror = false;
                    moveY = -1f;
                    progress = PartProgress.reload;
                }});
            }};
            requirements(Category.turret, with(Items.copper, 140, Items.graphite, 35, Items.titanium, 80));

            reload = 22;
            rotateSpeed = 12;
            range = 125;
            coolant = consumeCoolant(0.3f);
            shootCone = 35;
            shootEffect = new ParticleEffect() {{
                particles = 1;
                lifetime = 15;
                line = true;
                length = 55;
                lenFrom = 2;
                colorTo = colorFrom = Pal.lightOrange;
                cone = 60;
            }};
            maxAmmo = 40;
            ammoUseEffect = Fx.casing2Double;
            ammoPerShot = 4;
            velocityRnd = 0.3f;
            ammo(
                    Items.titanium, new BasicBulletType(9.4f, 12) {{
                        lifetime = 13f;
                        buildingDamageMultiplier = 0.2f;
                        knockback = 1.5f;

                        width = 8;
                        height = 10;
                        ammoMultiplier = 4;
                        pierce = true;
                        pierceCap = 3;
                        backColor = frontColor = hitColor = trailColor = Items.titanium.color;
                        trailWidth = 2.2f;
                        trailLength = 2;
                        hitEffect = new ParticleEffect() {{
                            particles = 3;
                            line = true;
                            length = 16;
                            lifetime = 10;
                            colorFrom = hitColor;
                        }};
                        despawnEffect = Fx.hitBulletColor;
                        shootEffect = smokeEffect = Fx.none;
                    }},
                    SFItems.strontium, new BasicBulletType(9.3f, 15) {{
                        lifetime = 14f;
                        splashDamageRadius = 17.6f;
                        splashDamage = 25;
                        makeFire = true;
                        status = StatusEffects.burning;
                        statusDuration = 600;
                        buildingDamageMultiplier = 0.2f;
                        knockback = 2.5f;

                        width = 8;
                        height = 10;
                        ammoMultiplier = 6;
                        backColor = frontColor = hitColor = trailColor = SFColor.strontiumLight;
                        trailWidth = 2.2f;
                        trailLength = 2;
                        hitEffect = new MultiEffect(
                                new ParticleEffect() {{
                                    particles = 3;
                                    line = true;
                                    length = 16;
                                    lifetime = 10;
                                    colorFrom = hitColor;
                                }},
                                Fx.hitBulletColor
                        );
                        despawnEffect = new Effect(35f, e -> {
                            color(SFColor.strontiumLight, SFColor.strontiumDark, e.fin());
                            randLenVectors(e.id, 3, 2f + e.fin() * 10f, (x, y) -> {
                                Fill.circle(e.x + x, e.y + y, 0.2f + e.fout() * 1.6f);
                            });
                            color();
                        });
                        shootEffect = smokeEffect = Fx.none;
                    }},
                    Items.thorium, new BasicBulletType(9.5f, 18) {{
                        lifetime = 13f;
                        buildingDamageMultiplier = 0.2f;
                        knockback = 1.5f;

                        width = 8;
                        height = 10;
                        ammoMultiplier = 6;
                        pierce = true;
                        pierceArmor = true;
                        pierceCap = 4;
                        backColor = frontColor = hitColor = trailColor = Items.thorium.color;
                        trailWidth = 2.3f;
                        trailLength = 2;
                        hitEffect = new ParticleEffect() {{
                            particles = 3;
                            line = true;
                            length = 16;
                            lifetime = 10;
                            colorFrom = hitColor;
                        }};
                        despawnEffect = Fx.hitBulletColor;
                        shootEffect = smokeEffect = Fx.none;
                    }}
            );
        }};
        mini = new ItemTurret("mini") {{
            size = 3;
            health = 2200;
            armor = 5;
            recoil = 2.88f;
            recoilTime = 25;
            shootSound = Sounds.shootBig;
            shake = 1.5f;
            inaccuracy = 3;
            shoot = new ShootAlternate(9);
            requirements(Category.turret, with(Items.titanium, 115, Items.graphite, 80, Items.thorium, 100, Items.metaglass, 20));

            reload = 4.5f;
            rotateSpeed = 6;
            range = 240;
            coolantMultiplier = 6f;
            liquidCapacity = 60;
            coolant = consumeCoolant(0.3f);
            shootCone = 180;
            shootEffect = Fx.shootBig;
            maxAmmo = 150;
            ammoUseEffect = Fx.casing3;
            ammo(
                    Items.graphite, new BasicBulletType(9, 22) {{
                        lifetime = 270 / 9f;
                        splashDamage = 13;
                        splashDamageRadius = 15;
                        width = 8;
                        height = 13;
                        knockback = 0.8f;
                        ammoMultiplier = 4;
                        rangeChange = 4f * 8f;

                        backColor = hitColor = Pal.graphiteAmmoBack;
                        frontColor = Pal.graphiteAmmoFront;
                        hitEffect = Fx.flakExplosion;
                    }},
                    Items.silicon, new BasicBulletType(13, 17) {{
                        lifetime = 19;
                        width = 9;
                        height = 13;
                        homingPower = 0.12f;
                        ammoMultiplier = 5;
                        reloadMultiplier = 1.3f;

                        trailLength = 5;
                        trailWidth = 1.5f;
                        backColor = hitColor = trailColor = Pal.siliconAmmoBack;
                        frontColor = Pal.siliconAmmoFront;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                    }},
                    Items.pyratite, new BasicBulletType(9, 20) {{
                        lifetime = 240 / 9f;
                        splashDamage = 14f;
                        splashDamageRadius = 22f;
                        status = StatusEffects.burning;
                        makeFire = true;
                        width = 9;
                        height = 13;
                        ammoMultiplier = 5;

                        frontColor = hitColor = Pal.lightishOrange;
                        backColor = Pal.lightOrange;
                        hitEffect = new MultiEffect(Fx.hitBulletColor, Fx.fireHit);
                    }},
                    Items.thorium, new BasicBulletType(9, 31, "bullet") {{
                        lifetime = 240 / 9f;
                        width = 8f;
                        height = 13f;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 4;

                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        backColor = hitColor = Pal.thoriumAmmoBack;
                        frontColor = Pal.thoriumAmmoFront;
                    }}
            );
        }};
        woliu = new LiquidTurret("woliu") {{
            size = 3;
            health = 1900;
            recoil = 1f;
            recoilTime = 60;
            reload = 6;
            shoot.shots = 5;
            shoot.shotDelay = 1.5f;
            velocityRnd = 0.1f;
            requirements(Category.turret, with(Items.metaglass,130, Items.thorium,90, SFItems.siliSteel,70));

            velocityRnd = 0.1f;
            inaccuracy = 2f;
            shootCone = 45f;
            liquidCapacity = 150f;
            shootEffect = Fx.shootLiquid;
            range = 250f;
            ammo(
                    Liquids.water, new LiquidBulletType(Liquids.water){{
                        lifetime = 30;
                        speed = 8f;
                        puddleSize = 8f;
                        orbSize = 5;
                        drag = 0.001f;
                        ammoMultiplier = 0.2f;
                        statusDuration = 60f * 4f;
                        layer = Layer.bullet - 2f;

                        damage = 0.4f;
                        knockback = 1.7f;
                    }},
                    Liquids.slag,  new LiquidBulletType(Liquids.slag){{
                        lifetime = 30;
                        speed = 8f;
                        puddleSize = 8f;
                        orbSize = 5;
                        drag = 0.001f;
                        ammoMultiplier = 0.2f;
                        statusDuration = 60f * 4f;

                        damage = 6.25f;
                        knockback = 1.3f;
                    }},
                    Liquids.cryofluid, new LiquidBulletType(Liquids.cryofluid){{
                        lifetime = 30;
                        speed = 8f;
                        puddleSize = 8f;
                        orbSize = 5;
                        drag = 0.001f;
                        ammoMultiplier = 0.2f;
                        statusDuration = 60f * 4f;

                        damage = 0.4f;
                        knockback = 1.3f;
                    }},
                    Liquids.oil, new LiquidBulletType(Liquids.oil){{
                        lifetime = 30;
                        speed = 8f;
                        puddleSize = 8f;
                        orbSize = 5;
                        drag = 0.001f;
                        ammoMultiplier = 0.2f;
                        statusDuration = 60f * 4f;
                        layer = Layer.bullet - 2f;

                        damage = 0.2f;
                        knockback = 1.3f;
                    }},
                    SFLiquids.nanoFluid, new LiquidBulletType(SFLiquids.actiNanofluid){{
                        lifetime = 30;
                        speed = 8f;
                        puddleSize = 8f;
                        orbSize = 5;
                        drag = 0.001f;
                        ammoMultiplier = 0.2f;
                        status = SFStatusEffects.disRepair;
                        statusDuration = 60f * 4f;

                        damage = 0.2f;
                        knockback = 0.3f;
                    }},
                    SFLiquids.nitrate, new LiquidBulletType(SFLiquids.nitrate){{
                        lifetime = 30;
                        speed = 8f;
                        puddleSize = 8f;
                        orbSize = 5;
                        drag = 0.001f;
                        ammoMultiplier = 0.2f;
                        status = SFStatusEffects.acidded;
                        statusDuration = 60f * 4f;
                        layer = 98;

                        damage = 6.8f;
                        knockback = 1.5f;
                    }},
                    SFLiquids.blastReagent, new LiquidBulletType(SFLiquids.blastReagent){{
                        lifetime = 30;
                        speed = 8f;
                        puddleSize = 8f;
                        orbSize = 5;
                        drag = 0.001f;
                        ammoMultiplier = 0.2f;
                        status = StatusEffects.blasted;
                        statusDuration = 60f * 4f;
                        layer = 98;

                        damage = 3f;
                        splashDamage = 15;
                        splashDamageRadius = 16;
                        scaledSplashDamage = true;
                        hitEffect = despawnEffect = Fx.blastExplosion;
                        hitColor = SFLiquids.blastReagent.color;
                        knockback = 1.3f;
                    }}
            );
        }};
        tieliu = new LiquidTurret("tieliu") {{
            size = 3;
            health = 2500;
            recoil = 3;
            recoilTime = 30;
            reload = 24;
            shake = 2;
            requirements(Category.turret, with(Items.lead,350, Items.metaglass,180, Items.thorium,100));

            inaccuracy = 2f;
            liquidCapacity = 60;
            shootSound = Sounds.shotgun;
            shootEffect = Fx.shootLiquid;
            range = 230f;
            ammoPerShot = 10;
            ammo(
                    Liquids.slag, new RailBulletType(){{
                        ammoMultiplier = 0.2f;
                        pointEffectSpace = 5;
                        pointEffect = new Effect(16, e -> {
                            color(Liquids.slag.color);

                            randLenVectors(e.id, 5, 1f + e.fin() * 15f, e.rotation, 60f, (x, y) -> {
                                Fill.circle(e.x + x, e.y + y, e.fout() * 2f);
                            });
                        });
                        damage = 45;
                        knockback = 3;
                        lifetime = 30;
                        length = 230;
                        pierceDamageFactor = 0.7f;
                        hitEffect = Fx.hitMeltdown;
                        status = StatusEffects.melting;
                        statusDuration = 600;
                        setDefaults = despawnHit = false;
                        fragBullets = 8;
                        fragRandomSpread = 15;
                        fragBullet = ((LiquidTurret)Blocks.tsunami).ammoTypes.get(Liquids.slag);
                    }}
            );
        }};
        dianji = new ContinuousLiquidTurret("dianji") {{
            size = 3;
            health = 2110;
            recoil = 2;
            recoilTime = 80;
            cooldownTime = 150;
            shake = 2;
            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-side"){{
                    mirror = true;
                    under = true;
                    moveX = 1.5f;
                    moveY = 1.25f;
                    children.add(new RegionPart("-barrel"){{
                        mirror = true;
                        moveY = 5.75f;
                        under = false;
                        heatProgress = PartProgress.heat;
                    }});
                }});
            }};
            shootY = 6;
            minWarmup = 0.8f;
            requirements(Category.turret, with(Items.lead,350, Items.metaglass,180, Items.thorium,100, SFItems.siliSteel,80));
            consumePower(8f);
            liquidCapacity = 120;
            loopSound = Sounds.torch;
            shootSound = Sounds.none;
            smokeEffect = Fx.shootBigSmoke2;

            rotateSpeed = 4;
            range = 180f;
            ammo(
                    Liquids.water, new ContinuousFlameBulletType(4) {{
                        colors = new Color[]{Color.sky, Color.valueOf("68A9B5B0"), Color.valueOf("45AACEF0"), Color.valueOf("20AAE8A0"), Color.valueOf("00ABFF50"),};
                        lightColor = Color.blue;
                        flareColor = Color.sky;
                        flareWidth = 5;
                        length = 180;
                        width = 3.5f;
                        lifetime = 8;
                        knockback = 2.5f;
                        status = SFStatusEffects.negative;
                        statusDuration = 120;
                        pierceCap = 3;
                        shootEffect = new ParticleEffect() {{
                            particles = 4;
                            sizeFrom = 3.3f;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            length = 88;
                            lifetime = 32;
                            colorFrom = Color.sky;
                            colorTo = Color.orange;
                            cone = 13;
                        }};
                        hitEffect = new ParticleEffect() {{
                            particles = 3;
                            lifetime = 20;
                            sizeFrom = 2;
                            interp = Interp.pow3Out;
                            sizeInterp = Interp.pow3In;
                            colorFrom = Color.valueOf("00ABFF");
                            colorTo = Color.valueOf("FDA74B");
                        }};
                        timescaleDamage = true;
                        ammoMultiplier = 0.1f;
                    }},
                    Liquids.slag, new ContinuousLaserBulletType(10){{
                        length = 180f;
                        width = 6;
                        lifetime = 25;
                        pierceCap = 3;
                        hitEffect = Fx.hitMeltdown;
                        hitColor = Pal.meltdownHit;
                        status = SFStatusEffects.postive;
                        statusDuration = 120;
                        knockback = 1;
                        absorbable = false;
                        timescaleDamage = true;
                        ammoMultiplier = 0.2f;
                    }}
            );
        }};
        changqiang = new ItemTurret("changqiang") {{
            size = 3;
            health = 830;
            recoil = 2f;
            recoilTime = 30;
            unitSort = UnitSorts.weakest;
            shootSound = Sounds.missileSmall;
            shake = 2f;
            drawer = new DrawTurret(){{parts.add(new RegionPart("-side"){{
                mirror = true;
                moveX = 1.5f;
            }});}};
            shoot = new ShootAlternate(11){{shots=3;shotDelay=8;}};
            minWarmup = 0.8f;
            shootWarmupSpeed = 0.04f;
            warmupMaintainTime = 45;
            requirements(Category.turret, with(Items.graphite,110, Items.silicon,70, SFItems.waveSteel,100));

            reload = 35;
            rotateSpeed = 5;
            range = 280;
            shootEffect = new MultiEffect(
                    Fx.shootBig2,
                    Fx.shootPyraFlame
            );
            smokeEffect = new ParticleEffect(){{
                particles = 8;
                strokeFrom = 3;
                length = -35;
                cone = 10;
                lifetime = 65;
                colorTo = SFColor.smoke.cpy().a(0.55f);
                layer = 49;
                interp = Interp.pow5Out;
                sizeInterp = Interp.pow10In;
            }};
            ammoUseEffect = Fx.none;
            consumeAmmoOnce = false;
            ammoPerShot = 3;
            maxAmmo = 24;
            ammo(
                    Items.pyratite, new MissileBulletType(10,25,"sfire-mod-missile2"){{
                        lifetime = 30;
                        splashDamage = 45;
                        splashDamageRadius = 36;
                        homingRange = 80;
                        status = StatusEffects.burning;
                        statusDuration = 600;

                        shrinkY = 0;
                        width = 16;
                        height = 40;
                        inaccuracy = 4;
                        hitShake = 2;
                        trailChance = 0.8f;
                        trailColor = backColor = hitColor = Pal.lightPyraFlame;
                        frontColor = SFColor.missileGray;
                        despawnEffect = Fx.flakExplosion;
                        hitEffect = new ExplosionEffect(){{
                            waveLife = 10;
                            waveRad = splashDamageRadius;
                            waveStroke = 8;
                            waveColor = hitColor;
                            sparks = 12;
                            sparkLen = 30;
                            sparkColor = Pal.bulletYellow;
                            sparkRad = splashDamageRadius*1.15f;
                            lifetime = 25;
                            smokes = 8;
                            smokeColor = SFColor.smoke;
                            smokeSize = 8;
                            smokeRad = splashDamageRadius*0.75f;
                        }};
                    }},
                    SFItems.siliSteel, new MissileBulletType(10,13,"sfire-mod-missile2"){{
                        lifetime = 30;
                        splashDamage = 33;
                        splashDamageRadius = 40;
                        homingRange = 80;
                        status = SFStatusEffects.magnStrif;
                        statusDuration = 40;

                        shrinkY = 0;
                        width = 15;
                        height = 40;
                        inaccuracy = 4;
                        hitShake = 2;
                        trailChance = 0.8f;
                        trailColor = backColor = hitColor = SFColor.sisteelDark;
                        frontColor = SFColor.missileGray;
                        despawnEffect = Fx.flakExplosion;
                        hitEffect = new ExplosionEffect(){{
                            waveLife = 10;
                            waveRad = splashDamageRadius;
                            waveStroke = 8;
                            waveColor = hitColor;
                            sparks = 12;
                            sparkLen = 30;
                            sparkColor = Pal.bulletYellow;
                            sparkRad = splashDamageRadius*1.15f;
                            lifetime = 25;
                            smokes = 8;
                            smokeColor = SFColor.smoke;
                            smokeSize = 8;
                            smokeRad = splashDamageRadius*0.75f;
                        }};
                    }},
                    Items.blastCompound, new MissileBulletType(4,20,"sfire-mod-missile1"){{
                        lifetime = 38;
                        drag = -0.03f;
                        splashDamage = 87;
                        splashDamageRadius = 60;
                        rangeChange = 60;
                        homingPower = 0.01f;
                        homingDelay = 33;
                        homingRange = 80;
                        reloadMultiplier = 0.75f;
                        ammoMultiplier = 3;
                        status = StatusEffects.blasted;
                        knockback = 11;

                        shrinkY = 0;
                        width = 14;
                        height = 50;
                        inaccuracy = 4;
                        recoil = 2;
                        hitShake = 5;
                        trailChance = 0;
                        trailLength = 15;
                        trailWidth = 3;
                        trailColor = SFColor.missileGray.cpy().a(0.5f);
                        backColor = hitColor = Pal.blastAmmoBack;
                        frontColor = SFColor.missileGray;
                        despawnEffect = Fx.none;
                        hitSound = Sounds.explosion;
                        hitSoundVolume = 5;
                        hitEffect = new ExplosionEffect(){{
                            waveLife = 10;
                            waveRad = splashDamageRadius;
                            waveStroke = 8;
                            waveColor = hitColor;
                            sparks = 12;
                            sparkLen = 30;
                            sparkStroke = 1.5f;
                            sparkColor = Pal.bulletYellow;
                            sparkRad = splashDamageRadius*1.25f;
                            lifetime = 25;
                            smokes = 12;
                            smokeColor = SFColor.smoke;
                            smokeSize = 8;
                            smokeRad = splashDamageRadius*0.75f;
                        }};
                    }},
                    SFItems.clusBomb, new MissileBulletType(3.6f,20,"sfire-mod-missile1"){{
                        lifetime = 46;
                        drag = -0.03f;
                        splashDamage = 45;
                        splashDamageRadius = 20;
                        rangeChange = 60;
                        homingPower = 0.01f;
                        homingDelay = 33;
                        homingRange = 80;
                        reloadMultiplier = 0.75f;
                        ammoMultiplier = 4;
                        status = StatusEffects.blasted;
                        knockback = 2.6f;

                        shrinkY = 0;
                        width = 14;
                        height = 50;
                        inaccuracy = 4;
                        recoil = 2;
                        hitShake = 3;
                        trailChance = 0;
                        trailLength = 15;
                        trailWidth = 3;
                        trailColor = SFColor.missileGray.cpy().a(0.5f);
                        backColor = hitColor = SFColor.clusRed;
                        frontColor = SFColor.missileGray;
                        despawnEffect = Fx.none;
                        hitSound = Sounds.explosion;
                        hitSoundVolume = 3;
                        hitEffect = new ExplosionEffect(){{
                            waveLife = 10;
                            waveRad = 50;
                            waveStroke = 8;
                            waveColor = hitColor;
                            sparks = 0;
                            lifetime = 35;
                            smokes = 8;
                            smokeColor = SFColor.smoke;
                            smokeSize = 9;
                            smokeRad = 35;
                        }};
                        fragBullets = 5;
                        fragLifeMin = 0.1f;
                        fragBullet = new BasicBulletType(2.5f,10){{
                            splashDamage = 55;
                            splashDamageRadius = 40;
                            lifetime = 15;
                            height = 12;
                            width = 10;
                            knockback = 2.7f;
                            hitShake = 2;
                            shrinkY = 1;
                            hitEffect = Fx.flakExplosionBig;
                            hitSound = Sounds.explosion;
                            hitSoundVolume = 3;
                            backColor = SFColor.clusRedDark;
                            frontColor = SFColor.clusRed;
                            despawnEffect = Fx.none;
                        }};
                    }}
            );
        }};
        longjuan = new ItemTurret("longjuan") {{
            size = 3;
            health = 2250;
            recoil = 3;
            recoilTime = 80;
            shoot = new ShootHelix(6,1);
            shootSound = Sounds.cannon;
            shake = 2.8f;
            drawer = new DrawTurret(){{parts.add(new RegionPart("-side"){{
                mirror = true;
                moveX = 0.25f;
                moveY = -2f;
                moveRot = -13f;
                under = true;
                x = 4;
                moves.add(new PartMove(){{
                    progress = PartProgress.recoil;
                    y = -2;
                    rot = -9;
                }});
                children.add(new RegionPart("-front"){{
                    mirror = true;
                    under = true;
                    moveY = -3;
                    moves.add(new PartMove(){{
                        progress = PartProgress.recoil;
                        y = -5;
                    }});
                }});
            }});}};
            minWarmup = 0.89f;
            shootY = 8;
            requirements(Category.turret, with(Items.plastanium,150, Items.surgeAlloy,110, SFItems.waveSteel,200));

            reload = 160;
            rotateSpeed = 2;
            range = 303;
            coolant = consumeCoolant(0.3f);
            ammoUseEffect = Fx.none;
            ammoPerShot = 8;
            maxAmmo = 32;
            smokeEffect = new ParticleEffect(){{
                particles = 20;
                line = true;
                length = 36;
                lenFrom = 16;
                strokeFrom = 3;
                lifetime = 12;
                cone = 40;
                colorFrom = colorTo = Pal.bulletYellow;
                sizeFrom = 2.3f;
            }};
            ammo(
                    Items.plastanium, new BasicBulletType(3.2f, 32, "mine-bullet"){{
                        splashDamage = 20;
                        splashDamageRadius = 40;
                        reloadMultiplier = 1.25f;
                        lifetime = 96;
                        drag = 0.004f;
                        knockback = 5;
                        ammoMultiplier = 3;
                        pierce = true;
                        pierceCap = 3;

                        width = height = 32;
                        spin = 10;
                        shrinkY = 0;
                        frontColor = hitColor = Pal.plastaniumFront;
                        backColor = Pal.plastaniumBack;
                        trailChance = 1;
                        trailEffect = new ParticleEffect(){{
                            particles = 4;
                            length = 10;
                            baseLength = 3;
                            lifetime = 60;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = colorTo = backColor.cpy().a(0.4f);
                            sizeFrom = 2.3f;
                        }};
                        shootEffect = Fx.shootBigColor;
                        hitShake = 3;
                        hitSound = Sounds.explosion;
                        hitEffect = new ExplosionEffect(){{
                            sparks = 0;
                            smokes = 20;
                            smokeRad = splashDamageRadius * 0.75f;
                            smokeSize = 8;
                            smokeColor = hitColor;
                            lifetime = 55;
                            waveLife = 18;
                            waveStroke = 4.5f;
                            waveRad = splashDamageRadius + 8;
                        }};
                        despawnEffect = Fx.none;

                        bulletInterval = 1.75f;
                        intervalBullets = 2;
                        intervalRandomSpread = 30;
                        intervalBullet = new BasicBulletType(9.2f,10){{
                            lifetime = 8;
                            knockback = 2;
                            frontColor = hitColor = Pal.plastaniumFront;
                            backColor = trailColor = Pal.plastaniumBack;
                            width = height = 16;
                            trailLength = 3;
                            trailWidth = 2;
                            despawnEffect = Fx.none;
                            hitEffect = Fx.hitBulletColor;
                        }};
                    }},
                    Items.surgeAlloy, new BasicBulletType(3, 32, "mine-bullet"){{
                        splashDamage = 60;
                        splashDamageRadius = 58;
                        lifetime = 115;
                        rangeChange = 60;
                        drag = 0.004f;
                        ammoMultiplier = 5;
                        pierce = true;
                        pierceCap = 5;
                        pierceDamageFactor = 0.01f;
                        lightning = 3;
                        lightningLength = 9;
                        lightningDamage = 27;

                        width = height = 35;
                        spin = 10;
                        shrinkY = 0;
                        frontColor = Pal.surgeAmmoFront;
                        backColor = hitColor = Pal.surgeAmmoBack;
                        trailChance = 1;
                        trailEffect = new ParticleEffect(){{
                            particles = 4;
                            length = 10;
                            baseLength = 3;
                            lifetime = 60;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = colorTo = backColor.cpy().a(0.4f);
                            sizeFrom = 2.3f;
                        }};
                        shootEffect = Fx.shootBigColor;
                        hitShake = 3;
                        hitSound = Sounds.explosion;
                        hitEffect = new ExplosionEffect(){{
                            sparks = 0;
                            smokes = 20;
                            smokeRad = splashDamageRadius * 0.75f;
                            smokeSize = 8;
                            smokeColor = hitColor;
                            lifetime = 55;
                            waveLife = 18;
                            waveStroke = 4.5f;
                            waveRad = splashDamageRadius + 8;
                            waveColor = hitColor;
                        }};
                        despawnEffect = Fx.none;

                        bulletInterval = 2.4f;
                        intervalBullets = 2;
                        intervalRandomSpread = 30;
                        intervalBullet = new BasicBulletType(9.2f,10){{
                            lifetime = 8;
                            lightning = 1;
                            lightningLength = 5;
                            lightningLengthRand = 2;
                            lightningDamage = 10;
                            frontColor = hitColor = Pal.surgeAmmoFront;
                            backColor = trailColor = Pal.surgeAmmoBack;
                            width = height = 16;
                            trailLength = 3;
                            trailWidth = 2;
                            despawnEffect = Fx.blastExplosion;
                            hitEffect = Fx.hitBulletColor;
                        }};
                    }},
                    SFItems.siliSteel, new BasicBulletType(2.3f, 21, "mine-bullet"){{
                        splashDamage = 21;
                        splashDamageRadius = 48;
                        lifetime = 150;
                        drag = 0.004f;
                        pierce = true;
                        pierceCap = 3;
                        pierceDamageFactor = 0.01f;
                        status = SFStatusEffects.magnStrif;
                        statusDuration = 60;

                        width = height = 30;
                        spin = 10;
                        shrinkY = 0;
                        frontColor = hitColor = SFColor.sisteelLight;
                        backColor = SFColor.sisteelDark;
                        trailChance = 1;
                        trailEffect = new ParticleEffect(){{
                            particles = 4;
                            length = 10;
                            baseLength = 3;
                            lifetime = 60;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = colorTo = backColor.cpy().a(0.4f);
                            sizeFrom = 2.3f;
                        }};
                        shootEffect = Fx.shootBigColor;
                        hitShake = 3;
                        hitSound = Sounds.explosion;
                        hitEffect = new ExplosionEffect(){{
                            sparks = 0;
                            smokes = 20;
                            smokeRad = splashDamageRadius * 0.75f;
                            smokeSize = 8;
                            smokeColor = hitColor;
                            lifetime = 55;
                            waveLife = 18;
                            waveStroke = 4.5f;
                            waveRad = splashDamageRadius + 8;
                        }};
                        despawnEffect = Fx.none;

                        bulletInterval = 2.4f;
                        intervalBullets = 2;
                        intervalRandomSpread = 30;
                        intervalBullet = new BasicBulletType(8,10){{
                            lifetime = 8;
                            knockback = 1;
                            splashDamage = 10;
                            splashDamageRadius = 18;
                            status = SFStatusEffects.magnStrif;
                            statusDuration = 10;
                            frontColor = hitColor = SFColor.sisteelLight;
                            backColor = trailColor = SFColor.sisteelDark;
                            width = height = 16;
                            trailLength = 3;
                            trailWidth = 2;
                            despawnEffect = Fx.hitBulletColor;
                            hitEffect = Fx.flakExplosion;
                        }};
                    }},
                    SFItems.clusBomb, new BasicBulletType(1.8f, 26, "mine-bullet"){{
                        splashDamage = 96;
                        splashDamageRadius = 55;
                        lifetime = 185;
                        drag = 0.003f;
                        knockback = 5;
                        ammoMultiplier = 5;
                        pierce = true;
                        pierceCap = 3;
                        pierceDamageFactor = 0.01f;
                        status = StatusEffects.blasted;

                        width = height = 32;
                        spin = 10;
                        shrinkY = 0;
                        frontColor = Color.white;
                        backColor = hitColor = SFColor.clusRed;
                        trailChance = 1;
                        trailEffect = new ParticleEffect(){{
                            particles = 4;
                            length = 10;
                            baseLength = 3;
                            lifetime = 60;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = colorTo = backColor.cpy().a(0.4f);
                            sizeFrom = 2.3f;
                        }};
                        shootEffect = Fx.shootBigColor;
                        hitShake = 5;
                        hitSound = Sounds.explosion;
                        hitEffect = new ExplosionEffect(){{
                            sparks = 0;
                            smokes = 20;
                            smokeRad = splashDamageRadius * 0.8f;
                            smokeSize = 8;
                            smokeColor = hitColor;
                            lifetime = 55;
                            waveLife = 12;
                            waveStroke = 4.5f;
                            waveRad = splashDamageRadius + 15;
                        }};
                        despawnEffect = Fx.none;

                        bulletInterval = 3.2f;
                        intervalBullets = 5;
                        intervalRandomSpread = 30;
                        intervalBullet = new BasicBulletType(6,10){{
                            lifetime = 10;
                            splashDamage = 45;
                            splashDamageRadius = 35;
                            status = StatusEffects.blasted;
                            knockback = 2;
                            hitShake = 2;
                            hitSound = Sounds.explosion;
                            frontColor = Color.white;
                            backColor = hitColor = trailColor = SFColor.clusRed;
                            width = height = 16;
                            trailLength = 3;
                            trailWidth = 2;
                            despawnEffect = hitEffect = Fx.blastExplosion;
                        }};
                    }},
                    SFItems.tayrAlloy, new BasicBulletType(1, 110, "mine-bullet"){{
                        splashDamage = 60;
                        splashDamageRadius = 60;
                        lifetime = 60;
                        rangeChange = 100;
                        drag = -0.05f;
                        ammoMultiplier = 6;
                        reloadMultiplier = 0.8f;
                        pierce = true;
                        pierceCap = 10;
                        pierceDamageFactor = 0.01f;
                        lightning = 4;
                        lightningLength = 9;
                        lightningLengthRand = 3;
                        lightningDamage = 30;
                        lightningColor = SFColor.tayrLight;
                        buildingDamageMultiplier = 0.25f;
                        status = SFStatusEffects.scrambled;
                        statusDuration = 60;

                        width = height = 38;
                        spin = 10;
                        shrinkY = 0;
                        frontColor = Color.white;
                        backColor = hitColor = SFColor.tayrLight;
                        trailChance = 1;
                        trailEffect = new ParticleEffect(){{
                            particles = 4;
                            length = 10;
                            baseLength = 3;
                            lifetime = 60;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = colorTo = backColor.cpy().a(0.4f);
                            sizeFrom = 2.3f;
                        }};
                        shootEffect = Fx.shootBigColor;
                        hitShake = 3;
                        hitSound = Sounds.plasmaboom;
                        hitEffect = new ExplosionEffect(){{
                            sparks = 0;
                            smokes = 20;
                            smokeRad = splashDamageRadius * 0.75f;
                            smokeSize = 8;
                            smokeColor = hitColor;
                            lifetime = 55;
                            waveLife = 18;
                            waveStroke = 4.5f;
                            waveRad = splashDamageRadius + 8;
                            waveColor = hitColor;
                        }};
                        despawnEffect = Fx.none;

                        intervalDelay = 33;
                        bulletInterval = 0.7f;
                        intervalBullets = 1;
                        intervalBullet = new LightningBulletType(){{
                            lifetime = 8;
                            lightning = 1;
                            lightningLength = 11;
                            lightningLengthRand = 6;
                            damage = 44;
                            buildingDamageMultiplier = 0.25f;
                            status = SFStatusEffects.scrambled;
                            statusDuration = 20;

                            lightningColor = frontColor = hitColor = SFColor.tayrLight;
                            hitSound = Sounds.spark;
                            despawnEffect = Fx.none;
                            hitEffect = Fx.hitLancer;
                            lightningType = new BulletType(0.0001f, 0f){{
                                lightning = 3;
                                lightningDamage = 28;
                                lightningLength = 0;
                                lightningLengthRand = 4;
                                lightningColor = hitColor = SFColor.tayrLight;

                                instantDisappear = true;
                                hitEffect = Fx.hitLancer;
                                despawnEffect = Fx.none;
                                status = StatusEffects.shocked;
                                hittable = false;
                                lightColor = Color.white;
                                buildingDamageMultiplier = 0.25f;
                            }};
                        }};
                    }}
            );
        }};
        manyou = new PowerTurret("manyou") {{
            size = 3;
            health = 1450;
            recoil = 3;
            recoilTime = 20;
            shootSound = Sounds.laser;
            shake = 2.5f;
            inaccuracy = 3;
            requirements(Category.turret, with(Items.silicon,220, Items.surgeAlloy,110, SFItems.siliSteel,130));

            reload = 45;
            rotateSpeed = 6;
            range = 276;
            consumePower(11f);
            coolant = consumeCoolant(0.3f);
            shootType = new BasicBulletType(8, 80) {{
                lifetime = 34.5f;
                splashDamage = 85;
                splashDamageRadius = 30;
                ammoMultiplier = 1;
                status = SFStatusEffects.breakdown;
                statusDuration = 60;

                frontColor = Color.white;
                trailColor = backColor = SFColor.energyYellow;
                trailWidth = 1.2f;
                trailLength = 16;
                width = 6;
                height = 35;
                hitColor = Pal.lightPyraFlame;
                hitSound = Sounds.plasmaboom;
                shootEffect = Fx.shootBigColor;
                smokeEffect = Fx.bigShockwave;
                hitEffect = new ExplosionEffect() {{
                    sparks = 12;
                    sparkLen = 15;
                    sparkStroke = 3;
                    sparkRad = 53;
                    sparkColor = SFColor.energyYellow;
                    smokes = 5;
                    smokeSize = 9;
                    smokeRad = 48;
                    smokeColor = SFColor.energyYellow.cpy().a(0.4f);
                    lifetime = 35;
                    waveRad = 44;
                    waveLife = 25;
                    waveStroke = 3;
                    waveColor = SFColor.energyYellow;
                }};
                despawnEffect = Fx.none;
            }};
        }};
        chuanyun = new ItemTurret("chuanyun") {{
            size = 3;
            health = 3400;
            armor = 4;
            recoil = 2.88f;
            recoilTime = 30;
            shootSound = Sounds.artillery;
            shake = 3.5f;
            drawer = new DrawTurret(){{parts.add(new RegionPart("-barrel"){{
                mirror = false;
                under = true;
                progress = PartProgress.recoil;
                moveY = -4;
            }});}};
            requirements(Category.turret, with(Items.graphite,90, SFItems.waveSteel,100, SFItems.fermium,80));

            reload = 80;
            rotateSpeed = 5;
            range = 280;
            coolant = consumeCoolant(0.3f);
            shootEffect = Fx.shootBig;
            smokeEffect = Fx.shootBigSmoke;
            ammoUseEffect = Fx.casing3Double;
            ammoPerShot = 6;
            maxAmmo = 150;
            liquidCapacity = 40;
            ammo(
                    Items.thorium, new BasicBulletType(17.5f, 144){{
                        lifetime = 16;
                        pierce = true;
                        pierceArmor = true;
                        pierceCap = 3;

                        width = 15;
                        height = 25;
                        frontColor = hitColor = Pal.thoriumAmmoFront;
                        backColor = Pal.thoriumAmmoBack;
                        trailColor = backColor.cpy().a(0.5f);
                        trailWidth = 1.8f;
                        trailLength = 6;
                        trailChance = 0.8f;
                        trailRotation = true;
                        trailEffect = new ParticleEffect(){{
                            sizeInterp = Interp.pow3In;
                            particles = 3;
                            line = true;
                            strokeFrom = 1;
                            lenFrom = 8;
                            length = 15;
                            lifetime = 15;
                            colorFrom = Color.white;
                            colorTo = backColor;
                            cone = 10;
                        }};
                        shootEffect = Fx.bigShockwave;
                        hitEffect = new ParticleEffect(){{
                            particles = 13;
                            line = true;
                            strokeFrom = 4;
                            lenFrom = 10;
                            length = 70;
                            lifetime = 10;
                            colorFrom = Pal.bulletYellow;
                            colorTo = Color.white;
                            cone = 60;
                        }};
                        despawnEffect = Fx.hitBulletColor;
                    }},
                    SFItems.rubidium, new BasicBulletType(17.5f, 72){{
                        lifetime = 16;
                        pierce = true;
                        pierceArmor = true;
                        pierceCap = 3;
                        status = SFStatusEffects.magnStrif;
                        statusDuration = 60;

                        width = 15;
                        height = 25;
                        frontColor = Color.white;
                        backColor = hitColor = SFColor.rubidium;
                        trailColor = backColor.cpy().a(0.5f);
                        trailWidth = 2;
                        trailLength = 6;
                        trailChance = 0.8f;
                        trailRotation = true;
                        trailEffect = new ParticleEffect(){{
                            sizeInterp = Interp.pow3In;
                            particles = 3;
                            line = true;
                            strokeFrom = 1;
                            lenFrom = 8;
                            length = 15;
                            lifetime = 15;
                            colorFrom = Color.white;
                            colorTo = backColor;
                            cone = 10;
                        }};
                        shootEffect = Fx.bigShockwave;
                        hitEffect = new ParticleEffect(){{
                            particles = 13;
                            line = true;
                            strokeFrom = 4;
                            lenFrom = 10;
                            length = 100;
                            lifetime = 10;
                            colorFrom = Pal.bulletYellow;
                            colorTo = Color.white;
                            cone = 60;
                        }};
                        despawnEffect = Fx.hitBulletColor;
                    }},
                    SFItems.fermium, new BasicBulletType(20, 210){{
                        rangeChange = 20;
                        lifetime = 15;
                        pierce = true;
                        pierceArmor = true;
                        pierceCap = 8;
                        status = StatusEffects.melting;
                        statusDuration = 60;
                        ammoMultiplier = 3;

                        width = 15;
                        height = 26;
                        frontColor = Color.white;
                        backColor = hitColor = SFColor.ferium;
                        trailColor = backColor.cpy().a(0.5f);
                        trailWidth = 2;
                        trailLength = 6;
                        trailChance = 0.8f;
                        trailRotation = true;
                        trailEffect = new ParticleEffect(){{
                            sizeInterp = Interp.pow3In;
                            particles = 3;
                            line = true;
                            strokeFrom = 1;
                            lenFrom = 8;
                            length = 15;
                            lifetime = 15;
                            colorFrom = Color.white;
                            colorTo = backColor;
                            cone = 10;
                        }};
                        shootEffect = Fx.bigShockwave;
                        hitEffect = new ParticleEffect(){{
                            particles = 13;
                            line = true;
                            strokeFrom = 4;
                            lenFrom = 10;
                            length = 100;
                            lifetime = 10;
                            colorFrom = Pal.bulletYellow;
                            colorTo = Color.white;
                            cone = 60;
                        }};
                        despawnEffect = Fx.hitBulletColor;
                    }},
                    SFItems.chromium, new BasicBulletType(20, 144){{
                        rangeChange = 20;
                        lifetime = 15;
                        pierce = true;
                        pierceArmor = true;
                        pierceCap = 4;
                        status = SFStatusEffects.inBreak;
                        statusDuration = 60;
                        knockback = 18;
                        ammoMultiplier = 3;

                        width = 15;
                        height = 26;
                        frontColor = SFColor.chromiumLight;
                        backColor = hitColor = SFColor.chromiumDark;
                        trailColor = backColor.cpy().a(0.5f);
                        trailWidth = 2;
                        trailLength = 6;
                        trailChance = 0.8f;
                        trailRotation = true;
                        trailEffect = new ParticleEffect(){{
                            sizeInterp = Interp.pow3In;
                            particles = 3;
                            line = true;
                            strokeFrom = 1;
                            lenFrom = 8;
                            length = 15;
                            lifetime = 15;
                            colorFrom = Color.white;
                            colorTo = backColor;
                            cone = 10;
                        }};
                        shootEffect = Fx.bigShockwave;
                        hitEffect = new ParticleEffect(){{
                            particles = 13;
                            line = true;
                            strokeFrom = 4;
                            lenFrom = 10;
                            length = 100;
                            lifetime = 10;
                            colorFrom = Pal.bulletYellow;
                            colorTo = Color.white;
                            cone = 60;
                        }};
                        despawnEffect = Fx.hitBulletColor;
                    }}
            );
        }};
        cijian = new ItemTurret("cijian") {{
            size = 3;
            health = 3250;
            armor = 4;
            recoil = 3;
            recoilTime = 30;
            shootSound = Sounds.shotgun;
            shake = 2;
            requirements(Category.turret, with(Items.graphite,80, SFItems.siliSteel,90, SFItems.fermium, 220));

            reload = 28;
            rotateSpeed = 8;
            range = 160;
            coolant = consumeCoolant(0.3f);
            ammoUseEffect = Fx.none;
            ammoPerShot = 6;
            maxAmmo = 48;
            inaccuracy = 5;
            shoot.shots = 6;
            shoot.shotDelay = 4;
            shootY = 10;
            ammo(
                    Items.thorium, new ShrapnelBulletType(){{
                        rangeChange = 10;
                        length = 180;
                        width = 20;
                        damage = 115f;
                        toColor = Pal.thoriumPink;
                        shootEffect = smokeEffect = Fx.thoriumShoot;
                    }},
                    SFItems.strontium, new ShrapnelBulletType(){{
                        length = 170;
                        width = 20;
                        damage = 97f;
                        ammoMultiplier = 3f;
                        toColor = SFColor.strontiumLight;
                        shootEffect = smokeEffect = new Effect(12f, e -> {
                            color(Color.white, SFColor.strontiumLight, e.fin());
                            stroke(e.fout() * 1.2f + 0.5f);

                            randLenVectors(e.id, 7, 25f * e.finpow(), e.rotation, 50f, (x, y) -> {
                                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
                            });
                        });
                        status = StatusEffects.burning;
                        statusDuration = 600;
                    }},
                    SFItems.fermium, new ShrapnelBulletType(){{
                        reloadMultiplier = 0.95f;
                        rangeChange = 20;
                        length = 190;
                        width = 20;
                        damage = 165f;
                        ammoMultiplier = 5f;
                        toColor = SFColor.ferium;
                        shootEffect = smokeEffect = new Effect(12f, e -> {
                            color(Color.white, SFColor.ferium, e.fin());
                            stroke(e.fout() * 1.2f + 0.5f);

                            randLenVectors(e.id, 7, 25f * e.finpow(), e.rotation, 50f, (x, y) -> {
                                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
                            });
                        });
                        status = StatusEffects.melting;
                        statusDuration = 160;
                    }}
            );
        }};
        //chuifa
    //4*4//
        fangtian = new ItemTurret("fangtian") {{
            size = 4;
            health = 2900;
            recoil = 4f;
            recoilTime = 65;
            shootSound = SFSounds.missileX;
            shake = 3f;
            inaccuracy = 7;
            xRand = 4;
            shoot = new ShootAlternate(18);
            shoot.shots = 2;
            shoot.shotDelay = 12;
            requirements(Category.turret, with(Items.lead,560, Items.graphite,200, Items.silicon,220, SFItems.waveSteel,200));
            consumePower(7);
            targetGround = false;

            reload = 112;
            rotateSpeed = 6;
            range = 640;
            coolantMultiplier = 1.5f;
            liquidCapacity = 60;
            coolant = consumeCoolant(0.5f);
            shootCone = 180;
            shootEffect = Fx.shootTitan;
            smokeEffect = Fx.shootPyraFlame;
            ammoPerShot = 2;
            ammoUseEffect = new ParticleEffect(){{
                particles = 9;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow5In;
                sizeFrom = 5.5f;
                length = 40;
                lifetime = 65;
                colorFrom = colorTo = SFColor.smoke.cpy().a(0.5f);
                layer = 49;
            }};
            ammo(
                    SFItems.siliSteel, new FlakBulletType(6,20){{
                        lifetime = 135f;
                        splashDamageRadius = 45;
                        splashDamage = 45;
                        scaledSplashDamage = true;
                        status = SFStatusEffects.magnStrif;
                        statusDuration = 60;
                        homingRange = 80;
                        homingDelay = 15;
                        homingPower = 0.08f;

                        width = 14;
                        height = 53;
                        shrinkY = 0;
                        sprite = "sfire-mod-missile1";
                        frontColor = SFColor.missileGray;
                        backColor = SFColor.sisteelDark;
                        trailColor = backColor.cpy().a(0.6f);
                        trailWidth = 2;
                        trailLength = 40;

                        hitShake = 4;
                        hitSound = Sounds.explosion;
                        hitSoundVolume = 4;
                        hitEffect = new Effect(15,e -> {
                            color(backColor);
                            stroke(e.fout() * 2f + 0.2f);
                            Lines.circle(e.x, e.y, e.fin() * 30f);

                            randLenVectors(e.id, 15, 5f + e.fin() * 50f, e.rotation, 360f, (x, y) -> {
                                Fill.square(e.x + x, e.y + y, e.fout(Interp.pow5Out)*3.5f, 45f);
                            });
                        });
                        despawnEffect = Fx.flakExplosionBig;
                    }},
                    Items.blastCompound, new FlakBulletType(6, 30){{
                        lifetime = 135f;
                        splashDamageRadius = 64;
                        splashDamage = 80*1.5f;
                        scaledSplashDamage = true;
                        status = StatusEffects.blasted;
                        homingRange = 80;
                        homingDelay = 15;
                        homingPower = 0.08f;
                        ammoMultiplier = 4;
                        reloadMultiplier = 1.7f;

                        width = 15;
                        height = 55;
                        shrinkY = 0;
                        sprite = "sfire-mod-missile1";
                        frontColor = SFColor.missileGray;
                        backColor = Pal.blastAmmoBack;
                        trailColor = backColor.cpy().a(0.6f);
                        trailWidth = 2;
                        trailLength = 40;

                        hitShake = 5;
                        hitSound = Sounds.explosionbig;
                        hitSoundVolume = 2;
                        hitEffect = new MultiEffect(
                                new ExplosionEffect(){{
                                    sparks = 0;
                                    smokes = 18;
                                    smokeSize = 10;
                                    smokeRad = splashDamageRadius*0.55f;
                                    lifetime = 35f;
                                    smokeColor = SFColor.smoke;
                                    waveLife = 15;
                                    waveRad = splashDamageRadius;
                                    waveStroke = 8;
                                    waveColor = Pal.bulletYellowBack;
                                }},
                                new ParticleEffect(){{
                                    line = true;
                                    lifetime = 22;
                                    lenFrom = 9;
                                    particles = 32;
                                    baseLength = 20;
                                    length = splashDamageRadius*1.25f;
                                    colorTo = Pal.bulletYellowBack;
                                }}
                        );
                        despawnEffect = Fx.flakExplosionBig;
                    }},
                    Items.surgeAlloy, new FlakBulletType(7, 60){{
                        lifetime = 115f;
                        splashDamageRadius = 40;
                        splashDamage = 75*1.5f;
                        scaledSplashDamage = true;
                        lightningColor = Pal.surgeAmmoBack;
                        lightningLength = 12;
                        lightningLengthRand = 2;
                        lightning = 3;
                        lightningDamage = 23;
                        lightningType = new BulletType(0.0001f, 0f){{
                            lifetime = Fx.lightning.lifetime;
                            hitEffect = Fx.hitLancer;
                            despawnEffect = Fx.none;
                            status = StatusEffects.shocked;
                            statusDuration = 10f;
                            hittable = false;
                            lightColor = Color.yellow;
                            collidesGround = false;
                            hitSound = Sounds.spark;
                        }};
                        homingRange = 80;
                        homingDelay = 15;
                        homingPower = 0.09f;
                        ammoMultiplier = 6;

                        width = 16;
                        height = 56;
                        shrinkY = 0;
                        sprite = "sfire-mod-missile2";
                        frontColor = SFColor.missileGray;
                        backColor = hitColor = Pal.surgeAmmoBack;
                        trailColor = backColor.cpy().a(0.6f);
                        trailWidth = 2;
                        trailLength = 40;

                        hitShake = 5;
                        hitSound = Sounds.explosion;
                        hitSoundVolume = 5;
                        hitEffect = new MultiEffect(
                                new ExplosionEffect(){{
                                    sparks = 0;
                                    smokes = 10;
                                    smokeSize = 10;
                                    smokeRad = splashDamageRadius*0.55f;
                                    lifetime = 35f;
                                    smokeColor = SFColor.smoke;
                                    waveLife = 15;
                                    waveRad = splashDamageRadius;
                                    waveStroke = 8;
                                    waveColor = Pal.surgeAmmoBack;
                                }},
                                new Effect(35f, 100f, e -> {
                                    color(hitColor);

                                    Mathf.rand.setSeed(e.id);
                                    for(int i = 0; i < 3; i++){
                                        float angle = Mathf.random(360f);

                                        float lr = Mathf.range(35,45);
                                        Drawf.tri(e.x, e.y, e.foutpow() * 16f, lr, angle);
                                    }
                                })
                        );
                        despawnEffect = Fx.flakExplosionBig;
                    }},
                    SFItems.fermium, new BasicBulletType(10, 110*1.5f){{
                        inaccuracy = -7;
                        rangeChange = 80;
                        collidesGround = false;
                        lifetime = 88f;
                        status = StatusEffects.melting;
                        statusDuration = 180;
                        homingRange = 80;
                        homingDelay = 15;
                        homingPower = 0.12f;
                        pierceArmor = true;
                        shieldDamageMultiplier = 2.5f;
                        ammoMultiplier = 1;

                        width = 15;
                        height = 53;
                        shrinkY = 0;
                        sprite = "sfire-mod-missile2";
                        frontColor = SFColor.missileGray;
                        backColor = SFColor.ferium;
                        trailColor = Color.white.cpy().a(0.44f);
                        trailWidth = 2;
                        trailLength = 40;
                        trailInterval = 4f;
                        trailEffect = new WaveEffect(){{
                            sizeTo = 15;
                            strokeFrom = 3;
                            startDelay = 3;
                            lifetime = 25;
                            colorFrom = colorTo = trailColor;
                        }};

                        hitShake = 2;
                        hitSound = Sounds.explosion;
                        hitSoundVolume = 5;
                        hitEffect = new MultiEffect(
                                new ExplosionEffect(){{
                                    sparks = 0;
                                    smokes = 9;
                                    smokeSize = 3;
                                    smokeRad = 35f;
                                    lifetime = 22f;
                                    smokeColor = SFColor.smoke;
                                    waveLife = 10;
                                    waveRad = 15;
                                    waveStroke = 8;
                                    waveColor = Pal.bulletYellowBack;
                                }},
                                new ParticleEffect(){{
                                    line = true;
                                    sizeInterp = Interp.slope;
                                    lifetime = 12;
                                    lenFrom = strokeFrom = 0;
                                    strokeTo = 2;
                                    lenTo = 15;
                                    particles = 32;
                                    baseLength = 20;
                                    length = 50;
                                    colorTo = Pal.bulletYellowBack;
                                    cone = 40;
                                }}
                        );
                        despawnEffect = Fx.flakExplosionBig;
                    }}
            );
        }};
        leizhenyu = new ItemTurret("leizhenyu") {{
            size = 4;
            health = 2900;
            recoil = 4f;
            recoilTime = 60;
            shootSound = Sounds.missileSmall;
            shake = 3f;
            inaccuracy = 10;
            shootY = -3;
            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        2.5f, 15, 0,
                        -2.5f, 15, 0,
                        6, 14.25f, 0,
                        -6, 14.25f, 0,
                        9.5f, 13.5f, 0,
                        -9.5f, 13.5f, 0
                };
                shots = 3;
                shotDelay = 5;
            }};
            requirements(Category.turret, with(Items.copper,560, Items.graphite,350, Items.plastanium,260, SFItems.waveSteel,220));
            targetAir = false;

            reload = 45f;
            rotateSpeed = 3.5f;
            range = 600;
            minRange = 80;
            liquidCapacity = 60;

            coolant = consumeCoolant(0.5f);
            shootEffect = Fx.shootBig;
            ammoPerShot = 4;
            ammoUseEffect = new ParticleEffect(){{
                particles = 9;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow5In;
                sizeFrom = 5.5f;
                length = 40;
                lifetime = 65;
                colorFrom = colorTo = SFColor.smoke.cpy().a(0.5f);
                layer = 49;
            }};
            velocityRnd = 0.05f;
            ammo(
                    Items.graphite, new ArtilleryBulletType(5,10,"sfire-mod-missile1"){{
                        splashDamage = 33f;
                        splashDamageRadius = 32f;
                        scaledSplashDamage = true;
                        lifetime = 180;

                        homingDelay = homingRange = 40;
                        homingPower = 0.08f;

                        height = 60;
                        width = 12;
                        backColor = trailColor = hitColor =  Pal.graphiteAmmoBack;
                        frontColor = SFColor.missileGray;
                        smokeEffect = Fx.shootSmallFlame;
                        hitEffect = Fx.flakExplosion;
                        hitShake = 3;
                        hitSoundVolume = 3;
                    }},
                    Items.pyratite, new ArtilleryBulletType(5,10,"sfire-mod-missile1"){{
                        splashDamage = 40f;
                        splashDamageRadius = 32f;
                        scaledSplashDamage = true;
                        lifetime = 180;
                        ammoMultiplier = 4;
                        status = StatusEffects.burning;
                        statusDuration = 360;
                        makeFire = true;

                        homingDelay = homingRange = 40;
                        homingPower = 0.08f;

                        height = 60;
                        width = 12;
                        backColor = trailColor = hitColor =  Pal.lightPyraFlame;
                        frontColor = SFColor.missileGray;
                        smokeEffect = Fx.shootSmallFlame;
                        hitEffect = Fx.flakExplosion;
                        hitShake = 3;
                        hitSoundVolume = 3;
                    }},
                    Items.blastCompound, new ArtilleryBulletType(5,10,"sfire-mod-missile1"){{
                        splashDamage = 50f;
                        splashDamageRadius = 45f;
                        scaledSplashDamage = true;
                        lifetime = 180;
                        ammoMultiplier = 5;
                        status = StatusEffects.blasted;

                        homingDelay = homingRange = 40;
                        homingPower = 0.08f;

                        height = 65;
                        width = 14;
                        backColor = trailColor = hitColor = Pal.blastAmmoBack;
                        frontColor = SFColor.missileGray;
                        smokeEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.flakExplosionBig;
                        hitShake = 3;
                        hitSoundVolume = 3;
                    }},
                    Items.surgeAlloy, new ArtilleryBulletType(5,10,"sfire-mod-missile1"){{
                        splashDamage = 75f;
                        splashDamageRadius = 40f;
                        lifetime = 180;
                        ammoMultiplier = 6;
                        lightning = 2;
                        lightningDamage = 18f;
                        lightningLength = 8;
                        lightningType = new BulletType(0.0001f, 0f){{
                            lifetime = Fx.lightning.lifetime;
                            hitEffect = Fx.hitLancer;
                            despawnEffect = Fx.none;
                            status = StatusEffects.shocked;
                            statusDuration = 10f;
                            hittable = false;
                            lightColor = Color.yellow;
                            collidesAir = false;
                            hitSound = Sounds.spark;
                        }};

                        homingDelay = homingRange = 40;
                        homingPower = 0.08f;

                        height = 65;
                        width = 15;
                        backColor = trailColor = hitColor = Pal.surgeAmmoBack;
                        frontColor = SFColor.missileGray;
                        smokeEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.flakExplosion;
                        hitShake = 3;
                        hitSoundVolume = 3;
                    }},
                    SFItems.siliSteel, new ArtilleryBulletType(5,10,"sfire-mod-missile1"){{
                        splashDamage = 28f;
                        splashDamageRadius = 35f;
                        scaledSplashDamage = true;
                        lifetime = 180;
                        ammoMultiplier = 3;
                        status = SFStatusEffects.magnStrif;
                        statusDuration = 60;

                        homingDelay = homingRange = 40;
                        homingPower = 0.08f;

                        height = 60;
                        width = 10;
                        backColor = trailColor = hitColor = SFColor.sisteelDark;
                        frontColor = SFColor.missileGray;
                        smokeEffect = Fx.shootSmallFlame;
                        hitEffect = Fx.flakExplosion;
                        hitShake = 3;
                        hitSoundVolume = 3;
                    }},
                    SFItems.clusBomb, new ArtilleryBulletType(5,10,"sfire-mod-missile1"){{
                        splashDamage = 45f;
                        splashDamageRadius = 45f;
                        scaledSplashDamage = true;
                        lifetime = 180;
                        ammoMultiplier = 6;
                        status = StatusEffects.burning;
                        statusDuration = 60;
                        makeFire = true;

                        homingDelay = 60;
                        homingRange = 40;
                        homingPower = 0.02f;

                        height = 65;
                        width = 15;
                        backColor = trailColor = hitColor = SFColor.clusRedDark;
                        frontColor = SFColor.missileGray;
                        smokeEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.flakExplosionBig;
                        hitShake = 3;
                        hitSoundVolume = 3;
                        fragLifeMin = 0.1f;
                        fragBullets = 5;
                        fragBullet = new BasicBulletType(2.5f,10){{
                            splashDamage = 45f;
                            splashDamageRadius = 50f;
                            scaledSplashDamage = true;
                            status = StatusEffects.blasted;
                            lifetime = 20;
                            drag = 0.005f;

                            height = width = 8;
                            shrinkY = 1;
                            backColor = trailColor = hitColor = SFColor.clusRedDark;
                            frontColor = SFColor.clusRed;
                            despawnEffect = hitEffect = Fx.blastExplosion;
                            hitSoundVolume = 3;
                        }};
                    }}
            );

        }};
        qingning = new PowerTurret("qingning") {{
            size = 4;
            health = 1450;
            accurateDelay = false;
            shoot = new ShootMulti(
                    new ShootHelix(15,2),
                    new ShootHelix(7.5f,4)
            );
            recoil = 2;
            recoilTime = 70;
            shootSound = Sounds.plasmadrop;
            shake = 2.5f;
            shootWarmupSpeed = 0.02f;
            warmupMaintainTime = 80;
            minWarmup = 0.9f;
            shootY = 10;
            drawer = new DrawTurret(){{parts.addAll(
                    new RegionPart("-front"){{
                        mirror = true;
                        color = Color.white;
                        colorTo = Color.white.cpy().a(0);
                        moveX = moveY = -1.25f;
                        moves.add(new PartMove(PartProgress.recoil,0,-2,-15));
                    }},
                    new RegionPart("-side"){{
                        mirror = true;
                        heatColor = SFColor.tayrLight;
                        under = true;
                        moveX = -1f;
                        moveRot = 6;
                        moves.add(new PartMove(PartProgress.recoil,0,-2,-10));
                    }},
                    new RegionPart("-top"){{
                        heatColor = SFColor.tayrLight;
                        moveY = -1.5f;
                    }},
                    new ShapePart(){{
                        y = 9;
                        circle = hollow = true;
                        color = SFColor.tayrLight;
                        radius = 18;
                        radiusTo = 0;
                        stroke = 0.9f;
                        strokeTo = 1;
                        layer = 110;
                    }},
                    new HaloPart(){{
                        y = 9;
                        shapeRotation = 180;
                        sides = shapes = 3;
                        color = SFColor.tayrLight;
                        layer = 110;
                        tri = true;
                        radius = 3;
                        radiusTo = 2.2f;
                        triLength = 6;
                        triLengthTo = 22;
                        haloRadius = 18;
                        haloRadiusTo = 0;
                        haloRotateSpeed = -1.2f;
                    }}
            );}};
            requirements(Category.turret, with(Items.lead,1150, Items.titanium,400, SFItems.siliSteel,300, SFItems.nanoCore,350, SFItems.tayrAlloy,400 ));

            shake = 8;
            inaccuracy = 16;
            velocityRnd = 0.2f;
            reload = 186;
            rotateSpeed = 3;
            range = 350;
            consumePower(22f);
            coolantMultiplier = 1.5f;
            coolant = consumeCoolant(1f);
            shootType = new BasicBulletType(2,26,"circle-bullet"){{
                lifetime = 90;
                drag = -0.013f;
                splashDamage = 30;
                splashDamageRadius = 35;
                status = SFStatusEffects.scrambled;
                statusDuration = 66;
                ammoMultiplier = 1;

                homingDelay = 33;
                homingPower = 0.04f;
                homingRange = 60;

                shrinkY = 0;
                width = height = 10;
                frontColor = Color.white;
                hitColor = backColor = trailColor = SFColor.tayrLight;
                trailLength = 20;
                trailWidth = 4;
                parts.add(new HaloPart(){{
                    sides = shapes = 3;
                    color = SFColor.tayrLight;
                    layer = 110;
                    tri = true;
                    radius = 6;
                    radiusTo = 3f;
                    triLength = 35;
                    triLengthTo = 0;
                    haloRadius = haloRadiusTo = 0;
                    haloRotateSpeed = -4.33f;
                }});
                hitSound = Sounds.laser;
                despawnEffect = new ParticleEffect(){{
                    sizeTo = 35f;
                    length = baseLength = 0;
                    lifetime = 25;
                    sizeInterp = Interp.pow5Out;
                    colorFrom = SFColor.tayrLight.cpy().a(0.5f);
                    colorTo = SFColor.tayrLight.cpy().a(0f);
                }};
                hitEffect = new MultiEffect(
                        new WaveEffect() {{
                            lifetime = 25;
                            sizeTo = 40f;
                            strokeFrom = 4;
                            colorTo = hitColor;
                        }},
                        new Effect(35f, 160f, e -> {
                            color(hitColor);

                            Mathf.rand.setSeed(e.id);
                            for (int i = 0; i < 5; i++) {
                                float lr = 20 + Mathf.range(25);
                                Drawf.tri(e.x, e.y, e.fout() * 10f, e.fin(Interp.pow5Out) * lr, 72 * i);
                            }
                        }));
                fragBullets = 3;
                fragRandomSpread = 180f;
                fragVelocityMin = 0.5f;
                fragBullet = new BasicBulletType(7.7f,11,"circle-bullet"){{
                    lifetime = 45;
                    status = SFStatusEffects.scrambled;
                    statusDuration = 15;

                    homingDelay = 10;
                    homingPower = 0.1f;
                    homingRange = 80;

                    shrinkX = shrinkY = 1f;
                    drag = 0.02f;
                    width = height = 4;
                    frontColor = hitColor = backColor = trailColor = SFColor.tayrLight;
                    trailEffect = Fx.none;
                    trailLength = 10;
                    trailWidth = 1.5f;
                    hitEffect = Fx.hitLiquid;
                    despawnEffect = Fx.hitBulletColor;
                    absorbable = false;
                    pierce = true;
                }};

            }};
        }};
        chongchao = new ItemTurret("chongchao") {{
            size = 4;
            health = 3000;
            recoil = 4f;
            recoilTime = 125;
            cooldownTime = 40;
            unitSort = UnitSorts.weakest;
            shootSound = Sounds.release;
            soundPitchMax = soundPitchMin = 2;
            shake = 5f;
            warmupMaintainTime = 60;
            targetHealing = true;
            shoot = new ShootHelix(3,1);
            shootY = 10;
            
            requirements(Category.turret, with(Items.copper,950, Items.plastanium,460, Items.silicon,370, SFItems.tayrAlloy,220, SFItems.nanoCore,500));
            consumePower(65);
            consumeLiquid(SFLiquids.nanoFluid,1);
            liquidCapacity = 360;

            reload = 175;
            rotateSpeed = 3;
            range = 432;
            ammoPerShot = 8;
            ammo(SFItems.nanoCore, new EmpBulletType(){{
                ammoMultiplier = 1;
                scaleLife = true;
                absorbable = reflectable = false;
                timeIncrease = 1;
                powerDamageScl = 3;
                powerSclDecrease = 0.75f;
                unitDamageScl = 1.25f;
                radius = 160;

                width = 18;
                height = 24;
                frontColor = Color.white;
                backColor = hitColor = trailColor = Pal.heal;
                trailLength = 46;
                trailWidth = 4;
                sprite = "missile-large";
                hitSize = 8;
                shrinkY = shrinkX = 0.1f;
                healAmount = 280;
                homingDelay = 40;
                homingPower = 0.04f;
                homingRange = 80;

                speed = 3.35f;
                damage = 50;
                splashDamageRadius = 80;
                splashDamage = 20;
                status = SFStatusEffects.disRepair;
                statusDuration = 120;

                hitSound = Sounds.plasmaboom;
                hitShake = 4;
                float rad = splashDamageRadius;
                hitEffect = new Effect(60,100,e ->{
                    e.scaled(7,b->{
                        color(Pal.heal, b.fout());
                        Fill.circle(e.x, e.y, rad);
                    });

                    color(Pal.heal);
                    stroke(e.fout() * 3f);
                    Lines.circle(e.x, e.y, rad);

                    int points = 16;
                    float offset = Mathf.randomSeed(e.id, 360f);
                    for(int i = 0; i < points; i++){
                        float angle = i * 360f / points + offset;
                        Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle+180f);
                    }

                    Drawf.light(e.x, e.y, rad * 1.6f, Pal.heal, e.fout());
                });
                despawnEffect = new ParticleEffect(){{
                    particles = 43;
                    sizeFrom = 2f;
                    length = 160;
                    lifetime = 100;
                    sizeInterp = sizeInterp.bounce;
                    interp = Interp.fastSlow;
                    colorFrom = colorTo = hitColor;
                }};
                shootEffect = new Effect(10, e -> {
                    color(Pal.heal, e.color, e.fin());
                    float w = 1.3f + 10 * e.fout();
                    Drawf.tri(e.x, e.y, w, 45f * e.fout(), e.rotation);
                    Drawf.tri(e.x, e.y, w, 8f * e.fout(), e.rotation + 180f);
                });
                smokeEffect = new Effect(75f, e -> {
                    color(Color.white, Pal.heal, Color.gray, e.fin());

                    randLenVectors(e.id, 7, e.finpow() * 23f, e.rotation, 45f, (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 5f + 0.2f);
                    });
                });
                fragBullets = 18;
                fragLifeMin = 0.3f;
                bulletInterval = 8;
                intervalBullets = 2;
                intervalDelay = 40;
                intervalBullet = fragBullet = new BasicBulletType(10,8,"circle-bullet"){{
                    lifetime = 45;
                    drag = 0.05f;
                    width = height = 4;
                    frontColor = Color.white;
                    backColor = hitColor = trailColor = Pal.heal;
                    trailLength = 20;
                    trailWidth = 2;
                    shrinkY = 0;
                    absorbable = reflectable = false;

                    status = SFStatusEffects.disRepair;
                    statusDuration = 20;
                    healPercent = 2;
                    homingDelay = 10;
                    homingPower = 0.08f;
                    homingRange = 80;
                    hitEffect = new ExplosionEffect(){{
                        smokes = 0;
                        sparks = 8;
                        sparkColor = waveColor = hitColor;
                        sparkLen = 16;
                        sparkRad = 26;
                        waveStroke = 2;
                        waveRad = 22;
                        waveLife = lifetime = 20;
                    }};
                    despawnEffect = Fx.hitBulletColor;
                }};
            }});
        }};
    //5*5//
        relang = new PowerTurret("relang") {{
            size = 5;
            health = 4800;
            recoil = 4;
            cooldownTime = 290;
            recoilTime = 120;
            shootSound = Sounds.laser;
            moveWhileCharging = reloadWhileCharging = false;
            shake = 5f;
            inaccuracy = 2;
            shoot.firstShotDelay = 65;
            shootY = 8;
            requirements(Category.turret, with(Items.silicon,1000, Items.plastanium,350, SFItems.siliSteel,500, Items.surgeAlloy,700));

            reload = 666;
            rotateSpeed = 2.5f;
            range = 544;
            coolantMultiplier = 0.5f;
            consumePower(42f);
            liquidCapacity = 300;
            coolant = consumeCoolant(2);
            shootEffect = new ParticleEffect(){{
                sizeFrom = 10;
                length = 0;
                lifetime = 55;
                colorFrom = colorTo = SFColor.energyGreen;
            }};
            chargeSound = Sounds.lasercharge;
            shootType = new PointBulletType(){{
                speed = 100;
                damage = splashDamage = 1130;
                splashDamageRadius = 64;
                status = SFStatusEffects.echoFlame;
                statusDuration = 15;
                ammoMultiplier = 1;
                trailSpacing = 9;
                trailEffect = new ParticleEffect() {{
                    line = true;
                    sizeInterp = Interp.slope;
                    lenFrom = lenTo = 10;
                    length = baseLength = 1;
                    strokeFrom = 0;
                    strokeTo = 8.5f;
                    randLength = false;
                    lifetime = 50;
                    colorFrom = colorTo = SFColor.energyGreen;
                    cone = 0;
                }};
                hitSound = Sounds.plasmaboom;
                hitSoundVolume = 2;
                hitShake = 7;
                smokeEffect = new ParticleEffect() {{
                    particles = 25;
                    interp = Interp.pow5Out;
                    sizeInterp = Interp.fade;
                    sizeFrom = 8;
                    lifetime = 125;
                    length = 60;
                    colorFrom = SFColor.energyGreen.cpy().a(0.8f);
                    colorTo = SFColor.energyGreen.cpy().a(0.2f);
                }};
                despawnEffect = smokeEffect;
                hitColor = SFColor.energyGreen.cpy().a(0.3f);
                hitEffect = new MultiEffect(
                        Fx.titanSmoke,
                        new WaveEffect(){{
                            lifetime = 13;
                            sizeTo = 150;
                            strokeFrom = 50;
                            colorFrom = SFColor.energyGreen;
                            colorTo = SFColor.energyGreen.cpy().a(0.2f);
                        }},
                        new ParticleEffect(){{
                            sizeInterp = Interp.pow5In;
                            sizeFrom = 20;
                            lifetime = 80;
                            length = 0;
                            colorFrom = SFColor.energyGreen;
                            colorTo = SFColor.energyGreen.cpy().a(0.1f);
                        }}
                );
                chargeEffect = new MultiEffect(
                        new ParticleEffect(){{
                            particles = 25;
                            interp = Interp.pow5In;
                            sizeInterp = Interp.bounce;
                            sizeFrom = 0;
                            sizeTo = 3.5f;
                            lifetime = 60;
                            length = 100;
                            baseLength = -100;
                            colorFrom = colorTo = SFColor.energyGreen;
                        }},
                        new ParticleEffect(){{
                            sizeInterp = Interp.elastic;
                            sizeTo = 10;
                            lifetime = 90;
                            length = 0;
                            colorFrom = colorTo = SFColor.energyGreen;
                        }}
                );
            }};
        }};
        sizhao = new ItemTurret("sizhao") {{
            size = 5;
            health = 5000;
            recoil = 5;
            recoilTime = 65;
            shootSound = Sounds.release;
            shake = 8f;
            cooldownTime = 100;
            minWarmup = 0.8f;
            shootWarmupSpeed = 0.1f;
            warmupMaintainTime = 350f;
            drawer = new DrawTurret(){{parts.addAll(
                new RegionPart("-barrel"){{
                    x = 8;
                    y = 4;
                    mirror = true;
                    heatColor = Color.valueOf("BFFFDB80");
                    moveX = 3;
                    moveY = -2;
                }},
                new RegionPart("-bottom"){{
                    under = true;
                    progress = PartProgress.reload;
                    moveY = -3;
                }},
                new RegionPart("-back"){{
                    x = 8;
                    y = -12;
                    mirror = true;
                    heatProgress = PartProgress.warmup;
                    heatColor = Color.valueOf("BFFFDB80");
                    moveX = 1.75f;
                    moveY = -2;
                    moveRot = 15;
                }},
                new RegionPart("-front"){{
                    under = true;
                    moveY = 8;
                }}
            );}};
            requirements(Category.turret, with(Items.lead,1100, Items.silicon,500, SFItems.fermium,800, SFItems.tayrAlloy,750, Items.phaseFabric,550));
            consumePower(28);

            reload = 360;
            rotateSpeed = 2;
            range = 880;
            coolantMultiplier = 0.8f;
            liquidCapacity = 120;
            coolant = consumeCoolant(1.5f);
            ammoPerShot = 8;
            maxAmmo = 24;
            ammo(
                    SFItems.tayrAlloy, new PointBulletType(){{
                        damage = 3000;
                        hitSound = SFSounds.hugeExplosion;
                        speed = 40;
                        hitShake = 8;
                        buildingDamageMultiplier = 0.12f;
                        status = SFStatusEffects.scrambled;
                        statusDuration = 115;
                        trailSpacing = 9;
                        trailEffect = new ParticleEffect() {{
                            line = true;
                            sizeInterp = Interp.slope;
                            lenFrom = lenTo = 10;
                            length = baseLength = 1;
                            strokeFrom = 0;
                            strokeTo = 12;
                            randLength = false;
                            lifetime = 22;
                            colorFrom = colorTo = SFColor.tayrLight;
                            cone = 0;
                        }};
                        hitColor = SFColor.tayrLight;
                        shootEffect = new Effect(24f, e -> {
                            e.scaled(15f, b -> {
                                color(Color.white, hitColor, b.fin());
                                stroke(b.fout() * 3f + 0.2f);
                                Lines.circle(b.x, b.y, b.fin() * 50f);
                            });

                            color(hitColor);

                            for(int i : Mathf.signs){
                                Drawf.tri(e.x, e.y, 16f * e.fout(), 85f, e.rotation + 135f * i);
                                Drawf.tri(e.x, e.y, 16f * e.fout(), 50f, e.rotation + 45f * i);
                            }

                            Drawf.light(e.x, e.y, 180f, SFColor.tayrDark, 0.9f * e.fout());
                        });
                        smokeEffect = new MultiEffect(
                                Fx.smokeCloud,
                                Fx.bigShockwave,
                                new ParticleEffect(){{
                                    sizeInterp = Interp.pow2In;
                                    sizeFrom = 10;
                                    lifetime = 80;
                                    length = 0;
                                    colorFrom = SFColor.tayrDark;
                                    colorTo = SFColor.tayrLight.cpy().a(0.1f);
                                }}
                        );
                        hitEffect = despawnEffect = new MultiEffect(
                                new ExplosionEffect() {{
                                    waveLife = 15;
                                    waveStroke = 3;
                                    waveRad = 80;
                                    waveColor = smokeColor = hitColor;
                                    sparks = 0;
                                    smokes = 1;
                                    smokeRad = 0;
                                    smokeSize = 12;
                                    lifetime = 55;
                                }},
                                new Effect(32f, 160f, e -> {
                                    color(hitColor);

                                    Mathf.rand.setSeed(e.id);
                                    for (int i = 0; i < 6; i++) {
                                        float angle = Mathf.random(360f);

                                        float lr = Mathf.range(35);
                                        Drawf.tri(e.x, e.y, e.foutpow() * 30, 55 + lr, angle);
                                    }
                                }));
                    }},
                    SFItems.discFabric, new RailBulletType(){{
                        pierce = pierceBuilding = true;
                        pierceCap = 8;
                        damage = 1960;
                        buildingDamageMultiplier = 0.25f;
                        ammoMultiplier = 3;
                        pierceDamageFactor = 0.075f;
                        status = SFStatusEffects.breakdown;
                        statusDuration = 100;
                        length = 880;
                        pointEffectSpace = 50;
                        pointEffect = new ParticleEffect(){{
                            particles = 1;
                            region = "sfire-mod-arrow";
                            sizeInterp = Interp.pow5In;
                            sizeFrom = 12;
                            length = 30;
                            randLength = false;
                            lifetime = 30;
                            colorFrom = colorTo = Color.valueOf("f9c27a");
                            cone = 0;
                        }};
                        shootEffect = Fx.instShoot;
                        smokeEffect = new ParticleEffect(){{
                            particles = 8;
                            sizeInterp = interp = Interp.circleOut;
                            sizeFrom = 12;
                            length = 70;
                            lifetime = 80;
                            colorFrom = colorTo = Color.valueOf("f9c27a");
                            cone = 40;
                        }};
                        pierceEffect = hitEffect = Fx.instHit;
                        hitSound = Sounds.explosionbig;
                        hitSoundVolume = 3;
                        spawnBullets.add(new BasicBulletType(41.6f, 1300) {{
                            shieldDamageMultiplier = 6;
                            lifetime = 880 / 41.6f;
                            lightning = 2;
                            lightningLength = 12;
                            lightningDamage = 96;
                            trailInterval = 0.5f;
                            trailEffect = new ParticleEffect() {{
                                particles = 8;
                                sizeInterp = interp = Interp.circleOut;
                                sizeFrom = 8;
                                baseLength = length = 10;
                                colorFrom = colorTo = Color.valueOf("f9c27a");
                            }};
                            pierce = true;
                            pierceCap = 6;
                            buildingDamageMultiplier = 0.25f;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 100;
                            width = 25;
                            height = 75;
                            lightningColor = backColor = trailColor = Color.valueOf("f9c27a");
                            trailLength = 13;
                            trailWidth = 7;
                            hitShake = 5;
                            hitSound = Sounds.plasmaboom;
                            despawnEffect = Fx.instBomb;
                            hitEffect = Fx.instHit;
                        }});
                    }}
            );
        }};
        liemei = new ItemTurret("liemei") {{
            size = 5;
            health = 3500;
            recoil = 5f;
            recoilTime = 90;
            shootSound = Sounds.dullExplosion;
            shake = 4f;
            minWarmup = 0.89f;
            shootWarmupSpeed = 0.03f;
            warmupMaintainTime = 150f;
            drawer = new DrawTurret() {{parts.add(
                    new RegionPart("-barrel") {{
                        x = 12f;
                        mirror = true;
                        under = true;
                        progress = PartProgress.recoil;
                        moveY = -18/4f;
                        moveRot = -5f;
                        children.add(new RegionPart("-in"){{
                            mirror = true;
                            under = true;
                            layerOffset = -0.0001f;
                            progress = PartProgress.warmup;
                            moveY = -15/4f;
                            moves.add(new PartMove(PartProgress.recoil,0,-26/4f,0));
                        }});
                    }},
                    new RegionPart("-mid") {{
                        y = -16;
                        mirror = false;
                        under = true;
                        moveY = 16;
                        heatProgress = PartProgress.warmup;
                        heatColor = SFColor.discLight.cpy().a(0.5f);
                    }}
            );}};
            shootY = 10;
            shoot.shots = 8;
            shoot.shotDelay = 1;
            xRand = 9;
            requirements(Category.turret, with(Items.graphite,1300, Items.titanium,1000, Items.surgeAlloy,650, SFItems.waveSteel,850, SFItems.discFabric,500));

            reload = 138;
            rotateSpeed = 2;
            range = 620;
            coolantMultiplier = 1.5f;
            coolant = consumeCoolant(1f);
            shootEffect = Fx.shootBig;
            smokeEffect = Fx.shootBigSmoke;
            ammoUseEffect = Fx.none;
            ammoPerShot = 5;
            maxAmmo = 36;
            inaccuracy = 12;
            velocityRnd = 0.13f;
            liquidCapacity = 80;
            targetAir = false;
            ammo(
                    Items.blastCompound, new ArtilleryBulletType(5f,35, "shell"){{
                        splashDamage = 80;
                        splashDamageRadius = 60;
                        lifetime = 120;
                        ammoMultiplier = 3;
                        collidesTiles = false;
                        absorbable = false;
                        status = StatusEffects.blasted;

                        width = height = 15;
                        backColor = trailColor = Pal.blastAmmoBack;
                        frontColor = hitColor = Pal.blastAmmoFront;
                        trailChance = 0.008f;
                        trailEffect = new MultiEffect(Fx.artilleryTrail, Fx.artilleryTrailSmoke);
                        hitSound = Sounds.explosion;
                        hitSoundVolume = 5;
                        hitShake = 3;
                        hitEffect = new ExplosionEffect(){{
                            smokes = 12;
                            smokeRad = splashDamageRadius * 0.75f;
                            smokeSize = 10;
                            smokeColor = hitColor;
                            sparks = 15;
                            sparkRad = splashDamageRadius + 35;
                            sparkLen = 16;
                            sparkStroke = 1.5f;
                            sparkColor = Pal.bulletYellow;
                            lifetime = 25;
                            waveColor = hitColor;
                            waveRad = splashDamageRadius;
                            waveStroke = 6;
                            waveLife = 10;
                        }};
                        despawnEffect = Fx.bigShockwave;
                        fragBullets = 5;
                        fragRandomSpread = 0;
                        fragSpread = 72;
                        fragBullet = new ArtilleryBulletType(2,10, "shell"){{
                            splashDamage = 73;
                            splashDamageRadius = 36;
                            lifetime = 20;
                            scaledSplashDamage = true;
                            collidesGround = true;
                            absorbable = false;
                            status = StatusEffects.blasted;

                            width = height = 8;
                            backColor = trailColor = Pal.blastAmmoBack;
                            frontColor = hitColor = Pal.blastAmmoFront;
                            hitSound = Sounds.explosion;
                            hitShake = 2;
                        }};
                    }},
                    Items.plastanium, new ArtilleryBulletType(7,40, "shell"){{
                        splashDamage = 50;
                        splashDamageRadius = 55;
                        lifetime = 120;
                        ammoMultiplier = 3;
                        reloadMultiplier = 1.2f;
                        collidesTiles = false;
                        absorbable = false;
                        status = StatusEffects.blasted;

                        width = height = 15;
                        backColor = hitColor = trailColor = Pal.plastaniumBack;
                        frontColor = Pal.plastaniumFront;
                        trailChance = 0.008f;
                        trailEffect = new MultiEffect(Fx.artilleryTrail, Fx.artilleryTrailSmoke);
                        hitSound = Sounds.explosion;
                        hitSoundVolume = 5;
                        hitShake = 3;
                        hitEffect = new ExplosionEffect(){{
                            smokes = 0;
                            sparks = 13;
                            sparkRad = splashDamageRadius + 35;
                            sparkLen = 16;
                            sparkStroke = 1.8f;
                            sparkColor = Pal.bulletYellow;
                            lifetime = 25;
                            waveColor = hitColor;
                            waveRad = splashDamageRadius;
                            waveStroke = 6;
                            waveLife = 10;
                        }};
                        despawnEffect = Fx.bigShockwave;
                        fragBullets = 9;
                        fragRandomSpread = 0;
                        fragSpread = 40;
                        fragBullet = new BasicBulletType(3.5f, 35, "shell") {{
                            despawnEffect = Fx.none;
                            width = 10;
                            height = 12;
                            knockback = 1.8f;
                            shrinkY = 1;
                            lifetime = 15;
                            collidesAir = false;
                            status = StatusEffects.slow;
                            statusDuration = 12;
                            backColor = Pal.plastaniumBack;
                            frontColor = Pal.plastaniumFront;
                        }};
                    }},
                    Items.surgeAlloy, new BasicBulletType(7,80, "shell"){{
                        rangeChange = 46;
                        splashDamage = 45;
                        splashDamageRadius = 45;
                        lifetime = 96;
                        ammoMultiplier = 4;
                        collidesAir = false;
                        absorbable = false;
                        pierce = true;
                        pierceCap = 4;
                        status = StatusEffects.shocked;

                        width = height = 16;
                        backColor = hitColor = trailColor = Pal.surgeAmmoBack;
                        frontColor = Pal.surgeAmmoFront;
                        trailLength = 11;
                        trailWidth = 4;
                        shrinkY = 0.6f;
                        hitSound = Sounds.explosionbig;
                        hitSoundVolume = 0.8f;
                        hitShake = 3;
                        hitEffect = new ExplosionEffect(){{
                            smokes = 8;
                            smokeRad = splashDamageRadius * 0.75f;
                            smokeSize = 8;
                            smokeColor = hitColor;
                            sparks = 0;
                            lifetime = 25;
                            waveColor = hitColor;
                            waveRad = splashDamageRadius;
                            waveStroke = 8;
                            waveLife = 13;
                        }};
                        despawnEffect = Fx.bigShockwave;
                        lightColor = Pal.surgeAmmoFront;
                        lightning = 2;
                        lightningLength = 14;
                        lightningLengthRand = 4;
                        lightningDamage = 15;
                        lightningType = new BulletType(0.0001f, 0f){{
                            collidesAir = false;
                            lifetime = Fx.lightning.lifetime;
                            hitEffect = Fx.hitLancer;
                            despawnEffect = Fx.none;
                            status = StatusEffects.shocked;
                            statusDuration = 10f;
                            hittable = false;
                            lightColor = Pal.surgeAmmoFront;
                        }};
                    }},
                    SFItems.siliSteel, new ArtilleryBulletType(5f,26, "shell"){{
                        splashDamage = 32;
                        splashDamageRadius = 52;
                        lifetime = 120;
                        ammoMultiplier = 3;
                        collidesTiles = false;
                        absorbable = false;
                        status = SFStatusEffects.magnStrif;
                        statusDuration = 30;

                        width = height = 15;
                        backColor = trailColor = SFColor.sisteelDark;
                        frontColor = hitColor = SFColor.sisteelLight;
                        trailChance = 0.008f;
                        trailEffect = new MultiEffect(Fx.artilleryTrail, Fx.artilleryTrailSmoke);
                        hitSound = Sounds.explosion;
                        hitSoundVolume = 5;
                        hitShake = 3;
                        hitEffect = new ExplosionEffect(){{
                            smokes = 8;
                            smokeRad = splashDamageRadius * 0.75f;
                            smokeSize = 10;
                            smokeColor = hitColor;
                            sparks = 7;
                            sparkRad = splashDamageRadius + 35;
                            sparkLen = 16;
                            sparkStroke = 1.5f;
                            sparkColor = Pal.bulletYellow;
                            lifetime = 25;
                            waveColor = hitColor;
                            waveRad = splashDamageRadius;
                            waveStroke = 6;
                            waveLife = 10;
                        }};
                        despawnEffect = Fx.bigShockwave;
                        fragBullets = 5;
                        fragRandomSpread = 0;
                        fragSpread = 72;
                        fragBullet = new ArtilleryBulletType(2,10, "shell"){{
                            splashDamage = 32;
                            splashDamageRadius = 26;
                            lifetime = 20;
                            scaledSplashDamage = true;
                            collidesGround = true;
                            absorbable = false;
                            status = SFStatusEffects.magnStrif;
                            statusDuration = 15;

                            width = height = 8;
                            backColor = trailColor = SFColor.sisteelDark;
                            frontColor = hitColor = SFColor.sisteelLight;
                            hitEffect = Fx.flakExplosion;
                            hitSound = Sounds.explosion;
                            hitShake = 2;
                        }};
                    }},
                    SFItems.clusBomb, new ArtilleryBulletType(4f,28, "shell"){{
                        rangeChange = 46;
                        splashDamage = 80;
                        splashDamageRadius = 60;
                        lifetime = 162;
                        ammoMultiplier = 3;
                        collidesTiles = false;
                        absorbable = false;
                        status = StatusEffects.blasted;

                        width = height = 17;
                        backColor = trailColor = SFColor.clusRedDark;
                        frontColor = hitColor = SFColor.clusRed;
                        trailChance = 0.008f;
                        trailEffect = new MultiEffect(Fx.artilleryTrail, Fx.artilleryTrailSmoke);
                        hitSound = Sounds.explosionbig;
                        hitSoundVolume = 0.8f;
                        hitShake = 4;
                        hitEffect = new ExplosionEffect(){{
                            smokes = 12;
                            smokeRad = splashDamageRadius * 0.75f;
                            smokeSize = 12;
                            smokeColor = hitColor.cpy().a(0.8f);
                            sparks = 15;
                            sparkRad = splashDamageRadius + 35;
                            sparkLen = 16;
                            sparkStroke = 1.5f;
                            sparkColor = Pal.bulletYellow;
                            lifetime = 25;
                            waveColor = hitColor;
                            waveRad = splashDamageRadius;
                            waveStroke = 6;
                            waveLife = 10;
                        }};
                        despawnEffect = Fx.bigShockwave;
                        fragBullets = 10;
                        fragRandomSpread = 0;
                        fragSpread = 36;
                        fragLifeMin = 0.2f;
                        fragBullet = new ArtilleryBulletType(2,28, "shell"){{
                            splashDamage = 85;
                            splashDamageRadius = 60;
                            lifetime = 20;
                            drag = 0.005f;
                            scaledSplashDamage = true;
                            collidesGround = true;
                            absorbable = false;
                            status = StatusEffects.blasted;

                            width = height = 9;
                            backColor = trailColor = SFColor.clusRedDark;
                            frontColor = hitColor = SFColor.clusRed;
                            hitEffect = new ExplosionEffect(){{
                                smokes = 5;
                                smokeRad = splashDamageRadius * 0.75f;
                                smokeSize = 9;
                                smokeColor = hitColor.cpy().a(0.8f);
                                sparks = 10;
                                sparkRad = splashDamageRadius + 35;
                                sparkLen = 8;
                                sparkStroke = 1.5f;
                                sparkColor = Pal.bulletYellow;
                                lifetime = 25;
                                waveColor = hitColor;
                                waveRad = splashDamageRadius;
                                waveStroke = 6;
                                waveLife = 12;
                            }};
                            hitSound = Sounds.explosion;
                            hitShake = 2;
                        }};
                    }}
            );
        }};
        cuowei = new ItemTurret("cuowei") {{
            size = 5;
            health = 3600;
            recoil = 5;
            recoilTime = 65;
            shootSound = Sounds.plasmaboom;
            shake = 4f;
            cooldownTime = 100;
            shootY = 15;
            requirements(Category.turret, with(Items.thorium,1100, Items.plastanium,550, Items.phaseFabric,800, SFItems.waveSteel,700, SFItems.tayrAlloy,260));
            consumePower(40);

            reload = 260;
            rotateSpeed = 2.55f;
            range = 346;
            coolantMultiplier = 1f;
            liquidCapacity = 60;
            coolant = consumeCoolant(1f);
            ammoUseEffect = Fx.none;
            ammoPerShot = 5;
            maxAmmo = 20;
            //for(int i=0; i<10; i++) {int f=i;
            BulletType discFrag = new BasicBulletType(0.5f, 36,"mine-bullet") {{
                lifetime = 30;
                collides = false;
                pierceArmor = true;
                lightningDamage = 15;
                lightning = 3;
                lightningLength = 6;
                lightningColor = SFColor.discLight;
                lightningType = new BulletType(0.0001f, 0f){{
                    shieldDamageMultiplier = 5;
                    lifetime = Fx.lightning.lifetime;
                    hitEffect = Fx.hitLancer;
                    despawnEffect = Fx.none;
                    status = StatusEffects.shocked;
                    hittable = false;
                    lightColor = lightningColor;
                }};

                splashDamageRadius = 52;
                splashDamage = 60;
                status = SFStatusEffects.breakdown;
                statusDuration = 60;

                width = height = 8;
                shrinkY = shrinkX = 1;
                frontColor = backColor = hitColor = trailColor = SFColor.discLight;

                despawnEffect = Fx.instBomb;
                hitEffect = new ExplosionEffect(){{
                   smokes =  8;
                   sparks = 0;
                   smokeSize = 6;
                   smokeRad = 55;
                   lifetime = 15;
                   waveColor = smokeColor = hitColor;
                   waveLife = 10;
                   waveRad = 50;
                   waveStroke = 6;
                }};
                hitSound = Sounds.plasmaboom;
                hitSoundVolume = 0.5f;
            }};
            //}
            ammo(
                    SFItems.discFabric, new PowerupBulletType(16,100,"sfire-mod-arrow-bullet"){{
                        lifetime = 21.6f;
                        hittable = false;
                        shieldDamageMultiplier = 5;
                        lightningDamage = 0.16f * damage;
                        lightning = 3;
                        lightningLength = 13;
                        lightningColor = SFColor.discLight;
                        lightningType = new BulletType(0.0001f, 0f){{
                            shieldDamageMultiplier = 5;
                            lifetime = Fx.lightning.lifetime;
                            hitEffect = Fx.hitLancer;
                            despawnEffect = Fx.none;
                            status = StatusEffects.shocked;
                            hittable = false;
                            lightColor = lightningColor;
                        }};
                        splashDamageRadius = 80;
                        splashDamage = damage * 1.7f;
                        status = SFStatusEffects.breakdown;
                        statusDuration = 320;
                        ammoMultiplier = 3;

                        width = 16;
                        height = 40;
                        frontColor = backColor = hitColor = trailColor = SFColor.discLight;
                        trailLength = 10;
                        trailWidth = 3;
                        shrinkY = 0;
                        hitSound = Sounds.plasmaboom;
                        despawnEffect = Fx.none;
                        hitEffect = new ExplosionEffect(){{
                            smokes =  15;
                            sparks = 0;
                            smokeSize = 9;
                            smokeRad = 85;
                            lifetime = 25;
                            waveColor = smokeColor = hitColor;
                            waveLife = 10;
                            waveRad = 80;
                            waveStroke =9;
                        }};
                        shootEffect = new Effect(20, e -> {
                            color(hitColor);
                            float w = 1.3f + 10 * e.fout();
                            Drawf.tri(e.x, e.y, w, 45f * e.fout(), e.rotation);
                            Drawf.tri(e.x, e.y, w, 16f * e.fout(), e.rotation + 180f);

                            for(int s : new int[]{1, -1}) {
                                Drawf.tri(e.x, e.y, 6f, 50f * e.fout(), e.rotation + s * 135);
                            }
                        });
                        intervalRandomSpread = fragRandomSpread = 60;
                        intervalAngle = fragAngle = 0;

                        fragBullets = 3;
                        fragVelocityMin = 2;
                        fragVelocityMax = 5;
                        fragLifeMax = 2;
                        fragBullet = discFrag;

                        bulletInterval = 0.5f;
                        intervalBullets = 1;
                        intervalSpread = 8;
                        intervalBullet = discFrag;
                    }}
            );
        }};
    //6*6//
        dingdaer = new PowerTurret("dingdaer") {{
            size = 6;
            health = 3800;
            recoil = 3;
            cooldownTime = 90;
            shootSound = Sounds.laser;
            soundPitchMax = soundPitchMin = 5f;
            shake = 0.5f;
            inaccuracy = 2;
            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        8, -1.25f, 0,
                        -8, -1.25f, 0,
                        -13.25f, -2.5f, 0,
                        13.25f,-2.5f,0
                };
                shots = 8;
                shotDelay = 4;
            }};
            drawer = new DrawTurret(){{parts.add(
                    new RegionPart("-back") {{
                        mirror = true;
                        under = true;
                        moveX = 2;
                        moveRot = -5;
                        moves.add(new PartMove(PartProgress.recoil, 0, -2, -2));
                    }},
                    new RegionPart("-warm"){{
                        drawRegion = false;
                        heatColor = SFColor.energyYellow;
                        heatProgress = PartProgress.warmup;
                    }}
                );}};
            shootWarmupSpeed = 0.02f;
            warmupMaintainTime = 50;
            requirements(Category.turret, with(SFItems.rubidium,1100, Items.plastanium,600, SFItems.siliSteel,560, SFItems.tayrAlloy,750, SFItems.discFabric,850, SFItems.lens,500));

            reload = 16;
            rotateSpeed = 3.6f;
            range = 500;
            coolantMultiplier = 1;
            consumePower(45f);
            liquidCapacity = 300;
            coolant = consumeCoolant(2);
            targetGround = false;
            shootType = new BasicBulletType(22, 70, "shell") {{
                shieldDamageMultiplier = 2;
                lifetime = 25;
                drag = 0.005f;
                ammoMultiplier = 1;
                status = SFStatusEffects.breakdown;
                statusDuration = 60;
                collidesGround = false;
                pierce = true;
                pierceCap = 6;
                hittable = false;

                frontColor = Color.white.cpy().a(0.35f);
                trailColor = backColor = hitColor = SFColor.energyYellow.cpy().a(0.5f);
                trailWidth = 2f;
                trailLength = 16;
                width = 9;
                height = 25;
                shootEffect = Fx.shootBigColor;
                smokeEffect = Fx.none;
                hitEffect = new ParticleEffect(){{
                    particles = 5;
                    line = true;
                    strokeFrom = 3;
                    lenFrom = 10;
                    length = 50;
                    lifetime = 10;
                    colorFrom = Color.white;
                    colorTo = SFColor.energyYellow;
                    cone = 60;
                }};
                despawnEffect = Fx.hitBulletColor;
            }};
        }};
        guangyin = new PowerTurret("guangyin") {{
            size = 6;
            armor = 6;
            health = 6800;
            recoil = 6;
            recoilTime = 100;
            cooldownTime = 120;
            shootSound = Sounds.laser;
            shake = 5f;
            inaccuracy = 3;
            shoot = new ShootAlternate(16){{shots=6;shotDelay=2;}};
            requirements(Category.turret, with(Items.copper,1600, Items.metaglass,650, SFItems.siliSteel,600, SFItems.fermium,700, Items.surgeAlloy,800, Items.phaseFabric,850));

            reload = 82;
            rotateSpeed = 2f;
            range = 588;
            consumePower(60f);
            consumeLiquid(Liquids.water,1f);
            liquidCapacity = 600;
            velocityRnd = 0.1f;

            shootType = new BasicBulletType(11, 50) {{
                lifetime = 48;
                ammoMultiplier = 1;
                status = SFStatusEffects.breakdown;
                statusDuration = 18;

                frontColor = Color.white;
                trailColor = backColor = SFColor.energyYellow;
                trailWidth = 3.6f;
                trailLength = 12;
                trailInterval = 1f;
                trailEffect = new ParticleEffect() {{
                    particles = 3;
                    sizeFrom = 4;
                    length = 10;
                    baseLength = 16;
                    colorFrom = SFColor.energyYellow.cpy().a(0.3f);
                    colorTo = SFColor.energyYellow;
                }};
                width = 18;
                height = 24;
                hitSize = 10f;
                hitSound = Sounds.lasercharge2;
                shootEffect = Fx.shootBigColor;
                smokeEffect = new MultiEffect(
                        Fx.smokeCloud,
                        new ParticleEffect() {{
                            particles = 5;
                            lifetime = 15;
                            sizeFrom = 4;
                            length = 35;
                            colorTo = colorFrom = SFColor.energyYellow;
                            cone = 30;
                        }}
                );
                hitEffect = despawnEffect = new MultiEffect(
                        new ParticleEffect() {{
                            particles = 17;
                            lifetime = 16;
                            sizeFrom = 0;
                            sizeTo = 2.25f;
                            length = -66;
                            baseLength = 66;
                            colorTo = SFColor.energyYellow;
                            sizeInterp = Interp.pow2Out;
                        }},
                        new ParticleEffect() {{
                            lifetime = 15;
                            sizeFrom = 0;
                            sizeTo = 9;
                            length = 0;
                            colorTo = SFColor.energyYellow;
                            sizeInterp = Interp.pow2Out;
                        }},
                        new WaveEffect() {{
                            sizeFrom = 70;
                            sizeTo = 8;
                            strokeFrom = 2;
                            strokeTo = 3;
                            interp = Interp.circleIn;
                            lifetime = 10;
                            colorFrom = colorTo = SFColor.energyYellow;
                        }}
                );
                fragLifeMin = 1;
                fragBullets = 1;
                fragBullet = new BasicBulletType(0,150,"mine-bullet"){{
                    hittable = absorbable = collides = false;
                    scaledSplashDamage = true;
                    width = height = 3;
                    lifetime = 15;
                    frontColor = Color.white;
                    trailColor = backColor = SFColor.energyYellow;
                    splashDamage = 220;
                    splashDamageRadius = 56;
                    status = SFStatusEffects.breakdown;
                    statusDuration = 180;
                    hitSound = Sounds.plasmaboom;
                    hitShake = 5;
                    hitEffect = new ExplosionEffect() {{
                        sparks = 22;
                        sparkLen = 13;
                        sparkStroke = 3;
                        sparkRad = 60;
                        sparkColor = SFColor.energyYellow;
                        smokes = 4;
                        smokeSize = 15;
                        smokeRad = 48;
                        smokeColor = SFColor.energyYellow.cpy().a(0.4f);
                        lifetime = 25;
                        waveRad = splashDamageRadius;
                        waveLife = 30;
                        waveStroke = 6;
                        waveColor = SFColor.energyYellow;
                    }};
                }};
            }};
        }};
        cimai = new ItemTurret("cimai") {{
            size = 6;
            health = 12800;
            armor = 22;
            recoil = 3f;
            recoilTime = 60;
            cooldownTime = 40;
            shootSound = Sounds.artillery;
            shake = 8f;
            minWarmup = 0.9f;
            warmupMaintainTime = 180;
            drawer = new DrawTurret(){{parts.add(
                    new RegionPart("-barrel") {{
                        mirror = false;
                        under = true;
                        moveY = 6;
                        progress = PartProgress.warmup;
                        heatProgress = PartProgress.recoil;
                        heatColor = Color.valueOf("FF7055");
                        moves.add(new PartMove(PartProgress.recoil.curve(Interp.pow3Out), 0, -16, 0));
                    }},
                    new RegionPart("-back2") {{
                        mirror = true;
                        x = 16;
                        y = -8;
                        progress = PartProgress.heat;
                        moveX = 4;
                        moveY = -4;
                    }},
                    new RegionPart("-back1") {{
                        mirror = true;
                        x = 12;
                        y = -16;
                        progress = PartProgress.heat;
                        moveX = 4;
                        moveY = -4;
                    }}
            );}};
            requirements(Category.turret, with(Items.graphite,1500, SFItems.fermium,1100, Items.blastCompound,300, Items.phaseFabric,500, SFItems.waveSteel,650, SFItems.chromium,650));

            reload = 230;
            rotateSpeed = 2.3f;
            range = 496;
            coolantMultiplier = 0.5f;
            coolant = consumeCoolant(3f);
            liquidCapacity = 120;
            shootSound = Sounds.mediumCannon;
            shootEffect = Fx.shootBig;
            smokeEffect = Fx.shootBigSmoke;
            ammoUseEffect = Fx.casing3Double;
            ammoPerShot = 18;
            maxAmmo = 60;
            targetAir = false;
            inaccuracy = 1.5f;
            ammo(
                    SFItems.chromium, new BasicBulletType(35, 1440, "missile-large"){{
                        drag = 0.05f;
                        lifetime = 25;
                        pierceArmor = true;
                        collidesAir = false;
                        status = SFStatusEffects.inBreak;
                        statusDuration = 60;
                        knockback = 36;
                        reloadMultiplier = 1.5f;

                        width = 16;
                        height = 25;
                        shrinkY = 0.1f;
                        frontColor = SFColor.chromiumLight;
                        backColor = hitColor = SFColor.chromiumDark;
                        trailColor = backColor.cpy().a(0.5f);
                        trailWidth = 2;
                        trailLength = 6;
                        trailChance = 1f;
                        trailEffect = new MultiEffect(
                                new WaveEffect(){{
                                    startDelay = 1.25f;
                                    lifetime = 20;
                                    sizeFrom = 3;
                                    sizeTo = 24;
                                    strokeFrom = 6;
                                    colorTo = SFColor.discLight.cpy().a(0.55f);
                                }},
                                new ParticleEffect(){{
                                    startDelay = 1.25f;
                                    lifetime = 45;
                                    particles = 6;
                                    sizeFrom = 6;
                                    length = 16;
                                    interp = Interp.pow10Out;
                                    sizeInterp = Interp.pow5In;
                                    region = "sfire-mod-loz";
                                    colorFrom = colorTo = SFColor.chromiumDark;
                                }}
                        );
                        shootEffect = Fx.bigShockwave;
                        smokeEffect = new ParticleEffect(){{
                            particles = 16;
                            sizeFrom = 8;
                            lifetime = 100;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = SFColor.discDark;
                            colorTo = SFColor.chromiumDark;
                            cone = 10;
                        }};
                        hitEffect = new ParticleEffect(){{
                            particles = 15;
                            line = true;
                            lenFrom = 16;
                            strokeFrom = 4.5f;
                            lifetime = 22;
                            length = 100;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow10In;
                            colorFrom = colorTo = Pal.bulletYellow;
                            cone = 60;
                        }};
                        hitSound = Sounds.shootSmite;
                        hitSoundVolume = 2;
                        hitShake = 6f;
                        despawnEffect = Fx.hitBulletColor;
                        fragBullets = 8;
                        fragRandomSpread = 45;
                        fragBullet = new BasicBulletType(12,88){{
                            frontColor = SFColor.chromiumLight;
                            backColor = hitColor = SFColor.chromiumDark;
                            width = 12;
                            height = 14;
                            shrinkY = 0.8f;
                            lifetime = 9;
                            pierceArmor = true;
                            collidesAir = false;
                            hitEffect = Fx.hitBulletColor;
                            despawnEffect = Fx.none;
                            status = StatusEffects.slow;
                            statusDuration = 30;
                            knockback = 19;
                        }};
                    }},
                    SFItems.leipAlloy, new BasicBulletType(44, 2100, "missile-large"){{
                        rangeChange = 48;
                        drag = 0.05f;
                        lifetime = 19.5f;
                        pierceArmor = true;
                        collidesAir = false;
                        pierce = true;
                        pierceCap = 3;
                        knockback = 36;
                        ammoMultiplier = 4;
                        reloadMultiplier = 1.5f;

                        width = 16;
                        height = 25;
                        shrinkY = 0.1f;
                        frontColor = SFColor.leipLight;
                        backColor = hitColor = SFColor.leipDark;
                        trailColor = backColor.cpy().a(0.5f);
                        trailWidth = 2;
                        trailLength = 6;
                        trailInterval = 0.5f;
                        trailEffect = new MultiEffect(
                                new WaveEffect(){{
                                    startDelay = 1.25f;
                                    lifetime = 20;
                                    sizeFrom = 3;
                                    sizeTo = 24;
                                    strokeFrom = 6;
                                    colorTo = SFColor.discLight.cpy().a(0.55f);
                                }},
                                new ParticleEffect(){{
                                    startDelay = 1.25f;
                                    lifetime = 45;
                                    particles = 6;
                                    sizeFrom = 6;
                                    length = 16;
                                    interp = Interp.pow10Out;
                                    sizeInterp = Interp.pow5In;
                                    region = "sfire-mod-loz";
                                    colorFrom = colorTo = SFColor.leipLight;
                                }}
                        );
                        shootEffect = Fx.bigShockwave;
                        smokeEffect = new ParticleEffect(){{
                            particles = 16;
                            sizeFrom = 8;
                            lifetime = 100;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            colorFrom = SFColor.leipLight;
                            colorTo = SFColor.leipDark;
                            cone = 10;
                        }};
                        hitEffect = new ParticleEffect(){{
                            particles = 22;
                            line = true;
                            lenFrom = 23;
                            strokeFrom = 5f;
                            lifetime = 12;
                            length = 120;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5;
                            colorFrom = colorTo = Pal.bulletYellow;
                            cone = 60;
                        }};
                        despawnSound = Sounds.explosion;
                        hitSound = Sounds.shootSmite;
                        hitSoundVolume = 3;
                        hitShake = 6f;
                        despawnEffect = Fx.hitBulletColor;
                        intervalDelay = 0.5f;
                        bulletInterval = 1.25f;
                        intervalBullets = 2;
                        intervalAngle = 0;
                        intervalRandomSpread = 30;
                        intervalBullet = new BasicBulletType(8,700){{
                            frontColor = SFColor.leipLight;
                            backColor = hitColor = SFColor.leipDark;
                            width = height = 6;
                            lifetime = 2;
                            pierceArmor = true;
                            collidesAir = false;
                            hitEffect = Fx.hitBulletColor;
                            despawnEffect = Fx.none;
                        }};
                    }}
            );
        }};
        //fengmang
    //8*8//
        yuanling = new ItemTurret("yuanling") {{
            size = 8;
            health = 33000;
            armor = 36;
            recoil = 2f;
            recoilTime = 18;
            cooldownTime = 90;
            shake = 6f;
            shoot = new ShootAlternate(24);
            recoils = 2;
            drawer = new DrawTurret(){{parts.add(
                    new RegionPart("-barrel-l") {{
                        under = true;
                        moveY = -6;
                        progress = PartProgress.recoil;
                        heatProgress = PartProgress.recoil;
                        recoilIndex = 0;
                    }},
                    new RegionPart("-barrel-r") {{
                        under = true;
                        moveY = -6;
                        progress = PartProgress.recoil;
                        heatProgress = PartProgress.recoil;
                        recoilIndex = 1;
                    }}
            );}};
            requirements(Category.turret, with(Items.thorium,4000, Items.graphite,1200, SFItems.waveSteel,1400, Items.surgeAlloy,800, SFItems.fermium,800));

            reload = 9;
            rotateSpeed = 3.5f;
            range = 520;
            coolantMultiplier = 0.77f;
            coolant = consumeCoolant(4.5f);
            liquidCapacity = 180;
            shootSound = Sounds.shootBig;
            shootEffect = Fx.shootTitan;
            smokeEffect = Fx.shootBigSmoke2;
            ammoEjectBack = 8;
            ammoUseEffect = new Effect(45f, e -> {
                color(Pal.lightOrange, Pal.lightishGray, Pal.lightishGray, e.fin());
                alpha(e.fout(0.5f));
                float rot = Math.abs(e.rotation) + 90f;
                int i = -Mathf.sign(e.rotation);
                float len = (4f + e.finpow() * 9f) * i;
                float lr = rot + Mathf.randomSeedRange(e.id + i + 6, 28+20f * e.fin()) * i;

                rect(Core.atlas.find("casing"),
                        e.x + trnsx(lr, len) + Mathf.randomSeedRange(e.id + i + 7, 3f * e.fin()),
                        e.y + trnsy(lr, len) + Mathf.randomSeedRange(e.id + i + 8, 3f * e.fin()),
                        6f, 11f,
                        rot + e.fin() * 50f * i
                );
            }).layer(Layer.bullet);
            ammoPerShot = 3;
            maxAmmo = 48;
            inaccuracy = 1.6f;
            ammo(
                    Items.thorium, new BasicBulletType(21,192f){{
                        lifetime = 23;
                        knockback = 4;
                        pierce = pierceArmor = true;
                        pierceCap = 2;

                        width = 16;
                        height = 21;
                        frontColor = Pal.thoriumAmmoFront;
                        backColor = hitColor = Pal.thoriumAmmoBack;
                        hitSound = Sounds.explosion;
                        hitEffect = Fx.hitBulletBig;
                        despawnEffect = Fx.hitBulletColor;
                    }},
                    SFItems.fermium, new BasicBulletType(26,227f){{
                        lifetime = 18;
                        knockback = 0.5f;
                        status = StatusEffects.melting;
                        statusDuration = 20;
                        pierce = pierceArmor = pierceBuilding = true;
                        pierceCap = 3;
                        ammoMultiplier = 3;

                        width = 20;
                        height = 26;
                        hitColor = Pal.bulletYellowBack;
                        hitSound = Sounds.explosion;
                        hitEffect = Fx.hitBulletBig;
                        despawnEffect = Fx.hitBulletColor;
                        fragBullets = 5;
                        fragRandomSpread = 30;
                        fragBullet = new BasicBulletType(26,56){{
                            width = 9;
                            height = 10;
                            status = StatusEffects.melting;
                            statusDuration = 10;
                            pierce = pierceBuilding = true;
                            lifetime = 9;
                            drag = 0.05f;
                            hitEffect = Fx.hitBulletBig;
                        }};
                    }},
                    SFItems.chromium, new BasicBulletType(21,158f){{
                        lifetime = 23;
                        knockback = 12;
                        pierceArmor = true;
                        status = SFStatusEffects.inBreak;
                        ammoMultiplier = 3;

                        width = 20;
                        height = 25;
                        frontColor = SFColor.chromiumLight;
                        backColor = hitColor = SFColor.chromiumDark;
                        hitSound = Sounds.explosion;
                        hitEffect = Fx.hitBulletBig;
                        despawnEffect = Fx.hitBulletColor;
                        fragBullets = 5;
                        fragRandomSpread = 60;
                        fragBullet = new BasicBulletType(26,56){{
                            width = 9;
                            height = 10;
                            status = StatusEffects.slow;
                            statusDuration = 10;
                            pierce = pierceBuilding = true;
                            lifetime = 6;
                            knockback = 5;
                            frontColor = SFColor.chromiumLight;
                            backColor = hitColor = SFColor.chromiumDark;
                            hitEffect = Fx.hitBulletBig;
                        }};
                    }},
                    SFItems.discFabric, new BasicBulletType(18,220f, "sfire-mod-arrow-bullet"){{
                        lifetime = 28;
                        status = SFStatusEffects.breakdown;
                        statusDuration = 75f;
                        pierce = pierceArmor = true;
                        reflectable = hittable = false;
                        shieldDamageMultiplier = 5f;
                        reloadMultiplier = 1.25f;
                        ammoMultiplier = 4;

                        width = 17;
                        height = 28;
                        frontColor = hitColor = SFColor.discLight;
                        backColor = trailColor = SFColor.discDark;
                        trailLength = 11;
                        trailWidth = 2.5f;
                        hitSound = Sounds.laser;
                        hitSoundVolume = 0.25f;
                        hitEffect = despawnEffect = Fx.instBomb;
                    }}
            );
        }};
        kuosan = new ItemTurret("kuosan") {{
            size = 8;
            health = 10700;
            recoil = 8f;
            recoilTime = 180;
            cooldownTime = 120;
            shake = 20f;
            drawer = new DrawTurret(){{parts.add(
                    new RegionPart("-barrel") {{
                        under = true;
                        moveY = -10;
                        progress = PartProgress.recoil;
                        heatProgress = PartProgress.recoil;
                    }}
            );}};
            requirements(Category.turret, with(Items.copper,4500, Items.plastanium,1800, SFItems.waveSteel,2200, SFItems.fermium,1300, SFItems.tayrAlloy,1450, SFItems.discFabric,1200));

            reload = 880;
            rotateSpeed = 1.7f;
            range = 800;
            coolantMultiplier = 0.5f;
            coolant = consumeCoolant(3.5f);
            liquidCapacity = 300;
            shootSound = Sounds.release;
            ammoUseEffect = Fx.none;
            ammoPerShot = 40;
            maxAmmo = 45;
            ammo(
                    SFItems.tayrAlloy, new BasicBulletType(10, 400,"sfire-mod-star-bullet"){{
                        lifetime = 32.5f *2;
                        splashDamage = 1800;
                        splashDamageRadius = 240;
                        scaledSplashDamage = true;
                        collidesTiles = false;
                        status = SFStatusEffects.scrambled;
                        statusDuration = 80;

                        lightningDamage = 87;
                        lightning = 8;
                        lightningLength = 33;
                        lightningLengthRand = 20;
                        lightningColor = backColor = trailColor = hitColor = SFColor.tayrDark;
                        frontColor = Color.white;
                        shrinkY = 0;
                        spin = 10;
                        height = width = 128f;
                        trailLength = 18;
                        trailWidth = 4;
                        trailInterval = 4;
                        trailEffect = new WaveEffect(){{
                            interp = Interp.circleOut;
                            lifetime = 45;
                            sizeTo = 46;
                            strokeFrom = 6;
                            colorFrom = trailColor;
                            colorTo = hitColor;
                        }};
                        hitSound = SFSounds.hugeExplosion;
                        hitShake = 20;
                        hitSize = 50;
                        hitEffect = new ExplosionEffect(){{
                            smokes = 80;
                            smokeSize = 50;
                            smokeRad = 165;
                            smokeColor =  hitColor.cpy().a(0.6f);
                            sparkColor = hitColor;
                            sparks = 92;
                            sparkStroke = 8;
                            sparkLen = 45;
                            sparkRad = 253;
                            lifetime = 35;
                            waveStroke = 8;
                            waveColor = hitColor;
                            waveRad = 240;
                            waveLife = 15;
                        }};
                        despawnEffect = new WaveEffect(){{
                            lifetime = 90;
                            sizeFrom = 20;
                            sizeTo = 240;
                            colorFrom = SFColor.tayrDark;
                            strokeFrom = 45;
                        }};
                        shootEffect = new Effect(20f, 200f, e -> {
                            color(hitColor);

                            for(int i = 0; i < 2; i++){
                                color(i == 0 ? SFColor.tayrDark : SFColor.tayrLight);
                                float m = i == 0 ? 1f : 0.5f;

                                for(int j = 0; j < 5; j++){
                                    float rot = e.rotation + Mathf.randomSeedRange(e.id + j, 50f);
                                    float w = 23f * e.fout() * m;
                                    Drawf.tri(e.x, e.y, w, (80f + Mathf.randomSeedRange(e.id + j, 40f)) * m, rot);
                                    Drawf.tri(e.x, e.y, w, 20f * m, rot + 180f);
                                }
                            }
                        });
                        smokeEffect = new Effect(70, e -> {
                            randLenVectors(e.id, e.fin(), 45, 60f, (x, y, fin, fout) -> {
                                color(SFColor.tayrLight);
                                alpha((0.5f - Math.abs(fin - 0.5f)) * 2f);
                                Fill.circle(e.x + x, e.y + y, fout * 5.5f);
                            });
                        });

                        fragBullets = 35;
                        fragLifeMin = 0.3f;
                        fragBullet = new BasicBulletType(2.2f,50,"circle-bullet"){{
                            lifetime = 90;
                            splashDamage = 100;
                            splashDamageRadius = 60;
                            scaledSplashDamage = true;
                            collidesTiles = false;
                            status = SFStatusEffects.scrambled;
                            statusDuration = 60;

                            lightningDamage = 45;
                            lightning = 3;
                            lightningLength = 18;
                            lightningLengthRand = 9;
                            lightningColor = backColor = hitColor = SFColor.tayrDark;
                            frontColor = Color.white;
                            shrinkY = 0;
                            height = width = 16;
                            hitSound = Sounds.plasmaboom;
                            hitShake = 11f;
                            hitEffect = new ExplosionEffect(){{
                                smokes = 6;
                                smokeSize = 30;
                                smokeRad = 133;
                                smokeColor = hitColor.cpy().a(0.1f);
                                sparks = 0;
                                lifetime = 35;
                                waveStroke = 4;
                                waveColor = hitColor;
                                waveRad = 70;
                                waveLife = 12;
                            }};
                            despawnEffect = new Effect(22f, 160f, e -> {
                                color(hitColor);

                                Mathf.rand.setSeed(e.id);
                                for(int i = 0; i < 5; i++){
                                    float angle = Mathf.random(360f);

                                    float lr = Mathf.range(25);
                                    //color(e.color.cpy().mul(0,0,0,lr/25));
                                    Drawf.tri(e.x, e.y, e.foutpow() * 30f, e.fout()*60 + lr, angle);
                                }
                            });
                            /*new Effect(22, e->{
                               color(Color.white, hitColor, e.fin());

                               for(int i = 0; i < 13; i++) {
                                   Lines.stroke(Interp.slope.apply(0,6f,e.fin()));

                                   float len = Interp.slope.apply(0,Mathf.range(60,80),e.fin());
                                   Lines.lineAngle(e.x, e.y, Mathf.randomSeed(e.id,360), len, 1);
                                   Drawf.light(e.x, e.y, len, lightColor, 1.5f);
                               };
                            });*/
                        }};
                    }},
                    SFItems.discFabric, new PowerupBulletType(8,2250,"sfire-mod-arrow-bullet"){{
                        damageUp = 0.5f;
                        maxDamageMultiplier = 7;
                        knockback = 80;
                        status = SFStatusEffects.breakdown;
                        statusDuration = 180;
                        hittable = false;
                        pierceBuilding = true;
                        shieldDamageMultiplier = 15f;
                        ammoMultiplier = 1;
                        lifetime = 33;
                        drag = -0.06f;

                        width = 32;
                        height = 56;
                        shrinkY = 0;
                        frontColor = backColor = trailColor = SFColor.discDark;
                        trailLength = 9;
                        trailWidth = 5.5f;
                        trailInterval = 1f;
                        trailEffect = new WaveEffect(){{
                            interp = Interp.circleOut;
                            lifetime = 15;
                            sizeFrom = 0;
                            sizeTo = 30;
                            strokeFrom = 8;
                            colorTo = colorFrom = trailColor;
                        }};
                        hitSound = Sounds.plasmaboom;
                        hitShake = 15f;
                        hitEffect = Fx.instBomb;
                        despawnEffect = new MultiEffect(Fx.instBomb, new WaveEffect(){{
                            lifetime = 15;
                            strokeFrom = 8;
                            sizeTo = 45;
                            colorTo = colorFrom = trailColor;
                        }});
                        shootEffect = Fx.instShoot;
                        smokeEffect = new Effect(70, e -> {
                            randLenVectors(e.id, e.fin(), 45, 60f, (x, y, fin, fout) -> {
                                color(trailColor);
                                alpha((0.5f - Math.abs(fin - 0.5f)) * 2f);
                                Fill.circle(e.x + x, e.y + y, fout * 5.5f);
                            });
                        });
                    }}
            );
        }};
        poxiao = new PowerTurret("poxiao") {{
            size = 8;
            armor = -10;
            health = 10800;
            recoil = 6;
            recoilTime = 180;
            cooldownTime = 300;
            shootSound = Sounds.laserblast;
            chargeSound = Sounds.lasercharge;
            accurateDelay = false;
            moveWhileCharging = false;
            reloadWhileCharging = false;
            shake = 15f;
            shoot.firstShotDelay = 120;
            warmupMaintainTime = 580;
            heatColor = Color.valueOf("FF4040");
            drawer = new DrawTurret(){{parts.addAll(
                    new RegionPart("-side"){{
                        mirror = true;
                        under = true;
                        moveX = 4;
                        moveRot = -25;
                        heatColor = Color.valueOf("FF4040");
                        moves.add(new PartMove(PartProgress.recoil,0,-2,-5));
                    }},
                    new RegionPart("-blade"){{
                        under = true;
                        y = -8;
                        moveY = 8;
                    }},
                    new RegionPart("-front"){{
                        mirror = true;
                        moveX = 2.75f;
                        moveY = -2.75f;
                        heatProgress = PartProgress.warmup;
                    }}
            );}};
            requirements(Category.turret, with(Items.lead,4000, Items.silicon,2400, Items.plastanium,1700, SFItems.tayrAlloy,1300, SFItems.discFabric,1000, SFItems.lens,1300));

            reload = 600;
            rotateSpeed = 2f;
            range = 1750;
            coolantMultiplier = 0.5f;
            consumePower(260f);
            liquidCapacity = 300;
            coolant = consumeCoolant(2f);

            shootType = new LaserBulletType(3200){{
                length = 1800;
                width = 75f;
                lightningColor = SFColor.tayrDark;
                lightningSpacing = 43.5f;
                lightningDelay = 1.2f;
                lightningLength = 8;
                lightningLengthRand = 4;
                lightningAngleRand = 90;
                lightningDamage = 180;

                sideAngle = 25;
                sideWidth = 2;
                sideLength = 420;
                pierceCap = 18;
                pierceArmor = true;
                buildingDamageMultiplier = 0.001f;
                lifetime = 84;
                colors = new Color[]{SFColor.tayrDark.cpy().a(0.5f), SFColor.tayrDark, SFColor.tayrLight.cpy().mul(1.2f), Color.white};
                shootEffect = Fx.bigShockwave;
                smokeEffect = Fx.smokeCloud;
                hitColor = SFColor.tayrDark;
                hitEffect = new MultiEffect(Fx.hitLaserBlast,
                        new ParticleEffect(){{
                            particles = 14;
                            lifetime = 15;
                            line = true;
                            lenFrom = 0;
                            lenTo = 45;
                            strokeFrom = strokeTo = 3.2f;
                            colorFrom = colorTo = SFColor.tayrLight;
                            sizeInterp = Interp.slope;
                            interp = Interp.circleOut;
                            length = 166;
                            cone = 40;
                        }});
                chargeEffect = new MultiEffect(
                        new WaveEffect(){{
                            lifetime = 40;
                            startDelay = 10;
                            sizeFrom = 180;
                            sizeTo = 0;
                            strokeTo = 6;
                            interp = Interp.circleIn;
                            colorFrom = SFColor.tayrDark;
                            colorTo = SFColor.tayrLight;
                        }},
                        new WaveEffect(){{
                            lifetime = 40;
                            startDelay = 20;
                            sizeFrom = 180;
                            sizeTo = 0;
                            strokeTo = 6;
                            interp = Interp.circleIn;
                            colorFrom = SFColor.tayrDark;
                            colorTo = SFColor.tayrLight;
                        }},
                        new WaveEffect(){{
                            lifetime = 40;
                            startDelay = 30;
                            sizeFrom = 180;
                            sizeTo = 0;
                            strokeTo = 6;
                            interp = Interp.circleIn;
                            colorFrom = SFColor.tayrDark;
                            colorTo = SFColor.tayrLight;
                        }},
                        new WaveEffect(){{
                            lifetime = 40;
                            startDelay = 40;
                            sizeFrom = 180;
                            sizeTo = 0;
                            strokeTo = 6;
                            interp = Interp.circleIn;
                            colorFrom = SFColor.tayrDark;
                            colorTo = SFColor.tayrLight;
                        }},
                        new Effect(120f, 100f, e -> {
                            color(SFColor.tayrLight);
                            stroke(e.fin() * 6f);
                            Lines.circle(e.x, e.y, 8f + e.fout() * 100f);
                            Fill.circle(e.x, e.y, e.fin() * 20);

                            randLenVectors(e.id, 35, 160f * e.fout(), (x, y) -> {
                                Fill.circle(e.x + x, e.y + y, e.fin() * 5f);
                                Drawf.light(e.x + x, e.y + y, e.fin() * 15f, SFColor.tayrLight, 0.7f);
                            });

                            color();
                            Fill.circle(e.x, e.y, e.fin() * 12);
                            Drawf.light(e.x, e.y, e.fin() * 80f, SFColor.tayrDark, 0.7f);
                        }).followParent(true).rotWithParent(true)
                );
            }};
        }};
        fenqing = new PowerTurret("fenqing") {{
            size = 8;
            health = 11000;
            recoil = 8;
            recoilTime = 180f;
            cooldownTime = 360;
            shootSound = Sounds.malignShoot;
            accurateDelay = false;

            shootY = 15f;
            xRand = 16;
            shoot = new ShootMulti(
                    new ShootPattern(){{
                        shots = 4;
                        shotDelay = 3;
                    }},
                    new ShootHelix(4,3),
                    new ShootHelix(4,5){{firstShotDelay = 60;}},
                    new ShootHelix(3,4){{firstShotDelay = 125;}}
            );
            heatColor = Color.valueOf("FF4040");
            warmupMaintainTime = 350;
            minWarmup = 0.88f;
            shootWarmupSpeed = 0.03f;
            drawer = new DrawTurret(){{parts.addAll(
                    new ShapePart(){{
                        y = 22;
                        color = SFColor.tayrDark.cpy().a(0);
                        colorTo = SFColor.tayrLight;
                        circle = true;
                        hollow = false;
                        radius = 99;
                        radiusTo = 10;
                        layer = 110;
                    }},
                    new RegionPart("-mid"){{
                        mirror = true;
                        moveX = 4;
                        moveY = -5f;
                        heatProgress = PartProgress.warmup;
                        heatColor = Color.valueOf("FF7055A8");
                        children.add(new RegionPart("-side"){{
                            mirror = true;
                            under = true;
                            moveX = 2;
                            moveY = 11.5f;
                            heatColor = Color.valueOf("FF7055");
                            moves.add(new PartMove(PartProgress.recoil,0,-3,0));
                        }});
                    }});
                for(int i = 0; i < 3; i++){float f = i;parts.add(
                        new ShapePart(){{
                            y = 22;
                            color = SFColor.tayrDark.cpy().a(0);
                            colorTo = SFColor.tayrLight.cpy().a(0.3f*f+0.3f);
                            hollow = circle = true;
                            stroke = 2.5f + f * 1.5f;
                            radius = 300;
                            radiusTo = 25 + 20 * f;
                            layer = 110;
                        }}
                );}
                for(int i = 0; i < 2; i++){float f = i;parts.add(
                        new HaloPart(){{
                            y = 22;
                            shapeRotation = 180*f;
                            color = SFColor.tayrDark.cpy().a(0);
                            colorTo = SFColor.tayrLight.cpy();
                            hollow = tri = true;
                            stroke = 2 + f * 0.5f;
                            radius = triLength = 0;
                            radiusTo = 16;
                            triLengthTo = 22 + 35*f;
                            layer = 110;
                            haloRadius = 300;
                            haloRadiusTo = 67;
                            haloRotateSpeed = -1.2f;
                        }}
                );}
            }};
            requirements(Category.turret, with(SFItems.fermium,1800, SFItems.siliSteel,1800, SFItems.nanoCore,2200, SFItems.tayrAlloy,2000, SFItems.discFabric,1500));

            reload = 230;
            rotateSpeed = 1.3f;
            range = 680;
            inaccuracy = 5;
            shake = 8f;
            consumePower(360f);
            liquidCapacity = 300;
            coolantMultiplier = 0.5f;
            consumeCoolant(3.5f);

            velocityRnd = 0.12f;
            shootType = new EmpBulletType() {{
                lifetime = 50;
                accel = 0.168f;
                speed = 9;

                timeIncrease = 1;
                unitDamageScl = 1.2f;

                ammoMultiplier = 1;
                status = SFStatusEffects.scrambled;
                statusDuration = 30;
                damage = splashDamage = 50;
                splashDamageRadius = radius = 65;

                frontColor = Color.white;
                trailColor = backColor = hitColor = SFColor.tayrLight;
                trailLength = 15;
                trailWidth = 4;
                shrinkY = 0;
                sprite = "circle-bullet";
                width = height = 12;
                hitSound = Sounds.plasmaboom;
                float rad = splashDamageRadius;
                hitPowerEffect = new Effect(40, e -> {
                    color(hitColor);
                    stroke(e.fout() * 1.6f);

                    randLenVectors(e.id, 18, e.finpow() * 27f, e.rotation, 60f, (x, y) -> {
                        float ang = Mathf.angle(x, y);
                        lineAngle(e.x + x, e.y + y, ang, e.fout() * 6 + 1f);
                    });
                });
                hitEffect = new Effect(15,100,e ->{
                    e.scaled(7,b->{
                        color(hitColor, b.fout());
                        Fill.circle(e.x, e.y, rad);
                    });

                    color(hitColor);
                    stroke(e.fout() * 3f);
                    Lines.circle(e.x, e.y, rad);

                    int points = 16;
                    float offset = Mathf.randomSeed(e.id, 360f);
                    for(int i = 0; i < points; i++){
                        float angle = i * 360f / points + offset;
                        Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle);
                    }

                    Drawf.light(e.x, e.y, rad * 1.6f, hitColor, e.fout());
                });
                shootEffect = Fx.shootBigColor;
                smokeEffect = despawnEffect = Fx.none;

                intervalDelay = 30;
                bulletInterval = 6;
                intervalBullets = 1;
                intervalAngle = intervalRandomSpread = 0;
                intervalBullet = new BasicBulletType(0.5f,15,"circle-bullet"){{
                    lifetime = 45;
                    drag = -0.08f;
                    weaveScale = 7;
                    weaveMag = 3;
                    status = StatusEffects.disarmed;
                    statusDuration = 1;
                    pierce = true;
                    pierceCap = 2;

                    homingDelay = 10;
                    homingPower = 0.8f;
                    homingRange = 160f;

                    frontColor = Color.white;
                    trailColor = backColor = hitColor = SFColor.tayrLight;
                    trailLength = 8;
                    trailWidth = 4f;
                    shrinkY = 0;
                    width = height = 9;
                    hitShake = 2;
                    despawnEffect = hitEffect = Fx.hitLaserBlast;
                }};
            }};
        }};
        zhulin = new ItemTurret("zhulin") {{
            size = 8;
            health = 15000;
            recoil = 6;
            recoilTime = 165;
            shootSound = Sounds.plasmadrop;
            shake = 4f;
            cooldownTime = 300;
            minWarmup = 0.8f;
            warmupMaintainTime = 60f;
            shoot.shots = 160;
            shoot.shotDelay = 0.75f;
            xRand = 20;
            heatColor = Color.valueOf("FF7055");
            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-side") {{
                    mirror = true;
                    moveX = 3f;
                    heatColor = Color.valueOf("FF7055");
                }});
                for(int i = 0; i < 3; i++) {
                    int f = i;
                    parts.add(new HaloPart(){{
                        y = 40;
                        moveY = f * 45;
                        sides = 3;
                        shapes = 1;
                        color = Color.white;
                        colorTo = SFColor.tayrDark;
                        tri = hollow = true;
                        radius = 0;
                        radiusTo = 16;
                        triLength = 500;
                        triLengthTo = 16;
                        layer = 51;
                        haloRadius = 0;
                    }});
                }
                for(int i = 1; i < 3; i++) {
                    int f = i;
                    parts.add(
                            new HaloPart() {{
                                x = -24 + 48*(f-1);
                                y = 33;
                                sides = 3;
                                shapes = 1;
                                shapeRotation = 180;
                                color = Color.white;
                                colorTo = SFColor.tayrDark;
                                tri = hollow = true;
                                radius = 0;
                                radiusTo = 5;
                                triLength = 100;
                                triLengthTo = 18;
                                haloRotation = -20 + 40*(f-1);
                                haloRadius = 0;
                                layer = 110;
                            }},
                            new HaloPart() {{
                                x = -24 + 48*(f-1);
                                y = 32;
                                sides = 3;
                                shapes = 1;
                                color = Color.white;
                                colorTo = SFColor.tayrDark;
                                tri = hollow = true;
                                radius = 0;
                                radiusTo = 5;
                                triLength = 100;
                                triLengthTo = 160;
                                haloRadius = 0;
                                layer = 110;
                            }});
                }
            }};
            requirements(Category.turret, with(Items.titanium,3500, Items.silicon,1500, SFItems.fermium,1450, SFItems.tayrAlloy,1500, SFItems.discFabric,2000, SFItems.lens,800));
            consumePower(300);
            consumeLiquid(Liquids.cryofluid,3);
            unitSort = UnitSorts.farthest;

            reload = 560;
            rotateSpeed = 0.65f;
            range = 800;
            inaccuracy = 0.5f;
            liquidCapacity = 300;
            consumeAmmoOnce = false;
            maxAmmo = 160;
            shootCone = 6;
            velocityRnd = 0.05f;
            ammo(
                    Items.blastCompound, new BasicBulletType(8, 36) {{
                        splashDamage = 150;
                        splashDamageRadius = 50;
                        hitSize = 40;
                        lifetime = 780/8f;
                        status = StatusEffects.blasted;
                        knockback = 28f;
                        reloadMultiplier = 1.25f;
                        ammoMultiplier = 4;

                        width = 18;
                        height = 23;
                        hitColor = frontColor = backColor = trailColor = Pal.blastAmmoBack;
                        trailWidth = 5;
                        trailLength = 9;
                        hitSound = Sounds.explosion;
                        hitSoundVolume = 5;
                        hitShake = 5;
                        shootEffect = Fx.shootBigColor;
                        smokeEffect = new ParticleEffect(){{
                            particles = 3;
                            sizeFrom = 5;
                            length = 45;
                            lifetime = 25;
                            colorFrom = frontColor;
                            cone = 60;
                        }};
                        hitEffect = new ExplosionEffect(){{
                            smokes = 8;
                            smokeRad = splashDamageRadius * 0.7f;
                            smokeSize = 8;
                            smokeColor = hitColor;
                            sparks = 15;
                            sparkRad = splashDamageRadius + 35;
                            sparkLen = 16;
                            sparkStroke = 1.5f;
                            sparkColor = Pal.bulletYellowBack;
                            lifetime = 35;
                            waveColor = hitColor;
                            waveRad = splashDamageRadius + 11;
                            waveStroke = 6;
                            waveLife = 15;
                        }};
                        despawnEffect = Fx.bigShockwave;
                    }},
                    SFItems.clusBomb, new BasicBulletType(6.88f, 36) {{
                        splashDamage = 50;
                        splashDamageRadius = 36;
                        hitSize = 30;
                        lifetime = 700/6.88f;
                        status = StatusEffects.blasted;
                        ammoMultiplier = 6;

                        width = 19;
                        height = 28;
                        hitColor = backColor = trailColor = SFColor.clusRedDark;
                        frontColor = SFColor.clusRed;
                        trailWidth = 5;
                        trailLength = 12;
                        hitSound = Sounds.explosionbig;
                        hitSoundVolume = 2;
                        hitShake = 5;
                        shootEffect = Fx.shootBigColor;
                        smokeEffect = new ParticleEffect(){{
                            particles = 3;
                            sizeFrom = 5;
                            length = 45;
                            lifetime = 25;
                            colorFrom = frontColor;
                            cone = 60;
                        }};
                        hitEffect = new ExplosionEffect(){{
                            smokes = 0;
                            sparks = 15;
                            sparkRad = splashDamageRadius + 35;
                            sparkLen = 16;
                            sparkStroke = 1.5f;
                            sparkColor = Pal.bulletYellowBack;
                            lifetime = 35;
                            waveColor = hitColor;
                            waveRad = splashDamageRadius + 11;
                            waveStroke = 6;
                            waveLife = 15;
                        }};
                        despawnEffect = Fx.bigShockwave;
                        fragRandomSpread = 180;
                        fragBullets = 4;
                        fragLifeMin = 0.3f;
                        fragBullet = new BasicBulletType(3,30){{
                            splashDamage = 180;
                            splashDamageRadius = 75;
                            scaledSplashDamage = true;
                            lifetime = 40f;
                            drag = 0.005f;
                            status = StatusEffects.blasted;
                            knockback = 0.7f;

                            width = 9;
                            height = 10;
                            hitColor = backColor = trailColor = SFColor.clusRedDark;
                            frontColor = SFColor.clusRed;

                            hitSound = Sounds.explosion;
                            hitSoundVolume = 5;
                            hitShake = 5;
                            hitEffect = new ExplosionEffect(){{
                                smokes = 6;
                                smokeRad = splashDamageRadius * 0.7f;
                                smokeSize = 12;
                                smokeColor = hitColor.cpy().a(0.3f);
                                sparks = 18;
                                sparkRad = splashDamageRadius + 35;
                                sparkLen = 16;
                                sparkStroke = 1.5f;
                                sparkColor = Pal.bulletYellowBack;
                                lifetime = 35;
                                waveColor = hitColor;
                                waveRad = splashDamageRadius + 11;
                                waveStroke = 6;
                                waveLife = 15;
                            }};
                            despawnEffect = Fx.flakExplosionBig;
                        }};
                    }},
                    Items.surgeAlloy, new BasicBulletType(15, 115) {{
                        splashDamage = 86;
                        splashDamageRadius = 45;
                        lightningDamage = 50;
                        lightning = 2;
                        lightningLength = 14;
                        lightningLengthRand = 10;

                        buildingDamageMultiplier = 0.3f;
                        pierce = pierceBuilding = true;
                        pierceCap = 2;
                        lifetime = 780/15f;
                        ammoMultiplier = 2;

                        width = 18;
                        height = 28;
                        hitColor = frontColor = backColor = trailColor = Pal.surgeAmmoBack;
                        trailWidth = 4;
                        trailLength = 9;
                        hitSound = Sounds.plasmaboom;
                        hitSoundVolume = 0.4f;
                        hitShake = 4;
                        shootEffect = Fx.shootBigColor;
                        smokeEffect = Fx.smokeCloud;
                        hitEffect = new MultiEffect(
                                new Effect(26f, 160f, e -> {
                                    color(hitColor);

                                    Mathf.rand.setSeed(e.id);
                                    for(int i = 0; i < 6; i++){
                                        float angle = Mathf.random(360f);

                                        float lr = Mathf.range(30);
                                        Drawf.tri(e.x, e.y, e.foutpow() * 18f, e.fout()*40 + lr, angle);
                                    }
                                }),
                                new WaveEffect(){{
                                  lifetime = 10;
                                  strokeFrom = 4;
                                  sizeTo = splashDamageRadius + 16;
                                  colorFrom = colorTo = hitColor;
                                }}
                        );
                        despawnEffect = Fx.bigShockwave;
                    }},
                    SFItems.discFabric, new BasicBulletType(3, 55, "sfire-mod-arrow-bullet") {{
                        splashDamage = 95;
                        splashDamageRadius = 75;
                        shieldDamageMultiplier = 5f;
                        drag = -0.05f;
                        lifetime = 54;
                        rangeChange = 73;
                        absorbable = hittable = reflectable = false;
                        pierce = true;
                        pierceCap = 2;

                        status = SFStatusEffects.breakdown;
                        statusDuration = 180;
                        ammoMultiplier = 2;

                        width = 12;
                        height = 35;
                        hitColor = frontColor = backColor = trailColor = SFColor.discLight;
                        trailWidth = 2;
                        trailLength = 6;
                        hitSound = Sounds.plasmaboom;
                        hitShake = 4;
                        shootEffect = Fx.shootBigColor;
                        smokeEffect = new ParticleEffect(){{
                            particles = 3;
                            sizeFrom = 5;
                            length = 45;
                            lifetime = 25;
                            colorFrom = frontColor;
                            cone = 60;
                        }};
                        hitEffect = new ExplosionEffect(){{
                            smokes = 0;
                            sparks = 6;
                            sparkRad = splashDamageRadius + 35;
                            sparkLen = 26;
                            sparkStroke = 1.5f;
                            sparkColor = Pal.bulletYellowBack;
                            lifetime = 35;
                            waveColor = hitColor;
                            waveRad = splashDamageRadius + 11;
                            waveStroke = 6;
                            waveLife = 15;
                        }};
                        despawnEffect = Fx.none;
                        fragBullets = 3;
                        fragRandomSpread = 45;
                        fragBullet = new PowerupBulletType(3,65,"sfire-mod-arrow-bullet"){{
                            splashDamage = 30;
                            splashDamageRadius = 75;
                            shieldDamageMultiplier = 5f;
                            lifetime = 30;
                            accel = 0.05f;
                            absorbable = hittable = reflectable = false;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 180;

                            width = 9;
                            height = 16;
                            hitColor = frontColor = backColor = trailColor = SFColor.discLight;
                            trailWidth = 2;
                            trailLength = 6;
                            hitSound = Sounds.laser;
                            hitShake = 4;
                            despawnEffect = new ParticleEffect(){{
                                sizeFrom = 8;
                                sizeTo = 0;
                                sizeInterp = Interp.elasticOut;
                                length = 0;
                                colorFrom = colorTo = SFColor.discDark;
                            }};
                            hitEffect = Fx.circleColorSpark;
                        }};
                    }}
            );
        }};
        luolong = new ItemTurret("luolong") {{
            size = 8;
            health = 28000;
            armor = 16;
            recoil = 13;
            recoilTime = 180;
            shootSound = Sounds.railgun;
            soundPitchMax = 1.2f;
            soundPitchMin = 0.8f;
            shake = 10f;
            cooldownTime = 180;
            minWarmup = 0.8f;
            warmupMaintainTime = 180f;
            shoot.firstShotDelay = 60f;
            heatColor = Color.valueOf("D85555A0");
            drawer = new DrawTurret(){{parts.addAll(
                    new RegionPart("-bottom"){{
                        layerOffset = heatLayerOffset = -0.001f;
                        under = true;
                        heatProgress = PartProgress.warmup;
                        heatColor = Color.valueOf("D85555A0");
                        progress = PartProgress.recoil;
                        moveY = 13;
                    }},
                    new RegionPart("-barrel"){{
                        mirror = true;
                        under = true;
                        x = 9;
                        y = 18.5f;
                        rotation = 180;
                        moveX = -1f;
                        moveY = 5.5f;
                        moveRot = 180;
                        moves.add(new PartMove(PartProgress.recoil,0,-8,0));
                    }}
            );
                for (int i = 0; i < 7; i++) {
                    for (int r = 0; r < 2; r++){
                        int f = i; int t = r;
                    parts.add(new HaloPart() {{
                        haloRadius = 40;
                        haloRadiusTo = 40 + f * 56;
                        shapes = 1;
                        tri = true;
                        hollow = false;
                        color = SFColor.leipLight;
                        layer = 110;
                        radius = radiusTo = 18;
                        triLength = 0;
                        triLengthTo = 24 - t * 18;
                        shapeRotation = t * 180;
                        progress = PartProgress.charge.curve(Interp.pow5Out);
                    }});
                }}
            }};
            requirements(Category.turret, with(Items.graphite,3600, Items.silicon,2000, Items.surgeAlloy,1300, SFItems.siliSteel,2000, SFItems.discFabric,2000, SFItems.leipAlloy,1000));
            consumePower(360);
            consumeLiquid(SFLiquids.nanoFluid,3.75f);
            unitSort = UnitSorts.strongest;

            reload = 420;
            rotateSpeed = 1.85f;
            range = 448 + 652f;
            inaccuracy = 0.63f;
            liquidCapacity = 300;
            ammoPerShot = 15;
            ammo(SFItems.leipAlloy, new PowerupBulletType(40,7500,"missile-large"){{
                lifetime = 29;
                drag = 0.007f;
                hitSize = 36f;
                pierce = true;
                knockback = 162f;
                hitShake = 15;

                damageUp = 1;
                maxDamageMultiplier = 2;

                height = 43;
                width = 23;
                shrinkY = 0;
                frontColor = trailColor = backColor = hitColor = SFColor.leipLight;
                trailLength = 22;
                trailWidth = 8;
                trailInterval = 1;
                trailRotation = true;
                trailEffect = new Effect(15, e -> {
                    color(hitColor);
                    for (int s : new int[]{1, -1}) {
                        Drawf.tri(e.x, e.y, 20f * e.fout(Interp.slope), 60f * e.fout(Interp.slope), e.rotation + s * 135);
                    }
                });
                shootEffect = new Effect(25, e -> {
                    color(hitColor);
                    float w = 20 * e.fout();
                    Drawf.tri(e.x, e.y, w, 15*8f * e.fout(), e.rotation);
                    Drawf.tri(e.x, e.y, w, 26f * e.fout(), e.rotation + 180f);
                });
                smokeEffect = new ParticleEffect(){{
                    particles = 17;
                    sizeFrom = 11;
                    interp = Interp.pow10Out;
                    sizeInterp = Interp.pow5In;
                    length = 128;
                    lifetime = 120;
                    colorTo = trailColor;
                    cone = 14;
                }};
                hitEffect = new MultiEffect(
                        new ParticleEffect(){{
                            particles = 36;
                            lifetime = 42;
                            line = true;
                            interp = Interp.fastSlow;
                            sizeInterp = Interp.slope;
                            strokeTo = 2;
                            lenFrom = 0;
                            lenTo = 15;
                            length = -110;
                            colorTo = Pal.bulletYellowBack;
                            cone = 90;
                        }},
                        new Effect(20f, 200f, e -> {
                            color(hitColor);
                            randLenVectors(e.id, 25, 5f + e.fin() * 80f, e.rotation, 60f, (x, y) -> {
                                Fill.square(e.x + x, e.y + y, e.fout() * 22f, 45f);
                            });
                        })
                );
                hitSound = Sounds.shootSmite;
                hitSoundPitch = 0.8f;
                hitSoundVolume = 3;
                despawnSound = Sounds.explosion;
                despawnEffect = new MultiEffect(
                        new Effect(70, e -> {
                            randLenVectors(e.id, e.fin(Interp.pow5In), 30, 30f, (x, y, fin, fout) -> {
                                color(Color.gray);
                                alpha((0.5f - Math.abs(fin - 0.5f)) * 2f);
                                Fill.circle(e.x + x, e.y + y, fout * 18f);
                            });
                        }),
                        new WaveEffect(){{
                            lifetime = 10;
                            sizeTo = 80;
                            strokeFrom = 30;
                            strokeTo = 10;
                            colorFrom = Color.white.cpy().a(0.5f);
                            colorTo = Color.white.cpy().a(0);
                        }}
                );
                spawnBullets.add(
                        new ShrapnelBulletType() {{
                            serrations = 11;
                            serrationLenScl = 16;
                            serrationWidth = 7;
                            serrationSpacing = 10;
                            serrationSpaceOffset = 110;
                            length = 224;
                            damage = 7500;
                            width = 20;
                            fromColor = SFColor.leipDark;
                            toColor = SFColor.leipLight;
                            laserAbsorb = false;
                            hitSound = Sounds.laser;
                        }}
                );
            }});
        }};
    //dp6-8//
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
                lightningColor = frontColor = backColor = SFColor.discLight;

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
                    colorFrom = SFColor.discLight.cpy().a(0.5f);
                    colorTo = SFColor.discLight;
                }};
                despawnEffect = Fx.instBomb;
                hitSound = Sounds.explosionbig;
                hitSoundVolume = 0.6f;
                hitEffect = new ExplosionEffect(){{
                    lifetime = 30;
                    waveLife = 15;
                    waveStroke = 8;
                    waveRad = 66;
                    waveColor = sparkColor = SFColor.discLight;
                    smokeColor = SFColor.discLight.cpy().a(0.45f);
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
            shootType = new BasicBulletType(4,2610,"sfire-mod-missile1"){{
                drag = -0.01f;
                shrinkY = 0;
                width = 20;
                height = 72;
                lifetime = 90;
                ammoMultiplier = 1;
                makeFire = scaledSplashDamage = true;
                splashDamageRadius = 80;
                splashDamage = 550;
                status = StatusEffects.blasted;
                homingDelay = 15;
                homingRange = 100;
                homingPower = 0.042f;
                reflectable = collidesAir = absorbable = hittable = false;
                collideFloor = true;
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
                        new WaveEffect(){{
                            lifetime = 42f;
                            startDelay = 2;
                            sizeTo = 53f;
                            strokeFrom = 3;
                            colorFrom = Color.valueOf("596ab8");
                            colorTo = Color.valueOf("596ab8A8");
                            layer = 49.9f;
                        }},/*
                        new Effect(90,e ->{
                            Floor floor = tileOn() == null ? Blocks.air.asFloor() : tileOn().floor();
                            Color color = Tmp.c1.set(floor.mapColor.equals(Color.black) ? Blocks.water.mapColor : floor.mapColor).mul(1.5f);
                        })*/

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
                            moves.add(new PartMove(PartProgress.recoil, 0,-10,0));
                        }}
                );
            }};
            shootWarmupSpeed = 0.08f;
            warmupMaintainTime = 600;
            minWarmup = 0.9f;
            accurateDelay = false;
            recoilTime = 150;
            recoil = 0;
            shootY = 8;
            shootSound = Sounds.missileLaunch;
            shootCone = 30;
            shake = 8;
            rotateSpeed = 1.25f;
            reload = 750;
            range = 940;
            shootEffect = new ParticleEffect(){{
                particles = 32;
                interp = Interp.fastSlow;
                sizeFrom = 12;
                length = 125;
                lifetime = 123;
                colorFrom = colorTo = SFColor.discLight;
                cone = 40;
            }};
            smokeEffect = new ParticleEffect(){{
                particles = 25;
                interp = Interp.fastSlow;
                sizeInterp = Interp.pow3In;
                sizeFrom = 9;
                length = -88;
                lifetime = 65;
                colorFrom = SFColor.discLight;
                colorTo = SFColor.discLight.cpy().a(0.5f);
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
                    engineColor = trailColor = SFColor.discLight;
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
                            colorFrom = colorTo = SFColor.discLight.cpy().a(0.8f);
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
                            splashDamage = 12700*0.5f;
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
                                        colorFrom = SFColor.discLight;
                                        colorTo = SFColor.discDark.cpy().a(0.5f);
                                    }},
                                    new WaveEffect() {{
                                        interp = Interp.circleOut;
                                        startDelay = 10;
                                        lifetime = 20;
                                        sizeTo = 300;
                                        strokeFrom = 22;
                                        strokeTo = 10;
                                        colorFrom = SFColor.discLight;
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
                                        colorTo = colorFrom = SFColor.discLight;
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
                                        colorFrom = SFColor.discLight;
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
                                colorFrom = SFColor.discLight;
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
                trailEffect = new ParticleEffect(){{
                    particles = 1;
                    line = true;
                    randLength = false;
                    sizeInterp = Interp.slope;
                    lenFrom = lenTo = 13;
                    strokeTo = 15;
                    length = baseLength = 0.1f;
                    lifetime = 62;
                    colorFrom = colorTo = SFColor.energyGreen;
                    cone = 0;
                }};
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
        //defensePlatform6 = new ItemTurret("defense-platform-missile"){{}};
        //endregion
        //region unit
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
            liquidCapacity = 120;
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
                    new UnitPlan(SFUnitTypes.tank3, 60f * 42, T3Item),
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
            requirements(Category.units, with(Items.metaglass,300, Items.plastanium,400,Items.surgeAlloy,300,SFItems.nanoCore,400));
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
        //endregion

    }
}