const 前哨基地 = extend(CoreBlock, "前哨基地", {
	canBreak(tile) {
		return Vars.state.teams.cores(tile.team()).size > 1;
	},
	canReplace(other) {
		return other.alwaysReplace;
	},
	canPlaceOn(tile, team, rotation) {
		return Vars.state.teams.cores(team).size < 10;
	}
});

前哨基地.buildType = prov(() => {
	let kill = false, num = 1.5, time = 60 * num;
	return extend(CoreBlock.CoreBuild, 前哨基地, {
		updateTile() {
			this.super$updateTile();
			if (Vars.state.teams.cores(this.team).size > 9) kill = true;
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
			Lines.stroke(2, Color.valueOf("FF5B5BFF"));
			Draw.alpha(kill ? 1 : Vars.state.teams.cores(this.team).size > 8 ? 1 : 0);
			Lines.arc(this.x, this.y, 16, time * (6 / num) / 360, 90);
		}
	})
});