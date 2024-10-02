package com.james.tinkerscalibration;

import com.james.tinkerscalibration.modifiers.armor.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.function.Supplier;

public class TinkersCalibrationArmorModifiers {
    private static final ItemDeferredRegisterExtension ITEM = new ItemDeferredRegisterExtension(TinkersCalibration.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersCalibration.MODID);
    private static final Supplier<Item.Properties> ARMOR = () -> (new Item.Properties()).tab(TinkerTools.TAB_TOOLS);
    public static void Init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        ITEM.register(bus);
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static void InitT() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MODIFIERST.register(bus);
    }
    public static void InitN() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MODIFIERSN.register(bus);
    }
    public TinkersCalibrationArmorModifiers() {
    }
    private static final Item.Properties ToolItem = new Item.Properties().stacksTo(1).tab(TinkerToolParts.TAB_TOOL_PARTS);
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersCalibration.MODID);
    public static ModifierDeferredRegister MODIFIERST = ModifierDeferredRegister.create(TinkersCalibration.MODID);
    public static ModifierDeferredRegister MODIFIERSN = ModifierDeferredRegister.create(TinkersCalibration.MODID);
    public static StaticModifier<Modifier> sharp_like_glass = MODIFIERS.register("sharp_like_glass_armor", ArmorSharpLikeGlassModifier::new);
    public static StaticModifier<Modifier> overslime_army = MODIFIERS.register("overslime_army_armor", ArmorOverslimeArmyModifier::new);
    public static StaticModifier<Modifier> impregnable = MODIFIERS.register("impregnable_armor", ArmorImpregnableModifier::new);
    public static StaticModifier<Modifier> darkness = MODIFIERS.register("darkness_armor", ArmorDarknessModifier::new);
    public static StaticModifier<Modifier> igneous = MODIFIERS.register("igneous_armor", ArmorIgneousModifier::new);
    public static StaticModifier<Modifier> vengeance = MODIFIERS.register("vengeance_armor", ArmorVengeanceModifier::new);
    public static StaticModifier<Modifier> bamboo_growing = MODIFIERS.register("bamboo_growing_armor", ArmorBambooGrowingModifier::new);
    public static StaticModifier<Modifier> oracular = MODIFIERS.register("oracular_armor", ArmorOracularModifier::new);
    public static StaticModifier<Modifier> over_natural = MODIFIERS.register("over_natural_armor", ArmorOverNaturalModifier::new);
    public static StaticModifier<Modifier> switching = MODIFIERS.register("switching_armor", ArmorSwitchingModifier::new);
    public static StaticModifier<Modifier> blowing = MODIFIERS.register("blowing_armor", ArmorBlowingModifier::new);
    public static StaticModifier<Modifier> extreme_freezing = MODIFIERS.register("extreme_freezing_armor", ArmorExtremeFreezingModifier::new);
    public static StaticModifier<Modifier> respect = MODIFIERS.register("respect_armor", ArmorRespectModifier::new);
    public static StaticModifier<Modifier> dash = MODIFIERS.register("dash_armor", ArmorDashModifier::new);
    public static StaticModifier<Modifier> retribution = MODIFIERS.register("retribution_armor", ArmorRetributionModifier::new);
    public static StaticModifier<Modifier> bloodthirsty = MODIFIERS.register("bloodthirsty_armor", ArmorBloodThirstyModifier::new);
    public static StaticModifier<Modifier> familiar = MODIFIERS.register("familiar_armor", ArmorFamiliarModifier::new);
    public static StaticModifier<Modifier> purgatory = MODIFIERS.register("purgatory_armor", ArmorPurgatoryModifier::new);
    public static StaticModifier<Modifier> dominate = MODIFIERS.register("dominate_armor", ArmorDominateModifier::new);
    public static StaticModifier<Modifier> enderference = MODIFIERS.register("enderference_armor", ArmorEnderferenceModifier::new);
    public static StaticModifier<Modifier> hydrophilous = MODIFIERS.register("hydrophilous_armor", ArmorHydrophilousModifier::new);
    public static StaticModifier<Modifier> heavy = MODIFIERS.register("heavy_armor", ArmorHeavyModifier::new);
    public static StaticModifier<Modifier> incandescent = MODIFIERS.register("incandescent_armor", ArmorIncandescentModifier::new);
    public static StaticModifier<Modifier> levitate = MODIFIERS.register("levitate_armor", ArmorLevitateModifier::new);
    public static StaticModifier<Modifier> lion_heart = MODIFIERS.register("lion_heart_armor", ArmorLionHeartModifier::new);
    public static StaticModifier<Modifier> refined = MODIFIERS.register("refined_armor", ArmorRefinedModifier::new);
    public static StaticModifier<Modifier> subdue = MODIFIERS.register("subdue_armor", ArmorSubdueModifier::new);
    public static StaticModifier<Modifier> wither_flow = MODIFIERS.register("wither_flow_armor", ArmorWitherFlowModifier::new);
    public static StaticModifier<Modifier> compatible = MODIFIERS.register("compatible_armor", ArmorCompatibleModifier::new);
    public static StaticModifier<Modifier> twilit = MODIFIERST.register("twilit_armor", ArmorTwilitModifier::new);
    public static StaticModifier<Modifier> blazerite = MODIFIERSN.register("blazerite_armor", ArmorBlazeriteModifier::new);
    public static StaticModifier<Modifier> enderite = MODIFIERSN.register("enderite_armor", ArmorEnderiteModifier::new);
    public static StaticModifier<Modifier> prismarite = MODIFIERSN.register("prismarite_armor", ArmorPrismariteModifier::new);
    public static StaticModifier<Modifier> spiderite = MODIFIERSN.register("spiderite_armor", ArmorSpideriteModifier::new);
    public static StaticModifier<Modifier> witherite = MODIFIERSN.register("witherite_armor", ArmorWitheriteModifier::new);
    public static StaticModifier<Modifier> phanterite = MODIFIERSN.register("phanterite_armor", ArmorPhanteriteModifier::new);
    public static StaticModifier<Modifier> featherite = MODIFIERSN.register("featherite_armor", ArmorFeatheriteModifier::new);
    public static StaticModifier<Modifier> blacksmith = MODIFIERS.register("blacksmith_armor", ArmorBlacksmithModifier::new);
    public static StaticModifier<Modifier> tough = MODIFIERS.register("tough_armor", ArmorToughModifier::new);
    public static StaticModifier<Modifier> circulation = MODIFIERS.register("circulation_armor", ArmorCirculationModifier::new);
    public static StaticModifier<Modifier> benthamism = MODIFIERS.register("benthamism_armor", ArmorBenthamismModifier::new);
    public static StaticModifier<Modifier> welltrained = MODIFIERS.register("welltrained_armor", ArmorWellTrainedModifier::new);
    public static StaticModifier<Modifier> lifeinspire = MODIFIERS.register("lifeinspire_armor", ArmorLifeInspireModifier::new);
    public static StaticModifier<Modifier> hyper = MODIFIERS.register("hyper_armor", ArmorHyperModifier::new);
    public static StaticModifier<Modifier> dichroic = MODIFIERS.register("dichroic_armor", ArmorDichroicModifier::new);
    public static StaticModifier<Modifier> vibrating = MODIFIERS.register("vibrating_armor", ArmorVibratingModifier::new);
    public static StaticModifier<Modifier> soluble = MODIFIERS.register("soluble_armor", ArmorSolubleModifier::new);
    public static StaticModifier<Modifier> shaking = MODIFIERS.register("shaking_armor", ArmorShakingModifier::new);
    public static StaticModifier<Modifier> clustering = MODIFIERS.register("clustering_armor", ArmorClusteringModifier::new);
    public static StaticModifier<Modifier> peace_energetic = MODIFIERS.register("peace_energetic_armor", ArmorPeaceEnergeticModifier::new);
    public static StaticModifier<Modifier> pyro_electric = MODIFIERS.register("pyro_electric_armor", ArmorPyroElectricModifier::new);
    public static StaticModifier<Modifier> stamina_focusing = MODIFIERS.register("stamina_focusing_armor", ArmorStaminaFocusingModifier::new);
    public static StaticModifier<Modifier> maiming = MODIFIERS.register("maiming_armor", ArmorMaimingModifier::new);
    public static StaticModifier<Modifier> gorgeous = MODIFIERS.register("gorgeous_armor", ArmorGorgeousModifier::new);

}
