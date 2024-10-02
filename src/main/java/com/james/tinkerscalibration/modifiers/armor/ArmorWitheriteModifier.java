package com.james.tinkerscalibration.modifiers.armor;


import com.rolfmao.upgradednetherite.config.UpgradedNetheriteConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

public class ArmorWitheriteModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> WITHER = TConstruct.createKey("witherite_armor");

    public ArmorWitheriteModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(ArmorWitheriteModifier::onApplyEffect);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(WITHER, false, null));
    }
    private static void onApplyEffect(MobEffectEvent event) {
        LivingEntity living = event.getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(WITHER, 0);
            if (level > 0 && living instanceof ServerPlayer && event.getEffectInstance() != null) {
                if (event.getEffectInstance().getEffect() == MobEffects.WITHER && UpgradedNetheriteConfig.EnableWitherImmune) {
                    event.setResult(Event.Result.DENY);
                    if (event.isCancelable()) {
                        event.setCanceled(true);
                    }
                }
            }
        });
    }
}