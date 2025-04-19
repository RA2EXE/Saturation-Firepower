package SFire.content;
import mindustry.world.meta.Attribute;

public class SFAttribute {
    public static Attribute
            radioactivity;
    public static void load() {
        radioactivity = Attribute.add("radioactivity");
    }
}