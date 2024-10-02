package com.james.tinkerscalibration.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class BloodThirstyEffect extends NoMilkEffect {
    private static final String SOURCE_KEY = TConstruct.prefix("blood_thirsty");
    public static final String uuidAttackSpeed = "643c78a6-fb2e-405a-a33d-dde16edb70ee";

    public BloodThirstyEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x5A603E, true);
    }

    @Override
    public void applyEffectTick(LivingEntity target, int level) {
        //if(ModifierUtil.getModifierLevel(target.getMainHandItem(), Utils.bloodthirsty.getId()) > 0)
        addAttributeModifier(Attributes.ATTACK_SPEED, uuidAttackSpeed, 0.2f * level, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}