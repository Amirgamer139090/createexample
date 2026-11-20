package com.example.createexample.content;

import com.example.createexample.registry.ModRecipes;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeParams;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;

import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;

/**
 * A recipe processed inside the Chip Fabricator.
 *
 * Modeled after Create's MillingRecipe: one item in, up to four out,
 * with a configurable processing duration.
 */
public class ChipFabricatingRecipe extends StandardProcessingRecipe<RecipeInput> {

	public ChipFabricatingRecipe(ProcessingRecipeParams params) {
		super(ModRecipes.CHIP_FABRICATING, params);
	}

	@Override
	public boolean matches(RecipeInput inv, Level world) {
		if (inv.isEmpty())
			return false;
		return ingredients.get(0)
			.test(inv.getItem(0));
	}

	@Override
	protected int getMaxInputCount() {
		return 1;
	}

	@Override
	protected int getMaxOutputCount() {
		return 4;
	}

	@Override
	protected boolean canSpecifyDuration() {
		return true;
	}
}
