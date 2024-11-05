package com.james.tinkerscalibration.mixins;

import com.james.tinkerscalibration.integration.BlueSkiesIntegration;
import com.legacy.blue_skies.asm_hooks.LivingEntityHooks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

@Mixin(LivingEntityHooks.class)
public class BlueSkiesArmorNerfMixin {
    @Inject(remap = false,
            method = {"getArmorValue"},
            at = {@At("RETURN")},
            cancellable = true
    )
    private static void cancelNerf(int original, LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        if (entity instanceof Player player) {
            EquipmentContext context = new EquipmentContext(player);
            boolean valid = false;
            if (context.hasModifiableArmor()) {
                IToolStackView tool = context.getToolInSlot(EquipmentSlot.FEET);
                if (tool != null && ModifierUtil.getModifierLevel(player.getItemBySlot(EquipmentSlot.FEET), BlueSkiesIntegration.protogenesis.getId()) >= 1)
                    valid = true;
                tool = context.getToolInSlot(EquipmentSlot.LEGS);
                if (tool != null && ModifierUtil.getModifierLevel(player.getItemBySlot(EquipmentSlot.LEGS), BlueSkiesIntegration.protogenesis.getId()) >= 1)
                    valid = true;
                tool = context.getToolInSlot(EquipmentSlot.CHEST);
                if (tool != null && ModifierUtil.getModifierLevel(player.getItemBySlot(EquipmentSlot.CHEST), BlueSkiesIntegration.protogenesis.getId()) >= 1)
                    valid = true;
                tool = context.getToolInSlot(EquipmentSlot.HEAD);
                if (tool != null && ModifierUtil.getModifierLevel(player.getItemBySlot(EquipmentSlot.HEAD), BlueSkiesIntegration.protogenesis.getId()) >= 1)
                    valid = true;
            }
            if (valid)
                cir.setReturnValue(original);
        }
    }
}
