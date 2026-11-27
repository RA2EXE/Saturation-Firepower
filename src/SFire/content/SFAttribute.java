package SFire.content;
import mindustry.world.meta.Attribute;

public class SFAttribute {
    public static Attribute
            radioactivity, gas, scrap;
    public static void load() {
        radioactivity = Attribute.add("radioactivity");
        gas = Attribute.add("gas");
        scrap = Attribute.add("scrap");
    }
}