package SFire.expand.blocks;

import arc.Core;
import arc.struct.EnumSet;
import arc.util.Strings;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Tile;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

public class SolarArray extends SolarGenerator {
    //相邻光伏板强化发电倍率
    public float nearbyEnchant = 0.5f;
    public float maxBoost = 10;
    public final int timerUse = timers++;

    public SolarArray(String name){
        super(name);
        flags = EnumSet.of(BlockFlag.generator);
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(new Stat("nearbyEnchant", StatCat.power), "+"+(int)(nearbyEnchant*100)+"%");
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("boost",(SolarArrayBuild e) -> new Bar(
                () -> Core.bundle.format("bar.boost", Strings.fixed((e.nowEnchant-1)*100,0)),
                () -> Pal.accent,
                () -> e.nowEnchant>1? 1:0
        ));
    }

    public class SolarArrayBuild extends SolarGeneratorBuild{
        public int neighbors;
        public float nowEnchant = 0f;

        @Override
        public Building init(Tile tile, Team team, boolean nearby, int rotation){
            super.init(tile, team, nearby, rotation);
            refresh();
            return this;//996还得做防溢
        }

        @Override
        public void updateTile(){
            super.updateTile();

            //定期刷新
            if(timer(timerUse, 1)){
                refresh();
            }

            productionEfficiency *= nowEnchant;
        }

        public void refresh(){
            neighbors = countNeighbors();
            nowEnchant = Math.min(1f + neighbors * nearbyEnchant, maxBoost);
        }

        public int countNeighbors(){
            if(Vars.world == null || tile == null) return 0;

            int size = block.size;
            int x0 = tileX(), y0 = tileY();
            int count = 0;

            for(int dx = -1; dx <= size; dx++){
                for(int dy = -1; dy <= size; dy++){
                    //跳过自己占的格子
                    if(dx >= 0 && dx < size && dy >= 0 && dy < size) continue;

                    Tile tile = Vars.world.tile(x0 + dx, y0 + dy);
                    if(tile == null || tile.build == null) continue;
                    if(tile.build.block != block) continue;
                    if(tile.build.team != team) continue;

                    count++;
                }
            }

            return count;
        }
    }

}
