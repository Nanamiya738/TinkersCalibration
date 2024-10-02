package com.james.tinkerscalibration.contents;

import com.james.tinkerscalibration.TinkersCalibration;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.TConstruct;

public class TinkersCalibrationFluids {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(TinkersCalibration.MODID);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenfiberglass = register("moltenfiberglass", 700);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenmangobberslime = register("moltenmangobberslime", 1500);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltengobber = register("moltengobber", 800);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltennethergobber = register("moltennethergobber", 950);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenfazelle = register("moltenfazelle", 1200);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenemperorslime = register("moltenemperorslime", 1150);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenlindsteel = register("moltenlindsteel", 1200);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltentitanium = register("moltentitanium", 1350);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenhymon = register("moltenhymon", 550);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenmandite = register("moltenmandite", 1450);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltencarminite = register("moltencarminite", 900);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenredmatter = register("moltenredmatter", 1500);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenendgobber = register("moltenendgobber", 1100);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltensoulgold = register("moltensoulgold", 1000);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenjazz = register("moltenjazz", 1300);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenrefinedquartz = register("moltenrefinedquartz", 1200);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenblazerite = register("moltenblazerite", 1250);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenenderite = register("moltenenderite", 1250);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltengolderite = register("moltengolderite", 1250);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenwitherite = register("moltenwitherite", 1250);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenphanterite = register("moltenphanterite", 1250);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenspiderite = register("moltenspiderite", 1250);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenprismarite = register("moltenprismarite", 1250);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenfeatherite = register("moltenfeatherite", 1250);
    //public static final FlowingFluidObject<ForgeFlowingFluid> moltenaltairium = register("moltenaltairium", 1050);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenhalleium = register("moltenhalleium", 1100);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenhothium = register("moltenhothium", 1070);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenmagiga = register("moltenmagiga", 1240);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltentonium = register("moltentonium", 1220);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenimmersedsilver = register("moltenimmersedsilver", 1090);
    public static final FlowingFluidObject<ForgeFlowingFluid> molteninertwitherium = register("molteninertwitherium", 1130);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenoraclium = register("moltenoraclium", 1440);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltensteamium = register("moltensteamium", 1100);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenstellarium = register("moltenstellarium", 1260);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenwitherium = register("moltenwitherium", 1490);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltengravity = register("moltengravity", 1450);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenhorizonite = register("moltenhorizonite", 790);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenmoonsteel = register("moltenmoonsteel", 870);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenmoonstone = register("moltenmoonstone", 800);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenichor = register("moltenichor", 1000);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltentopaz = register("moltentopaz", 700);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenicelandspar = register("moltenicelandspar", 800);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenlizanite = register("moltenlizanite", 780);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenprehnite = register("moltenprehnite", 690);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltendarkmatter = register("moltendarkmatter", 1400);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenalumite = register("moltenalumite", 925);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltencorundum = register("moltencorundum", 925);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenspinel = register("moltenspinel", 905);
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenvibratingcrystal = register("moltenvibratingcrystal", 785);
    //public static final FlowingFluidObject<ForgeFlowingFluid> moltenlavacrystal = register("moltenlavacrystal", 900);
    public static final FlowingFluidObject<ForgeFlowingFluid> dragonbreath = register("dragonbreath", 2000);
    private static FluidType.Properties hot(String name) {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000)
                .descriptionId(TConstruct.makeDescriptionId("fluid", name))
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA);
    }
    private static FluidType.Properties cool(String name) {
        return cool().descriptionId(TConstruct.makeDescriptionId("fluid", name))
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);
    }
    private static FluidType.Properties cool() {
        return FluidType.Properties.create()
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);
    }
    private static FlowingFluidObject<ForgeFlowingFluid> register(String name, int temp) {
        return FLUIDS.register(name).type(hot(name).temperature(temp).lightLevel(12)).block(Material.LAVA, 12).bucket().flowing();
    }
}
