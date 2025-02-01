package com.james.tinkerscalibration.mixins;

import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.MaterialNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

@Mixin(ToolStack.class)
public class CreateToolMixin {

    @Inject(method = "createTool", at = @At("RETURN"), remap = false, cancellable = true)
    private static void create(Item item, ToolDefinition definition, MaterialNBT materials, CallbackInfoReturnable<ToolStack> cir)
    {
        List<MaterialVariant> materialList = materials.getList();
        for(MaterialVariant material : materialList) {
            ToolStack tool = cir.getReturnValue();
            if (material.getId().equals(new MaterialId("tinkerscalibration", "diamond"))) {
                tool.addModifier(new ModifierId(TConstruct.MOD_ID, "diamond"), 1);
            }
            if (material.getId().equals(new MaterialId("tinkerscalibration", "emerald"))) {
                tool.addModifier(new ModifierId(TConstruct.MOD_ID, "emerald"), 1);
            }
            if (material.getId().getNamespace().equals("tinkerscalibration") && material.getId().getPath().contains("netherite")) {
                tool.addModifier(new ModifierId(TConstruct.MOD_ID, "diamond"), 1);
                tool.addModifier(new ModifierId(TConstruct.MOD_ID, "netherite"), 1);
            }
            cir.setReturnValue(tool);
            return;
        }
    }
}
