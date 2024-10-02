package com.james.tinkerscalibration.modifiers.armor;

import com.github.L_Ender.cataclysm.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ArmorWalkModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class ArmorChariotModifier extends Modifier implements ArmorWalkModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.BOOT_WALK);
    }
    @Override
    public void onWalk(IToolStackView tool, ModifierEntry modifier, LivingEntity living, BlockPos prevPos, BlockPos newPos) {
        if(living.isSprinting() && living instanceof Player player)
        {
            List<LivingEntity> entities = player.level.getEntitiesOfClass(LivingEntity.class, living.getBoundingBox().inflate(0.5D));
            for(LivingEntity entity : entities)
            {
                if(entity != player)
                {
                    entity.setDeltaMovement(entity.getDeltaMovement().add(living.getDeltaMovement().add(0, 1D, 0)));
                    entity.hurt(DamageSource.playerAttack(player), player.getSpeed() * 10);
                    ToolDamageUtil.damageAnimated(tool, RANDOM.nextInt(4), living);
                    player.playSound(ModSounds.HARBINGER_CHARGE.get(), 1.0f, 0.5f);
                }
            }

        }
    }
}
