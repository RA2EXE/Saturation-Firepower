package SFire.expand.bullets;

import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.Bullet;

public class PowerupBulletType extends BasicBulletType {

    //最高伤害倍率
    public float maxDamageMultiplier = 5f;
    //每次穿透后伤害提升倍率
    public float damageUp = 1f;

    public PowerupBulletType(float speed, float damage, String bulletSprite){
        super(speed, damage);
        this.sprite = bulletSprite;

        pierce = true;
        reflectable = false;
    }

    @Override
    //获取子弹，目标生命值，坐标
    public void handlePierce(Bullet b, float initialHealth, float x, float y){
        //在目标生命*伤害衰减和0之间取大值
        //float sub = Math.max(initialHealth * pierceDamageFactor, 0);

        //穿透后伤害*伤害提升倍数，最大伤害倍数  取小的一个
        b.damage = Math.min(b.damage + damage * damageUp, damage * maxDamageMultiplier);
        createSplashDamage(b, x, y);

        //默认的结束穿透
        if(removeAfterPierce && b.damage <= 0){
            b.hit = true;
            b.remove();
        }
    }
}
