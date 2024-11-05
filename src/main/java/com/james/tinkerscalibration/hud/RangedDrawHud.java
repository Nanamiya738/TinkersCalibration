package com.james.tinkerscalibration.hud;

import com.james.tinkerscalibration.TinkersCalibration;
import com.james.tinkerscalibration.Utils;
import com.james.tinkerscalibration.modifiers.OvershieldModifier;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.mantle.config.Config;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import static slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook.KEY_DRAWTIME;

public class RangedDrawHud {
    private static final ResourceLocation NO_CHARGE = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/no.png");
    private static final ResourceLocation CHARGE_08 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/-8.png");
    private static final ResourceLocation CHARGE_07 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/-7.png");
    private static final ResourceLocation CHARGE_06 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/-6.png");
    private static final ResourceLocation CHARGE_05 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/-5.png");
    private static final ResourceLocation CHARGE_04 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/-4.png");
    private static final ResourceLocation CHARGE_03 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/-3.png");
    private static final ResourceLocation CHARGE_02 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/-2.png");
    private static final ResourceLocation CHARGE_01 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/-1.png");
    private static final ResourceLocation CHARGE_0 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/0.png");
    private static final ResourceLocation CHARGE_1 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/1.png");
    private static final ResourceLocation CHARGE_2 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/2.png");
    private static final ResourceLocation CHARGE_3 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/3.png");
    private static final ResourceLocation CHARGE_4 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/4.png");
    private static final ResourceLocation CHARGE_5 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/5.png");
    private static final ResourceLocation CHARGE_6 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/6.png");
    private static final ResourceLocation CHARGE_7 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/7.png");
    private static final ResourceLocation CHARGE_8 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/8.png");
    private static final ResourceLocation CHARGE_9 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/9.png");
    private static final ResourceLocation CHARGE_10 = new ResourceLocation(TinkersCalibration.MODID,
            "textures/charge/10.png");


