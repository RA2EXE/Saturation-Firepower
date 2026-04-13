package SFire.content;

import mindustry.type.*;

import static SFire.content.SFPlanets.*;
import static mindustry.content.Planets.*;

public class SFSectorPresets {
    public static SectorPreset
            //sector-sfire-mod-
    frozenFront, centerCore,

    crackHills,sandValley,craterBay
    ;
    public static void load(){
        frozenFront = new SectorPreset("frozenFront", tiberia, 218){{
            difficulty = 1;
            addStartingItems = true;
            
        }};
        centerCore = new SectorPreset("centerCore", tiberia, 125){{
            difficulty = 2;
            addStartingItems = true;
            captureWave = 60;
        }};
    }
}
