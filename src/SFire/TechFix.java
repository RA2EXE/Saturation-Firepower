package SFire;

import SFire.content.*;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.type.Planet;
import mindustry.type.UnitType;
import mindustry.world.Block;

import java.lang.reflect.Field;

public class TechFix {
    public static void init() {
        //come from @guiYMOUR: ADCMain
        Planet sfplanet = SFPlanets.tiberia;

        Liquids.nitrogen.shownPlanets.add(Planets.serpulo);

        for (Item item : Vars.content.items()) {
            if (item.shownPlanets.contains(Planets.serpulo)) {
                item.shownPlanets.add(sfplanet);
                item.postInit();
                //Log.info(item.name + "加入泰伯利亚");
            }
        }

        for (Liquid liquid : Vars.content.liquids()) {
            if (liquid.shownPlanets.contains(Planets.serpulo)) {
                liquid.shownPlanets.add(sfplanet);
                liquid.postInit();
                //Log.info(liquid.name + "加入泰伯利亚");
            }
        }

        for (UnitType units : Vars.content.units()) {
            if (units.shownPlanets.contains(Planets.serpulo)) {
                units.shownPlanets.add(sfplanet);
                units.postInit();
                //Log.info(units.name + "加入泰伯利亚");
            }
        }

        Field[] units = SFUnitTypes.class.getFields();
        int unitCount = 0;
        for (var field : units) {
            Object obj = null;
            try {
                obj = field.get(units);
                if (obj instanceof UnitType) {
                    UnitType unitType = (UnitType) obj;
                    unitType.shownPlanets.add(sfplanet);
                    unitType.postInit();
                    unitCount++;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }//Log.info(unitCount + "个火力单位加到泰伯利亚");

        Seq<Planet> expSun = Vars.content.planets().copy().removeAll(p -> p == Planets.sun);
        for (Block b : Vars.content.blocks()) {
            if (b.requirements.length == 0) {
                b.shownPlanets.addAll(expSun);//分配到有这个物品的星球
            } else {
                b.shownPlanets.clear();//没查到就取消分配
            }
            b.postInit();
        }
    }
}