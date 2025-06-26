package SFire.expand.blocks;

import mindustry.world.blocks.defense.turrets.*;

public class SFAccelTurret extends PowerTurret {

    public SFAccelTurret(String name) {
        super(name);
    }

    //开火时每秒加速50%，最大300%
    //也就是reload从1缩减到1/3
    public float accelMount = 0.5f;
    public float maxBoost = 3.0f;

    public float currentSpeedIncrease = 0f;
    public float timer = 0f;

    public void update() {
        //在开火
        if ("shooting找不到了") {
            //每秒加速
            timer += 1;
            if (timer >= 1f) {
                timer = 0f;
                currentSpeedIncrease += accelMount;
                if (currentSpeedIncrease > maxBoost) {
                    currentSpeedIncrease = maxBoost;
                }
            }
        } else {
            //没有开火就扣攻速
            if (currentSpeedIncrease > 0f) {
                currentSpeedIncrease -= accelMount * delta();
                if (currentSpeedIncrease < 0f) {
                    currentSpeedIncrease = 0f;
                }
            }
        }

        float multiplier = 1f + currentSpeedIncrease;
        //射速 =
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add();
    }

    @Override
    public void load() {
        super.load();
    }
    /*
    @Override
    public void setBars() {
        super.setBars();
        addBar("acceling",
                (SFAccelTurretBuild entity) -> new Bar(
                        () -> Core.bundle.format("sfire-", Strings.autoFixed((entity.maxBoost) * 100, 0)),
                        () -> Pal.accent,
                        () -> entity.boost
                )
        );
    }

    public class SFAccelTurretBuild extends PowerTurret.PowerTurretBuild {

        public float maxBoost = 0f;
        public float boost = 0f;

        @Override
        protected void shoot(BulletType type) {
            super.shoot(type);

            slowDownReload = slowDownReloadTime;
            if (speedupScl < maxSpeedupScl) {
                speedupScl += speedupPerShoot;
            }
            else speedupScl = maxSpeedupScl;
        }*/

}