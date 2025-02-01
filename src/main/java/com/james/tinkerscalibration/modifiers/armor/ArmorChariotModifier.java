package com.james.tinkerscalibration.modifiers.armor;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ArmorWalkModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.KeybindInteractModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.List;

public class ArmorChariotModifier extends Modifier implements ArmorWalkModifierHook, KeybindInteractModifierHook {
    private final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "chariot");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.BOOT_WALK, ModifierHooks.ARMOR_INTERACT);
    }
    @Override
    public void onWalk(IToolStackView tool, ModifierEntry modifier, LivingEntity living, BlockPos prevPos, BlockPos newPos) {
        if(living.isSprinting() && living instanceof Player player)
        {
            List<LivingEntity> entities = player.getCommandSenderWorld().getEntitiesOfClass(LivingEntity.class, new AABB(newPos).inflate(1D));
            for(LivingEntity entity : entities)
            {
                if(entity != player)
                {
                    Vec3 vec = player.getDeltaMovement();
                    entity.setDeltaMovement(entity.getDeltaMovement().add(living.getDeltaMovement().add(vec.x * 2, 1D, vec.z * 2)));
                    entity.hurt(new DamageSource(player.getCommandSenderWorld().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK)), (float) (player.getDeltaMovement().length() * 10));
                    entity.addEffect(new MobEffectInstance(ModEffect.EFFECTSTUN.get(), 40, 0));
                    ToolDamageUtil.damageAnimated(tool, RANDOM.nextInt(4), living);
                    player.playSound(ModSounds.HARBINGER_CHARGE.get(), 1.0f, 0.5f);
                }
            }

        }
    }
    public boolean startInteract(IToolStackView tool, ModifierEntry modifier, Player player, EquipmentSlot slot, TooltipKey keyModifier) {
        if (player.isSprinting() && !player.hasEffect(Utils.witherSprintCooldownEffect.get())) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 9));
            player.addEffect(new MobEffectInstance(Utils.witherSprintCooldownEffect.get(), 200));
            player.playSound(ModSounds.HARBINGER_CHARGE.get(), 1.0f, 0.5f);
            return true;
        } else {
            return false;
        }
    }
}
