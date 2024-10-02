package com.james.tinkerscalibration.modifiers.armor;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

public class ArmorBlowingModifier extends Modifier {
    private static final TinkerDataCapability.TinkerDataKey<Integer> BLOW = TConstruct.createKey("over_natural_armor");

    public ArmorBlowingModifier() {
        super();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, ArmorBlowingModifier::onHurt);
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addModule(new ArmorLevelModule(BLOW, false, null));
    }
    private static void onHurt(LivingHurtEvent event) {
        LivingEntity living = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(BLOW, 0);
            if(level > 0 && attacker instanceof LivingEntity attackerl) {
                Level world = attackerl.getCommandSenderWorld();
                if (!world.isClientSide && RANDOM.nextFloat() <= 0.2f * Math.min(level, 4)) {
                    ItemStack stack2drop;
                    stack2drop = attackerl.getMainHandItem();
                    if (stack2drop.isEmpty()) // 主手没找到
                    {
                        stack2drop = attackerl.getOffhandItem();
                        attackerl.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
                    } else if (!stack2drop.isEmpty()) // 主手找到了
                    {
                        attackerl.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    } else { // 都没找到
                        return;
                    }
                    ItemEntity item = new ItemEntity(world, attackerl.getX(), attackerl.getY(), attackerl.getZ(), stack2drop);
                    world.addFreshEntity(item);
                }
            }
        });
    }
}
