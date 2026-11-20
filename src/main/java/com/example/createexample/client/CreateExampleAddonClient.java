package com.example.createexample.client;

import com.example.createexample.CreateExampleAddon;
import com.example.createexample.registry.ModBlockEntities;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Client-only registrations: block entity renderers.
 */
@EventBusSubscriber(modid = CreateExampleAddon.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class CreateExampleAddonClient {

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		// Load partial models early so they are baked with the other models
		ModPartialModels.class.getName();

		event.registerBlockEntityRenderer(ModBlockEntities.CHIP_FABRICATOR.get(), ChipFabricatorRenderer::new);
	}

	private CreateExampleAddonClient() {}
}
