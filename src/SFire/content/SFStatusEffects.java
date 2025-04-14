package SFire.content;

import arc.graphics.*;
import arc.math.Angles;
import arc.math.Mathf;
import arc.net.dns.SRVRecord;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.effect.WrapEffect;
import mindustry.graphics.*;
import arc.math.Interp;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.type.StatusEffect;

import static mindustry.Vars.*;
import static mindustry.content.StatusEffects.*;

public class SFStatusEffects {
    public static StatusEffect
            repair, repairX, disRepair, scrambled, strengthen, negative, postive,
            magnStrif, marked, acidded, inBreak, breakdown, echoFlame, overLoad,
            stormed, shattered, overFreezing, chemicalFlame, fullFire,
            skewed, charging;

    public static void load() {
        repair = new StatusEffect("repair") {{
            color = Pal.heal;
            damage = -4f;
            healthMultiplier = 1.15f;
            effectChance = 0.3f;
            effect = new ParticleEffect() {{
                particles = 1;
                spin = 6;
                region = "sfire-mod-triangle";
                baseLength = 30;
                length = -30;
                lifetime = 30;
                interp = Interp.pow3Out;
                sizeInterp = Interp.pow3In;
                colorFrom = colorTo = Pal.heal;
            }};
            init(() -> {
                opposite(burning, sapped, disRepair);
            });
        }};
        repairX = new StatusEffect("repair-X") {{
            color = Pal.heal;
            damage = -18f;
            speedMultiplier = 0.92f;
            effectChance = 1f;
            effect = new ParticleEffect() {{
                particles = 1;
                line = true;
                lenFrom = 12;
                lenTo = 0;
                strokeFrom = 2;
                strokeTo = 0;
                baseLength = 50;
                length = -50;
                lifetime = 30;
                interp = Interp.fastSlow;
                sizeInterp = Interp.pow3In;
                colorFrom = colorTo = Pal.heal;
            }};
            init(() -> opposite(burning, sapped, melting, tarred, disRepair, scrambled));
        }};
        disRepair = new StatusEffect("dismantle") {{
            color = Pal.heal;
            damage = 3;
            speedMultiplier = 0.8f;
            effectChance = 0.1f;
            effect = new ParticleEffect() {{
                particles = 6;
                spin = 6;
                region = "sfire-mod-triangle";
                sizeFrom = 2;
                length = 35;
                lifetime = 10;
                interp = Interp.fastSlow;
                colorFrom = Pal.heal;
            }};
            init(() -> opposite(repair, repairX));
        }};
        scrambled = new StatusEffect("scrambled") {{
            color = Pal.heal;
            speedMultiplier = 0.3f;
            reloadMultiplier = 0.5f;
            effectChance = 0.4f;
            effect = new ParticleEffect() {{
                particles = 5;
                line = true;
                lenFrom = 8;
                strokeFrom = 1;
                length = 10;
                lifetime = 20;
                sizeInterp = Interp.pow3Out;
                interp = Interp.fastSlow;
                colorTo = colorFrom = Pal.heal;
            }};
            init(() -> opposite(repairX));
        }};
        strengthen = new StatusEffect("strengthen") {{
            color = Pal.heal;
            damageMultiplier = 1.52f;
            speedMultiplier = 1.3f;
            reloadMultiplier = 1.3f;
            effectChance = 0.4f;
            effect = new ParticleEffect() {{
                particles = 2;
                region = "sfire-mod-triangle";
                baseLength = 10;
                length = 25;
                lifetime = 15;
                sizeFrom = 1.8f;
                colorFrom = colorTo = Pal.heal;
            }};
        }};

        negative = new StatusEffect("negative-charge") {{
            color = Items.lead.color;
            speedMultiplier = 0.75f;
            healthMultiplier = 1.2f;
            effectChance = 0.8f;
            transitionDamage = 18;
            effect = new ParticleEffect() {{
                particles = 2;
                baseLength = 16;
                length = -16;
                sizeFrom = 1;
                lifetime = 10;
                colorFrom = colorTo = Color.valueOf("AB99D3");
            }};
            init(() -> {
                opposite(burning, melting, breakdown, magnStrif);
                affinity(postive, (unit, result, time) -> {
                    float pierceFraction = 0.5f;
                    unit.damagePierce(transitionDamage * pierceFraction);
                    unit.damage(transitionDamage * (1f - pierceFraction));
                });
            });
        }};
        postive = new StatusEffect("postive-charge") {{
            color = Items.copper.color;
            damage = 0.33f;
            speedMultiplier = 1.2f;
            healthMultiplier = 0.75f;
            transitionDamage = 18;
            effectChance = 0.8f;
            effect = new ParticleEffect() {{
                particles = 2;
                line = true;
                lenFrom = 8;
                strokeFrom = 1;
                length = 16;
                lifetime = 10;
                colorFrom = colorTo = Color.valueOf("EAC2A9");
            }};
            init(() -> opposite(burning, melting, breakdown, magnStrif));
        }};
        magnStrif = new StatusEffect("magnetic-strif") {{
            color = Color.gray;
            speedMultiplier = 0.85f;
            reloadMultiplier = 0.9f;
            dragMultiplier = 3.5f;
            effectChance = 0.05f;
            effect = new ParticleEffect() {{
                line = true;
                particles = 3;
                baseLength = 0;
                length = 16;
                lifetime = 80;
                interp = Interp.pow3Out;
                sizeInterp = Interp.pow5In;
                colorFrom = Color.valueOf("FF8C00");
                colorTo = Color.valueOf("d0bae6");
            }};
        }};

        marked = new StatusEffect("marked") {{
            color = SFColor.disc;
            healthMultiplier = 0.6f;
            speedMultiplier = 1.5f;
            reloadMultiplier = 0.5f;
            effectChance = 0.6f;
            effect = new WaveEffect() {{
                lifetime = 30;
                strokeFrom = 2;
                strokeTo = 0;
                sizeFrom = 10f;
                sizeTo = 30;
                colorFrom = SFColor.disc;
                colorTo = SFColor.discDark;
            }};
        }};
        acidded = new StatusEffect("acidded") {{
            damage = 1.88f;
            speedMultiplier = 0.98f;
            healthMultiplier = 0.85f;
            effectChance = 0.6f;
            effect = new ParticleEffect() {{
                particles = 3;
                baseLength = 0;
                length = 16;
                lifetime = 250;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow10Out;
                sizeFrom = 1;
                sizeTo = 6;
                colorFrom = Color.valueOf("a0b46e");
                colorTo = Color.valueOf("a0b46e00");
            }};
        }};
        inBreak = new StatusEffect("inside-break") {{
            color = Color.valueOf("666484");
            healthMultiplier = 0.9f;
            reloadMultiplier = 0.9f;
            effectChance = 0;
            effect = null;
            permanent = true;
        }};
        breakdown = new StatusEffect("breakdown") {{
            color = SFColor.energyYellow;
            healthMultiplier = 0.95f;
            speedMultiplier = 0.95f;
            dragMultiplier = 1.15f;
            effectChance = 0.1f;
            effect = new MultiEffect(
                    new ParticleEffect() {{
                        particles = 6;
                        length = 25;
                        lifetime = 10;
                        sizeFrom = 2;
                        colorFrom = colorTo = Color.valueOf("FEEBB3");
                    }},
                    new ParticleEffect() {{
                        particles = 2;
                        line = true;
                        lenFrom = 10;
                        lenTo = 0;
                        strokeFrom = 2;
                        length = 23;
                        lifetime = 10;
                        interp = Interp.slowFast;
                        color = Color.valueOf("FEEBB3");
                    }}
            );
            transitionDamage = 1000;
            init(() -> {
                affinity(melting, (unit, result, time) -> {
                    unit.damagePierce(8f);
                    breakdown.effect.at(unit.x + Mathf.range(unit.bounds() / 2f), unit.y + Mathf.range(unit.bounds() / 2f));
                    result.set(breakdown, Math.min(time + result.time, 200f));
                });
            });
        }};
        echoFlame = new StatusEffect("echo-flame") {{
            color = SFColor.plastLight;
            damage = 120;
            dragMultiplier = 1.8f;
            effectChance = 1;
            effect = new MultiEffect(
                    new ParticleEffect() {{
                        particles = 1;
                        baseLength = 25;
                        length = 35;
                        lifetime = 60;
                        interp = Interp.fastSlow;
                        sizeInterp = Interp.pow3In;
                        region = "sfire-mod-star";
                        sizeFrom = 16;
                        colorFrom = colorTo = SFColor.plastLight;
                    }},
                    new ParticleEffect() {{
                        particles = 3;
                        length = 125;
                        lifetime = 30;
                        interp = Interp.fastSlow;
                        sizeInterp = Interp.pow3In;
                        region = "sfire-mod-star";
                        sizeFrom = 9;
                        colorFrom = SFColor.plastLight.cpy().a(0.05f);
                        colorTo = SFColor.plastLight;
                    }}
            );
            transitionDamage = 1000;
            init(() -> {
                affinity(breakdown, (unit, result, time) -> {
                    float transitionDamageMultiplier = 1.5f;
                    new WrapEffect() {{
                        effect = Fx.dynamicSpikes;
                        color = SFColor.plastLight;
                        rotation = 60;
                    }}.at(unit.x, unit.y);
                    unit.damagePierce(transitionDamage * transitionDamageMultiplier);
                    unit.damage(transitionDamage * transitionDamageMultiplier);
                    result.set(echoFlame, Math.min(time + result.time, 10f));
                });
            });
        }};
        overLoad = new StatusEffect("over-load") {{
            color = SFColor.energyYellow;
            damage = 20 / 3f;
            damageMultiplier = 25 / 3f;
            healthMultiplier = 0.54f;
            reloadMultiplier = 0.36f;
            effectChance = 0.08f;
            effect = new WaveEffect() {{
                sizeFrom = 8;
                sides = 6;
                sizeTo = 26;
                strokeFrom = 12;
                interp = Interp.circleOut;
                lifetime = 16;
                colorFrom = SFColor.energyYellow.cpy().a(0.08f);
                colorTo = SFColor.energyYellow;
                lightOpacity = 0.12f;
            }};
        }};

        stormed = new StatusEffect("stormed") {{
            color = SFColor.enemyRedLight;
            healthMultiplier = 1.2f;
            speedMultiplier = 1.25f;
            reloadMultiplier = 1.5f;
            damageMultiplier = 2;
            effectChance = 0.8f;
            effect = new ParticleEffect() {{
                particles = 3;
                baseLength = 26;
                length = -25;
                lifetime = 20;
                spin = 9;
                region = "sfire-mod-triangle";
                sizeFrom = 2.5f;
                colorFrom = Color.valueOf("FF7C7C");
                colorTo = Color.red;
            }};
        }};
        shattered = new StatusEffect("shattered") {{
            color = SFColor.enemyRedDark;
            healthMultiplier = 0.75f;
            speedMultiplier = 0.8f;
            damageMultiplier = 0.7f;
            damage = 9;
            effectChance = 0.8f;
            effect = new ParticleEffect() {{
                particles = 1;
                length = 65;
                lifetime = 10;
                spin = 12;
                region = "sfire-mod-triangle";
                sizeFrom = 4f;
                colorFrom = Color.red.lerp(Color.white, 0.5f);
            }};
        }};
        overFreezing = new StatusEffect("over-freezing") {{
            color = Liquids.cryofluid.color;
            healthMultiplier = 0.7f;
            speedMultiplier = 0.4f;
            dragMultiplier = 1.6f;
            reloadMultiplier = 0.45f;
            effect = new ParticleEffect() {{
                particles = 1;
                length = 8;
                spin = -0.05f;
                interp = Interp.fastSlow;
                region = "sfire-mod-triangle";
                sizeInterp = Interp.pow5In;
                sizeFrom = 5;
                colorFrom = colorTo = Liquids.cryofluid.color;
            }};
        }};
        chemicalFlame = new StatusEffect("chemical-flame") {{
            color = SFColor.enemyRedLight;
            damage = 16.6f;
            speedMultiplier = 0.95f;
            dragMultiplier = 1.2f;
            effectChance = 1;
            effect = new MultiEffect(
                    new ParticleEffect() {{
                        particles = 4;
                        length = 13;
                        lifetime = 19;
                        spin = -3;
                        sizeInterp = Interp.fastSlow;
                        sizeFrom = 1;
                        colorFrom = SFColor.enemyRedLight;
                        colorTo = SFColor.enemyRedLight.cpy().a(0.5f);
                    }},
                    new ParticleEffect() {{
                        particles = 3;
                        startDelay = 19;
                        length = 16;
                        lifetime = 60;
                        interp = Interp.pow10Out;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 3;
                        colorFrom = SFColor.enemyRedLight.cpy().a(0.8f);
                        colorTo = Color.valueOf("585858A8");
                    }}
            );
            transitionDamage = 60;
            init(() -> {
                affinity(tarred, (unit, result, time) -> {
                    unit.damagePierce(transitionDamage);
                    Fx.burning.at(unit.x + Mathf.range(unit.bounds() / 2f), unit.y + Mathf.range(unit.bounds() / 2f));
                    result.set(burning, Math.min(time + result.time, 300f));
                });
            });
        }};
        fullFire = new StatusEffect("saturation-attack") {{
            color = Color.gray;
            speedMultiplier = 0.125f;
            dragMultiplier = 2;
            damageMultiplier = 1.2f;
            reloadMultiplier = 5;
            effectChance = 0.3f;
            /*
            for(int i = 0; i < 4; i++){
                float angle = i*45;
                effect.add
            }
             */
            effect = new MultiEffect(
                    new WrapEffect() {{
                        effect = Fx.colorSparkBig;
                        color = Color.gray;
                        rotation = 45;
                    }},
                    new WrapEffect() {{
                        effect = Fx.colorSparkBig;
                        color = Color.gray;
                        rotation = 135;
                    }},
                    new WrapEffect() {{
                        effect = Fx.colorSparkBig;
                        color = Color.gray;
                        rotation = 225;
                    }},
                    new WrapEffect() {{
                        effect = Fx.colorSparkBig;
                        color = Color.gray;
                        rotation = 315;
                    }}
            );
            init(() -> {
                opposite(skewed, shattered, marked);
            });
        }};

        skewed = new StatusEffect("skewed") {{
            //show = false;
            color = SFColor.tayrLight;
            damageMultiplier = 0.5f;
            speedMultiplier = -1.1f;
            reloadMultiplier = 1.43f;
            effectChance = 0.2f;
            effect = new MultiEffect(
                    new WrapEffect() {{
                        effect = Fx.dynamicSpikes;
                        color = SFColor.tayrLight;
                        rotation = 22;
                    }},
                    new ParticleEffect() {{
                        particles = 16;
                        line = true;
                        strokeFrom = 1.5f;
                        lenFrom = 8;
                        interp = Interp.circleOut;
                        sizeInterp = Interp.pow3In;
                        length = 30;
                        colorFrom = colorTo = SFColor.tayrLight;
                    }}
            );
        }};
        charging = new StatusEffect("charging") {{
            //show = false;
            color = SFColor.tayrLight;
            damageMultiplier = 3.78f;
            healthMultiplier = 1.949f;
            reloadMultiplier = 0.22f;
            speedMultiplier = 0f;
            effectChance = 0.25f;
            effect = new WaveEffect() {{
                lifetime = 36;
                strokeFrom = 3;
                strokeTo = 8;
                interp = Interp.circleOut;
                sizeFrom = 220;
                sizeTo = 0;
                colorFrom = Color.valueOf("BFFFDBA0");
                colorTo = Color.valueOf("25C9AB");
            }};
        }};

    }
}

