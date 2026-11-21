package com.example.createexample.content;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A block whose front face points toward the player when placed,
 * exactly like the vanilla furnace. Used by the computer case,
 * monitor and speakers so they always look right in a room.
 */
public class FrontFacingBlock extends HorizontalDirectionalBlock {

        public FrontFacingBlock(Properties properties) {
                super(properties);
                registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
        }

        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
                // Front side toward the player, like a furnace.
                return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
}
