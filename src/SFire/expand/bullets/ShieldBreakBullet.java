package SFire.expand.bullets;


import arc.util.Tmp;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.*;

public class ShieldBreakBullet extends BasicBulletType {
    //每次造成当前剩余盾量的%
    public float shieldDamagePercent = 0.02f;
    public Effect shbreakEffect = Fx.none;


    public ShieldBreakBullet(float speed, float damage, String bulletSprite){
        super(speed, damage);
        this.sprite = bulletSprite;
        hittable = false;
        reflectable = false;
        absorbable = false;
    }

    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){

        if(entity instanceof Healthc h){
            float damage = b.damage;
            float shield = entity instanceof Shieldc s ? Math.max(s.shield(), 0f) : 0f;

            if(shield*shieldDamagePercent > damage){
                /*shield -= shield*shieldDamagePercent;
                health += shield;*/
                h.damage(shield*shieldDamagePercent);
                shbreakEffect.at(entity.x(), entity.y());
                Effect.shake(hitShake, hitShake, b);
            }else{
                if(pierceArmor){
                    h.damagePierce(damage);
                }else{
                    h.damage(damage);
                }
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
