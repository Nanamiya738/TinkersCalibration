package com.james.tinkerscalibration.contents;

import com.james.tinkerscalibration.group.ModGroup;
import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.TinkersCalibrationToolDefinition;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class TinkersCalibrationItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersCalibration.MODID);
    private static final ItemDeferredRegisterExtension ITEME = new ItemDeferredRegisterExtension(TinkersCalibration.MODID);
    public static BlockItem registerItemBlock(Block block) {
        return new BlockItem(block, new Item.Properties().tab(ModGroup.itemGroup));
    }

    public static RegistryObject<Item> mangobberslime_ingot = ITEMS.register("mangobberslime_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> lindsteel_ingot = ITEMS.register("lindsteel_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> fazelle_ingot = ITEMS.register("fazelle_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> titanium_ingot = ITEMS.register("titanium_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> mandite_ingot = ITEMS.register("mandite_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> soulgold_ingot = ITEMS.register("soulgold_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> emperorslime_ingot = ITEMS.register("emperorslime_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> jazz_ingot = ITEMS.register("jazz_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> stellarium_ingot = ITEMS.register("stellarium_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> immersed_silver_ingot = ITEMS.register("immersed_silver_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> inert_witherium_ingot = ITEMS.register("inert_witherium_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> witherium_ingot = ITEMS.register("witherium_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> halleium_ingot = ITEMS.register("halleium_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> hothium_ingot = ITEMS.register("hothium_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> oraclium_ingot = ITEMS.register("oraclium_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> steamium_ingot = ITEMS.register("steamium_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> grain_ingot = ITEMS.register("grain_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> magiga_ingot = ITEMS.register("magiga_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> tonium_ingot = ITEMS.register("tonium_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> gravity_ingot = ITEMS.register("gravity_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> alumite_ingot = ITEMS.register("alumite_ingot", TinkersCalibrationItems::register);
    public static RegistryObject<Item> moonsteel_ingot = ITEMS.register("moonsteel_ingot", TinkersCalibrationItems::register);
    //public static RegistryObject<Item> brokenice = ITEMS.register("brokenice", TinkersCalibrationItems::register);
    public static RegistryObject<Item> corundum = ITEMS.register("corundum", TinkersCalibrationItems::register);
    public static RegistryObject<Item> nitre = ITEMS.register("nitre", TinkersCalibrationItems::register);
    public static RegistryObject<Item> spinel = ITEMS.register("spinel", TinkersCalibrationItems::register);
    public static RegistryObject<Item> lizanite = ITEMS.register("lizanite_crystal", TinkersCalibrationItems::register);
    public static RegistryObject<Item> tourmaline = ITEMS.register("tourmaline", TinkersCalibrationItems::register);
    //public static RegistryObject<Item> moonstone = ITEMS.register("moonstone", TinkersCalibrationItems::register);
    public static RegistryObject<Item> talcum = ITEMS.register("talcum", TinkersCalibrationItems::register);
    public static RegistryObject<Item> vibrating_crystal = ITEMS.register("vibrating_crystal", TinkersCalibrationItems::register);

    public static RegistryObject<Item> stellarium_nugget = ITEMS.register("stellarium_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> gravity_nugget = ITEMS.register("gravity_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> immersed_silver_nugget = ITEMS.register("immersed_silver_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> inert_witherium_nugget = ITEMS.register("inert_witherium_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> witherium_nugget = ITEMS.register("witherium_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> halleium_nugget = ITEMS.register("halleium_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> alumite_nugget = ITEMS.register("alumite_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> hothium_nugget = ITEMS.register("hothium_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> oraclium_nugget = ITEMS.register("oraclium_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> steamium_nugget = ITEMS.register("steamium_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> magiga_nugget = ITEMS.register("magiga_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> tonium_nugget = ITEMS.register("tonium_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> hymon = ITEMS.register("hymon", TinkersCalibrationItems::register);
    public static RegistryObject<Item> mangobberslime_nugget = ITEMS.register("mangobberslime_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> lindsteel_nugget = ITEMS.register("lindsteel_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> fazelle_nugget = ITEMS.register("fazelle_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> titanium_nugget = ITEMS.register("titanium_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> mandite_nugget = ITEMS.register("mandite_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> soulgold_nugget = ITEMS.register("soulgold_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> emperorslime_nugget = ITEMS.register("emperorslime_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> jazz_nugget = ITEMS.register("jazz_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> moonsteel_nugget = ITEMS.register("moonsteel_nugget", TinkersCalibrationItems::register);
    public static RegistryObject<Item> glass_silk = ITEMS.register("glass_silk", TinkersCalibrationItems::register);
    public static RegistryObject<Item> hymon_scrap = ITEMS.register("hymon_scrap", TinkersCalibrationItems::register);
    public static RegistryObject<Item> ocean_compound = ITEMS.register("ocean_compound", TinkersCalibrationItems::register);
    public static RegistryObject<Item> bamboo_steel = ITEMS.register("bamboo_steel", TinkersCalibrationItems::register);
    public static RegistryObject<Item> raw_titanium = ITEMS.register("raw_titanium", TinkersCalibrationItems::register);
    public static RegistryObject<Item> raw_stellarium = ITEMS.register("raw_stellarium", TinkersCalibrationItems::register);
    public static RegistryObject<Item> raw_immersed_silver = ITEMS.register("raw_immersed_silver", TinkersCalibrationItems::register);
    public static RegistryObject<Item> raw_inert_witherium = ITEMS.register("raw_inert_witherium", TinkersCalibrationItems::register);
    // public static RegistryObject<Item> raw_altairium = ITEMS.register("raw_altairium", TinkersCalibrationItems::register);
    public static RegistryObject<Item> raw_hothium = ITEMS.register("raw_hothium", TinkersCalibrationItems::register);
    public static RegistryObject<Item> raw_magiga = ITEMS.register("raw_magiga", TinkersCalibrationItems::register);
    public static RegistryObject<Item> raw_tonium = ITEMS.register("raw_tonium", TinkersCalibrationItems::register);
    public static RegistryObject<Item> refined_quartz = ITEMS.register("refinedquartz", TinkersCalibrationItems::register);
    public static RegistryObject<Item> mending_moss = ITEMS.register("mending_moss", TinkersCalibrationItems::register);
    public static RegistryObject<Item> moss_ball = ITEMS.register("moss_ball", TinkersCalibrationItems::register);
    public static RegistryObject<Item> breashell = ITEMS.register("breashell", TinkersCalibrationItems::register);
    public static RegistryObject<Item> emperorslime_block = ITEMS.register("emperorslime_block", () -> registerItemBlock(TinkersCalibrationBlocks.emperorslime_block.get()));
    public static RegistryObject<Item> lindsteel_block = ITEMS.register("lindsteel_block", () -> registerItemBlock(TinkersCalibrationBlocks.lindsteel_block.get()));
    public static RegistryObject<Item> fazelle_block = ITEMS.register("fazelle_block", () -> registerItemBlock(TinkersCalibrationBlocks.fazelle_block.get()));
    public static RegistryObject<Item> titanium_block = ITEMS.register("titanium_block", () -> registerItemBlock(TinkersCalibrationBlocks.titanium_block.get()));
    public static RegistryObject<Item> jazz_block = ITEMS.register("jazz_block", () -> registerItemBlock(TinkersCalibrationBlocks.jazz_block.get()));
    public static RegistryObject<Item> mandite_block = ITEMS.register("mandite_block", () -> registerItemBlock(TinkersCalibrationBlocks.mandite_block.get()));
    public static RegistryObject<Item> titanium_ore = ITEMS.register("titanium_ore", () -> registerItemBlock(TinkersCalibrationBlocks.titanium_ore.get()));
    public static RegistryObject<Item> deepslate_titanium_ore = ITEMS.register("deepslate_titanium_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_titanium_ore.get()));
    public static RegistryObject<Item> inert_witherium_ore = ITEMS.register("inert_witherium_ore", () -> registerItemBlock(TinkersCalibrationBlocks.inert_witherium_ore.get()));
    public static RegistryObject<Item> stellarium_ore = ITEMS.register("stellarium_ore", () -> registerItemBlock(TinkersCalibrationBlocks.stellarium_ore.get()));
    public static RegistryObject<Item> immersed_silver_ore = ITEMS.register("immersed_silver_ore", () -> registerItemBlock(TinkersCalibrationBlocks.immersed_silver_ore.get()));
    public static RegistryObject<Item> hothium_ore = ITEMS.register("hothium_ore", () -> registerItemBlock(TinkersCalibrationBlocks.hothium_ore.get()));
    public static RegistryObject<Item> magiga_ore = ITEMS.register("magiga_ore", () -> registerItemBlock(TinkersCalibrationBlocks.magiga_ore.get()));
    public static RegistryObject<Item> tonium_ore = ITEMS.register("tonium_ore", () -> registerItemBlock(TinkersCalibrationBlocks.tonium_ore.get()));
    public static RegistryObject<Item> deepslate_stellarium_ore = ITEMS.register("deepslate_stellarium_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_stellarium_ore.get()));
    public static RegistryObject<Item> deepslate_immersed_silver_ore = ITEMS.register("deepslate_immersed_silver_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_immersed_silver_ore.get()));
    public static RegistryObject<Item> deepslate_hothium_ore = ITEMS.register("deepslate_hothium_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_hothium_ore.get()));
    public static RegistryObject<Item> deepslate_magiga_ore = ITEMS.register("deepslate_magiga_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_magiga_ore.get()));
    public static RegistryObject<Item> deepslate_tonium_ore = ITEMS.register("deepslate_tonium_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_tonium_ore.get()));
    public static RegistryObject<Item> stellarium_block = ITEMS.register("stellarium_block", () -> registerItemBlock(TinkersCalibrationBlocks.stellarium_block.get()));
    public static RegistryObject<Item> immersed_silver_block = ITEMS.register("immersed_silver_block", () -> registerItemBlock(TinkersCalibrationBlocks.immersed_silver_block.get()));
    public static RegistryObject<Item> inert_witherium_block = ITEMS.register("inert_witherium_block", () -> registerItemBlock(TinkersCalibrationBlocks.inert_witherium_block.get()));
    public static RegistryObject<Item> halleium_block = ITEMS.register("halleium_block", () -> registerItemBlock(TinkersCalibrationBlocks.halleium_block.get()));
    public static RegistryObject<Item> hothium_block = ITEMS.register("hothium_block", () -> registerItemBlock(TinkersCalibrationBlocks.hothium_block.get()));
    public static RegistryObject<Item> steamium_block = ITEMS.register("steamium_block", () -> registerItemBlock(TinkersCalibrationBlocks.steamium_block.get()));
    public static RegistryObject<Item> magiga_block = ITEMS.register("magiga_block", () -> registerItemBlock(TinkersCalibrationBlocks.magiga_block.get()));
    public static RegistryObject<Item> tonium_block = ITEMS.register("tonium_block", () -> registerItemBlock(TinkersCalibrationBlocks.tonium_block.get()));
    public static RegistryObject<Item> oraclium_block = ITEMS.register("oraclium_block", () -> registerItemBlock(TinkersCalibrationBlocks.oraclium_block.get()));
    public static RegistryObject<Item> witherium_block = ITEMS.register("witherium_block", () -> registerItemBlock(TinkersCalibrationBlocks.witherium_block.get()));
    public static RegistryObject<Item> alumite_block = ITEMS.register("alumite_block", () -> registerItemBlock(TinkersCalibrationBlocks.alumite_block.get()));
    public static RegistryObject<Item> corundum_block = ITEMS.register("corundum_block", () -> registerItemBlock(TinkersCalibrationBlocks.corundum_block.get()));
    public static RegistryObject<Item> nitre_block = ITEMS.register("nitre_block", () -> registerItemBlock(TinkersCalibrationBlocks.nitre_block.get()));
    //public static RegistryObject<Item> pyrophyllite_block = ITEMS.register("pyrophyllite_block", () -> registerItemBlock(TinkersCalibrationBlocks.pyrophyllite_block.get()));
    public static RegistryObject<Item> spinel_block = ITEMS.register("spinel_block", () -> registerItemBlock(TinkersCalibrationBlocks.spinel_block.get()));
    public static RegistryObject<Item> talcum_block = ITEMS.register("talcum_block", () -> registerItemBlock(TinkersCalibrationBlocks.talcum_block.get()));
    public static RegistryObject<Item> tourmaline_block = ITEMS.register("tourmaline_block", () -> registerItemBlock(TinkersCalibrationBlocks.tourmaline_block.get()));
    //public static RegistryObject<Item> sunstone_block = ITEMS.register("sunstone_block", () -> registerItemBlock(TinkersCalibrationBlocks.sunstone_block.get()));
    //public static RegistryObject<Item> moonstone_block = ITEMS.register("moonstone_block", () -> registerItemBlock(TinkersCalibrationBlocks.moonstone_block.get()));
    public static RegistryObject<Item> vibrating_crystal_block = ITEMS.register("vibrating_crystal_block", () -> registerItemBlock(TinkersCalibrationBlocks.vibrating_crystal_block.get()));
    public static RegistryObject<Item> iceland_spar_block = ITEMS.register("iceland_spar_block", () -> registerItemBlock(TinkersCalibrationBlocks.iceland_spar_block.get()));
    public static RegistryObject<Item> topaz_block = ITEMS.register("topaz_block", () -> registerItemBlock(TinkersCalibrationBlocks.topaz_block.get()));
    public static RegistryObject<Item> lizanite_block = ITEMS.register("lizanite_block", () -> registerItemBlock(TinkersCalibrationBlocks.lizanite_block.get()));
    public static RegistryObject<Item> prehnite_block = ITEMS.register("prehnite_block", () -> registerItemBlock(TinkersCalibrationBlocks.prehnite_block.get()));

    public static RegistryObject<Item> corundum_ore = ITEMS.register("corundum_ore", () -> registerItemBlock(TinkersCalibrationBlocks.corundum_ore.get()));
    public static RegistryObject<Item> nitre_ore = ITEMS.register("nitre_ore", () -> registerItemBlock(TinkersCalibrationBlocks.nitre_ore.get()));
    //public static RegistryObject<Item> pyrophyllite_ore = ITEMS.register("pyrophyllite_ore", () -> registerItemBlock(TinkersCalibrationBlocks.pyrophyllite_ore.get()));
    public static RegistryObject<Item> spinel_ore = ITEMS.register("spinel_ore", () -> registerItemBlock(TinkersCalibrationBlocks.spinel_ore.get()));
    public static RegistryObject<Item> talcum_ore = ITEMS.register("talcum_ore", () -> registerItemBlock(TinkersCalibrationBlocks.talcum_ore.get()));
    public static RegistryObject<Item> tourmaline_ore = ITEMS.register("tourmaline_ore", () -> registerItemBlock(TinkersCalibrationBlocks.tourmaline_ore.get()));
    //public static RegistryObject<Item> sunstone_ore = ITEMS.register("sunstone_ore", () -> registerItemBlock(TinkersCalibrationBlocks.sunstone_ore.get()));
    //public static RegistryObject<Item> moonstone_ore = ITEMS.register("moonstone_ore", () -> registerItemBlock(TinkersCalibrationBlocks.moonstone_ore.get()));
    public static RegistryObject<Item> vibrating_crystal_ore = ITEMS.register("vibrating_crystal_ore", () -> registerItemBlock(TinkersCalibrationBlocks.vibrating_crystal_ore.get()));
    public static RegistryObject<Item> deepslate_corundum_ore = ITEMS.register("deepslate_corundum_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_corundum_ore.get()));
    public static RegistryObject<Item> deepslate_nitre_ore = ITEMS.register("deepslate_nitre_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_nitre_ore.get()));
    //public static RegistryObject<Item> deepslate_pyrophyllite_ore = ITEMS.register("deepslate_pyrophyllite_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_pyrophyllite_ore.get()));
    public static RegistryObject<Item> deepslate_spinel_ore = ITEMS.register("deepslate_spinel_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_spinel_ore.get()));
    public static RegistryObject<Item> deepslate_talcum_ore = ITEMS.register("deepslate_talcum_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_talcum_ore.get()));
    public static RegistryObject<Item> deepslate_tourmaline_ore = ITEMS.register("deepslate_tourmaline_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_tourmaline_ore.get()));
    //public static RegistryObject<Item> deepslate_sunstone_ore = ITEMS.register("deepslate_sunstone_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_sunstone_ore.get()));
    //public static RegistryObject<Item> deepslate_moonstone_ore = ITEMS.register("deepslate_moonstone_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_moonstone_ore.get()));
    public static RegistryObject<Item> deepslate_vibrating_crystal_ore = ITEMS.register("deepslate_vibrating_crystal_ore", () -> registerItemBlock(TinkersCalibrationBlocks.deepslate_vibrating_crystal_ore.get()));

    private static final Item.Properties ToolItem = new Item.Properties().stacksTo(1).tab(ModGroup.itemGroup);
    //public static final RegistryObject<ModifiableItem> GUN = ITEMS.register("gun", () -> new ModifiableItem(ToolItem, TinkersCalibrationToolDefinition.Gun));
    public static final RegistryObject<ModifiableItem> CUTLASS = ITEMS.register("cutlass", () -> new ModifiableItem(ToolItem, TinkersCalibrationToolDefinition.Cutlass));

    //

    // public static final RegistryObject<ToolPartItem> BARREL = ITEMS.register("barrel", () -> new ToolPartItem(ToolItem, GripMaterialStats.ID));
    // public static final RegistryObject<ToolPartItem> GUNSTOCK = ITEMS.register("gun_stock", () -> new ToolPartItem(ToolItem, GripMaterialStats.ID));
    //public static final RegistryObject<ToolPartItem> GUNGRIP = ITEMS.register("gun_grip", () -> new ToolPartItem(ToolItem, LimbMaterialStats.ID));
    //  public static final RegistryObject<ToolPartItem> GUNSIGHT = ITEMS.register("gun_sight", () -> new ToolPartItem(ToolItem, ExtraMaterialStats.ID));
    public static final RegistryObject<ToolPartItem> BENT_BLADE = ITEMS.register("bent_blade", () -> new ToolPartItem(ToolItem, HeadMaterialStats.ID));
    //
    public static RegistryObject<Item> bent_blade_cast = ITEMS.register("bent_blade_cast", TinkersCalibrationItems::register);
    public static RegistryObject<Item> bent_blade_red_sand_cast = ITEMS.register("bent_blade_red_sand_cast", TinkersCalibrationItems::register);
    public static RegistryObject<Item> bent_blade_sand_cast = ITEMS.register("bent_blade_sand_cast", TinkersCalibrationItems::register);
    public static RegistryObject<Item> queens_slime_reinforcement = ITEMS.register("queens_slime_reinforcement", TinkersCalibrationItems::register);

    public static Item register() {
        return new Item(new Item.Properties().tab(ModGroup.itemGroup));
    }

}
