package com.james.tinkerscalibration.modifiers;

import com.legacy.blue_skies.registries.SkiesDimensions;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.mantle.client.TooltipKey;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class DawnModifier extends Modifier implements TooltipModifierHook, MeleeDamageModifierHook, BreakSpeedModifierHook, ConditionalStatModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOLTIP, ModifierHooks.MELEE_DAMAGE, ModifierHooks.BREAK_SPEED, ModifierHooks.CONDITIONAL_STAT, ModifierHooks.PROJECTILE_HIT);
    }
    @Override
    public void onBreakSpeed(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull PlayerEvent.BreakSpeed event, @Nonnull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        Player player = event.getEntity();
        Level world = player.getCommandSenderWorld();
        if (world.getDayTime() < 24000L && world.getDayTime() > 22200L || player.level.dimension().equals(SkiesDimensions.everdawnKey())) {
            event.setNewSpeed(event.getNewSpeed() + 3 * modifier.getLevel() * tool.getMultiplier(ToolStats.MINING_SPEED));
        }
    }
    @Override
    public float getMeleeDamage(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull ToolAttackContext context, float baseDamage, float damage) {
        Player player = context.getPlayerAttacker();
        if(player != null){
            Level world = player.getLevel();
            if (world.dayTime() < 24000L && world.dayTime() > 22200L || player.level.dimension().equals(SkiesDimensions.everdawnKey())) {
            return damage + 3 * modifier.getLevel() * tool.getMultiplier(ToolStats.ATTACK_DAMAGE);
            }
        }
        return damage;
    }
    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        Level world = living.getCommandSenderWorld();
        int level = modifier.getLevel();
        if (stat == ToolStats.DRAW_SPEED) {
            if (world.getDayTime() < 24000L && world.getDayTime() > 22200L || living.level.dimension().equals(SkiesDimensions.everdawnKey())) {
                return (float) (baseValue + 0.1 * level);
            }
        }
        return 0;
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (attacker != null && (attacker.getCommandSenderWorld().getDayTime() < 24000L && attacker.getCommandSenderWorld().getDayTime() > 22200L || attacker.level.dimension().equals(SkiesDimensions.everdawnKey()))) {
            if (projectile instanceof AbstractArrow arrow) {
                arrow.setBaseDamage(arrow.getBaseDamage() + 0.5 * modifier.getLevel());
            }
        }
        return false;
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, slimeknights.mantle.client.TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        boolean harvest = tool.hasTag(TinkerTags.Items.HARVEST);
        if(player != null) {
            Level world = player.getCommandSenderWorld();
            if (world.getDayTime() < 24000L && world.getDayTime() > 22200L || player.level.dimension().equals(SkiesDimensions.everdawnKey())) {
                if (harvest || tool.hasTag(TinkerTags.Items.RANGED)) {
                    if (tooltipKey == TooltipKey.SHIFT) {
                        if (harvest) {
                            TooltipModifierHook.addStatBoost(tool, modifier.getModifier(), ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, 3 * modifier.getLevel(), tooltip);
                            TooltipModifierHook.addStatBoost(tool, modifier.getModifier(), ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, 3 * modifier.getLevel(), tooltip);
                        } else {
                            TooltipModifierHook.addStatBoost(tool, modifier.getModifier(), ToolStats.DRAW_SPEED, TinkerTags.Items.RANGED, 0.1f * modifier.getLevel(), tooltip);
                            TooltipModifierHook.addStatBoost(tool, modifier.getModifier(), ToolStats.PROJECTILE_DAMAGE, TinkerTags.Items.RANGED, 0.5f * modifier.getLevel(), tooltip);
                        }
                    }
                }
            }
        }
    }
}
