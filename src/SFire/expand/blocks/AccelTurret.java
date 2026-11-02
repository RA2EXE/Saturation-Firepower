package SFire.expand.blocks;

import arc.Core;
import arc.math.Mathf;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.consumers.ConsumeLiquidFilter;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

import static mindustry.Vars.tilesize;

public class AccelTurret extends PowerTurret {

    public AccelTurret(String name) {
        super(name);
    }

    //开火时每秒加速50%，最大300%
    public float accelMount = 0.5f;
    public float maxBoost = 3.0f;
    //掉火速度，0不掉，1:100%每秒
    public float downMount = 1f;
    //线性加速和掉速，非线性停火直接掉完
    public boolean linerAccel = true;

    @Override
    public void setStats(){
        super.setStats();
        stats.add(new Stat("maxshootboost", StatCat.function), (maxBoost*100)+"%");
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("boost", (AccelTurretBuild e) -> new Bar(
                () -> Core.bundle.format("bar.boost",(int)((e.boost - 1f) * 100f)),
                () -> Pal.accent,
                () -> Mathf.clamp((e.boost - 1f) / Math.max(0.0001f, maxBoost - 1f))
        ));
    }

    public class AccelTurretBuild extends PowerTurretBuild {
        public float boost = 0f;
        //public float reloadCounter;

        @Override
        public void updateTile(){
            super.updateTile();
            updateBoost();
        }

        protected void updateBoost(){
            float seconds = delta() / 60f;

            if(isShooting && efficiency != 0){
                if(linerAccel){//线加速感觉跟现有方案差不多？？？留着吧
                    boost = Mathf.clamp(boost + accelMount * seconds, 1f, maxBoost);
                }else{//复利加速，麻麻滴高中数学还在追我/好像这个比线性有实战价值啊我草/专注轻机枪，还是这个爽
                    boost = Mathf.clamp(boost * (float)Math.pow(1f + accelMount, seconds), 1f, maxBoost);
                }
            }else if(downMount >0){
                boost = linerAccel ? Mathf.clamp(boost - downMount * seconds, 1f, maxBoost) : 1;
            }
        }

        @Override
        protected void updateReload(){
            reloadCounter += delta() * ammoReloadMultiplier() * baseReloadSpeed() * boost;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(boost);
        }
        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            if(revision >= 2){
                boost = read.f();
            }
        }
        @Override
        public byte version(){return 2;}
    }


}