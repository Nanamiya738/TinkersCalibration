package com.james.tinkerscalibration.modifiers.armor;


import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

public class ArmorVengeanceModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> VEN = TConstruct.createKey("vengeance_armor");

    public ArmorVengeanceModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, ArmorVengeanceModifier::onHurt);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(VEN, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(VEN, 0);
            if(level > 0)
                if (living instanceof Player player && attacker != null && living.getLastHurtByMob() != null && attacker.getType() == player.getLastHurtByMob().getType() && RANDOM.nextFloat() <= level * 0.3f && !(attacker instanceof Guardian)) {
                    if(event.getSource().is(DamageTypes.THORNS)) return;
                    attacker.hurt(new DamageSource(player.getCommandSenderWorld().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.THORNS)), event.getAmount() * 1.5f);
                }
        });
    }
}
