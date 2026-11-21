package com.example.createexample.registry;

import java.util.function.Supplier;

import com.example.createexample.CreateExampleAddon;
import com.example.createexample.content.ChipFabricatorBlock;
import com.example.createexample.content.FrontFacingBlock;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * All blocks of the addon.
 *
 * The Chip Fabricator is a kinetic machine: it extends Create's KineticBlock,
 * which makes it participate in Create's rotation network (shafts, cogwheels...).
 */
public class ModBlocks {

        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreateExampleAddon.MOD_ID);

        // The kinetic machine. Shaft connection at the bottom, like Create's Millstone.
        public static final DeferredBlock<ChipFabricatorBlock> CHIP_FABRICATOR =
                BLOCKS.register("chip_fabricator", () -> new ChipFabricatorBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.METAL)
                        .strength(3.5f)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.METAL)
                        .noOcclusion()));

        // Decorative devices - front-facing blocks that turn toward the player.
        public static final DeferredBlock<FrontFacingBlock> COMPUTER_CASE =
                BLOCKS.register("computer_case", () -> new FrontFacingBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_GRAY)
                        .strength(2.5f)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.METAL)));

        public static final DeferredBlock<FrontFacingBlock> MONITOR =
                BLOCKS.register("monitor", () -> new FrontFacingBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_BLACK)
                        .strength(1.5f)
                        .sound(SoundType.METAL)));

        public static final DeferredBlock<FrontFacingBlock> SPEAKERS =
                BLOCKS.register("speakers", () -> new FrontFacingBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_BROWN)
                        .strength(1.5f)
                        .sound(SoundType.WOOD)));

        private ModBlocks() {}
}
