package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.Utils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;

import static slimeknights.tconstruct.library.tools.stat.ToolStats.DRAW_SPEED;
import static slimeknights.tconstruct.library.tools.stat.ToolStats.VELOCITY;

public class BloodBlastModifier extends Modifier implements GeneralInteractionModifierHook, ConditionalStatModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.GENERAL_INTERACT, ModifierHooks.CONDITIONAL_STAT);
    }
    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (source == InteractionSource.LEFT_CLICK && !tool.isBroken() && player.isCrouching()) {
            ToolDamageUtil.damageAnimated(tool, 5 * modifier.getLevel(), player);
            player.getCooldowns().addCooldown(tool.getItem(), 600);
            player.addEffect(new MobEffectInstance(Utils.bloodblastEffect.get(), 300, modifier.getLevel() - 1));
            player.hurt(DamageSource.MAGIC, 3);
            return InteractionResult.sidedSuccess(player.getLevel().isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == VELOCITY){
            return (float) (baseValue + 0.25 * living.getEffect(Utils.bloodblastEffect.get()).getAmplifier() - 1);
        }
        return baseValue;
    }
}
