package SFire.content;

import arc.graphics.*;
import arc.math.Interp;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.effect.ParticleEffect;
import mindustry.graphics.Pal;
import mindustry.type.*;

import static mindustry.content.Liquids.*;

public class SFLiquids {
    public static Liquid
    nanoFluid, nitratedOil, actiNanofluid,
    nitrate,blastReagent,mixGas,heatchangeroil;

    public static void load(){

        nanoFluid = new Liquid("nanofluid", Color.valueOf("7CF389")){{
            heatCapacity = 1.5f;
            viscosity = 0.3f;
            effect = SFStatusEffects.scrambled;
            lightColor = Color.valueOf("7CF389").a(0.3f);
        }};

        nitratedOil =  new Liquid("nitrated-oil", Color.valueOf("36312D")){{
            viscosity = 0.8f;
            flammability = 1.5f;
            explosiveness = 1.8f;
            effect = StatusEffects.tarred;
            canStayOn.addAll(water, cryofluid);
        }};

        actiNanofluid = new CellLiquid("actived-nanofluid", Color.valueOf("7FD489")){{
            moveThroughBlocks = true;
            blockReactive = true;
            capPuddles = true;
            coolant = false;
            canStayOn.addAll(water, oil, cryofluid, slag);
            effect = SFStatusEffects.disRepair;
            spreadTarget = nanoFluid;
            cells = 12;
            particleSpacing = 10;
            particleEffect = new ParticleEffect(){{
                particles = 5;
                length = 13;
                region = "sfire-mod-loz";
                sizeInterp = Interp.pow5In;
                sizeFrom = 1.1f;
                colorFrom = Color.valueOf("96E6A0");
                colorTo = Color.valueOf("62AE7F");
            }};
            lightColor = Color.valueOf("7FD489BF");
            viscosity = 0.1f;
            maxSpread = 1.5f;
            spreadConversion = 1.5f;
            spreadDamage = 0.375f;
            colorFrom = Color.valueOf("96E6A0");
            colorTo = Color.valueOf("62AE7F");
        }};

        nitrate = new Liquid("nitrate", Color.valueOf("daedb2")){{
            temperature = 0.7f;
            explosiveness = 0.2f;
            viscosity = 0.2f;
            effect = SFStatusEffects.acidded;
        }};

        blastReagent = new Liquid("blast-reagent", Color.valueOf("d97c7c")){{
            flammability = 0.75f;
            temperature = 0.5f;
            viscosity = 0.8f;
            explosiveness = 3f;
        }};

        mixGas = new Liquid("mix-gas", Color.valueOf("FFD0D0AA")){{
            flammability = 2f;
            explosiveness = 0.25f;
            gas = true;
        }};

        heatchangeroil = new Liquid("heat-changer", Color.valueOf("CC7B56")){{
            heatCapacity = 2.5f;
            viscosity = 0.7f;
            flammability = 0.07f;
            boilPoint = -1f;
            effect = StatusEffects.none;
        }};

    }
}
