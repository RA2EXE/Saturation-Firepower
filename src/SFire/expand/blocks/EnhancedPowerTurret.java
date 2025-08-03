package SFire.expand.blocks;

import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.Item;
import mindustry.ui.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.meta.*;


//来自@苏泽
public class EnhancedPowerTurret extends Turret{

    public @Nullable Item enhancerItem;//强化物品
    public @Nullable BulletType enhancedBullet;//强化子弹
    //public ObjectMap<Item, BulletType> ammoTypes = new OrderedMap<>();

    public ShootPattern enhancedPattern;//强化后射击方式
    public int maxEnhanced = maxAmmo;
    public BulletType shootType;

    public EnhancedPowerTurret(String name){
        super(name);
        hasPower = true;
    }

    /*public void enhance(Item enhancer, BulletType bullet, ShootPattern pattern){
        this.enhancerItem = enhancer;
        this.enhancedBullet = bullet;
        this.enhancedPattern = pattern;
    }*/

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(this, shootType)));
        stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(enhancerItem, enhancedBullet)));
        stats.add(Stat.ammoCapacity, (float) maxEnhanced / ammoPerShot, StatUnit.shots);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("ammo", (PowerTurretBuild entity) ->
        new Bar(
        "stat.ammo", Pal.ammo,
        () -> (float)entity.enhancedAmmo / maxEnhanced));
    }

    public class PowerTurretBuild extends TurretBuild{
        public int enhancedAmmo;
        
        @Override
        public void handleItem(Building source, Item item){
            if(item == enhancerItem && enhancedAmmo < maxEnhanced){
                enhancedAmmo += (int)enhancedBullet.ammoMultiplier;
            }
        }

        @Override
        public BulletType useAmmo(){
            if(enhancedAmmo > 0){
                enhancedAmmo -= ammoPerShot;
                return enhancedBullet != null ? enhancedBullet : shootType;
            }
            return shootType;
        }

        @Override
        public boolean hasAmmo(){
            return enhancedAmmo > 0 || power.status > 0.99f;
        }

        @Override
        public BulletType peekAmmo(){
            return enhancedAmmo > 0 && enhancedBullet != null ? 
                enhancedBullet : shootType;
        }

        @Override
        public int acceptStack(Item item, int amount, Teamc source){
            return item == enhancerItem ? 
                Math.min((maxEnhanced - enhancedAmmo) / (int)enhancedBullet.ammoMultiplier, amount) : 0;
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return item == enhancerItem && enhancedAmmo < maxEnhanced;
        }

        public boolean hasEnhancedAmmo(){
            return enhancedAmmo >= ammoPerShot;
        }

        @Override
        protected void shoot(BulletType type){
            float
            bulletX = x + Angles.trnsx(rotation - 90, shootX, shootY),
            bulletY = y + Angles.trnsy(rotation - 90, shootX, shootY);

            BulletType finalType;

            if(hasEnhancedAmmo()){
                finalType = enhancedBullet;
            }else {
                finalType = type;
            }


            ShootPattern enPatt = enhancedPattern != null ? enhancedPattern : shoot;
            ShootPattern pattern = enhancedAmmo > 0 ? enPatt : shoot;

            if(pattern.firstShotDelay > 0){
                chargeSound.at(bulletX, bulletY, Mathf.random(soundPitchMin, soundPitchMax));
                pattern.shoot(0, (xOffset, yOffset, angle, delay, mover) -> {
                    type.chargeEffect.at(
                            x + Angles.trnsx(rotation -90, shootX +xOffset, shootY +yOffset),
                            y + Angles.trnsy(rotation -90, shootX +xOffset, shootY +yOffset),
                            rotation
                    );
                }, () -> {});
                //finalType.chargeEffect.at(bulletX, bulletY, rotation);
            }

            pattern.shoot(barrelCounter, (xOffset, yOffset, angle, delay, mover) -> {
                queuedBullets++;
                if(delay > 0f){
                    Time.run(delay, () -> bullet(finalType, xOffset, yOffset, angle, mover));
                }else{
                    bullet(finalType, xOffset, yOffset, angle, mover);
                }
            }, () -> barrelCounter++);

            if(consumeAmmoOnce){
                useAmmo();
            }
        }
    }
}