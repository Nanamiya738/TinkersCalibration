package com.james.tinkerscalibration.modifiers.armor;


import com.rolfmao.upgradednetherite.config.UpgradedNetheriteConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;

public class ArmorBlazeriteModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> BLAZERITE = TConstruct.createKey("blazerite_armor");

    public ArmorBlazeriteModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(ArmorBlazeriteModifier::onUpdateApply);
        MinecraftForge.EVENT_BUS.addListener(ArmorBlazeriteModifier::onLivingAttackEvent);
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(BLAZERITE, false, null));
    }
    private static void onUpdateApply(LivingEvent.LivingTickEvent evt) {
        LivingEntity living = evt.getEntity();
        if (!living.isSpectator()) {
            EquipmentContext context = new EquipmentContext(living);
            if (context.hasModifiableArmor()) {
                if (living.isAlive()) {
                    living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
                        int level = holder.get(BLAZERITE, 0);
                        if (level > 0 && living instanceof Player player) {
                            player.clearFire();
                            if (UpgradedNetheriteConfig.EnableLavaSpeed && player.isInLava() && !player.getAbilities().flying) {
                                player.setDeltaMovement(player.getDeltaMovement().multiply(1.659999966621399, 1.0, 1.659999966621399));
                            }
                        }
                    });
                }

            }
        }
    }

    public static void onLivingAttackEvent(LivingAttackEvent event) {
        LivingEntity living = event.getEntity();
        if (!living.isSpectator()) {
            EquipmentContext context = new EquipmentContext(living);
            if (context.hasModifiableArmor()) {
                if (living.isAlive()) {
                    living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
                        int level = holder.get(BLAZERITE, 0);
                        if (level > 0 && living instanceof Player player) {
                            if (event.getSource().isFire()) {
                                if (UpgradedNetheriteConfig.EnableFireImmune) {
                                    if (event.isCancelable()) {
                                        event.setCanceled(true);
                                    }
                                    player.clearFire();
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
