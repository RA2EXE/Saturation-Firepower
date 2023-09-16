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

const 迫降区 = new SectorPreset("迫降区", TBLY, 1);
迫降区.description = "我们选择了一个敌人偏远的储存区进行突袭，夺下这个地区，然后想办法与主力部队取得联系";
迫降区.difficulty = 2;
迫降区.alwaysUnlocked = false;
迫降区.addStartingItems = true;
迫降区.captureWave = 0;
迫降区.localizedName = "迫降区";
exports.迫降区 = 迫降区;
SFlib.addToResearch(迫降区, {
	parent: "planetaryTerminal",
	objectives: Seq.with(
	new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});

const 荒芜矿区 = new SectorPreset("荒芜矿区", TBLY, 2);
荒芜矿区.description = "看看我们找到了什么？一个资源富饶的大矿区，坏消息是敌人也发现了这里，还有我们。既然不能隐秘行事了，那就放开了去做吧。";
荒芜矿区.difficulty = 2;
荒芜矿区.alwaysUnlocked = false;
荒芜矿区.addStartingItems = true;
荒芜矿区.captureWave = 100;
荒芜矿区.localizedName = "荒芜矿区";
exports.荒芜矿区 = 荒芜矿区;
SFlib.addToResearch(荒芜矿区, {
	parent: "迫降区",
	objectives: Seq.with(
	new Objectives.SectorComplete(迫降区))
})

const 雷鸣裂谷 = new SectorPreset("雷鸣裂谷", TBLY, 62);
雷鸣裂谷.description = "这里地形狭长，资源丰富，是敌人的巨浪合金一大产地。\n一支先遣部队已经抵达，摧毁防御工事并占峡谷。\n为以后的战斗准备充足的合金供应";
雷鸣裂谷.difficulty = 6;
雷鸣裂谷.alwaysUnlocked = false;
雷鸣裂谷.addStartingItems = true;
雷鸣裂谷.captureWave = 0;
雷鸣裂谷.localizedName = "雷鸣裂谷";
exports.雷鸣裂谷 = 雷鸣裂谷;
SFlib.addToResearch(雷鸣裂谷, {
	parent: "荒芜矿区",
	objectives: Seq.with(
	new Objectives.SectorComplete(荒芜矿区))
})

const 中央盆地 = new SectorPreset("中央盆地", TBLY, 4);
中央盆地.description = "第五军团遭遇敌方主力，我们收到警告必须停下。\n雷达探测到大量敌方单位正向我们围拢，迅速消灭他们，支援第五军团";
中央盆地.difficulty = 8;
中央盆地.alwaysUnlocked = false;
中央盆地.addStartingItems = true;
中央盆地.captureWave = 160;
中央盆地.localizedName = "中央盆地";
exports.中央盆地 = 中央盆地;
SFlib.addToResearch(中央盆地, {
	parent: "雷鸣裂谷",
	objectives: Seq.with(
	new Objectives.Research(雷鸣裂谷))
});

const 第五战区 = new SectorPreset("第五战区", TBLY, 5);
第五战区.description = "第五军团遭到了敌人的猛烈反扑，但敌人不全力进攻，只拖延时间的行为及其反常。有大批机械化部队扑向这里，迅速解决敌人继续推进。\n[red]我们只有一次机会！";
第五战区.difficulty = 14;
第五战区.alwaysUnlocked = false;
第五战区.addStartingItems = true;
第五战区.captureWave = 100;
第五战区.localizedName = "第五战区";
exports.第五战区 = 第五战区;
SFlib.addToResearch(第五战区, {
	parent: "中央盆地",
	objectives: Seq.with(
	new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});

const 火山岛 = new SectorPreset("火山岛", TBLY, 13);
火山岛.description = "海军先遣队在火山口附近建立了一个基地，这里可开采矿物并不多。分离出矿渣中的矿物质，在其余部队赶来之前，消灭追兵并建立基地";
火山岛.difficulty = 13;
火山岛.alwaysUnlocked = false;
火山岛.addStartingItems = true;
火山岛.captureWave = 115;
火山岛.localizedName = "火山岛";
exports.火山岛 = 火山岛;
SFlib.addToResearch(火山岛, {
	parent: "中央盆地",
	objectives: Seq.with(
	new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});

const 断层带 = new SectorPreset("断层带", TBLY, 16);
断层带.description = "这里原是一片岸防基地，前不久探测到大规模地质运动，已经被夷为废墟。\n尚不清楚详细数据信息，接收这片区域，仔细搜查一切可利用的物资并建立哨所。\n第四军团与第五军团海军会与你行动。";
断层带.difficulty = 4;
//难度断层：4-1~25，10-30~70，15-70~90
断层带.alwaysUnlocked = false;
断层带.addStartingItems = true;
断层带.captureWave = 90;
断层带.localizedName = "断层带";
exports.断层带 = 断层带;
SFlib.addToResearch(断层带, {
	parent: "火山岛",
	objectives: Seq.with(
	new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});

/*
//做不出来，待定
可能能做，待命
const 军工河原 = new SectorPreset("军工河原", TBLY, 7);
军工河原.description = "这里仍在运行并持续不断的派遣军队进攻第五战区。先遣部队已经抵达并建立基地，迅速摧毁这个巨大的军工区，不要给他们派出更多援军的机会。";
军工河原.difficulty = 12;
军工河原.alwaysUnlocked = false;
军工河原.addStartingItems = true;
军工河原.captureWave = 160;
军工河原.localizedName = "军工河原";
exports.军工河原 = 军工河原;
SFlib.addToResearch(军工河原, {
	parent: "第五战区",
	objectives: Seq.with(
	new Objectives.SectorComplete(第五战区))
});
*/

const 溢裂要塞 = new SectorPreset("溢裂要塞", TBLY, 24);
溢裂要塞.description = "此处常常探测到能量暴动，侦察分队一去不返，甚至一支阔野舰队都在此失联。\n在第五军团交战之中，一支‘破裂’小队趁虚而入炸毁了整个未完全建成的要塞。\n查出阔野舰队失联的原因，随时提防敌方主力反攻";
溢裂要塞.difficulty = 26;
溢裂要塞.alwaysUnlocked = false;
溢裂要塞.addStartingItems = true;
溢裂要塞.captureWave = 80;
溢裂要塞.localizedName = "溢裂要塞";
exports.溢裂要塞 = 溢裂要塞;
SFlib.addToResearch(溢裂要塞, {
	parent: "第五战区",
	objectives: Seq.with(
	new Objectives.SectorComplete(第五战区))
});

const 风沙谷 = new SectorPreset("风沙谷", TBLY, 8);
风沙谷.description = "地处敌人的空军基地附近，环境恶劣，资源稀少，尽可能搜集当地资源，组织防御。\n阻断了敌人的雷达，在10分钟之内不可能发现我们，当心附近的巡逻部队，一旦被发现，主力军只需数十分钟便会赶过来";
风沙谷.difficulty = 12;
风沙谷.alwaysUnlocked = false;
风沙谷.addStartingItems = true;
风沙谷.captureWave = 150;
风沙谷.localizedName = "风沙谷";
exports.风沙谷 = 风沙谷;
SFlib.addToResearch(风沙谷, {
	parent: "火山岛",
	objectives: Seq.with(
	new Objectives.SectorComplete(火山岛))
});

const 星稀山脉 = new SectorPreset("星稀山脉", TBLY, 124);
星稀山脉.description = "环星山脉北部群山，这里临近敌空军基地。夺取战略制高点能够很好的监视敌人空军部队的一举一动。\n第二、第四军团会协助你的战斗，先遣部队已经建立数个地空导弹阵地。\n附近有大量巡逻队，消灭他们夺下这里！";
星稀山脉.difficulty = 18;
星稀山脉.alwaysUnlocked = false;
星稀山脉.addStartingItems = true;
星稀山脉.captureWave = 45;
星稀山脉.localizedName = "星稀山脉";
exports.星稀山脉 = 星稀山脉;
SFlib.addToResearch(星稀山脉, {
	parent: "风沙谷",
	objectives: Seq.with(
	new Objectives.SectorComplete(风沙谷))
});

const 急冻雾岭 = new SectorPreset("急冻雾岭", TBLY, 65);
急冻雾岭.description = "行动代号：“断钢锯”/n在此次对敌某集结点的突袭行动中缴获了数台重型机甲单位，这将对我方先进单位的研究突破有重要作用。\n此次撤离行动将由你指挥，敌人绝不会任由我部将之带走，\n数支敌我部队都在向你靠近，小心应对。坚守直到主力部队前来支援。";
急冻雾岭.difficulty = 20;
急冻雾岭.alwaysUnlocked = false;
急冻雾岭.addStartingItems = true;
急冻雾岭.captureWave = 60;
急冻雾岭.localizedName = "急冻雾岭";
exports.急冻雾岭 = 急冻雾岭;
SFlib.addToResearch(急冻雾岭, {
	parent: "星稀山脉",
	objectives: Seq.with(
	new Objectives.SectorComplete(星稀山脉))
});

const 灭绝 = new SectorPreset("灭绝", TBLY, 78);
灭绝.description = "北极基地群78号地区。这里是敌人的纵深腹地，外围防御火力异常凶猛，第五军团勉强撕开了一道裂口，并成功建立前进基地。\n外围友军正在逐步攻坚，一旦突破封锁便会立刻支援。\n[gold]这将是一场硬仗，祝你好运！";
灭绝.difficulty = 20;
灭绝.alwaysUnlocked = false;
灭绝.addStartingItems = true;
灭绝.captureWave = 0;
灭绝.localizedName = "灭绝";
exports.灭绝 = 灭绝;
SFlib.addToResearch(灭绝, {
	parent: "星稀山脉",
	objectives: Seq.with(
	new Objectives.SectorComplete(星稀山脉))
});
/*
const 惶恐滩 = new SectorPreset("惶恐滩", TBLY, 224);
惶恐滩.description = "欢迎来到星移滩，这里是[stat]第五军团的驻扎地[white]。\n多亏各个军团之间的紧密配合，北极基地群已经不复存在。对我们最大的威胁已经被解决，是时候休整一下了。\n研究一下刚刚缴获的巨型机械，等待下一步任务指示。";
惶恐滩.difficulty = 0;
//难度参考等级：26
惶恐滩.alwaysUnlocked = false;
惶恐滩.addStartingItems = true;
惶恐滩.captureWave = 0;
惶恐滩.localizedName = "星移滩";
惶恐滩.isLastSector = true;
exports.惶恐滩 = 惶恐滩;
SFlib.addToResearch(惶恐滩, {
	parent: "灭绝",
	objectives: Seq.with(
	new Objectives.SectorComplete(灭绝))
});
*/
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
	new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
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
	new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});




const 火天路 = new SectorPreset("火天路", TBLY, 48);
火天路.description = "[stat]分支难度警告：非主线战役，可不打。\n[white]阔野生产试验基地-熔岩区，该处地质活动比较频繁，同时该处的矿物质丰度很高，能源储备充裕。\n整理手头资源，尽可能协助第二军团进行阔野的生产理论研究，不要使用高能量反应的武器，避免被敌方探测。";
火天路.difficulty = 22;
火天路.alwaysUnlocked = false;
火天路.addStartingItems = true;
火天路.captureWave = 90;
火天路.localizedName = "火天路";
exports.火天路 = 火天路;
SFlib.addToResearch(火天路, {
	parent: "星稀山脉",
	objectives: Seq.with(
	new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});

const 实验种植地 = new SectorPreset("实验种植地", TBLY, 64);
实验种植地.description = "[stat]分支难度警告：非主线战役，可不打。\n[white]这是一处难得的自然条件适宜进行有机体研究的河域，第二军团已经在此驻扎，协助他们获取足够的样本并发送至我们的基地。";
实验种植地.difficulty = 20;
实验种植地.alwaysUnlocked = false;
实验种植地.addStartingItems = true;
实验种植地.captureWave = 12;
实验种植地.localizedName = "实验种植地";
exports.实验种植地 = 实验种植地;
SFlib.addToResearch(实验种植地, {
	parent: "星稀山脉",
	objectives: Seq.with(
	new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});
