package com.james.tinkerscalibration.modifiers;

import com.james.tinkerscalibration.TinkersCalibration;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class LightnessModifier extends Modifier implements BreakSpeedModifierHook, TooltipModifierHook, ConditionalStatModifierHook, AttributesModifierHook, InventoryTickModifierHook {
    private final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "lightness");
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {

    }

    @Override
    public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if(!event.getEntity().isSprinting())
        {
            event.setNewSpeed(event.getNewSpeed() * (1 + 0.2f * modifier.getLevel()));
        }
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(persistentData.getBoolean(KEY))
        {
            consumer.accept(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("19a8033f-41c5-4648-9210-74777a91afbb"), Attributes.ATTACK_SPEED.getDescriptionId(), 0.2f * modifier.getLevel(), AttributeModifier.Operation.MULTIPLY_BASE));
        }
        else
        {
            consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("51c295e0-3a10-4a77-bfe2-c9f8dbeeac38"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.2f * modifier.getLevel(), AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if(!living.isSprinting() && stat == ToolStats.DRAW_SPEED)
        {
            return baseValue * (1 + 0.2f * modifier.getLevel());
        }
        return baseValue;
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        ModDataNBT persistentData = tool.getPersistentData();
        persistentData.putBoolean(KEY, !holder.isSprinting());
    }
}
