package com.james.tinkerscalibration.hud;

import com.james.tinkerscalibration.TinkersCalibration;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import static slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook.KEY_DRAWTIME;
@Mod.EventBusSubscriber(value = Dist.CLIENT)
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
            if (var3 instanceof ForgeGui gui) {
                if (!mc.options.hideGui) {
                    Entity renderViewEnity = mc.getCameraEntity();
                    if (!(renderViewEnity instanceof Player player)) {
                        return;
                    }
                    gui.setupOverlayRenderState(true, false);
                    int width = event.getWindow().getGuiScaledWidth();
                    int height = event.getWindow().getGuiScaledHeight();
                    int x = width / 2;
                    int y = height / 2;
                    RenderSystem.setShaderTexture(0, NO_CHARGE);
                    ItemStack stack = null;
                    if(player.getMainHandItem().getItem() instanceof IModifiable && ToolStack.from(player.getMainHandItem()).hasTag(TinkerTags.Items.RANGED) && !ToolStack.from(player.getMainHandItem()).isBroken()) {
                        stack = player.getMainHandItem();
                    }
                    else if(player.getOffhandItem().getItem() instanceof IModifiable && ToolStack.from(player.getOffhandItem()).hasTag(TinkerTags.Items.RANGED) && !ToolStack.from(player.getOffhandItem()).isBroken()) {
                        stack = player.getOffhandItem();
                    }
                    if (Minecraft.getInstance().gameMode != null && stack != null && Minecraft.getInstance().gameMode.getPlayerMode() != GameType.SPECTATOR) {
                        ToolStack tool = ToolStack.from(stack);
                        if (tool.hasTag(TinkerTags.Items.RANGED) && !tool.isBroken()) {
                            ModDataNBT persistentData = tool.getPersistentData();
                            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                            RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
                            if (!persistentData.contains(KEY_DRAWTIME, 3)) {
                                event.getGuiGraphics().blit(NO_CHARGE, x - 4, height / 2 - 4, 0, 0, 8, 8, 8, 8);
                            } else {
                                double total = 16.0 / ConditionalStatModifierHook.getModifiedStat(tool, player, ToolStats.DRAW_SPEED);
                                int eta = (int) Math.max(0, Math.ceil(total - player.getTicksUsingItem()));
                                float charge = 1 - (float) (eta / total);
                                charge = (charge * charge + charge * 2.0F) / 3.0F;

                                if (charge >= 1) {
                                    RenderSystem.setShaderTexture(0, NO_CHARGE);
                                    event.getGuiGraphics().blit(NO_CHARGE, x - 4, height / 2 - 4, 0, 0, 8, 8, 8, 8);
                                } else if (charge >= 0.9) {
                                    RenderSystem.setShaderTexture(0, CHARGE_10);
                                    event.getGuiGraphics().blit(CHARGE_10, x - 5, height / 2 - 5, 0, 0, 10, 10, 10, 10);
                                } else if (charge >= 0.85) {
                                    RenderSystem.setShaderTexture(0, CHARGE_9);
                                    event.getGuiGraphics().blit(CHARGE_9, x - 6, height / 2 - 6, 0, 0, 12, 12, 12, 12);
                                } else if (charge >= 0.8) {
                                    RenderSystem.setShaderTexture(0, CHARGE_8);
                                    event.getGuiGraphics().blit(CHARGE_8, x - 7, height / 2 - 7, 0, 0, 14, 14, 14, 14);
                                } else if (charge >= 0.75) {
                                    RenderSystem.setShaderTexture(0, CHARGE_7);
                                    event.getGuiGraphics().blit(CHARGE_7, x - 8, height / 2 - 8, 0, 0, 16, 16, 16, 16);
                                } else if (charge >= 0.7) {
                                    RenderSystem.setShaderTexture(0, CHARGE_6);
                                    event.getGuiGraphics().blit(CHARGE_6, x - 9, height / 2 - 9, 0, 0, 18, 18, 18, 18);
                                } else if (charge >= 0.65) {
                                    RenderSystem.setShaderTexture(0, CHARGE_5);
                                    event.getGuiGraphics().blit(CHARGE_5, x - 10, height / 2 - 10, 0, 0, 20, 20, 20, 20);
                                } else if (charge >= 0.6) {
                                    RenderSystem.setShaderTexture(0, CHARGE_4);
                                    event.getGuiGraphics().blit(CHARGE_4, x - 11, height / 2 - 11, 0, 0, 22, 22, 22, 22);
                                } else if (charge >= 0.55) {
                                    RenderSystem.setShaderTexture(0, CHARGE_3);
                                    event.getGuiGraphics().blit(CHARGE_3, x - 12, height / 2 - 12, 0, 0, 24, 24, 24, 24);
                                } else if (charge >= 0.5) {
                                    RenderSystem.setShaderTexture(0, CHARGE_2);
                                    event.getGuiGraphics().blit(CHARGE_2, x - 13, height / 2 - 13, 0, 0, 26, 26, 26, 26);
                                } else if (charge >= 0.45) {
                                    RenderSystem.setShaderTexture(0, CHARGE_1);
                                    event.getGuiGraphics().blit(CHARGE_1, x - 14, height / 2 - 14, 0, 0, 28, 28, 28, 28);
                                } else if (charge >= 0.4) {
                                    RenderSystem.setShaderTexture(0, CHARGE_0);
                                    event.getGuiGraphics().blit(CHARGE_0, x - 15, height / 2 - 15, 0, 0, 30, 30, 30, 30);
                                } else if (charge >= 0.35) {
                                    RenderSystem.setShaderTexture(0, CHARGE_01);
                                    event.getGuiGraphics().blit(CHARGE_01, x - 16, height / 2 - 16, 0, 0, 32, 32, 32, 32);
                                } else if (charge >= 0.3) {
                                    RenderSystem.setShaderTexture(0, CHARGE_02);
                                    event.getGuiGraphics().blit(CHARGE_02, x - 17, height / 2 - 17, 0, 0, 34, 34, 34, 34);
                                } else if (charge >= 0.25) {
                                    RenderSystem.setShaderTexture(0, CHARGE_03);
                                    event.getGuiGraphics().blit(CHARGE_03, x - 18, height / 2 - 18, 0, 0, 36, 36, 36, 36);
                                } else if (charge >= 0.2) {
                                    RenderSystem.setShaderTexture(0, CHARGE_04);
                                    event.getGuiGraphics().blit(CHARGE_04, x - 19, height / 2 - 19, 0, 0, 38, 38, 38, 38);
                                } else if (charge >= 0.15) {
                                    RenderSystem.setShaderTexture(0, CHARGE_05);
                                    event.getGuiGraphics().blit(CHARGE_05, x - 20, height / 2 - 20, 0, 0, 40, 40, 40, 40);
                                } else if (charge >= 0.1) {
                                    RenderSystem.setShaderTexture(0, CHARGE_06);
                                    event.getGuiGraphics().blit(CHARGE_06, x - 21, height / 2 - 21, 0, 0, 42, 42, 42, 42);
                                } else if (charge >= 0.05) {
                                    RenderSystem.setShaderTexture(0, CHARGE_07);
                                    event.getGuiGraphics().blit(CHARGE_07, x - 22, height / 2 - 22, 0, 0, 44, 44, 44, 44);
                                } else {
                                    RenderSystem.setShaderTexture(0, CHARGE_08);
                                    event.getGuiGraphics().blit(CHARGE_08, x - 23, height / 2 - 23, 0, 0, 46, 46, 46, 46);
                                }
                            }
                            event.setCanceled(true);
                            GlStateManager._enableCull();
                            GlStateManager._depthMask(true);
                            RenderSystem.disableBlend();
                            mc.getProfiler().pop();
                            MinecraftForge.EVENT_BUS.post(new RenderGuiOverlayEvent.Post(event.getWindow(), event.getGuiGraphics(), event.getPartialTick(), VanillaGuiOverlay.CROSSHAIR.type()));
                        }
                    }
                }
            }
        }
    }
}
