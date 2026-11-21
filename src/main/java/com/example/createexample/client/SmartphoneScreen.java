package com.example.createexample.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;

/**
 * The smartphone home screen. Shows what a phone shows:
 * clock, day counter, position, facing and a battery level
 * (mapped from the player's food bar). Purely client-side,
 * the world keeps running while you check it.
 */
public class SmartphoneScreen extends Screen {

        private static final int PHONE_W = 145;
        private static final int PHONE_H = 205;

        public static void open() {
                Minecraft.getInstance().setScreen(new SmartphoneScreen());
        }

        public SmartphoneScreen() {
                super(Component.translatable("gui.createexample.smartphone.title"));
        }

        @Override
        public boolean isPauseScreen() {
                return false; // the world keeps running, just like a real phone
        }

        @Override
        public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
                // Dim the world behind the phone
                g.fill(0, 0, this.width, this.height, 0xB0101018);

                Minecraft mc = Minecraft.getInstance();
                ClientLevel level = mc.level;

                int x = (this.width - PHONE_W) / 2;
                int y = (this.height - PHONE_H) / 2;

                // Bezel and screen
                g.fill(x - 4, y - 4, x + PHONE_W + 4, y + PHONE_H + 4, 0xFF0B0E13);
                g.fill(x, y, x + PHONE_W, y + PHONE_H, 0xFF141B25);

                super.render(g, mouseX, mouseY, partialTick);

                if (mc.player == null || level == null) {
                        return;
                }

                // --- status bar: clock left, battery right ---
                long dayTime = level.getDayTime();
                int ticks = (int) (dayTime % 24000L);
                int hours = (ticks / 1000 + 6) % 24;
                int minutes = (int) ((ticks % 1000) * 60L / 1000L);
                String clock = String.format("%02d:%02d", hours, minutes);
                int battery = mc.player.getFoodData().getFoodLevel() * 5; // 20 food = 100%
                String batteryText = battery + "%";

                g.fill(x, y, x + PHONE_W, y + 15, 0xFF1E2937);
                g.drawString(this.font, clock, x + 8, y + 4, 0xFFE5EDF7);
                g.drawString(this.font, batteryText, x + PHONE_W - 8 - this.font.width(batteryText), y + 4, 0xFF86E29B);

                // --- app title ---
                g.drawString(this.font, this.title, x + 12, y + 26, 0xFFE5EDF7);

                // --- info rows ---
                int ty = y + 46;
                ty = row(g, x, ty, "gui.createexample.smartphone.day",
                                Component.translatable("gui.createexample.smartphone.day", dayTime / 24000L + 1).getString());
                ty = row(g, x, ty, "gui.createexample.smartphone.position",
                                String.format("%.0f, %.0f, %.0f", mc.player.getX(), mc.player.getY(), mc.player.getZ()));
                ty = row(g, x, ty, "gui.createexample.smartphone.facing",
                                Component.translatable("gui.createexample.dir." + mc.player.getDirection().getName()).getString());
                ty = row(g, x, ty, "gui.createexample.smartphone.biome",
                                level.getBiome(mc.player.blockPosition()).unwrapKey()
                                                .map(key -> key.location().getPath())
                                                .orElse("?"));
                ty = row(g, x, ty, "gui.createexample.smartphone.battery", batteryText);

                // --- home button on the bottom bezel ---
                g.fill(x + PHONE_W / 2 - 12, y + PHONE_H - 7, x + PHONE_W / 2 + 12, y + PHONE_H - 5, 0xFF3A4657);

                g.drawCenteredString(this.font, Component.translatable("gui.createexample.close_hint"),
                                this.width / 2, y + PHONE_H + 16, 0xFF8892A6);
        }

        private int row(GuiGraphics g, int x, int y, String labelKey, String value) {
                g.drawString(this.font, Component.translatable(labelKey), x + 12, y, 0xFF8A9AB4);
                g.drawString(this.font, value, x + PHONE_W - 12 - this.font.width(value), y, 0xFFE5EDF7);
                return y + 17;
        }
}
