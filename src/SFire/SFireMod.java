package SFire;

import SFire.content.*;

import arc.*;
import arc.util.*;
import mindustry.game.EventType;
import mindustry.mod.*;

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
        /*BaseDialog dialog = new BaseDialog("Warning!");
        Log.info("4.1.0 update!");
        dialog.cont.add("mod has updated to Mindustry 159.3 from 159, using (v4.1.0) load old version maps may broken and lost your saves\n使用4.1.0加载旧版本地图可能失败并且丢失存档！请慎重选择");
        dialog.cont.add("[stat]Version[4.1.0]").row();
        //dialog.cont.image(Core.atlas.find("sfire-mod-gked")).pad(20f).row();
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
