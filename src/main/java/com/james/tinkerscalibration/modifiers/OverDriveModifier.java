package com.james.tinkerscalibration.modifiers;

import com.github.L_Ender.cataclysm.client.sound.BossMusicSound;
import com.github.L_Ender.cataclysm.init.ModSounds;
import com.james.tinkerscalibration.TinkersCalibration;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DurabilityDisplayModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class OverDriveModifier extends Modifier implements BlockBreakModifierHook, MeleeHitModifierHook, ProjectileLaunchModifierHook, DurabilityDisplayModifierHook, InventoryTickModifierHook, MeleeDamageModifierHook, BreakSpeedModifierHook, ConditionalStatModifierHook {
    private final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "overdrive");
    private final ResourceLocation OVER = new ResourceLocation(TinkersCalibration.MODID, "overdrive_time");
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.BLOCK_BREAK, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_LAUNCH, ModifierHooks.DURABILITY_DISPLAY);
    }

    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(context.isEffective() && RANDOM.nextFloat() <= 0.3f * modifier.getLevel()) {
            persistentData.putInt(KEY, persistentData.getInt(KEY) + 1);
            LivingEntity holder = context.getLiving();
            Level world = holder.level;
            world.addParticle(ParticleTypes.SMOKE, holder.getX(), holder.getY(), holder.getZ(), 0, 0, 0);
        }
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
        ModDataNBT tp = tool.getPersistentData();
        if(primary && arrow != null && arrow.isCritArrow() && RANDOM.nextFloat() <= 0.3f * modifier.getLevel()) {
            tp.putInt(KEY, tp.getInt(KEY) + 1);
            Level world = shooter.level;
            world.addParticle(ParticleTypes.SMOKE, shooter.getX(), shooter.getY(), shooter.getZ(), 0, 0, 0);
        }
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(context.isFullyCharged() && RANDOM.nextFloat() <= 0.3f * modifier.getLevel()) {
            persistentData.putInt(KEY, persistentData.getInt(KEY) + 1);
            LivingEntity holder = context.getAttacker();
            Level world = holder.level;
            world.addParticle(ParticleTypes.SMOKE, holder.getX(), holder.getY(), holder.getZ(), 0, 0, 0);
        }
    }

    @Nullable
    @Override
    public Boolean showDurabilityBar(IToolStackView tool, ModifierEntry modifier) {
        return null;
    }

    @Override
    public int getDurabilityWidth(IToolStackView tool, ModifierEntry modifier) {
        return 0;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, ModifierEntry modifier) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(persistentData.contains(KEY, 3))
        {
            if(persistentData.getInt(KEY) == 1) return 0xFFF937;
            else if(persistentData.getInt(KEY) == 2) return 0xFFC835;
            else if(persistentData.getInt(KEY) == 3) return 0xFF9D36;
            else if(persistentData.getInt(KEY) == 4) return 0xFF7A3F;
            else return 0xFF4443;
        }
        return 0;
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(persistentData.contains(OVER, 3) && holder.tickCount % 20 == 0)
        {
            world.addParticle(ParticleTypes.SMOKE, holder.getX(), holder.getY(), holder.getZ(), 0, 0, 0);
            world.addParticle(ParticleTypes.FLAME, holder.getX(), holder.getY(), holder.getZ(), 0, 0, 0);
            persistentData.putInt(OVER, persistentData.getInt(OVER) - 1);
            if(persistentData.getInt(OVER) == 0)
            {
                persistentData.remove(KEY);
            }
        }
        if(persistentData.getInt(KEY) == 6)
        {
            persistentData.remove(KEY);
            persistentData.putInt(OVER, 5);
            holder.playSound(ModSounds.HARBINGER_CHARGE.get(), 1.0f, 0.5f);
        }
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(persistentData.contains(OVER, 3))
        {
            if(stat == ToolStats.DRAW_SPEED) return baseValue * (1 + RANDOM.nextFloat(0.3f, 0.8f) * modifier.getLevel());
            if(stat == ToolStats.VELOCITY) return baseValue * (1 + RANDOM.nextFloat(0.3f, 0.8f) * modifier.getLevel());
        }
        return baseValue;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(persistentData.contains(OVER, 3))
        {
            return damage * (1 + RANDOM.nextFloat(0.3f, 0.8f) * modifier.getLevel());
        }
        return damage;
    }

    @Override
    public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(persistentData.contains(OVER, 3))
        {
            event.setNewSpeed(event.getNewSpeed() * (1 + RANDOM.nextFloat(0.3f, 0.8f) * modifier.getLevel()));
        }
    }
}
