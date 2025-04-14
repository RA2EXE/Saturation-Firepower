package SFire;

import SFire.content.*;

import arc.*;
import arc.util.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class SFireMod extends Mod {
    public static String ModName = "sfire-mod";
    public static String name(String add){
        return ModName + "-" + add;
    }
    public SFireMod() {
    }

    @Override
    public void init() {
        BaseDialog dialog = new BaseDialog("Saturation Firepower has loaded");
        Log.info("加载饱和火力mod");
        dialog.cont.add("这是饱和火力logo").row();
        dialog.cont.image(Core.atlas.find("sfire-mod-sflog")).pad(20f).row();
        dialog.cont.button("好！很有精神！", dialog::hide).size(150f, 50f);
        dialog.show();
    }

    @Override
    public void loadContent() {
        SFSounds.load();
        SFStatusEffects.load();
        SFItems.load();
        SFLiquids.load();
        SFOverride.load();
        //SFBulletType.load();
        SFUnitTypes.loadUnit();
        SFBlocks.load();
        SFTechTree.load();
    }

}
