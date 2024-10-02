package com.james.tinkerscalibration.tiers;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class FakeNetherGobberTier implements Tier {

    public static Tier instance = new FakeNetherGobberTier();

    @Override
    public int getUses() {
        return 4000;
    }

    @Override
    public float getSpeed() {
        return 4f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 4f;
    }

    @Override
    public int getLevel() {
        return 6;
    }

    @Override
    public int getEnchantmentValue() {
        return 40;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

}
