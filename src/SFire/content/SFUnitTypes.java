package SFire.content;

import SFire.SFireMod;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.math.*;
import arc.math.geom.Rect;
import arc.struct.ObjectSet;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.AssemblerAI;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.PointDefenseWeapon;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static SFire.SFireMod.name;

public class SFUnitTypes {

    public static Weapon copy(Weapon weapon, float x, float y){
        Weapon n = weapon.copy();
        n.x = x;
        n.y = y;
        return n;
    }
    public static Weapon copyRotate(Weapon weapon, float x, float y, float br){
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
        bullet = new RailBulletType(){{
            damage = 78;
            length = 330;
            pierce = false;
            pierceDamageFactor = 0.4f;
            pierceEffect = Fx.none;
            pointEffectSpace = 16;
            pointEffect = new ParticleEffect(){{
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
            endEffect = new ParticleEffect(){{
                particles = 1;
                length = 0;
                sizeFrom = 1;
                lifetime = 10;
                colorFrom = colorTo = SFColor.enemyRedLight;
            }};
            smokeEffect = Fx.none;
            hitEffect = new MultiEffect(
                    Fx.hitBulletColor,
                    new ParticleEffect(){{
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
    }}

;
    public static UnitType
            //enemy only
            flareX,/* flareY,*/ electrodile, thunderclap, knocker,
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
            farmer, carrier, //repairer,
            flamer, thunder, banisher, hammer,
            omega, terrascape,
    //campaign only
    utv, utvA, utvC, utvD, utvM,
    //drones
    assemblerDrone, assemblerDrone2, armedDrone
;
    public static void loadUnit() {
        flareX = new UnitType("flareX") {{
            constructor = UnitTypes.flare.constructor;
            flying = true;
            outlineColor = SFColor.darkOutline;
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
        electrodile = new UnitType("electrodile") {{
            constructor = UnitTypes.elude.constructor;
            hovering = true;
            outlineColor = SFColor.darkOutline;
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
            abilities.add(new RegenAbility(){{percentAmount=0.005f;}});
            abilities.add(new ShieldRegenFieldAbility(50,2500,60,50){{
                applyEffect = new WaveEffect(){{
                    sides = 4;
                    sizeTo = 15;
                    interp = Interp.circleOut;
                    strokeFrom = 6;
                    colorFrom = colorTo = SFColor.enemyRedLight;
                }};
                activeEffect = new WaveEffect(){{
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
            abilities.add(new ShieldArcAbility(){{
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
                    new Weapon(name("electrodile-weapon")){{
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
                                new RegionPart("-barrel"){{
                                    under = true;
                                    mirror = false;
                                    x = 0;
                                    recoilIndex = 0;
                                    heatProgress = PartProgress.recoil;
                                    heatColor = Color.valueOf("FF4040");
                                    progress = PartProgress.recoil;
                                    moveY = -8;
                                }},
                                new RegionPart("-barrel"){{
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
                                new RegionPart("-barrel"){{
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
                                new HaloPart(){{
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
                                new ShapePart(){{
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
                        bullet = new BasicBulletType(15,135){{
                            splashDamage = 122;
                            splashDamageRadius = 56;
                            lightningColor = SFColor.enemyRedLight;
                            lightning = 3;
                            lightningLength = 7;
                            lightningLengthRand = 3;
                            lifetime = 28;
                            hitShake =3;
                            width = 12;
                            height = 18;
                            frontColor = backColor = SFColor.enemyRedLight;
                            trailLength = 32;
                            trailWidth = 3;
                            trailColor = SFColor.enemyRedLight;
                            shootEffect = Fx.shootBig2;
                            smokeEffect = new ParticleEffect(){{
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
                            hitEffect = new ExplosionEffect(){{
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
            outlineColor = SFColor.darkOutline;
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
            abilities.add(new StatusFieldAbility(StatusEffects.shielded, 30, 60, 8){{
                applyEffect = Fx.none;
                activeEffect = new WaveEffect(){{
                    interp = Interp.circleOut;
                    lifetime = 10;
                    sizeTo = 45;
                    strokeFrom = 8;
                    colorFrom = SFColor.enemyRedLight;
                    colorTo = Pal.sap.cpy().a(0.1f);
                }};
            }});
            for(int i = 1; i <= 3; i++){
                int fi = i;
                parts.add(new RegionPart("-missile-wing"){{
                    mirror = true;
                    under = true;
                    layerOffset = -0.001f;
                    progress = PartProgress.warmup;
                    heatProgress = PartProgress.warmup;
                    heatColor = SFColor.enemyRedLight;
                    moveX = 6 + fi;
                    moveY = -13.25f + 1.5f*fi;
                    moveRot = -180 + 30*fi;
                    moves.add(new PartMove(PartProgress.recoil,0,-2,0));
                }});
            }
            faceTarget = true;
            weapons.add(
                    new Weapon(name("knocker-weapon")){{
                        rotate = false;
                        reload = 180;
                        x = 0;
                        mirror = false;
                        shootCone = 4;
                        ejectEffect = Fx.none;
                        shootSound = Sounds.missileLaunch;
                        shootStatusDuration = 180;
                        shootStatus = StatusEffects.slow;
                        shoot = new ShootPattern(){{
                            firstShotDelay = 180;
                        }};
                        parts.add(new RegionPart("-side"){{
                            mirror = true;
                            progress = PartProgress.warmup;
                            moveX = 3.5f;
                            moveRot = -35;
                            moves.add(new PartMove(PartProgress.charge,0.5f,-2,-10));
                        }});
                        shootWarmupSpeed = 0.05f;
                        minWarmup = 0.9f;
                        bullet = new BulletType(){{
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
                            spawnUnit = new MissileUnitType("knocker-missile"){{
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
                                abilities.add(new StatusFieldAbility(StatusEffects.shielded, 30, 60, 8){{
                                    applyEffect = Fx.none;
                                    activeEffect = new WaveEffect(){{
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
                                for(int i = 1; i <= 3; i++){
                                    int fi = i;
                                    parts.add(new RegionPart("-wing"){{
                                        mirror = true;
                                        under = true;
                                        layerOffset = -0.001f;
                                        heatProgress = PartProgress.warmup;
                                        heatColor = Color.valueOf("A278E1");
                                        x = 6 + fi;
                                        y = -13.25f + 1.5f*fi;
                                        rotation = -180 + 30*fi;
                                        children.add(new RegionPart("-wing-heat"){{
                                            layerOffset = 0.1f;
                                            blending = Blending.additive;
                                            color = SFColor.enemyRedLight;
                                            outline = false;
                                        }});
                                    }});
                                }
                                weapons.add(new Weapon(name("knocker-weapon")){{
                                    reload = 60;
                                    x = 0;
                                    mirror = false;
                                    rotate = true;
                                    shake = 10;
                                    shootSound = Sounds.none;
                                    shootOnDeath = true;
                                    shootCone = 360;
                                    bullet = new BulletType(0,3000){{
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
                                                new WrapEffect(Fx.dynamicSpikes,SFColor.enemyRedLight,380),
                                                new WaveEffect(){{
                                                    interp = Interp.circleOut;
                                                    lifetime = 20;
                                                    sizeTo = 380;
                                                    strokeFrom = 22;
                                                    strokeTo = 10;
                                                    colorFrom = SFColor.enemyRedLight;
                                                    colorTo = SFColor.enemyRedLight.cpy().a(0);
                                                }},
                                                new ParticleEffect(){{
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
        blade = new UnitType("blade") {{
            constructor = UnitTypes.zenith.constructor;
            outlineColor = SFColor.darkOutline;
            flying = true;
            speed = 1.28f;
            rotateSpeed = 1.2f;
            accel = 0.052f;
            drag = 0.05f;
            engineLayer = 110;
            engineOffset = 24;
            engineSize = 9;
            setEnginesMirror(new UnitEngine(16,-39.25f,4,90));
            hitSize = 58;
            health = 300000;
            armor = 64;
            targetFlags = new BlockFlag[]{BlockFlag.unitAssembler, BlockFlag.turret};
            abilities.add(new RepairFieldAbility(530,330,150){{
                healEffect = Fx.none;
                activeEffect = new WaveEffect(){{
                    lifetime = 35;
                    sizeTo = 152;
                    strokeFrom = 16;
                    interp = Interp.circleOut;
                    colorFrom = colorTo = SFColor.enemyRedLight;
                }};
            }});
            abilities.add(new ForceFieldAbility(150,100,18000,900){{sides = 4;}});
            drawShields = false;
            faceTarget = false;
            BulletType bladePoint = new PointBulletType(){{
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
                trailEffect = new ParticleEffect(){{
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
                shootEffect = new ParticleEffect(){{
                    particles = 1;
                    sizeFrom = 7;
                    length = 0;
                    lifetime = 11;
                    colorFrom = colorTo = SFColor.energyYellow;
                }};
                hitEffect = new MultiEffect(
                        new ParticleEffect(){{
                            particles = 9;
                            sizeFrom = 9;
                            length = 20;
                            baseLength = 28;
                            lifetime = 25;
                            colorFrom = SFColor.energyYellow.cpy().a(0.4f);
                            colorTo = SFColor.energyYellow.cpy().a(0);
                        }},
                        new ParticleEffect(){{
                            particles = 9;
                            line = true;
                            lenFrom = 32;
                            strokeFrom = 2;
                            length = 50;
                            lifetime = 22;
                            colorFrom = SFColor.energyYellow;
                            colorTo = SFColor.energyYellow;
                        }},
                        new WaveEffect(){{
                            lifetime = 15;
                            sizeTo = 45;
                            strokeFrom = 5;
                            colorFrom = colorTo = SFColor.energyYellow;
                        }}
                );
            }};
            weapons.add(
                    new Weapon(name("red-point-gun")){{
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
                    new Weapon(name("red-point-gun")){{
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
        titan = new UnitType("titan"){{
            constructor = UnitTypes.reign.constructor;
            outlineColor = SFColor.darkOutline;
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
            abilities.add(new StatusFieldAbility(StatusEffects.overclock,360,300,160){{applyEffect = activeEffect = Fx.none;}});
            abilities.add(new ShieldRegenFieldAbility(360,12000,60,160){{
                activeEffect = new WaveEffect(){{
                    lifetime = 42;
                    interp = Interp.circleOut;
                    sizeTo = 160;
                    strokeFrom = 12;
                    colorFrom = colorTo = SFColor.energyYellow.cpy().a(0.45f);
                }};
            }});
            weapons.add(
                    new Weapon(name("titan-weapon")){{
                        reload = 28;
                        shoot = new ShootMulti(
                                new ShootHelix(){{scl=6;mag=6;shots=3;}},
                                new ShootHelix(){{scl=8;mag=3;shots=3;}}
                        );
                        inaccuracy = 15;
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
                        bullet = new BasicBulletType(23,104.6f){{
                            pierce = pierceBuilding = true;
                            pierceArmor = true;
                            pierceCap = 4;
                            knockback = 16;
                            lifetime = 17.3f;
                            width = 13;
                            height = 24;
                            despawnEffect = Fx.hitBulletBig;
                            hitEffect = new ParticleEffect(){{
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
                            shootEffect = new ParticleEffect(){{
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
                    new Weapon(name("red-cannon")){{
                        x = 93/4f;
                        y = -37/4f;
                        rotate = true;
                        rotateSpeed = 6.4f;
                        shoot = new ShootPattern(){{shots=3;shotDelay=6;}};
                        reload = 70;
                        alternate = false;
                        autoFindTarget = true;
                        autoTarget = true;
                        shootSound = Sounds.cannon;
                        shootY = 14;
                        bullet = new BasicBulletType(10,65, "missile-large"){{
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
                            hitEffect = new ExplosionEffect(){{
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
        vast = new UnitType("vast"){{
            constructor = UnitTypes.omura.constructor;
            outlineColor = SFColor.darkOutline;
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
            weapons.addAll(
                    new Weapon(name("vast-radar")) {{
                        reload = 2100;
                        x = 0;
                        shootCone = 360;
                        rotate = false;
                        minWarmup = 0.96f;
                        targetInterval = 120;
                        targetSwitchInterval = 60;
                        shootWarmupSpeed = 0.0075f;
                        shootStatus = SFStatusEffects.fullFire;
                        shootStatusDuration = 180;
                        shootSound = Sounds.lasercharge;
                        bullet = new BulletType(400,10){{
                            lifetime = 1;
                            instantDisappear = true;
                            shootEffect = new WaveEffect(){{
                                    interp = Interp.circleOut;
                                    lifetime = 120;
                                    sizeTo = 80;
                                    strokeFrom = 10;
                                    colorFrom = SFColor.disc;
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
                                    -3,6.5f,0,
                                    3,6.5f,0
                            };
                        }};
                        xRand = 2;
                        bullet = new FlakBulletType(13,50){{
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
                            height  = 46;
                            hitShake = 3;
                            sprite = "sfire-mod-missile2";
                            frontColor = SFColor.smoke;
                            backColor = SFColor.enemyRedLight;
                            trailLength = 40;
                            trailWidth = 2;
                            trailColor = Color.white.cpy().a(0.5f);
                            trailChance = 0.45f;
                            trailEffect = new ParticleEffect(){{
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
                            hitEffect = new ExplosionEffect(){{
                                waveLife = 10;
                                waveRad = 45;
                                waveStroke = 8;
                                waveColor = SFColor.enemyRedLight;
                                sparkRad = 56;
                                sparkStroke = 2.5f;
                                sparkLen = 13;
                                smokes = 10;
                                smokeSizeBase = 10;
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
                        shoot = new ShootBarrel(){{
                            shots = 2;
                            shotDelay = 12;
                            barrels = new float[]{
                                    2,12,0,
                                    2,8.5f,0,
                                    2,5,0,
                                    4.75f,1.25f,0,
                                    4.75f,6.75f,0,
                                    2.75f,0.5f,0,
                                    2.75f,-3.25f,0,
                                    5.5f,2.25f,0,
                                    5.5f,-1.5f,0,
                                    5.5f,-5,0
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
                        bullet = new MissileBulletType(7,75,"sfire-mod-missile1"){{
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
                            hitEffect = new ExplosionEffect(){{
                                lifetime =22;
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
                            trailEffect = new ParticleEffect(){{
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
                    new Weapon(name("vast-m")) {{
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
                        shoot = new ShootBarrel(){{
                            shots = 4;
                            shotDelay = 6;
                            barrels = new float[]{
                                    -2.75f,0.5f,0,
                                    -2.75f,-3.25f,0,
                                    -5.5f,2.25f,0,
                                    -5.5f,-1.5f,0,
                                    5.5f,-5,0,
                                    -2,12,0,
                                    -2,8.5f,0,
                                    -2,5,0,
                                    -4.75f,1.25f,0,
                                    -4.75f,6.75f,0
                            };
                        }};
                        bullet = new EmpBulletType(){{
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
                            shootEffect = new WaveEffect(){{
                                lifetime = 35;
                                interp = Interp.circleOut;
                                sizeTo = 10;
                                strokeFrom = 4;
                                colorFrom = SFColor.enemyRedLight;
                                colorTo = SFColor.enemyRedLight.cpy().a(0.55f);
                            }};
                            smokeEffect = Fx.none;
                            hitPowerEffect = new ParticleEffect(){{
                                particles = 5;
                                line = true;
                                length = 50;
                                lifetime = 16;
                                lenFrom = 16;
                                strokeFrom = 2;
                                colorFrom = colorTo = SFColor.enemyRedLight;
                                cone = 10;
                            }};
                            hitEffect = new WrapEffect(Fx.dynamicSpikes,SFColor.enemyRedLight,50);
                            hitSound = Sounds.laser;
                            hitSoundVolume = 0.5f;
                            despawnEffect = new ParticleEffect(){{
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
                    new PointDefenseWeapon(name("red-cannon")){{
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
            health = 2110;
            armor = 9;
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
                            damage = 95;
                            knockback = 20;
                            status = SFStatusEffects.scrambled;
                            statusDuration = 60;
                            width = 15;
                            length = 250;
                            sideAngle = 135;
                            sideWidth = 1.35f;
                            sideLength = 45;
                            smokeEffect = Fx.smokeCloud;
                            shootEffect = new ParticleEffect() {{
                                line = true;
                                particles = 9;
                                lenFrom = 10;
                                strokeFrom = 1.5f;
                                baseLength = 240;
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
                        parts.add(new RegionPart("-b") {{
                            x = -9;
                            y = -8;
                            rotation = -60;
                            mirror = false;
                            moveX = -x;
                            moveY = -y;
                            moveRot = -rotation;
                        }});
                        bullet = new MissileBulletType(9.6f, 36, "circle-bullet") {{
                            lifetime = 25;
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
            constructor = UnitTypes.dagger.constructor;;
            health = 11500;
            armor = 19;
            hitSize = 35;
            boostMultiplier = 1;
            engineSize = 10;
            engineOffset = 20;
            canBoost = true;
            speed = 0.48f;
            rotateSpeed = 2.35f;
            baseRotateSpeed = 2.3f;
            mechStepParticles = true;
            mechFrontSway = 0.98f;
            mechSideSway = 0.4f;
            abilities.add(new LiquidExplodeAbility(){{liquid=SFLiquids.nitratedOil;amount=300;radScale=2.5f;}});
            abilities.add(new ArmorPlateAbility(){{healFlash=false;healthMultiplier=1;}});
            weapons.add(
                    new Weapon(name("flamer-weapon")){{
                        mirror = true;
                        rotate = true;
                        rotateSpeed = 0.13f;
                        rotationLimit = 13;
                        x = 35;
                        shootY = 6;
                        top = false;
                        alwaysContinuous = true;
                        cooldownTime = 300;
                        alternate = false;
                        heatColor = Color.valueOf("FF4040");
                        bullet = new ContinuousFlameBulletType(60){{
                            recoil = 0.01f;
                            pierceBuilding = true;
                            colors = new Color[]{Color.valueOf("F33304A0"), Color.valueOf("F33304B8"), Color.valueOf("FEAF1FCC"), Color.valueOf("FCBE11"),  Color.valueOf("FEFF5F")};
                            lightColor = Color.valueOf("FCBE11");
                            flareColor = Color.valueOf("FEAF1F");
                            flareWidth = 5;
                            length = 125;
                            width = 5;
                            lifetime = 8;
                            knockback = 2.5f;
                            statusDuration = 600;
                            status = StatusEffects.burning;
                            damage = 30;
                            incendAmount = 1;
                            incendChance = 0.2f;
                            incendSpread = 16;
                            makeFire = true;
                            shootEffect = new ParticleEffect(){{
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
                            hitEffect = new ParticleEffect(){{
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
                    new Weapon(name("flamer-weapon-2")){{
                        mirror = false;
                        top = true;
                        x = 16;
                        y = 0;
                        rotate = true;
                        rotateSpeed = 8;
                        reload = 8.5f;
                        inaccuracy = 10;
                        recoil = 0;
                        shootSound = Sounds.missile;
                        velocityRnd = 0.1f;
                        shoot = new ShootPattern(){{
                            shotDelay = 1.15f;
                            shots = 3;
                        }};
                        bullet = new ArtilleryBulletType(10,35,"large-bomb"){{
                            height = 20;
                            width = 20;
                            frontColor = Pal.lightPyraFlame;
                            backColor = Pal.darkPyraFlame;
                            spin = 18;
                            drag = 0.017f;
                            splashDamage = 83;
                            splashDamageRadius = 26;
                            incendAmount = 1;
                            incendChance = 0.2f;
                            incendSpread = 16;
                            status = StatusEffects.burning;
                            statusDuration = 360;
                            absorbable = false;
                            hitSound = Sounds.explosion;
                            hitEffect = Fx.flakExplosionBig;
                            shootEffect = Fx.shootPyraFlame;
                            despawnEffect = Fx.none;
                        }};
                    }}
                    );
        }};
        thunder = new UnitType("thunder") {{
            constructor = UnitTypes.flare.constructor;
            flying = true;
            speed = 2;
            drag = 0.04f;
            accel = 0.05f;
            rotateSpeed = 5;
            hitSize = 30;
            health = 1800;
            armor = 20;
            lightRadius = 60;
            lightColor = SFColor.energyYellow;
            engineOffset = 14;
            engineSize = 4.5f;
            parts.add(new RegionPart("-side") {{
                mirror = false;
                moves.add(new PartMove(PartProgress.recoil, 0, -2, 0));
            }});
            weapons.add(
                    new Weapon(name("thunder-gun")){{
                        x = 0;
                        shootWarmupSpeed = 0.13f;
                        minWarmup = 0.9f;
                        reload = 48;
                        shootSound = Sounds.missile;
                        shootStatus = StatusEffects.shielded;
                        shootStatusDuration =50;
                        shoot = new ShootPattern(){{
                            shots = 3;
                            shotDelay = 3;
                            firstShotDelay = 14;
                        }};
                        mirror = false;
                        rotate = false;
                        shootY = 6;
                        bullet = new BasicBulletType(10,17){{
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
                            splashDamage = 65;
                            status = StatusEffects.shocked;
                            lightning = 3;
                            lightningDamage = 18;
                            lightningLength = 6;
                            lightningLengthRand = 4;
                            trailInterval = 1;
                            trailRotation = true;
                            trailEffect = new ParticleEffect(){{
                                line = true;
                                interp = Interp.pow10Out;
                                lenFrom = 10;
                                strokeFrom = 1.5f;
                                length = -45;
                                lifetime = 40;
                                colorFrom = colorTo = SFColor.energyYellow;
                                cone = 13;
                            }};
                            smokeEffect = new ParticleEffect(){{
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
                            hitEffect = new ExplosionEffect(){{
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
                    new Weapon(name("thunder-cannon")){{
                        x = 0;
                        shootY = 3;
                        shootWarmupSpeed = 0.1f;
                        minWarmup = 0.9f;
                        recoil = 0;
                        reload = 180;
                        rotate = false;
                        mirror = false;
                        shootSound = Sounds.laserblast;
                        bullet = new BasicBulletType(3,80,"large-bomb"){{
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
                                    new ParticleEffect(){{
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
        hammer = new UnitType("hammer") {{
            constructor = UnitTypes.atrax.constructor;
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
            weapons.add(new Weapon(name("hammer-weapon")){{
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
                bullet = new ArtilleryBulletType(5,95){{
                    collides = true;
                    collidesTiles = true;
                    absorbable = false;
                    splashDamage = 205;
                    splashDamageRadius = 45;
                    buildingDamageMultiplier = 2;
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
        omega = new UnitType("omega") {{
            constructor = UnitTypes.flare.constructor;
            outlineColor = SFColor.darkOutline;
            flying = true;
            rotateSpeed = 6;
            speed = 3.8f;
            accel = 0.08f;
            drag = 0.07f;
            hitSize = 38;
            buildRange = 288;
            buildBeamOffset = 16;
            buildSpeed = 3;
            health = 4800;
            armor = 12;
            engineSize = 5.5f;
            engineOffset = 16;
            setEnginesMirror(
                    new UnitEngine(-13,-15,2.5f,225),
                    new UnitEngine(-16,-9.5f,2,225));
            weapons.add(
                    new Weapon(name("omega-gun")){{
                        x = y = 0;
                        recoil = 0;
                        shootX = 5;
                        shootY = 22;
                        reload = 6;
                        rotate = false;
                        shootSound = Sounds.bolt;
                        bullet = new BasicBulletType(10,45){{
                            pierceArmor = true;
                            pierce = true;
                            pierceCap = 2;
                            buildingDamageMultiplier  = 0.3f;
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
                    new Weapon(name("omega-missile")){{
                    x = 14;
                    reload = 42;
                    rotate = false;
                    shoot = new ShootBarrel(){{
                        shots = 6;
                        shotDelay = 2;
                        barrels = new float[]{
                                0, 1, -30,
                                1, 0, -35,
                                2, -4, -40,
                                2, -4, -40,
                                1, 0, -35,
                                0, 1, -30
                        };}};
                        shootSound = Sounds.missile;
                        inaccuracy = 2;
                        shootCone = 180;
                        bullet = new MissileBulletType(11,33){{
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
            immunities = ObjectSet.with(StatusEffects.sapped,StatusEffects.slow,StatusEffects.unmoving,StatusEffects.sporeSlowed,StatusEffects.electrified,SFStatusEffects.acidded,SFStatusEffects.breakdown);
        }};

        utv = new TankUnitType("UTV") {{
            constructor= UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = true;
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
            accel = 0.08f;
            drag = 0.07f;
            itemCapacity = 2000;
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
                                lifetime = 60;
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
                        }}
            );
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.electrified, StatusEffects.sapped, SFStatusEffects.breakdown, SFStatusEffects.scrambled, SFStatusEffects.overLoad);
        }};
        utvA = new TankUnitType("UTV-Artillery") {{
            constructor= UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = false;
            healFlash = false;
            faceTarget = false;
            targetAir = false;

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
            accel = 0.08f;
            drag = 0.07f;
            lightRadius = 60;
            weapons.add(new Weapon(name("utv-2")) {{
                mirror = false;
                rotate = true;
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
                    lifetime = 90;
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
            constructor= UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = true;
            healFlash = false;
            faceTarget = false;

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
                                    colorFrom = SFColor.disc;
                                    colorTo = SFColor.energyYellow;
                                }},
                                new WaveEffect() {{
                                    startDelay = 40;
                                    lifetime = 60;
                                    sides = 6;
                                    sizeFrom = sizeTo = 59;
                                    strokeFrom = 4;
                                    interp = Interp.circleIn;
                                    colorFrom = SFColor.disc;
                                    colorTo = SFColor.energyYellow;
                                }},
                                new WaveEffect() {{
                                    startDelay = 80;
                                    lifetime = 60;
                                    sides = 6;
                                    sizeFrom = sizeTo = 88;
                                    strokeFrom = 5;
                                    interp = Interp.circleIn;
                                    colorFrom = SFColor.disc;
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
            accel = 0.08f;
            drag = 0.07f;
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
                                    colorTo = colorFrom = SFColor.disc;
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
            constructor= UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = true;
            healFlash = false;
            faceTarget = false;

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
            accel = 0.08f;
            drag = 0.07f;
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
                        }}
            );
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.electrified, StatusEffects.sapped, SFStatusEffects.breakdown, SFStatusEffects.scrambled, SFStatusEffects.overLoad);
        }};
        utvM = new TankUnitType("UTV-Missile") {{
            constructor= UnitTypes.stell.constructor;
            flying = false;
            outlineColor = SFColor.darkOutline;
            hovering = true;
            healFlash = false;
            faceTarget = false;
            targetGround = false;

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
            accel = 0.08f;
            drag = 0.07f;
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
        
        assemblerDrone = new UnitType("assembler-drone"){{
            constructor = UnitTypes.assemblyDrone.constructor;
            outlineColor = SFColor.darkOutline;
            flying = true;
            controller = u -> new AssemblerAI();
            speed = 0.95f;
            accel = 0.09f;
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
        assemblerDrone2 = new UnitType("assembler-drone-large"){{
            constructor = UnitTypes.assemblyDrone.constructor;
            outlineColor = SFColor.darkOutline;
            flying = true;
            controller = u -> new AssemblerAI();
            speed = 1.15f;
            accel = 0.09f;
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
            abilities.add(new RepairFieldAbility(180,60,80){{
                activeEffect = new WaveEffect(){{
                    lifetime = 248;
                    sizeTo = 80;
                    strokeFrom = 3;
                    colorFrom = Pal.heal;
                    colorTo = Pal.heal.cpy().a(0.5f);
                }};
            }});
            parts.add(new ShapePart(){{
                        stroke = strokeTo = 1.5f;
                        circle = true;
                        hollow = true;
                        radius = radiusTo = 80;
                        color = Pal.heal.cpy().a(0.4f);
                        colorTo = Pal.heal.cpy();
                    }});
            weapons.add(new RepairBeamWeapon(name("assembler-weapon")){{
                x = 0;
                y = 1.75f;
                rotateSpeed = 4.5f;
                beamWidth = 0.6f;
                repairSpeed = 1.5f;
                bullet = new BulletType(){{maxRange = 81;}};
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
            setEnginesMirror(new UnitEngine(-4,-10,2.5f,315));
            immunities = ObjectSet.with(StatusEffects.corroded, StatusEffects.sporeSlowed, SFStatusEffects.magnStrif, SFStatusEffects.marked);
            abilities.add(new ForceFieldAbility(20,8,2990,480));
            abilities.add(new RegenAbility(){{percentAmount=0.075f;}});
            singleTarget = true;
            weapons.add(
                    new Weapon(name("silence")){{
                        x = 0;
                        reload = 4;
                        mirror = false;
                        top = false;
                        rotate = false;
                        shootCone = 11;
                        shootSound = Sounds.blaster;
                        shoot = new ShootBarrel(){{
                            shots = 2;
                            barrels = new float[]{
                                    7.75f,12,0,
                                    -7.75f,12,0,
                                    10.75f,10.5f,0,
                                    -10.75f,10.5f,0
                            };
                        }};
                        shootWarmupSpeed = 0.1f;
                        minWarmup = 0.8f;
                        shake = 0.1f;
                        recoils = 4;
                        recoilTime = 20;
                        recoil = 0;
                        for(int i = 1; i <= 2; i++){
                            int fi = i;
                            parts.add(new RegionPart("-1"){{
                                recoilIndex = fi-1;
                                mirror = true;
                                layerOffset = -0.001f;
                                moveY = 9.5f;
                                x = 8;
                                moves.add(new PartMove(PartProgress.recoil,0,-2,0));
                            }});
                        };
                        for(int i = 1; i <= 2; i++){
                            int fi = i;
                            parts.add(new RegionPart("-2"){{
                                recoilIndex = fi+1;
                                mirror = true;
                                layerOffset = -0.001f;
                                progress = PartProgress.warmup;
                                moveX = -3;
                                moveY = 8;
                                x = -8;
                                moves.add(new PartMove(PartProgress.recoil,0,-2,0));
                            }});
                        };
                        bullet = new RailBulletType(){{
                            damage = 32;
                            status = SFStatusEffects.breakdown;
                            statusDuration = 10;
                            length = 232;
                            pierceDamageFactor = 0.5f;
                            pierce = false;
                            pierceEffect = Fx.none;
                            pointEffectSpace = 500;
                            pointEffect = new ParticleEffect(){{
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
                                    new ParticleEffect(){{
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
