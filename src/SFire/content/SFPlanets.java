package SFire.content;

import arc.graphics.Color;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;
import mindustry.world.meta.Attribute;

public class SFPlanets {
    public static Planet tiberia;
    public static void load(){
        tiberia = new Planet("tiberia", Planets.sun,1,3){{
            visible = accessible = alwaysUnlocked = true;

            sectorSeed = 3164;
            generator = new SerpuloPlanetGenerator();

            //generator = new TiberiaPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 8);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(SFItems.tayrAlloy.color).mul(0.9f).a(0.75f), 2, 0.42f, 0.2f, 0.43f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(SFItems.tayrAlloy.color, 0.55f).a(0.75f), 2, 0.42f, 0.5f, 0.45f)
            );
            iconColor = SFColor.tayrDark;
            atmosphereColor = lightColor = SFColor.tayrDark.cpy().a(0.8f);
            prebuildBase = false;
            bloom = false;
            startSector = 15;

            orbitRadius = 85;
            orbitTime = 180 * 60;
            rotateTime = 30 * 60;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            landCloudColor = SFColor.tayrDark.cpy().a(0.5f);

            defaultAttributes.set(Attribute.heat, 0.005f);
            defaultAttributes.set(SFAttribute.radioactivity, 0.01f);

            allowWaves = true;
            allowLegacyLaunchPads = false;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = false;
            allowLaunchToNumbered = false;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            prebuildBase = false;
            clearSectorOnLose = true;
            ruleSetter = r -> {
                r.waveTeam = Team.crux;
                r.placeRangeCheck = false;
                r.showSpawns = false;
                r.coreDestroyClear = true;
                r.onlyDepositCore = true;
            };

            allowCampaignRules = false;
            campaignRuleDefaults.showSpawns = true;
            campaignRuleDefaults.rtsAI = true;




        }};
    }
}
