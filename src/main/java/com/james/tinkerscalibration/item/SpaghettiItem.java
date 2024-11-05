package com.james.tinkerscalibration.item;

import com.james.tinkerscalibration.TinkersCalibration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.display.DurabilityDisplayModifierHook;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class SpaghettiItem extends ModifiableItem {
    public SpaghettiItem(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }
    private final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "spaghetti");

    public boolean isBarVisible(@NotNull ItemStack stack) {
        return ToolStack.from(stack).getPersistentData().getInt(KEY) < 100;
    }

    public int getBarColor(@NotNull ItemStack pStack) {
        float f = Math.max(0.0F, (float) ToolStack.from(pStack).getPersistentData().getInt(KEY) / 100);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);

    }

    public int getBarWidth(ItemStack pStack) {
        int uses = ToolStack.from(pStack).getPersistentData().getInt(KEY);
        return uses > 0 ? DurabilityDisplayModifierHook.getWidthFor(uses, 100) : 0;

    }

}
