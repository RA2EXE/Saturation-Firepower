package SFire.content;

import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives.*;
import mindustry.type.ItemStack;

import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.craters;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;
import static mindustry.content.UnitTypes.*;

import static SFire.content.SFBlocks.*;
import static SFire.content.SFUnitTypes.*;


public class SFTechTree {
    public static TechNode context = null;

    public static void load() {
        //SFPlanets.tiberia.techTree = nodeRoot("tiberia", frondCore, () -> {}


        //crafting
        addToNode(pulverizer, () -> node(crusher));
        addToNode(sporePress, () -> node(sporeCompressor));
        addToNode(coalCentrifuge, () -> node(flywheelCentrifuge));
        addToNode(pyratiteMixer, () -> node(pyraBlender));
        addToNode(blastMixer, () -> {
            node(blastBlender);
            node(clusBlender, () -> node(blastReagentMixer, () -> node(clusMaker)));
        });
        addToNode(cryofluidMixer, () -> {
            node(cryoCentrifuge);
            node(nanoActivator);
        });
        addToNode(plastaniumCompressor, () -> node(plasMultiCompresser));
        addToNode(surgeSmelter, () -> {
            node(surgeTheSmelter);
            node(surgeElesmelter);
        });

        addToNode(siliconSmelter, () -> node(silisteelSmelter, () -> node(silisteelSmelterLarge, () -> {
            node(silisteelCrucible);
            node(silisteelSmelterHuge);
            node(nanoConstructor, () -> node(nanoPrinter));
        })));
        addToNode(multiPress, () -> node(wavesteelCompresseor, () -> node(wavesteelForger)));
        addToNode(disassembler, () -> {
            node(metalAnalyzer);
            node(nitrPrecipitator);
        });
        addToNode(kiln, () -> {
            node(airCollector);
            node(nitrateMixer);
            node(fractionator);
            node(lensAtomizer);
        });
        addToNode(phaseWeaver, () -> {
            node(discPhaseKnitter);
            node(discPhaseWaver);
        });
        addToNode(siliconCrucible, () -> node(chemicalSiSmelter, () -> node(blastSiSmelter)));
        addToNode(nitrateMixer, () -> {
            node(nitrReactor);
            node(nitrCentrifuge);
        });
        addToNode(surgeSmelter, () -> {
            node(tayriumSlelter, () -> node(leippiumSmelter));
            node(tayriumCrucible, () -> node(leippiumCrucible));
        });

        //wall + defense
        addToNode(titaniumWallLarge, () -> node(steelWall, () -> node(steelWallLarge)));
        addToNode(surgeWall, () -> node(influxWall, () -> node(influxWallLarge, () -> node(discWall))));
        addToNode(thoriumWall, () -> node(fermWall, () -> node(fermWallLarge, () -> node(leipWall, () -> node(leipWallLarge)))));
        addToNode(mendProjector, () -> node(nanoMendProjector, () -> node(nanoRegenProjector)));
        addToNode(shieldProjector, () -> node(ironCurtain, () -> node(ironDome)));
        //transport + liquid
        addToNode(titaniumConveyor, () -> node(waveConveyor, () -> {
            node(waveJunction);
            node(waveBridge);
        }));
        addToNode(armoredConveyor, () -> node(rearmoredConveyor));
        addToNode(plastaniumConveyor, () -> node(silisteelConveyor));
        addToNode(phaseConveyor, () -> node(discConveyor));

        addToNode(impulsePump, () -> node(tidalPump));
        addToNode(pulseConduit, () -> node(silisteelConduit));
        addToNode(platedConduit, () -> node(reArmoredConduit));
        addToNode(phaseConduit, () -> node(discConduit));
        addToNode(liquidTank, () -> node(silisteelTank));

        //power
        addToNode(batteryLarge, () -> node(armorBattery));
        addToNode(powerNodeLarge, () -> node(armorNode));
        addToNode(surgeTower, () -> node(discNodeTower));
        addToNode(steamGenerator, () -> node(coalPyrolyzer, () -> node(gasSmoker)));
        addToNode(thermalGenerator, () -> node(heatGenerator));
        addToNode(thoriumReactor, () -> node(fermReactor));
        addToNode(impactReactor, () -> node(fissionReactor, () -> node(arcFissionReactor)));

        //production
        addToNode(pneumaticDrill, () -> node(energyDrill));
        addToNode(laserDrill, () -> node(heavyDrill));
        addToNode(blastDrill, () -> node(blastWell));
        addToNode(Blocks.waterExtractor, () -> node(SFBlocks.waterExtractor));
        addToNode(oilExtractor, () -> node(oilPressurePump));
        addToNode(cultivator, () -> node(sporeCultivator));

        //storage
        addToNode(coreNucleus, () -> node(industryCore, () -> node(finalCommandCenter)));
        addToNode(unloader, () -> node(hyperUnloader));
        addToNode(vault, () -> node(molecularDatabase));

        //units + payloads
        addToNode(tetrativeReconstructor, () -> node(pentativeReconstrustor));
        addToNode(reign, () -> node(liXian));
        addToNode(corvus, () -> node(diXing));
        addToNode(toxopid, () -> node(panLong));
        addToNode(oct, () -> node(guangHan));
        addToNode(eclipse, () -> node(yuHui));
        addToNode(omura, () -> node(tengWang));
        addToNode(navanax, () -> node(luoHan));
        addToNode(flare, () ->
                node(air1, () -> node(air2, () -> node(air3, () -> node(air4, () -> node(air5, () -> node(air6))))))
        );
        addToNode(dagger, () ->
                node(tank1, () -> node (tank2, () -> node(tank3, () -> node(tank4, () -> node(tank5, () -> node(tank6))))))
        );
        addToNode(risso, () ->
                node(naval1, () -> node (naval2, () -> node(naval3, () -> node(naval4, () -> node(naval5, () -> node(naval6))))))
        );
        addToNode(multiplicativeReconstructor, () -> node(specFactory, () ->{
            node(farmer);
            node(flamer);
            node(thunder);
            node(banisher);
            node(hammer);
        }));
        addToNode(specFactory, () -> node(payloadConstrustor, () -> {
            node(tayrContainmentUnit);
            node(armorContainmentUnit);
            node(discContainmentUnit);
        }));
        addToNode(unitRepairTower, () -> node(nanoUnitRegener));

        //items + liquids
        node(Items.coal, () -> {
            node(SFItems.strontium, () -> {});
            node(SFLiquids.mixGas, () -> {});
        });
        node(Items.sand, () -> node(SFItems.rareEarth, () ->{
            node(SFItems.rubidium, () -> {});
            node(SFItems.chromium, () -> {});
        }));
        node(Items.titanium, () -> node(SFItems.siliSteel, () -> {}));
        node(Items.silicon, () -> node(SFItems.nanoCore, () -> {
            node(SFItems.lens, () -> {});
            node(SFLiquids.nanoFluid, () -> node(SFLiquids.actiNanofluid, () -> {}));
        }));
        node(Items.surgeAlloy, () -> node(SFItems.tayrAlloy, () -> {}));
        node(Items.thorium, () -> node(SFItems.fermium, () -> node(SFItems.leipAlloy, () -> {})));
        node(Items.phaseFabric, () -> node(SFItems.discFabric, () -> {}));
        node(Items.blastCompound, () -> node(SFItems.clusBomb, () -> {}));
        node(Liquids.water, () -> node(SFLiquids.nitrate, () -> {}));
        node(Liquids.oil, () -> node(SFLiquids.nitratedOil, () -> node(SFLiquids.blastReagent, () ->{})));




    }

    //虽然看不懂，但是能用，GUIY这么写一定有他的深意......能run的东西就别乱动了
    public static void addToNode(UnlockableContent p, Runnable c) {
        context = TechTree.all.find(t -> t.content == p);
        c.run();
    }

    public static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null){
            node.objectives.addAll(objectives);
        }

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    public static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    public static void node(UnlockableContent block){
        node(block, () -> {});
    }

    public static void nodeProduce(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.add(new Produce(content)), children);
    }

    public static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, new Seq<>(), children);
    }
}
