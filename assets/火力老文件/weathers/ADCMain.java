package SFire;

import arc.files.Fi;
import arc.struct.Seq;
import arc.util.Log;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.serialization.Json;
import arc.util.serialization.Jval;
import mindustry.Vars;
import mindustry.content.Liquids;
import mindustry.content.Planets;
import mindustry.mod.Mods;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.type.Planet;
import mindustry.type.UnitType;
import mindustry.world.Block;

import java.util.Arrays;
import java.util.Locale;

import static mindustry.Vars.maxModSubtitleLength;

public class ADCMain {
    private static final Json json = new Json();

    public static String ADCRoot = "adc.json";

    public static void init() {
        boolean load = false;
        for (var mod : Vars.mods.list()) {
            if (mod != null && mod.meta != null) {
                if (!mod.enabled()) continue;
                String name = mod.meta.name;
                Fi metaFile = null;
                if (mod.root.child(ADCRoot).exists()) {
                    metaFile = mod.root.child(ADCRoot);
                }
                if (metaFile == null) {
                    Log.info("mod " + name + " has no adc.json, skip...");
                    continue;
                }
                Log.info("mod " + name + " load adc.json...");
                adcMeta meta = json.fromJson(adcMeta.class, Jval.read(metaFile.readString()).toString(Jval.Jformat.plain));
                if (meta.root.length <= 0) continue;
                for (adcRoot root : meta.root) {
                    if (root.planet == null) {
                        Log.err("where is you planet?");
                        continue;
                    }
                    Planet planetO = Vars.content.planet(root.planet);
                    Planet planetM = Vars.content.planet(name + "-" + root.planet);
                    Planet planet = planetO != null ? planetO : planetM;
                    if (planet == null) {
                        Log.err("can not find planet '" + name + "-" + root.planet + "' or '" + root.planet + "'");
                        continue;
                    }
                    if (root.items != null) {
                        int i = 0;
                        if (root.items.length > 0) {
                            for (String s : root.items) {
                                Item itemO = Vars.content.item(s);
                                Item itemM = Vars.content.item(name + "-" + s);
                                Item item = itemO != null ? itemO : itemM;
                                if (item == null) {
                                    Log.err("can not find item '" + name + "-" + s + "' or '" + s + "'");
                                    continue;
                                }
                                i++;
                                item.shownPlanets.add(planet);
                                item.postInit();
                                load = true;
                            }
                        }
                        Log.info("mod " + name + " adds " + i + " items to " + planet.localizedName);
                    } else Log.info("mod " + name + " adc.json has no items");
                    if (root.liquids != null) {
                        int i = 0;
                        if (root.liquids.length > 0) {
                            for (String s : root.liquids) {
                                Liquid liquidO = Vars.content.liquid(s);
                                Liquid liquidM = Vars.content.liquid(name + "-" + s);
                                Liquid liquid = liquidO != null ? liquidO : liquidM;
                                if (liquid == null) {
                                    Log.err("can not find liquid '" + name + "-" + s + "' or '" + s + "'");
                                    continue;
                                }
                                i++;
                                liquid.shownPlanets.add(planet);
                                liquid.postInit();
                            }
                        }
                        Log.info("mod " + name + " adds " + i + " liquids to " + planet.localizedName);
                    } else Log.info("mod " + name + " adc.json has no liquids");
                    if (root.units != null) {
                        int i = 0;
                        if (root.units.length > 0) {
                            for (String s : root.units) {
                                UnitType unitO = Vars.content.unit(s);
                                UnitType unitM = Vars.content.unit(name + "-" + s);
                                UnitType unit = unitO != null ? unitO : unitM;
                                if (unit == null) {
                                    Log.err("can not find unit '" + name + "-" + s + "' or '" + s + "'");
                                    continue;
                                }
                                i++;
                                unit.shownPlanets.add(planet);
                                unit.postInit();
                            }
                        }
                        Log.info("mod " + name + " adds " + i + " units to " + planet.localizedName);
                    } else Log.info("mod " + name + " adc.json has no units");
                }
            }
        }
        if (!load) return;
        Log.info("last progress...");
        Seq<Planet> expSun = Vars.content.planets().copy().removeAll(p -> p == Planets.sun);
        for (Block b : Vars.content.blocks()) {
            if (b.requirements.length == 0) b.shownPlanets.addAll(expSun);
            else b.shownPlanets.clear();
            b.postInit();
        }
    }

    private static class adcMeta {
        public adcRoot[] root;

        @Override
        public String toString() {
            return "adcMeta{" +
                    "root=" + Arrays.toString(root) +
                    '}';
        }
    }

    private static class adcRoot {
        public String planet;
        public String[] items;
        public String[] liquids;
        public String[] units;

        @Override
        public String toString() {
            return "adcRoot{" +
                    "planet='" + planet + '\'' +
                    ", items=" + Arrays.toString(items) +
                    '}';
        }
    }
}
