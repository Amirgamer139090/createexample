package com.example.createexample.content;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * The tablet: a field technician's diagnostic tool.
 * Right-click while looking at (or standing next to) any kinetic machine
 * to read its speed, stress usage and the whole network's health -
 * like a real industrial service tablet, no goggles required.
 */
public class TabletItem extends Item {

        public TabletItem(Properties properties) {
                super(properties);
        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
                ItemStack stack = player.getItemInHand(hand);
                if (level.isClientSide) {
                        // Client-only class; never touched on a dedicated server.
                        com.example.createexample.client.TabletScreen.open();
                }
                return InteractionResultHolder.success(stack);
        }
}
