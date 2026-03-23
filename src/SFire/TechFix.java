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
        //找星球》找物品》设置shownPlanets
        /*if (root.items.length > 0) {物品列表长度
              for (String s : root.items) {
                   Item itemO = Vars.content.item(s);几个物品
                   Item itemM = Vars.content.item(name + "-" + s);
                   Item item = itemO != null ? itemO : itemM;是否走到头
                   if (item == null) {报错用
                       Log.err("can not find item '" + name + "-" + s + "' or '" + s + "'");
                       continue;
                   }报错用
                   i++;准备下一个
                   item.shownPlanets.add(planet);把这个加星球
                   item.postInit();
          load = true;
            }}*/

        Planet sfplanet = SFPlanets.tiberia;

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
        /*Field[] items = SFItems.class.getFields();
        int itemCount = 0;
        for (var field : items) {
            Object obj = null;
            try {
                obj = field.get(items);
                if (obj instanceof Item) {
                    Item item = (Item) obj;
                    item.shownPlanets.add(sfplanet);
                    item.postInit();
                    itemCount++;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }Log.info(itemCount + "个火力物品加到泰伯利亚");*/


        /*Field[] liquids = SFLiquids.class.getFields();
        int liquidCount = 0;
        for (var field : liquids) {
            Object obj = null;
            try {
                obj = field.get(liquids);
                if (obj instanceof Liquid) {
                    Liquid liquid = (Liquid) obj;
                    liquid.shownPlanets.add(sfplanet);
                    liquid.postInit();
                    liquidCount++;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }Log.info(liquidCount + "个火力液体加到泰伯利亚");*/


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
        }Log.info(unitCount + "个火力单位加到泰伯利亚");

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
/*
    public static void addItems(String... itemNames) {
        Planet planet = SFPlanets.tiberia;
        if (planet == null) {
            Log.err("SFire: Can not find planet 'tiberia'");
            return;
        }

        for (String s : itemNames) {
            Item item = Vars.content.item(s);
            if (item == null) {
                Log.err("SFire: Can not find item '" + s + "'");
                continue;
            }
            item.shownPlanets.add(planet);
            item.postInit();
        }
        Log.info("SFire: Added " + itemNames.length + " items to " + planet.localizedName);
    }

    public static void addLiquids(String... liquidNames) {
        if (liquidNames == null || liquidNames.length == 0) return;

        Planet planet = SFPlanets.tiberia;
        if (planet == null) {
            Log.err("SFire: Can not find planet 'tiberia'");
            return;
        }

        for (String s : liquidNames) {
            Liquid liquid = Vars.content.liquid(s);
            if (liquid == null) {
                Log.err("SFire: Can not find liquid '" + s + "'");
                continue;
            }
            liquid.shownPlanets.add(planet);
            liquid.postInit();
        }
        Log.info("SFire: Added " + liquidNames.length + " liquids to " + planet.localizedName);
    }*/
}