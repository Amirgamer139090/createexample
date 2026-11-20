package com.example.createexample.content;

import com.example.createexample.registry.ModBlockEntities;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.foundation.block.IBE;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

/**
 * The Chip Fabricator block. Place it, attach a shaft or cogwheel below it
 * and feed it silicon wafers - it fabricates microchips.
 */
public class ChipFabricatorBlock extends KineticBlock implements IBE<ChipFabricatorBlockEntity> {

	public ChipFabricatorBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AllShapes.MILLSTONE;
	}

	@Override
	public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
		return face == Direction.DOWN;
	}

	@Override
	public Axis getRotationAxis(BlockState state) {
		return Axis.Y;
	}

	/**
	 * Right-click with an empty hand: first collect all outputs, then the input.
	 */
	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
		Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (!stack.isEmpty())
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		if (level.isClientSide)
			return ItemInteractionResult.SUCCESS;

		withBlockEntityDo(level, pos, fabricator -> {
			boolean emptyOutput = true;
			IItemHandlerModifiable inv = fabricator.outputInv;
			for (int slot = 0; slot < inv.getSlots(); slot++) {
				ItemStack stackInSlot = inv.getStackInSlot(slot);
				if (!stackInSlot.isEmpty())
					emptyOutput = false;
				player.getInventory()
					.placeItemBackInInventory(stackInSlot);
				inv.setStackInSlot(slot, ItemStack.EMPTY);
			}

			if (emptyOutput) {
				inv = fabricator.inputInv;
				for (int slot = 0; slot < inv.getSlots(); slot++) {
					player.getInventory()
						.placeItemBackInInventory(inv.getStackInSlot(slot));
					inv.setStackInSlot(slot, ItemStack.EMPTY);
				}
			}

			fabricator.setChanged();
			fabricator.sendData();
		});

		return ItemInteractionResult.SUCCESS;
	}

	/**
	 * Items dropped on top of the machine are inserted (useful for automation).
	 */
	@Override
	public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) {
		super.updateEntityAfterFallOn(worldIn, entityIn);

		if (entityIn.level().isClientSide)
			return;
		if (!(entityIn instanceof ItemEntity itemEntity))
			return;
		if (!entityIn.isAlive())
			return;

		ChipFabricatorBlockEntity fabricator = getBlockEntity(worldIn, entityIn.blockPosition());
		if (fabricator == null)
			return;

		IItemHandler capability =
			fabricator.getLevel().getCapability(Capabilities.ItemHandler.BLOCK, fabricator.getBlockPos(), null);
		if (capability == null)
			return;

		ItemStack remainder = capability.insertItem(0, itemEntity.getItem(), false);
		if (remainder.isEmpty())
			itemEntity.discard();
		if (remainder.getCount() < itemEntity.getItem()
			.getCount())
			itemEntity.setItem(remainder);
	}

	@Override
	public Class<ChipFabricatorBlockEntity> getBlockEntityClass() {
		return ChipFabricatorBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends ChipFabricatorBlockEntity> getBlockEntityType() {
		return ModBlockEntities.CHIP_FABRICATOR.get();
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}
}
