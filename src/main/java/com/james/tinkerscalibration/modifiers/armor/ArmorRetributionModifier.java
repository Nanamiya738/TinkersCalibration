package com.james.tinkerscalibration.modifiers.armor;


import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.Utils;
import net.minecraft.resources.ResourceLocation;
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

public class ArmorRetributionModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> RETRIBUTION = TConstruct.createKey("retribution");
    private final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "envy");

    public ArmorRetributionModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOW, ArmorRetributionModifier::onHurt);
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(RETRIBUTION, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(RETRIBUTION, 0);
            if (level > 0 && attacker instanceof LivingEntity attackerl) {
                if (event.getAmount() != 0) {
                    int effectLevel = Math.min(7, Utils.envyEffect.get().getLevel(living) + 1);
                    Utils.envyArmorEffect.get().apply(living, 5 * 20, effectLevel, true);
                    Utils.envied.get().apply(attackerl, 5 * 20, effectLevel, true);
                }
            }
        });
    }
}
