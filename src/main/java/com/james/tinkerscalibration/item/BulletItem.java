package com.james.tinkerscalibration.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import slimeknights.mantle.util.TranslationHelper;
import slimeknights.tconstruct.gadgets.entity.shuriken.ShurikenEntityBase;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiFunction;

public class BulletItem extends SnowballItem {
    private final BiFunction<Level, Player, ShurikenEntityBase> entity;

    public BulletItem(Properties properties, BiFunction<Level, Player, ShurikenEntityBase> entity) {
        super(properties);
        this.entity = entity;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        TranslationHelper.addOptionalTooltip(stack, tooltip);
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
