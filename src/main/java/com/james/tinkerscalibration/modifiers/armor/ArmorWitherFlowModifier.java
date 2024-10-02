package com.james.tinkerscalibration.modifiers.armor;


import com.james.tinkerscalibration.TinkersCalibration;
import net.minecraft.resources.ResourceLocation;
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

public class ArmorWitherFlowModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> WITHER = TConstruct.createKey("retribution");

    public ArmorWitherFlowModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOW, ArmorWitherFlowModifier::onHurt);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(WITHER, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(WITHER, 0);
            if (level > 0 && attacker instanceof LivingEntity attackerl) {
                if (event.getAmount() != 0) {
                    attackerl.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, level - 1));
                    attackerl.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, level - 1));
                }
            }
        });
    }
}
