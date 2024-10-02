package com.james.tinkerscalibration.modifiers;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ChemicalInstableModifier extends Modifier {
    public static void boom(LivingEntity player, double posX, double posY, double posZ) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10, 1));
        player.getCommandSenderWorld().explode(null, posX, posY, posZ,
                (float) (double) 1,
                false, Explosion.BlockInteraction.DESTROY);
    }

    public static void boom(LivingEntity player, LivingEntity target) {
        boom(player, target.getX(), target.getY(), target.getZ());
    }

    /*@Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        if (damageDealt > 0 && !context.getAttacker().getCommandSenderWorld().isClientSide && RANDOM.nextFloat() <= 0.15)
            boom(context.getAttacker(), context.getAttacker());
        return 0;
    }

    @Override
    public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
        if (RANDOM.nextFloat() <= 0.06)
            boom(context.getLiving(), context.getLiving());
    }
     */
}
