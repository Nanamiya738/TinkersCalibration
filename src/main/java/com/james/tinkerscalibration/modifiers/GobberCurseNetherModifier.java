package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.TinkersCalibration;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class GobberCurseNetherModifier extends NoLevelsModifier implements VolatileDataModifierHook{
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.VOLATILE_DATA);
    }

    private final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "gobber_curse");
    @Override
    public void addVolatileData(IToolContext context, ModifierEntry modifier, ModDataNBT volatileData) {
        IModDataView persistentData = context.getPersistentData();
        int number = volatileData.getSlots(SlotType.UPGRADE);
        if (number >= 2) {
            volatileData.addSlots(SlotType.UPGRADE, -number);
        } else {
            volatileData.addSlots(SlotType.UPGRADE, -2);
        }
        int numbera = volatileData.getSlots(SlotType.ABILITY);
        volatileData.addSlots(SlotType.ABILITY, -numbera);
        if (context.hasTag(TinkerTags.Items.ARMOR)) {
            int numberd = volatileData.getSlots(SlotType.DEFENSE);
            volatileData.addSlots(SlotType.DEFENSE, -numberd);
        }


    }
}
