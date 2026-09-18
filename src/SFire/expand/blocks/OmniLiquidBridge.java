package SFire.expand.blocks;

import mindustry.gen.Building;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;

public class OmniLiquidBridge extends OmniBridge{
    public OmniLiquidBridge(String name){
        super(name);
        hasItems = false;
        hasLiquids = true;
        outputsLiquid = true;
        canOverdrive = false;
        group = BlockGroup.liquids;
        envEnabled = Env.any;
    }

    public class OmniLiquidBridgeBuild extends OmniBridgeBuild{

        @Override
        public void updateTransport(Building other){
            if(warmup >= 0.25f){
                moved |= moveLiquid(other, liquids.current()) > 0.05f;
            }
        }

        @Override
        public void doDump(){
            dumpLiquid(liquids.current(), 1f);
        }
    }
}
