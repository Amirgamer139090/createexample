package com.example.createexample.content;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * The smartphone: a personal device assembled in the Chip Fabricator chain.
 * Right-click to pull it out and check the clock, your position and more.
 * The screen itself is client-only; servers simply acknowledge the interaction.
 */
public class SmartphoneItem extends Item {

        public SmartphoneItem(Properties properties) {
                super(properties);
        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
                ItemStack stack = player.getItemInHand(hand);
                if (level.isClientSide) {
                        // Client-only class; never touched on a dedicated server.
                        com.example.createexample.client.SmartphoneScreen.open();
                }
                return InteractionResultHolder.success(stack);
        }
}
