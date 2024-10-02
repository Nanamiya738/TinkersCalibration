package com.james.tinkerscalibration.integration;

import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.modifiers.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.james.tinkerscalibration.Utils.createMaterial;

public class MNAIntegration {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersCalibration.MODID);
    public static StaticModifier<Modifier> chimerite = MODIFIERS.register("chimerite", ChimeriteModifier::new);
    public static StaticModifier<Modifier> purify = MODIFIERS.register("purify", PurifyModifier::new);
    public MNAIntegration() {
    }
    public static void Init() {
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
