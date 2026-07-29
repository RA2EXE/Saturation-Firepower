package SFire.expand.blocks;


import SFire.content.SFItems;
import arc.Core;
import arc.math.Mathf;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.*;



public class MemoryWall extends Wall {
    //每次受击提高的护甲，可负        -1不封顶，0封底
    public float hitArmorUp = 1, maxArmor = 10;

    //脱战后回血预热时间
    public float regenDelay = 60f * 2;
    //帧回血或者百分比回血
    public float regenAmount = 0f, regenPercent = 0f;

    public MemoryWall(String name) {
        super(name);
        update = true;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(new Stat("wallmaxarmor", StatCat.function), maxArmor);
        stats.add(new Stat("regendelay", StatCat.function), (int)(regenDelay/60f), StatUnit.seconds);

        if (regenPercent>0){
            stats.add(Stat.repairTime, (int)(1f / (regenPercent /100f) /60f), StatUnit.seconds);
        }
        if (regenAmount>0){
            stats.add(Stat.repairTime, (int)(health /regenAmount /60f), StatUnit.seconds);
        }
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("healdelay", (MemoryWallBuild e) -> new Bar(
                () -> Core.bundle.format("bar.healdelay", Strings.fixed(regenDelay/60f,0)),
                () -> e.charge>0? Pal.heal : Pal.health,
                //() -> e.charge>0? e.charge/regenDelay : 1
                () -> e.charge>0? (regenDelay - e.charge) / regenDelay : 1
        ));
        addBar("armor", (MemoryWallBuild e) -> new Bar(
                () -> Core.bundle.format("bar.armorup", Strings.fixed(armor,0)),
                () -> SFItems.memoryAlloy.color,
                () -> (armor-e.basicArmor) / (maxArmor - e.basicArmor)
        ));
    }

    public class MemoryWallBuild extends WallBuild {
        public float charge = 0f;
        public float basicArmor = armor;

        @Override
        public void updateTile() {
            super.updateTile();

            if (health() < maxHealth() && !wasRecentlyDamaged()) {
                charge += Time.delta;

                if(charge >= regenDelay){
                    //直接回溯满血有点暴力了，还是换成量吧
                    heal((this.maxHealth * regenPercent / 100f + regenAmount) * Time.delta);
                    armor = 0;
                }
            } else {
                charge = 0;
            }

        }

        @Override
        public boolean collision(Bullet bullet) {
            super.collision(bullet);

            //wasHit = (health() < maxHealth()) ? true : false;
            //charge = 0;
        //    if(maxArmor !=0){
        //        //armor = armor > maxArmor ? armor+hitArmorUp : maxArmor;草超出上限了
        //        armor = Math.min(maxArmor, armor + hitArmorUp);
        //        //wasHit = true;
        //    } else {//还能扣到负数的？？？
        //        armor = Math.max(maxArmor, armor + hitArmorUp);
        //        //wasHit = true;
        //    }

            if (maxArmor!=0) {
                if (hitArmorUp > 0) {
                    armor = Math.min(maxArmor, armor + hitArmorUp);
                    //wasHit = true;
                } else {
                    armor = Math.max(maxArmor, armor + hitArmorUp);
                }
            }
            return true;
        }
    }
}
