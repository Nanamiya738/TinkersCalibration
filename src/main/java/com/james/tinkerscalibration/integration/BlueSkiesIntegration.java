package com.james.tinkerscalibration.integration;

import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.modifiers.*;
import com.james.tinkerscalibration.modifiers.armor.ArmorNobleModifier;
import com.james.tinkerscalibration.modifiers.armor.ArmorWellTrainedModifier;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.DynamicModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.james.tinkerscalibration.Utils.createMaterial;

public class BlueSkiesIntegration {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersCalibration.MODID);
    public static StaticModifier<Modifier> noble = MODIFIERS.register("noble", NobleModifier::new);
    public static DynamicModifier noble_real = MODIFIERS.registerDynamic("noble_real");
    public static StaticModifier<Modifier> accumulate = MODIFIERS.register("accumulate", AccumulateModifier::new);
    public static StaticModifier<Modifier> protogenesis = MODIFIERS.register("protogenesis", ProtogenesisModifier::new);
    public static StaticModifier<Modifier> grind = MODIFIERS.register("grind", GrindModifier::new);
    public static StaticModifier<Modifier> crystalaccuracy = MODIFIERS.register("crystalaccuracy", CrystalAccuModifier::new);
    public static StaticModifier<Modifier> dawn = MODIFIERS.register("dawn", DawnModifier::new);
    public static StaticModifier<Modifier> sweetheart = MODIFIERS.register("sweetheart", SweetHeartModifier::new);
    public static StaticModifier<Modifier> weatherbeaten = MODIFIERS.register("weatherbeaten", WeatherBeatenModifier::new);
    public static StaticModifier<Modifier> falsite = MODIFIERS.register("falsite", FalsiteModifier::new);
    public static StaticModifier<Modifier> dense = MODIFIERS.register("dense", DenseModifier::new);
    public static StaticModifier<Modifier> extremespeed = MODIFIERS.register("extremespeed", ExtremeSpeedModifier::new);
    public static StaticModifier<TreeWallModifier> treewall = MODIFIERS.register("treewall", TreeWallModifier::new);
    public static final MaterialId horizonite = createMaterial("horizonite");
    public static final MaterialId moonsteel = createMaterial("moonsteel");
    public static final MaterialId lunar = createMaterial("lunar");
    public static final MaterialId starlit = createMaterial("starlit");
    public static final MaterialId cherry = createMaterial("cherry");
    public static final MaterialId maple = createMaterial("maple");
    public static final MaterialId ventium = createMaterial("ventium");
    public static final MaterialId charoite = createMaterial("charoite");
    public static final MaterialId diopside = createMaterial("diopside");
    public static final MaterialId pyrope = createMaterial("pyrope");
    public BlueSkiesIntegration() {
    }
    public static void Init() {
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
