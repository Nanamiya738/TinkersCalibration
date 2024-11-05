package com.james.tinkerscalibration.mixins;

import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.integration.BlueSkiesIntegration;
import com.legacy.blue_skies.crafting.toolbox.ApplyFalsiteRecipe;
import com.legacy.blue_skies.data.objects.tags.SkiesItemTags;
import com.legacy.blue_skies.items.util.IFalsiteItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import static com.legacy.blue_skies.items.util.IFalsiteItem.getMaxFalsiteUses;

@Mixin(ApplyFalsiteRecipe.class)
public class BlueSkiesFalsiteMixin {
    private final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "falsite");

    @Inject(remap=false,
            method = {"matches"},
            at = {@At(
                    value = "RETURN",
                    ordinal = 0
            )},
            cancellable = true
    )
    public void ticMatches(ItemStack inputItem, ItemStack modifierItem, CallbackInfoReturnable<Boolean> cir) {
        if(inputItem.getItem() instanceof IModifiable)
        {
            ToolStack tool = ToolStack.from(inputItem);
            cir.setReturnValue(modifierItem.is(SkiesItemTags.FALSITE) && tool.getPersistentData().getInt(KEY) < getMaxFalsiteUses(inputItem) && ModifierUtil.getModifierLevel(inputItem, BlueSkiesIntegration.protogenesis.getId()) > 0);
            return;
        }
        cir.setReturnValue(inputItem.getItem() instanceof IFalsiteItem && ((IFalsiteItem)inputItem.getItem()).isFalsiteCompatible(inputItem) && modifierItem.is(SkiesItemTags.FALSITE) && IFalsiteItem.getFalsiteUses(inputItem) < getMaxFalsiteUses(inputItem));
    }
}
