package SFire.content;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.math.*;
import arc.math.geom.Rect;
import arc.struct.ObjectSet;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.AssemblerAI;
import mindustry.ai.types.BuilderAI;
import mindustry.ai.types.DefenderAI;
import mindustry.ai.types.SuicideAI;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.PointDefenseWeapon;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.*;

import static SFire.SFireMod.name;
import static arc.graphics.g2d.Draw.color;

public class SFUnitTypes {

    public static Weapon copy(Weapon weapon, float x, float y) {
        Weapon n = weapon.copy();
        n.x = x;
        n.y = y;
        return n;
    }

    public static Weapon copyRotate(Weapon weapon, float x, float y, float br) {
        Weapon n = weapon.copy();
        n.x = x;
        n.y = y;
        n.baseRotation = br;
        return n;
    }

    public static Weapon
            redPointDefense = new PointDefenseWeapon(name("red-point-defense")) {{
        reload = 9;
        targetInterval = 20;
        targetSwitchInterval = 4.5f;
        rotateSpeed = 7;
        recoil = 1;
        shootY = 1.25f;
        bullet = new BulletType() {{
            shootEffect = Fx.sparkShoot;
            hitEffect = Fx.pointHit;
            maxRange = 280;
            damage = 50;
        }};
    }},
    /*redPointDefenseBig = new PointDefenseWeapon(name("red-point-gun")) {{
        reload = 9;
        targetInterval = 20;
        targetSwitchInterval = 4.5f;
        rotateSpeed = 7;
        recoil = 1;
        shootY = 1.25f;
        bullet = new BulletType() {{
            shootEffect = Fx.sparkShoot;
            hitEffect = Fx.pointHit;
            maxRange = 280;
            damage = 50;
        }};
    }},*/
    vastGun = new Weapon(name("vast-gun")) {{
        shootCone = 5.5f;
        shootSound = Sounds.blaster;
        shoot = new ShootAlternate(4.5f);
        shootY = 6.5f;
        recoil = 1;
        rotate = true;
        rotateSpeed = 7;
        reload = 4.5f;
        alternate = false;
        bullet = new RailBulletType() {{
            damage = 78;
            length = 330;
            pierce = false;
            pierceDamageFactor = 0.4f;
            pierceEffect = Fx.none;
            pointEffectSpace = 16;
            pointEffect = new ParticleEffect() {{
                particles = 1;
                sizeInterp = Interp.pow3In;
                line = true;
                randLength = false;
                lenFrom = lenTo = 17;
                strokeFrom = 2;
                strokeTo = 0;
                length = 0;
                baseLength = 1;
                lifetime = 10;
                colorFrom = colorTo = SFColor.enemyRedLight;
                cone = 0;
            }};
            endEffect = new ParticleEffect() {{
                particles = 1;
                length = 0;
                sizeFrom = 1;
                lifetime = 10;
                colorFrom = colorTo = SFColor.enemyRedLight;
            }};
            smokeEffect = Fx.none;
            hitEffect = new MultiEffect(
                    Fx.hitBulletColor,
                    new ParticleEffect() {{
                        particles = 5;
                        interp = Interp.circleOut;
                        sizeInterp = Interp.fastSlow;
                        line = true;
                        lenFrom = 16;
                        strokeFrom = 2;
                        length = 32;
                        lifetime = 15;
                        colorFrom = colorTo = SFColor.enemyRedLight;
                        cone = 100;
                    }}
            );
            hitColor = SFColor.enemyRedLight;
        }};
    }};

    public static UnitType
            //enemy only
            flareX, flareY, electrodile, thunderclap, knocker,
            painA, painB, painC, painD,
            blade, titan, vast,
            falcon, wyvern,
    //T6
    liXian, diXing, panLong, guangHan, yuHui, tengWang, luoHan,
    //tanks
    tank1, tank2, tank3, tank4, tank5, tank6,
    //air
    air1, air2, air3, air4, air5, air6,
    //naval
    naval1, naval2, naval3, naval4, naval5, naval6,
    //special force + flagship*/
    farmer, carrier, flamer, thunder, banisher, hammer, tau, omega, terrascape,
    //campaign only
    utv, utvA, utvC, utvD, utvM,
    //drones
    assemblerDrone, assemblerDrone2, armedDrone;

