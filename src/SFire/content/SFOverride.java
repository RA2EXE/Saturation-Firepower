package SFire.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.effect.WrapEffect;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Pump;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static mindustry.type.ItemStack.with;

public class SFOverride {
    public static void load() {

        //turrets
        ((ItemTurret) Blocks.scatter).ammoTypes.putAll(
                Items.blastCompound, new FlakBulletType(4, 5){{
                    lifetime = 60;
                    ammoMultiplier = 6;
                    width = 6;
                    height = 8;
                    splashDamage = 45;
                    splashDamageRadius = 35;
                    status = StatusEffects.blasted;
                    backColor = Pal.blastAmmoBack;
                    frontColor = Pal.blastAmmoFront;
                    hitEffect = Fx.flakExplosion;
                }},
                SFItems.siliSteel, new FlakBulletType(6,3){{
                    lifetime = 40f;
                    ammoMultiplier = 4;
                    width = 6;
                    height = 8;
                    splashDamage = 23;
                    splashDamageRadius = 20;
                    status = SFStatusEffects.magnStrif;
                    statusDuration = 32;
                    backColor = SFColor.sisteelDark;
                    frontColor = SFColor.sisteelLight;
                    hitEffect = Fx.flakExplosion;
                }}
        );
        ((ItemTurret) Blocks.salvo).ammoTypes.put(Items.blastCompound, new BasicBulletType(5,16){{
            lifetime = 44.4f;
            rangeChange = 4f * 8f;
            splashDamage = 35;
            splashDamageRadius = 30 * 0.75f;
            status = StatusEffects.blasted;
            ammoMultiplier = 6;
            knockback = 3;
            frontColor = hitColor = Pal.blastAmmoFront;
            backColor = Pal.blastAmmoBack;
            hitEffect = Fx.blastExplosion;
            width = 10;
            height = 13;
        }});
        ((LiquidTurret) Blocks.wave).ammoTypes.putAll(
                SFLiquids.nanoFluid, new LiquidBulletType(SFLiquids.nanoFluid){{
                    lifetime = 32;
                    drag = 0.01f;
                    statusDuration = 120;
                    status = SFStatusEffects.scrambled;
                    healAmount = 10;
                    collidesTeam = true;
                }},
                SFLiquids.nitrate, new LiquidBulletType(SFLiquids.nitrate){{
                    lifetime = 32;
                    drag = 0.01f;
                    damage = 3.1f;
                    knockback = 0.5f;
                    statusDuration = 240;
                    status = SFStatusEffects.acidded;
                    layer = 98;
                }}
        );
        ((LiquidTurret) Blocks.tsunami).ammoTypes.putAll(
                SFLiquids.nanoFluid, new LiquidBulletType(SFLiquids.nanoFluid){{
                    lifetime = 49f;
                    speed = 4f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    drag = 0.001f;

                    damage = 0.3f;
                    knockback = 0.3f;
                    statusDuration = 240;
                    status = SFStatusEffects.scrambled;
                    healAmount = 10;
                    collidesTeam = true;
                    ammoMultiplier = 0.4f;
                }},
                SFLiquids.nitrate, new LiquidBulletType(SFLiquids.nitrate){{
                    lifetime = 49f;
                    speed = 4f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    drag = 0.001f;

                    damage = 4.4f;
                    knockback = 1.5f;
                    statusDuration = 240;
                    status = SFStatusEffects.acidded;
                    layer = 98;
                }},
                SFLiquids.blastReagent, new LiquidBulletType(SFLiquids.blastReagent){{
                    lifetime = 49f;
                    speed = 4f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    drag = 0.001f;

                    damage = 3f;
                    splashDamage = 16;
                    splashDamageRadius = 8;
                    hitEffect = despawnEffect = Fx.blastExplosion;
                    hitColor = SFLiquids.blastReagent.color;
                    knockback = 1.3f;
                    statusDuration = 240;
                    status = StatusEffects.blasted;
                    layer = 98;
                }}
        );
        ((ItemTurret) Blocks.ripple).shoot.shots = 5;
        ((ItemTurret) Blocks.ripple).ammoTypes.putAll(
                SFItems.siliSteel, new ArtilleryBulletType(3,15){{
                    knockback = 0.8f;
                    lifetime = 80f;
                    width = height = 12f;
                    collidesTiles = false;
                    splashDamageRadius = 25f * 0.75f;
                    splashDamage = 33f;
                    ammoMultiplier = 3f;
                    status = SFStatusEffects.magnStrif;
                    statusDuration = 10;

                    despawnEffect = Fx.hitBulletColor;
                    backColor = SFColor.sisteelDark;
                    frontColor = hitColor = trailColor = SFColor.sisteelLight;
                }},
                SFItems.clusBomb, new ArtilleryBulletType(3,15){{
                    knockback = 1.8f;
                    inaccuracy = 3;
                    lifetime = 80f;
                    splashDamage = 40;
                    splashDamageRadius = 20;
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
                        splashDamage = 35;
                        splashDamageRadius = 20;
                        scaledSplashDamage = true;
                        width = height = 9;
                        hitEffect = Fx.blastExplosion;
                        status = StatusEffects.blasted;
                        backColor = trailColor = SFColor.clusRedDark;
                        frontColor = SFColor.clusRed;
                    }};
                }}
        );
        Blocks.swarmer.requirements(Category.turret, ItemStack.with( Items.plastanium,45, Items.silicon,70, Items.thorium,60));
        ((ItemTurret) Blocks.swarmer).reload = 32;
        ((ItemTurret) Blocks.swarmer).range = 260;
        ((ItemTurret) Blocks.swarmer).shoot = new ShootBarrel(){{
            barrels = new float[]{
                    -4, -1.25f, 0,
                    0, 0, 0,
                    4, -1.25f, 0
            };
            shots = 6;
            shotDelay = 2.5f;
        }};
        ((ItemTurret) Blocks.swarmer).ammoTypes.get(Items.blastCompound).lifetime = 70.27f;
        ((ItemTurret) Blocks.swarmer).ammoTypes.get(Items.blastCompound).splashDamage = 50;
        ((ItemTurret) Blocks.swarmer).ammoTypes.get(Items.pyratite).lifetime = 70.27f;
        ((ItemTurret) Blocks.swarmer).ammoTypes.get(Items.surgeAlloy).lifetime = 70.27f;
        ((ItemTurret) Blocks.swarmer).ammoTypes.putAll(
                SFItems.siliSteel, new MissileBulletType(4.1f, 5){{
                    lifetime = 63.41f;
                    width = height = 7f;
                    shrinkY = 0f;
                    splashDamageRadius = 32f;
                    splashDamage = 19;
                    ammoMultiplier = 5f;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;

                    status = SFStatusEffects.magnStrif;
                    statusDuration = 12f;

                    hitColor = backColor = trailColor = SFColor.sisteelDark;
                    frontColor = SFColor.sisteelLight;
                }},
                SFItems.clusBomb, new MissileBulletType(3, 8){{
                    lifetime = 100;
                    width = height = 9f;
                    rangeChange = 44;
                    shrinkY = 0f;
                    splashDamageRadius = 32f;
                    splashDamage = 44;
                    ammoMultiplier = 5f;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;

                    status = StatusEffects.blasted;
                    statusDuration = 60f;

                    hitColor = backColor = trailColor = SFColor.clusRedDark;
                    frontColor = SFColor.clusRed;
                    fragLifeMin = 0.1f;
                    fragBullets = 3;
                    fragBullet = new ArtilleryBulletType(1.5f, 8) {{
                        lifetime = 20;
                        splashDamage = 35;
                        splashDamageRadius = 20;
                        scaledSplashDamage = true;
                        width = height = 9;
                        hitEffect = Fx.blastExplosion;
                        status = StatusEffects.blasted;
                        backColor = trailColor = SFColor.clusRedDark;
                        frontColor = SFColor.clusRed;
                    }};
                }}
        );

        ((ItemTurret) Blocks.fuse).reload = 40;
        ((ItemTurret) Blocks.fuse).range = 100;
        ((ItemTurret) Blocks.fuse).shoot = new ShootSpread(4,12.5f);
        Blocks.fuse.health = 2230;
        ((ItemTurret) Blocks.fuse).ammoTypes.get(Items.titanium).damage = 88;
        ((ItemTurret) Blocks.fuse).ammoTypes.get(Items.thorium).damage = 135.2f;
        ((ItemTurret) Blocks.fuse).ammoTypes.get(Items.thorium).rangeChange = 10;
        ((ItemTurret) Blocks.fuse).ammoTypes.put(SFItems.discFabric, new ShrapnelBulletType() {{
            inaccuracy = 3;
            length = 125;
            rangeChange = 20;
            damage = 130;
            status = SFStatusEffects.breakdown;
            statusDuration = 44;
            ammoMultiplier = 4f;
            fromColor = SFColor.discLight.cpy().a(0.5f);
            toColor = SFColor.discLight;
            shootEffect = smokeEffect = new Effect(12f, e -> {
                color(Color.white, SFColor.discLight, e.fin());
                stroke(e.fout() * 1.2f + 0.5f);

                randLenVectors(e.id, 7, 25f * e.finpow(), e.rotation, 50f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
                });
            });
            reloadMultiplier = 0.9f;
            spawnBullets.add(new ShrapnelBulletType() {{
                length = 125;
                rangeChange = 10;
                damage = 130;
                ammoMultiplier = 4f;
                fromColor = SFColor.discLight.cpy().a(0.5f);
                toColor = SFColor.discLight;
                status = SFStatusEffects.breakdown;
                statusDuration = 44;
            }});
        }});

        ((ItemTurret) Blocks.spectre).reload = 8.28f;
        ((ItemTurret) Blocks.spectre).range = 280;
        ((ItemTurret) Blocks.spectre).coolantMultiplier = 1.5f;
        ((ItemTurret) Blocks.spectre).inaccuracy = 3;
        ((ItemTurret) Blocks.spectre).maxAmmo = 60;
        var yl1 = ((ItemTurret) Blocks.spectre).ammoTypes.get(Items.graphite);
        var yl2 = ((ItemTurret) Blocks.spectre).ammoTypes.get(Items.pyratite);
        yl1.damage = 50*1.25f;
        yl1.speed = 9;
        yl1.lifetime = 31.1f;
        yl2.damage = 70f;
        yl2.speed = 10;
        yl2.lifetime = 26f;
        yl2.splashDamage = 38.2f;
        yl2.splashDamageRadius = 28f;
        ((ItemTurret) Blocks.spectre).ammoTypes.putAll(
                Items.thorium, new BasicBulletType(10f, 80*1.25f){{
                    lifetime = 28;
                    hitSize = 5;
                    width = 16f;
                    height = 23f;
                    shootEffect = Fx.shootBig;
                    pierceCap = 3;
                    pierceArmor = true;
                    pierceBuilding = true;
                    knockback = 0.7f;

                    backColor = hitColor = Pal.thoriumAmmoBack;
                    frontColor = Pal.thoriumAmmoFront;
                }},
                SFItems.siliSteel, new BasicBulletType(9,70){{
                    lifetime = 31.1f;
                    hitSize = 5;
                    width = 15f;
                    height = 21f;
                    knockback = 0.2f;
                    status = SFStatusEffects.magnStrif;
                    statusDuration = 12;
                    shootEffect = Fx.shootBig;
                    hitEffect = Fx.hitBulletBig;
                    despawnEffect = Fx.hitBulletColor;
                    frontColor = hitColor = SFColor.sisteelLight;
                    backColor = SFColor.sisteelDark;
                    ammoMultiplier = 3;
                }}
        );

        ((PowerTurret) Blocks.meltdown).range = 216;
        ((PowerTurret) Blocks.meltdown).reload = 240;
        ((PowerTurret) Blocks.meltdown).shootType = new ContinuousLaserBulletType(86){{
            length = 216;
            hitEffect = Fx.hitMeltdown;
            hitColor = Pal.meltdownHit;
            status = StatusEffects.melting;
            drawSize = 500f;
            timescaleDamage = true;
            pierceArmor = true;
            hitSize = 7.5f;

            incendChance = 0.4f;
            incendSpread = 5f;
            incendAmount = 1;
            ammoMultiplier = 1f;
            bulletInterval = 8;
            intervalBullets = 1;
            intervalRandomSpread = 0;
            intervalBullet = new LaserBulletType(33){{
                hitEffect = Fx.hitMeltdown;
                lightningSpacing = 21.6f;
                lightningDelay = 1;
                lightningLength = 4;
                lightningLengthRand = 2;
                lightningDamage = 33;
                lightningColor = Color.valueOf("D86E56");
                pierceCap = 6;
                pierceArmor = true;
                knockback = 2;
                sideAngle = 20;
                sideWidth = 1;
                sideLength = 35;
                colors = new Color[]{Color.valueOf("ec745855"), Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                status = SFStatusEffects.postive;
                statusDuration = 12;
                lifetime = 10;
                length = 216;
                width = 16;
            }};
        }};




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

        //power
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
