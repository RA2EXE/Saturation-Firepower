package SFire;

import SFire.content.*;

import arc.*;
import arc.util.*;
import mindustry.game.EventType;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class SFireMod extends Mod {
    public SFireMod() {
        //Events.on(EventType.ContentInitEvent.class, e -> {});
    }
    public static String ModName = "sfire-mod";
    public static String name(String add){
        return ModName + "-" + add;
    }


    @Override
    public void init() {
       /* BaseDialog dialog = new BaseDialog("饱和火力mod加载");
        Log.info("Saturation Firepower【饱和火力】 has loaded");
        dialog.cont.add("当前版本4.0.2").row();
        dialog.cont.image(Core.atlas.find("sfire-mod-gked")).pad(20f).row();
        dialog.cont.button("OK", dialog::hide).size(150f, 50f);
        dialog.show();*/
        TechFix.init();
    }


    @Override
    public void loadContent() {
        SFSounds.load();
        SFStatusEffects.load();
        SFAttribute.load();
        SFItems.load();
        SFLiquids.load();
        SFUnitTypes.loadUnit();
        SFBlocks.load();
        SFOverride.load();
        SFPlanets.load();
        SFSectorPresets.load();
        SFTechTree.load();
    }

}
