package SFire.expand.bullets;

import arc.Events;
import arc.util.Tmp;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.game.EventType;
import mindustry.gen.*;

public class SizeDamageBullet extends BasicBulletType {

    //体积增伤倍率修正系数
    public float sizeDamageMul = 1f;
    //基础体积大小
    public float basicSize = 30f;
    //30内造成基础伤害，30以上造成 基础*体积多出30的倍*修正系数

    //是否要考虑同体积/大体积 的 过穿/击飞？

    public SizeDamageBullet(float speed, float damage, String bulletSprite){
        super(speed, damage);
        this.sprite = bulletSprite;
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){

        if(entity instanceof Healthc h){
            float damage = b.damage;
            float size = entity.hitSize();

            float finalDamage = size > basicSize ? damage * (size/basicSize) * sizeDamageMul : damage;

            if(pierceArmor){
                h.damagePierce(finalDamage);
            }else{
                h.damage(finalDamage);
            }
        }

        if(entity instanceof Unit unit){
            Tmp.v3.set(unit).sub(b).nor().scl(knockback * 80f);
            if(impact) Tmp.v3.setAngle(b.rotation() + (knockback < 0 ? 180f : 0f));
            unit.impulse(Tmp.v3);
            unit.apply(status, statusDuration);
        }

        handlePierce(b, health, entity.x(), entity.y());
    }
}
