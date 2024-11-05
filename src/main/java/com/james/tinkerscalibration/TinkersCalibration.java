package com.james.tinkerscalibration;

import com.james.tinkerscalibration.contents.*;
import com.james.tinkerscalibration.group.ModGroup;
import com.james.tinkerscalibration.hud.RangedDrawHud;
import com.james.tinkerscalibration.integration.BlueSkiesIntegration;
import com.james.tinkerscalibration.integration.MNAIntegration;
import com.james.tinkerscalibration.item.*;
import com.james.tinkerscalibration.hud.OvershieldHud;
import com.james.tinkerscalibration.library.TinkersCalibrationLootModifiers;
import com.james.tinkerscalibration.tiers.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(Utils.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersCalibration {
    public static final Logger logger = LogManager.getLogger("tinkerscalibration");
    public TinkersCalibration() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(TinkersCalibration::registerGUIOverlays);
        bus.addListener(this::setup);
        TinkersCalibrationFluids.FLUIDS.register(bus);
        TinkersCalibrationBlocks.BLOCKS.register(bus);
        TinkersCalibrationItems.ITEMS.register(bus);
        TinkersCalibrationConfig.init();
        Utils.MODIFIERS.register(bus);
        Utils.PARTICLE_TYPES.register(bus);
        TinkersCalibrationWorldFeatures.CONFIGURED_FEATURES.register(bus);
        TinkersCalibrationWorldFeatures.PLACED_FEATURES.register(bus);
        TinkersCalibrationWorldFeatures.BLOCKS.register(bus);
        if(ModList.get().isLoaded("tinkers_thinking")) {
            TinkersCalibrationLootModifiers.init(bus);
            logger.info("Found Tinkers' Thinking, spaghetti initializing……");
        }
        FiberGlass.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        HardWheatRod.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        WetSoftNoodles.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DryColdNoodles.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        HymonArrow.ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Utils.RECIPE_SERIALIZERS.register(bus);
        TinkersCalibrationArmorModifiers.Init();
    }
    @SubscribeEvent
    public static void registerGUIOverlays(RegisterGuiOverlaysEvent event)
    {
        event.registerAboveAll("shield", OvershieldHud.HUD_SHIELD);
    }
    private void setup(final FMLCommonSetupEvent event) {
        boolean pe = ModList.get().isLoaded("projecte");
        if (pe) {
            logger.info("Found ProjectE, integration initializing……");
            registerTier(RedMatterTier.instance, MODID, "redmatter");
            registerTier(DarkMatterTier.instance, MODID, "darkmatter");
        }
        boolean gb = ModList.get().isLoaded("gobber2");
        if(!gb) {
            registerTier(FakeGobberTier.instance, GOBBERID, "overworld_gobber");
            registerTier(FakeNetherGobberTier.instance, GOBBERID, "nether_gobber");
            registerTier(FakeEndGobberTier.instance, GOBBERID, "end_gobber");
        }
        boolean bs = ModList.get().isLoaded("blue_skies");
        if (bs) {
            BlueSkiesIntegration.Init();
            logger.info("Found Blue Skies, integration initializing……");
        }
        boolean mn = ModList.get().isLoaded("mna");
        if (mn) {
            MNAIntegration.Init();
            logger.info("Found Mana and Artifice, integration initializing……");
        }
        boolean tw = ModList.get().isLoaded("twilightforest");
        if (tw) {
            TinkersCalibrationArmorModifiers.InitT();
            logger.info("Found Twilight Forest, armor integration initializing……");
        }
        boolean un = ModList.get().isLoaded("upgradednetherite");
        if (un) {
            TinkersCalibrationArmorModifiers.InitN();
            logger.info("Found Upgraded Netherite, armor integration initializing……");
        }

        MinecraftForge.EVENT_BUS.register(new RangedDrawHud());
    }
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tinkerscalibration";
    public static final String GOBBERID = "gobber2";

    public void registerTier(Tier instance, String id, String name) {
        if (!TierSortingRegistry.isTierSorted(instance)) {
            if (!TierSortingRegistry.getSortedTiers().isEmpty()) {
                TierSortingRegistry.registerTier(instance, new ResourceLocation(id + ":" + name), List.of(TierSortingRegistry.getSortedTiers().get(TierSortingRegistry.getSortedTiers().size())), List.of());
            } else {
                TierSortingRegistry.registerTier(instance, new ResourceLocation(id + ":" + name), List.of(Tiers.NETHERITE), List.of());
            }
        }
    }

    public static Item register() {
        return new Item(new Item.Properties().tab(ModGroup.itemGroup));
    }

    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersCalibration.MODID);
    public static RegistryObject<Item> Hymon_Arrow = Items.register("hymon_arrow", TinkersCalibration::register);

    public static Logger getLogger() {
        return LOGGER;
    }

}