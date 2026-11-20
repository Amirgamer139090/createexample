package com.example.createexample.client;

import com.example.createexample.content.ChipFabricatorBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Renderer for the Chip Fabricator, modelled after the Millstone renderer.
 * The static block model (shell) is drawn by the chunk renderer; this class
 * only draws the inner cogwheel and rotates it around the Y axis based on
 * the kinetic speed of the machine.
 */
public class ChipFabricatorRenderer extends KineticBlockEntityRenderer<ChipFabricatorBlockEntity> {

    public ChipFabricatorRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected SuperByteBuffer getRotatedModel(ChipFabricatorBlockEntity be, BlockState state) {
        return CachedBuffers.partial(ModPartialModels.CHIP_FABRICATOR_COG, state);
    }
}
