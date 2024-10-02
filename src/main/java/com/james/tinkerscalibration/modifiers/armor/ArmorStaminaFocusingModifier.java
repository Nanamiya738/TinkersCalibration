package com.james.tinkerscalibration.modifiers.armor;


import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;

public class ArmorStaminaFocusingModifier extends Modifier {

    private static final TinkerDataCapability.TinkerDataKey<Integer> STAMINA = TConstruct.createKey("stamina_armor");

    public ArmorStaminaFocusingModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(ArmorStaminaFocusingModifier::onUpdateApply);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(STAMINA, false, null));
    }
    private static void onUpdateApply(LivingEvent.LivingTickEvent evt) {
        LivingEntity living = evt.getEntity();
        if (!living.isSpectator()) {
            EquipmentContext context = new EquipmentContext(living);
            if (context.hasModifiableArmor()) {
                if (!living.level.isClientSide && living.isAlive()) {
                    living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
                        int level = holder.get(STAMINA, 0);
                        if (level > 0) {
                            if (living.tickCount % 4 == 0 && living.isCrouching()) {
                                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80, 1));
                                living.getLevel().addParticle(ParticleTypes.WITCH, living.getX(), living.getY(), living.getZ(), 0, 0, 0);
                            }
                        }
                    });
                }
            }
        }
    }
}