    @SubscribeEvent(
            priority = EventPriority.LOW
    )
    public void renderDraw(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (!event.isCanceled() && event.getOverlay() == VanillaGuiOverlay.CROSSHAIR.type()) {
            Gui var3 = mc.gui;
            if (var3 instanceof ForgeGui) {
                ForgeGui gui = (ForgeGui) var3;
                if (!mc.options.hideGui) {
                    Entity renderViewEnity = mc.getCameraEntity();
                    if (!(renderViewEnity instanceof Player player)) {
                        return;
                    }
                    gui.setBlitOffset(-90);
                    gui.setupOverlayRenderState(true, false);
                    int width = mc.getWindow().getGuiScaledWidth();
                    int height = mc.getWindow().getGuiScaledHeight();
                    int x = width / 2;
                    int y = height / 2;
                    PoseStack matrixStack = event.getPoseStack();
                    RenderSystem.setShaderTexture(0, NO_CHARGE);
                    ItemStack stack = player.getMainHandItem();
                    if (Minecraft.getInstance().gameMode != null && stack.getItem() instanceof IModifiable && Minecraft.getInstance().gameMode.getPlayerMode() != GameType.SPECTATOR) {
                        ToolStack tool = ToolStack.from(stack);
                        if (tool.hasTag(TinkerTags.Items.RANGED) && !tool.isBroken()) {
                            ModDataNBT persistentData = tool.getPersistentData();
                            if (!persistentData.contains(KEY_DRAWTIME, 3)) {
                                GuiComponent.blit(matrixStack, x - 4, height / 2 - 4, 0, 0, 8, 8, 8, 8);
                            } else {
                                double total = 16.0 / ConditionalStatModifierHook.getModifiedStat(tool, player, ToolStats.DRAW_SPEED);
                                int eta = (int) Math.ceil(total + 3 - player.getTicksUsingItem());
                                if (eta <= 0.05 * total) {
                                    RenderSystem.setShaderTexture(0, NO_CHARGE);
                                    GuiComponent.blit(matrixStack, x - 4, height / 2 - 4, 0, 0, 8, 8, 8, 8);
                                } else if (eta <= 0.1 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_10);
                                    GuiComponent.blit(matrixStack, x - 5, height / 2 - 5, 0, 0, 10, 10, 10, 10);
                                } else if (eta <= 0.15 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_9);
                                    GuiComponent.blit(matrixStack, x - 6, height / 2 - 6, 0, 0, 12, 12, 12, 12);
                                } else if (eta <= 0.2 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_8);
                                    GuiComponent.blit(matrixStack, x - 7, height / 2 - 7, 0, 0, 14, 14, 14, 14);
                                } else if (eta <= 0.25 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_7);
                                    GuiComponent.blit(matrixStack, x - 8, height / 2 - 8, 0, 0, 16, 16, 16, 16);
                                } else if (eta <= 0.3 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_6);
                                    GuiComponent.blit(matrixStack, x - 9, height / 2 - 9, 0, 0, 18, 18, 18, 18);
                                } else if (eta <= 0.35 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_5);
                                    GuiComponent.blit(matrixStack, x - 10, height / 2 - 10, 0, 0, 20, 20, 20, 20);
                                } else if (eta <= 0.4 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_4);
                                    GuiComponent.blit(matrixStack, x - 11, height / 2 - 11, 0, 0, 22, 22, 22, 22);
                                } else if (eta <= 0.45 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_3);
                                    GuiComponent.blit(matrixStack, x - 12, height / 2 - 12, 0, 0, 24, 24, 24, 24);
                                } else if (eta <= 0.5 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_2);
                                    GuiComponent.blit(matrixStack, x - 13, height / 2 - 13, 0, 0, 26, 26, 26, 26);
                                } else if (eta <= 0.55 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_1);
                                    GuiComponent.blit(matrixStack, x - 14, height / 2 - 14, 0, 0, 28, 28, 28, 28);
                                } else if (eta <= 0.6 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_0);
                                    GuiComponent.blit(matrixStack, x - 15, height / 2 - 15, 0, 0, 30, 30, 30, 30);
                                } else if (eta <= 0.65 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_01);
                                    GuiComponent.blit(matrixStack, x - 16, height / 2 - 16, 0, 0, 32, 32, 32, 32);
                                } else if (eta <= 0.7 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_02);
                                    GuiComponent.blit(matrixStack, x - 17, height / 2 - 17, 0, 0, 34, 34, 34, 34);
                                } else if (eta <= 0.75 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_03);
                                    GuiComponent.blit(matrixStack, x - 18, height / 2 - 18, 0, 0, 36, 36, 36, 36);
                                } else if (eta <= 0.8 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_04);
                                    GuiComponent.blit(matrixStack, x - 19, height / 2 - 19, 0, 0, 38, 38, 38, 38);
                                } else if (eta <= 0.85 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_05);
                                    GuiComponent.blit(matrixStack, x - 20, height / 2 - 20, 0, 0, 40, 40, 40, 40);
                                } else if (eta <= 0.9 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_06);
                                    GuiComponent.blit(matrixStack, x - 21, height / 2 - 21, 0, 0, 42, 42, 42, 42);
                                } else if (eta <= 0.95 * total) {
                                    RenderSystem.setShaderTexture(0, CHARGE_07);
                                    GuiComponent.blit(matrixStack, x - 22, height / 2 - 22, 0, 0, 44, 44, 44, 44);
                                } else {
                                    RenderSystem.setShaderTexture(0, CHARGE_08);
                                    GuiComponent.blit(matrixStack, x - 23, height / 2 - 23, 0, 0, 46, 46, 46, 46);
                                }
                            }
                            event.setCanceled(true);
                            RenderSystem.disableBlend();
                            mc.getProfiler().pop();
                            MinecraftForge.EVENT_BUS.post(new RenderGuiOverlayEvent.Post(event.getWindow(), matrixStack, event.getPartialTick(), VanillaGuiOverlay.CROSSHAIR.type()));
                        }
                    }
                }
            }
        }
    }
}
