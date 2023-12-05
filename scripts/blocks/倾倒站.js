const library = require("base/library");
const myliquids = require("SFliquids");
const myitems = require("SFitems");
const 倾倒站 = library.MultiCrafter(GenericCrafter, GenericCrafter.GenericCrafterBuild, "倾倒站", [
	{
		input: {
			items: ["饱和火力-水桶/1"],
		},
		output: {
			liquids: ["water/60"],
		},
		craftTime: 30,
	},
	{
		input: {
			items: ["饱和火力-矿渣桶/1"],
		},
		output: {
			liquids: ["slag/60"],
		},
		craftTime: 30,
	},
	{
		input: {
			items: ["饱和火力-冷冻液桶/1"],
		},
		output: {
			liquids: ["cryofluid/60"],
		},
		craftTime: 30,
	},
	{
		input: {
			items: ["饱和火力-石油桶/1"],
		},
		output: {
			liquids: ["oil/60"],
		},
		craftTime: 30,
	},
	{
		input: {
			items: ["饱和火力-纳米流体桶/1"],
		},
		output: {
			liquids: ["饱和火力-纳米流体/60"],
		},
		craftTime: 30,
	},


]);