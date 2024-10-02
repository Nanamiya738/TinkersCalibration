package com.james.tinkerscalibration.modifiers.armor;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

public class ArmorLevitateModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> LEVITATE = TConstruct.createKey("heavy");

    public ArmorLevitateModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOW, ArmorLevitateModifier::onHurt);
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(LEVITATE, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(LEVITATE, 0);
            if (level > 0 && attacker instanceof LivingEntity attackerl) {
                if (event.getAmount() != 0 && RANDOM.nextFloat() <= level * 0.4f) {
                     attackerl.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, level - 1));
                }
            }
        });
    }
}
