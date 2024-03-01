//By 无量级萌新重构工厂/fqyNOPE
//让工厂支持逻辑控制是否输出
//shootX为是否输出物品
//shootY为是否输出液体
//使用：将json方块中的Type去掉,并将其放在list中.

const list = ["孢子冲压机","爆破冲压炉","大冷冻","大硫","大型硅厂","大炸混","飞轮离心机","硅钢冶炼炉","电磁合金冶炼炉","镄合成器","高压粉碎机","铬合成器","归中编织器","硅钢冶炼厂","激发放射塔","集束捆合器","激活器","莱普合金厂","塑钢厂","温压冶炼炉","泰勒厂","纳米打印机","熔炼炉","硝化反应器","硅钢高炉","激发器","纳米组装机"];//在这里填写支持逻辑控制输出的GenericCrafter工厂
const Alist = ["热能硅钢高炉","细胞培养机"];//在这里填写支持逻辑控制输出的AttributeCrafter工厂
const Slist = ["重油沉淀池","沙分离机"];//在这里填写支持逻辑控制输出的Separator工厂
const blocks = [];
const Ablocks = [];
const Sblocks = [];
for(var i = 0;i<list.length;i++){
	const block = extend(GenericCrafter, list[i], {

	});
	block.buildType = prov(() => {
		let outputi = true;
		let outputl = true;
		return extend(GenericCrafter.GenericCrafterBuild, block, {
		
			control(type, p1, p2, p3, p4) {
				if (type == LAccess.shoot) {
					outputi = !Mathf.zero(p1);
					outputl = !Mathf.zero(p2);
				}
				this.super$control(type, p1, p2, p3, p4);
			},
	
		canDump(to, item) {
	
				return outputi;
			},
	
		canDumpLiquid(to, liquid) {
	
				return outputl;
			}
		});
	});
}

for(var i = 0;i<Alist.length;i++){
	const block = extend(AttributeCrafter, Alist[i], {

	});
	block.buildType = prov(() => {
		let outputi = true;
		let outputl = true;
		return extend(AttributeCrafter.AttributeCrafterBuild, block, {
		
			control(type, p1, p2, p3, p4) {
				if (type == LAccess.shoot) {
					outputi = !Mathf.zero(p1);
					outputl = !Mathf.zero(p2);
				}
				this.super$control(type, p1, p2, p3, p4);
			},
	
			canDump(to, item) {
	
				return outputi;
			},
	
			canDumpLiquid(to, liquid) {
	
				return outputl;
			}
		});
	});
}

for(var i = 0;i<Slist.length;i++){
	const block = extend(Separator, Slist[i], {

	});
	block.buildType = prov(() => {
		let outputi = true;
		let outputl = true;
		return extend(Separator.SeparatorBuild, block, {
		
			control(type, p1, p2, p3, p4) {
				if (type == LAccess.shoot) {
					outputi = !Mathf.zero(p1);
					outputl = !Mathf.zero(p2);
				}
				this.super$control(type, p1, p2, p3, p4);
			},
	
		canDump(to, item) {
	
				return outputi;
			},
	
		canDumpLiquid(to, liquid) {
	
				return outputl;
			}
		});
	});
}

	
		
