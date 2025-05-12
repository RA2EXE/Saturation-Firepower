package SFire.world.blocks;

import arc.func.Prov;
import mindustry.game.Team;
import mindustry.world.Block;
import mindustry.world.Build;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;

import static mindustry.Vars.*;

public class SFCore extends CoreBlock {
    public SFCore(String name) {
        super(name);
    }


    @Override
    public boolean canBreak(Tile tile){
        return state.teams.cores(tile.team()).size > 1;
    }

    @Override
    public boolean canReplace(Block other){
        return other.alwaysReplace;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        return state.teams.cores(team).size < 7;
    }


}



/*
超限爆炸未知是否有需保留
CoreFrontline.buildType = prov(() => {
	let kill = false, num = 1, time = 60 * num;
	return extend(CoreBlock.CoreBuild, CoreFrontline, {
		updateTile() {
			this.super$updateTile();
			if (Vars.state.teams.cores(this.team).size > 10) kill = true;
			if (kill) {
				if (!Vars.headless) {
					Vars.ui.showLabel("[red]     数据上行堵塞\n▲中央数据库过载▲\n     强制重启倒计时", 0.015, this.x, this.y);
				}
				time--
				if (time == 0) {
					this.kill();
				}
			}
		},
		draw() {
			this.super$draw();
			Draw.z(Layer.effect);
			Lines.stroke(2, Color.valueOf("FF5B5B"));
			Draw.alpha(kill ? 1 : Vars.state.teams.cores(this.team).size > 9 ? 1 : 0);
			Lines.arc(this.x, this.y, 16, time * (6 / num) / 360, 90);
		}
	})
});

"description": "在前线建立一个可拆除的先锋作战基地。\n在[yellow]同一个区块[red]中存在过多核心会导致中央数据库过载爆炸。\n[yellow]区块核心总承载上限：8",
	"research": {
		"root":true,
		"name": "泰伯利亚"
		},
	"requirements": [
		"copper/800",
		"lead/2000",
		"silicon/500"
	],
	"unitType": "alpha",
	"alwaysUnlocked":true,
	"armor":28,
	"health": 1000,
	"itemCapacity": 800,
	"size": 3,
	"unitCapModifier": 3,
	"category": "effect"

*/