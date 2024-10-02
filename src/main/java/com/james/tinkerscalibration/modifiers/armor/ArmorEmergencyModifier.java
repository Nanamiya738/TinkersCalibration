package com.james.tinkerscalibration.modifiers.armor;

import com.github.L_Ender.cataclysm.init.ModSounds;
import com.james.tinkerscalibration.TinkersCalibration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ProtectionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.function.BiConsumer;

public class ArmorEmergencyModifier extends Modifier implements OnAttackedModifierHook, ProtectionModifierHook, InventoryTickModifierHook, AttributesModifierHook, DamageBlockModifierHook {
    private static final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "emergency");
    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(amount > 0 && context.getEntity() instanceof Player player)
        {
            if(RANDOM.nextFloat() <= Math.sqrt(amount) / 10 && !persistentData.contains(KEY, 3) && !player.getCooldowns().isOnCooldown(tool.getItem()))
            {
                persistentData.putInt(KEY, 3);
                player.playSound(ModSounds.HARBINGER_CHARGE.get(), 1.0f, 0.5f);
            }
        }
    }
    public float getProtectionModifier(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull EquipmentContext context, @Nonnull EquipmentSlot slotType, DamageSource source, float modifierValue) {
        if (!source.isBypassInvul()) {
            ModDataNBT persistentData = tool.getPersistentData();
            if(persistentData.contains(KEY, 3))
            {
                return modifierValue * (1 + modifier.getLevel());
            }
        }

        return modifierValue;
    }
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROTECTION, ModifierHooks.ON_ATTACKED, ModifierHooks.INVENTORY_TICK, ModifierHooks.DAMAGE_BLOCK);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(persistentData.contains(KEY, 3) && holder.tickCount % 20 == 0 && holder instanceof Player player)
        {
            if(persistentData.getInt(KEY) > 0)
            {
                persistentData.putInt(KEY, persistentData.getInt(KEY) - 1);
            }
            else
            {
                persistentData.remove(KEY);
                player.getCooldowns().addCooldown(tool.getItem(), 20);
            }
        }
    }
    public boolean isArmor(EquipmentSlot slot)
    {
        return slot == EquipmentSlot.HEAD || slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;
    }
    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (isArmor(slot)) {
            ModDataNBT persistentData = tool.getPersistentData();
            if(persistentData.contains(KEY, 3))
            {
                consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("36893a5c-6fdc-42f3-867f-b2f8ba5a7cb7"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.2f * modifier.getLevel(), AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        ModDataNBT persistentData = tool.getPersistentData();
        if(persistentData.contains(KEY, 3)) {
            return source.isProjectile();
        }
        return false;
    }
}
