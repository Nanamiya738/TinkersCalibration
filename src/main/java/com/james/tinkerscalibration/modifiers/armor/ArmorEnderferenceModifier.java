package com.james.tinkerscalibration.modifiers.armor;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class ArmorEnderferenceModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> ENDERFERENCE = TConstruct.createKey("heavy");

    public ArmorEnderferenceModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOW, ArmorEnderferenceModifier::onHurt);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(ENDERFERENCE, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(ENDERFERENCE, 0);
            if (level > 0 && attacker instanceof LivingEntity attackerl) {
                if (event.getAmount() != 0) {
                     attackerl.addEffect(new MobEffectInstance(TinkerModifiers.enderferenceEffect.get(), 60));
                }
            }
        });
    }
}
