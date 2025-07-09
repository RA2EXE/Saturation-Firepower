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
    public SFireMod() {}


    @Override
    public void init() {
        BaseDialog dialog = new BaseDialog("饱和火力mod加载");
        Log.info("Saturation Firepower【饱和火力】 has loaded");
        dialog.cont.add("[red]警告！[未完成的测试版]\n具体数值仍需要敲定\n[]在确定具体数值调节之前请认真思考其[stat]必要性、合理性、可靠性[]后上报").row();
        dialog.cont.image(Core.atlas.find("sfire-mod-gked")).pad(20f).row();
        dialog.cont.button("确认", dialog::hide).size(150f, 50f);
        dialog.show();
    }

    @Override
    public void loadContent() {
        SFSounds.load();
        SFStatusEffects.load();
        SFAttribute.load();
        SFItems.load();
        SFLiquids.load();
        //SFBulletTypes.load();
        SFUnitTypes.loadUnit();
        SFBlocks.load();
        SFOverride.load();
        SFPlanets.load();
        SFTechTree.load();
    }

}
