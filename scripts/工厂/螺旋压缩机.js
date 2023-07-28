const library = require("base/library");
const 螺旋压缩机 = library.MultiCrafter(GenericCrafter, GenericCrafter.GenericCrafterBuild, "螺旋压缩机", [
  {
    input: {
      items: ["coal/8"],     
      liquids: ["water/12"],
      power: 3,
    },
    output: {
      items: ["graphite/4"],
    },
    craftTime: 7.5,
  }, 
  {
    input: {
      items: ["spore-pod/4"],     
      power: 2.8,
    },
    output: {
      liquids: ["oil/24"],
    },
    craftTime: 15,
  },
  {
    input: {
      items: ["titanium/2"],     
      liquids: ["oil/15"],
      power: 7,
    },
    output: {
      items: ["plastanium/1"],
    },
    craftTime: 7.5,
  },
  {
    input: {
      items: ["titanium/8","饱和火力-石油桶/1"],
      power: 12,
    },
    output: {
      items: ["plastanium/5"],
    },
    craftTime: 15,
  }, 


]);