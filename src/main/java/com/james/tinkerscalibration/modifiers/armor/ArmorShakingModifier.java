package com.james.tinkerscalibration.modifiers.armor;


import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Collection;

public class ArmorShakingModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> SHAKING = TConstruct.createKey("shaking_armor");

    public ArmorShakingModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, ArmorShakingModifier::onHurt);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(SHAKING, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            Collection<MobEffectInstance> listEffect1;
            Collection<MobEffectInstance> listEffect2;
            int level = holder.get(SHAKING, 0);
            if (level > 0) {
                if (attacker instanceof LivingEntity attackerl && living != attackerl) {
                    if (event.getAmount() > 0) {
                        Vec3 motionOld = attackerl.getDeltaMovement();
                        Vec3 motionNew = new Vec3(
                                0,
                                motionOld.y <= 0 ? 0.4 : motionOld.y + 1,
                                0
                        );
                        attackerl.setDeltaMovement(motionNew);
                        attackerl.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 2));
                        attackerl.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30, 2));

                    }
                }
            }
        });
    }
}
