package SFire.content;

import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives.*;
import mindustry.type.ItemStack;

import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;
import static mindustry.content.UnitTypes.*;

import static SFire.content.SFBlocks.*;
import static SFire.content.SFSectorPresets.*;
import static SFire.content.SFUnitTypes.*;


public class SFTechTree {
    public static TechNode context = null;

    public static void load() {
        //SFPlanets.tiberia.techTree = nodeRoot("tiberia", frondCore, () -> {}

        // addToNode(XXX, () -> node(XXX));

        //turrets
        addToNode(arc, () -> {
            node(gaosi);
            node(bingfengbao);
            node(xianqu, () -> node(liebao, () -> node(qingning)));
        });
        addToNode(hail, () -> {
            node(huojian, () -> node(changqiang, () -> node(fangtian)));
            node(mengma);
        });
        addToNode(salvo, () -> {
            node(kuodao, () -> node(longjuan, () -> node(liemei)));
            node(mini, () -> {
                node(chuanyun, () -> node(cimai));
                node(cijian);
            });
        });
        addToNode(lancer, () -> {
            node(dianguang);
            node(dianji);
        });
        addToNode(parallax, () -> node(cuodao));
        addToNode(scorch, () -> node(longxi));
        addToNode(swarmer, () -> node(leizhenyu));
        addToNode(spectre, () -> node(yuanling));
        addToNode(tsunami, () -> node(woliu, () -> node(tieliu)));
        addToNode(meltdown, () -> {
            node(manyou,  () -> {
                node(guangyin, () -> node(fenqing));
                node(dingdaer, () -> node(poxiao));
            });
            //node(fengmang);
        });
        addToNode(foreshadow, () -> {
            node(chongchao);
            node(relang);
            node(sizhao, () -> node(kuosan, () ->  node(zhulin)));
        });

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
            node(airCollector, () -> node(lensAtomizer));
            node(nitrateMixer, () -> node(airCooler));
            node(fractionator);
        });
        addToNode(phaseWeaver, () -> {
            node(discPhaseKnitter);
            node(discPhaseWaver);
            node(phaseActiver);
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
        addToNode(forceProjector, () -> node(ironCurtain, () -> node(ironDome)));

        //transport + liquid
        addToNode(titaniumConveyor, () -> node(waveConveyor, () -> {
            node(waveJunction);
            node(waveBridge);
        }));
        addToNode(armoredConveyor, () -> node(rearmoredConveyor));
        addToNode(plastaniumConveyor, () -> node(silisteelConveyor));
        addToNode(phaseConveyor, () -> node(discConveyor));

        addToNode(impulsePump, () -> node(tidalPump));
        addToNode(pulseConduit, () -> node(silisteelConduit, () -> node(silesteelBridgeConduit)));
        addToNode(platedConduit, () -> node(reArmoredConduit));
        addToNode(phaseConduit, () -> node(discConduit));
        addToNode(liquidTank, () -> node(silisteelTank));

        //power
        addToNode(batteryLarge, () -> node(armorBattery));
        addToNode(powerNodeLarge, () -> node(armorNode));
        addToNode(surgeTower, () -> node(discNodeTower));
        addToNode(steamGenerator, () -> node(coalPyrolyzer, () -> node(gasSmoker)));
        addToNode(thermalGenerator, () -> node(heatGenerator, () -> node(radiGenerator)));
        addToNode(thoriumReactor, () -> node(fermReactor));
        addToNode(impactReactor, () -> node(fissionReactor, () -> node(arcFissionReactor)));

        //production
        addToNode(pneumaticDrill, () -> node(energyDrill));
        addToNode(laserDrill, () -> node(heavyDrill));
        addToNode(blastDrill, () -> node(blastWell, () -> node(quantumOreExtractor)));
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
                node(tank1, () -> node(tank2, () -> node(tank3, () -> node(tank4, () -> node(tank5, () -> node(tank6))))))
        );
        addToNode(risso, () ->
                node(naval1, () -> node(naval2, () -> node(naval3, () -> node(naval4, () -> node(naval5, () -> node(naval6))))))
        );
        addToNode(multiplicativeReconstructor, () -> node(specFactory, () -> {
            node(farmer, () -> node(thunder));
            node(hammer, () -> node(flamer));
            node(banisher);
        }));
        addToNode(specFactory, () -> node(payloadConstrustor, () -> {
            node(tayrContainmentUnit);
            node(armorContainmentUnit);
            node(discContainmentUnit);
        }));
        addToNode(repairTurret, () -> node(nanoUnitRegener));

        //items + liquids
        addToNode(Items.coal, () -> {
            nodeProduce(SFItems.strontium, () -> {
            });
            nodeProduce(SFLiquids.mixGas, () -> {
            });
        });
        addToNode(Items.sand, () -> nodeProduce(SFItems.rareEarth, () -> {
            nodeProduce(SFItems.rubidium, () -> {
            });
            nodeProduce(SFItems.chromium, () -> {
                nodeProduce(SFItems.waveSteel, () ->{

                });
            });
        }));
        addToNode(Items.titanium, () -> nodeProduce(SFItems.siliSteel, () -> {
        }));
        addToNode(Items.silicon, () -> nodeProduce(SFItems.nanoCore, () -> {
            nodeProduce(SFItems.lens, () -> {
            });
            nodeProduce(SFLiquids.nanoFluid, () -> nodeProduce(SFLiquids.actiNanofluid, () -> {
            }));
        }));
        addToNode(Items.surgeAlloy, () -> nodeProduce(SFItems.tayrAlloy, () -> {
        }));
        addToNode(Items.thorium, () -> nodeProduce(SFItems.fermium, () -> nodeProduce(SFItems.leipAlloy, () -> {
        })));
        addToNode(Items.phaseFabric, () -> nodeProduce(SFItems.discFabric, () -> {
        }));
        addToNode(Items.blastCompound, () -> nodeProduce(SFItems.clusBomb, () -> {
        }));
        addToNode(Liquids.water, () -> nodeProduce(SFLiquids.nitrate, () -> {
        }));
        addToNode(Liquids.oil, () -> nodeProduce(SFLiquids.nitratedOil, () -> nodeProduce(SFLiquids.blastReagent, () -> {
        })));

        //sector
        addToNode(planetaryTerminal, () -> {
            node(frozenFront, Seq.with(
            new SectorComplete(planetaryTerminal),
            new Research(oct),
            new Research(corvus),
            new Research(reign),
            new Research(eclipse),
            new Research(tank5),
            new Research(air5),
            new Research(toxopid)
            ), () -> {

            });
        });


    }


    public static void addToNode(UnlockableContent p, Runnable c) {
        context = TechTree.all.find(t -> t.content == p);
        c.run();
    }

    public static void node(UnlockableContent content, Runnable children) {
        node(content, content.researchRequirements(), children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Runnable children) {
        node(content, requirements, null, children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children) {
        TechNode node = new TechNode(context, content, requirements);
        if (objectives != null) {
            node.objectives.addAll(objectives);
        }

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    public static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children) {
        node(content, content.researchRequirements(), objectives, children);
    }

    public static void node(UnlockableContent block) {
        node(block, () -> {
        });
    }

    public static void nodeProduce(UnlockableContent content, Seq<Objective> objectives, Runnable children) {
        node(content, content.researchRequirements(), objectives.add(new Produce(content)), children);
    }

    public static void nodeProduce(UnlockableContent content, Runnable children) {
        nodeProduce(content, new Seq<>(), children);
    }
}
