package com.james.tinkerscalibration.event;

import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.tools.helper.TooltipBuilder;
import slimeknights.tconstruct.library.tools.helper.TooltipUtil;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mod.EventBusSubscriber(
        modid = "tinkerscalibration",
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class SpaghettiTooltipEvent {
    private static final ResourceLocation KEY = new ResourceLocation(TinkersCalibration.MODID, "spaghetti");
    private static final Component SPAGHETTI = TConstruct.makeTranslation("modifier", "spaghetti");
    private static final Component SPAGHETTI1 = TConstruct.makeTranslation("modifier", "spaghetti1");
    private static final Component SPAGHETTI1_1 = TConstruct.makeTranslation("modifier", "spaghetti1_1");
    private static final Component SPAGHETTI2 = TConstruct.makeTranslation("modifier", "spaghetti2");
    private static final Component SPAGHETTI2_1 = TConstruct.makeTranslation("modifier", "spaghetti2_1");
    private static final Component SPAGHETTI3 = TConstruct.makeTranslation("modifier", "spaghetti3");

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof IModifiable) {
            ToolStack tool = ToolStack.from(stack);
            if (tool.getPersistentData().contains(KEY, 3)) {
                KeyModifier activeModifierKey = KeyModifier.getActiveModifier();
                if (activeModifierKey != KeyModifier.SHIFT) {
                    event.getToolTip().add(1, Component.literal("").append(SPAGHETTI).append(TooltipBuilder.formatPartialAmount(tool.getPersistentData().getInt(KEY), 100)));
                    switch (tool.getModifierLevel(Utils.spaghetti.get())) {
                        case 1:
                            event.getToolTip().add(3, Component.translatable("").append(SPAGHETTI1).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
                            event.getToolTip().add(4, Component.translatable("").append(SPAGHETTI1_1).withStyle(ChatFormatting.GRAY));
                            break;
                        case 2:
                            event.getToolTip().add(3, Component.translatable("").append(SPAGHETTI2).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
                            event.getToolTip().add(4, Component.translatable("").append(SPAGHETTI2_1).withStyle(ChatFormatting.GRAY));
                            break;
                        case 3:
                            event.getToolTip().add(3, Component.translatable("").append(SPAGHETTI3).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
                            break;
                    }
                }
            }
        }
    }
}
