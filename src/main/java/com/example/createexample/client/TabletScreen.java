package com.example.createexample.client;

import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.kinetics.KineticNetwork;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * The diagnostic tablet screen. Reads the kinetic machine the player is
 * looking at (or the nearest one within 6 blocks) and shows its speed,
 * stress usage and the health of the whole rotation network -
 * capacity, load, headroom and machine count, with a load bar.
 */
public class TabletScreen extends Screen {

        private static final int TAB_W = 235;
        private static final int TAB_H = 175;

        private KineticBlockEntity target;
        private int scanCooldown = 0;

        public static void open() {
                Minecraft.getInstance().setScreen(new TabletScreen());
        }

        public TabletScreen() {
                super(Component.translatable("gui.createexample.tablet.title"));
        }

        @Override
        public boolean isPauseScreen() {
                return false;
        }

        @Override
        public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
                // Refresh the scanned machine roughly twice a second
                if (--scanCooldown <= 0) {
                        scanForTarget();
                        scanCooldown = 10;
                }

                g.fill(0, 0, this.width, this.height, 0xB0101018);

                Minecraft mc = Minecraft.getInstance();
                int x = (this.width - TAB_W) / 2;
                int y = (this.height - TAB_H) / 2;

                // Bezel and screen
                g.fill(x - 4, y - 4, x + TAB_W + 4, y + TAB_H + 4, 0xFF20281F);
                g.fill(x, y, x + TAB_W, y + TAB_H, 0xFF0F1710);

                super.render(g, mouseX, mouseY, partialTick);

                g.drawString(this.font, this.title, x + 12, y + 10, 0xFFB9E4BE);

                if (mc.level == null || target == null || target.isRemoved()) {
                        g.drawCenteredString(this.font,
                                        Component.translatable("gui.createexample.tablet.no_target"),
                                        this.width / 2, y + 72, 0xFFE0A24B);
                        g.drawCenteredString(this.font,
                                        Component.translatable("gui.createexample.tablet.hint"),
                                        this.width / 2, y + 86, 0xFF76836F);
                } else {
                        var state = target.getBlockState();
                        var block = state.getBlock();
                        float speed = target.getSpeed();
                        double usage = Math.abs(speed) * BlockStressValues.getImpact(block);

                        int ty = y + 30;
                        ty = row(g, x, ty, "gui.createexample.tablet.machine", block.getName().getString());
                        ty = row(g, x, ty, "gui.createexample.tablet.speed", String.format("%.1f RPM", speed));
                        ty = row(g, x, ty, "gui.createexample.tablet.impact", String.format("%.1f SU", usage));

                        g.fill(x + 10, ty + 3, x + TAB_W - 10, ty + 4, 0xFF2C3A2E);
                        ty += 14;

                        if (target.hasNetwork()) {
                                KineticNetwork network = target.getOrCreateNetwork();
                                float capacity = network.calculateCapacity();
                                float load = network.calculateStress();
                                int members = network.getSize();

                                ty = row(g, x, ty, "gui.createexample.tablet.capacity", String.format("%.0f SU", capacity));
                                ty = row(g, x, ty, "gui.createexample.tablet.load", String.format("%.0f SU", load));
                                ty = row(g, x, ty, "gui.createexample.tablet.headroom",
                                                String.format("%.0f SU", Math.max(0, capacity - load)));
                                ty = row(g, x, ty, "gui.createexample.tablet.machines", String.valueOf(members));

                                // Network load bar: green -> amber -> red
                                int barX = x + 12;
                                int barW = TAB_W - 24;
                                int barY = ty + 10;
                                g.fill(barX - 1, barY - 1, barX + barW + 1, barY + 7, 0xFF050805);
                                int filled = capacity > 0 ? (int) (Math.min(1.0, load / capacity) * barW) : 0;
                                int barColor = filled > barW * 85 / 100 ? 0xFFE05B4B
                                                : filled > barW * 60 / 100 ? 0xFFE0A24B : 0xFF6BCB77;
                                g.fill(barX, barY, barX + Math.max(0, filled), barY + 6, barColor);
                        } else {
                                g.drawString(this.font, Component.translatable("gui.createexample.tablet.no_network"),
                                                x + 12, ty, 0xFF76836F);
                        }
                }

                g.drawCenteredString(this.font, Component.translatable("gui.createexample.close_hint"),
                                this.width / 2, y + TAB_H + 16, 0xFF71806E);
        }

        /** Find the kinetic machine being looked at, else the nearest one within 6 blocks. */
        private void scanForTarget() {
                Minecraft mc = Minecraft.getInstance();
                target = null;
                if (mc.level == null || mc.player == null) {
                        return;
                }

                HitResult hit = mc.hitResult;
                if (hit != null && hit.getType() == HitResult.Type.BLOCK && hit instanceof BlockHitResult blockHit) {
                        if (mc.level.getBlockEntity(blockHit.getBlockPos()) instanceof KineticBlockEntity kbe
                                        && !kbe.isRemoved()) {
                                target = kbe;
                                return;
                        }
                }

                BlockPos center = mc.player.blockPosition();
                double bestDist = Double.MAX_VALUE;
                for (BlockPos pos : BlockPos.betweenClosed(center.offset(-6, -6, -6), center.offset(6, 6, 6))) {
                        if (mc.level.getBlockEntity(pos) instanceof KineticBlockEntity kbe && !kbe.isRemoved()) {
                                double dist = pos.distSqr(center);
                                if (dist < bestDist) {
                                        bestDist = dist;
                                        target = kbe;
                                }
                        }
                }
        }

        private int row(GuiGraphics g, int x, int y, String labelKey, String value) {
                g.drawString(this.font, Component.translatable(labelKey), x + 12, y, 0xFF7FA98A);
                g.drawString(this.font, value, x + TAB_W - 12 - this.font.width(value), y, 0xFFE4F0E4);
                return y + 16;
        }
}
