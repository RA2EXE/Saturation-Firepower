const library = require("base/library");
const myliquids = require("液体");
const myitems = require("物品");
const 灌注站 = library.MultiCrafter(GenericCrafter, GenericCrafter.GenericCrafterBuild, "灌注站", [
  {
    input: {
      items: ["metaglass/2"],     
      liquids: ["water/60"],
      power: 1,
    },
    output: {
      items: ["饱和火力-水桶/1"],
    },
    craftTime: 60,
  }, 
  {
    input: {
      items: ["metaglass/2"],     
      liquids: ["slag/60"],
      power: 1,
    },
    output: {
      items: ["饱和火力-矿渣桶/1"],
    },
    craftTime: 60,
  },
  {
    input: {
      items: ["metaglass/2"],     
      liquids: ["cryofluid/60"],
      power: 1,
    },
    output: {
      items: ["饱和火力-冷冻液桶/1"],
    },
    craftTime: 60,
  },
  {
    input: {
      items: ["metaglass/2"],     
      liquids: ["oil/60"],
      power: 1,
    },
    output: {
      items: ["饱和火力-石油桶/1"],
    },
    craftTime: 60,
  },
{
    input: {
      items: ["metaglass/2"],     
      liquids: ["饱和火力-纳米流体/60"],
      power: 1,
    },
    output: {
      items: ["饱和火力-纳米流体桶/1"],
    },
    craftTime: 60,
  },
  
  
]);