package com.example.createexample.registry;

import java.util.function.Supplier;

import com.example.createexample.CreateExampleAddon;
import com.example.createexample.content.ChipFabricatorBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * All block entity types of the addon.
 */
public class ModBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
		DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreateExampleAddon.MOD_ID);

	public static final Supplier<BlockEntityType<ChipFabricatorBlockEntity>> CHIP_FABRICATOR =
		BLOCK_ENTITIES.register("chip_fabricator",
			() -> BlockEntityType.Builder
				.of(ChipFabricatorBlockEntity::new, ModBlocks.CHIP_FABRICATOR.get())
				.build(null));

	/**
	 * Expose the machine's inventory to hoppers, funnels and Create belts.
	 * Must be called on the MOD event bus from the main mod class context.
	 */
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CHIP_FABRICATOR.get(),
			(be, context) -> be.capability);
	}

	private ModBlockEntities() {}
}
