package SFire.expand.blocks;

import arc.graphics.*;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;

public class ExplodeWall extends Block {
    public int timerCheck = timers ++;
    public float checkInterval = 8f;

    public float range = 60;
    public float expDamage = 300;
    public float shake = size * 2f;
    public float expLimit = 0.1f;
    public float buildingDamageMultiplier = 0.25f;

    public Effect hitEffect = Fx.hitSquaresColor;
    public Effect waveEffect = new Effect(20, e -> {
        color(e.color);
        stroke(e.fout() * 1.8f * size);
        Lines.circle(e.x, e.y, e.finpow() * e.rotation);
        randLenVectors(e.id + 1, 8, 1f + 23f * e.finpow(), (x, y) ->
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 3f));
    }),
    destroyEffect = Fx.massiveExplosion;

    public Color waveColor = Pal.blastAmmoBack;

    public ExplodeWall(String name) {
        super(name);
        update = true;
        solid = true;

        destructible = true;
        canOverdrive = false;
        drawDisabled = false;
        group = BlockGroup.walls;
        buildCostMultiplier = 4f;
        crushDamageMultiplier = 5f;
        priority = TargetPriority.wall;
    }

    @Override
    public void setStats() {
        super.setStats();

        stats.add(Stat.damage, expDamage, StatUnit.none);
        stats.add(Stat.damageMultiplier, buildingDamageMultiplier, StatUnit.none);
        stats.add(Stat.range, range / tilesize, StatUnit.blocks);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, waveColor);
    }


    public class ExplodeWallBuild extends Building {
        public Seq<Bullet> targets = new Seq<>();

        public void updateTile() {
            if (health < maxHealth * expLimit && timer(timerCheck, checkInterval)) {
                targets.clear();
                Groups.bullet.intersect(x - range, y - range, range * 2, range * 2, b -> {
                    if (b.team != team && b.type.hittable) {
                        targets.add(b);
                    }
                });

                if (targets.size > 0) {
                    waveEffect.at(x, y, range, waveColor);
                    Effect.shake(shake, shake, this);
                    float waveDamage = Math.min(expDamage, expDamage * 25 / targets.size);

                    for (var target : targets) {
                        if (target.damage > waveDamage) {
                            target.damage -= waveDamage;
                        } else {
                            target.remove();
                        }
                        hitEffect.at(target.x, target.y, waveColor);
                    }
                    kill();
                }
            }
        }


        @Override
        public void onDestroyed() {
            super.onDestroyed();

            float explosionDamage = expDamage * buildingDamageMultiplier;
            Damage.damage(x, y, range, explosionDamage);
            destroyEffect.at(x,y,(size+1) * tilesize);
        }


        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, range, waveColor);
        }

    }

}
