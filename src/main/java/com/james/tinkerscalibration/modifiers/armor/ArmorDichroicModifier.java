package com.james.tinkerscalibration.modifiers.armor;


import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ProtectionModifierHook;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nonnull;

import static com.james.tinkerscalibration.modifiers.DarknessModifier.getLight;

public class ArmorDichroicModifier extends Modifier implements ProtectionModifierHook {
    private static final TinkerDataCapability.TinkerDataKey<Integer> DICHROIC = TConstruct.createKey("dichroic_armor");
    public ArmorDichroicModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::livingKnockback);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(DICHROIC, false, null));
    }
    private void livingKnockback(LivingKnockBackEvent event) {
        LivingEntity living = event.getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent(data -> {
            int levels = data.get(DICHROIC, 0);
            if (levels > 0) {int light = getLight(living.getLevel(), living.blockPosition());
                if (light < 7)
                    event.setStrength(event.getStrength() * (1 - (float) (7 - light) / 20 * levels));
            }
        });
    }
    public float getProtectionModifier(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull EquipmentContext context, @Nonnull EquipmentSlot slotType, DamageSource source, float modifierValue) {
        if (!source.isBypassInvul()) {
            LivingEntity living = context.getEntity();
            int light = getLight(living.getLevel(), living.blockPosition());
            if (light > 7)
                modifierValue *= 1 + (float) (light - 7) / 20 * modifier.getLevel();
        }
        return modifierValue;
    }
}
