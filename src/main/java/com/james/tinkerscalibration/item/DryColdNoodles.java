package com.james.tinkerscalibration.item;

import com.james.tinkerscalibration.Utils;
import com.james.tinkerscalibration.group.ModGroup;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.item.TooltipItem;

public class DryColdNoodles extends TooltipItem {
    public DryColdNoodles() {
        super(new Properties().tab(ModGroup.itemGroup));
    }

    public class ItemRegistry {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
        public static final RegistryObject<Item> Dry_Cold_Noodles = ITEMS.register("dry_cold_noodles", DryColdNoodles::new);
    }
}


