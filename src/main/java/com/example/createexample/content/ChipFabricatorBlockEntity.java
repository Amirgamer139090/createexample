package com.example.createexample.content;

import java.util.List;
import java.util.Optional;

import com.example.createexample.registry.ModRecipes;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.behaviour.DirectBeltInputBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.item.ItemHelper;
import com.simibubi.create.foundation.sound.SoundScapes;
import com.simibubi.create.foundation.sound.SoundScapes.AmbienceGroup;

import net.createmod.catnip.math.VecHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

/**
 * The Chip Fabricator.
 *
 * A kinetic machine modeled after Create's Millstone: feed it a silicon
 * wafer, spin it with a shaft or cogwheel from below, and it slowly
 * fabricates microchips. Faster rotation = faster production.
 */
public class ChipFabricatorBlockEntity extends KineticBlockEntity implements Clearable {

	public ItemStackHandler inputInv;
	public ItemStackHandler outputInv;
	public IItemHandler capability;
	public int timer;
	private ChipFabricatingRecipe lastRecipe;

	public ChipFabricatorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		inputInv = new ItemStackHandler(1);
		outputInv = new ItemStackHandler(9);
		capability = new ChipFabricatorInventoryHandler();
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		// Lets belts and depots insert items from above
		behaviours.add(new DirectBeltInputBehaviour(this));
		super.addBehaviours(behaviours);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void tickAudio() {
		super.tickAudio();

		if (getSpeed() == 0)
			return;
		if (inputInv.getStackInSlot(0)
			.isEmpty())
			return;

		float pitch = Mth.clamp((Math.abs(getSpeed()) / 512f) + .55f, .85f, 1f);
		SoundScapes.play(AmbienceGroup.MILLING, worldPosition, pitch);
	}

	@Override
	public void tick() {
		super.tick();

		if (getSpeed() == 0)
			return;
		for (int i = 0; i < outputInv.getSlots(); i++)
			if (outputInv.getStackInSlot(i)
				.getCount() == outputInv.getSlotLimit(i))
				return;

		if (timer > 0) {
			timer -= getProcessingSpeed();

			if (level.isClientSide) {
				spawnParticles();
				return;
			}
			if (timer <= 0)
				process();
			return;
		}

		if (inputInv.getStackInSlot(0)
			.isEmpty())
			return;

		RecipeWrapper inventoryIn = new RecipeWrapper(inputInv);
		if (lastRecipe == null || !lastRecipe.matches(inventoryIn, level)) {
			Optional<RecipeHolder<ChipFabricatingRecipe>> recipe = ModRecipes.find(inventoryIn, level);
			if (recipe.isEmpty()) {
				timer = 100;
				sendData();
			} else {
				lastRecipe = recipe.get().value();
				timer = lastRecipe.getProcessingDuration();
				sendData();
			}
			return;
		}

		timer = lastRecipe.getProcessingDuration();
		sendData();
	}

	@Override
	public void invalidate() {
		super.invalidate();
		invalidateCapabilities();
	}

	@Override
	public void clearContent() {
		for (int i = 0; i < inputInv.getSlots(); i++)
			inputInv.setStackInSlot(i, ItemStack.EMPTY);
		for (int i = 0; i < outputInv.getSlots(); i++)
			outputInv.setStackInSlot(i, ItemStack.EMPTY);
	}

	@Override
	public void destroy() {
		super.destroy();
		ItemHelper.dropContents(level, worldPosition, inputInv);
		ItemHelper.dropContents(level, worldPosition, outputInv);
	}

	private void process() {
		RecipeWrapper inventoryIn = new RecipeWrapper(inputInv);

		if (lastRecipe == null || !lastRecipe.matches(inventoryIn, level)) {
			Optional<RecipeHolder<ChipFabricatingRecipe>> recipe = ModRecipes.find(inventoryIn, level);
			if (recipe.isEmpty())
				return;
			lastRecipe = recipe.get().value();
		}

		ItemStack stackInSlot = inputInv.getStackInSlot(0);
		stackInSlot.shrink(1);
		inputInv.setStackInSlot(0, stackInSlot);
		lastRecipe.rollResults(level.random)
			.forEach(stack -> ItemHandlerHelper.insertItemStacked(outputInv, stack, false));

		sendData();
		setChanged();
	}

	public void spawnParticles() {
		ItemStack stackInSlot = inputInv.getStackInSlot(0);
		if (stackInSlot.isEmpty())
			return;

		ItemParticleOption data = new ItemParticleOption(ParticleTypes.ITEM, stackInSlot);
		float angle = level.random.nextFloat() * 360;
		Vec3 offset = new Vec3(0, 0, 0.5f);
		offset = VecHelper.rotate(offset, angle, Axis.Y);
		Vec3 target = VecHelper.rotate(offset, getSpeed() > 0 ? 25 : -25, Axis.Y);

		Vec3 center = offset.add(VecHelper.getCenterOf(worldPosition));
		target = VecHelper.offsetRandomly(target.subtract(offset), level.random, 1 / 128f);
		level.addParticle(data, center.x, center.y, center.z, target.x, target.y, target.z);
	}

	@Override
	public void write(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket) {
		compound.putInt("Timer", timer);
		compound.put("InputInventory", inputInv.serializeNBT(registries));
		compound.put("OutputInventory", outputInv.serializeNBT(registries));
		super.write(compound, registries, clientPacket);
	}

	@Override
	protected void read(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket) {
		timer = compound.getInt("Timer");
		inputInv.deserializeNBT(registries, compound.getCompound("InputInventory"));
		outputInv.deserializeNBT(registries, compound.getCompound("OutputInventory"));
		super.read(compound, registries, clientPacket);
	}

	public int getProcessingSpeed() {
		return Mth.clamp((int) Math.abs(getSpeed() / 16f), 1, 512);
	}

	private boolean canProcess(ItemStack stack) {
		ItemStackHandler tester = new ItemStackHandler(1);
		tester.setStackInSlot(0, stack);
		RecipeWrapper inventoryIn = new RecipeWrapper(tester);

		if (lastRecipe != null && lastRecipe.matches(inventoryIn, level))
			return true;
		return ModRecipes.find(inventoryIn, level)
			.isPresent();
	}

	/** Input slots accept only valid ingredients, output slots can only be extracted. */
	private class ChipFabricatorInventoryHandler extends CombinedInvWrapper {

		public ChipFabricatorInventoryHandler() {
			super(inputInv, outputInv);
		}

		@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			if (outputInv == getHandlerFromIndex(getIndexForSlot(slot)))
				return false;
			return canProcess(stack) && super.isItemValid(slot, stack);
		}

		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			if (outputInv == getHandlerFromIndex(getIndexForSlot(slot)))
				return stack;
			if (!isItemValid(slot, stack))
				return stack;
			return super.insertItem(slot, stack, simulate);
		}

		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate) {
			if (inputInv == getHandlerFromIndex(getIndexForSlot(slot)))
				return ItemStack.EMPTY;
			return super.extractItem(slot, amount, simulate);
		}
	}
}
