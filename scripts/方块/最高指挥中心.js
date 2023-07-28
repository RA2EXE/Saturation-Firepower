const myitems = require("物品");

let range = 520; //俩圆半径，炮塔射程
const 怨灵Z = extend(ItemTurret, "怨灵Z", {});
const core = extend(CoreBlock, "最高指挥中心", {
	drawPlace(x, y, rotation, valid) {
		this.super$drawPlace(x, y, rotation, valid);
		Drawf.dashCircle(x * 8 + this.offset, y * 8 + this.offset, range, Pal.accent);
	}
});

core.buildType = prov(() => {
	const p = new BuildPayload(怨灵Z, Team.derelict); //这里写炮塔
	return extend(CoreBlock.CoreBuild, core, {
		updateTile() {
			this.super$updateTile();
			if (p.build.team != this.team) {
				p.build.team = this.team;
			}
			p.update(null, this);
			if (p.build.acceptItem(this, myitems.裂位能) && this.team.core().items.get(myitems.裂位能) >= 1) {
				p.build.handleItem(this, myitems.裂位能);
				this.team.core().items.remove(myitems.裂位能, 1);
			}
			p.set(this.x, this.y, p.build.payloadRotation);
		},
		draw() {
			this.super$draw();
				p.draw();
		},
		drawSelect() {
			this.super$drawSelect();
			Drawf.dashCircle(this.x, this.y, range, Pal.accent); //点击时显示的虚线圆
		},
		handleDamage(amount) {
			amount = Math.min(amount * 10, amount + this.block.armor);
			return Damage.applyArmor(amount / 1.055, this.block.armor); 
		}
	})
});