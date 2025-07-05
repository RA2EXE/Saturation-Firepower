package SFire.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
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
import mindustry.world.draw.DrawTurret;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static mindustry.type.ItemStack.with;

public class SFOverride {
    public static void load() {

        //region turrets
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
        ((ItemTurret) Blocks.salvo).shoot.shots = 6;
        ((ItemTurret) Blocks.salvo).shoot.shotDelay = 2;
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

        ((PowerTurret) Blocks.lancer).range = 226;
        ((PowerTurret) Blocks.lancer).reload = 100;
        ((PowerTurret) Blocks.lancer).shootType = new LaserBulletType(200) {{
            length = 226;
            width = 18;
            ammoMultiplier = 1;
            buildingDamageMultiplier = 0.35f;
            colors = new Color[]{Pal.lancerLaser.cpy().a(0.4f), Pal.lancerLaser, Color.white};
            lightningColor = Pal.lancerLaser;
            lightningSpacing = 25;
            lightningDelay = 0.6f;
            lightningLength = 4;
            lightningLengthRand = 2;
            lightningAngleRand = 16;
            lightningDamage = 15;
            collidesAir = false;

            chargeEffect = new MultiEffect(Fx.lancerLaserCharge, Fx.lancerLaserChargeBegin);
            hitEffect = Fx.hitLancer;
            hitSize = 4;
            shootEffect = Fx.lancerLaserShoot;
            smokeEffect = Fx.lancerLaserShootSmoke;
            drawSize = 400f;
        }};
        ((PowerTurret) Blocks.lancer).drawer = new DrawTurret() {{
            parts.add(new RegionPart("-barrel") {{
                progress = PartProgress.recoil;
                moveY = -1;
            }});
        }};
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
        ((ItemTurret) Blocks.cyclone).range = 228;
        ((ItemTurret) Blocks.cyclone).ammoTypes.get(Items.metaglass).lifetime = 56;
        ((ItemTurret) Blocks.cyclone).ammoTypes.get(Items.blastCompound).lifetime = 57;
        ((ItemTurret) Blocks.cyclone).ammoTypes.get(Items.plastanium).lifetime = 57;
        ((ItemTurret) Blocks.cyclone).ammoTypes.get(Items.surgeAlloy).speed = 4.5f;
        ((ItemTurret) Blocks.cyclone).ammoTypes.get(Items.surgeAlloy).lifetime = 52;
        ((ItemTurret) Blocks.cyclone).ammoTypes.putAll(
                SFItems.siliSteel, new FlakBulletType(4f, 8){{
                    shootEffect = Fx.shootBig;
                    ammoMultiplier = 3f;
                    splashDamage = 23f;
                    splashDamageRadius = 28f;
                    collidesGround = true;

                    status = SFStatusEffects.magnStrif;
                    statusDuration = 30f;

                    backColor = hitColor = trailColor = SFColor.sisteelDark;
                    frontColor = SFColor.sisteelLight;
                    despawnEffect = Fx.hitBulletColor;
                }},
                SFItems.clusBomb, new FlakBulletType(4f, 8){{
                    shootEffect = Fx.shootBig;
                    ammoMultiplier = 6f;
                    splashDamage = 35f;
                    splashDamageRadius = 25f;
                    collidesGround = true;
                    status = StatusEffects.blasted;

                    backColor = hitColor = trailColor = SFColor.clusRedDark;
                    frontColor = SFColor.clusRed;
                    despawnEffect = Fx.hitBulletColor;
                    fragBullets = 4;
                    fragLifeMin = 0.1f;
                    fragBullet = new FlakBulletType(2.5f, 8) {{
                        lifetime = 20;
                        drag = 0.005f;
                        collidesGround = true;
                        splashDamage = 35;
                        splashDamageRadius = 38;
                        scaledSplashDamage = true;
                        width = height = 8;
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
        Blocks.fuse.armor = 8;
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
            toColor = fromColor = SFColor.discLight.cpy().a(0.5f);
            shootEffect = smokeEffect = new Effect(12f, e -> {
                color(Color.white, SFColor.discLight, e.fin());
                stroke(e.fout() * 1.2f + 0.5f);

                randLenVectors(e.id, 7, 25f * e.finpow(), e.rotation, 50f, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
                });
            });
            reloadMultiplier = 0.9f;
            spawnBullets.add(new ShrapnelBulletType() {{
                length = 85;
                damage = 130;
                ammoMultiplier = 4f;
                toColor = fromColor = SFColor.discLight;
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

        ((PowerTurret) Blocks.meltdown).range = 250;
        ((PowerTurret) Blocks.meltdown).reload = 240;
        Blocks.meltdown.liquidCapacity = 120f;
        ((PowerTurret) Blocks.meltdown).shootType = new ContinuousLaserBulletType(86){{
            length = 250;
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
                length = 250;
                width = 16;
            }};
        }};

        ((ItemTurret) Blocks.foreshadow).ammoTypes.put(SFItems.tayrAlloy, new PointBulletType(){{
            damage = 1750;
            hitSound = Sounds.boom;
            speed = 100;
            hitShake = 5;
            buildingDamageMultiplier = 0.3f;
            trailSpacing = 20;
            trailEffect = new Effect(30, e -> {
                for(int i = 0; i < 2; i++){
                    color(i == 0 ? SFColor.tayrDark : SFColor.tayrLight);

                    float m = i == 0 ? 1f : 0.5f;

                    float rot = e.rotation + 180f;
                    float w = 15f * e.fout() * m;
                    Drawf.tri(e.x, e.y, w, (30f + Mathf.randomSeedRange(e.id, 15f)) * m, rot);
                    Drawf.tri(e.x, e.y, w, 10f * m, rot + 180f);
                }

                Drawf.light(e.x, e.y, 60f, SFColor.tayrDark, 0.6f * e.fout());
            });
            shootEffect = new Effect(24f, e -> {
                e.scaled(10f, b -> {
                    color(Color.white, SFColor.tayrDark, b.fin());
                    stroke(b.fout() * 3f + 0.2f);
                    Lines.circle(b.x, b.y, b.fin() * 50f);
                });

                color(SFColor.tayrDark);

                for(int i : Mathf.signs){
                    Drawf.tri(e.x, e.y, 13f * e.fout(), 85f, e.rotation + 90f * i);
                    Drawf.tri(e.x, e.y, 13f * e.fout(), 50f, e.rotation + 20f * i);
                }

                Drawf.light(e.x, e.y, 180f, SFColor.tayrDark, 0.9f * e.fout());
            });
            smokeEffect = Fx.smokeCloud;
            hitEffect =  new Effect(20f, 200f, e -> {
                color(SFColor.tayrDark);

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

                e.scaled(10f, c -> {
                    color(SFColor.tayrLight);
                    stroke(c.fout() * 2f + 0.2f);
                    Lines.circle(e.x, e.y, c.fin() * 30f);
                });

                e.scaled(12f, c -> {
                    color(SFColor.tayrDark);
                    randLenVectors(e.id, 25, 5f + e.fin() * 80f, e.rotation, 60f, (x, y) -> {
                        Fill.square(e.x + x, e.y + y, c.fout() * 3f, 45f);
                    });
                });
            });
            despawnEffect = new Effect(15f, 100f, e -> {
                color(SFColor.tayrDark);
                stroke(e.fout() * 4f);
                Lines.circle(e.x, e.y, 4f + e.finpow() * 20f);

                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 6f, 80f * e.fout(), i*90 + 45);
                }

                color();
                for(int i = 0; i < 4; i++){
                    Drawf.tri(e.x, e.y, 3f, 30f * e.fout(), i*90 + 45);
                }

                Drawf.light(e.x, e.y, 150f, SFColor.tayrDark, 0.9f * e.fout());
            });
        }});
        //endregion
        //region drill + production
        ((Drill) Blocks.laserDrill).drillTime = 252;
        ((Drill) Blocks.laserDrill).updateEffect = Fx.hitLancer;
        ((Drill) Blocks.blastDrill).drillTime = 252;
        ((Drill) Blocks.blastDrill).updateEffect = Fx.explosion;
        ((Drill) Blocks.blastDrill).hardnessDrillMultiplier = 40;
        Blocks.blastDrill.requirements(Category.production, ItemStack.with(Items.copper, 120, Items.titanium, 60, Items.silicon, 80, Items.thorium, 90));
        ((Pump) Blocks.impulsePump).pumpAmount = 1/3f;
        Blocks.impulsePump.consumePower(1.5f);

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
        //endregion
        Blocks.armoredConveyor.placeableLiquid = true;
        //region power
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
        Blocks.impactReactor.requirements(Category.power, with(Items.lead,500, SFItems.siliSteel,250, Items.graphite,400, Items.thorium,200, Items.surgeAlloy,300, Items.metaglass,350));
        //endregion


        //cores
        Blocks.coreShard.health = 2000;
        Blocks.coreShard.armor = 3;
        Blocks.coreFoundation.health = 5000;
        Blocks.coreFoundation.armor = 8;
        Blocks.coreNucleus.health = 8000;
        Blocks.coreNucleus.armor = 12;

        //region status
        StatusEffects.boss.damageMultiplier = 1.5f;
        StatusEffects.boss.healthMultiplier = 2f;

        StatusEffects.fast.effectChance = 0.05f;
        StatusEffects.fast.effect = new MultiEffect(new WrapEffect(){{effect=Fx.colorSparkBig;color=Color.orange;rotation=45;}},
                                                    new WrapEffect(){{effect=Fx.colorSparkBig;color=Color.orange;rotation=-135;}});
        StatusEffects.slow.effectChance = 0.05f;
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
            region = "sfire-mod-loz";
            sizeInterp = Interp.pow3;
            sizeFrom = 1;
            sizeTo = 5;
            colorFrom = Color.valueOf("A278E1");
            colorTo = Color.valueOf("A278E100");
        }});
        //endregion
        //region units
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
        //endregion


    }
}
