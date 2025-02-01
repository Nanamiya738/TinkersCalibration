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

public class ArmorSharpLikeGlassModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> SHARP = TConstruct.createKey("sharp");

    public ArmorSharpLikeGlassModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, ArmorSharpLikeGlassModifier::onHurt);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(SHARP, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getDirectEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(SHARP, 0);
            if(level > 0)
                if (living instanceof Player player && attacker != null && !(attacker instanceof Guardian)) {
                    if(event.getSource().is(DamageTypes.THORNS)) return;
                    float amount = event.getAmount();
                    int ram = RANDOM.nextInt(9);
                    if (ram <= 5) {
                        attacker.hurt(new DamageSource(player.getCommandSenderWorld().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK)), amount * 1.5f);
                    }
                    if (ram == 6 || ram == 7) {
                        event.setAmount(amount * 1.2f);
                    }
                }
        });
    }
}
