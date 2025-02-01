package com.james.tinkerscalibration.hud;

import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.Utils;
import com.james.tinkerscalibration.modifiers.OvershieldModifier;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class OvershieldHud {
    private static final ResourceLocation FILLED_SHIELD = new ResourceLocation(TinkersCalibration.MODID,
            "textures/overshield/filled_shield.png");
    private static final ResourceLocation EMPTY_SHIELD = new ResourceLocation(TinkersCalibration.MODID,
            "textures/overshield/empty_shield.png");
    private static final ResourceLocation COOLDOWN_SHIELD = new ResourceLocation(TinkersCalibration.MODID,
            "textures/overshield/cooldown_shield.png");
    private static final ResourceLocation WARNING_SHIELD = new ResourceLocation(TinkersCalibration.MODID,
            "textures/overshield/warning_shield.png");
    private static final ResourceLocation CRITICAL_SHIELD = new ResourceLocation(TinkersCalibration.MODID,
            "textures/overshield/critical_shield.png");
    public static final IGuiOverlay HUD_SHIELD = ((gui, poseStack, partialTick, width, height) -> {
        int x = width/2;
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,EMPTY_SHIELD);
        if (player != null && !player.isCreative()) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
            ToolStack tool = ToolStack.from(stack);
            if (ModifierUtil.getModifierLevel(stack, Utils.overshield.getId()) >= 1) {
                OvershieldModifier overshield = (OvershieldModifier) Utils.overshield.get();
                //It's very rarely that the chestplate doesn't grant any armor value, so it's unlikely the hud will be too high.
                for (int i = 0; i < 10; i++) {
                    poseStack.blit(EMPTY_SHIELD, x - 90 + (i * 8), height - 57, 0, 0, 7, 7, 7, 7);
                }

                RenderSystem.setShaderTexture(0, CRITICAL_SHIELD);
                for (int i = 0; i < 4; i++) {
                    if ((float)overshield.getShield(tool) / (float)overshield.getShieldCapacity(tool, tool.getModifiers().getEntry(Utils.overshield.getId())) * 10 > i) {
                        poseStack.blit(CRITICAL_SHIELD, x - 90 + (i * 8), height - 57, 0, 0, 7, 7, 7, 7);
                    } else {
                        break;
                    }
                }
                RenderSystem.setShaderTexture(0, WARNING_SHIELD);
                for (int i = 4; i < 7; i++) {
                    if ((float)overshield.getShield(tool) / (float)overshield.getShieldCapacity(tool, tool.getModifiers().getEntry(Utils.overshield.getId())) * 10 > i) {
                        poseStack.blit(WARNING_SHIELD, x - 90 + (i * 8), height - 57, 0, 0, 7, 7, 7, 7);
                    } else {
                        break;
                    }
                }
                RenderSystem.setShaderTexture(0, FILLED_SHIELD);
                for (int i = 7; i < 10; i++) {
                    if ((float)overshield.getShield(tool) / (float)overshield.getShieldCapacity(tool, tool.getModifiers().getEntry(Utils.overshield.getId())) * 10 > i) {
                        poseStack.blit(FILLED_SHIELD, x - 90 + (i * 8), height - 57, 0, 0, 7, 7, 7, 7);
                    } else {
                        break;
                    }
                }
                if(player.getCooldowns().isOnCooldown(stack.getItem()))
                {
                    RenderSystem.setShaderTexture(0, COOLDOWN_SHIELD);
                    for (int i = 0; i < 10; i++) {
                        poseStack.blit(COOLDOWN_SHIELD, x - 90 + (i * 8), height - 57, 0, 0, 7, 7, 7, 7);
                    }
                }
            }
        }
    });
}
