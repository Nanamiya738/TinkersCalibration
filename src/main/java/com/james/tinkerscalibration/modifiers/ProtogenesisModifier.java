package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.integration.BlueSkiesIntegration;
import com.legacy.blue_skies.capability.SkiesPlayer;
import com.legacy.blue_skies.registries.SkiesDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nonnull;

public class ProtogenesisModifier extends NoLevelsModifier implements InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }
    @Override
    public void onInventoryTick(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull Level world, @Nonnull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        int boriginal = 0, doriginal = 0;
        boolean ischanged = false;
        if (holder instanceof Player player) {
            SkiesPlayer skiesPlayer = (SkiesPlayer) SkiesPlayer.get(player);
            if (skiesPlayer != null) {
                if (player.level.dimension().equals(SkiesDimensions.everbrightKey()) || player.level.dimension().equals(SkiesDimensions.everdawnKey())){
                    if (!world.isClientSide && isSelected) {
                        if(skiesPlayer.getDawnProgression() <= 3 || skiesPlayer.getBrightProgression() <= 3) {
                            ischanged = true;
                            boriginal = skiesPlayer.getBrightProgression();
                            doriginal = skiesPlayer.getDawnProgression();
                            skiesPlayer.setBrightProgression((byte) 4);
                            skiesPlayer.setDawnProgression((byte) 4);
                        }
                    }
                    if (!world.isClientSide && !isSelected) {
                        if (ModifierUtil.getModifierLevel(holder.getMainHandItem(), BlueSkiesIntegration.protogenesis.getId()) == 0) {
                            skiesPlayer.setBrightProgression((byte) boriginal);
                            skiesPlayer.setDawnProgression((byte) doriginal);
                        }
                    }
                }
            }
        }
    }
}