    public static void loadUnit() {
        flareX = new UnitType("flareX") {{
            constructor = UnitTypes.flare.constructor;
            flying = true;
            outlineColor = SFColor.darkOutline;
            itemCapacity = 0;
            autoFindTarget = true;
            drawMinimap = false;

            speed = 3.7f;
            rotateSpeed = 5;
            accel = 0.03f;
            drag = 0.02f;
            hitSize = 17;
            health = 4200;
            armor = 16;
            engineOffset = 12;
            engineSize = 3.5f;
            setEnginesMirror(new UnitEngine(-5.5f, -9.7f, 1.8f, 247.5f));
            trailLength = 6;
            circleTarget = true;
            targetFlags = new BlockFlag[]{BlockFlag.battery, BlockFlag.core};

            abilities.add(new StatusFieldAbility(SFStatusEffects.stormed, 60, 60, -1));
            weapons.add(
                    new Weapon(name("flareX-1")) {{
                        x = 0;
                        reload = 60;
                        inaccuracy = 5;
                        alternate = true;
                        rotate = false;
                        ignoreRotation = true;
                        shootSound = Sounds.missile;
                        shoot = new ShootBarrel() {{
                            shots = 2;
                            shotDelay = 10;
                            barrels = new float[]{
                                    -5, 0, 22.5f,
                                    4, 2.5f, -15,
                                    5, 0, -22.5f,
                                    -4, 0, 16
                            };
                        }};
                        shootCone = 90;
                        minShootVelocity = 0.15f;
                        bullet = new MissileBulletType(3, 76, "sfire-mod-missile2") {{
                            keepVelocity = false;
                            width = 12;
                            height = 40;
                            drag = -0.03f;
                            buildingDamageMultiplier = 0.43f;
                            splashDamage = 280;
                            splashDamageRadius = 22;
                            homingDelay = 30;
                            homingRange = 240;
                            homingPower = 0.5f;
                            hittable = false;
                            pierceArmor = true;
                            absorbable = false;
                            trailEffect = Fx.none;
                            trailColor = SFColor.enemyRedLight;
                            trailLength = 5;
                            trailWidth = 2;
                            shootEffect = Fx.none;
                            hitEffect = new ExplosionEffect() {{
                                lifetime = 32;
                                waveStroke = 3;
                                waveLife = 8;
                                waveRadBase = 0;
                                waveColor = SFColor.enemyRedLight;
                                waveRad = 24;
                                smokes = 12;
                                smokeColor = sparkColor = SFColor.enemyRedLight.cpy().a(0.85f);
                                sparks = 11;
                                sparkRad = 35;
                                sparkStroke = 1.25f;
                                sparkLen = 16;
                            }};
                            shrinkX = shrinkY = 0;
                            frontColor = backColor = SFColor.enemyRedLight;
                            suppressionRange = 60;
                            suppressionDuration = 100;
                            status = SFStatusEffects.shattered;
                            statusDuration = 100;
                        }};
                    }},
                    new Weapon(name("flareX-2")) {{
                        x = 0;
                        reload = 30;
                        inaccuracy = 100;
                        rotate = false;
                        mirror = false;
                        shootSound = Sounds.mineDeploy;
                        shootCone = 180;
                        ignoreRotation = true;
                        minShootVelocity = 0.3f;
                        bullet = new BasicBulletType(0, 20, "missile-large") {{
                            maxRange = 40;
                            width = height = 10;
                            lifetime = 33;
                            makeFire = true;
                            keepVelocity = false;
                            splashDamage = 86;
                            splashDamageRadius = 40;
                            buildingDamageMultiplier = 1.5f;

                            shootEffect = smokeEffect = Fx.none;
                            hitShake = 3;
                            hitEffect = new ExplosionEffect() {{
                                lifetime = 45;
                                waveStroke = 6;
                                waveLife = 10;
                                waveRadBase = 0;
                                waveColor = SFColor.enemyRedLight;
                                waveRad = 56;
                                smokes = 18;
                                smokeColor = sparkColor = SFColor.enemyRedLight.cpy().a(0.85f);
                                sparks = 8;
                                sparkRad = 50;
                                sparkStroke = 0.86f;
                                sparkLen = 8;
                            }};
                            despawnEffect = new ParticleEffect() {{
                                particles = 1;
                                length = 0;
                                sizeFrom = 36;
                                sizeInterp = Interp.pow5In;
                                lifetime = 25;
                                layer = 90;
                                colorFrom = SFColor.enemyRedLight.cpy().a(0.85f);
                                colorTo = SFColor.smoke;
                            }};
                            shrinkX = shrinkY = 0.45f;
                            frontColor = backColor = SFColor.enemyRedLight;

                            status = SFStatusEffects.chemicalFlame;
                            statusDuration = 60;
                        }};
                    }}
            );
        }};
        flareY = new UnitType("flareY") {{
            constructor = UnitTypes.flare.constructor;
            healColor = SFColor.tayrLight;
            outlineColor = SFColor.darkOutline;
            rotateSpeed = 6;
            speed = 4.86f;
            accel = 0.03f;
            drag = 0.022f;
            hitSize = 26f;
            flying = true;
            health = 6000;
            armor = 36f;
            faceTarget = true;
            circleTarget = true;
            engineLayer = 110f;
            engineSize = 0;
            setEnginesMirror(new UnitEngine(-3.75f, -18.8f, 2.35f, -90));
            abilities.add(new RegenAbility() {{
                amount = 10;
            }});
            weapons.add(new Weapon(name("flareY-weapon")) {{
                reload = 28.8f;
                x = 5;
                y = 3;
                inaccuracy = 2;
                ignoreRotation = true;
                shootSound = Sounds.missile;
                soundPitchMax = 0.5f;
                soundPitchMin = 0.5f;
                shoot.shots = 3;
                shoot.shotDelay = 4;
                shootCone = 15f;
                rotationLimit = 20f;
                rotateSpeed = 2f;
                rotate = true;
                bullet = new BasicBulletType(40, 88) {{
                    drag = 0.12f;
                    pierceArmor = true;
                    lightning = 1;
                    lightningLength = 2;
                    lightningColor = SFColor.tayrLight;
                    status = SFStatusEffects.skewed;
                    statusDuration = 22;
                    height = 45f;
                    width = 8;
                    frontColor = Color.white;
                    backColor = hitColor = SFColor.tayrLight;
                    trailLength = 16;
                    trailWidth = 1.5f;
                    trailColor = backColor;
                    shootEffect = Fx.shootSmallColor;
                    smokeEffect = Fx.none;
                    hitSound = Sounds.explosion;
                    despawnEffect = Fx.none;
                    hitShake = 3f;
                    hitEffect = new ExplosionEffect() {{
                        lifetime = 30f;
                        waveStroke = 4;
                        waveLife = 16;
                        waveRad = 30f;
                        smokes = 0;
                        sparks = 8;
                        sparkRad = 45f;
                        sparkStroke = 0.88f;
                        sparkLen = 18;
                        waveColor = sparkColor = SFColor.tayrLight;
                    }};
                }};
            }});
        }};
        electrodile = new UnitType("electrodile") {{
            constructor = UnitTypes.elude.constructor;
            hovering = true;
            canDrown = false;
            outlineColor = SFColor.darkOutline;
            itemCapacity = 0;
            flying = false;
            healFlash = false;
            rotateSpeed = 1.5f;
            speed = 1.2f;
            canBoost = true;
            boostMultiplier = 0.85f;
            riseSpeed = 0.015f;
            engineOffset = 18;
            engineSize = 7;
            accel = 0.05f;
            drag = 0.04f;
            hitSize = 40;
            health = 28000f;
            armor = 14;
            faceTarget = true;
            lightRadius = 80;
            lightColor = SFColor.enemyRedLight;
            abilities.add(new RegenAbility() {{
                percentAmount = 0.005f;
            }});
            abilities.add(new ShieldRegenFieldAbility(50, 2500, 60, 50) {{
                applyEffect = new WaveEffect() {{
                    sides = 4;
                    sizeTo = 15;
                    interp = Interp.circleOut;
                    strokeFrom = 6;
                    colorFrom = colorTo = SFColor.enemyRedLight;
                }};
                activeEffect = new WaveEffect() {{
                    sides = 4;
                    lifetime = 50;
                    sizeTo = 55;
                    interp = Interp.circleOut;
                    strokeFrom = 9;
                    colorFrom = SFColor.enemyRedLight.cpy().a(0.5f);
                    colorTo = SFColor.enemyRedLight;
                }};
                /*for(int i = 1; i<= 3; i++) {
                    int fi = i;
                    activeEffect = new MultiEffect(
                            new WaveEffect() {{
                                sides = 4;
                                lifetime = 50;
                                startDelay = fi * 5 - 5;
                                sizeTo = 55;
                                interp = Interp.circleOut;
                                strokeFrom = 9;
                                colorFrom = SFColor.enemyRedDark.cpy().a(0.5f);
                                colorTo = SFColor.enemyRedDark;
                            }}
                    );
                };*/
            }});
            abilities.add(new ShieldArcAbility() {{
                radius = 32;
                width = 6;
                max = 3600;
                regen = 10;
                cooldown = 300;
                angle = 110;
            }});
            abilities.add(new MoveEffectAbility() {{
                minVelocity = 0;
                interval = 10;
                effect = new MultiEffect(
                        new ParticleEffect() {{
                            particles = 2;
                            sizeFrom = 9;
                            lifetime = 30;
                            sizeInterp = Interp.pow5In;
                            interp = Interp.pow5Out;
                            length = 24;
                            layer = 60;
                            colorFrom = SFColor.enemyRedLight.cpy().a(0.8f);
                            colorTo = Color.white.cpy().a(0.1f);
                        }},
                        new ParticleEffect() {{
                            particles = 3;
                            sizeFrom = 5;
                            lifetime = 30;
                            sizeInterp = Interp.pow5In;
                            interp = Interp.pow5Out;
                            length = 26;
                            layer = 60;
                            colorFrom = SFColor.enemyRedLight.cpy().a(0.5f);
                            colorTo = Color.white.cpy().a(0.1f);
                        }}
                );
            }});
            abilities.add(new MoveEffectAbility() {{
                minVelocity = 1.025f;
                interval = 8;
                effect = new MultiEffect(
                        new ParticleEffect() {{
                            particles = 12;
                            sizeFrom = 3;
                            lifetime = 30;
                            sizeInterp = Interp.pow5In;
                            interp = Interp.pow5Out;
                            length = 30;
                            layer = 60;
                            colorFrom = SFColor.enemyRedLight.cpy().a(0.8f);
                            colorTo = Color.white.cpy().a(0.1f);
                        }},
                        new ParticleEffect() {{
                            particles = 2;
                            sizeFrom = 9;
                            lifetime = 30;
                            sizeInterp = Interp.pow5In;
                            interp = Interp.pow5Out;
                            length = 29;
                            layer = 60;
                            colorFrom = SFColor.enemyRedLight.cpy().a(0.8f);
                            colorTo = Color.white.cpy().a(0.1f);
                        }}
                );
            }});
            parts.add(
                    new HoverPart() {{
                        x = 12f;
                        y = -12;
                        mirror = true;
                        radius = 18;
                        phase = 100;
                        circles = 2;
                        stroke = 2f;
                        layerOffset = -0.001f;
                        minStroke = 0;
                        color = SFColor.enemyRedLight;
                    }},
                    new HoverPart() {{
                        mirror = false;
                        radius = 26;
                        phase = 300;
                        circles = 4;
                        stroke = 3.5f;
                        minStroke = 0.3f;
                        layerOffset = -1f;
                        color = SFColor.enemyRedLight;
                    }}
            );
            weapons.add(
                    new Weapon(name("electrodile-weapon")) {{
                        x = 0;
                        y = -4;
                        rotate = true;
                        rotateSpeed = 1.75f;
                        shootCone = 12;
                        mirror = false;
                        inaccuracy = 3;
                        shake = 1;
                        shootSound = Sounds.laser;
                        layerOffset = 0.001f;
                        minWarmup = 0.88f;
                        shootY = 23.5f;
                        reload = 13;
                        shootStatus = StatusEffects.overclock;
                        shootStatusDuration = 20;
                        recoil = 0;
                        recoilTime = 20;
                        shoot = new ShootBarrel() {{
                            barrels = new float[]{
                                    0, 0, 0,
                                    -3, -1, 0,
                                    3, -1, 0
                            };
                        }};
                        recoils = 3;
                        parts.addAll(
                                new RegionPart("-barrel") {{
                                    under = true;
                                    mirror = false;
                                    x = 0;
                                    recoilIndex = 0;
                                    heatProgress = PartProgress.recoil;
                                    heatColor = Color.valueOf("FF4040");
                                    progress = PartProgress.recoil;
                                    moveY = -8;
                                }},
                                new RegionPart("-barrel") {{
                                    under = true;
                                    mirror = false;
                                    x = -3;
                                    y = -1;
                                    recoilIndex = 1;
                                    heatProgress = PartProgress.recoil;
                                    heatColor = Color.valueOf("FF4040");
                                    progress = PartProgress.recoil;
                                    moveY = -8;
                                }},
                                new RegionPart("-barrel") {{
                                    under = true;
                                    mirror = false;
                                    x = 3;
                                    y = -1;
                                    recoilIndex = 2;
                                    heatProgress = PartProgress.recoil;
                                    heatColor = Color.valueOf("FF4040");
                                    progress = PartProgress.recoil;
                                    moveY = -8;
                                }},
                                new HaloPart() {{
                                    y = -13.5f;
                                    shapes = 3;
                                    haloRotateSpeed = 0.8f;
                                    shapeRotation = 50;
                                    color = SFColor.enemyRedLight;
                                    layer = 110;
                                    tri = true;
                                    hollow = true;
                                    radius = 1;
                                    radiusTo = 3;
                                    triLength = 2.5f;
                                    triLengthTo = 6;
                                    haloRadius = 1.5f;
                                    haloRadiusTo = 4;
                                }},
                                new ShapePart() {{
                                    y = -13.5f;
                                    color = SFColor.enemyRedLight;
                                    layer = 110;
                                    circle = true;
                                    hollow = false;
                                    radius = 2;
                                    radiusTo = 5;
                                }}
                        );
                        ejectEffect = Fx.none;
                        velocityRnd = 0.06f;
                        bullet = new BasicBulletType(15, 135) {{
                            splashDamage = 122;
                            splashDamageRadius = 56;
                            lightningColor = SFColor.enemyRedLight;
                            lightning = 3;
                            lightningLength = 7;
                            lightningLengthRand = 3;
                            lifetime = 28;
                            hitShake = 3;
                            width = 12;
                            height = 18;
                            frontColor = backColor = SFColor.enemyRedLight;
                            trailLength = 32;
                            trailWidth = 3;
                            trailColor = SFColor.enemyRedLight;
                            shootEffect = Fx.shootBig2;
                            smokeEffect = new ParticleEffect() {{
                                particles = 4;
                                sizeFrom = 4;
                                lifetime = 68;
                                interp = Interp.pow5Out;
                                sizeInterp = Interp.pow5In;
                                length = 36;
                                cone = 16;
                                colorFrom = SFColor.enemyRedLight.cpy().a(0.5f);
                                colorTo = SFColor.enemyRedLight.cpy().a(0.02f);
                            }};
                            despawnEffect = Fx.none;
                            hitSound = Sounds.explosion;
                            hitSoundVolume = 3;
                            hitEffect = new ExplosionEffect() {{
                                lifetime = 22;
                                waveColor = SFColor.enemyRedLight;
                                sparkColor = smokeColor = SFColor.enemyRedLight;
                                waveStroke = 6;
                                waveLife = 9;
                                waveRadBase = 0;
                                waveRad = 60;
                                smokes = 7;
                                sparks = 5;
                                sparkRad = 75;
                                sparkStroke = 2.2f;
                                sparkLen = 16;
                            }};
                        }};
                    }}
            );

        }};
        /*thunderclap = new UnitType("thunderclap") {{
            outlineColor = SFColor.darkOutline;
            constructor = EntityMapping.map(3);
            weapons.add(new Weapon(name("thunderclap-gun")){{
                top = false;
                rotate = true;
                alternate = true;
                mirror = false;
                x = 0f;
                y = -10f;
                reload = 6f;
                inaccuracy = 3f;
                ejectEffect = Fx.none;
            }});
            targetAir = false;
            maxRange = 200;
            engineOffset = 14.0F;
            engineSize = 4f;
            speed = 5f;
            accel = 0.04F;
            drag = 0.0075F;
            circleTarget = true;
            hitSize = 14f;
            health = 1000f;
            baseRotateSpeed = 1.5f;
            rotateSpeed = 2.5f;
            armor = 3.5f;
            flying = true;
        }};*/
        knocker = new UnitType("knocker") {{
            constructor = UnitTypes.corvus.constructor;
            aiController = SuicideAI::new;
            outlineColor = SFColor.darkOutline;
            itemCapacity = 0;
            armor = 15;
            speed = 1.36f;
            drag = 0.1f;
            rotateSpeed = 1.52f;
            hitSize = 18;
            health = 3600;
            stepShake = 0;
            legCount = 6;
            legLength = 19.5f;
            legGroupSize = 3;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -3;
            legBaseOffset = 7;
            legMaxLength = 1.1f;
            legMinLength = 0.2f;
            legLengthScl = 0.95f;
            legForwardScl = 0.9f;
            legMoveSpace = 1;
            drownTimeMultiplier = 6;
            hovering = true;
            allowLegStep = true;
            range = 410;
            groundLayer = 49;
            abilities.add(new StatusFieldAbility(StatusEffects.shielded, 30, 60, 8) {{
                applyEffect = Fx.none;
                activeEffect = new WaveEffect() {{
                    interp = Interp.circleOut;
                    lifetime = 10;
                    sizeTo = 45;
                    strokeFrom = 8;
                    colorFrom = SFColor.enemyRedLight;
                    colorTo = Pal.sap.cpy().a(0.1f);
                }};
            }});
            for (int i = 1; i <= 3; i++) {
                int fi = i;
                parts.add(new RegionPart("-missile-wing") {{
                    mirror = true;
                    under = true;
                    layerOffset = -0.001f;
                    progress = PartProgress.warmup;
                    heatProgress = PartProgress.warmup;
                    heatColor = SFColor.enemyRedLight;
                    moveX = 6 + fi;
                    moveY = -13.25f + 1.5f * fi;
                    moveRot = -180 + 30 * fi;
                    moves.add(new PartMove(PartProgress.recoil, 0, -2, 0));
                }});
            }
            faceTarget = true;
            weapons.add(
                    new Weapon(name("knocker-weapon")) {{
                        rotate = false;
                        reload = 180;
                        x = 0;
                        mirror = false;
                        shootCone = 4;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.missileLaunch;
                        shootStatusDuration = 180;
                        shootStatus = StatusEffects.slow;
                        shoot = new ShootPattern() {{
                            firstShotDelay = 180;
                        }};
                        parts.add(new RegionPart("-side") {{
                            mirror = true;
                            progress = PartProgress.warmup;
                            moveX = 3.5f;
                            moveRot = -35;
                            moves.add(new PartMove(PartProgress.charge, 0.5f, -2, -10));
                        }});
                        shootWarmupSpeed = 0.05f;
                        minWarmup = 0.9f;
                        bullet = new BulletType() {{
                            killShooter = true;
                            keepVelocity = false;
                            collidesAir = true;
                            hitShake = 8;
                            speed = 0;
                            shootEffect = Fx.massiveExplosion;
                            smokeEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 16;
                                        interp = Interp.slowFast;
                                        sizeInterp = Interp.slowFast;
                                        sizeFrom = 9;
                                        length = 65;
                                        lifetime = 88;
                                        colorFrom = SFColor.enemyRedLight.cpy().a(0.5f);
                                        colorTo = SFColor.enemyRedLight.cpy().a(0.6f);
                                    }},
                                    new ParticleEffect() {{
                                        particles = 36;
                                        interp = Interp.pow10Out;
                                        sizeInterp = Interp.pow10In;
                                        sizeFrom = 7;
                                        length = -77;
                                        lifetime = 60;
                                        colorFrom = SFColor.enemyRedLight;
                                        colorTo = SFColor.enemyRedLight;
                                        cone = 25;
                                    }}
                            );
                            spawnUnit = new MissileUnitType("knocker-missile") {{
                                outlineColor = SFColor.darkOutline;
                                armor = 33;
                                speed = 11.6f;
                                lifetime = 35;
                                drag = 0.1f;
                                rotateSpeed = 0;
                                hitSize = 13;
                                health = 1800;
                                targetAir = true;
                                collidesAir = true;
                                deathSound = Sounds.explosionbig;
                                engineColor = trailColor = SFColor.enemyRedLight;
                                trailLength = 14;
                                engineLayer = 110;
                                engineOffset = 16;
                                engineSize = 4;
                                abilities.add(new StatusFieldAbility(StatusEffects.shielded, 30, 60, 8) {{
                                    applyEffect = Fx.none;
                                    activeEffect = new WaveEffect() {{
                                        interp = Interp.circleOut;
                                        lifetime = 10;
                                        sizeTo = 45;
                                        strokeFrom = 8;
                                        colorFrom = SFColor.enemyRedLight;
                                        colorTo = Pal.sap.cpy().a(0.1f);
                                    }};
                                }});
                                abilities.add(new MoveEffectAbility() {{
                                    minVelocity = 5;
                                    rotation = 0;
                                    rotateEffect = true;
                                    interval = 3;
                                    y = -8;
                                    effect = new MultiEffect(
                                            new ParticleEffect() {{
                                                particles = 1;
                                                sizeTo = 18;
                                                lifetime = 30;
                                                sizeInterp = Interp.pow5Out;
                                                length = 0;
                                                region = "sfire-mod-arrow";
                                                colorFrom = SFColor.enemyRedLight.cpy();
                                                colorTo = SFColor.enemyRedLight.cpy();
                                                cone = 0;
                                            }},
                                            new ParticleEffect() {{
                                                particles = 1;
                                                sizeFrom = 18;
                                                sizeTo = 0;
                                                startDelay = 6;
                                                lifetime = 30;
                                                length = 0;
                                                region = "sfire-mod-arrow";
                                                colorFrom = SFColor.enemyRedLight;
                                                colorTo = SFColor.enemyRedLight;
                                                cone = 0;
                                            }}
                                    );
                                }});
                                maxRange = 45;
                                for (int i = 1; i <= 3; i++) {
                                    int fi = i;
                                    parts.add(new RegionPart("-wing") {{
                                        mirror = true;
                                        under = true;
                                        layerOffset = -0.001f;
                                        heatProgress = PartProgress.warmup;
                                        heatColor = Color.valueOf("A278E1");
                                        x = 6 + fi;
                                        y = -13.25f + 1.5f * fi;
                                        rotation = -180 + 30 * fi;
                                        children.add(new RegionPart("-wing-heat") {{
                                            layerOffset = 0.1f;
                                            blending = Blending.additive;
                                            color = SFColor.enemyRedLight;
                                            outline = false;
                                        }});
                                    }});
                                }
                                weapons.add(new Weapon(name("knocker-weapon")) {{
                                    reload = 60;
                                    x = 0;
                                    mirror = false;
                                    rotate = true;
                                    shake = 10;
                                    shootSound = Sounds.none;
                                    shootOnDeath = true;
                                    shootCone = 360;
                                    bullet = new BulletType(0, 3000) {{
                                        killShooter = true;
                                        splashDamage = 1200;
                                        splashDamageRadius = 30;
                                        suppressionRange = 380;
                                        suppressionDuration = 300;
                                        pierceArmor = true;
                                        buildingDamageMultiplier = 6;
                                        status = SFStatusEffects.shattered;
                                        statusDuration = 100;
                                        hitSound = Sounds.titanExplosion;
                                        hitShake = 8;
                                        shootEffect = smokeEffect = despawnEffect = Fx.massiveExplosion;
                                        hitEffect = new MultiEffect(
                                                new WrapEffect(Fx.dynamicSpikes, SFColor.enemyRedLight, 380),
                                                new WaveEffect() {{
                                                    interp = Interp.circleOut;
                                                    lifetime = 20;
                                                    sizeTo = 380;
                                                    strokeFrom = 22;
                                                    strokeTo = 10;
                                                    colorFrom = SFColor.enemyRedLight;
                                                    colorTo = SFColor.enemyRedLight.cpy().a(0);
                                                }},
                                                new ParticleEffect() {{
                                                    particles = 92;
                                                    line = true;
                                                    strokeFrom = 8;
                                                    lenFrom = 35;
                                                    length = 400;
                                                    baseLength = 33;
                                                    lifetime = 45;
                                                    interp = Interp.fastSlow;
                                                    sizeInterp = Interp.pow3In;
                                                    colorFrom = Color.white;
                                                    colorTo = SFColor.enemyRedLight;
                                                }},
                                                new ParticleEffect() {{
                                                    particles = 26;
                                                    sizeFrom = 10;
                                                    length = 65;
                                                    lifetime = 120;
                                                    interp = Interp.fastSlow;
                                                    sizeInterp = Interp.pow5In;
                                                    colorFrom = SFColor.enemyRedLight;
                                                    colorTo = SFColor.enemyRedLight.cpy().a(0.1f);
                                                }}
                                        );
                                    }};
                                }});
                            }};
                        }};
                    }}
            );
        }};
        painA = new UnitType("partiality-A") {{
            constructor = UnitTypes.corvus.constructor;
            outlineColor = SFColor.darkOutline;
            flying = false;
            hovering = true;
            drownTimeMultiplier = 8;

            speed = 0.88f;
            rotateSpeed = 1.88f;
            drag = 0.18f;
            accel = 0.3f;
            hitSize = 33;
            itemCapacity = 0;
            health = 56000;
            armor = 55;

            legCount = 4;
            legLength = 35f;
            legForwardScl = 0.35f;
            legMoveSpace = 1;
            legSplashDamage = 60;
            legSplashRange = 20;

            abilities.add(new ArmorPlateAbility() {{
                healthMultiplier = 1.88f;
            }});

            weapons.add(new Weapon(name("pain-gun")) {{
                rotate = true;
                rotateSpeed = 3.6f;
                mirror = false;
                x = 0;
                reload = 78;
                shootY = 28;
                shootStatus = SFStatusEffects.stormed;
                shootStatusDuration = 80;
                shoot = new ShootAlternate(8) {{
                    shots = 4;
                    shotDelay = 4;
                }};
                cooldownTime = 100;
                recoil = 2;
                shootSound = Sounds.malignShoot;
                shake = 3.6f;
                bullet = new BasicBulletType(16, 83, "missile-large") {{
                    lightning = 2;
                    lightningDamage = damage;
                    lightningColor = SFColor.discLight;
                    lightningLength = 3;
                    lightningLengthRand = 5;
                    lightningType = new BulletType(0.00001f, 0f) {{
                        hitEffect = new WrapEffect(Fx.hitLaserColor, SFColor.discLight);
                        despawnEffect = Fx.none;
                        lightColor = SFColor.discLight;
                        status = StatusEffects.shocked;
                        statusDuration = 10f;
                        hittable = false;
                        buildingDamageMultiplier = 0.4f;
                    }};
                    buildingDamageMultiplier = 0.4f;
                    width = 6;
                    height = 20;
                    lifetime = 25 / 2f;
                    drag = -0.016f;
                    pierceArmor = true;
                    hittable = absorbable = false;
                    status = SFStatusEffects.breakdown;
                    statusDuration = 99;
                    frontColor = Color.white;
                    backColor = SFColor.discLight;
                    trailColor = SFColor.discLight;
                    trailLength = 3;
                    trailWidth = 2;
                    hitShake = 2.2f;
                    shrinkY = 0;
                    shootEffect = new ParticleEffect() {{
                        line = true;
                        particles = 2;
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.pow5In;
                        strokeFrom = 2;
                        lenFrom = 25;
                        length = 30;
                        lifetime = 19;
                        colorTo = SFColor.discLight;
                        cone = 15f;
                    }};
                    smokeEffect = new ParticleEffect() {{
                        particles = 2;
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 3;
                        length = 30;
                        lifetime = 19;
                        colorFrom = SFColor.discLight;
                        colorTo = SFColor.discLight.cpy().a(0.8f);
                        cone = 15f;
                    }};
                    despawnEffect = Fx.hitBulletBig;
                    hitEffect = new ExplosionEffect() {{
                        smokes = 3;
                        sparks = 0;
                        lifetime = 65;
                        smokeRad = 30;
                        smokeSize = 6;
                        smokeColor = waveColor = SFColor.discLight;
                        waveLife = 10;
                        waveStroke = 6;
                        waveRad = 20;
                        waveRadBase = 5;
                    }};
                }};
            }});
        }};
        painB = new UnitType("partiality-B") {{
            constructor = UnitTypes.corvus.constructor;
            outlineColor = SFColor.darkOutline;
            flying = false;
            hovering = true;
            drownTimeMultiplier = 8;

            speed = 0.88f;
            rotateSpeed = 1.88f;
            drag = 0.18f;
            accel = 0.3f;
            hitSize = 33;
            itemCapacity = 0;
            health = 56000;
            armor = 55;

            lockLegBase = true;
            legContinuousMove = true;
            legCount = 4;
            legLength = 35f;
            legForwardScl = 0.35f;
            legMoveSpace = 1;
            legSplashDamage = 60;
            legSplashRange = 20;

            abilities.add(new ArmorPlateAbility() {{
                healthMultiplier = 1.88f;
            }});

            weapons.add(new Weapon(name("pain-cannon")) {{
                rotate = true;
                rotateSpeed = 2.3f;
                mirror = false;
                x = 0;
                reload = 208.12f;
                shootY = 29;
                shootStatus = SFStatusEffects.stormed;
                shootStatusDuration = 219.12f;
                cooldownTime = 130;
                recoil = 2;
                parts.add(new RegionPart("-barrel") {{
                    progress = PartProgress.recoil;
                    under = true;
                    moveY = -4f;
                }});
                shootSound = Sounds.mediumCannon;
                shake = 3.6f;
                bullet = new BasicBulletType(16, 188 * 2f, "missile-large") {{
                    lightningDamage = damage / 2;
                    lightning = 2;
                    lightningLength = 10;
                    lightningLengthRand = 4;
                    lightningType = new BulletType(0.00001f, 0f) {{
                        hitEffect = new WrapEffect(Fx.hitLaserColor, SFColor.discLight);
                        despawnEffect = Fx.none;
                        lightColor = SFColor.discLight;
                        status = StatusEffects.shocked;
                        statusDuration = 10f;
                        hittable = false;
                        buildingDamageMultiplier = 5.25f;
                    }};
                    buildingDamageMultiplier = 5.25f;
                    lightningColor = backColor;
                    splashDamage = 260;
                    splashDamageRadius = 66f;
                    width = 10;
                    height = 20;
                    lifetime = 43 / 2f;
                    drag = -0.016f;
                    pierceArmor = true;
                    hittable = absorbable = false;
                    status = SFStatusEffects.breakdown;
                    statusDuration = 200;
                    frontColor = Color.white;
                    backColor = SFColor.discLight;
                    trailColor = SFColor.discLight;
                    trailLength = 10;
                    trailWidth = 4;
                    hitShake = 8f;
                    hitSound = Sounds.explosion;
                    hitSoundVolume = 5;
                    shrinkY = 0;
                    shootEffect = new WaveEffect() {{
                        lifetime = 28;
                        sizeTo = 30;
                        strokeFrom = 3;
                        colorFrom = colorTo = SFColor.discLight;
                    }};
                    smokeEffect = new ParticleEffect() {{
                        particles = 9;
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 6;
                        length = 30;
                        lifetime = 65;
                        colorFrom = SFColor.discLight;
                        colorTo = SFColor.discLight.cpy().a(0.8f);
                        cone = 18f;
                    }};
                    despawnEffect = new WaveEffect() {{
                        lifetime = 26;
                        sizeFrom = 5;
                        sizeTo = 90;
                        strokeFrom = 11;
                        colorFrom = colorTo = SFColor.discLight;
                    }};
                    hitEffect = new ExplosionEffect() {{
                        smokes = 13;
                        sparks = 0;
                        lifetime = 95;
                        smokeRad = 60;
                        smokeSize = 8;
                        smokeColor = SFColor.discLight.cpy().a(0.8f);
                        waveColor = SFColor.discLight;
                        waveLife = 26;
                        waveStroke = 8;
                        waveRad = 70;
                        waveRadBase = 5;
                    }};
                    ejectEffect = new MultiEffect(
                            new ParticleEffect() {{
                                particles = 12;
                                interp = Interp.pow10Out;
                                sizeInterp = Interp.pow5In;
                                sizeFrom = 13;
                                length = 30;
                                lifetime = 95;
                                layer = 60;
                                colorFrom = colorTo = SFColor.smoke.cpy().a(0.8f);
                            }},
                            new WaveEffect() {{
                                lifetime = 22;
                                sizeFrom = 5;
                                sizeTo = 70;
                                strokeFrom = 48;
                                strokeTo = 16;
                                layer = 60f;
                                colorTo = Color.white.cpy().a(0.1f);
                            }}
                    );
                }};
            }});
        }};
        painC = new UnitType("partiality-C") {{
            constructor = UnitTypes.corvus.constructor;
            outlineColor = SFColor.darkOutline;
            flying = false;
            hovering = true;
            drownTimeMultiplier = 8;

            speed = 0.88f;
            rotateSpeed = 1.88f;
            drag = 0.18f;
            accel = 0.3f;
            hitSize = 33;
            itemCapacity = 0;
            health = 56000;
            armor = 55;

            legCount = 4;
            legLength = 35f;
            legForwardScl = 0.35f;
            legMoveSpace = 1;
            legSplashDamage = 60;
            legSplashRange = 20;

            abilities.add(new ArmorPlateAbility() {{
                healthMultiplier = 1.88f;
            }});

            weapons.add(new Weapon(name("pain-missile")) {{
                rotate = true;
                rotateSpeed = 6.3f;
                x = 0;
                reload = 98f;
                shootY = 21;
                shootX = 14;
                shootStatus = SFStatusEffects.stormed;
                shootStatusDuration = 66f;
                recoil = 0;
                recoilTime = 80;
                parts.add(new RegionPart("-barrel") {{
                    progress = PartProgress.recoil;
                    under = true;
                    mirror = false;
                    moveY = -8f;
                }});
                shootCone = 30;
                shootSound = Sounds.missileLarge;
                shake = 3.6f;
                bullet = new MissileBulletType(1, 135, "sfire-mod-missile1") {{
                    splashDamage = 180;
                    splashDamageRadius = 48f;
                    buildingDamageMultiplier = 2f;
                    width = 12;
                    height = 50;
                    lifetime = 108 * 0.85f;
                    drag = -0.03f;
                    homingRange = 250;
                    homingPower = 0.1f;
                    homingDelay = 55 * 0.75f;
                    keepVelocity = false;
                    pierceArmor = true;
                    hittable = absorbable = false;
                    status = SFStatusEffects.shattered;
                    statusDuration = 22;
                    frontColor = Color.white;
                    backColor = SFColor.enemyRedLight;
                    hitShake = 8f;
                    hitSound = Sounds.titanExplosion;
                    hitSoundVolume = 3;
                    shrinkY = 0;
                    shootEffect = Fx.shootTitan;
                    smokeEffect = new ParticleEffect() {{
                        particles = 9;
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 5;
                        length = 38;
                        lifetime = 65;
                        colorFrom = SFColor.discLight;
                        colorTo = SFColor.discLight.cpy().a(0.8f);
                        cone = 22f;
                    }};
                    despawnEffect = new ParticleEffect() {{
                        particles = 1;
                        sizeFrom = 50;
                        length = 0;
                        lifetime = 105;
                        colorFrom = colorTo = SFColor.discLight;
                    }};
                    hitEffect = new ExplosionEffect() {{
                        smokes = 8;
                        sparks = 32;
                        lifetime = 35;
                        smokeRad = 55;
                        smokeSize = 8;
                        smokeColor = SFColor.discLight.cpy().a(0.8f);
                        sparkLen = 35;
                        sparkStroke = 1.33f;
                        sparkRad = 75;
                        waveColor = sparkColor = SFColor.discLight;
                        waveLife = 16;
                        waveStroke = 11;
                        waveRad = 60;
                        waveRadBase = 5;
                    }};
                    trailColor = SFColor.discLight;
                    trailWidth = 2;
                    trailLength = 15;
                    trailRotation = true;
                    trailChance = 0.8f;
                    trailEffect = new MultiEffect(
                            new ParticleEffect() {{
                                particles = 3;
                                sizeFrom = 3;
                                length = -50;
                                lifetime = 60;
                                interp = Interp.pow5Out;
                                sizeInterp = Interp.pow5In;
                                colorFrom = colorTo = SFColor.discLight.cpy().a(0.5f);
                                cone = 19f;
                            }},
                            new ParticleEffect() {{
                                particles = 3;
                                line = true;
                                lenFrom = 35f;
                                strokeFrom = 1;
                                baseLength = 16;
                                length = -70;
                                lifetime = 15;
                                interp = Interp.pow5Out;
                                colorFrom = colorTo = SFColor.discLight;
                                cone = 16;
                            }}
                    );
                    fragBullets = 3;
                    fragBullet = new FlakBulletType(4, 135*3) {{
                        frontColor = backColor = SFColor.discLight;
                        homingPower = 0.05f;
                        homingRange = 60;
                        hitSound = Sounds.explosion;
                        despawnEffect = Fx.flakExplosionBig;
                        status = SFStatusEffects.breakdown;
                        statusDuration = 206f;
                        hitEffect = new WrapEffect(Fx.dynamicSpikes, SFColor.discLight, 30);
                        lifetime = 20;
                        pierceArmor = true;
                        hittable = false;
                        scaledSplashDamage = true;
                        splashDamage = 180;
                        splashDamageRadius = 30;
                        hitSound = Sounds.boom;
                        trailColor = SFColor.discLight;
                        trailWidth = 1.25f;
                        trailLength = 15;
                    }};
                }};
            }});
        }};
        painD = new UnitType("partiality-D") {{
            constructor = UnitTypes.corvus.constructor;
            outlineColor = SFColor.darkOutline;
            flying = false;
            hovering = true;
            drownTimeMultiplier = 8;

            speed = 0.88f;
            rotateSpeed = 1.88f;
            drag = 0.18f;
            accel = 0.3f;
            hitSize = 33;
            itemCapacity = 0;
            health = 56000;
            armor = 55;

            legCount = 6;
            legLength = 35f;
            legForwardScl = 0.35f;
            legMoveSpace = 1;
            legSplashDamage = 60;
            legSplashRange = 20;

            abilities.add(new ArmorPlateAbility() {{
                healthMultiplier = 1.88f;
            }});

            weapons.add(
                    new RepairBeamWeapon(name("pain-heal")) {{
                        reload = 60;
                        y = 4;
                        x = 0;
                        shootY = 13;
                        rotate = true;
                        rotateSpeed = 8;
                        beamWidth = 1.2f;
                        repairSpeed = 22f;
                        laserColor = SFColor.enemyRedLight;
                        laserTopColor = Color.white;
                        bullet = new BulletType() {{
                            maxRange = 150f;
                        }};
                    }},
                    new Weapon(name("pain-explosion")) {{
                        x = 0;
                        reload = 60f;
                        shootCone = 360f;
                        //display = false;
                        ejectEffect = Fx.none;
                        shootSound = SFSounds.hugeExplosion;
                        mirror = false;
                        shootOnDeath = true;
                        controllable = false;
                        autoTarget = true;
                        shoot.shots = 2;
                        shoot.firstShotDelay = 480f;
                        shootStatus = SFStatusEffects.overLoad;
                        shootStatusDuration = 488f;
                        bullet = new ExplosionBulletType(12000, 150) {{
                            maxRange = 200;
                            hitShake = 88f;
                            shootEffect = new MultiEffect(Fx.massiveExplosion, new WrapEffect(Fx.dynamicSpikes, SFColor.enemyRedLight, 160), new WaveEffect() {{
                                colorFrom = colorTo = SFColor.enemyRedLight;
                                sizeTo = 40f;
                                lifetime = 12f;
                                strokeFrom = 4f;
                            }});
                            smokeEffect = new ExplosionEffect() {{
                                lifetime = 128;
                                waveStroke = 9;
                                waveLife = 20;
                                waveRadBase = 7;
                                waveColor = SFColor.enemyRedLight;
                                waveRad = 220;
                                smokes = 30;
                                smokeSize = 10;
                                smokeColor = sparkColor = SFColor.enemyRedLight;
                                sparks = 60;
                                sparkRad = 350;
                                sparkStroke = 3;
                                sparkLen = 50;
                            }};
                            hitEffect = new ParticleEffect() {{
                                particles = 1;
                                region = "sfire-mod-star";
                                sizeFrom = 230;
                                lifetime = 20;
                                length = 0;
                                colorFrom = colorTo = SFColor.enemyRedLight;
                            }};
                            despawnEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 1;
                                        sizeTo = 150;
                                        lifetime = 9;
                                        length = 0;
                                        colorTo = SFColor.enemyRedLight;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 1;
                                        startDelay = 16;
                                        sizeFrom = 150;
                                        lifetime = 129;
                                        length = 0;
                                        colorFrom = SFColor.enemyRedLight;
                                        colorTo = SFColor.enemyRedLight.cpy().a(0.65f);
                                    }},
                                    new WaveEffect() {{
                                        strokeFrom = 25;
                                        lifetime = 46;
                                        sizeTo = 200;
                                        colorTo = SFColor.enemyRedLight;
                                    }}
                            );
                        }};
                    }}
            );
        }};

        blade = new UnitType("blade") {{
            constructor = UnitTypes.zenith.constructor;
            outlineColor = SFColor.darkOutline;
            itemCapacity = 0;
            flying = true;
            speed = 1.28f;
            rotateSpeed = 1.2f;
            accel = 0.052f;
            drag = 0.05f;
            engineLayer = 110;
            engineOffset = 24;
            engineSize = 9;
            setEnginesMirror(new UnitEngine(16, -39.25f, 4, 90));
            hitSize = 58;
            health = 300000;
            armor = 64;
            targetFlags = new BlockFlag[]{BlockFlag.unitAssembler, BlockFlag.turret};
            abilities.add(new RepairFieldAbility(530, 330, 150) {{
                healEffect = Fx.none;
                activeEffect = new WaveEffect() {{
                    lifetime = 35;
                    sizeTo = 152;
                    strokeFrom = 16;
                    interp = Interp.circleOut;
                    colorFrom = colorTo = SFColor.enemyRedLight;
                }};
            }});
            abilities.add(new ForceFieldAbility(150, 100, 18000, 900) {{
                sides = 4;
            }});
            drawShields = false;
            faceTarget = false;
            BulletType bladePoint = new PointBulletType() {{
                shootEffect = Fx.shootBig;
                damage = 126;
                splashDamage = 88;
                splashDamageRadius = 45;
                buildingDamageMultiplier = 3;
                hitShake = 1.8f;
                hitSound = Sounds.shotgun;
                hitSoundPitch = 1.6f;
                speed = 30;
                lifetime = 16;
                status = SFStatusEffects.breakdown;
                statusDuration = 180;
                trailSpacing = 9;
                trailEffect = new ParticleEffect() {{
                    particles = 1;
                    length = 0;
                    baseLength = 1;
                    lifetime = 10;
                    line = true;
                    randLength = false;
                    lenFrom = lenTo = 10;
                    strokeFrom = 3.5f;
                    colorFrom = colorTo = SFColor.energyYellow;
                    cone = 0;
                }};
                smokeEffect = Fx.bigShockwave;
                shootEffect = new ParticleEffect() {{
                    particles = 1;
                    sizeFrom = 7;
                    length = 0;
                    lifetime = 11;
                    colorFrom = colorTo = SFColor.energyYellow;
                }};
                hitEffect = new MultiEffect(
                        new ParticleEffect() {{
                            particles = 9;
                            sizeFrom = 9;
                            length = 20;
                            baseLength = 28;
                            lifetime = 25;
                            colorFrom = SFColor.energyYellow.cpy().a(0.4f);
                            colorTo = SFColor.energyYellow.cpy().a(0);
                        }},
                        new ParticleEffect() {{
                            particles = 9;
                            line = true;
                            lenFrom = 32;
                            strokeFrom = 2;
                            length = 50;
                            lifetime = 22;
                            colorFrom = SFColor.energyYellow;
                            colorTo = SFColor.energyYellow;
                        }},
                        new WaveEffect() {{
                            lifetime = 15;
                            sizeTo = 45;
                            strokeFrom = 5;
                            colorFrom = colorTo = SFColor.energyYellow;
                        }}
                );
            }};
            weapons.add(
                    new Weapon(name("blade-nuke")) {{
                        rotate = false;
                        reload = 280;
                        x = 9;
                        shootSound = Sounds.missileLarge;
                        shootCone = 360;
                        minWarmup = 0.9f;
                        shootWarmupSpeed = 0.022f;
                        baseRotation = -45;
                        bullet = new BulletType() {{
                            keepVelocity = false;
                            collidesAir = true;
                            hitShake = 18;
                            speed = 0;
                            shootEffect = Fx.massiveExplosion;
                            spawnUnit = new MissileUnitType("blade-missile") {{
                                outlineColor = SFColor.darkOutline;
                                missileAccelTime = 60;
                                homingDelay = 70;
                                speed = 6f;
                                lifetime = 290;
                                rotateSpeed = 2.25f;
                                hitSize = 20;
                                health = 3600;
                                armor = 15;
                                targetAir = true;
                                collidesAir = true;
                                deathSound = Sounds.explosionbig;
                                engineColor = trailColor = SFColor.enemyRedLight;
                                trailLength = 35;
                                engineLayer = 110;
                                engineOffset = 16;
                                engineSize = 4;
                                abilities.add(new MoveEffectAbility() {{
                                    rotateEffect = true;
                                    interval = 3;
                                    y = -8;
                                    effect = new ParticleEffect() {{
                                        particles = 3;
                                        sizeFrom = 4;
                                        sizeTo = 10;
                                        lifetime = 33;
                                        length = 42;
                                        interp = Interp.fastSlow;
                                        colorFrom = SFColor.enemyRedLight;
                                        colorTo = SFColor.enemyRedLight.cpy().a(0);
                                        cone = 16;
                                    }};
                                    y = -8f;
                                }});
                                maxRange = 45;
                                weapons.add(new Weapon(name("blade-missile-nuke")) {{
                                    reload = 60;
                                    x = 0;
                                    mirror = false;
                                    rotate = true;
                                    shake = 10;
                                    shootSound = Sounds.none;
                                    shootOnDeath = true;
                                    shootCone = 360;
                                    bullet = new BulletType(0, 180) {{
                                        killShooter = true;
                                        instantDisappear = true;
                                        makeFire = true;
                                        maxRange = 50f;
                                        splashDamageRadius = 200;
                                        splashDamage = 1200;
                                        status = SFStatusEffects.shattered;
                                        statusDuration = 600;
                                        hitSound = Sounds.titanExplosion;
                                        hitSoundVolume = 8;
                                        hitShake = 15;
                                        shootEffect = new WrapEffect(Fx.scatheExplosion, SFColor.enemyRedLight);
                                        smokeEffect = despawnEffect = Fx.none;
                                        hitEffect = new MultiEffect(
                                                new WaveEffect() {{
                                                    interp = Interp.circleOut;
                                                    lifetime = 20;
                                                    sizeTo = 300;
                                                    strokeFrom = 22;
                                                    strokeTo = 10;
                                                    colorFrom = SFColor.enemyRedLight;
                                                    colorTo = SFColor.discDark.cpy().a(0.5f);
                                                }},
                                                new WaveEffect() {{
                                                    interp = Interp.circleOut;
                                                    startDelay = 10;
                                                    lifetime = 20;
                                                    sizeTo = 300;
                                                    strokeFrom = 22;
                                                    strokeTo = 10;
                                                    colorFrom = SFColor.enemyRedLight;
                                                    colorTo = SFColor.discDark.cpy().a(0.5f);
                                                }},
                                                new ParticleEffect() {{
                                                    particles = 26;
                                                    line = true;
                                                    strokeFrom = 5;
                                                    lenFrom = 36;
                                                    length = 230;
                                                    baseLength = 33;
                                                    lifetime = 22;
                                                    interp = Interp.pow10Out;
                                                    sizeInterp = Interp.pow3In;
                                                    colorFrom = SFColor.enemyRedLight;
                                                    colorTo = SFColor.discLight;
                                                }},
                                                new ParticleEffect() {{
                                                    particles = 1;
                                                    sizeTo = 200;
                                                    colorFrom = SFColor.enemyRedLight;
                                                    colorTo = SFColor.discLight;
                                                    sizeInterp = Interp.pow3Out;
                                                    lifetime = 20;
                                                    length = 0;
                                                }},
                                                new ParticleEffect() {{
                                                    particles = 1;
                                                    sizeFrom = 200;
                                                    sizeTo = 0;
                                                    colorFrom = SFColor.discLight;
                                                    colorTo = SFColor.discDark.cpy().a(0.5f);
                                                    startDelay = 18;
                                                    lifetime = 120;
                                                    length = 0;
                                                }}
                                        );
                                    }};
                                }});
                            }};
                        }};
                    }},
                    new Weapon(name("red-point-gun")) {{
                        reload = 15;
                        x = 13.25f;
                        y = 8.5f;
                        shootY = 4;
                        rotate = true;
                        alternate = true;
                        rotateSpeed = 4;
                        shake = 1;
                        shootSound = Sounds.railgun;
                        soundPitchMax = 1.5f;
                        bullet = bladePoint;
                    }},
                    new Weapon(name("red-point-gun")) {{
                        reload = 18.6f;
                        x = -19;
                        y = -17.75f;
                        shootY = 4;
                        rotate = true;
                        alternate = true;
                        rotateSpeed = 4;
                        shake = 1;
                        shootSound = Sounds.railgun;
                        soundPitchMax = 1.5f;
                        bullet = bladePoint;
                    }},
                    copy(redPointDefense, 8.25f, -16.25f)
            );
        }};
        titan = new UnitType("titan") {{
            constructor = UnitTypes.reign.constructor;
            outlineColor = SFColor.darkOutline;
            itemCapacity = 0;
            flying = false;
            speed = 0.76f;
            mechSideSway = 0.7f;
            mechFrontSway = 2.1f;
            rotateSpeed = 1.88f;
            baseRotateSpeed = 1.5f;
            hitSize = 75;
            health = 320000;
            armor = 230;
            canDrown = false;
            faceTarget = true;
            abilities.add(new StatusFieldAbility(StatusEffects.overclock, 360, 300, 160) {{
                applyEffect = activeEffect = Fx.none;
            }});
            abilities.add(new ShieldRegenFieldAbility(360, 12000, 60, 160) {{
                activeEffect = new WaveEffect() {{
                    lifetime = 42;
                    interp = Interp.circleOut;
                    sizeTo = 160;
                    strokeFrom = 12;
                    colorFrom = colorTo = SFColor.energyYellow.cpy().a(0.45f);
                }};
            }});
            weapons.add(
                    new Weapon(name("titan-weapon")) {{
                        reload = 28;
                        shoot = new ShootSpread(46, 0);
                        inaccuracy = 10.5f;
                        shootCone = 60;
                        x = 44;
                        y = 0;
                        shootY = 30;
                        rotate = false;
                        top = false;
                        shake = 5;
                        recoil = 6;
                        shootSound = Sounds.largeCannon;
                        velocityRnd = 0.35f;
                        bullet = new BasicBulletType(23, 146f) {{
                            pierce = pierceBuilding = true;
                            pierceArmor = true;
                            pierceCap = 4;
                            knockback = 16;
                            lifetime = 17.3f;
                            width = 13;
                            height = 24;
                            despawnEffect = Fx.hitBulletBig;
                            hitEffect = new ParticleEffect() {{
                                particles = 6;
                                line = true;
                                lifetime = 10;
                                strokeFrom = 3.35f;
                                lenFrom = 26;
                                length = 100;
                                interp = Interp.fastSlow;
                                sizeInterp = Interp.pow5;
                                colorFrom = Pal.bulletYellow;
                                colorTo = Pal.bulletYellowBack;
                                cone = 35;
                            }};
                            shootEffect = new ParticleEffect() {{
                                particles = 1;
                                interp = Interp.fastSlow;
                                sizeFrom = 8;
                                length = 35;
                                lifetime = 16;
                                colorFrom = colorTo = Color.white;
                                lightOpacity = 0.5f;
                                cone = 16;
                            }};
                        }};
                    }},
                    new Weapon(name("red-cannon")) {{
                        x = 93 / 4f;
                        y = -37 / 4f;
                        rotate = true;
                        rotateSpeed = 6.4f;
                        shoot = new ShootPattern() {{
                            shots = 3;
                            shotDelay = 6;
                        }};
                        reload = 70;
                        alternate = false;
                        autoFindTarget = true;
                        autoTarget = true;
                        shootSound = Sounds.cannon;
                        shootY = 14;
                        bullet = new BasicBulletType(10, 65, "missile-large") {{
                            frontColor = SFColor.enemyRedLight;
                            backColor = SFColor.enemyRedDark;
                            trailColor = SFColor.enemyRedLight.cpy().a(0.5f);
                            splashDamage = 40;
                            splashDamageRadius = 20;
                            status = SFStatusEffects.shattered;
                            statusDuration = 80;
                            trailLength = 4;
                            trailWidth = 3;
                            hitShake = 2;
                            width = 8;
                            height = 16;
                            shootEffect = Fx.shootBig2;
                            smokeEffect = Fx.shootBigSmoke2;
                            hitSound = Sounds.explosion;
                            hitEffect = new ExplosionEffect() {{
                                waveRad = 22;
                                waveLife = 10;
                                lifetime = 30;
                                smokes = 6;
                                smokeSize = 4;
                                smokeRad = 30;
                                smokeColor = waveColor = SFColor.enemyRedLight;
                                sparks = 0;
                            }};
                            despawnEffect = Fx.flakExplosionBig;
                        }};
                    }}
            );
        }};
        vast = new UnitType("vast") {{
            constructor = UnitTypes.omura.constructor;
            outlineColor = SFColor.darkOutline;
            itemCapacity = 0;
            flying = false;
            speed = 0.57f;
            rotateSpeed = 0.45f;
            trailLength = 120;
            waveTrailX = 24;
            waveTrailY = -48;
            trailScl = 5;
            hitSize = 100;
            health = 375000;
            armor = 77;
            faceTarget = true;
            forceMultiTarget = false;
            weapons.addAll(
                    new Weapon(name("vast-radar")) {{
                        reload = 2100;
                        x = 0;
                        recoil = 0;
                        shootCone = 360;
                        rotate = false;
                        mirror = false;
                        minWarmup = 0.96f;
                        targetInterval = 120;
                        targetSwitchInterval = 60;
                        shootWarmupSpeed = 0.0075f;
                        shootStatus = SFStatusEffects.fullFire;
                        shootStatusDuration = 180;
                        shootSound = Sounds.lasercharge;
                        parts.add(new ShapePart() {{
                            rotateSpeed = 0.05f;
                            sides = 4;
                            color = SFColor.enemyRedLight.cpy().a(0f);
                            colorTo = SFColor.enemyRedLight;
                            hollow = true;
                            stroke = 0;
                            strokeTo = 3;
                            radius = 160;
                            radiusTo = 90;
                            lightOpacity = 1;
                            layer = 60;
                        }});
                        bullet = new BulletType(400, 10) {{
                            lifetime = 1;
                            instantDisappear = true;
                            shootEffect = new WaveEffect() {{
                                interp = Interp.circleOut;
                                lifetime = 120;
                                sizeTo = 80;
                                strokeFrom = 10;
                                colorFrom = SFColor.discLight;
                                colorTo = SFColor.discDark;
                            }};
                            smokeEffect = Fx.none;
                            splashDamage = 10;
                            splashDamageRadius = 400;
                            collidesTiles = false;
                            hitEffect = despawnEffect = Fx.none;
                            status = SFStatusEffects.marked;
                            statusDuration = 300;
                        }};
                    }},
                    new Weapon(name("vast-missile")) {{
                        x = 28.5f;
                        y = -32;
                        baseRotation = -45;
                        rotate = true;
                        rotateSpeed = 2.4f;
                        alternate = false;
                        inaccuracy = 3;
                        shootSound = Sounds.missile;
                        reload = 76;
                        shootY = 0;
                        shoot = new ShootBarrel() {{
                            shots = 3;
                            shotDelay = 12;
                            barrels = new float[]{
                                    -3, 6.5f, 0,
                                    3, 6.5f, 0
                            };
                        }};
                        xRand = 2;
                        bullet = new FlakBulletType(13, 50) {{
                            splashDamage = 250;
                            splashDamageRadius = 40;
                            lightningDamage = 38;
                            lightning = 5;
                            lightningLength = 2;
                            lightningLengthRand = 2;
                            lightColor = SFColor.enemyRedLight;
                            shrinkY = 0;
                            homingRange = 80;
                            homingPower = 0.1f;
                            homingDelay = 8;
                            lifetime = 44;
                            hitSound = Sounds.explosion;
                            width = 13;
                            height = 46;
                            hitShake = 3;
                            sprite = "sfire-mod-missile2";
                            frontColor = SFColor.smoke;
                            backColor = SFColor.enemyRedLight;
                            trailLength = 40;
                            trailWidth = 2;
                            trailColor = Color.white.cpy().a(0.5f);
                            trailChance = 0.45f;
                            trailEffect = new ParticleEffect() {{
                                particles = 3;
                                length = 11;
                                baseLength = 1;
                                lifetime = 45;
                                interp = Interp.pow10Out;
                                colorFrom = colorTo = SFColor.smoke.cpy().a(0.45f);
                                sizeFrom = 3;
                            }};
                            shootEffect = Fx.shootTitan;
                            smokeEffect = Fx.shootPyraFlame;
                            hitEffect = new ExplosionEffect() {{
                                waveLife = 10;
                                waveRad = 45;
                                waveStroke = 8;
                                waveColor = SFColor.enemyRedLight;
                                sparkRad = 56;
                                sparkStroke = 2.5f;
                                sparkLen = 13;
                                smokes = 10;
                                smokeRad = 33;
                                lifetime = 35;
                                smokeColor = SFColor.enemyRedLight.cpy().a(0.56f);
                                sparkColor = Pal.bulletYellow;
                            }};
                        }};
                    }},
                    new Weapon(name("vast-m")) {{
                        x = 0;
                        y = 16;
                        recoil = 0;
                        reload = 40;
                        shootCone = 360;
                        rotate = false;
                        shootSound = Sounds.missileSmall;
                        controllable = false;
                        autoTarget = true;
                        inaccuracy = 90;
                        shootWarmupSpeed = 0.05f;
                        minWarmup = 0.8f;
                        targetInterval = 5;
                        targetSwitchInterval = 5;
                        shoot = new ShootBarrel() {{
                            shots = 2;
                            shotDelay = 12;
                            barrels = new float[]{
                                    2, 12, 0,
                                    2, 8.5f, 0,
                                    2, 5, 0,
                                    4.75f, 1.25f, 0,
                                    4.75f, 6.75f, 0,
                                    2.75f, 0.5f, 0,
                                    2.75f, -3.25f, 0,
                                    5.5f, 2.25f, 0,
                                    5.5f, -1.5f, 0,
                                    5.5f, -5, 0
                            };
                        }};
                        parts.add(new RegionPart("-top") {{
                            y = 4;
                            mirror = false;
                            moveX = -8;
                            outline = false;
                            mixColorTo = color = Color.white;
                            mixColor = colorTo = Color.white.cpy().a(0);
                        }});
                        bullet = new MissileBulletType(7, 75, "sfire-mod-missile1") {{
                            absorbable = false;
                            keepVelocity = false;
                            reflectable = false;
                            hitShake = 3;
                            splashDamage = 225;
                            splashDamageRadius = 50;
                            status = SFStatusEffects.shattered;
                            statusDuration = 160;
                            frontColor = SFColor.smoke;
                            backColor = SFColor.enemyRedLight;
                            homingPower = 0.2f;
                            homingDelay = 8;
                            homingRange = 400;
                            shootEffect = Fx.none;
                            smokeEffect = Fx.shootPyraFlame;
                            hitEffect = new ExplosionEffect() {{
                                lifetime = 22;
                                waveStroke = 6;
                                waveLife = 11;
                                waveRad = 55;
                                smokes = 12;
                                smokeColor = SFColor.smoke;
                                sparkColor = SFColor.enemyRedLight;
                                sparks = 11;
                                sparkRad = 55;
                                sparkStroke = 1.25f;
                                sparkLen = 16;
                            }};
                            despawnEffect = Fx.flakExplosionBig;
                            hitSoundVolume = 5;
                            trailChance = 1;
                            trailEffect = new ParticleEffect() {{
                                particles = 5;
                                sizeFrom = 2.5f;
                                length = -30;
                                lifetime = 20;
                                lightOpacity = 0.2f;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow5In;
                                colorTo = SFColor.smoke.cpy().a(0.4f);
                                cone = 10;
                            }};
                            trailRotation = true;
                            shrinkY = 0;
                            drag = -0.015f;
                            lifetime = 61;
                            width = 14;
                            height = 50;
                        }};
                    }},
                    new Weapon(name("vast-m2")) {{
                        x = 0;
                        y = 16;
                        recoil = 0;
                        reload = 60;
                        shootCone = 360;
                        rotate = false;
                        shootSound = Sounds.missile;
                        controllable = false;
                        autoTarget = true;
                        inaccuracy = 90;
                        shootWarmupSpeed = 0.05f;
                        minWarmup = 0.83f;
                        shoot = new ShootBarrel() {{
                            shots = 4;
                            shotDelay = 6;
                            barrels = new float[]{
                                    -2.75f, 0.5f, 0,
                                    -2.75f, -3.25f, 0,
                                    -5.5f, 2.25f, 0,
                                    -5.5f, -1.5f, 0,
                                    5.5f, -5, 0,
                                    -2, 12, 0,
                                    -2, 8.5f, 0,
                                    -2, 5, 0,
                                    -4.75f, 1.25f, 0,
                                    -4.75f, 6.75f, 0
                            };
                        }};
                        bullet = new EmpBulletType() {{
                            hittable = false;
                            keepVelocity = false;
                            reflectable = false;
                            timeIncrease = 1;
                            buildingDamageMultiplier = 0.5f;
                            powerDamageScl = 6;
                            powerSclDecrease = 0.78f;
                            unitDamageScl = 1.15f;
                            suppressionDuration = 300;
                            suppressionRange = 220;
                            hitShake = 1;
                            radius = 92;
                            splashDamage = 88;
                            splashDamageRadius = 46;
                            damage = 60;
                            status = StatusEffects.slow;
                            statusDuration = 8;
                            lightning = 2;
                            lightningLength = 5;
                            lightningLengthRand = 3;
                            lightningColor = SFColor.enemyRedLight;
                            frontColor = Color.white;
                            backColor = SFColor.enemyRedLight;
                            homingPower = 0.2f;
                            homingDelay = 8;
                            homingRange = 400;
                            shootEffect = new WaveEffect() {{
                                lifetime = 35;
                                interp = Interp.circleOut;
                                sizeTo = 10;
                                strokeFrom = 4;
                                colorFrom = SFColor.enemyRedLight;
                                colorTo = SFColor.enemyRedLight.cpy().a(0.55f);
                            }};
                            smokeEffect = Fx.none;
                            hitPowerEffect = new ParticleEffect() {{
                                particles = 5;
                                line = true;
                                length = 50;
                                lifetime = 16;
                                lenFrom = 16;
                                strokeFrom = 2;
                                colorFrom = colorTo = SFColor.enemyRedLight;
                                cone = 10;
                            }};
                            hitEffect = new WrapEffect(Fx.dynamicSpikes, SFColor.enemyRedLight, 50);
                            hitSound = Sounds.laser;
                            hitSoundVolume = 0.5f;
                            despawnEffect = new ParticleEffect() {{
                                particles = 1;
                                sizeFrom = 5f;
                                length = 0;
                                lifetime = 50;
                                sizeInterp = Interp.pow5In;
                                colorTo = SFColor.enemyRedLight;
                            }};
                            sprite = "missile-large";
                            trailEffect = Fx.circleColorSpark;
                            trailColor = SFColor.enemyRedLight;
                            trailWidth = 3;
                            trailLength = 9;
                            shrinkY = 0;
                            speed = 8;
                            drag = -0.012f;
                            lifetime = 59;
                            width = 10;
                            height = 13;
                        }};
                    }},
                    copyRotate(vastGun, 16, 16, -45),
                    copyRotate(vastGun, -22, -10, 90),
                    copy(redPointDefense, 21.5f, 32f),
                    copy(redPointDefense, 13, 42.5f),
                    copy(redPointDefense, 6, -59f),
                    new PointDefenseWeapon(name("red-cannon")) {{
                        x = 0;
                        y = -48;
                        mirror = false;
                        reload = 9;
                        targetInterval = 10;
                        targetSwitchInterval = 2f;
                        shootSound = Sounds.shoot;
                        rotateSpeed = 6;
                        recoil = 0;
                        shootY = 1.25f;
                        bullet = new BulletType() {{
                            shootEffect = Fx.sparkShoot;
                            hitEffect = Fx.pointHit;
                            maxRange = 320;
                            damage = 60;
                        }};
                    }}
            );
        }};

        liXian = new UnitType("ordinance") {{
            constructor = UnitTypes.reign.constructor;
            researchCostMultiplier = 0.1f;
            hitSize = 48;
            canDrown = false;
            armor = 36;
            speed = 0.58f;
            rotateSpeed = 1.44f;
            baseRotateSpeed = 1.3f;
            health = 76000;
            mechSideSway = 0.8f;
            mechFrontSway = 2.2f;
            immunities.add(StatusEffects.disarmed);
            weapons.add(
                    new Weapon(name("ordinance-weapon")) {{
                        reload = 5.5f;
                        shake = 3;
                        recoil = 5;
                        x = 28;
                        y = -1;
                        shootY = 20;
                        rotate = false;
                        top = false;
                        inaccuracy = 0.5f;
                        shootSound = Sounds.shootBig;
                        ejectEffect = Fx.casing4;
                        shootCone = 15;
                        bullet = new BasicBulletType(14.3f, 115) {{
                            splashDamage = 30;
                            splashDamageRadius = 40;
                            pierce = true;
                            pierceBuilding = true;
                            pierceCap = 10;
                            lifetime = 28;
                            hitSound = Sounds.explosion;
                            shootEffect = Fx.shootBig2;
                            smokeEffect = Fx.shootBigSmoke2;
                            trailLength = 7;
                            trailWidth = 3.3f;
                            trailColor = backColor = Color.valueOf("FFA05C");
                            width = 14;
                            height = 33;
                            hitEffect = new ParticleEffect() {{
                                particles = 4;
                                line = true;
                                lifetime = 10;
                                strokeFrom = 3;
                                lenFrom = 16;
                                length = 60;
                                interp = Interp.fastSlow;
                                sizeInterp = Interp.pow5;
                                colorFrom = Pal.bulletYellow;
                                colorTo = Pal.bulletYellowBack;
                                cone = 60;
                            }};
                            despawnEffect = Fx.flakExplosionBig;
                        }};
                    }},
                    new Weapon(name("ordinance-gun")) {{
                        reload = 50;
                        shootY = 7.5f;
                        x = 16;
                        y = 12;
                        controllable = false;
                        autoTarget = true;
                        shootStatusDuration = 30;
                        shootCone = 180;
                        shootStatus = SFStatusEffects.stormed;
                        shoot = new ShootSpread(5, 5);
                        recoil = 2;
                        rotate = true;
                        rotateSpeed = 6;
                        rotationLimit = 50;
                        shootSound = Sounds.spark;
                        inaccuracy = 16;
                        bullet = new LightningBulletType() {{
                            damage = 32;
                            lightningColor = Color.valueOf("FFA05C");
                            lightningLength = 12;
                            lightningLengthRand = 12;
                            shootEffect = new ParticleEffect() {{
                                particles = 2;
                                line = true;
                                length = 55;
                                lifetime = 22;
                                colorFrom = Color.valueOf("FFA05C");
                                colorTo = Color.valueOf("D86E56");
                            }};
                            despawnEffect = smokeEffect = Fx.none;
                            hitEffect = Fx.hitLancer;
                        }};
                    }}
            );
        }};
        diXing = new UnitType("libra") {{
            constructor = UnitTypes.corvus.constructor;
            researchCostMultiplier = 0.1f;
            armor = 15;
            speed = 0.3f;
            rotateSpeed = 1.52f;
            hitSize = 52;
            health = 73600;
            legCount = 6;
            lockLegBase = true;
            legContinuousMove = true;
            legSplashDamage = 220;
            legSplashRange = 18;
            legMoveSpace = 1.4f;
            legExtension = -8;
            legBaseOffset = 20;
            legLength = 32;
            legForwardScl = 0.7f;
            stepShake = 0.77f;
            buildRange = 360;
            buildSpeed = 8.5f;
            buildBeamOffset = 24;
            rippleScale = 1;
            hovering = true;
            allowLegStep = true;
            drownTimeMultiplier = 8;
            immunities.addAll(StatusEffects.electrified, StatusEffects.unmoving, StatusEffects.disarmed, SFStatusEffects.scrambled);
            abilities.addAll(
                    new StatusFieldAbility(SFStatusEffects.strengthen, 480, 600, 340) {{
                        activeEffect = new WaveEffect() {{
                            lifetime = 56;
                            interp = Interp.circleOut;
                            sizeTo = 340;
                            strokeFrom = 9;
                            colorFrom = colorTo = Pal.heal;
                        }};
                    }},
                    new ShieldArcAbility() {{
                        radius = 80;
                        width = 25;
                        max = 10000;
                        regen = 25;
                        cooldown = 20;
                        angle = 140;
                    }}
            );
            speed = 0.3f;
            hitSize = 52;
            health = 77600;
            armor = 28;
            rotateSpeed = 0.8f;
            faceTarget = true;
            singleTarget = true;
            weapons.add(
                    new Weapon(name("libra-laser")) {{
                        reload = 70;
                        shootY = 16f;
                        x = 28;
                        shake = 3;
                        recoil = 3;
                        layerOffset = 0.05f;
                        rotate = false;
                        shootCone = 5;
                        top = false;
                        shootSound = Sounds.malignShoot;
                        cooldownTime = 120;
                        heatColor = Color.red;
                        bullet = new BasicBulletType(25, 100, "circle-bullet") {{
                            frontColor = Color.white;
                            backColor = trailColor = Pal.heal;
                            healPercent = 11;
                            status = SFStatusEffects.disRepair;
                            statusDuration = 120;
                            splashDamage = 80;
                            splashDamageRadius = 60;
                            trailLength = 3;
                            trailWidth = 1.5f;
                            trailInterval = 20;
                            trailEffect = new WaveEffect() {{
                                lifetime = 20;
                                sizeTo = 30;
                                strokeFrom = 3;
                                colorFrom = colorTo = Pal.heal;
                            }};
                            shootEffect = Fx.bigShockwave;
                            shrinkY = 0;
                            absorbable = false;
                            reflectable = false;
                            pierce = collidesTeam = true;
                            lifetime = 19.8f;
                            width = 14;
                            height = 14;
                            despawnEffect = Fx.none;
                            hitSound = Sounds.laser;
                            hitEffect = new MultiEffect(
                                    new ExplosionEffect() {{
                                        sparks = 32;
                                        sparkStroke = 6;
                                        sparkLen = 30;
                                        sparkRad = 70;
                                        lifetime = 20;
                                        smokes = 0;
                                        sparkColor = waveColor = Pal.heal;
                                        waveRad = 60;
                                        waveLife = 15;
                                        waveStroke = 3;
                                    }},
                                    new ParticleEffect() {{
                                        region = "sfire-mod-star";
                                        particles = 1;
                                        length = baseLength = 0;
                                        lifetime = 15;
                                        spin = 3.34f;
                                        sizeInterp = Interp.fastSlow;
                                        sizeFrom = 130;
                                        colorFrom = colorTo = Pal.heal;
                                    }}
                            );
                            spawnBullets.add(
                                    new LaserBulletType(216) {{
                                        length = 496;
                                        width = 27;
                                        healPercent = 5;
                                        collidesTeam = true;
                                        sideAngle = 30;
                                        sideWidth = 0.8f;
                                        sideLength = 70;
                                        lifetime = 50;
                                        status = SFStatusEffects.scrambled;
                                        statusDuration = 60;
                                        colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                                    }},
                                    new LaserBulletType(216) {{
                                        length = 496;
                                        width = 27;
                                        healPercent = 5;
                                        collidesTeam = true;
                                        sideAngle = 126;
                                        sideWidth = 1f;
                                        sideLength = 140;
                                        lifetime = 50;
                                        status = SFStatusEffects.scrambled;
                                        statusDuration = 60;
                                        colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                                    }}
                            );
                        }};
                    }},
                    new Weapon(name("libra-bomb")) {{
                        reload = 420;
                        x = 0;
                        shootY = 24;
                        shoot.shotDelay = 5;
                        shoot.shots = 6;
                        parts.add(new ShapePart() {{
                            circle = true;
                            hollow = false;
                            radius = 0;
                            radiusTo = 5;
                            y = 24;
                            color = Pal.heal;
                            layerOffset = -0.001f;
                        }});
                        parts.add(new ShapePart() {{
                            circle = true;
                            hollow = false;
                            radius = 0;
                            radiusTo = 5;
                            y = 24;
                            color = Pal.heal;
                            layer = 100;
                            progress = PartProgress.reload;
                        }});
                        parts.add(new RegionPart("-front") {{
                            layerOffset = -0.001f;
                            y = -24;
                            moveY = 24;
                            mirror = false;
                        }});
                        rotate = false;
                        mirror = false;
                        cooldownTime = 300;
                        inaccuracy = 0;
                        shootSound = Sounds.plasmadrop;
                        recoil = 0;
                        bullet = new PointBulletType() {{
                            damage = 20;
                            lifetime = 8;
                            speed = 60;
                            hitSound = Sounds.none;
                            despawnEffect = new ParticleEffect() {{
                                particles = 1;
                                sizeFrom = 16;
                                length = 0;
                                lifetime = 60;
                                colorFrom = Pal.heal;
                                colorTo = Pal.heal.cpy().a(0);
                            }};
                            shootEffect = Fx.bigShockwave;
                            smokeEffect = Fx.none;
                            hitEffect = new WaveEffect() {{
                                lifetime = 60;
                                sizeFrom = 100;
                                sizeTo = 0;
                                strokeFrom = 4;
                                strokeTo = 1;
                                colorFrom = colorTo = Pal.heal;
                            }};
                            trailEffect = Fx.none;
                            fragBullets = 1;
                            fragAngle = 180;
                            fragRandomSpread = 40;
                            fragVelocityMin = fragVelocityMax = 1;
                            fragBullet = new PointBulletType() {{
                                lifetime = 100;
                                speed = 10;
                                trailEffect = hitEffect = Fx.none;
                                despawnEffect = new MultiEffect(
                                        new ParticleEffect() {{
                                            line = true;
                                            particles = 11;
                                            strokeFrom = 8;
                                            lenFrom = 46;
                                            length = -103;
                                            baseLength = 10;
                                            lifetime = 30;
                                            colorFrom = colorTo = Pal.heal;
                                            cone = 60;
                                        }},
                                        new ParticleEffect() {{
                                            particles = 1;
                                            sizeTo = 30;
                                            length = baseLength = 0;
                                            lifetime = 20;
                                            colorFrom = Pal.heal;
                                            colorTo = Color.white.cpy().a(0);
                                        }}
                                );
                                fragBullets = 1;
                                fragAngle = 180;
                                fragRandomSpread = 5;
                                fragVelocityMin = 0.95f;
                                fragVelocityMax = 1.05f;
                                fragBullet = new BasicBulletType(10, 400, "circle-bullet") {{
                                    shieldDamageMultiplier = 3.5f;
                                    splashDamage = 180;
                                    splashDamageRadius = 150;
                                    scaledSplashDamage = true;
                                    buildingDamageMultiplier = 1.25f;
                                    lifetime = 100;
                                    healPercent = 24;
                                    collides = false;
                                    shrinkX = shrinkY = 0.4f;
                                    width = 30;
                                    height = 30;
                                    trailLength = 50;
                                    trailWidth = 5;
                                    trailColor = backColor = Pal.heal;
                                    frontColor = Color.white;
                                    trailEffect = new ParticleEffect() {{
                                        particles = 4;
                                        region = "sfire-mod-loz";
                                        sizeFrom = 7;
                                        sizeTo = 16;
                                        length = 42;
                                        lifetime = 33;
                                        colorFrom = Pal.heal;
                                        colorTo = Pal.heal.cpy().a(0);
                                    }};
                                    hitShake = 20;
                                    status = SFStatusEffects.scrambled;
                                    statusDuration = 30;
                                    pierceBuilding = true;
                                    hitSound = Sounds.plasmaboom;
                                    hitEffect = new MultiEffect(
                                            new ExplosionEffect() {{
                                                lifetime = 40;
                                                smokes = 0;
                                                sparkColor = waveColor = Pal.heal;
                                                sparks = 60;
                                                sparkStroke = 6;
                                                sparkLen = 85;
                                                sparkRad = 133;
                                                waveStroke = 19;
                                                waveRad = 160;
                                                waveLife = 15;
                                            }},
                                            new ParticleEffect() {{
                                                particles = 30;
                                                sizeFrom = 10;
                                                sizeInterp = Interp.pow2Out;
                                                interp = Interp.pow10Out;
                                                length = 130;
                                                baseLength = 30;
                                                colorFrom = Pal.heal.cpy().a(0.88f);
                                                colorTo = Pal.heal.cpy().a(0);
                                            }},
                                            new ParticleEffect() {{
                                                particles = 1;
                                                sizeFrom = 160;
                                                sizeInterp = Interp.pow2Out;
                                                length = baseLength = 0;
                                                colorFrom = Pal.heal.cpy().a(0.4f);
                                                colorTo = Pal.heal.cpy().a(0);
                                            }}
                                    );
                                    despawnEffect = Fx.none;
                                    parts.add(new FlarePart() {{
                                        progress = PartProgress.life.slope().curve(Interp.fastSlow);
                                        radius = 0f;
                                        radiusTo = 85;
                                        stroke = 15;
                                        color1 = Pal.heal.cpy();
                                        color2 = Pal.heal.cpy().a(0.55f);
                                        rotation = 45;
                                        spinSpeed = 4.6f;
                                    }});
                                }};
                            }};
                        }};
                    }}
            );
        }};
        panLong = new UnitType("agelenid") {{
            constructor = UnitTypes.corvus.constructor;
            researchCostMultiplier = 0.1f;
            armor = 30;
            speed = 0.76f;
            rotateSpeed = 1.12f;
            hitSize = 48;
            health = 81000;
            legPairOffset = 3;
            legExtension = -20;
            legBaseOffset = 8;
            stepShake = 1.2f;
            legCount = 8;
            legLength = 80;
            legMoveSpace = 1;
            rippleScale = 1.6f;
            immunities.addAll(StatusEffects.sapped, StatusEffects.unmoving, StatusEffects.disarmed, SFStatusEffects.scrambled, SFStatusEffects.breakdown);
            legSplashRange = 46;
            legSplashDamage = 88;
            drownTimeMultiplier = 6;
            hovering = true;
            allowLegStep = true;
            weapons.addAll(
                    new Weapon(name("agelenid-weapon")) {{
                        x = 24;
                        rotate = false;
                        top = false;
                        reload = 150;
                        shootCone = 30;
                        shootY = 16;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.missileLaunch;
                        bullet = new BasicBulletType(2, 120, "sfire-mod-dragon") {{
                            lifetime = 300;
                            homingDelay = 30;
                            homingPower = 0.5f;
                            homingRange = 60;
                            width = 32;
                            height = 65;
                            weaveMag = 3;
                            weaveScale = 10;
                            hitShake = 4;
                            shrinkX = shrinkY = 0;
                            hitSound = Sounds.explosionbig;
                            pierce = pierceBuilding = true;
                            status = StatusEffects.sapped;
                            statusDuration = 600;
                            shootEffect = new WrapEffect(Fx.shootBigColor, Pal.sap);
                            smokeEffect = new ParticleEffect() {{
                                particles = 8;
                                sizeFrom = 6;
                                length = 50;
                                lifetime = 25;
                                colorFrom = Pal.sap;
                                cone = 30;
                            }};
                            splashDamageRadius = 40;
                            splashDamage = 50;
                            frontColor = Pal.sapBulletBack;
                            backColor = Pal.sapBullet;
                            trailLength = 60;
                            trailWidth = 6;
                            trailColor = Pal.sapBullet;
                            hitEffect = new ExplosionEffect() {{
                                sparks = 0;
                                waveLife = 15;
                                waveRad = 40;
                                waveStroke = 2;
                                waveColor = Pal.sapBullet;
                                lifetime = 25;
                                smokes = 6;
                                smokeSize = 15;
                                smokeSizeBase = 3;
                                smokeRad = 55;
                                smokeColor = Pal.sapBullet;
                            }};
                            despawnEffect = new ExplosionEffect() {{
                                sparks = 35;
                                sparkStroke = 5;
                                sparkRad = 75;
                                sparkLen = 55;
                                sparkColor = Pal.sapBullet;
                                lifetime = 15;
                                waveLife = 17;
                                waveRad = 60;
                                waveStroke = 4;
                                waveColor = Pal.sapBullet;
                                smokes = 16;
                                smokeSize = 9;
                                smokeRad = 45;
                                smokeColor = Pal.sapBullet;
                            }};
                            fragRandomSpread = 0;
                            fragBullets = 1;
                            fragBullet = new ShrapnelBulletType() {{
                                damage = 75;
                                lifetime = 15;
                                length = 110;
                                width = 12;
                                buildingDamageMultiplier = 0.83f;
                                pierceArmor = true;
                                serrationLenScl = 8;
                                serrationSpaceOffset = 60;
                                serrationFadeOffset = 0;
                                serrations = 8;
                                serrationWidth = 8;
                                fromColor = Pal.sapBullet;
                                toColor = Pal.sapBulletBack;
                            }};
                            intervalBullets = 1;
                            bulletInterval = 10f;
                            intervalRandomSpread = 180;
                            intervalSpread = 60;
                            intervalAngle = 0;
                            intervalBullet = new BasicBulletType(6, 60, "sfire-mod-dragon") {{
                                splashDamage = 40;
                                splashDamageRadius = 16;
                                scaledSplashDamage = true;
                                absorbable = false;
                                lifetime = 30;
                                homingPower = 0.5f;
                                homingRange = 90;
                                width = 18;
                                height = 35;
                                weaveMag = 3;
                                weaveScale = 3;
                                shrinkX = shrinkY = 0;
                                hitSound = Sounds.explosion;
                                knockback = 5;
                                status = StatusEffects.sapped;
                                statusDuration = 180;
                                frontColor = Pal.sapBulletBack;
                                backColor = Pal.sapBullet;
                                trailLength = 10;
                                trailWidth = 2;
                                trailColor = Pal.sapBullet;
                                hitEffect = new ExplosionEffect() {{
                                    smokes = 0;
                                    waveLife = 15;
                                    waveRad = 40;
                                    waveStroke = 2;
                                    waveColor = Pal.sapBullet;
                                    lifetime = 25;
                                    sparks = 5;
                                    sparkColor = Pal.sapBullet;
                                    sparkLen = 35;
                                    sparkStroke = 2;
                                    sparkRad = 45;
                                }};
                                despawnEffect = new ParticleEffect() {{
                                    particles = 1;
                                    sizeFrom = 5;
                                    length = 0;
                                    lifetime = 35;
                                    colorFrom = colorTo = Pal.sapBullet;
                                }};
                            }};
                        }};
                    }},
                    new Weapon(name("agelenid-sap")) {{
                        x = -7;
                        y = 20;
                        shoot.shotDelay = 3;
                        shoot.shots = 3;
                        shootSound = Sounds.sap;
                        top = true;
                        shootY = 8;
                        reload = 33;
                        ejectEffect = Fx.none;
                        rotate = true;
                        rotateSpeed = 5;
                        recoil = 2;
                        bullet = new SapBulletType() {{
                            lifetime = 30;
                            sapStrength = 2;
                            length = 90;
                            width = 0.7f;
                            knockback = -1.24f;
                            statusDuration = 60;
                            damage = 77;
                            status = StatusEffects.sapped;
                            shootEffect = Fx.shootSmall;
                            hitColor = color = Color.valueOf("bf92f9");
                            despawnEffect = Fx.none;
                        }};
                    }},
                    new Weapon(name("agelenid-sap")) {{
                        x = -16;
                        y = 12;
                        shoot.shotDelay = 3;
                        shoot.shots = 3;
                        shootSound = Sounds.sap;
                        top = true;
                        shootY = 8;
                        reload = 33;
                        ejectEffect = Fx.none;
                        rotate = true;
                        rotateSpeed = 5;
                        recoil = 2;
                        bullet = new SapBulletType() {{
                            lifetime = 30;
                            sapStrength = 2;
                            length = 90;
                            width = 0.7f;
                            knockback = -1.24f;
                            statusDuration = 60;
                            damage = 77;
                            status = StatusEffects.sapped;
                            shootEffect = Fx.shootSmall;
                            hitColor = color = Color.valueOf("bf92f9");
                            despawnEffect = Fx.none;
                        }};
                    }}
            );
        }};
        guangHan = new UnitType("hepta") {{
            constructor = UnitTypes.oct.constructor;
            researchCostMultiplier = 0.1f;
            payloadCapacity = 9 * 9 * 64;
            aiController = DefenderAI::new;
            speed = 0.68f;
            drag = 0.08f;
            accel = 0.07f;
            rotateSpeed = 1.2f;
            immunities.addAll(StatusEffects.slow, StatusEffects.unmoving, StatusEffects.muddy, StatusEffects.electrified, SFStatusEffects.repairX, SFStatusEffects.disRepair);
            hitSize = 98;
            buildSpeed = 9;
            buildBeamOffset = 11;
            flying = true;
            health = 90000;
            armor = 25;
            engineSize = 0;
            faceTarget = false;
            lowAltitude = true;
            setEnginesMirror(
                    new UnitEngine(36, -36, 10f, -45f),
                    new UnitEngine(36, 36, 10f, 45f)
            );
            abilities.add(new StatusFieldAbility(SFStatusEffects.repairX, 70, 60, 240) {{
                activeEffect = new WaveEffect() {{
                    lifetime = 10;
                    sizeFrom = sizeTo = 240;
                    strokeFrom = 8;
                    colorFrom = Pal.heal;
                    colorTo = Pal.heal.cpy().a(0);
                }};
            }});
            abilities.add(new EnergyFieldAbility(80, 120, 240) {{
                healPercent = 2.5f;
                effectRadius = 13;
                sectors = 6;
                sectorRad = 0.11f;
                sameTypeHealMult = 0.3f;
                shootSound = Sounds.shotgun;
                hitEffect = new ParticleEffect() {{
                    particles = 1;
                    sizeFrom = 16;
                    sizeTo = 35;
                    lifetime = 15;
                    colorFrom = Pal.heal;
                    colorTo = Pal.heal.cpy().a(0);
                }};
                damageEffect = Fx.chainLightning;
                status = SFStatusEffects.scrambled;
                statusDuration = 360;
                maxTargets = 18;
            }});
            abilities.add(new ShieldRegenFieldAbility(180, 1800, 80, 200) {{
                activeEffect = new WaveEffect() {{
                    interp = Interp.circleOut;
                    lifetime = 35;
                    sizeFrom = 16;
                    sizeTo = 240;
                    strokeFrom = 15;
                    colorFrom = colorTo = Pal.heal;
                }};
            }});
            weapons.addAll(
                    new Weapon(name("hepta-laser")) {{
                        reload = 85;
                        x = 0;
                        rotate = true;
                        rotateSpeed = 20;
                        recoil = 0;
                        inaccuracy = 1;
                        xRand = 8;
                        mirror = false;
                        shootSound = Sounds.laser;
                        shake = 3;
                        shoot.shotDelay = 3;
                        shoot.shots = 5;
                        bullet = new LaserBulletType(60) {{
                            healPercent = 2.5f;
                            width = 22;
                            length = 240;
                            status = SFStatusEffects.scrambled;
                            statusDuration = 16;
                            collidesTeam = true;
                            hitEffect = Fx.hitLaserBlast;
                            shootEffect = smokeEffect = Fx.none;
                            colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                        }};
                    }},
                    new RepairBeamWeapon(name("hepta-repair")) {{
                        x = 40;
                        y = -8;
                        shootY = 6;
                        beamWidth = 1;
                        targetBuildings = true;
                        mirror = true;
                        repairSpeed = 4.5f;
                        fractionRepairSpeed = 0.01f;
                        bullet = new BulletType() {{
                            maxRange = 220;
                        }};
                    }},
                    new RepairBeamWeapon(name("hepta-repair")) {{
                        x = 20;
                        y = 20;
                        shootY = 6;
                        beamWidth = 1;
                        targetBuildings = true;
                        mirror = true;
                        repairSpeed = 4.5f;
                        fractionRepairSpeed = 0.01f;
                        bullet = new BulletType() {{
                            maxRange = 220;
                        }};
                    }},
                    new RepairBeamWeapon(name("hepta-repair")) {{
                        x = 0;
                        y = -48;
                        shootY = 6;
                        beamWidth = 1;
                        targetBuildings = true;
                        mirror = false;
                        repairSpeed = 4.5f;
                        fractionRepairSpeed = 0.01f;
                        bullet = new BulletType() {{
                            maxRange = 220;
                        }};
                    }}
            );
            parts.add(
                    new ShapePart() {{
                        stroke = strokeTo = 2;
                        color = Color.valueOf("97FFA8AF");
                        circle = true;
                        hollow = true;
                        radius = radiusTo = 240;
                        layer = 110;
                    }},
                    new HaloPart() {{
                        rotateSpeed = 0;
                        sides = 3;
                        shapes = 6;
                        color = Color.valueOf("97FFA890");
                        tri = true;
                        hollow = true;
                        stroke = 0;
                        strokeTo = 0.1f;
                        radiusTo = radius = 480;
                        triLength = triLengthTo = 16;
                        haloRadius = 8;
                        haloRotation = 0;
                        haloRotateSpeed = -0.1f;
                        layer = 80;
                    }}
            );
        }};
        yuHui = new UnitType("sundown") {{
            constructor = UnitTypes.eclipse.constructor;
            researchCostMultiplier = 0.1f;
            health = 74000;
            armor = 33;
            speed = 0.62f;
            drag = 0.05f;
            accel = 0.04f;
            flying = true;
            hitSize = 86;
            lowAltitude = true;
            rotateSpeed = 0.78f;
            engineOffset = 40;
            engineSize = 16;
            BulletType sundownBullet = new FlakBulletType(10, 26) {{
                lifetime = 30.4f;
                splashDamage = 80;
                splashDamageRadius = 45f;
                collidesGround = true;
                shootEffect = Fx.shootBig;
                smokeEffect = Fx.shootBigSmoke;
                trailLength = 5;
                trailWidth = 4;
                lightningColor = trailColor = backColor = Color.valueOf("FFA05C");
                width = 12;
                height = 26;
                lightningLength = 10;
                lightning = 2;
                lightningLengthRand = 3;
                lightningDamage = 40;
                status = StatusEffects.blasted;
                hitEffect = Fx.flakExplosionBig;
                fragBullets = 3;
                fragBullet = new BasicBulletType(8, 13) {{
                    splashDamage = 25;
                    splashDamageRadius = 25;
                    lifetime = 10;
                    trailLength = 3;
                    trailWidth = 2;
                    lightningColor = trailColor = backColor = Color.valueOf("FFA05C");
                    width = 8;
                    height = 9;
                    lightningLength = 7;
                    lightning = 1;
                    lightningDamage = 13;
                    hitEffect = Fx.flakExplosion;
                }};
            }};
            weapons.addAll(
                    new Weapon(name("sundown-weapon")) {{
                        reload = 33;
                        x = 22.25f;
                        y = 22.75f;
                        inaccuracy = 6;
                        shoot.shotDelay = 3;
                        shoot.shots = 3;
                        shootY = 10.5f;
                        rotate = true;
                        rotateSpeed = 2.3f;
                        recoil = 2;
                        shake = 1;
                        shootSound = Sounds.shootBig;
                        ejectEffect = Fx.casing3;
                        bullet = sundownBullet;
                    }},
                    new Weapon(name("sundown-weapon")) {{
                        reload = 30;
                        x = 38.25f;
                        y = 5f;
                        inaccuracy = 6;
                        shoot.shotDelay = 3;
                        shoot.shots = 3;
                        shootY = 10.5f;
                        rotate = true;
                        rotateSpeed = 2.3f;
                        recoil = 2;
                        shake = 1;
                        shootSound = Sounds.shootBig;
                        ejectEffect = Fx.casing3;
                        bullet = sundownBullet;
                    }},
                    new PointDefenseWeapon(name("ordinance-gun")) {{
                        x = 18;
                        y = -20;
                        mirror = true;
                        rotateSpeed = 9;
                        rotate = true;
                        reload = 10;
                        targetInterval = 8;
                        targetSwitchInterval = 8;
                        bullet = new BulletType() {{
                            maxRange = 250;
                            damage = 80;
                            shootEffect = Fx.sparkShoot;
                            hitEffect = Fx.pointHit;
                        }};
                    }},
                    new Weapon(name("sundown-m")) {{
                        reload = 500;
                        rotate = false;
                        baseRotation = -90;
                        x = 18;
                        y = -20;
                        shootSound = Sounds.missileSmall;
                        shootWarmupSpeed = 0.085f;
                        minWarmup = 0.98f;
                        shake = 5;
                        shootCone = 360;
                        inaccuracy = 10;
                        xRand = 16;
                        shoot.shotDelay = 5;
                        shoot.shots = 9;
                        bullet = new BulletType() {{
                            speed = 0;
                            maxRange = 800;
                            keepVelocity = false;
                            shootEffect = Fx.shootTitan;
                            smokeEffect = Fx.shootBigSmoke;
                            spawnUnit = new MissileUnitType("sundown-missile") {{
                                targetAir = true;
                                speed = 5;
                                missileAccelTime = 40;
                                homingDelay = 40;
                                lifetime = 200;
                                hitSize = 10;
                                health = 600;
                                trailLength = 35;
                                engineLayer = 110;
                                engineOffset = 10;
                                engineSize = 3;
                                maxRange = 25;
                                /*abilities.add(new MoveEffectAbility(){{
                                    effect = new ParticleEffect(){{
                                        particles = 3;
                                        sizeFrom = 5;
                                        interp = Interp.pow5Out;
                                        sizeInterp = Interp.pow5;
                                        length = 25;
                                        baseLength = 2;
                                        lifetime = 93;
                                        colorFrom = Color.valueOf("FFA05C88");
                                        colorTo = Color.valueOf("FFA05C48");
                                        cone = 30;
                                    }};
                                    interval = 2;
                                    rotateEffect = true;
                                    y = -16;
                                }});*/
                                parts.add(new RegionPart("-wing") {{
                                    mirror = true;
                                    top = false;
                                    under = true;
                                    x = 4;
                                    y = -4;
                                    moveX = moveY = 4;
                                    rotation = -45;
                                    moveRot = 45;
                                    progress = PartProgress.life.curve(Interp.pow3Out);
                                }});
                                weapons.add(new Weapon() {{
                                    shootCone = 360;
                                    shake = 5;
                                    mirror = false;
                                    reload = 1;
                                    shootOnDeath = true;
                                    shootSound = Sounds.none;
                                    bullet = new BasicBulletType(10, 10) {{
                                        lifetime = 2.5f;
                                        instantDisappear = true;
                                        collides = false;
                                        hitSound = Sounds.titanExplosion;
                                        killShooter = true;
                                        scaledSplashDamage = true;
                                        splashDamageRadius = 50;
                                        splashDamage = 110;
                                        status = StatusEffects.melting;
                                        statusDuration = 70;
                                        shootEffect = Fx.massiveExplosion;
                                        despawnEffect = Fx.none;
                                        hitEffect = new ExplosionEffect() {{
                                            lifetime = 25;
                                            waveColor = smokeColor = Color.valueOf("FFA05C");
                                            smokes = 15;
                                            smokeRad = 50;
                                            smokeSize = 8;
                                            smokeSizeBase = 3;
                                            waveStroke = 5;
                                            waveRad = 60;
                                            waveLife = 20;
                                        }};
                                    }};
                                }});
                            }};
                        }};
                    }}
            );
        }};
        tengWang = new UnitType("dorudon") {{
            constructor = UnitTypes.omura.constructor;
            researchCostMultiplier = 0.1f;
            flying = false;
            faceTarget = false;
            speed = 0.48f;
            rotateSpeed = 0.75f;
            hitSize = 80;
            trailLength = 160;
            waveTrailX = 25;
            waveTrailY = -46;
            trailScl = 5.4f;
            health = 86000;
            armor = 38;
            weapons.addAll(
                    new Weapon("sei-launcher") {{
                        x = 17;
                        y = -29;
                        reload = 30;
                        shootCone = 60;
                        shoot = new ShootSpread(5, 8);
                        shootSound = Sounds.missile;
                        rotate = true;
                        rotateSpeed = 4;
                        inaccuracy = 6;
                        xRand = 6;
                        bullet = new MissileBulletType(6, 40) {{
                            splashDamageRadius = 45;
                            splashDamage = 48;
                            status = StatusEffects.blasted;
                            homingDelay = 5;
                            homingRange = 60;
                            homingPower = 0.35f;
                            shootEffect = Fx.shootBig2;
                            smokeEffect = Fx.shootSmallFlame;
                            hitEffect = Fx.flakExplosionBig;
                            despawnEffect = Fx.blastExplosion;
                            trailWidth = 2.5f;
                            trailLength = 9;
                            width = 12;
                            height = 13;
                            lifetime = 352 / 6f;
                        }};
                    }},
                    new Weapon("sei-launcher") {{
                        x = -32;
                        y = -40;
                        reload = 54;
                        shootCone = 60;
                        shoot.shotDelay = 1;
                        shoot.shots = 5;
                        shootSound = Sounds.missile;
                        rotate = true;
                        rotateSpeed = 4;
                        inaccuracy = 10;
                        xRand = 6;
                        bullet = new MissileBulletType(8, 44) {{
                            splashDamageRadius = 22;
                            splashDamage = 32;
                            homingDelay = 5;
                            homingRange = 60;
                            homingPower = 0.4f;
                            shootEffect = Fx.shootBig2;
                            smokeEffect = Fx.shootSmallFlame;
                            hitEffect = Fx.plasticExplosion;
                            despawnEffect = Fx.blastExplosion;
                            trailWidth = 2.5f;
                            trailLength = 9;
                            width = 12;
                            height = 13;
                            lifetime = 44;
                            fragBullets = 8;
                            fragBullet = new BasicBulletType(2.8f, 16) {{
                                despawnEffect = Fx.none;
                                width = 10;
                                height = 12;
                                shrinkY = 1;
                                lifetime = 15;
                                pierceBuilding = true;
                                pierce = true;
                                pierceCap = 2;
                                backColor = Pal.plastaniumBack;
                                frontColor = Pal.plastaniumFront;
                            }};
                        }};
                    }},
                    new Weapon(name("dorudon-weapon")) {{
                        reload = 160;
                        rotate = true;
                        rotateSpeed = 1.25f;
                        x = 0;
                        mirror = false;
                        inaccuracy = 0;
                        recoil = 12;
                        cooldownTime = 160;
                        shootSound = Sounds.release;
                        shake = 12;
                        bullet = new BasicBulletType(14, 950, "sfire-mod-arrow-bullet") {{
                            scaleLife = true;
                            shieldDamageMultiplier = 1.5f;
                            statusDuration = 200;
                            status = SFStatusEffects.breakdown;
                            pierceArmor = true;
                            shootEffect = new ParticleEffect() {{
                                interp = Interp.pow10Out;
                                sizeInterp = Interp.pow10In;
                                particles = 35;
                                sizeFrom = 9;
                                length = 65;
                                baseLength = 59;
                                lifetime = 65;
                                colorTo = SFColor.energyYellow;
                                cone = 30;
                            }};
                            despawnEffect = Fx.none;
                            hitEffect = new MultiEffect(
                                    new ExplosionEffect() {{
                                        smokes = 15;
                                        smokeSize = 15;
                                        smokeRad = 85;
                                        lifetime = 95;
                                        waveLife = 10;
                                        waveRad = 80;
                                        waveStroke = 10;
                                        waveColor = smokeColor = SFColor.energyYellow;
                                    }},
                                    new WaveEffect() {{
                                        lifetime = 80;
                                        interp = Interp.circleOut;
                                        sizeFrom = 5;
                                        sizeTo = 80;
                                        colorFrom = colorTo = SFColor.energyYellow;
                                    }}
                            );
                            shrinkY = 0;
                            width = 22;
                            height = 45;
                            trailLength = 15;
                            trailWidth = 3;
                            trailColor = backColor = SFColor.energyYellow;
                            frontColor = Color.white;
                            lifetime = 40;
                            pierce = true;
                            pierceCap = 3;
                            fragRandomSpread = 0;
                            fragBullets = 1;
                            fragLifeMin = 1;
                            fragVelocityMin = 1;
                            fragBullet = new BasicBulletType(0.1f, 0, "mine-bullet") {{
                                collides = false;
                                hittable = false;
                                absorbable = false;
                                width = height = 60;
                                speed = 0.1f;
                                lifetime = 60;
                                homingRange = 180;
                                homingPower = 0.5f;
                                hitEffect = new WrapEffect(Fx.dynamicSpikes, SFColor.energyYellow, 80);
                                statusDuration = 80;
                                status = SFStatusEffects.breakdown;
                                backColor = SFColor.energyYellow;
                                frontColor = Color.white;
                                spin = 10;
                                shrinkY = shrinkX = 0.5f;
                                hitSound = Sounds.plasmaboom;
                                hitShake = 8;
                                bulletInterval = 2.5f;
                                intervalBullets = 2;
                                intervalRandomSpread = 35;
                                intervalBullet = new BasicBulletType(9, 20) {{
                                    hitShake = 3;
                                    shieldDamageMultiplier = 2f;
                                    splashDamage = 35;
                                    splashDamageRadius = 35;
                                    lightningDamage = 20;
                                    lightning = 2;
                                    lightningLengthRand = 6;
                                    lightningLength = 9;
                                    lightningColor = SFColor.energyYellow;
                                    drag = -0.02f;
                                    lifetime = 10;
                                    width = 9;
                                    height = 23;
                                    pierceArmor = true;
                                    trailLength = 6;
                                    trailWidth = 2;
                                    trailColor = backColor = SFColor.energyYellow;
                                    frontColor = Color.white;
                                    hitEffect = new WrapEffect(Fx.dynamicSpikes, SFColor.energyYellow, 35);
                                    despawnEffect = new ParticleEffect() {{
                                        particles = 3;
                                        sizeInterp = Interp.pow5In;
                                        sizeFrom = 8;
                                        length = 35;
                                        lifetime = 35;
                                        colorFrom = colorTo = SFColor.energyYellow;
                                    }};
                                    hitSound = Sounds.laser;
                                    hitSoundVolume = 0.3f;
                                    status = SFStatusEffects.breakdown;
                                    statusDuration = 20;
                                }};
                            }};
                        }};
                    }}
            );
        }};
        luoHan = new UnitType("cerberilla") {{
            constructor = UnitTypes.omura.constructor;
            researchCostMultiplier = 0.1f;
            flying = false;
            faceTarget = false;
            speed = 0.5f;
            rotateSpeed = 0.86f;
            hitSize = 90;
            trailLength = 160;
            waveTrailX = 29.5f;
            waveTrailY = -40;
            trailScl = 5f;
            health = 74000;
            armor = 26;

            abilities.addAll(
                    new StatusFieldAbility(SFStatusEffects.repair, 200, 180, 160) {{
                        applyEffect = Fx.none;
                        activeEffect = new WaveEffect() {{
                            lifetime = 45;
                            sizeTo = 160;
                            strokeFrom = 22;
                            interp = Interp.circleOut;
                            colorFrom = colorTo = Pal.heal;
                        }};
                    }},
                    new ForceFieldAbility(190, 35, 12000, 35*60)
            );
            forceMultiTarget = true;
            for (float mountY : new float[]{17, -16}) {
                for (float mountX : new float[]{11, 32}) {
                    if ((mountY == 17 && mountX == 11) || (mountY == -16 && mountX == 32)) {
                        weapons.add(new PointDefenseWeapon("point-defense-mount") {{
                            rotate = true;
                            rotateSpeed = 12;
                            x = mountX;
                            y = mountY;
                            reload = 10f;
                            targetInterval = 7.5f;
                            targetSwitchInterval = 7.5f;
                            bullet = new BulletType() {{
                                shootEffect = Fx.sparkShoot;
                                hitEffect = Fx.pointHit;
                                maxRange = 260f;
                                damage = 80f;
                            }};
                        }});
                    }
                }
            }
            weapons.addAll(
                    new Weapon(name("cerberilla-weapon")) {{
                        rotate = true;
                        rotateSpeed = 3f;
                        x = -23.5f;
                        y = 9f;
                        reload = 40;
                        alternate = true;
                        shootCone = 60;
                        shootSound = Sounds.laser;
                        shootY = 5;
                        shake = 3;
                        bullet = new EmpBulletType() {{
                            damage = 90;
                            splashDamage = 90;
                            radius = splashDamageRadius = 92;
                            speed = 6;
                            lifetime = 70;
                            width = 66;
                            height = 66;
                            sprite = "sfire-mod-star-bullet";
                            spin = 3.34f;
                            shrinkX = shrinkY = 0.15f;
                            healPercent = 3;
                            hitSize = 36;
                            reflectable = false;
                            hitShake = 8;
                            pierce = true;
                            pierceBuilding = true;
                            pierceCap = 2;
                            timeIncrease = 3.75f;
                            powerDamageScl = 3;
                            powerSclDecrease = 0.01f;
                            unitDamageScl = 1.25f;
                            hitUnits = true;
                            scaleLife = true;
                            status = StatusEffects.electrified;
                            statusDuration = 300;
                            backColor = frontColor = Pal.heal;
                            trailColor = Pal.heal;
                            trailWidth = 4;
                            trailLength = 16;
                            shootEffect = new WrapEffect(Fx.shootBigColor, Pal.heal);
                            smokeEffect = new ParticleEffect() {{
                                particles = 5;
                                sizeFrom = 3;
                                length = 45;
                                lifetime = 35;
                                colorFrom = Pal.heal;
                                cone = 30;
                            }};
                            hitSound = Sounds.plasmaboom;
                            despawnEffect = new WaveEffect() {{
                                lifetime = 45;
                                sizeFrom = 90;
                                sizeTo = 92;
                                strokeFrom = 6;
                                colorFrom = colorTo = Pal.heal;
                            }};
                            hitEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 10;
                                        baseLength = 130;
                                        length = 50;
                                        sizeFrom = 20;
                                        lifetime = 35;
                                        colorFrom = Pal.heal.cpy().a(0.5f);
                                    }},
                                    new ParticleEffect() {{
                                        particles = 1;
                                        sizeFrom = sizeTo = 92;
                                        length = 0;
                                        lifetime = 15;
                                        colorFrom = Pal.heal.cpy().a(0.5f);
                                        colorTo = Pal.heal.cpy().a(0);
                                    }},
                                    new ParticleEffect() {{
                                        particles = 1;
                                        region = "sfire-mod-star";
                                        sizeFrom = 110;
                                        length = 1;
                                        lifetime = 45;
                                        spin = 3.34f;
                                        colorFrom = Pal.heal;
                                    }}
                            );
                            intervalBullets = 2;
                            bulletInterval = 7;
                            intervalBullet = new EmpBulletType() {{
                                instantDisappear = true;
                                hittable = false;
                                absorbable = false;
                                damage = 24;
                                radius = 92;
                                timeIncrease = 1;
                                powerDamageScl = 2;
                                powerSclDecrease = 0.8f;
                                unitDamageScl = 1.5f;
                                hitUnits = true;
                                status = SFStatusEffects.scrambled;
                                statusDuration = 18;
                                hitEffect = new ParticleEffect() {{
                                    line = true;
                                    particles = 7;
                                    length = 80;
                                    lifetime = 35;
                                    colorFrom = Pal.heal;
                                }};
                                despawnEffect = Fx.none;
                            }};
                        }};
                    }},
                    new Weapon(name("cerberilla-cannon")) {{
                        rotate = true;
                        rotateSpeed = 2f;
                        x = 23.25f;
                        y = -29f;
                        alternate = true;
                        shootSound = Sounds.laser;
                        shootY = 5;
                        shake = 3;
                        reload = 110;
                        parts.add(new RegionPart("-barrel") {{
                            mirror = false;
                            progress = PartProgress.recoil;
                            under = true;
                            moveY = 2;
                        }});
                        shoot.shotDelay = 12;
                        shoot.shots = 3;
                        bullet = new BasicBulletType(15, 119f) {{
                            lifetime = 448 / 15f;
                            height = 35;
                            width = 18;
                            sprite = "sfire-mod-arrow-bullet";
                            healPercent = 3;
                            knockback = 30;
                            reflectable = false;
                            absorbable = false;
                            hitShake = 8;
                            pierce = true;
                            status = SFStatusEffects.scrambled;
                            statusDuration = 60;
                            backColor = frontColor = Pal.heal;
                            trailColor = Pal.heal;
                            trailWidth = 2.5f;
                            trailLength = 20;
                            trailChance = 0.6f;
                            trailEffect = Fx.hitEmpSpark;
                            shootEffect = new WrapEffect(Fx.shootBigColor, Pal.heal);
                            smokeEffect = new ParticleEffect() {{
                                line = true;
                                particles = 5;
                                strokeFrom = 3;
                                lenFrom = 12;
                                length = 45;
                                lifetime = 45;
                                colorFrom = Pal.heal;
                                cone = 30;
                            }};
                            hitSound = Sounds.plasmaboom;
                            despawnEffect = Fx.none;
                            hitEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 23;
                                        strokeFrom = 4;
                                        lenFrom = 22;
                                        length = 80;
                                        interp = Interp.circleOut;
                                        lifetime = 40;
                                        colorFrom = Pal.heal;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 8;
                                        strokeFrom = 4;
                                        lenFrom = 22;
                                        length = 80;
                                        interp = Interp.circleOut;
                                        sizeInterp = Interp.circleOut;
                                        lifetime = 16;
                                        colorFrom = Pal.heal;
                                        cone = 15;
                                    }}
                            );
                        }};
                    }}
            );
        }};

        tank1 = new UnitType("vanguard") {{
            constructor = UnitTypes.stell.constructor;
            omniMovement = false;
            rotateMoveFirst = false;
            rotateSpeed = 3;
            speed = 1.8f;
            hitSize = 9.5f;

            health = 155;
            armor = 4;
            drag = 0.08f;
            accel = 0.1f;
            itemCapacity = 5;
            faceTarget = false;
            abilities.add(new StatusFieldAbility(StatusEffects.overclock, 250, 300, 30) {{
                activeEffect = new WaveEffect() {{
                    lifetime = 20;
                    interp = Interp.circleOut;
                    sides = 4;
                    strokeFrom = 3;
                    sizeTo = 33;
                    colorFrom = colorTo = SFColor.energyYellow;
                }};
                applyEffect = Fx.none;
            }});
            weapons.add(new Weapon(name("vanguard-weapon")) {{
                reload = 7.6f;
                recoil = 0;
                x = y = 0;
                shootY = 5.5f;
                mirror = false;
                rotate = true;
                rotateSpeed = 15;
                inaccuracy = 0.5f;
                ejectEffect = Fx.casing1;
                shootSound = Sounds.shoot;
                bullet = new BasicBulletType(9, 8) {{
                    buildingDamageMultiplier = 0.7f;
                    lifetime = 18;
                    width = 3;
                    height = 9;
                }};
            }});
        }};
        tank2 = new UnitType("striker") {{
            constructor = UnitTypes.stell.constructor;
            omniMovement = false;
            rotateMoveFirst = false;
            rotateSpeed = 3;
            speed = 1.35f;
            hitSize = 18f;
            hovering = true;
            canDrown = false;

            health = 530;
            armor = 5;
            drag = 0.08f;
            accel = 0.1f;
            faceTarget = false;
            weapons.add(new Weapon(name("striker-weapon")) {{
                reload = 90;
                x = 0;
                y = -1;
                rotate = true;
                rotateSpeed = 9;
                mirror = false;
                inaccuracy = 1;
                ejectEffect = Fx.casing2;
                shootSound = Sounds.artillery;
                bullet = new MissileBulletType(8, 80, "sfire-mod-missile1") {{
                    splashDamage = 66;
                    splashDamageRadius = 46;
                    status = StatusEffects.blasted;
                    backColor = SFColor.enemyRedLight;
                    frontColor = SFColor.missileGray;
                    lifetime = 24;
                    homingRange = 50;
                    homingPower = 0.03f;
                    width = 12;
                    height = 40;
                    smokeEffect = Fx.shootPyraFlame;
                    hitEffect = Fx.flakExplosionBig;
                    despawnEffect = Fx.blastExplosion;
                }};
            }});
        }};
        tank3 = new UnitType("skyfire") {{
            constructor = UnitTypes.stell.constructor;
            omniMovement = false;
            rotateMoveFirst = true;
            rotateSpeed = 2;
            speed = 0.975f;
            hitSize = 20f;
            treadRects = new Rect[]{new Rect(-45f, -45f, 24, 88)};

            health = 1100;
            armor = 8;
            drag = 0.08f;
            accel = 0.1f;
            itemCapacity = 0;
            faceTarget = false;
            targetAir = false;
            autoFindTarget = false;
            parts.add(new RegionPart("-back") {{
                y = -4.75f;
                layerOffset = -1;
                moveX = 5.55f;
                moveY = -8;
                moveRot = -30;
                mirror = true;
            }});
            weapons.add(new Weapon(name("skyfire-weapon")) {{
                x = y = 0;
                reload = 400;
                rotate = true;
                rotateSpeed = 2;
                minWarmup = 0.93f;
                shootWarmupSpeed = 0.05f;
                mirror = true;
                alternate = false;
                parts.add(new RegionPart("-front") {{
                    moveY = -4;
                    under = true;
                    mirror = true;
                }});
                shoot.shots = 25;
                shoot.shotDelay = 6;
                xRand = 4;
                inaccuracy = 6;
                shootSound = Sounds.missile;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = 200;
                velocityRnd = 0.1f;
                bullet = new ArtilleryBulletType(8, 8, "sfire-mod-missile1") {{
                    lifetime = 37.5f;
                    splashDamage = 55;
                    splashDamageRadius = 40;
                    buildingDamageMultiplier = 2.25f;
                    collides = false;
                    status = StatusEffects.blasted;
                    backColor = SFColor.enemyRedLight;
                    frontColor = SFColor.missileGray;
                    width = 12;
                    height = 40;
                    trailInterval = 1;
                    trailEffect = new ParticleEffect() {{
                        particles = 3;
                        length = 30;
                        sizeInterp = Interp.pow5In;
                        lifetime = 10;
                        colorFrom = SFColor.smoke;
                        colorTo = SFColor.smoke.cpy().a(0.44f);
                        sizeFrom = 2.6f;
                        cone = 8;
                    }};
                    trailRotation = true;
                    shootEffect = Fx.shootPyraFlame;
                    smokeEffect = new ParticleEffect() {{
                        particles = 3;
                        interp = Interp.pow10Out;
                        sizeInterp = Interp.pow10In;
                        sizeFrom = 6;
                        length = -58;
                        baseLength = -20;
                        lifetime = 42;
                        colorFrom = colorTo = SFColor.smoke.cpy().a(0.56f);
                        cone = 40;
                        layer = 49;
                    }};
                    hitShake = 2;
                    hitSound = Sounds.explosion;
                    hitEffect = new MultiEffect(
                            new ExplosionEffect() {{
                                sparkRad = 50;
                                sparkStroke = 2;
                                sparkLen = 16;
                                sparks = 12;
                                lifetime = 12;
                                sparkColor = Pal.bulletYellow;
                                waveColor = SFColor.smoke.cpy().a(0.5f);
                                waveLife = 10;
                                waveRad = 55;
                                waveStroke = 10;
                                smokeRad = 38;
                                smokeSize = 6;
                                smokes = 7;
                                smokeColor = SFColor.smoke.cpy().a(0.5f);
                            }},
                            new ParticleEffect() {{
                                particles = 1;
                                sizeInterp = Interp.pow5Out;
                                sizeFrom = 10;
                                sizeTo = 40;
                                length = 0;
                                lifetime = 12;
                                colorFrom = Color.white.cpy().a(0.8f);
                                colorTo = SFColor.smoke.cpy().a(0);
                            }},
                            new ParticleEffect() {{
                                particles = 8;
                                interp = Interp.pow10Out;
                                sizeInterp = Interp.pow5In;
                                sizeFrom = 8;
                                length = 38;
                                baseLength = 10;
                                lifetime = 35;
                                colorFrom = colorTo = SFColor.smoke.cpy().a(0.8f);
                            }}
                    );
                    despawnEffect = Fx.flakExplosionBig;
                }};
            }});
        }};
        tank4 = new UnitType("flanker") {{
            constructor = UnitTypes.stell.constructor;
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = false;
            rotateSpeed = 1.5f;
            speed = 0.85f;
            hitSize = 36f;
            crushDamage = 2;
            treadRects = new Rect[]{new Rect(-67f, -84f, 39, 176)};
            treadFrames = 8;
            drownTimeMultiplier = 2;

            health = 10500;
            armor = 15;
            itemCapacity = 0;
            faceTarget = false;
            abilities.add(new StatusFieldAbility(StatusEffects.overclock, 1200, 1200, 45) {{
                activeEffect = new WaveEffect() {{
                    lifetime = 15;
                    strokeFrom = 3;
                    sizeTo = 45;
                    colorFrom = colorTo = SFColor.energyYellow;
                }};
                applyEffect = Fx.none;
            }});
            immunities.addAll(StatusEffects.burning, StatusEffects.wet, SFStatusEffects.breakdown);
            weapons.add(new Weapon(name("flanker-weapon")) {{
                layerOffset = 0.001f;
                reload = 90;
                shootY = 27f;
                x = y = 0;
                rotate = true;
                rotateSpeed = 1.9f;
                shootSound = Sounds.mediumCannon;
                shake = 4;
                mirror = false;
                bullet = new BasicBulletType(12, 180) {{
                    lifetime = 25;
                    width = 10;
                    height = 24;
                    buildingDamageMultiplier = 0.6f;
                    status = SFStatusEffects.breakdown;
                    statusDuration = 160;
                    frontColor = Color.white;
                    backColor = SFColor.energyYellow;
                    trailLength = 15;
                    trailWidth = 2f;
                    trailColor = SFColor.energyYellow;
                    knockback = 8;
                    hitSound = Sounds.explosion;
                    hitSoundVolume = 3;
                    hitShake = 5;
                    hitEffect = new ParticleEffect() {{
                        lifetime = 30;
                        line = true;
                        lenFrom = 25;
                        strokeFrom = 4.2f;
                        colorFrom = colorTo = SFColor.energyYellow;
                        sizeInterp = interp = Interp.circleOut;
                        length = 56;
                        baseLength = -16;
                        cone = 40;
                    }};
                    despawnEffect = new ExplosionEffect() {{
                        lifetime = 15;
                        smokes = 6;
                        smokeRad = 15;
                        smokeSize = 6;
                        smokeColor = waveColor = SFColor.energyYellow;
                        waveRad = 20;
                        waveStroke = 4;
                        waveLife = 10;
                    }};
                    shootEffect = Fx.shootTitan;
                    smokeEffect = new ParticleEffect() {{
                        particles = 6;
                        sizeFrom = 4;
                        length = 26;
                        cone = 16;
                        lifetime = 33;
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.fastSlow;
                        colorFrom = colorTo = SFColor.energyYellow;
                    }};
                    fragBullets = 3;
                    fragRandomSpread = 60;
                    fragBullet = new BasicBulletType(2.5f, 46) {{
                        despawnEffect = Fx.none;
                        pierceArmor = true;
                        width = 10;
                        height = 12;
                        shrinkY = 1;
                        lifetime = 15;
                        pierce = true;
                        pierceCap = 2;
                        frontColor = Color.white;
                        backColor = SFColor.energyYellow;
                    }};
                }};
                parts.add(new RegionPart("-barrel") {{
                    moveY = -8;
                    under = true;
                    heatProgress = PartProgress.recoil;
                    progress = PartProgress.recoil;
                }});
            }});
        }};
        tank5 = new UnitType("executioner") {{
            constructor = UnitTypes.stell.constructor;
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = true;
            rotateSpeed = 1.22f;
            speed = 0.8f;
            hitSize = 52f;
            crushDamage = 6;
            treadRects = new Rect[]{new Rect(-98, -130, 46, 255)};
            treadFrames = 8;
            drownTimeMultiplier = 5;
            health = 25000;
            armor = 26;
            itemCapacity = 0;
            faceTarget = false;
            immunities.addAll(StatusEffects.burning, StatusEffects.wet, SFStatusEffects.breakdown);
            targetFlags = new BlockFlag[]{BlockFlag.core};
            singleTarget = true;
            weapons.add(
                    new Weapon(name("executioner-laser")) {{
                        reload = 30;
                        x = y = 0;
                        rotate = true;
                        display = false;
                        rotateSpeed = 1f;
                        shootSound = Sounds.missile;
                        mirror = false;
                        shootY = 12;
                        shoot = new ShootBarrel(){{
                            barrels = new float[]{
                                    16,0,0,
                                    -16,0,0
                            };
                            shots = 2;
                            shotDelay = 8;
                        }};
                        recoil = 0;
                        minWarmup = 0.9f;
                        inaccuracy = 1;
                        bullet = new RailBulletType() {{
                            length = 352;
                            damage = 45;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 60;
                            buildingDamageMultiplier = 0.6f;
                            pointEffectSpace = 500;
                            pointEffect = Fx.none;
                            shootEffect = Fx.hitBulletColor;
                            smokeEffect = Fx.shootSmallColor;
                            pierceEffect = despawnEffect = hitEffect =
                            new ParticleEffect() {{
                                particles = 1;
                                sizeFrom = 4;
                                length = 0;
                                lifetime = 30;
                                sizeInterp = Interp.fastSlow;
                                colorFrom = colorTo = SFColor.energyYellow;
                            }};
                        }};
                        parts.add(new RegionPart("-side") {{
                            layerOffset = 0.001f;
                            mirror = true;
                            y = 8;
                            moveX = 16;
                        }});
                    }},
                    new Weapon(name("executioner-weapon")) {{
                        reload = 110;
                        x = y = 0;
                        layerOffset = 0.001f;
                        rotate = true;
                        rotateSpeed = 1f;
                        shootSound = Sounds.boom;
                        mirror = false;
                        shootY = 46.5f;
                        recoil = 0;
                        shake = 8;
                        bullet = new BasicBulletType(16, 220) {{
                            lifetime = 22;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 120;
                            pierce = true;
                            pierceCap = 2;

                            hittable = absorbable = false;
                            shieldDamageMultiplier = buildingDamageMultiplier = 2;
                            frontColor = Color.white;
                            trailColor = backColor = SFColor.energyYellow;
                            trailWidth = 3.6f;
                            trailLength = 12;
                            trailChance = 0.1f;
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
                            fragBullet = new BasicBulletType(0, 10, "circle-bullet") {{
                                width = height = 16;
                                shrinkY = shrinkX = 0.1f;
                                lifetime = 15;
                                hittable = absorbable = collides = false;
                                status = SFStatusEffects.breakdown;
                                statusDuration = 120;
                                buildingDamageMultiplier = 1.5f;
                                scaledSplashDamage = true;
                                splashDamage = 270;
                                splashDamageRadius = 65;
                                hitShake = 4;
                                hitSound = Sounds.plasmaboom;
                                hitEffect = new ParticleEffect() {{
                                    particles = 15;
                                    sizeFrom = 8;
                                    length = 60;
                                    lifetime = 33;
                                    interp = Interp.pow5Out;
                                    sizeInterp = Interp.pow5;
                                    colorFrom = colorTo = SFColor.energyYellow;
                                }};
                                despawnEffect = new WrapEffect(Fx.titanExplosion, SFColor.energyYellow);
                            }};
                        }};
                        parts.add(new RegionPart("-barrel") {{
                            y = 16;
                            moveY = -10;
                            under = true;
                            heatProgress = PartProgress.recoil;
                            progress = PartProgress.recoil;
                        }});
                    }}
            );
        }};
        tank6 = new UnitType("enforcer") {{
            constructor = UnitTypes.stell.constructor;
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = false;
            rotateSpeed = 1.22f;
            speed = 0.75f;
            drag = 0.3f;
            hitSize = 66;
            crushDamage = 10;
            treadRects = new Rect[]{
                    new Rect(-115, 118, 52, 48),
                    new Rect(-118, -160, 79, 144)
            };
            treadFrames = 8;
            drownTimeMultiplier = 5;
            health = 81000;
            armor = 45;
            itemCapacity = 0;
            faceTarget = false;
            drawShields = false;
            immunities.addAll(StatusEffects.burning, StatusEffects.wet, StatusEffects.electrified, StatusEffects.unmoving, SFStatusEffects.scrambled, SFStatusEffects.breakdown);
            weapons.add(new Weapon(name("enforcer-weapon")) {{
                rotate = true;
                rotateSpeed = 1.5f;
                x = y = 0;
                shootY = 40;
                mirror = false;
                cooldownTime = 160;
                recoil = shake = 6;
                reload = 98;
                shoot = new ShootAlternate(18) {{
                    shots = 2;
                    shotDelay = 8;
                }};
                shootSound = Sounds.largeCannon;
                bullet = new BasicBulletType(24, 325, "missile-large") {{
                    lifetime = 15;
                    drag = -0.01f;
                    width = 10;
                    height = 26;
                    hitSize = 16;
                    splashDamage = 225;
                    splashDamageRadius = 60;
                    buildingDamageMultiplier = 1.2f;
                    absorbable = false;
                    hittable = false;
                    pierce = true;
                    pierceArmor = true;
                    pierceBuilding = true;
                    pierceCap = 3;
                    status = StatusEffects.unmoving;
                    statusDuration = 80;
                    frontColor = Color.white;
                    backColor = SFColor.energyYellow;
                    trailLength = 8;
                    trailWidth = 4;
                    trailColor = SFColor.energyYellow;
                    trailChance = 1;
                    trailRotation = true;
                    trailEffect = new ParticleEffect() {{
                        particles = 3;
                        sizeFrom = 3;
                        interp = Interp.circleOut;
                        sizeInterp = Interp.pow3In;
                        colorFrom = colorTo = SFColor.energyYellow;
                        lifetime = 8;
                    }};
                    hitSound = Sounds.plasmaboom;
                    hitSoundVolume = 3;
                    hitShake = 8;
                    hitEffect = new ExplosionEffect() {{
                        lifetime = 35;
                        smokes = 9;
                        smokeRad = 90;
                        smokeSize = 10;
                        smokeColor = waveColor = SFColor.energyYellow;
                        waveRad = 60;
                        waveStroke = 10;
                        waveLife = 10;
                    }};
                    despawnEffect = new ParticleEffect() {{
                        lifetime = 65;
                        particles = 1;
                        sizeFrom = 10;
                        colorFrom = colorTo = SFColor.energyYellow;
                        length = 0;
                    }};
                    smokeEffect = Fx.smokeCloud;
                    shootEffect = new ParticleEffect() {{
                        particles = 6;
                        line = true;
                        strokeFrom = 6;
                        lenFrom = 25;
                        length = 50;
                        cone = 15;
                        lifetime = 11;
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.fastSlow;
                        colorFrom = colorTo = SFColor.energyYellow;
                    }};
                }};
            }});
        }};

        Effect hitEnergySky = new WaveEffect() {{
            lifetime = 10;
            sizeFrom = 1;
            sizeTo = 8;
            strokeFrom = 2.5f;
            colorFrom = colorTo = SFColor.energySky;
        }};
        Effect desEnergySky = new ParticleEffect() {{
            particles = 1;
            sizeFrom = 1.5f;
            length = baseLength = 0;
            lifetime = 20;
            colorFrom = colorTo = SFColor.energySky;
        }};

        air1 = new UnitType("convection") {{
            constructor = UnitTypes.flare.constructor;
            flying = true;
            lowAltitude = true;
            faceTarget = true;
            rotateSpeed = 8f;
            speed = 1.8f;
            drag = 0.05f;
            accel = 0.1f;
            hitSize = 8;
            health = 110;
            armor = 0;
            itemCapacity = 0;
            engineOffset = 8;
            engineSize = 2.5f;
            immunities.add(StatusEffects.freezing);
            weapons.add(new Weapon(name("convection-weapon")) {{
                rotate = false;
                mirror = false;
                reload = 60;
                x = y = 0;
                shootSound = Sounds.missile;
                shoot = new ShootSpread(2, 20) {{
                    shotDelay = 8;
                }};
                velocityRnd = 0.02f;
                bullet = new MissileBulletType(4, 8, "circle-bullet") {{
                    width = height = 5;
                    lifetime = 30;
                    weaveMag = 3;
                    weaveScale = 3;
                    reflectable = false;
                    frontColor = Color.white;
                    backColor = SFColor.energySky;
                    trailColor = SFColor.energySky;
                    trailLength = 4;
                    trailWidth = 2;
                    trailEffect = Fx.none;
                    hitEffect = hitEnergySky;
                    despawnEffect = desEnergySky;
                    //it not follow the weapon rotation......
                    //shootEffect = new WrapEffect(Fx.shootSmallColor,SFColor.energySky);
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                    status = SFStatusEffects.magnStrif;
                    statusDuration = 15;
                }};
            }});
        }};
        air2 = new UnitType("sleet") {{
            constructor = UnitTypes.zenith.constructor;
            flying = true;
            lowAltitude = true;
            faceTarget = true;
            rotateSpeed = 4.7f;
            speed = 1.5f;
            drag = 0.08f;
            accel = 0.1f;
            hitSize = 13;
            health = 520;
            armor = 3;
            itemCapacity = 0;
            engineOffset = 10;
            engineSize = 3f;
            immunities.add(StatusEffects.freezing);
            weapons.add(new Weapon(name("sleet-weapon")) {{
                x = 0;
                rotate = false;
                mirror = false;
                reload = 17;
                shootY = 4;
                shootCone = 15;
                recoil = 0;
                shootSound = Sounds.missile;
                heatColor = Color.red;
                cooldownTime = 20;
                shoot = new ShootPattern() {{
                    firstShotDelay = 7.5f;
                }};
                parts.add(new ShapePart() {{
                    color = SFColor.energySky;
                    y = 4;
                    layer = 110;
                    circle = true;
                    hollow = false;
                    radius = 0.6f;
                    radiusTo = 2.2f;
                }});
                bullet = new PointBulletType() {{
                    trailSpacing = 3;
                    trailEffect = new MultiEffect(
                            new ParticleEffect() {{
                                particles = 1;
                                line = true;
                                randLength = false;
                                length = 0;
                                baseLength = 1;
                                lifetime = 7.5f;
                                lenFrom = lenTo = 3.3f;
                                strokeFrom = 2.2f;
                                strokeTo = 0;
                                colorFrom = colorTo = SFColor.energySky;
                                cone = 0;
                            }},
                            new ParticleEffect() {{
                                particles = 1;
                                baseLength = 3f;
                                lifetime = 15f;
                                sizeFrom = 1.6f;
                                colorFrom = colorTo = SFColor.energySky;
                            }}
                    );
                    buildingDamageMultiplier = 0.68f;
                    damage = 35f;
                    lifetime = 10;
                    speed = 16;
                    status = StatusEffects.freezing;
                    statusDuration = 240;
                    chargeEffect = new ParticleEffect() {{
                        particles = 2;
                        length = -35;
                        baseLength = 35;
                        lifetime = 55;
                        sizeFrom = 0;
                        sizeTo = 1.3f;
                        colorFrom = colorTo = SFColor.energySky;
                    }};
                    shootEffect = hitEnergySky;
                    smokeEffect = Fx.none;
                    hitSound = Sounds.explosion;
                    hitEffect = new MultiEffect(
                            new WaveEffect() {{
                                lifetime = 10;
                                sizeFrom = 1;
                                sizeTo = 16;
                                strokeFrom = 2.5f;
                                colorFrom = colorTo = SFColor.energySky;
                            }},
                            new ParticleEffect() {{
                                particles = 8;
                                length = 16;
                                line = true;
                                lifetime = 55;
                                interp = Interp.pow5Out;
                                colorFrom = colorTo = SFColor.energySky;
                            }}
                    );
                    despawnEffect = new ParticleEffect() {{
                        particles = 1;
                        sizeFrom = 3f;
                        length = baseLength = 0;
                        lifetime = 30;
                        colorFrom = colorTo = SFColor.energySky;
                    }};
                }};
            }});
        }};
        air3 = new UnitType("cumulus") {{
            constructor = UnitTypes.zenith.constructor;
            flying = true;
            lowAltitude = false;
            rotateSpeed = 6f;
            speed = 2.45f;
            drag = 0.035f;
            accel = 0.04f;
            hitSize = 24;
            health = 1000;
            armor = 4;
            itemCapacity = 10;
            engineSize = 3.5f;
            engineOffset = 13f;
            trailLength = 11;
            immunities.addAll(StatusEffects.burning, StatusEffects.unmoving);
            circleTarget = faceTarget = true;
            weapons.add(
                    new Weapon(name("cumulus-gun")) {{
                        top = false;
                        x = 4;
                        y = 4.5f;
                        shootX = 1;
                        shootY = 3;
                        inaccuracy = 1;
                        layerOffset = -0.001f;
                        shootSound = Sounds.blaster;
                        rotate = false;
                        reload = 6f;
                        shootCone = 5;
                        bullet = new RailBulletType() {{
                            damage = 12;
                            pierce = false;
                            pointEffectSpace = 4;
                            pierceDamageFactor = 0.8f;
                            pointEffect = new ParticleEffect() {{
                                particles = 1;
                                line = true;
                                randLength = false;
                                length = 0;
                                baseLength = 1;
                                lifetime = 7f;
                                lenFrom = lenTo = 4.3f;
                                strokeFrom = 3f;
                                strokeTo = 0;
                                colorFrom = colorTo = Pal.lightPyraFlame;
                                cone = 0;
                            }};
                            endEffect = new Effect(7, e -> {
                                color(e.color);
                                Drawf.tri(e.x, e.y, e.fout() * 3f, 5f, e.rotation);
                            });
                            smokeEffect = Fx.none;
                            hitEffect = new ParticleEffect() {{
                                particles = 5;
                                lifetime = 10;
                                sizeFrom = 3;
                                colorFrom = colorTo = Pal.lightPyraFlame;
                            }};
                            status = StatusEffects.burning;
                            statusDuration = 180;
                            length = 120;
                        }};
                    }},
                    new Weapon(name("cumulus-missile")) {{
                        x = 4;
                        reload = 17f;
                        rotate = false;
                        shootY = 3f;
                        shoot = new ShootAlternate(10);
                        shootCone = 30f;
                        ejectEffect = Fx.none;
                        inaccuracy = 15f;
                        ignoreRotation = true;
                        shootSound = Sounds.missile;
                        minWarmup = 0.9f;
                        shootWarmupSpeed = 0.05f;
                        minShootVelocity = 0.01f;
                        bullet = new MissileBulletType(10, 35, "sfire-mod-missile2") {{
                            splashDamage = 20;
                            lifetime = 35;
                            incendAmount = 1;
                            incendChance = 0.35f;
                            incendSpread = 30;
                            makeFire = true;
                            width = 10;
                            height = 35;
                            shrinkY = 0;
                            frontColor = SFColor.missileGray;
                            trailEffect = Fx.none;
                            trailColor = SFColor.missileGray.cpy().a(0.5f);
                            backColor = Pal.lightPyraFlame;
                            trailWidth = 1.5f;
                            trailLength = 15;
                            hitEffect = new ExplosionEffect(){{
                                smokes = 9;
                                smokeSize = 6;
                                smokeRad = 35;
                                lifetime = 25;
                                sparks = 0;
                                smokeColor = waveColor = Pal.lightPyraFlame;
                                waveStroke = 4;
                                waveRad = 35;
                                waveLife = 15;
                            }};
                            despawnEffect = Fx.flakExplosion;
                            shootEffect = Fx.none;
                            smokeEffect = Fx.none;
                            status = StatusEffects.burning;
                            statusDuration = 180f;
                        }};
                    }}
            );
        }};
        air4 = new UnitType("circulation") {{
            constructor = UnitTypes.zenith.constructor;
            flying = true;
            lowAltitude = true;
            faceTarget = true;
            rotateSpeed = 2f;
            speed = 1.33f;
            drag = 0.02f;
            accel = 0.02f;
            hitSize = 35;
            health = 8200;
            armor = 6;
            itemCapacity = 0;
            immunities.add(StatusEffects.freezing);
            engineOffset = 24;
            engineSize = 3;
            setEnginesMirror(new UnitEngine(-11.75f, -14, 4, -55));
            abilities.add(new SuppressionFieldAbility() {{
                range = 180;
                reload = 60;
                y = 1;
                color = SFColor.energySky;
                orbRadius = 5;
                orbMidScl = 0.33f;
                particles = 12;
                particleColor = SFColor.energySky;
                particleLife = 50;
                particleLen = -8;
                particleSize = 2;
                applyParticleChance = 0;
            }});
            BulletType energyBulletSky = new BasicBulletType(6, 12) {{
                reflectable = false;
                shrinkY = 0;
                shootEffect = hitEffect = hitEnergySky;
                smokeEffect = Fx.none;
                despawnEffect = desEnergySky;
                buildingDamageMultiplier = 0.68f;
                width = 7;
                height = 10;
                lifetime = 30;
                frontColor = Color.white;
                backColor = SFColor.energySky;
            }};
            weapons.add(
                    new Weapon(name("cumulus-gun")) {{
                        rotate = true;
                        rotateSpeed = 12;
                        rotationLimit = 22;
                        top = false;
                        x = 16.75f;
                        y = -1.5f;
                        shootX = 1;
                        shootY = 3;
                        inaccuracy = 1.2f;
                        layerOffset = -0.001f;
                        shootSound = Sounds.blaster;
                        reload = 7f;
                        shootCone = 13;
                        bullet = energyBulletSky;
                    }},
                    new Weapon(name("cumulus-gun")) {{
                        rotate = true;
                        rotateSpeed = 12;
                        rotationLimit = 22;
                        top = false;
                        x = 8.5f;
                        y = 11.75f;
                        shootX = 1;
                        shootY = 3;
                        inaccuracy = 1.2f;
                        layerOffset = -0.001f;
                        shootSound = Sounds.blaster;
                        reload = 8f;
                        shootCone = 13;
                        bullet = energyBulletSky;
                    }},
                    new Weapon(name("circulation-missile")) {{
                        rotate = false;
                        x = 13f;
                        y = 6f;
                        alternate = false;
                        shoot = new ShootBarrel() {{
                            shots = 10;
                            shotDelay = 3.5f;
                            barrels = new float[]{
                                    -1.5f, 1.5f, 0,
                                    0, 0, 0,
                                    1.5f, -1.5f, 0
                            };
                        }};
                        inaccuracy = 5f;
                        shootSound = Sounds.missile;
                        reload = 50f;
                        shootCone = 30;
                        velocityRnd = 0.1f;
                        bullet = new MissileBulletType(6, 18, "circle-bullet") {{
                            reflectable = false;
                            drag = -0.01f;
                            width = height = 6;
                            lifetime = 28;
                            shrinkY = 0;
                            homingDelay = 15;
                            frontColor = Color.white;
                            backColor = SFColor.energySky;
                            trailColor = SFColor.energySky;
                            trailLength = 5;
                            trailWidth = 2.5f;
                            shootEffect = smokeEffect = Fx.none;
                            splashDamage = 18;
                            splashDamageRadius = 25;
                            status = SFStatusEffects.magnStrif;
                            statusDuration = 18;
                            knockback = 0.5f;
                            hitEffect = new MultiEffect(
                                    new WaveEffect() {{
                                        interp = Interp.circleOut;
                                        lifetime = 18;
                                        sizeTo = 38;
                                        strokeFrom = 4;
                                        colorFrom = colorTo = SFColor.energySky;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 5;
                                        sizeFrom = 2.5f;
                                        length = 35;
                                        interp = Interp.pow5Out;
                                        sizeInterp = Interp.pow3Out;
                                        lifetime = 45;
                                        colorFrom = SFColor.energySky;
                                        colorTo = SFColor.energySky.cpy().a(0.8f);
                                    }}
                            );
                        }};
                    }}
            );
        }};
        air5 = new UnitType("cirrus") {{
            constructor = UnitTypes.zenith.constructor;
            flying = true;
            lowAltitude = true;
            faceTarget = true;
            rotateSpeed = 1.22f;
            speed = 0.6f;
            drag = 0.05f;
            accel = 0.04f;
            hitSize = 59;
            health = 20000;
            armor = 8;
            itemCapacity = 0;
            engineOffset = 18;
            engineSize = 15;
            immunities.addAll(StatusEffects.wet, StatusEffects.freezing);
            drawShields = false;
            abilities.add(new ForceFieldAbility(82, 3, 1800, 600));
            weapons.add(
                    new Weapon(name("cirrus-weapon")) {{
                        rotate = true;
                        rotateSpeed = 3;
                        reload = 18;
                        x = 16;
                        y = -8;
                        shootY = 11;
                        shoot = new ShootAlternate(8);
                        shake = 2;
                        shootSound = Sounds.laser;
                        bullet = new BasicBulletType(8, 35, "circle-bullet") {{
                            reflectable = false;
                            width = height = 10;
                            lifetime = 33.75f;
                            shrinkY = 0;
                            frontColor = Color.white;
                            backColor = SFColor.energySky;
                            trailColor = SFColor.energySky;
                            trailLength = 8;
                            trailWidth = 4f;
                            trailInterval = 3;
                            trailEffect = new ParticleEffect() {{
                                particles = 3;
                                sizeFrom = 3;
                                length = 8;
                                sizeInterp = Interp.pow3In;
                                interp = Interp.pow5Out;
                                lifetime = 22;
                                colorFrom = colorTo = SFColor.energySky;
                            }};
                            shootEffect = Fx.lancerLaserShoot;
                            smokeEffect = Fx.none;

                            splashDamage = 45;
                            splashDamageRadius = 38;
                            status = SFStatusEffects.magnStrif;
                            statusDuration = 30;
                            lightning = 3;
                            lightningDamage = 15;
                            lightningLength = 5;
                            lightningLengthRand = 3;
                            lightningColor = SFColor.energySky;
                            despawnEffect = Fx.lancerLaserShoot;
                            hitSound = Sounds.laser;
                            hitShake = 3;
                            hitEffect = new MultiEffect(
                                    new ExplosionEffect() {{
                                        waveLife = 22;
                                        waveRad = 50;
                                        waveStroke = 8;
                                        waveColor = SFColor.energySky;
                                        smokeColor = SFColor.smoke;
                                        lifetime = 60;
                                        smokeRad = 55;
                                        smokeSize = 10;
                                        smokes = 7;
                                        sparks = 0;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 6;
                                        sizeFrom = 6;
                                        length = 20;
                                        baseLength = 30;
                                        lifetime = 60;
                                        colorTo = SFColor.smoke;
                                        colorFrom = SFColor.energySky;
                                    }}
                            );
                        }};
                    }},
                    new Weapon(name("cirrus-storm")) {{
                        rotate = false;
                        alternate = false;
                        x = 28;
                        y = 24;
                        reload = 400;
                        shootSound = Sounds.release;
                        shootCone = 10;
                        bullet = new BasicBulletType(8, 50, "missile-large") {{
                            reflectable = false;
                            collides = false;
                            scaleLife = true;
                            width = 20;
                            height = 24;
                            lifetime = 40f;
                            shrinkY = 0;
                            frontColor = Color.white;
                            backColor = SFColor.energySky;
                            trailColor = SFColor.energySky;
                            trailLength = 18;
                            trailWidth = 4f;
                            trailInterval = 3;
                            trailEffect = new WaveEffect() {{
                                sizeTo = 20;
                                strokeFrom = 2;
                                interp = Interp.circleOut;
                                lifetime = 30;
                                colorFrom = colorTo = SFColor.energySky;
                            }};
                            shootEffect = smokeEffect = Fx.none;
                            despawnEffect = new MultiEffect(
                                    new WaveEffect() {{
                                        sizeTo = 150;
                                        lifetime = 20;
                                        strokeFrom = 1;
                                        strokeTo = 4;
                                        colorFrom = colorTo = SFColor.energySky;
                                    }},
                                    new WaveEffect() {{
                                        startDelay = 20;
                                        sizeFrom = sizeTo = 150;
                                        lifetime = 80;
                                        strokeFrom = 4;
                                        interp = Interp.circleOut;
                                        colorFrom = colorTo = SFColor.energySky;
                                    }}
                            );
                            hitEffect = new ParticleEffect() {{
                                particles = 35;
                                sizeFrom = 28;
                                length = 130;
                                baseLength = 20;
                                lifetime = 150;
                                sizeInterp = Interp.pow5In;
                                interp = Interp.pow10Out;
                                layer = 120;
                                colorFrom = SFColor.smoke;
                                colorTo = SFColor.smoke.cpy().a(0.5f);
                            }};
                            hitShake = 12;
                            fragBullets = 15;
                            fragLifeMin = 0.5f;
                            fragBullet = new BasicBulletType(1.3f, 10) {{
                                lifetime = 100;
                                width = height = 0;
                                collides = false;
                                hittable = false;
                                absorbable = false;
                                buildingDamageMultiplier = 0.58f;
                                splashDamage = 25;
                                splashDamageRadius = 38;
                                status = SFStatusEffects.magnStrif;
                                statusDuration = 30;
                                lightning = 5;
                                lightningDamage = 25;
                                lightningLength = 12;
                                lightningLengthRand = 5;
                                lightningColor = SFColor.energySky;
                                lightningType = new BulletType(0.0001f, 0f) {{
                                    lifetime = Fx.lightning.lifetime;
                                    hitEffect = Fx.hitLancer;
                                    despawnEffect = Fx.none;
                                    status = StatusEffects.shocked;
                                    statusDuration = 10f;
                                    hittable = false;
                                    lightColor = Color.white;
                                    buildingDamageMultiplier = 0.58f;
                                }};
                                hitShake = 5;
                                hitSound = Sounds.plasmaboom;
                                despawnEffect = Fx.lancerLaserShoot;
                                hitEffect = new MultiEffect(
                                        new ExplosionEffect() {{
                                            waveLife = 22;
                                            waveRad = 50;
                                            waveStroke = 8;
                                            waveColor = SFColor.energySky;
                                            smokeColor = SFColor.smoke;
                                            lifetime = 60;
                                            smokeRad = 55;
                                            smokeSize = 10;
                                            smokes = 7;
                                            sparks = 0;
                                        }},
                                        new ParticleEffect() {{
                                            particles = 6;
                                            sizeFrom = 6;
                                            length = 20;
                                            baseLength = 30;
                                            lifetime = 60;
                                            colorTo = SFColor.smoke;
                                            colorFrom = SFColor.energySky;
                                        }}
                                );
                            }};
                        }};
                    }}
            );
        }};
        air6 = new UnitType("stratosphere") {{
            constructor = UnitTypes.quad.constructor;
            payloadCapacity = 6 * 6 * 64;
            flying = true;
            lowAltitude = false;
            faceTarget = false;
            rotateSpeed = 1f;
            speed = 1.11f;
            drag = 0.07f;
            accel = 0.09f;
            hitSize = 88;
            health = 82500;
            armor = 30;
            itemCapacity = 0;
            engineOffset = 22;
            engineSize = 13;
            trailLength = 40;
            setEnginesMirror(
                    new UnitEngine(-40, -17, 7, -135),
                    new UnitEngine(-68.75f, -7, 4.3f, -135)
            );
            immunities.addAll(StatusEffects.electrified, StatusEffects.slow, StatusEffects.freezing, StatusEffects.unmoving, SFStatusEffects.negative, SFStatusEffects.postive, SFStatusEffects.scrambled, SFStatusEffects.disRepair, SFStatusEffects.magnStrif);
            targetFlags = new BlockFlag[]{BlockFlag.turret, BlockFlag.core};
            circleTarget = true;
            weapons.add(
                    new Weapon(name("stratosphere-emp")) {{
                        rotate = false;
                        x = 0;
                        reload = 380;
                        shoot = new ShootBarrel() {{
                            shots = 12;
                            shotDelay = 120 / 12f;
                            barrels = new float[]{
                                    53.5f, 8, 0,
                                    24, 24, 0,
                                    -53.5f, 8, 0,
                                    -24, 24, 0
                            };
                        }};
                        minShootVelocity = 0.1f;
                        ignoreRotation = true;
                        shootStatus = StatusEffects.shielded;
                        shootStatusDuration = 90;
                        shootCone = 60;
                        shootSound = Sounds.plasmadrop;
                        xRand = 6;
                        bullet = new EmpBulletType() {{
                            maxRange = 80f;
                            sprite = "missile-large";
                            width = 24;
                            height = 32;
                            hitShake = 20;
                            hitSound = Sounds.plasmaboom;
                            frontColor = Color.white;
                            backColor = SFColor.energySky;
                            trailLength = 24;
                            trailWidth = 5;
                            trailColor = backColor;
                            layer = 100;
                            collidesTiles = false;
                            collides = false;
                            collidesAir = false;
                            scaledSplashDamage = true;
                            speed = 0.7f;
                            drag = 0.03f;
                            lifetime = 100;
                            shrinkY = shrinkX = 0.8f;

                            timeIncrease = 1;
                            powerSclDecrease = 0.88f;
                            radius = 60;
                            unitDamageScl = 2f;
                            chainEffect = Fx.chainLightning;
                            hitPowerEffect = new WrapEffect(Fx.hitLaserColor, SFColor.energySky);
                            applyEffect = Fx.none;
                            status = StatusEffects.slow;
                            statusDuration = 600;
                            suppressionRange = 120;
                            suppressionDuration = 1200;

                            lightning = 4;
                            lightningDamage = 50;
                            lightningLength = 5;
                            lightningLengthRand = 8;
                            lightningColor = SFColor.energySky;

                            damage = 150;
                            splashDamage = 160;
                            splashDamageRadius = 60;
                            shootEffect = smokeEffect = Fx.none;
                            despawnEffect = Fx.none;
                            hitEffect = new MultiEffect(
                                    new WrapEffect(Fx.dynamicSpikes, SFColor.energySky, 60),
                                    new ExplosionEffect() {{
                                        sparks = 37;
                                        sparkLen = 32;
                                        sparkStroke = 2.25f;
                                        sparkRad = 80;
                                        waveColor = sparkColor = SFColor.energySky;
                                        lifetime = 20;
                                        smokes = 0;
                                        waveStroke = 8;
                                        waveRad = 70;
                                        waveLife = 35;
                                    }},
                                    new ParticleEffect() {{
                                        lifetime = 60;
                                        sizeFrom = 12;
                                        colorFrom = colorTo = SFColor.energySky;
                                        length = 0;
                                    }}
                            );
                        }};
                    }},
                    new Weapon(name("stratosphere-burning")) {{
                        rotate = false;
                        x = 0;
                        reload = 380;
                        shoot = new ShootBarrel() {{
                            shots = 27;
                            shotDelay = 120 / 27f;
                            barrels = new float[]{
                                    0, 12, 0,
                                    40, -6, 0,
                                    -40, -6, 0
                            };
                        }};
                        minShootVelocity = 0.1f;
                        ignoreRotation = true;
                        shootStatus = StatusEffects.shielded;
                        shootStatusDuration = 90;
                        shootCone = 60;
                        shootSound = Sounds.plasmadrop;
                        xRand = 6;
                        bullet = new BasicBulletType() {{
                            maxRange = 80f;
                            sprite = "missile-large";
                            width = 24;
                            height = 32;
                            hitShake = 20;
                            hitSound = Sounds.explosionbig;
                            frontColor = Color.white;
                            backColor = Pal.lightPyraFlame;
                            trailLength = 24;
                            trailWidth = 5;
                            trailColor = backColor;
                            layer = 100;
                            collidesTiles = false;
                            collides = false;
                            collidesAir = false;
                            scaledSplashDamage = true;
                            speed = 0.7f;
                            drag = 0.03f;
                            lifetime = 100;
                            shrinkY = shrinkX = 0.8f;

                            status = StatusEffects.burning;
                            statusDuration = 1200;
                            incendAmount = 3;
                            incendSpread = 86;
                            incendChance = 1;

                            damage = 180;
                            splashDamage = 180;
                            buildingDamageMultiplier = 2;
                            splashDamageRadius = 77;
                            shootEffect = smokeEffect = Fx.none;
                            despawnEffect = Fx.none;
                            hitEffect = new MultiEffect(
                                    new ExplosionEffect() {{
                                        smokes = 8;
                                        smokeSize = 18;
                                        smokeRad = 80;
                                        waveColor = smokeColor = Pal.lightPyraFlame.cpy().a(0.48f);
                                        lifetime = 50;
                                        sparks = 0;
                                        waveStroke = 10;
                                        waveRad = 80;
                                        waveLife = 25;
                                    }},
                                    new ParticleEffect() {{
                                        lifetime = 80;
                                        sizeFrom = 12;
                                        sizeInterp = Interp.pow3In;
                                        colorFrom = colorTo = Pal.lightPyraFlame;
                                        length = 0;
                                    }}
                            );
                        }};
                    }},
                    new Weapon(name("stratosphere-gun")) {{
                        reload = 5;
                        alternate = false;
                        mirror = true;
                        x = 43;
                        rotate = true;
                        rotateSpeed = 9;
                        controllable = false;
                        autoTarget = true;
                        shootSound = Sounds.missile;
                        shootY = 3;
                        bullet = new PointBulletType() {{
                            trailSpacing = 3;
                            trailEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 1;
                                        line = true;
                                        randLength = false;
                                        layer = 120;
                                        lightOpacity = 1;
                                        length = 0;
                                        baseLength = 1;
                                        lifetime = 7.5f;
                                        lenFrom = lenTo = 3.3f;
                                        strokeFrom = 2.2f;
                                        strokeTo = 0;
                                        colorFrom = colorTo = SFColor.energySky;
                                        cone = 0;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 1;
                                        baseLength = 3f;
                                        lifetime = 15f;
                                        sizeFrom = 1.6f;
                                        layer = 120;
                                        lightOpacity = 1;
                                        colorFrom = colorTo = SFColor.energySky;
                                    }}
                            );
                            collidesGround = false;
                            splashDamage = 16;
                            splashDamageRadius = 30;
                            damage = 35f;
                            lifetime = 8;
                            speed = 30;
                            status = StatusEffects.freezing;
                            statusDuration = 60;
                            shootEffect = hitEnergySky;
                            smokeEffect = Fx.none;
                            hitSound = Sounds.explosion;
                            hitEffect = new MultiEffect(
                                    new WaveEffect() {{
                                        lifetime = 10;
                                        sizeFrom = 1;
                                        sizeTo = 30;
                                        strokeFrom = 2.5f;
                                        colorFrom = colorTo = SFColor.energySky;
                                    }},
                                    new ParticleEffect() {{
                                        particles = 8;
                                        length = 16;
                                        line = true;
                                        lifetime = 55;
                                        interp = Interp.pow5Out;
                                        colorFrom = colorTo = SFColor.energySky;
                                    }}
                            );
                            despawnEffect = new ParticleEffect() {{
                                particles = 1;
                                sizeFrom = 4.6f;
                                length = baseLength = 0;
                                lifetime = 34;
                                colorFrom = colorTo = SFColor.energySky;
                            }};
                        }};
                    }}
            );
        }};

        naval1 = new UnitType("alnitak") {{
            constructor = UnitTypes.risso.constructor;
            speed = 1f;
            rotateSpeed = 3;
            trailLength = 24;
            waveTrailX = 0;
            waveTrailY = -6;
            trailScl = 2;
            hitSize = 10;
            health = 300;
            armor = 2;
            faceTarget = false;

            weapons.add(
                    new Weapon(name("alnitak-gun")) {{
                        reload = 8;
                        recoil = 0.6f;
                        shootY = 3;
                        x = 3.5f;
                        rotate = true;
                        rotateSpeed = 6;
                        inaccuracy = 1f;
                        ejectEffect = Fx.casing1;
                        bullet = new BasicBulletType(8, 9.6f) {{
                            lifetime = 15;
                            width = 5;
                            height = 8;
                        }};
                    }},
                    new Weapon(name("alnitak-mine")) {{
                        x = 0;
                        mirror = false;
                        rotate = true;
                        reload = 120;
                        shoot = new ShootSpread(2, 15);
                        ignoreRotation = true;
                        bullet = new BasicBulletType(5, 25, "missile") {{
                            lifetime = 45;
                            splashDamage = 15;
                            splashDamageRadius = 35;
                            homingPower = 0.1f;
                            homingDelay = 8;
                            homingRange = 30;
                            absorbable = false;
                            collidesAir = false;
                            collideFloor = true;
                            smokeEffect = shootEffect = Fx.none;
                            hitEffect = Fx.flakExplosionBig;
                            hitSound = Sounds.explosion;
                            trailLength = 6;
                            trailWidth = 1.2f;
                            trailColor = backColor = Color.valueOf("596ab8");
                            frontColor = Color.white;
                            trailChance = 1;
                            trailEffect = new ParticleEffect() {{
                                particles = 3;
                                sizeTo = 3;
                                lifetime = 45;
                                interp = Interp.pow10Out;
                                baseLength = 3;
                                length = -35;
                                cone = 30;
                                colorFrom = Color.valueOf("596ab8");
                                colorTo = Color.valueOf("596ab800");
                            }};
                            trailRotation = true;
                            width = 8;
                            height = 20;
                        }};
                    }}
            );
        }};
        naval2 = new UnitType("polaris") {{
            constructor = UnitTypes.risso.constructor;
            speed = 0.71f;
            rotateSpeed = 2.6f;
            trailLength = 24;
            waveTrailX = 3.75f;
            waveTrailY = -7.5f;
            trailScl = 1.6f;
            hitSize = 16;
            health = 800;
            armor = 5;
            faceTarget = false;
            fogRadius = 31;
            weapons.add(
                    new Weapon(name("alnitak-gun")) {{
                        reload = 6;
                        shootY = 3;
                        x = 6f;
                        rotate = true;
                        rotateSpeed = 6;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.bolt;
                        bullet = new RailBulletType() {{
                            damage = 13;
                            knockback = 0.3f;
                            pierce = false;
                            pointEffectSpace = 4;
                            length = 110;
                            pointEffect = new ParticleEffect() {{
                                particles = 1;
                                line = true;
                                randLength = false;
                                length = 0;
                                baseLength = 1;
                                lifetime = 7.5f;
                                lenFrom = lenTo = 3.3f;
                                strokeFrom = 2f;
                                strokeTo = 0;
                                colorFrom = colorTo = SFColor.energyYellow;
                                cone = 0;
                            }};
                            hitEffect = new ParticleEffect() {{
                                particles = 5;
                                line = true;
                                lifetime = 12f;
                                colorFrom = colorTo = SFColor.energyYellow;
                            }};
                        }};
                    }},
                    new Weapon(name("polaris-weapon")) {{
                        reload = 32;
                        shootY = 5;
                        x = 0f;
                        y = -8f;
                        mirror = false;
                        rotate = true;
                        rotateSpeed = 6;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.bolt;
                        bullet = new SapBulletType() {{
                            damage = 22;
                            shootEffect = Fx.shootSmall;
                            lifetime = 10;
                            sapStrength = 1;
                            length = 133;
                            width = 0.54f;
                            knockback = -1.3f;
                            statusDuration = 10;
                            status = StatusEffects.slow;
                            lightColor = hitColor = color = SFColor.energyYellow;
                            despawnEffect = Fx.none;
                        }};
                    }}
            );
        }};
        naval3 = new UnitType("antares") {{
            constructor = UnitTypes.risso.constructor;
            speed = 0.63f;
            rotateSpeed = 2;
            trailLength = 40;
            waveTrailX = 8;
            waveTrailY = -8;
            trailScl = 2.5f;
            hitSize = 25;
            health = 1300;
            armor = 6;
            faceTarget = false;
            immunities.addAll(StatusEffects.slow, StatusEffects.electrified);
            weapons.addAll(
                    new Weapon(name("antares-weapon")) {{
                        rotate = true;
                        x = 0;
                        rotateSpeed = 4.4f;
                        shootY = 5.5f;
                        reload = 45f;
                        mirror = false;
                        inaccuracy = 2;
                        shootSound = Sounds.shootBig;
                        ejectEffect = Fx.casing3;
                        layerOffset = 0.001f;
                        parts.add(new RegionPart("-front") {{
                            under = true;
                            moveY = -1.2f;
                            progress = PartProgress.recoil;
                        }});
                        bullet = new FlakBulletType(5, 28) {{
                            collidesGround = true;
                            backColor = SFColor.energyYellow;
                            frontColor = Color.white;
                            trailColor = SFColor.energyYellow;
                            trailLength = 12;
                            trailWidth = 1.6f;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 20;
                            splashDamage = 30;
                            splashDamageRadius = 26;
                            drag = -0.05f;
                            width = 9;
                            height = 18;
                            lifetime = 25.2f;
                            shootEffect = Fx.shootBig2;
                            hitEffect = new ExplosionEffect() {{
                                smokeRad = 30;
                                lifetime = 15;
                                smokes = 9;
                                smokeSize = 5;
                                sparks = 0;
                                waveLife = 10;
                                waveStroke = 3;
                                waveRad = 28;
                                waveColor = smokeColor = SFColor.energyYellow;
                            }};
                            hitSound = Sounds.laser;
                            hitShake = 2;
                            despawnEffect = Fx.none;
                        }};
                    }},
                    new Weapon(name("antares-missile")) {{
                        x = y = 0;
                        mirror = false;
                        shootCone = 360;
                        shootSound = Sounds.missile;
                        rotate = false;
                        inaccuracy = 0;
                        reload = 110;
                        shoot = new ShootBarrel() {{
                            shots = 6;
                            shotDelay = 6;
                            barrels = new float[]{
                                    10, 6, -35,
                                    -10, 6, 35,
                                    10, -6, -35,
                                    -10, -6, 35
                            };
                        }};
                        bullet = new MissileBulletType(6f, 25, "sfire-mod-missile2") {{
                            splashDamage = 30;
                            splashDamageRadius = 45;
                            status = StatusEffects.blasted;
                            backColor = SFColor.enemyRedLight;
                            frontColor = SFColor.missileGray;
                            trailColor = Color.white;
                            trailLength = 12;
                            trailWidth = 1.5f;
                            lifetime = 30f;
                            homingRange = 30;
                            homingPower = 0.18f;
                            width = 12;
                            height = 36;
                            smokeEffect = Fx.none;
                            hitEffect = Fx.flakExplosionBig;
                            despawnEffect = Fx.blastExplosion;
                        }};
                    }}
            );
        }};
        naval4 = new UnitType("merak") {{
            constructor = UnitTypes.risso.constructor;
            speed = 0.52f;
            rotateSpeed = 0.825f;
            trailLength = 36;
            waveTrailX = 14;
            waveTrailY = -30;
            trailScl = 3;
            hitSize = 50;
            health = 9300;
            armor = 8;
            faceTarget = false;
            weapons.addAll(
                    new PointDefenseWeapon(name("striker-weapon")) {{
                        x = 0;
                        y = 14;
                        rotate = true;
                        rotateSpeed = 9;
                        mirror = false;
                        shootY = 4;
                        shootSound = Sounds.lasershoot;
                        recoil = 0.6f;
                        reload = 15;
                        targetInterval = 5;
                        targetSwitchInterval = 5;
                        bullet = new BulletType() {{
                            maxRange = 240;
                            shootEffect = Fx.sparkShoot;
                            hitEffect = Fx.pointHit;
                            damage = 30f;
                        }};
                    }},
                    new Weapon(name("merak-missile")) {{
                        rotate = false;
                        baseRotation = -45;
                        alternate = false;
                        x = 12;
                        y = -18;
                        shoot.shots = 2;
                        shoot.shotDelay = 8;
                        recoil = 0;
                        reload = 30f;
                        shootCone = 360;
                        shootSound = Sounds.missileSmall;
                        inaccuracy = 5;
                        autoTarget = true;
                        controllable = false;
                        bullet = new FlakBulletType(8, 48) {{
                            sprite = "sfire-mod-missile2";
                            splashDamage = 110f;
                            splashDamageRadius = 55f;
                            scaledSplashDamage = true;
                            status = StatusEffects.blasted;
                            lifetime = 48;
                            homingRange = 100;
                            homingDelay = 3f;
                            homingPower = 0.25f;
                            shootEffect = Fx.shootBig2;
                            smokeEffect = Fx.shootSmallFlame;
                            hitEffect = Fx.flakExplosionBig;
                            hitSound = Sounds.explosion;
                            hitShake = 3;
                            shrinkY = 0;
                            trailLength = 8;
                            trailWidth = 2;
                            trailColor = Color.white.cpy().a(0.8f);
                            trailChance = 1f;
                            trailEffect = new ParticleEffect() {{
                                particles = 4;
                                sizeFrom = 2;
                                length = 30f;
                                lifetime = 45;
                                lightOpacity = 0.2f;
                                interp = Interp.circleOut;
                                sizeInterp = Interp.pow10In;
                                colorTo = SFColor.smoke;
                                cone = 10;
                            }};
                            trailRotation = true;
                            drag = -0.01f;
                            width = 14;
                            height = 45;
                            frontColor = SFColor.missileGray;
                            backColor = SFColor.enemyRedLight;
                            lifetime = 35;
                        }};
                    }},
                    new Weapon(name("merak-cannon")) {{
                        rotate = true;
                        x = 0;
                        y = -8f;
                        rotateSpeed = 3.85f;
                        shootY = 4f;
                        reload = 68f;
                        mirror = false;
                        inaccuracy = 3;
                        shootSound = Sounds.laser;
                        ejectEffect = Fx.none;
                        bullet = new FlakBulletType(12, 48) {{
                            collidesGround = true;
                            backColor = SFColor.discLight;
                            frontColor = Color.white;
                            trailColor = SFColor.discLight;
                            trailLength = 8;
                            trailWidth = 2.25f;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 300;
                            splashDamage = 64;
                            splashDamageRadius = 35;
                            buildingDamageMultiplier = 2.25f;
                            width = 10;
                            height = 20;
                            lifetime = 20;
                            shootEffect = new ParticleEffect() {{
                                sizeFrom = 6;
                                length = 40;
                                lifetime = 30;
                                colorFrom = SFColor.discLight;
                                colorTo = SFColor.discDark;
                                cone = 16;
                            }};
                            smokeEffect = Fx.bigShockwave;
                            despawnEffect = hitEffect = Fx.instBomb;
                            hitSound = Sounds.laser;
                            hitShake = 6;
                        }};
                    }}
            );
        }};
        naval5 = new UnitType("regulus") {{
            constructor = UnitTypes.risso.constructor;
            speed = 0.45f;
            rotateSpeed = 0.6f;
            trailLength = 80;
            waveTrailX = 20;
            waveTrailY = -24;
            trailScl = 4.5f;
            hitSize = 60;
            health = 21000;
            armor = 14;
            faceTarget = false;

            MissileBulletType regulusMissile = new MissileBulletType(8, 33, "sfire-mod-missile1") {{
                splashDamage = 33;
                splashDamageRadius = 40;
                hitEffect = Fx.flakExplosionBig;
                trailWidth = 2;
                trailLength = 8;
                trailEffect = Fx.none;
                trailColor = Color.white;
                backColor = SFColor.enemyRedLight;
                frontColor = SFColor.missileGray;
                width = 12;
                height = 40;
                lifetime = 45;
            }};

            weapons.addAll(
                    new Weapon(name("regulus-mine")) {{
                        x = 0;
                        reload = 65f;
                        mirror = false;
                        shoot = new ShootBarrel(){{
                            shots = 2;
                            shotDelay = 23f;
                            barrels = new float[]{
                                    20, 0, -22.5f,
                                    -20, 0, 22.5f
                            };
                        }};
                        shootSound = Sounds.mineDeploy;
                        rotate =false;
                        shootCone = 360f;
                        inaccuracy = 20f;
                        autoTarget = true;
                        bullet = new BasicBulletType(5,110, "sfire-mod-missile1"){{
                            splashDamageRadius = 50;
                            splashDamage = 240;
                            homingDelay = 15f;
                            homingRange = 40;
                            homingPower = 0.08f;
                            statusDuration = 160;
                            status = SFStatusEffects.breakdown;
                            lifetime = 60;
                            shrinkY = 0;
                            width = 13f;
                            height = 50;
                            trailRotation = true;
                            trailInterval = 4;
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
                            trailWidth = 2.6f;
                            trailLength = 10;
                            hitShake = 8;
                            hitSound = Sounds.plasmaboom;
                            hitSoundVolume = 5;
                            shootEffect = smokeEffect = Fx.none;
                            despawnEffect = new ParticleEffect(){{
                                particles = 18;
                                sizeFrom = 11;
                                lifetime = 95;
                                interp = Interp.pow10Out;
                                sizeInterp = Interp.pow10In;
                                length = -75;
                                colorFrom = colorTo = Color.valueOf("596ab8A8");
                            }};
                            hitEffect = new MultiEffect(
                                    new ExplosionEffect(){{
                                        waveRad = 65;
                                        waveStroke = 9;
                                        waveLife = 22;
                                        sparks = 14;
                                        sparkLen = 44;
                                        sparkStroke = 2;
                                        sparkRad = 70;
                                        lifetime = 60;
                                        smokes = 22;
                                        smokeRad = 55;
                                        smokeSize = 9;
                                        smokeColor = Color.valueOf("596ab890");
                                        waveColor = sparkColor = Pal.bulletYellow;
                                    }},
                                    new WaveEffect(){{
                                        lifetime = 22;
                                        sizeTo = 73f;
                                        strokeFrom = 7;
                                        colorFrom = Pal.bulletYellow;
                                        colorTo = Color.white;
                                    }}
                            );
                            layer = 49f;
                            collideFloor = true;
                            collidesAir = false;
                            absorbable = hittable = false;
                        }};
                    }},
                    new Weapon(name("regulus-missile")) {{
                        x = -24;
                        y = -11;
                        reload = 60;
                        rotate = true;
                        rotateSpeed = 6;
                        shootSound = Sounds.missile;
                        shoot.shots = 3;
                        shoot.shotDelay = 3f;
                        alternate = false;
                        inaccuracy = 10;
                        xRand = 3;
                        controllable = false;
                        autoTarget = true;
                        bullet = regulusMissile;
                    }},
                    new Weapon(name("regulus-missile")) {{
                        x = 18;
                        y = 15;
                        reload = 60;
                        rotate = true;
                        rotateSpeed = 6;
                        shootSound = Sounds.missile;
                        shoot.shots = 3;
                        shoot.shotDelay = 3f;
                        alternate = false;
                        inaccuracy = 10;
                        xRand = 3;
                        controllable = false;
                        autoTarget = true;
                        bullet = regulusMissile;
                    }},
                    new Weapon(name("regulus-missile")) {{
                        x = 12;
                        y = -28;
                        reload = 60;
                        rotate = true;
                        rotateSpeed = 6;
                        shootSound = Sounds.missile;
                        shoot.shots = 3;
                        shoot.shotDelay = 3f;
                        alternate = false;
                        inaccuracy = 10;
                        xRand = 3;
                        controllable = false;
                        autoTarget = true;
                        bullet = regulusMissile;
                    }},
                    new Weapon(name("regulus-weapon")) {{
                        x = 0;
                        y = -4f;
                        rotate = true;
                        rotateSpeed = 3.85f;
                        shootY = 20f;
                        reload = 70f;
                        mirror = false;
                        shootSound = Sounds.railgun;
                        soundPitchMax = soundPitchMin = 0.5f;
                        cooldownTime = 120;
                        ejectEffect = Fx.none;
                        recoil = 4;
                        shake = 4;
                        shoot = new ShootAlternate(18) {{
                            shots = 2;
                        }};
                        bullet = new FlakBulletType(12, 110) {{
                            collidesGround = true;
                            backColor = SFColor.discLight;
                            frontColor = Color.white;
                            trailColor = SFColor.discLight;
                            trailLength = 8;
                            trailWidth = 3.3f;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 460;
                            splashDamage = 175;
                            splashDamageRadius = 45;
                            buildingDamageMultiplier = 3f;
                            width = 12;
                            height = 22;
                            lifetime = 40;
                            shootEffect = new ParticleEffect() {{
                                line = true;
                                length = 40;
                                lifetime = 36;
                                strokeFrom = 1.5f;
                                lenFrom = 20;
                                interp = Interp.pow10Out;
                                sizeInterp = Interp.pow5In;
                                colorFrom = SFColor.discLight;
                                cone = 10;
                            }};
                            smokeEffect = Fx.bigShockwave;
                            despawnEffect = Fx.instBomb;
                            hitEffect = new ExplosionEffect() {{
                                lifetime = 36;
                                smokes = 9;
                                smokeRad = 50;
                                smokeSize = 7;
                                smokeColor = waveColor = SFColor.discLight;
                                waveRad = 50;
                                waveStroke = 5;
                                waveLife = 16;
                            }};
                            hitSound = Sounds.plasmaboom;
                            hitShake = 6;
                        }};
                    }}
            );
        }};
        naval6 = new UnitType("alioth") {{
            constructor = UnitTypes.risso.constructor;
            speed = 0.41f;
            rotateSpeed = 0.53f;
            trailLength = 200;
            waveTrailX = 30;
            waveTrailY = -40;
            trailScl = 5;
            hitSize = 90;
            health = 80000;
            armor = 38;
            faceTarget = false;
            immunities.addAll(StatusEffects.burning, StatusEffects.melting, SFStatusEffects.breakdown, SFStatusEffects.overLoad);

            BulletType aliothBullet = new BasicBulletType(8, 110, "missile") {{
                splashDamage = 118;
                splashDamageRadius = 68;
                lightning = 1;
                lightningLength = 1;
                lightningLengthRand = 9;
                buildingDamageMultiplier = 1.6f;
                status = SFStatusEffects.breakdown;
                statusDuration = 180;
                lifetime = 66f;
                shootEffect = new WrapEffect(Fx.shootBigColor, SFColor.energyYellow);
                smokeEffect = new ParticleEffect() {{
                    particles = 8;
                    interp = Interp.fastSlow;
                    sizeFrom = 8;
                    length = 105;
                    lifetime = 35;
                    colorFrom = colorTo = SFColor.energyYellow;
                    cone = 30;
                }};
                width = 18;
                height = 30;
                frontColor = Color.white;
                backColor = SFColor.energyYellow;
                trailLength = 10;
                trailWidth = 4;
                trailColor = SFColor.energyYellow;
                hitShake = 8;
                hitSound = Sounds.plasmaboom;
                hitEffect = new ExplosionEffect() {{
                    sparks = 22;
                    sparkLen = 30;
                    sparkStroke = 3;
                    sparkRad = 80;
                    sparkColor = smokeColor = SFColor.energyYellow;
                    smokes = 6;
                    smokeSize = 9;
                    smokeRad = 60;
                    lifetime = 65;
                    waveLife = 25;
                    waveStroke = 3;
                    waveRad = 66;
                    waveColor = SFColor.energyYellow;
                }};
                despawnEffect = new WrapEffect(Fx.titanExplosion, SFColor.energyYellow);
            }};

            weapons.addAll(
                    new Weapon(name("alioth-weapon")) {{
                        x = 24;
                        y = 32;
                        reload = 55;
                        rotate = true;
                        rotateSpeed = 0.8f;
                        layerOffset = 0.001f;
                        shootY = 6;
                        shoot.shots = 2;
                        shoot.shotDelay = 12;
                        minWarmup = 0.8f;
                        shootWarmupSpeed = 0.03f;
                        inaccuracy = 3;
                        recoil = 2;
                        shootSound = Sounds.mediumCannon;
                        parts.add(new RegionPart("-barrel") {{
                            under = true;
                            moveY = -2;
                            progress = PartProgress.recoil;
                            heatProgress = PartProgress.recoil;
                            heatColor = Color.valueOf("FEEBB380");
                        }});
                        bullet = aliothBullet;
                    }},
                    new Weapon(name("alioth-weapon")) {{
                        x = -33;
                        y = 8;
                        reload = 53;
                        rotate = true;
                        rotateSpeed = 0.8f;
                        layerOffset = 0.001f;
                        shootY = 6;
                        shoot.shots = 2;
                        shoot.shotDelay = 12;
                        minWarmup = 0.8f;
                        shootWarmupSpeed = 0.03f;
                        inaccuracy = 3;
                        recoil = 2;
                        shootSound = Sounds.mediumCannon;
                        parts.add(new RegionPart("-barrel") {{
                            under = true;
                            moveY = -2;
                            progress = PartProgress.recoil;
                            heatProgress = PartProgress.recoil;
                            heatColor = Color.valueOf("FEEBB380");
                        }});
                        bullet = aliothBullet;
                    }},
                    new Weapon(name("alioth-weapon")) {{
                        x = -28;
                        y = -24;
                        reload = 58;
                        rotate = true;
                        rotateSpeed = 0.8f;
                        layerOffset = 0.001f;
                        shootY = 6;
                        shoot.shots = 2;
                        shoot.shotDelay = 12;
                        minWarmup = 0.8f;
                        shootWarmupSpeed = 0.03f;
                        inaccuracy = 3;
                        recoil = 2;
                        shootSound = Sounds.mediumCannon;
                        parts.add(new RegionPart("-barrel") {{
                            under = true;
                            moveY = -2;
                            progress = PartProgress.recoil;
                            heatProgress = PartProgress.recoil;
                            heatColor = Color.valueOf("FEEBB380");
                        }});
                        bullet = aliothBullet;
                    }},
                    new Weapon(name("alioth-mount")) {{
                        x = 0;
                        reload = 750;
                        mirror = false;
                        rotate = true;
                        rotateSpeed = 0.7f;
                        layerOffset = 0.001f;
                        shootY = 8;
                        shoot = new ShootPattern() {{
                            firstShotDelay = 120;
                        }};
                        inaccuracy = 3;
                        recoil = 2;
                        shootSound = Sounds.railgun;
                        chargeSound = Sounds.lasercharge;
                        cooldownTime = 500;
                        shootStatusDuration = 360;
                        shootStatus = StatusEffects.overclock;
                        parts.add(new RegionPart("-barrel") {{
                            under = true;
                            moveY = -8;
                            progress = PartProgress.recoil;
                            heatProgress = PartProgress.recoil;
                            heatColor = Color.valueOf("FEEBB380");
                        }});
                        bullet = new EmpBulletType() {{
                            timeIncrease = 1;
                            powerSclDecrease = 0.6f;
                            hitUnits = true;
                            unitDamageScl = 1.5f;
                            radius = 150;
                            damage = 1160;
                            splashDamageRadius = 150;
                            splashDamage = 480;
                            buildingDamageMultiplier = 1.65f;
                            speed = 3;
                            drag = -0.05f;
                            lifetime = 48f;
                            status = SFStatusEffects.overLoad;
                            statusDuration = 360;
                            hittable = false;
                            pierce = true;
                            pierceArmor = true;
                            pierceCap = 3;
                            hitShake = 12;
                            frontColor = backColor = trailColor = SFColor.energyYellow;
                            trailLength = 16;
                            trailWidth = 3;
                            trailChance = 1;
                            trailRotation = true;
                            trailEffect = new ParticleEffect() {{
                                particles = 4;
                                sizeFrom = 3;
                                sizeTo = 10;
                                lifetime = 95;
                                interp = Interp.pow10Out;
                                length = -60;
                                baseLength = 10;
                                colorFrom = SFColor.energyYellow;
                                colorTo = SFColor.energyYellow.cpy().a(0);
                                cone = 20;
                            }};
                            hitSize = 30;
                            width = 24;
                            height = 45;
                            sprite = "missile-large";
                            shrinkY = shrinkX = 0;
                            chargeEffect = new MultiEffect(
                                    new WaveEffect() {{
                                        lifetime = 30;
                                        sizeFrom = 110;
                                        sizeTo = 0;
                                        strokeFrom = 0;
                                        strokeTo = 3;
                                        colorFrom = SFColor.energyYellow;
                                        colorTo = SFColor.energyYellow;
                                    }},
                                    new WaveEffect() {{
                                        lifetime = 30;
                                        sizeFrom = 110;
                                        sizeTo = 0;
                                        strokeFrom = 0;
                                        strokeTo = 5;
                                        colorFrom = SFColor.energyYellow;
                                        colorTo = SFColor.energyYellow;
                                        startDelay = 30;
                                    }},
                                    new WaveEffect() {{
                                        lifetime = 30;
                                        sizeFrom = 110;
                                        sizeTo = 0;
                                        strokeFrom = 0;
                                        strokeTo = 7;
                                        colorFrom = SFColor.energyYellow;
                                        colorTo = SFColor.energyYellow;
                                        startDelay = 70;
                                    }}
                            );
                            hitEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 1;
                                        region = "sfire-mod-star";
                                        lifetime = 20;
                                        length = 0;
                                        sizeFrom = 220;
                                        colorFrom = SFColor.energyYellow;
                                        colorTo = SFColor.energyYellow;
                                    }},
                                    new WaveEffect() {{
                                        lifetime = 40;
                                        sizeTo = 160;
                                        strokeFrom = strokeTo = 6;
                                        ;
                                        colorFrom = SFColor.energyYellow;
                                        colorTo = SFColor.energyYellow.cpy().a(0);
                                    }},
                                    new ExplosionEffect() {{
                                        waveLife = 0;
                                        lifetime = 200;
                                        smokes = 32;
                                        smokeSize = 30;
                                        smokeRad = 100;
                                        smokeColor = SFColor.energyYellow.cpy().a(0.5f);
                                        sparkColor = SFColor.energyYellow;
                                        sparkRad = 200;
                                        sparks = 36;
                                        sparkStroke = 3;
                                        sparkLen = 90;
                                    }}
                            );
                            hitSound = SFSounds.hugeExplosion;
                            hitPowerEffect = new ParticleEffect() {{
                                particles = 1;
                                length = 0;
                                lifetime = 20;
                                sizeFrom = 40;
                                spin = 3;
                                region = "sfire-mod-star";
                                colorFrom = SFColor.energyYellow;
                                colorTo = SFColor.energyYellow.cpy().a(0);
                            }};
                            despawnEffect = Fx.none;
                            shootEffect = new WaveEffect() {{
                                lifetime = 30;
                                sizeTo = 90;
                                strokeFrom = 8;
                            }};
                            smokeEffect = new ParticleEffect() {{
                                particles = 10;
                                line = true;
                                lenFrom = 25;
                                lenTo = 0;
                                lifetime = 160;
                                length = 85f;
                                strokeFrom = strokeTo = 3;
                                sizeInterp = Interp.pow5In;
                                interp = Interp.pow10Out;
                                colorFrom = SFColor.energyYellow;
                                colorTo = SFColor.energyYellow;
                                cone = 30;
                            }};
                            fragOnHit = false;
                            //fragOnAbsorb = false;
                            fragLifeMax = 1;
                            fragLifeMin = 0.3f;
                            fragBullets = 25;
                            fragBullet = new BasicBulletType(20, 100, "sfire-mod-star-bullet") {{
                                collides = false;
                                width = height = 48;
                                frontColor = Color.white;
                                backColor = SFColor.energyYellow;
                                status = SFStatusEffects.breakdown;
                                statusDuration = 125;
                                hitShake = 6;
                                hitSound = Sounds.plasmaboom;
                                hitEffect = new WrapEffect(Fx.dynamicSpikes, SFColor.energyYellow, 65);
                                despawnEffect = new WaveEffect() {{
                                    lifetime = 15;
                                    sizeTo = 65;
                                    strokeFrom = 4;
                                    colorFrom = SFColor.energyYellow;
                                    colorTo = SFColor.energyYellow;
                                }};
                                drag = 0.1f;
                                lifetime = 40;
                                trailLength = 33;
                                trailWidth = 2;
                                spin = 3;
                                shrinkY = shrinkX = 0.3f;
                                frontColor = Color.white;
                                backColor = trailColor = SFColor.energyYellow;
                                splashDamage = 76;
                                splashDamageRadius = 60;
                                buildingDamageMultiplier = 1.25f;
                            }};
                        }};
                    }}
            );
        }};

        farmer = new UnitType("farmer") {{
            constructor = UnitTypes.mega.constructor;
            flying = true;
            payloadCapacity = 64 * 3 * 3;
            aiController = BuilderAI::new;
            defaultCommand = UnitCommand.rebuildCommand;
            faceTarget = true;
            targetFlags = new BlockFlag[]{BlockFlag.drill, BlockFlag.core};
            speed = 2;
            rotateSpeed = 4;
            accel = 0.07f;
            drag = 0.06f;
            health = 1500;
            armor = 6;
            hitSize = 24;
            buildSpeed = 3.5f;
            buildRange = 288;
            buildBeamOffset = 11;
            itemOffsetY = 1;
            itemCapacity = 150;
            mineSpeed = 11;
            mineTier = 4;
            mineRange = 70;
            trailLength = 6;
            engineSize = 2.8f;
            engineOffset = 16;
            setEnginesMirror(new UnitEngine(-6.5f, -14, 1.8f, 247.5f));
            lightColor = Pal.heal;
            lightRadius = 60;
            abilities.add(new StatusFieldAbility(SFStatusEffects.repair, 50, 120, 60) {{
                applyEffect = Fx.heal;
                activeEffect = new WaveEffect() {{
                    lifetime = 30;
                    sizeFrom = sizeTo = 60;
                    strokeFrom = 1.5f;
                    colorFrom = Pal.heal;
                    colorTo = Color.white;
                }};
            }});
            weapons.add(
                    new Weapon(name("farmer-laser")) {{
                        reload = 120;
                        x = y = 0;
                        shootY = 12;
                        rotate = false;
                        top = false;
                        recoil = 3.25f;
                        mirror = false;
                        shootSound = Sounds.laser;
                        continuous = true;
                        bullet = new LaserBulletType() {{
                            healPercent = 16;
                            collidesTeam = true;
                            recoil = 0.12f;
                            damage = 40;
                            knockback = 20;
                            status = SFStatusEffects.scrambled;
                            statusDuration = 60;
                            width = 15;
                            length = 176;
                            sideAngle = 135;
                            sideWidth = 1.35f;
                            sideLength = 45;
                            smokeEffect = Fx.smokeCloud;
                            shootEffect = new ParticleEffect() {{
                                line = true;
                                particles = 9;
                                lenFrom = 10;
                                strokeFrom = 1.5f;
                                baseLength = 160;
                                length = cone = 0;
                                sizeInterp = Interp.pow3In;
                                lifetime = 20;
                                colorFrom = colorTo = Pal.heal;
                            }};
                            colors = new Color[]{Pal.heal.cpy().a(.5f), Pal.heal.cpy().mul(1.2f), Color.white};
                        }};
                    }},
                    new Weapon(name("farmer-gun")) {{
                        reload = 15;
                        x = 9;
                        y = -2;
                        shootY = 6;
                        rotate = false;
                        alternate = true;
                        layerOffset = -0.001f;
                        shootSound = Sounds.lasershoot;
                        shootWarmupSpeed = 0.1f;
                        minWarmup = 0.98f;
                        parts.add(new RegionPart("-preview") {{
                            x = -9;
                            y = -8;
                            rotation = -60;
                            mirror = false;
                            moveX = -x;
                            moveY = -y;
                            moveRot = -rotation;
                        }});
                        bullet = new MissileBulletType(9.6f, 20, "circle-bullet") {{
                            lifetime = 176 / 9.6f;
                            width = 6;
                            height = 9;
                            shrinkY = 0;
                            collidesTeam = true;
                            healPercent = 8;
                            reflectable = false;
                            keepVelocity = false;
                            frontColor = Color.white;
                            backColor = Pal.heal;
                            trailColor = Pal.heal;
                            status = SFStatusEffects.disRepair;
                            statusDuration = 10;
                            homingPower = 0.5f;
                            homingRange = 16;
                            trailWidth = trailLength = 3;
                            shootEffect = Fx.shootHeal;
                            hitEffect = new ParticleEffect() {{
                                particles = 5;
                                sizeFrom = 3;
                                length = 25;
                                lifetime = 25;
                                colorFrom = Pal.heal;
                                colorTo = Pal.heal.cpy().a(0.02f);
                            }};
                            despawnEffect = Fx.none;
                        }};
                    }}
            );
            immunities = ObjectSet.with(StatusEffects.unmoving, StatusEffects.electrified, SFStatusEffects.scrambled);
        }};
        carrier = new UnitType("carrier") {{
            constructor = UnitTypes.mega.constructor;
            outlineColor = SFColor.darkOutline;
            flying = true;
            payloadCapacity = 64 * 4 * 4;
            aiController = BuilderAI::new;
            defaultCommand = UnitCommand.rebuildCommand;
            faceTarget = true;
            targetFlags = new BlockFlag[]{BlockFlag.drill, BlockFlag.core};
            speed = 2.24f;
            rotateSpeed = 4.4f;
            accel = 0.08f;
            drag = 0.07f;
            health = 3300;
            armor = 12;
            hitSize = 28;
            buildSpeed = 2;
            buildRange = 220;
            buildBeamOffset = 11;
            itemOffsetY = 1;
            itemCapacity = 300;
            engineSize = 2.8f;
            engineOffset = 16;
            setEnginesMirror(
                    new UnitEngine(-6f, -14, 1.8f, 247.5f),
                    new UnitEngine(-10.5f, -7, 2.25f, 247.5f));
            lightColor = Pal.heal;
            lightRadius = 60;
            abilities.add(new StatusFieldAbility(SFStatusEffects.repair, 50, 180, 60) {{
                applyEffect = Fx.heal;
                activeEffect = new WaveEffect() {{
                    sides = 6;
                    lifetime = 30;
                    sizeFrom = sizeTo = 60;
                    strokeFrom = 2f;
                    colorFrom = Pal.heal;
                    colorTo = Color.white;
                }};
            }});
            parts.add(
                    new RegionPart("-side") {{
                        mirror = true;
                        layerOffset = -0.001f;
                        moveX = 1.75f;
                        moveY = -1.75f;
                    }},
                    new RegionPart("-front") {{
                        mirror = false;
                        layerOffset = -0.001f;
                        moveY = -2;
                    }}
            );
            weapons.add(
                    new Weapon(name("carrier-flame")) {{
                        reload = 300;
                        x = y = 0;
                        shootY = 9.5f;
                        rotate = false;
                        top = false;
                        recoil = 0;
                        cooldownTime = 60;
                        mirror = false;
                        shootSound = Sounds.torch;
                        alwaysContinuous = true;
                        shootWarmupSpeed = 0.125f;
                        minWarmup = 0.98f;
                        bullet = new ContinuousFlameBulletType() {{
                            recoil = 0.03f;
                            damage = 18;
                            knockback = 5;
                            status = SFStatusEffects.acidded;
                            statusDuration = 120;
                            laserAbsorb = true;
                            width = 3;
                            length = 150;
                            lightColor = Color.valueOf("a0b46e88");
                            flareColor = hitColor = Color.valueOf("a0b46e");
                            shootEffect = new WaveEffect() {{
                                sizeTo = 50;
                                interp = Interp.circleOut;
                                strokeFrom = 8;
                                colorFrom = Color.valueOf("7CF389");
                                colorTo = Color.valueOf("a0b46e");
                            }};
                            smokeEffect = new ParticleEffect() {{
                                particles = 9;
                                sizeFrom = 5;
                                length = 50;
                                baseLength = 5;
                                cone = 12;
                                sizeInterp = Interp.pow5In;
                                interp = Interp.pow10Out;
                                lifetime = 20;
                                colorFrom = Color.valueOf("a0b46e");
                                colorTo = Color.valueOf("7CF38990");
                            }};
                            colors = new Color[]{Color.valueOf("7CF38990"), Color.valueOf("97FFA880"), Color.valueOf("a0b46e"), Color.white};
                        }};
                    }}
            );
        }};
        flamer = new UnitType("flamer") {{
            constructor = UnitTypes.dagger.constructor;
            itemCapacity = 0;
            health = 1800;
            armor = 18;
            hitSize = 25;
            speed = 0.48f;
            rotateSpeed = 2.35f;
            baseRotateSpeed = 2.3f;
            mechStepParticles = true;
            mechFrontSway = 0.98f;
            mechSideSway = 0.4f;
            singleTarget = true;
            abilities.add(new LiquidExplodeAbility() {{
                liquid = SFLiquids.nitratedOil;
                amount = 300;
                radScale = 2.5f;
            }});
            abilities.add(new ArmorPlateAbility() {{
                healFlash = false;
                healthMultiplier = 1;
            }});
            immunities.addAll(StatusEffects.burning,StatusEffects.melting,StatusEffects.tarred);
            weapons.add(
                    new Weapon(name("flamer-weapon")) {{
                        mirror = true;
                        rotate = true;
                        rotateSpeed = 3f;
                        rotationLimit = 20;
                        x = 16;
                        shootY = 10.5f;
                        top = false;
                        alwaysContinuous = true;
                        cooldownTime = 300;
                        recoilTime = 160;
                        alternate = false;
                        shootSound = Sounds.torch;
                        heatColor = Color.valueOf("FF4040");
                        bullet = new ContinuousFlameBulletType(60) {{
                            recoil = 0.01f;
                            buildingDamageMultiplier = 2f;
                            colors = new Color[]{Color.valueOf("F33304A0"), Color.valueOf("F33304B8"), Color.valueOf("FEAF1FCC"), Color.valueOf("FCBE11"), Color.valueOf("FEFF5F")};
                            lightColor = Color.valueOf("FCBE11");
                            flareColor = Color.valueOf("FEAF1F");
                            flareWidth = 5;
                            length = 110;
                            width = 3;
                            lifetime = 8;
                            knockback = 2.5f;
                            statusDuration = 600;
                            status = StatusEffects.burning;
                            damage = 30;
                            incendAmount = 1;
                            incendChance = 0.2f;
                            incendSpread = 16;
                            makeFire = true;
                            shootEffect = new ParticleEffect() {{
                                particles = 4;
                                sizeFrom = 3.3f;
                                interp = Interp.pow10Out;
                                sizeInterp = Interp.pow5In;
                                length = 88;
                                lifetime = 32;
                                colorFrom = Color.valueOf("f8ad42");
                                colorTo = Color.valueOf("F33304B8");
                                cone = 13;
                            }};
                            smokeEffect = Fx.none;
                            hitEffect = new ParticleEffect() {{
                                particles = 3;
                                lifetime = 20;
                                sizeFrom = 2;
                                interp = Interp.pow3Out;
                                sizeInterp = Interp.pow3In;
                                colorFrom = Color.valueOf("F33304");
                                colorTo = Color.valueOf("FFE176");
                            }};
                        }};
                    }},
                    new Weapon(name("flamer-slag")) {{
                        x = 6.75f;
                        y = 3f;
                        rotate = false;
                        reload = 28.5f;
                        inaccuracy = 2;
                        recoil = 0;
                        shootSound = Sounds.artillery;
                        velocityRnd = 0.1f;
                        shoot.shots = 3;
                        shootCone = 30f;
                        bullet = new ArtilleryBulletType(10, 35) {{
                            height = 8;
                            width = 8;
                            frontColor = Pal.lightPyraFlame;
                            backColor = Pal.darkPyraFlame;
                            drag = 0.017f;
                            splashDamage = damage;
                            splashDamageRadius = 26;
                            incendAmount = 1;
                            incendChance = 0.2f;
                            incendSpread = 16;
                            status = StatusEffects.burning;
                            statusDuration = 360;
                            hitSound = Sounds.explosion;
                            hitEffect = Fx.flakExplosionBig;
                            shootEffect = Fx.shootPyraFlame;
                            despawnEffect = Fx.none;
                            fragBullets = 8;
                            fragBullet = new LiquidBulletType(Liquids.slag){{
                                damage = 8;
                                lifetime = 20;
                                speed = 6;
                                drag = 0.1f;
                            }};
                        }};
                    }}
            );
        }};
        thunder = new UnitType("thunder") {{
            constructor = UnitTypes.flare.constructor;
            itemCapacity = 0;
            flying = true;
            speed = 2;
            drag = 0.04f;
            accel = 0.05f;
            rotateSpeed = 5;
            hitSize = 30;
            health = 1500;
            armor = 14;
            lightRadius = 60;
            lightColor = SFColor.energyYellow;
            engineOffset = 14;
            engineSize = 4.5f;
            parts.add(new RegionPart("-side") {{
                mirror = false;
                moves.add(new PartMove(PartProgress.recoil, 0, -2, 0));
            }});
            weapons.add(
                    new Weapon(name("thunder-gun")) {{
                        x = 0;
                        shootWarmupSpeed = 0.13f;
                        minWarmup = 0.9f;
                        reload = 48;
                        shootSound = Sounds.missile;
                        shootStatus = StatusEffects.shielded;
                        shootStatusDuration = 50;
                        shoot = new ShootPattern() {{
                            shots = 3;
                            shotDelay = 3;
                            firstShotDelay = 14;
                        }};
                        mirror = false;
                        rotate = false;
                        shootY = 6;
                        bullet = new BasicBulletType(10, 17) {{
                            recoil = 0.3f;
                            lifetime = 30;
                            width = 12;
                            height = 15;
                            hitShake = 1.5f;
                            knockback = 9;
                            frontColor = backColor = SFColor.energyYellow;
                            trailLength = 5;
                            trailWidth = 2.25f;
                            trailColor = SFColor.energyYellow;
                            splashDamageRadius = 45;
                            splashDamage = 35;
                            status = StatusEffects.shocked;
                            lightning = 3;
                            lightningDamage = damage;
                            lightningLength = 6;
                            lightningLengthRand = 4;
                            trailInterval = 1;
                            trailRotation = true;
                            trailEffect = new ParticleEffect() {{
                                line = true;
                                interp = Interp.pow10Out;
                                lenFrom = 10;
                                strokeFrom = 1.5f;
                                length = -45;
                                lifetime = 40;
                                colorFrom = colorTo = SFColor.energyYellow;
                                cone = 13;
                            }};
                            smokeEffect = new ParticleEffect() {{
                                line = true;
                                particles = 9;
                                interp = Interp.pow10Out;
                                lenFrom = 18;
                                strokeFrom = 1.5f;
                                length = 45;
                                lifetime = 40;
                                colorFrom = colorTo = SFColor.energyYellow;
                                cone = 13;
                            }};
                            hitEffect = new ExplosionEffect() {{
                                lifetime = 35;
                                sparks = 0;
                                waveRad = 50;
                                waveLife = 22;
                                waveStroke = 3;
                                waveColor = smokeColor = SFColor.energyYellow;
                                smokes = 6;
                                smokeSize = 4.3f;
                                smokeRad = 40;
                            }};
                            despawnEffect = Fx.none;
                        }};
                    }},
                    new Weapon(name("thunder-cannon")) {{
                        x = 0;
                        shootY = 3;
                        shootWarmupSpeed = 0.1f;
                        minWarmup = 0.9f;
                        recoil = 0;
                        reload = 180;
                        rotate = false;
                        mirror = false;
                        shootSound = Sounds.laserblast;
                        bullet = new BasicBulletType(3, 200, "large-bomb") {{
                            collides = false;
                            hitSize = 15;
                            hitShake = 3;
                            height = width = 38;
                            spin = 10;
                            shrinkY = 0;
                            trailLength = 13;
                            trailWidth = 4;
                            trailColor = SFColor.energyYellow;
                            frontColor = SFColor.energyYellow;
                            backColor = Color.white;
                            lifetime = 120;
                            pierce = true;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 10;
                            bulletInterval = 2;
                            intervalBullets = 3;
                            intervalSpread = 16;
                            intervalBullet = new LightningBulletType() {{
                                lightningColor = SFColor.energyYellow;
                                lightningLength = 5;
                                lightningLengthRand = 5;
                                damage = 16;
                            }};
                            despawnEffect = Fx.none;
                            hitSound = Sounds.plasmaboom;
                            hitColor = SFColor.energyYellow;
                            hitEffect = new MultiEffect(
                                    new ParticleEffect() {{
                                        particles = 9;
                                        sizeFrom = 8;
                                        length = 65;
                                        baseLength = 9;
                                        lifetime = 15;
                                        colorFrom = SFColor.energyYellow;
                                        colorTo = Color.white;
                                        cone = 40;
                                    }},
                                    Fx.titanExplosion
                            );
                        }};
                    }});
        }};
        banisher = new UnitType("banisher") {{
            constructor = UnitTypes.elude.constructor;
            hovering = true;
            canDrown = false;
            drag = 0.07f;
            accel = 0.08f;
            speed = 0.82f;
            rotateSpeed = 1.5f;
            hitSize = 38f;
            trailLength = 45;
            waveTrailX = 12;
            waveTrailY = -23;
            trailScl = 3;
            health = 2500f;
            armor = 20;
            abilities.add(new StatusFieldAbility(StatusEffects.shielded,120,360,65){{
                applyEffect = Fx.none;
                activeEffect = new WaveEffect(){{
                    lifetime = 45f;
                    sides = 6;
                    sizeTo = 65;
                    strokeFrom = 6;
                    interp = Interp.circleOut;
                    colorFrom = colorTo = SFColor.energyYellow;
                }};
            }});
            abilities.add(new MoveEffectAbility() {{
                minVelocity = 0.5f;
                interval = 8;
                effect = new MultiEffect(
                        new ParticleEffect() {{
                            particles = 12;
                            sizeFrom = 3;
                            lifetime = 30;
                            sizeInterp = Interp.pow5In;
                            interp = Interp.pow5Out;
                            length = 30;
                            layer = 60;
                            colorFrom = SFColor.enemyRedLight.cpy().a(0.8f);
                            colorTo = Color.white.cpy().a(0.1f);
                        }},
                        new ParticleEffect() {{
                            particles = 2;
                            sizeFrom = 9;
                            lifetime = 30;
                            sizeInterp = Interp.pow5In;
                            interp = Interp.pow5Out;
                            length = 29;
                            layer = 60;
                            colorFrom = SFColor.enemyRedLight.cpy().a(0.8f);
                            colorTo = Color.white.cpy().a(0.1f);
                        }}
                );
            }});
            parts.add(new HoverPart() {{
                mirror = false;
                sides = 6;
                radius = 40;
                phase = 300;
                circles = 4;
                stroke = 6f;
                minStroke = 0.2f;
                layerOffset = -1f;
                color = SFColor.energyYellow;
            }});

            BulletType banisherLaser = new LaserBulletType(){{
                damage = 9;
                buildingDamageMultiplier = 2.6f;
                pierceArmor = true;
                status = SFStatusEffects.breakdown;
                statusDuration = 6;
                width = 8;
                lifetime = 15;
                length = 172;
                sideWidth = 1.4f;
                sideLength = 18;
                smokeEffect = Fx.none;
                shootEffect = Fx.shootSmallColor;
                colors = new Color[]{Pal.accent.cpy().a(.5f), Pal.accent.cpy().mul(1.2f), Color.white};
                hitEffect = Fx.hitLaserColor;
                hitColor = Pal.accent;
            }};

            weapons.addAll(
                    new Weapon(name("banisher-gun")) {{
                        x = 12;
                        y = 6;
                        reload = 10;
                        rotate = true;
                        rotateSpeed = 6;
                        shootCone = 5;
                        shootSound = Sounds.sap;
                        soundPitchMin = soundPitchMax = 0.5f;
                        recoil = 1f;
                        continuous = true;
                        bullet = banisherLaser;
                    }},
                    new Weapon(name("banisher-gun")) {{
                        x = -70/4f;
                        y = -6;
                        reload = 12;
                        rotate = true;
                        rotateSpeed = 6;
                        shootCone = 5;
                        shootSound = Sounds.sap;
                        recoil = 1f;
                        continuous = true;
                        bullet = banisherLaser;
                    }},
                    new Weapon(name("banisher-weapon")) {{
                        x = 0;
                        y = 0;
                        reload = 110f;
                        mirror = false;
                        rotate = true;
                        rotateSpeed = 2f;
                        shootCone = 5;
                        shootSound = Sounds.laser;
                        soundPitchMin = soundPitchMax = 1.5f;
                        recoil = 4f;
                        shoot.firstShotDelay = 40f;
                        chargeSound = Sounds.lasercharge2;
                        bullet = new BasicBulletType(18,120f,"sfire-mod-arrow-bullet") {{
                            lifetime = 32f;
                            drag = 0.022f;
                            trailLength = 16;
                            trailWidth = 3;
                            trailColor = backColor = frontColor = SFColor.energyYellow;
                            knockback = 16f;
                            width = 13;
                            height = 30;
                            hitShake = 6f;
                            hitSound = Sounds.explosion;
                            shootEffect = new ParticleEffect() {{
                                line = true;
                                particles = 6;
                                interp = Interp.pow5Out;
                                sizeInterp = Interp.pow5In;
                                strokeFrom = 1;
                                lenFrom = 18;
                                length = 60;
                                lifetime = 30;
                                colorTo = SFColor.energyYellow;
                                cone = 15f;
                            }};
                            smokeEffect = new ParticleEffect() {{
                                particles = 7;
                                interp = Interp.pow5Out;
                                sizeFrom = 6;
                                length = 50;
                                lifetime = 45;
                                colorFrom = SFColor.energyYellow;
                                colorTo = SFColor.energyYellow.cpy().a(0.8f);
                                cone = 15f;
                            }};
                            despawnEffect = new ExplosionEffect() {{
                                sparks = 0;
                                smokes = 11;
                                smokeRad = 23f;
                                smokeSize = 5;
                                lifetime = 45;
                                smokeColor = waveColor = SFColor.energyYellow;
                                waveStroke = 3;
                                waveRad = 30;
                                waveLife = 20;
                            }};
                            hitEffect = new ParticleEffect() {{
                                particles = 8;
                                sizeFrom = 6;
                                length = 50;
                                interp = Interp.pow5Out;
                                sizeInterp = Interp.pow10In;
                                lifetime = 30;
                                colorTo = SFColor.energyYellow;
                                cone = 20;
                            }};
                            fragBullets = 1;
                            fragRandomSpread = 0;
                            fragBullet = new ShrapnelBulletType() {{
                                damage = 120;
                                knockback = 20f;
                                length = 40f;
                                width = 8f;
                                fromColor = toColor = SFColor.energyYellow;
                            }};
                        }};
                    }}
            );
        }};
        hammer = new UnitType("hammer") {{
            constructor = UnitTypes.atrax.constructor;
            itemCapacity = 0;
            legCount = 4;
            legLength = 14;
            legForwardScl = 0.6f;
            legMoveSpace = 1.4f;
            hovering = true;
            legContinuousMove = true;
            speed = 1;
            hitSize = 18;
            targetAir = false;
            health = 1950;
            armor = 8;
            rotateSpeed = 2.5f;
            faceTarget = true;
            weapons.add(new Weapon(name("hammer-weapon")) {{
                reload = 100;
                shootY = 13;
                rotate = false;
                mirror = false;
                shootStatusDuration = 110;
                shootStatus = StatusEffects.slow;
                x = 0;
                shake = 2;
                recoil = 8;
                cooldownTime = 120;
                ejectEffect = Fx.casing4;
                shootSound = Sounds.artillery;
                bullet = new ArtilleryBulletType(5, 95) {{
                    collides = true;
                    collidesTiles = true;
                    absorbable = false;
                    splashDamage = 105;
                    splashDamageRadius = 45;
                    buildingDamageMultiplier = 2.5f;
                    hitShake = 3;
                    lifetime = 80;
                    shootEffect = Fx.shootBig2;
                    smokeEffect = Fx.shootBigSmoke2;
                    hitSound = Sounds.explosion;
                    width = 13;
                    height = 18;
                    trailWidth = 2.5f;
                    trailLength = 18;
                    trailColor = Color.valueOf("FEEBB390");
                    hitEffect = Fx.flakExplosionBig;
                    despawnEffect = Fx.blastExplosion;
                }};
            }});
        }};
        tau = new UnitType("tau") {{
            constructor = UnitTypes.gamma.constructor;
            outlineColor = SFColor.darkOutline;
            aiController = BuilderAI::new;
            flying = true;
            rotateSpeed = 8;
            speed = 3.8f;
            accel = 0.06f;
            drag = 0.04f;
            hitSize = 30;
            buildRange = 220;
            buildBeamOffset = 11;
            buildSpeed = 2.25f;
            itemCapacity = 150;
            mineRange = 50;
            mineTier = 4;
            mineSpeed = 10;
            health = 1000;
            engineSize = 2.8f;
            engineOffset = 16;
            setEnginesMirror(new UnitEngine(-10, -11, 3f, -112.5f));
            weapons.add(new Weapon(name("tau-weapon")) {{
                x = y = 0;
                recoil = 0;
                reload = 30;
                rotate = false;
                shootSound = Sounds.missile;
                inaccuracy = 3;
                baseRotation = 45;
                shootCone = 60;
                shoot = new ShootSpread(6, 3);
                bullet = new MissileBulletType(8, 12) {{
                    splashDamage = 10;
                    splashDamageRadius = 10;
                    buildingDamageMultiplier = 0.3f;
                    hitEffect = new WrapEffect(Fx.hitSquaresColor, Pal.heal);
                    despawnEffect = new WrapEffect(Fx.hitBulletColor, Pal.heal);
                    collidesTeam = true;
                    healPercent = 1 / 6f;
                    reflectable = false;
                    trailColor = Pal.heal;
                    frontColor = Color.white;
                    backColor = Pal.heal;
                    status = StatusEffects.electrified;
                    statusDuration = 30;
                    width = 8;
                    height = 11;
                    trailLength = 4;
                    trailWidth = 2.2f;
                    homingDelay = 5;
                    homingPower = 0.5f;
                    lifetime = 36;
                }};
            }});
        }};
        omega = new UnitType("omega") {{
            constructor = UnitTypes.gamma.constructor;
            outlineColor = SFColor.darkOutline;
            aiController = BuilderAI::new;
            flying = true;
            rotateSpeed = 6;
            speed = 3.4f;
            accel = 0.05f;
            drag = 0.04f;
            hitSize = 38;
            buildRange = 288;
            buildBeamOffset = 16;
            buildSpeed = 3;
            health = 4800;
            armor = 12;
            engineSize = 5.5f;
            engineOffset = 16;
            setEnginesMirror(
                    new UnitEngine(-13, -15, 2.5f, 225),
                    new UnitEngine(-16, -9.5f, 2, 225));
            weapons.add(
                    new Weapon(name("omega-gun")) {{
                        x = y = 0;
                        recoil = 0;
                        shootX = -5;
                        shootY = 22;
                        reload = 6;
                        rotate = false;
                        shootSound = Sounds.bolt;
                        bullet = new BasicBulletType(10, 45) {{
                            pierceArmor = true;
                            pierce = true;
                            pierceCap = 2;
                            buildingDamageMultiplier = 0.3f;
                            hitEffect = Fx.hitFlamePlasma;
                            despawnEffect = Fx.none;
                            makeFire = true;
                            trailColor = Pal.darkPyraFlame;
                            frontColor = Pal.lightPyraFlame;
                            backColor = Pal.darkPyraFlame;
                            status = StatusEffects.burning;
                            statusDuration = 600;
                            trailWidth = 3;
                            trailLength = 2;
                            width = 10;
                            height = 15;
                            lifetime = 36;
                        }};
                    }},
                    new Weapon(name("omega-missile")) {{
                        x = 14;
                        reload = 42;
                        rotate = false;
                        shoot = new ShootBarrel() {{
                            shots = 6;
                            shotDelay = 2;
                            barrels = new float[]{
                                    0, 1, -30,
                                    1, 0, -35,
                                    2, -4, -40,
                                    2, -4, -40,
                                    1, 0, -35,
                                    0, 1, -30
                            };
                        }};
                        shootSound = Sounds.missile;
                        inaccuracy = 2;
                        shootCone = 180;
                        bullet = new MissileBulletType(11, 33) {{
                            keepVelocity = false;
                            buildingDamageMultiplier = 0.3f;
                            status = StatusEffects.blasted;
                            homingRange = 40;
                            homingPower = 0.15f;
                            splashDamage = 45;
                            splashDamageRadius = 45;
                            hitEffect = Fx.flakExplosionBig;
                            trailWidth = 0.8f;
                            trailLength = 8;
                            trailColor = Color.white.cpy().a(0.5f);
                            drag = -0.012f;
                            shrinkY = 0;
                            width = 6;
                            height = 12;
                            lifetime = 30;
                        }};
                    }}
            );
            immunities = ObjectSet.with(StatusEffects.sapped, StatusEffects.slow, StatusEffects.unmoving, StatusEffects.sporeSlowed, StatusEffects.electrified, SFStatusEffects.acidded, SFStatusEffects.breakdown);
        }};

        utv = new TankUnitType("UTV") {{
            constructor = UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = true;
            canDrown = false;
            healFlash = false;
            faceTarget = false;

            treadFrames = 16;
            treadPullOffset = 8;
            treadRects = new Rect[]{new Rect(-60f, -76f, 24, 152)};
            crushDamage = 2;

            health = 15000;
            armor = 36;
            hitSize = 32;
            speed = 0.78f;
            rotateSpeed = 0.94f;
            accel = 0.18f;
            drag = 0.17f;
            itemCapacity = 3000;
            itemOffsetY = 15;
            lightRadius = 60;
            weapons.add(new Weapon(name("utv-1")) {{
                mirror = false;
                rotate = true;
                rotateSpeed = 3;
                reload = 12.51f;
                recoil = 1.5f;
                inaccuracy = 1.8f;
                x = 0;
                shootY = 18.5f;
                shootCone = 31.8f;
                shootSound = Sounds.bolt;
                ejectEffect = Fx.casing3Double;
                shoot = new ShootAlternate(16);
                bullet = new BasicBulletType(6, 85) {{
                    width = 9;
                    height = 12;
                    lifetime = 280/6f;
                    frontColor = Color.white;
                    backColor = trailColor = SFColor.energyYellow;
                    trailWidth = 2;
                    trailLength = 12;
                    shootEffect = Fx.shootBig2;
                    smokeEffect = new ParticleEffect() {{
                        particles = 4;
                        sizeFrom = 3;
                        length = 35;
                        interp = Interp.fastSlow;
                        sizeInterp = Interp.pow3In;
                        lifetime = 23;
                        colorFrom = colorTo = SFColor.energyYellow;
                        cone = 12;
                    }};
                    hitEffect = new ParticleEffect() {{
                        particles = 4;
                        sizeFrom = 5;
                        length = 25;
                        interp = Interp.fastSlow;
                        lifetime = 15;
                        colorFrom = colorTo = SFColor.energyYellow;
                    }};
                }};
            }});
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.electrified, StatusEffects.sapped, SFStatusEffects.breakdown, SFStatusEffects.scrambled, SFStatusEffects.overLoad);
        }};
        utvA = new TankUnitType("UTV-Artillery") {{
            constructor = UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = false;
            healFlash = false;
            faceTarget = false;
            targetAir = false;
            itemCapacity = 0;

            treadFrames = 16;
            treadPullOffset = 8;
            treadRects = new Rect[]{new Rect(-60f, -76f, 24, 152)};
            crushDamage = 2;
            abilities.add(new RegenAbility() {{
                percentAmount = 0.002f;
            }});
            parts.add(
                    new RegionPart("-leg") {{
                        mirror = true;
                        y = -6;
                        layerOffset = -1;
                        moveX = -15;
                        moveY = -19;
                        moveRot = -35;
                    }},
                    new HaloPart() {{
                        sides = 6;
                        shapes = 1;
                        rotateSpeed = 1.3f;
                        color = SFItems.discFabric.color;
                        colorTo = SFColor.energyYellow;
                        layer = 49;
                        tri = false;
                        hollow = true;
                        strokeTo = 3;
                        radiusTo = 30;
                        haloRadius = 0;
                    }}
            );
            health = 15000;
            armor = 36;
            hitSize = 32;
            speed = 0.78f;
            rotateSpeed = 0.94f;
            accel = 0.18f;
            drag = 0.17f;
            lightRadius = 60;
            autoFindTarget = false;
            weapons.add(new Weapon(name("utv-2")) {{
                mirror = false;
                top = true;
                rotate = true;
                layerOffset = 0.001f;
                rotateSpeed = 0.85f;
                reload = 190f;
                recoil = 0f;
                recoilTime = 70;
                cooldownTime = 160;
                inaccuracy = 3f;
                x = 0;
                y = -4;
                parts.add(new RegionPart("-barrel") {{
                    progress = PartProgress.warmup;
                    top = true;
                    under = true;
                    moveY = 10;
                    heatColor = Color.valueOf("FF4040");
                    moves.add(new PartMove(PartProgress.recoil, 0, -9, 0));
                }});
                shootY = 33f;
                shootCone = 8;
                shootSound = Sounds.mediumCannon;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = 195;
                shootWarmupSpeed = 0.013f;
                minWarmup = 0.9f;
                shake = 7;
                velocityRnd = 0.06f;
                ejectEffect = new MultiEffect(
                        new WaveEffect() {{
                            lifetime = 16;
                            sizeTo = 60;
                            strokeFrom = 20;
                            strokeTo = 8;
                            layer = 60;
                            interp = Interp.circleOut;
                            colorFrom = Color.white;
                            colorTo = Color.white.cpy().a(0.2f);
                        }},
                        new WrapEffect() {{
                            effect = Fx.titanSmoke;
                            color = SFColor.smoke.cpy().a(0.85f);
                            lightOpacity = 0;
                            layer = 49;
                        }},
                        new ParticleEffect() {{
                            particles = 1;
                            line = true;
                            casingFlip = true;
                            interp = Interp.pow10Out;
                            sizeInterp = Interp.pow5In;
                            strokeFrom = 6;
                            lenFrom = 15;
                            baseLength = -10;
                            length = -48;
                            layer = 49;
                            randLength = false;
                            lifetime = 80;
                            lightOpacity = 0;
                            colorFrom = Pal.bulletYellowBack;
                            colorTo = Color.valueOf("737373");
                        }}

                );
                bullet = new ArtilleryBulletType(12, 160, "shell") {{
                    width = 12;
                    height = 18;
                    frontColor = Color.white;
                    backColor = SFColor.energyYellow;
                    lifetime = 53.3f;
                    splashDamage = 2503.52f;
                    splashDamageRadius = 65;
                    trailColor = SFColor.energyYellow;
                    trailWidth = 3;
                    trailLength = 32;
                    trailInterval = 1f;
                    trailEffect = new ParticleEffect() {{
                        particles = 1;
                        sizeFrom = 4.3f;
                        sizeTo = 10;
                        lifetime = 25;
                        sizeInterp = Interp.pow5In;
                        length = 0;
                        colorFrom = SFColor.energyYellow;
                        colorTo = SFColor.energyYellow.cpy().a(0);
                    }};
                    shootEffect = Fx.shootBig2;
                    smokeEffect = new ParticleEffect() {{
                        particles = 12;
                        sizeFrom = 4;
                        lifetime = 68;
                        sizeInterp = Interp.pow5In;
                        interp = Interp.pow5Out;
                        length = 36;
                        cone = 16;
                        lightOpacity = 0.15f;
                        colorFrom = SFColor.energyYellow.cpy().a(0.5f);
                        colorTo = SFColor.energyYellow.cpy().a(0.3f);
                    }};
                    hitSound = Sounds.explosionbig;
                    hitSoundPitch = 0.5f;
                    hitSoundVolume = 3;
                    hitShake = 8;
                    hitColor = SFColor.energyYellow.cpy().a(0.4f);
                    hitEffect = new MultiEffect(
                            new WaveEffect() {{
                                lifetime = 8;
                                sizeTo = 80;
                                strokeFrom = 20;
                                strokeTo = 8;
                                interp = Interp.circleOut;
                                colorFrom = SFColor.energyYellow.cpy().a(0.4f);
                                colorTo = SFColor.energyYellow.cpy().a(0.01f);
                            }},
                            Fx.titanExplosion,
                            Fx.titanSmoke
                    );
                    status = SFStatusEffects.breakdown;
                    statusDuration = 460;
                }};
            }});
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.wet, StatusEffects.electrified, StatusEffects.sapped, SFStatusEffects.breakdown, SFStatusEffects.scrambled, SFStatusEffects.overLoad);
        }};
        utvC = new TankUnitType("UTV-Command") {{
            constructor = UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = true;
            canDrown = false;
            healFlash = false;
            faceTarget = false;
            itemCapacity = 0;

            treadFrames = 16;
            treadPullOffset = 8;
            treadRects = new Rect[]{new Rect(-60f, -76f, 24, 152)};
            crushDamage = 2;
            abilities.addAll(
                    new StatusFieldAbility(StatusEffects.shielded, 80, 140, 78) {{
                        activeEffect = new MultiEffect(
                                new WaveEffect() {{
                                    lifetime = 60;
                                    sides = 6;
                                    sizeFrom = sizeTo = 30;
                                    strokeFrom = 3;
                                    interp = Interp.circleIn;
                                    colorFrom = SFColor.discLight;
                                    colorTo = SFColor.energyYellow;
                                }},
                                new WaveEffect() {{
                                    startDelay = 40;
                                    lifetime = 60;
                                    sides = 6;
                                    sizeFrom = sizeTo = 59;
                                    strokeFrom = 4;
                                    interp = Interp.circleIn;
                                    colorFrom = SFColor.discLight;
                                    colorTo = SFColor.energyYellow;
                                }},
                                new WaveEffect() {{
                                    startDelay = 80;
                                    lifetime = 60;
                                    sides = 6;
                                    sizeFrom = sizeTo = 88;
                                    strokeFrom = 5;
                                    interp = Interp.circleIn;
                                    colorFrom = SFColor.discLight;
                                    colorTo = SFColor.energyYellow;
                                }}
                        );
                        applyEffect = Fx.none;
                    }},
                    new StatusFieldAbility(SFStatusEffects.stormed, 25, 20, 78) {{
                        applyEffect = activeEffect = Fx.none;
                    }}
            );
            health = 13000;
            armor = 14;
            hitSize = 32;
            speed = 0.78f;
            rotateSpeed = 0.94f;
            accel = 0.18f;
            drag = 0.17f;
            lightRadius = 200;
            weapons.add(new Weapon(name("utv-3")) {{
                            mirror = false;
                            rotate = true;
                            rotateSpeed = 8;
                            reload = 460f;
                            recoil = 0;
                            x = 0;
                            y = -3;
                            shootSound = Sounds.wave;
                            shootCone = 360;
                            bullet = new BasicBulletType(640, 0) {{
                                instantDisappear = true;
                                lifetime = 1;
                                shootEffect = new WaveEffect() {{
                                    lifetime = 90;
                                    sizeTo = 640;
                                    strokeFrom = 10;
                                    colorTo = colorFrom = SFColor.discLight;
                                }};
                                smokeEffect = Fx.none;
                                splashDamageRadius = 640;
                                splashDamage = 0.01f;
                                hitEffect = despawnEffect = Fx.none;
                                status = SFStatusEffects.marked;
                                statusDuration = 300;
                            }};
                        }}
            );
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.electrified, StatusEffects.sapped, SFStatusEffects.breakdown, SFStatusEffects.scrambled, SFStatusEffects.overLoad);
        }};
        utvD = new TankUnitType("UTV-Defense") {{
            constructor = UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = true;
            canDrown = false;
            healFlash = false;
            faceTarget = false;
            itemCapacity = 0;

            treadFrames = 16;
            treadPullOffset = 8;
            treadRects = new Rect[]{new Rect(-60f, -76f, 24, 152)};
            crushDamage = 2;
            abilities.add(new ForceFieldAbility(96, 4, 3300, 60 * 5, 6, 0));
            health = 15000;
            armor = 36;
            hitSize = 32;
            speed = 0.78f;
            rotateSpeed = 0.94f;
            accel = 0.18f;
            drag = 0.17f;
            lightRadius = 60;
            weapons.add(new PointDefenseWeapon(name("utv-4")) {{
                mirror = false;
                rotate = true;
                rotateSpeed = 8.8f;
                reload = 15;
                recoil = 0;
                x = 0;
                y = -14 / 4f;
                shootY = 10;
                targetInterval = 6.5f;
                targetSwitchInterval = 7.5f;
                shootSound = Sounds.bolt;
                bullet = new BulletType() {{
                    maxRange = 8 * 46;
                    damage = 260;
                    shootEffect = Fx.shootBig2;
                    smokeEffect = Fx.shootBigSmoke2;
                    hitEffect = new WrapEffect(Fx.hitBulletColor, SFColor.energyYellow);
                }};
            }});
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.electrified, StatusEffects.sapped, SFStatusEffects.breakdown, SFStatusEffects.scrambled, SFStatusEffects.overLoad);
        }};
        utvM = new TankUnitType("UTV-Missile") {{
            constructor = UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = true;
            canDrown = false;
            healFlash = false;
            faceTarget = false;
            targetGround = false;
            itemCapacity = 0;

            treadFrames = 16;
            treadPullOffset = 8;
            treadRects = new Rect[]{new Rect(-60f, -76f, 24, 152)};
            crushDamage = 2;
            abilities.add(new RegenAbility() {{
                percentAmount = 0.002f;
            }});
            parts.add(new HaloPart() {{
                sides = 6;
                shapes = 1;
                rotateSpeed = -1.3f;
                color = SFItems.discFabric.color;
                colorTo = SFColor.energyYellow;
                layer = 49;
                tri = false;
                hollow = true;
                strokeTo = 3;
                radiusTo = 30;
                haloRadius = 0;
            }});
            health = 15000;
            armor = 36;
            hitSize = 32;
            speed = 0.78f;
            rotateSpeed = 0.94f;
            accel = 0.18f;
            drag = 0.17f;
            lightRadius = 60;
            weapons.add(new Weapon(name("utv-5")) {{
                            mirror = false;
                            rotate = true;
                            rotateSpeed = 4.6f;
                            reload = 88;
                            recoil = 0;
                            inaccuracy = 1.8f;
                            x = 0;
                            y = -2;
                            shootY = 10;
                            shootCone = 30;
                            shootSound = Sounds.missileLaunch;
                            shootStatus = StatusEffects.overclock;
                            shootStatusDuration = 90;
                            ejectEffect = Fx.none;
                            shoot = new ShootBarrel() {{
                                shots = 4;
                                shotDelay = 12;
                                barrels = new float[]{
                                        6.75f, 0, 0,
                                        -11.25f, 0, 0,
                                        11.25f, 0, 0,
                                        -6.75f, 0, 0
                                };
                            }};
                            bullet = new FlakBulletType(11, 160) {{
                                homingDelay = 11;
                                homingPower = 0.6f;
                                homingRange = 64;
                                splashDamage = 155;
                                splashDamageRadius = 60;
                                keepVelocity = false;
                                scaleLife = true;
                                lifetime = 36;
                                sprite = "sfire-mod-missile2";
                                width = 12;
                                height = 46;
                                frontColor = Color.darkGray;
                                backColor = trailColor = SFColor.energyYellow;
                                trailChance = 1;
                                trailRotation = true;
                                trailEffect = new ParticleEffect() {{
                                    particles = 4;
                                    sizeFrom = 1;
                                    sizeTo = 3;
                                    length = 10;
                                    lifetime = 23;
                                    lightOpacity = 0.2f;
                                    interp = Interp.circleOut;
                                    sizeInterp = Interp.pow5Out;
                                    colorFrom = SFColor.energyYellow;
                                    colorTo = SFColor.energyYellow.cpy().a(0.05f);
                                    cone = 10;
                                }};
                                shootEffect = Fx.shootBig2;
                                smokeEffect = new ParticleEffect() {{
                                    particles = 9;
                                    sizeFrom = 2.8f;
                                    length = 46;
                                    interp = Interp.pow10Out;
                                    sizeInterp = Interp.pow5In;
                                    lifetime = 16;
                                    colorFrom = colorTo = SFColor.energyYellow;
                                    cone = 16;
                                }};
                                hitShake = 3;
                                hitSound = Sounds.explosionbig;
                                hitSoundPitch = hitSoundVolume = 2;
                                hitColor = SFColor.energyYellow;
                                hitEffect = new MultiEffect(
                                        new ParticleEffect() {{
                                            particles = 16;
                                            sizeFrom = 6;
                                            length = 60;
                                            lightOpacity = 0.6f;
                                            interp = Interp.circleOut;
                                            sizeInterp = Interp.pow10In;
                                            lifetime = 40;
                                            colorFrom = SFColor.energyYellow.cpy().a(0.8f);
                                            colorTo = SFColor.energyYellow.cpy().a(0.2f);
                                        }},
                                        Fx.titanExplosion
                                );
                                despawnEffect = Fx.flakExplosion;
                                status = SFStatusEffects.breakdown;
                                statusDuration = 100;
                            }};
                        }}
            );
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.electrified, StatusEffects.sapped, SFStatusEffects.breakdown, SFStatusEffects.scrambled, SFStatusEffects.overLoad);
        }};

        assemblerDrone = new UnitType("assembler-drone") {{
            constructor = UnitTypes.assemblyDrone.constructor;
            outlineColor = SFColor.darkOutline;
            flying = true;
            controller = u -> new AssemblerAI();
            speed = 0.95f;
            rotateSpeed = 2.5f;
            hitSize = 12;
            buildBeamOffset = 6;
            isEnemy = false;
            hidden = true;
            useUnitCap = false;
            logicControllable = false;
            playerControllable = false;
            allowedInPayloads = false;
            createWreck = false;
            engineOffset = 5;
            engineSize = 3;
        }};
        assemblerDrone2 = new UnitType("assembler-drone-large") {{
            constructor = UnitTypes.assemblyDrone.constructor;
            outlineColor = SFColor.darkOutline;
            health = 550;
            armor = 5;
            flying = true;
            controller = u -> new AssemblerAI();
            speed = 1.15f;
            rotateSpeed = 2.5f;
            hitSize = 24;
            buildBeamOffset = 11;
            lightRadius = 60;
            lightColor = Pal.heal;
            immunities = ObjectSet.with(StatusEffects.electrified, StatusEffects.unmoving, SFStatusEffects.scrambled);
            isEnemy = false;
            hidden = true;
            useUnitCap = false;
            logicControllable = false;
            playerControllable = false;
            allowedInPayloads = false;
            createWreck = false;
            engineOffset = 16;
            engineSize = 2.6f;
            setEnginesMirror(new UnitEngine(-9.25f, -8, 2.2f, 225));
            abilities.add(new RepairFieldAbility(180, 60, 80) {{
                activeEffect = new WaveEffect() {{
                    lifetime = 248;
                    sizeTo = 80;
                    strokeFrom = 3;
                    colorFrom = Pal.heal;
                    colorTo = Pal.heal.cpy().a(0.5f);
                }};
            }});
            parts.add(new ShapePart() {{
                stroke = strokeTo = 1.5f;
                circle = true;
                hollow = true;
                radius = radiusTo = 80;
                color = Pal.heal.cpy().a(0.4f);
                colorTo = Pal.heal.cpy();
            }});
            weapons.add(new RepairBeamWeapon(name("assembler-weapon")) {{
                x = 0;
                y = 1.75f;
                rotateSpeed = 4.5f;
                beamWidth = 0.6f;
                repairSpeed = 1.5f;
                bullet = new BulletType() {{
                    maxRange = 81;
                }};
            }});
        }};
        armedDrone = new UnitType("armed-drone") {{
            constructor = UnitTypes.flare.constructor;
            flying = true;
            outlineColor = SFColor.darkOutline;
            speed = 3.8f;
            accel = 0.06f;
            drag = 0.05f;
            rotateSpeed = 8;
            hitSize = 22;
            isEnemy = false;
            //hidden = true;
            useUnitCap = false;
            drawShields = false;
            drawMinimap = false;
            health = 510;
            range = 230;
            trailLength = 6;
            engineOffset = 12;
            engineSize = 3;
            setEnginesMirror(new UnitEngine(-4, -10, 2.5f, 315));
            immunities = ObjectSet.with(StatusEffects.corroded, StatusEffects.sporeSlowed, SFStatusEffects.magnStrif, SFStatusEffects.marked);
            abilities.add(new ForceFieldAbility(20, 8, 2990, 480));
            abilities.add(new RegenAbility() {{
                percentAmount = 0.075f;
            }});
            singleTarget = true;
            weapons.add(
                    new Weapon(name("silence")) {{
                        x = 0;
                        reload = 4;
                        mirror = false;
                        top = false;
                        rotate = false;
                        shootCone = 11;
                        shootSound = Sounds.blaster;
                        shoot = new ShootBarrel() {{
                            shots = 2;
                            barrels = new float[]{
                                    7.75f, 12, 0,
                                    -7.75f, 12, 0,
                                    10.75f, 10.5f, 0,
                                    -10.75f, 10.5f, 0
                            };
                        }};
                        shootWarmupSpeed = 0.1f;
                        minWarmup = 0.8f;
                        shake = 0.1f;
                        recoils = 4;
                        recoilTime = 20;
                        recoil = 0;
                        for (int i = 1; i <= 2; i++) {
                            int fi = i;
                            parts.add(new RegionPart("-1") {{
                                recoilIndex = fi - 1;
                                mirror = true;
                                layerOffset = -0.001f;
                                moveY = 9.5f;
                                x = 8;
                                moves.add(new PartMove(PartProgress.recoil, 0, -2, 0));
                            }});
                        }
                        for (int i = 1; i <= 2; i++) {
                            int fi = i;
                            parts.add(new RegionPart("-2") {{
                                recoilIndex = fi + 1;
                                mirror = true;
                                layerOffset = -0.001f;
                                progress = PartProgress.warmup;
                                moveX = -3;
                                moveY = 8;
                                x = -8;
                                moves.add(new PartMove(PartProgress.recoil, 0, -2, 0));
                            }});
                        }
                        bullet = new RailBulletType() {{
                            damage = 32;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 10;
                            length = 232;
                            pierceDamageFactor = 0.5f;
                            pierce = false;
                            pierceEffect = Fx.none;
                            pointEffectSpace = 500;
                            pointEffect = new ParticleEffect() {{
                                particles = 1;
                                lifetime = 6;
                                line = true;
                                lenFrom = 50;
                                strokeFrom = 2;
                                length = 0;
                                baseLength = 224;
                                colorFrom = colorTo = SFColor.energyYellow;
                                cone = 0;
                            }};
                            hitColor = SFColor.energyYellow;
                            hitEffect = new MultiEffect(
                                    Fx.hitBulletColor,
                                    new ParticleEffect() {{
                                        particles = 3;
                                        line = true;
                                        strokeFrom = 1.5f;
                                        lenFrom = 16;
                                        sizeInterp = Interp.fastSlow;
                                        interp = Interp.circleOut;
                                        length = 32;
                                        lifetime = 15;
                                        colorFrom = colorTo = SFColor.energyYellow;
                                    }}
                            );
                            despawnEffect = Fx.none;
                        }};
                    }}
            );
        }};
    }
}
