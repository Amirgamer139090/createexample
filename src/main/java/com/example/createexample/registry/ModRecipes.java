package com.example.createexample.registry;

import java.util.Optional;
import java.util.function.Supplier;

import com.example.createexample.CreateExampleAddon;
import com.example.createexample.content.ChipFabricatingRecipe;

import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Custom recipe types of the addon, built on top of Create's
 * ProcessingRecipe framework. JSON files for these live in
 * data/createexample/recipe/chip_fabricating/.
 */
public class ModRecipes {

        public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
                DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, CreateExampleAddon.MOD_ID);
        public static final DeferredRegister<RecipeType<?>> TYPES =
                DeferredRegister.create(Registries.RECIPE_TYPE, CreateExampleAddon.MOD_ID);

        public static final ResourceLocation CHIP_FABRICATING_ID = CreateExampleAddon.id("chip_fabricating");

        public static final Supplier<RecipeSerializer<ChipFabricatingRecipe>> CHIP_FABRICATING_SERIALIZER =
                SERIALIZERS.register("chip_fabricating",
                        () -> new StandardProcessingRecipe.Serializer<>(ChipFabricatingRecipe::new));

        public static final Supplier<RecipeType<ChipFabricatingRecipe>> CHIP_FABRICATING_TYPE =
                TYPES.register("chip_fabricating", () -> RecipeType.simple(CHIP_FABRICATING_ID));

        /** Info object handed to ProcessingRecipe subclasses (mirrors Create's AllRecipeTypes). */
        public static final IRecipeTypeInfo CHIP_FABRICATING =
                new RecipeTypeInfo(CHIP_FABRICATING_ID, CHIP_FABRICATING_SERIALIZER, CHIP_FABRICATING_TYPE);

        public static void register(IEventBus modEventBus) {
                SERIALIZERS.register(modEventBus);
                TYPES.register(modEventBus);
        }

        /** Find a matching chip fabricating recipe for the given input inventory. */
        public static Optional<RecipeHolder<ChipFabricatingRecipe>> find(RecipeInput inv, Level level) {
                return level.getRecipeManager()
                        .getRecipeFor(CHIP_FABRICATING_TYPE.get(), inv, level);
        }

        /** Tiny holder satisfying Create's IRecipeTypeInfo interface. */
        private record RecipeTypeInfo(ResourceLocation id,
                Supplier<? extends RecipeSerializer<?>> serializer,
                Supplier<? extends RecipeType<?>> type) implements IRecipeTypeInfo {

                @Override
                public ResourceLocation getId() {
                        return id;
                }

                @Override
                @SuppressWarnings("unchecked")
                public <T extends RecipeSerializer<?>> T getSerializer() {
                        return (T) serializer.get();
                }

                @Override
                @SuppressWarnings("unchecked")
                public <I extends RecipeInput, R extends Recipe<I>> RecipeType<R> getType() {
                        return (RecipeType<R>) type.get();
                }
        }
}
