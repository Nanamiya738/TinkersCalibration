package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.integration.BlueSkiesIntegration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import javax.annotation.Nonnull;

import static com.legacy.blue_skies.items.util.IFalsiteItem.getMaxFalsiteUses;

public class ProtogenesisModifier extends NoLevelsModifier implements InventoryTickModifierHook {
    private final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "falsite");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }
    @Override
    public void onInventoryTick(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull Level world, @Nonnull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if(!world.isClientSide && holder.tickCount % 10 == 0 && stack.getTag().contains("Falsite")) //it's very unlikely that an item doesn't have any tags, so just ignore the warning.
        {
            if(tool.getPersistentData().getInt(KEY) < stack.getTag().getInt("Falsite") || !tool.getPersistentData().contains(KEY, 3)) {
                tool.getPersistentData().putInt(KEY, Math.min(stack.getTag().getInt("Falsite"), getMaxFalsiteUses(stack)));
                stack.getTag().putInt("Falsite", 0);
                ToolStack toolstack = ToolStack.from(stack);
                if(ModifierUtil.getModifierLevel(stack, BlueSkiesIntegration.falsite.getId()) == 0)
                    toolstack.addModifier(BlueSkiesIntegration.falsite.getId(), 1);
            }
        }
    }

}
