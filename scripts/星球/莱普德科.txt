const lib = require("SFlib");
const 莱普德科 = new JavaAdapter(Planet, {
    load() {
        this.meshLoader = prov(() => new HexMesh(莱普德科, 4));
        this.super$load();
    }
}, "莱普德科", Planets.sun, 1);
const sS = require("sectorSize");
sS.planetGrid(莱普德科, 3.3);
莱普德科.generator = new SerpuloPlanetGenerator();
莱普德科.visible = true;
莱普德科.bloom = false;
莱普德科.accessible = true;
莱普德科.tidalLock = true
莱普德科.atmosphereColor = Color.valueOf("5F86ADFF");
莱普德科.alwaysUnlocked = true;
莱普德科.atmosphereRadIn = 0.02;
莱普德科.atmosphereRadOut = 0.1;
莱普德科.localizedName = "莱普德科";
莱普德科.orbitRadius = 85;
莱普德科.startSector = 1;

const 残兵败将 = new SectorPreset("残兵败将", 莱普德科, 1);
残兵败将.alwaysUnlocked = false;
残兵败将.difficulty = 12;
残兵败将.captureWave = 85;
残兵败将.localizedName = "残兵败将";
exports.残兵败将 = 残兵败将;
lib.addToResearch(残兵败将, {
    parent: '惶恐滩',
    objectives: Seq.with(
        new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});

const 狭长冰谷 = new SectorPreset("狭长冰谷", 莱普德科, 18);
狭长冰谷.alwaysUnlocked = false;
狭长冰谷.difficulty = 12;
狭长冰谷.captureWave = 65;
狭长冰谷.localizedName = "狭长冰谷";
exports.狭长冰谷 = 狭长冰谷;
lib.addToResearch(狭长冰谷, {
    parent: '残兵败将',
    objectives: Seq.with(
        new Objectives.SectorComplete(SectorPresets.planetaryTerminal))
});
