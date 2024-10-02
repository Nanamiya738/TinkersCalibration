package com.james.tinkerscalibration;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import slimeknights.tconstruct.common.config.Config;

public class TinkersCalibrationConfig {
    public static final CommonConfig COMMON;
    public static ForgeConfigSpec config;
    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TinkersCalibrationConfig.config);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }
    static {
        final Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = pair.getLeft();
        config = pair.getRight();
    }

    public static class CommonConfig {
        public final OreConfig titaniumOre;
        public final OreConfig hothiumOre;
        public final OreConfig immersedsilverOre;
        public final OreConfig magigaOre;
        public final OreConfig stellariumOre;
        public final OreConfig toniumOre;
        public final OreConfig inertwitheriumOre;
        public final OreConfig corundumOre;
        public final OreConfig vibratingcrystalOre;
        public final OreConfig talcumOre;
        public final OreConfig nitreOre;
        public final OreConfig tourmalineOre;
        public final OreConfig spinelOre;
        public final ForgeConfigSpec.BooleanValue icelandsparGeodes;
        public final ForgeConfigSpec.BooleanValue topazGeodes;
        public final ForgeConfigSpec.BooleanValue lizaniteGeodes;
        public final ForgeConfigSpec.BooleanValue cordieriteGeodes;
        public final ForgeConfigSpec.BooleanValue prehniteGeodes;
        CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Titanium Ore Worldgen").push("titanium_ore");
            titaniumOre = new TitaniumOreConfig(builder);
            builder.pop();
            builder.comment("Hothium Ore Worldgen").push("hothium_ore");
            hothiumOre = new HothiumOreConfig(builder);
            builder.pop();
            builder.comment("Immersed Silver Ore Worldgen").push("immersedsilver_ore");
            immersedsilverOre = new ImmersedSilverOreConfig(builder);
            builder.pop();
            builder.comment("Inert Witherium Ore Worldgen").push("inertwitherium_ore");
            inertwitheriumOre = new InertwitheriumOreConfig(builder);
            builder.pop();
            builder.comment("Stellarium Ore Worldgen").push("stellarium_ore");
            stellariumOre = new StellariumOreConfig(builder);
            builder.pop();
            builder.comment("Magiga Ore Worldgen").push("magiga_ore");
            magigaOre = new MagigaOreConfig(builder);
            builder.pop();
            builder.comment("Tonium Ore Worldgen").push("tonium_ore");
            toniumOre = new ToniumOreConfig(builder);
            builder.pop();
            builder.comment("Corundum Ore Worldgen").push("corundum_ore");
            corundumOre = new CorundumOreConfig(builder);
            builder.pop();
            builder.comment("Vibrating Crystal Ore Worldgen").push("vibrating_crystal_ore");
            vibratingcrystalOre = new VibratingOreConfig(builder);
            builder.pop();
            builder.comment("Talcum Ore Worldgen").push("talcum_ore");
            talcumOre = new TalcumOreConfig(builder);
            builder.pop();
            builder.comment("Spinel Ore Worldgen").push("spinel_ore");
            spinelOre = new SpinelOreConfig(builder);
            builder.pop();
            builder.comment("Nitre Ore Worldgen").push("nitre_ore");
            nitreOre = new NitreOreConfig(builder);
            builder.pop();
            builder.comment("Tourmaline Ore Worldgen").push("tourmaline_ore");
            tourmalineOre = new TourmalineOreConfig(builder);
            builder.pop();
            builder.comment("World generation, mainly geodes").push("worldgen");
            {
                this.icelandsparGeodes = builder
                        .comment("If true, iceland spar geodes generate in icy biomes.")
                        .define("iceland_spar", true);
                this.topazGeodes = builder
                        .comment("If true, topaz geodes generate at the bottom of the sea.")
                        .define("topaz", true);
                this.lizaniteGeodes = builder
                        .comment("If true, lizanite geodes generate in the overworld.")
                        .define("lizanite", true);
                this.cordieriteGeodes = builder
                        .comment("If true, cordierite geodes generate in the overworld.")
                        .define("cordierite", true);
                this.prehniteGeodes = builder
                        .comment("If true, prehnite geodes generate in the overworld.")
                        .define("prehnite", true);
                builder.pop();
            }
        }
    }

    public static class OreConfig {
        public ForgeConfigSpec.BooleanValue enabled;
        public ForgeConfigSpec.IntValue minY;
        public ForgeConfigSpec.IntValue maxY;
        public ForgeConfigSpec.IntValue count;
        public ForgeConfigSpec.IntValue size;

        public OreConfig(ForgeConfigSpec.Builder builder) {
        }

        public boolean isEnabled() {
            return enabled.get();
        }

        public int getCount() {
            return count.get();
        }

        public int getSize() {
            return size.get();
        }

        public int getMaxY() {
            return maxY.get();
        }

        public int getMinY() {
            return minY.get();
        }
    }

    public static class TitaniumOreConfig extends OreConfig {

        public TitaniumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Titanium ore").define("titaniumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class HothiumOreConfig extends OreConfig {

        public HothiumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Hothium ore").define("hothiumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 10, -10, 50);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 60, 40, 90);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 30, 10, 50);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 5, 1, 40);
        }
    }

    public static class ImmersedSilverOreConfig extends OreConfig {

        public ImmersedSilverOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable ImmersedSilver ore").define("immersedSilverOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 30, 10, 50);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 40, 20, 70);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class InertwitheriumOreConfig extends OreConfig {

        public InertwitheriumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Inertwitherium ore").define("inertwitheriumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 5, 0, 60);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 10, 2, 85);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 8, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 7);
        }
    }

    public static class MagigaOreConfig extends OreConfig {

        public MagigaOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Magiga ore").define("magigaOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class StellariumOreConfig extends OreConfig {

        public StellariumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Stellarium ore").define("stellariumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }
    public static class ToniumOreConfig extends OreConfig {

        public ToniumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Tonium ore").define("toniumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -60, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", -10, -60, 20);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }
    public static class CorundumOreConfig extends OreConfig {

        public CorundumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Corundum ore").define("corundumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -10, -30, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 10, 0, 30);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class TalcumOreConfig extends OreConfig {

        public TalcumOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Talcum ore").define("talcumOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 40, 20, 50);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 90, 60, 120);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 8, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 5, 1, 40);
        }
    }

    public static class SpinelOreConfig extends OreConfig {

        public SpinelOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Spinel ore").define("spinelOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -10, -30, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 10, 0, 30);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class TourmalineOreConfig extends OreConfig {

        public TourmalineOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Tourmaline ore").define("tourmalineOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -20, -40, 0);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 20, 0, 30);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }

    public static class NitreOreConfig extends OreConfig {

        public NitreOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Nitre ore").define("nitreOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", 30, 10, 50);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 70, 60, 90);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 15, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }
    public static class VibratingOreConfig extends OreConfig {

        public VibratingOreConfig(ForgeConfigSpec.Builder builder) {
            super(builder);
            this.enabled = builder.worldRestart().comment("Enable/Disable Vibrating Crystal ore").define("vibratingcrystalOreEnabled", true);
            this.minY = builder.comment("Min Y level").defineInRange("minY", -30, -50, -10);
            this.maxY = builder.comment("Max Y Level").defineInRange("maxY", 0, -10, 10);
            this.count = builder.comment("Ore vein count").defineInRange("veinCount", 20, 1, 40);
            this.size = builder.comment("Ore vein size").defineInRange("veinSize", 3, 1, 40);
        }
    }
}

