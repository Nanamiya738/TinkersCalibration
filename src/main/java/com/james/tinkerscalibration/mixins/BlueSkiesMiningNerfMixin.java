package com.james.tinkerscalibration.mixins;

import com.james.tinkerscalibration.integration.BlueSkiesIntegration;
import com.legacy.blue_skies.asm_hooks.PlayerHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import javax.annotation.Nullable;

@Mixin(
        value = {PlayerHooks.class},
        remap = false
)
public class BlueSkiesMiningNerfMixin {
    @Inject(remap=false,
            method = {"modifyBreakSpeed"},
            at = {@At(
                    value = "HEAD",
                    ordinal = 0
            )},
            cancellable = true
    )
    private static void cancelNerfBreakSpeed(float speed, BlockState state, @Nullable BlockPos pos, Player player, CallbackInfoReturnable<Float> cir) {
        if(player != null)
        {
            ItemStack stack = player.getMainHandItem();
            if(stack.getItem() instanceof IModifiable)
            {
                ToolStack tool = ToolStack.from(stack);
                if(ModifierUtil.getModifierLevel(stack, BlueSkiesIntegration.protogenesis.getId()) >= 1)
                {
                    player.displayClientMessage(Component.translatable(""), true);
                    cir.setReturnValue(speed);
                }
            }
        }
    }
    @Inject(
            method = {"isBreakingNerfed"},
            at = {@At(
                    value = "RETURN",
                    ordinal = 0
            )},
            cancellable = true
    )
    private static void disableBreakingNerf(Item item, Player player, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
