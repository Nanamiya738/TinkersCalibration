package com.james.tinkerscalibration.modifiers.armor;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.UUID;
import java.util.function.BiConsumer;

public class ArmorNobleModifier extends Modifier implements AttributesModifierHook {
    private static final TinkerDataCapability.TinkerDataKey<Integer> NOBLE = TConstruct.createKey("noble");

    public ArmorNobleModifier() {
        MinecraftForge.EVENT_BUS.addListener(ArmorNobleModifier::onApplyEffect);
    }

    private static void onApplyEffect(MobEffectEvent event) {
        LivingEntity living = event.getEntity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            int level = holder.get(NOBLE, 0);
            if (level > 0 && living instanceof ServerPlayer && event.getEffectInstance() != null) {
                MobEffectInstance instance = event.getEffectInstance();
                if (instance.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
                    if (instance.getAmplifier() + 1 <= level) {
                        event.setResult(Event.Result.DENY);
                        if (event.isCancelable()) {
                            event.setCanceled(true);
                        }
                    } else
                        event.getEffectInstance().update(new MobEffectInstance(instance.getEffect(), (int) (Math.max(5, (1 - 0.2f * level) * instance.getDuration())), instance.getAmplifier() - level));
                }
            }
        });
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        consumer.accept(Attributes.LUCK, new AttributeModifier(UUID.fromString("8ece3973-02f0-459e-a5d1-4cda8209f161"), Attributes.LUCK.getDescriptionId(), modifier.getLevel(), AttributeModifier.Operation.ADDITION));
    }
}