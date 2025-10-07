package SFire.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Vec2;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class SFFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static Effect TriExplosion(float liftime, int particles, float minsize, float size, float stroke, Color color) {
        return new Effect(liftime, size * 2, e -> {
            color(e.color);

            rand.setSeed(e.id);
            for (int i = 0; i < particles; i++) {
                float l = Mathf.randomSeedRange(e.id, size) + minsize;
                float rot = Mathf.randomSeedRange(e.id + i, 360f);
                //rv.trns(realRotation + rand.range(cone), l);

                Drawf.tri(e.x, e.y, e.foutpow() * stroke, l, rot);
                Drawf.light(e.x, e.y, 2 * size, color, 0.6f);
            }
        });
    }

    public static final Effect

    chemFlame = new Effect(35f, e -> {
        color(SFColor.strontiumLight, SFColor.strontiumDark, e.fin());

        randLenVectors(e.id, 3, 2f + e.fin() * 7f, (x, y) -> {
            Fill.square(e.x + x, e.y + y, 0.1f + e.fout() * 1.4f);
        });
    });
}