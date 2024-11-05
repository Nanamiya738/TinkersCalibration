package com.james.tinkerscalibration.mixins;

import com.james.tinkerscalibration.integration.BlueSkiesIntegration;
import com.legacy.blue_skies.entities.util.SkiesEntityHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(SkiesEntityHooks.class)
public class BlueSkiesDamageNerfMixin {
    @Inject(remap=false,
            method = {"nerfDamage"},
            at = {@At(
                    value = "RETURN",
                    ordinal = 0
            )},
            cancellable = true
    )
    private static void cancelNerf(DamageSource source, float amount, CallbackInfoReturnable<Float> cir){
        Entity attacker = source.getEntity();
        if(attacker instanceof Player player)
        {
            ItemStack stack = player.getMainHandItem();
            if(stack.getItem() instanceof IModifiable)
            {
                ToolStack tool = ToolStack.from(stack);
                if(ModifierUtil.getModifierLevel(stack, BlueSkiesIntegration.protogenesis.getId()) >= 1)
                {
                    player.displayClientMessage(Component.translatable(""), true);
                    cir.setReturnValue(amount);
                }
            }
        }
    }

    @Inject(remap=false,
            method = {"nerfIndirectDamage"},
            at = {@At(
                    value = "RETURN",
                    ordinal = 0
            )},
            cancellable = true
    )
    private static void cancelNerfIndirect(DamageSource source, float amount, CallbackInfoReturnable<Float> cir){
        Entity attacker = source.getEntity();
        if(attacker instanceof Player player)
        {
            ItemStack stack = player.getMainHandItem();
            if(stack.getItem() instanceof IModifiable)
            {
                ToolStack tool = ToolStack.from(stack);
                if(ModifierUtil.getModifierLevel(stack, BlueSkiesIntegration.protogenesis.getId()) >= 1)
                {
                    player.displayClientMessage(Component.translatable(""), true);
                    cir.setReturnValue(amount);
                }
            }
        }
    }
}
