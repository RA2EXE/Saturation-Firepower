package SFire.content;

import SFire.expand.blocks.OmniBridge;
import SFire.expand.blocks.OmniLiquidBridge;
import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.DirectionalUnloader;
import mindustry.world.blocks.distribution.Duct;
import mindustry.world.blocks.distribution.DuctRouter;
import mindustry.world.blocks.liquid.ArmoredConduit;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidJunction;
import mindustry.world.blocks.liquid.LiquidRouter;

import static mindustry.type.ItemStack.with;

public class SFspaceBlock {
    public static Block
    tiDuct, tiRouter, tiUnloader,
    spBridge, spBridgeLong,
    tiConduit, tiLiquidRouter, tiLiquidJunction,
    spLquidBridge, spLquidBridgeLong
    //production

    ;

    public static void load(){
        tiDuct = new Duct("space-duct"){{
            health = 250;
            requirements(Category.distribution, with(Items.titanium, 1));
            speed = 60/ 20f;
        }};
        tiRouter = new DuctRouter("space-router"){{
            requirements(Category.distribution, with(Items.titanium, 3, Items.graphite, 1));
            health = 250;
            speed = 60/ 20f;
            regionRotated1 = 1;
            solid = false;
        }};
        tiUnloader = new DirectionalUnloader("space-unloader"){{
            requirements(Category.distribution, with(SFItems.waveSteel, 10, Items.silicon, 10));
            health = 250;
            speed = 60/ 20f;
            regionRotated1 = 1;
            squareSprite = false;
            solid = false;
            underBullets = true;
            allowCoreUnload = true;
        }};
        spBridge = new OmniBridge("space-bridge"){{
            health = 500;
            requirements(Category.distribution, with(Items.lead,3, SFItems.waveSteel,3));
            //envRequired = Env.space;
            hasPower = false;
            range = 13;
            transportTime = 60/ 20f;
            bridgeWidth = 8f;
            arrowSpacing = 6f;
            ((Duct) tiDuct).bridgeReplacement = this;
            unloadable = true;
        }};
        spBridgeLong = new OmniBridge("space-bridge-long"){{
            health = 1100;
            armor = 8;
            requirements(Category.distribution, with(SFItems.siliSteel,3, SFItems.waveSteel,6, SFItems.discFabric,3));
            //envRequired = Env.space;
            hasPower = false;
            pulse = true;
            consumePower(3/60f);
            range = 33;
            transportTime = 60/ 40f;
            bridgeWidth = 8f;
            arrowSpacing = 8;
            arrowOffset = 4;
            arrowTimeScl = 12;
            //((Duct) tiDuct).bridgeReplacement = this;
            unloadable = true;
        }};

        tiConduit = new ArmoredConduit("space-conduit"){{
            requirements(Category.liquid, with(Items.graphite,1, Items.titanium, 1));
            health = 250;
            liquidCapacity = 100;
            liquidPressure = 3f;
            explosivenessScale = flammabilityScale = 0;
            botColor = Color.valueOf("3C3E45");
            underBullets = true;
        }};
        tiLiquidRouter = new LiquidRouter("space-liquid-router"){{
            requirements(Category.liquid, with(Items.graphite,4, Items.titanium, 4));
            health = 250;
            liquidCapacity = 200;
            explosivenessScale = flammabilityScale = 0;
            underBullets = true;
            solid = false;
        }};
        tiLiquidJunction = new LiquidJunction("space-liquid-junction"){{
            requirements(Category.liquid, with(Items.graphite,6, Items.titanium, 4));
            health = 250;
            solid = false;
            ((Conduit) tiConduit).junctionReplacement = this;
        }};
        spLquidBridge = new OmniLiquidBridge("space-liquid-bridge"){{
            health = 500;
            requirements(Category.liquid, with(Items.lead,3, SFItems.siliSteel,3));
            //envRequired = Env.space;
            range = 13;
            hasPower = false;

            liquidCapacity = 100f;
            explosivenessScale = flammabilityScale = 0;

            bridgeWidth = 8f;
            arrowSpacing = 6f;
            ((Conduit) tiConduit).bridgeReplacement = this;
        }};
        spLquidBridgeLong = new OmniLiquidBridge("space-liquid-bridge-long"){{
            health = 1100;
            armor = 8;
            requirements(Category.liquid, with(SFItems.siliSteel,6, SFItems.waveSteel,3, SFItems.discFabric,3));
            //envRequired = Env.space;
            range = 33;
            consumePower(3/60f);

            liquidCapacity = 200f;
            explosivenessScale = flammabilityScale = 0;

            bridgeWidth = 8f;
            arrowSpacing = 8;
            arrowOffset = 4;
            arrowTimeScl = 12;
            fadeIn = moveArrows = false;
            //((Conduit) tiConduit).bridgeReplacement = this;
        }};


    }
}
