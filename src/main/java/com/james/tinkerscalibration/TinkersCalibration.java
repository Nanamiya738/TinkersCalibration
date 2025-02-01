package com.james.tinkerscalibration;

import com.james.tinkerscalibration.contents.*;
import com.james.tinkerscalibration.hud.RangedDrawHud;
import com.james.tinkerscalibration.integration.BlueSkiesIntegration;
import com.james.tinkerscalibration.integration.MNAIntegration;
import com.james.tinkerscalibration.hud.OvershieldHud;
import com.james.tinkerscalibration.library.TinkersCalibrationLootModifiers;
import com.james.tinkerscalibration.tiers.*;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.world.data.WorldgenProvider;

import java.util.List;

@Mod(Utils.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersCalibration {
    public static final Logger logger = LogManager.getLogger("tinkerscalibration");
    public TinkersCalibration() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        TinkersCalibrationModule.initRegisters();
        TinkersCalibrationFluids.FLUIDS.register(bus);
        TinkersCalibrationBlocks.BLOCKS.register(bus);
        TinkersCalibrationItems.ITEMS.register(bus);
        TinkersCalibrationItems.CREATIVE_TABS.register(bus);
        bus.register(new TinkersCalibrationWorldFeatures());
        Utils.MODIFIERS.register(bus);
        Utils.PARTICLE_TYPES.register(bus);
        if(ModList.get().isLoaded("tinkers_thinking")) {
            TinkersCalibrationLootModifiers.init(bus);
            logger.info("Found Tinkers' Thinking, spaghetti initializing……");
        }
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
    }
    @SubscribeEvent
    static void gatherData(final GatherDataEvent event) {
        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        TinkersCalibrationWorldFeaturesProvider.register(registrySetBuilder);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
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

    public static Logger getLogger() {
        return LOGGER;
    }

}