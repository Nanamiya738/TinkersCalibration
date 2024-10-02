package com.james.tinkerscalibration.modifiers.armor;


import net.minecraft.core.BlockPos;
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

public class ArmorSwitchingModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> SWITCH = TConstruct.createKey("switching_armor");

    public ArmorSwitchingModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, ArmorSwitchingModifier::onHurt);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(SWITCH, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(SWITCH, 0);
            if (level > 0 && attacker != null && event.getAmount() > 0 && living.isAlive() && attacker.isAlive()) {
                BlockPos posHolder = living.getOnPos();
                BlockPos posTarget = attacker.getOnPos();
                attacker.moveTo(posHolder.getX(), posHolder.getY(), posHolder.getZ());
                living.moveTo(posTarget.getX(), posTarget.getY(), posTarget.getZ());
            }
        });
    }
}
