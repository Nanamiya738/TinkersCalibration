package com.james.tinkerscalibration.modifiers.armor;


import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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

public class ArmorOverNaturalModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> OVER = TConstruct.createKey("over_natural_armor");

    public ArmorOverNaturalModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, ArmorOverNaturalModifier::onHurt);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(OVER, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(OVER, 0);
            if(level > 0) {
                living.invulnerableTime += 5;
                if (RANDOM.nextFloat() <= 0.2f * level && attacker != null) {
                    attacker.hurt(new DamageSource(living.getCommandSenderWorld().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)), event.getAmount() * 0.1f * level);
                }
            }
        });
    }
}
