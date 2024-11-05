package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.integration.BlueSkiesIntegration;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierTraitHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;

import static com.legacy.blue_skies.items.util.IFalsiteItem.getMaxFalsiteUses;

public class NobleModifier extends NoLevelsModifier implements ModifierTraitHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MODIFIER_TRAITS);
    }

    @Override
    public void addTraits(IToolContext iToolContext, ModifierEntry modifierEntry, TraitBuilder traitBuilder, boolean b) {
        traitBuilder.addEntry(new ModifierEntry(BlueSkiesIntegration.noble_real.getId(), 1));
    }
}
