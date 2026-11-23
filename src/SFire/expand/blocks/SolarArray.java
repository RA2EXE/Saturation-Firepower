package SFire.expand.blocks;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.*;
import arc.struct.EnumSet;
import arc.util.Nullable;
import arc.util.Strings;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Tile;
import mindustry.world.blocks.*;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.meta.*;

public class SolarArray extends SolarGenerator {
    //相邻光伏板强化发电倍率
    public float nearbyEnchant = 1.5f / 8f;
    public float maxBoost = 10;

    public final int timerUse = timers++;
    public boolean autotile = true;
    public int autotileMidVariants = 1;

    //protected TextureRegion[][][] tilingRegions;
    protected TextureRegion[] autotileRegions, autotileMidRegions;
    //protected TextureRegion[][] autotileVariantRegions;

    public SolarArray(String name) {
        super(name);
        flags = EnumSet.of(BlockFlag.battery);
        consumePowerBuffered(1000f);
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(new Stat("nearbyEnchant", StatCat.power), "+" + (int) (nearbyEnchant * 100) + "%");
    }

    @Override
    public void setBars() {
        super.setBars();
        addBar("boost", (SolarArrayBuild e) -> new Bar(
                () -> Core.bundle.format("bar.boost", Strings.fixed((e.nowEnchant - 1) * 100, 0)),
                () -> Pal.accent,
                () -> e.nowEnchant > 1 ? 1 : 0
        ));
    }

    @Override
    public void drawBase(Tile tile) {
        if (autotile) {
            int bits = 0;

            for (int i = 0; i < 8; i++) {
                Tile other = tile.nearby(Geometry.d8[i]);
                if (checkAutotileSame(tile, other)) {
                    bits |= (1 << i);
                }
            }

            int bit = TileBitmask.values[bits];

            TextureRegion region = bit == 13 && autotileMidVariants > 1 ? autotileMidRegions[variant(tile.x, tile.y, autotileMidRegions.length)] : autotileRegions[bit];

            Draw.rect(region, tile.worldx(), tile.worldy());
        } else {
            if (variants > 0) {
                Draw.rect(variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 1))], tile.worldx(), tile.worldy());
            } else {
                Draw.rect(region, tile.worldx(), tile.worldy());
            }
        }
    }
    public int variant(int x, int y, int max){
        return Mathf.randomSeed(Point2.pack(x, y), 0, Math.max(0, max - 1));
    }

    public boolean checkAutotileSame(Tile tile, @Nullable Tile other){
        return other != null && other.block() == this;
    }


    @Override
    public void load() {
        super.load();

        if (autotile) {
            autotileRegions = TileBitmask.load(name);

            if (autotileMidVariants > 1) {
                autotileMidRegions = new TextureRegion[autotileMidVariants];
                for (int i = 0; i < autotileMidVariants; i++) {
                    autotileMidRegions[i] = Core.atlas.find(i == 0 ? name + "-13" : name + "-mid-" + (i + 1));
                }
            }
        }
    }


    public class SolarArrayBuild extends SolarGeneratorBuild {
        public int neighbors;
        public float nowEnchant = 0f;

        @Override
        public Building init(Tile tile, Team team, boolean nearby, int rotation) {
            super.init(tile, team, nearby, rotation);
            refresh();
            return this;
        }

        @Override
        public void updateTile() {
            super.updateTile();

            //定期刷新
            if (timer(timerUse, 1)) {
                refresh();
            }

            productionEfficiency *= nowEnchant;
        }

        public void refresh() {
            neighbors = countNeighbors();
            nowEnchant = Math.min(1f + neighbors * nearbyEnchant, maxBoost);
        }

        public int countNeighbors() {
            if (Vars.world == null || tile == null) return 0;

            int size = block.size;
            int x0 = tileX(), y0 = tileY();
            int count = 0;

            for (int dx = -1; dx <= size; dx++) {
                for (int dy = -1; dy <= size; dy++) {
                    //跳过自己占的格子
                    if (dx >= 0 && dx < size && dy >= 0 && dy < size) continue;

                    Tile tile = Vars.world.tile(x0 + dx, y0 + dy);
                    if (tile == null || tile.build == null) continue;
                    if (tile.build.block != block) continue;
                    if (tile.build.team != team) continue;

                    count++;
                }
            }

            return count;
        }
    }

}
