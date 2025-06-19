package SFire.content;

import arc.graphics.*;
//import javafx.scene.paint.Color;
import mindustry.type.*;

public class SFItems {
    public static Item
            strontium, rubidium, fermium, chromium, rareEarth,
    siliSteel, waveSteel, clusBomb, discFabric, nanoCore, lens,
    tayrAlloy, leipAlloy;
    public static void load(){
        strontium = new Item("strontium", Color.valueOf("FFB0B0")){{
            hardness = 2;
            flammability = 1.6f;
            explosiveness = 0.3f;
            buildable = false;
        }};

        rubidium = new Item("rubidium", Color.valueOf("D0BAE6")){{
            hardness = 3;
            cost = 1f;
        }};

        fermium = new Item("fermium", Color.valueOf("DEDEDE")){{
            hardness = 6;
            cost = 2f;
            healthScaling = 1.8f;
            radioactivity = 1.5f;
        }};

        chromium = new Item("chromium", Color.valueOf("666484")){{
            hardness = 4;
            cost = 6f;
            healthScaling = 2f;
        }};

        rareEarth = new Item("rare-earth", Color.valueOf("D0DEB1")){{
            hardness = 2;
            buildable = false;
        }};

        siliSteel = new Item("silisteel", Color.valueOf("7595D2")){{
            cost = 1.75f;
            healthScaling = 0.4f;
        }};

        waveSteel = new Item("wavesteel", Color.valueOf("71A5BF")){{
            cost = 1.2f;
            healthScaling = 0.4f;
        }};

        clusBomb = new Item("cluster-bomb", Color.valueOf("FF5845")){{
            explosiveness = 3.8f;
            flammability = 0.85f;
            buildable = false;
        }};

        discFabric = new Item("discordance-fabric", Color.valueOf("EEC591")){{
            cost = 1.5f;
            radioactivity = 2.5f;
            explosiveness = 0.85f;
        }};

        nanoCore = new Item("nano-core", Color.valueOf("76D081")){{
            cost = -0.75f;
        }};

        lens = new Item("lens", Color.valueOf("E8EEFF")){{
            cost = 0.75f;
        }};

        tayrAlloy = new Item("tayrium-alloy", Color.valueOf("25C9AB")){{
            cost = 6f;
            charge = 1.2f;
        }};

        leipAlloy = new Item("leippium-alloy", Color.valueOf("5C5D79")){{
            cost = 6f;
            healthScaling = 3f;
        }};

    }
}