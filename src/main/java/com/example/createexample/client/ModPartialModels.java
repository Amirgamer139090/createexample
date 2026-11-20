package com.example.createexample.client;

import com.example.createexample.CreateExampleAddon;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;

/**
 * Partial models rendered on top of the static block model.
 * The Chip Fabricator's inner cogwheel is one of these.
 */
public final class ModPartialModels {

	public static final PartialModel CHIP_FABRICATOR_COG =
		PartialModel.of(CreateExampleAddon.id("block/chip_fabricator/inner"));

	private ModPartialModels() {}
}
