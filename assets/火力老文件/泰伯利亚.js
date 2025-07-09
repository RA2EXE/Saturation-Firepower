const SFlib = require("base/SFlib");
const TBLY = new Planet("泰伯利亚", Planets.sun, 1, 3.3);
TBLY.meshLoader = prov(() => new MultiMesh(
	new HexMesh(TBLY, 8)
));
TBLY.generator = extend(SerpuloPlanetGenerator, {
	getDefaultLoadout() {
		return Schematics.readBase64("bXNjaAF4nA3JMQ6AIBAAwQXFRr9i4XuMBR5XkCAYkP9LphwcbmLO/lHMwRq0SY3vF0sGluRvTQ17XoZNStU9d0na20gDduAHAc0Org==")
	}
});
TBLY.cloudMeshLoader = prov(() => new MultiMesh(
	new HexSkyMesh(TBLY, 2, 0.15, 0.14, 5, Color.valueOf("25C9AB80"), 2, 0.42, 1, 0.43),
	new HexSkyMesh(TBLY, 3, 0.6, 0.15, 5, Color.valueOf("25C9AB"), 2, 0.42, 1.2, 0.45)
));
TBLY.generator = new SerpuloPlanetGenerator();
TBLY.visible = TBLY.accessible = TBLY.alwaysUnlocked = true;
TBLY.clearSectorOnLose = false;
TBLY.tidalLock = false;
TBLY.localizedName = "泰伯利亚";
TBLY.prebuildBase = false;
TBLY.bloom = false;
TBLY.startSector = 1;
TBLY.orbitRadius = 85;
TBLY.orbitTime = 180 * 60;
TBLY.rotateTime = 90 * 60;
TBLY.atmosphereRadIn = 0.02;
TBLY.atmosphereRadOut = 0.3;
TBLY.atmosphereColor = TBLY.lightColor = Color.valueOf("25C9AB90");
TBLY.iconColor = Color.valueOf("25C9AB"),
TBLY.hiddenItems.addAll(Items.erekirItems).removeAll(Items.serpuloItems);
/*
main
map number head2
SC map number head2
side
SC map name
*/
const map1pj = new SectorPreset("迫降区", TBLY, 1);
map1pj.description = "我们选择了一个敌人偏远的储存区进行突袭，夺下这个地区，然后想办法与主力部队取得联系";
map1pj.difficulty = 2;
map1pj.alwaysUnlocked = false;
map1pj.addStartingItems = true;
map1pj.captureWave = 0;
map1pj.localizedName = "迫降区";
exports.map1pj = map1pj;
SFlib.addToResearch(map1pj, {
	parent: "planetaryTerminal",
	objectives: Seq.with(
	new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});

const map2hw = new SectorPreset("荒芜矿区", TBLY, 2);
map2hw.description = "雷达摧毁晚了一步，我们被发现了，大批敌人正在赶来。接管后勤基地，迅速巩固防御并开发这里，保护雷达等待我们与其他军团取得联系。";
map2hw.difficulty = 2;
map2hw.alwaysUnlocked = false;
map2hw.addStartingItems = true;
map2hw.captureWave = 100;
map2hw.localizedName = "荒芜矿区";
exports.map2hw = map2hw;
SFlib.addToResearch(map2hw, {
	parent: "迫降区",
	objectives: Seq.with(
	new Objectives.SectorComplete(map1pj))
})

const map3lm = new SectorPreset("雷鸣裂谷", TBLY, 62);
map3lm.description = "这里地形狭长，资源丰富，是敌人的巨浪合金一大产地。\n一支先遣部队已经抵达，摧毁防御工事并占峡谷。\n为以后的战斗准备充足的合金供应";
map3lm.difficulty = 6;
map3lm.alwaysUnlocked = false;
map3lm.addStartingItems = true;
map3lm.captureWave = 0;
map3lm.localizedName = "雷鸣裂谷";
exports.map3lm = map3lm;
SFlib.addToResearch(map3lm, {
	parent: "荒芜矿区",
	objectives: Seq.with(
	new Objectives.SectorComplete(map2hw))
})

const map4pd = new SectorPreset("中央盆地", TBLY, 4);
map4pd.description = "第五军团遭遇敌方主力，我们收到警告必须停下。\n雷达探测到大量敌方单位正向我们围拢，迅速消灭他们，支援第五军团";
map4pd.difficulty = 8;
map4pd.alwaysUnlocked = false;
map4pd.addStartingItems = true;
map4pd.captureWave = 160;
map4pd.localizedName = "中央盆地";
exports.map4pd = map4pd;
SFlib.addToResearch(map4pd, {
	parent: "雷鸣裂谷",
	objectives: Seq.with(
	new Objectives.SectorComplete(map3lm))
});

const map5dw = new SectorPreset("第五战区", TBLY, 5);
map5dw.description = "第五军团遭到了敌人的猛烈反扑，但敌人不全力进攻，只拖延时间的行为及其反常。有大批机械化部队扑向这里，迅速解决敌人继续推进。\n[red]我们只有一次机会！";
map5dw.difficulty = 14;
map5dw.alwaysUnlocked = false;
map5dw.addStartingItems = true;
map5dw.captureWave = 100;
map5dw.localizedName = "第五战区";
exports.map5dw = map5dw;
SFlib.addToResearch(map5dw, {
	parent: "中央盆地",
	objectives: Seq.with(
	new Objectives.SectorComplete(map4pd))
});

const map5hs = new SectorPreset("火山岛", TBLY, 13);
map5hs.description = "海军先遣队在火山口附近建立了一个基地，这里可开采矿物并不多。分离出矿渣中的矿物质，在其余部队赶来之前，消灭追兵并建立基地";
map5hs.difficulty = 13;
map5hs.alwaysUnlocked = false;
map5hs.addStartingItems = true;
map5hs.captureWave = 115;
map5hs.localizedName = "火山岛";
exports.map5hs = map5hs;
SFlib.addToResearch(map5hs, {
	parent: "中央盆地",
	objectives: Seq.with(
	new Objectives.SectorComplete(map4pd))
});

const SCmap6dc = new SectorPreset("断层带", TBLY, 16);
SCmap6dc.description = "这里原是一片岸防基地，前不久探测到大规模地质运动，已经被夷为废墟。\n尚不清楚详细数据信息，接收这片区域，仔细搜查一切可利用的物资并建立哨所。\n第四军团与第五军团海军会与你行动。";
SCmap6dc.difficulty = 4;
//难度断层：4=1~25，10=30~70，15=70~90
SCmap6dc.alwaysUnlocked = false;
SCmap6dc.addStartingItems = true;
SCmap6dc.captureWave = 90;
SCmap6dc.localizedName = "断层带";
exports.SCmap6dc = SCmap6dc;
SFlib.addToResearch(SCmap6dc, {
	parent: "火山岛",
	objectives: Seq.with(
	new Objectives.SectorComplete(map5hs))
});

/*
//做不出来，待定
可能能做，待命
做不出来，放弃
const map8zz = new SectorPreset("战争集成工业区", TBLY, 7);
map8zz.description = "这里仍在运行并持续不断的派遣军队进攻第五战区。先遣部队已经抵达并建立基地，迅速摧毁这个巨大的军工区，不要给他们派出更多援军的机会。";
map8zz.difficulty = 12;
map8zz.alwaysUnlocked = false;
map8zz.addStartingItems = true;
map8zz.captureWave = 160;
map8zz.localizedName = "战争集成工业区";
exports.map8zz = map8zz;
SFlib.addToResearch(map8zz, {
	parent: "第五战区",
	objectives: Seq.with(
	new Objectives.SectorComplete(map5dw))
});
*/

const SCmap6yl = new SectorPreset("溢裂要塞", TBLY, 24);
SCmap6yl.description = "此处常常探测到能量暴动，侦察分队一去不返，甚至一支阔野舰队都在此失联。\n在第五军团交战之中，一支‘破裂’小队趁虚而入炸毁了整个未完全建成的要塞。\n查出阔野舰队失联的原因，随时提防敌方主力反攻";
SCmap6yl.difficulty = 26;
SCmap6yl.alwaysUnlocked = false;
SCmap6yl.addStartingItems = true;
SCmap6yl.captureWave = 80;
SCmap6yl.localizedName = "溢裂要塞";
exports.SCmap6yl = SCmap6yl;
SFlib.addToResearch(SCmap6yl, {
	parent: "第五战区",
	objectives: Seq.with(
	new Objectives.SectorComplete(map5dw))
});

const map6fs = new SectorPreset("风沙谷", TBLY, 8);
map6fs.description = "";
map6fs.difficulty = 12;
map6fs.alwaysUnlocked = false;
map6fs.addStartingItems = true;
map6fs.captureWave = 150;
map6fs.localizedName = "风沙谷";
exports.map6fs = map6fs;
SFlib.addToResearch(map6fs, {
	parent: "火山岛",
	objectives: Seq.with(
	new Objectives.SectorComplete(map5hs))
});

const map7xx = new SectorPreset("星稀山脉", TBLY, 124);
map7xx.description = "环星山脉北部群山，这里临近敌空军基地。夺取战略制高点能够很好的监视敌人空军部队的一举一动。\n第二、第四军团会协助你的战斗，先遣部队已经建立数个地空导弹阵地。\n附近有大量巡逻队，消灭他们夺下这里！";
map7xx.difficulty = 18;
map7xx.alwaysUnlocked = false;
map7xx.addStartingItems = true;
map7xx.captureWave = 45;
map7xx.localizedName = "星稀山脉";
exports.map7xx = map7xx;
SFlib.addToResearch(map7xx, {
	parent: "风沙谷",
	objectives: Seq.with(
	new Objectives.SectorComplete(map6fs))
});
/*
const SCmap8jd = new SectorPreset("急冻雾岭", TBLY, 65);
SCmap8jd.description = "[stat]分支难度警告：非主线战役，可不打。\n行动代号：“断钢锯”\n在此次对敌某集结点的突袭行动中缴获了数台重型机甲单位，这将对我方先进单位的研究突破有重要作用。\n此次撤离行动将由你指挥，敌人绝不会任由我部将之带走，\n数支敌我部队都在向你靠近，小心应对。坚守直到主力部队前来支援。";
SCmap8jd.difficulty = 20;
SCmap8jd.alwaysUnlocked = false;
SCmap8jd.addStartingItems = true;
SCmap8jd.captureWave = 60;
SCmap8jd.localizedName = "急冻雾岭";
exports.SCmap8jd = SCmap8jd;
SFlib.addToResearch(SCmap8jd, {
	parent: "星稀山脉",
	objectives: Seq.with(
	new Objectives.SectorComplete(map7xx))
});
*/
const map9mj = new SectorPreset("灭绝", TBLY, 199);
map9mj.description = "北极基地群78号地区。这里是敌人的纵深腹地，外围防御火力异常凶猛，第五军团勉强撕开了一道裂口，并成功建立前进基地。\n外围友军正在逐步攻坚，一旦突破封锁便会立刻支援。\n[gold]这将是一场硬仗，祝你好运！";
map9mj.difficulty = 20;
map9mj.alwaysUnlocked = false;
map9mj.addStartingItems = true;
map9mj.captureWave = 0;
map9mj.localizedName = "灭绝";
exports.map9mj = map9mj;
SFlib.addToResearch(map9mj, {
	parent: "星稀山脉",
	objectives: Seq.with(
	new Objectives.SectorComplete(map7xx))
});
/*
const map10hk = new SectorPreset("惶恐滩", TBLY, 224);
map10hk.description = "欢迎来到星移滩，这里是[stat]第五军团的驻扎地[white]。\n多亏各个军团之间的紧密配合，北极基地群已经不复存在。对我们最大的威胁已经被解决，是时候休整一下了。\n研究一下刚刚缴获的巨型机械，等待下一步任务指示。";
map10hk.difficulty = 0;
//难度参考等级：26
map10hk.alwaysUnlocked = false;
map10hk.addStartingItems = true;
map10hk.captureWave = 0;
map10hk.localizedName = "星移滩";
map10hk.isLastSector = true;
exports.map10hk = map10hk;
SFlib.addToResearch(map10hk, {
	parent: "灭绝",
	objectives: Seq.with(
	new Objectives.SectorComplete(map9mj))
});

const 残兵败将 = new SectorPreset("残兵败将", TBLY, 9);
残兵败将.description = "亚龙被击毁，他们没有逃跑的机会了。乘胜追击，摧毁这个基地，不要给敌人喘息的机会。";
残兵败将.difficulty = 12;
残兵败将.alwaysUnlocked = false;
残兵败将.addStartingItems = true;
残兵败将.captureWave = 65;
残兵败将.localizedName = "残兵败将";
exports.残兵败将 = 残兵败将;
SFlib.addToResearch(残兵败将, {
	parent: "灭绝",
	objectives: Seq.with(
	new Objectives.SectorComplete(灭绝))
});

const 狭长冰谷 = new SectorPreset("狭长冰谷", TBLY, 10);
狭长冰谷.description = "这里是北部峡谷的必经之地，地形险峻易守难攻。但天气异常寒冷，对我们的单位与建筑或多或少都有些影响。\n敌人正在蜂拥而至，阻断敌军的后续部队并占领这里。";
狭长冰谷.difficulty = 18;
狭长冰谷.alwaysUnlocked = false;
狭长冰谷.addStartingItems = true;
狭长冰谷.captureWave = 150;
狭长冰谷.localizedName = "狭长冰谷";
exports.狭长冰谷 = 狭长冰谷;
SFlib.addToResearch(狭长冰谷, {
	parent: "残兵败将",
	objectives: Seq.with(
	new Objectives.SectorComplete(残兵败将))
});

const SCmapkdhk = new SectorPreset("溃堤海口", TBLY, 48);
SCmapkdhk.description = "[stat]分支难度警告：非主线战役，可不打。\n[white]-泰伯利亚赤道南侧，拉乌达洋某处河流集群入海口。此处峡谷年代久远，长年累月的冲刷甚至使峭壁之下产生了入海口三角洲。\n第二军团已于此处建立5条坝式水力能源站，敌舰队正在附近聚集，保护能源站并剿灭所有敌人。";
SCmapkdhk.difficulty = 20;
SCmapkdhk.alwaysUnlocked = false;
SCmapkdhk.addStartingItems = true;
SCmapkdhk.captureWave = 90;
SCmapkdhk.localizedName = "溃堤海口";
exports.SCmapkdhk = SCmapkdhk;
SFlib.addToResearch(SCmapkdhk, {
	parent: "断层带",
	objectives: Seq.with(
	new Objectives.SectorComplete(SCmap6dc))
});
*/
const SCmaphtl = new SectorPreset("火天路", TBLY, 48);
SCmaphtl.description = "[stat]分支难度警告：非主线战役，可不打。\n[white]阔野生产试验基地-熔岩区，该处地质活动比较频繁，同时该处的矿物质丰度很高，能源储备充裕。\n整理手头资源，尽可能协助第二军团进行阔野的生产理论研究，不要使用高能量反应的器械，避免被敌方探测。";
SCmaphtl.difficulty = 22;
SCmaphtl.alwaysUnlocked = false;
SCmaphtl.addStartingItems = true;
SCmaphtl.captureWave = 90;
SCmaphtl.localizedName = "火天路";
exports.SCmaphtl = SCmaphtl;
SFlib.addToResearch(SCmaphtl, {
	parent: "星稀山脉",
	objectives: Seq.with(
	new Objectives.SectorComplete(map7xx),
	new Objectives.SectorComplete(SCmap6yl))
});//神渎

const SCmapsyzzd = new SectorPreset("实验种植地", TBLY, 64);
SCmapsyzzd.description = "[stat]分支难度警告：非主线战役，可不打。\n[white]这是一处难得的自然条件适宜进行有机体研究的河域，第二军团已经在此驻扎，协助他们获取足够的样本并发送至我们的基地。";
SCmapsyzzd.difficulty = 20;
SCmapsyzzd.alwaysUnlocked = false;
SCmapsyzzd.addStartingItems = true;
SCmapsyzzd.captureWave = 12;
SCmapsyzzd.localizedName = "实验种植地";
exports.SCmapsyzzd = SCmapsyzzd;
SFlib.addToResearch(SCmapsyzzd, {
	parent: "火山岛",
	objectives: Seq.with(
	new Objectives.SectorComplete(map5hs),
	new Objectives.SectorComplete(SCmap6yl))
});//地魁
/*

const SCmapjrsl = new SectorPreset("锯刃山岭", TBLY, 10);
SCmapjrsl.description = "[stat]分支难度警告：非主线战役，可不打。\n[white]";
SCmapjrsl.difficulty = 24;
SCmapjrsl.alwaysUnlocked = false;
SCmapjrsl.addStartingItems = true;
SCmapjrsl.captureWave = 30;
SCmapjrsl.localizedName = "SCmapjrsl";
exports.SCmapjrsl = SCmapjrsl;
SFlib.addToResearch(SCmapjrsl, {
	parent: "风沙谷",
	objectives: Seq.with(
	new Objectives.SectorComplete(map6fs),
	new Objectives.SectorComplete(SCmap6yl))
});//

const SCmapnlfsc = new SectorPreset("逆流发射场", TBLY, 10);
SCmapnlfsc.description = "[stat]分支难度警告：非主线战役，可不打。\n[white]";
SCmapnlfsc.difficulty = 24;
SCmapnlfsc.alwaysUnlocked = false;
SCmapnlfsc.addStartingItems = true;
SCmapnlfsc.captureWave = 30;
SCmapnlfsc.localizedName = "SCmapnlfsc";
exports.SCmapnlfsc = SCmapnlfsc;
SFlib.addToResearch(SCmapnlfsc, {
	parent: "锯刃山岭",
	objectives: Seq.with(
	new Objectives.SectorComplete(SCmapjrsl))
});


const 阑珊子午线 = new SectorPreset("阑珊子午线", TBLY, 10);
阑珊子午线.description = "[stat]分支难度警告：非主线战役，可不打。\n[white]";
阑珊子午线.difficulty = 28;
阑珊子午线.alwaysUnlocked = false;
阑珊子午线.addStartingItems = true;
阑珊子午线.captureWave = 70;
阑珊子午线.localizedName = "阑珊子午线";
exports.阑珊子午线 = 阑珊子午线;
SFlib.addToResearch(阑珊子午线, {
	parent: "溢裂要塞",
	objectives: Seq.with(
	new Objectives.SectorComplete(溢裂要塞))
});



*/

/*
const 地图变量名 = new SectorPreset("灭绝", TBLY, 78);
地图变量名.description = "";
地图变量名.difficulty = 20;
地图变量名.alwaysUnlocked = false;
地图变量名.addStartingItems = true;
地图变量名.captureWave = 0;
地图变量名.localizedName = "灭绝";
exports.地图变量名 = 地图变量名;
SFlib.addToResearch(地图变量名, {
	parent: "科技树位置=地图名",
	objectives: Seq.with(
	new Objectives.SectorComplete(星稀山脉))
});
*/