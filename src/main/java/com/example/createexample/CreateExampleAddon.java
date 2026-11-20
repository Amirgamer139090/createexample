package com.example.createexample;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import com.example.createexample.registry.ModBlockEntities;
import com.example.createexample.registry.ModBlocks;
import com.example.createexample.registry.ModCreativeTabs;
import com.example.createexample.registry.ModItems;
import com.example.createexample.registry.ModRecipes;

/**
 * Entry point of the addon.
 *
 * The addon registers everything through NeoForge DeferredRegisters,
 * then hooks into Create's public API for stress values.
 */
@Mod(CreateExampleAddon.MOD_ID)
public class CreateExampleAddon {

        public static final String MOD_ID = "createexample";
        public static final Logger LOGGER = LogUtils.getLogger();

        public CreateExampleAddon(IEventBus modEventBus, ModContainer modContainer) {
                // Register all deferred registers on the MOD event bus
                ModBlocks.BLOCKS.register(modEventBus);
                ModItems.ITEMS.register(modEventBus);
                ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
                ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
                ModRecipes.register(modEventBus);

                // Expose the machine's inventory to hoppers, belts, funnels...
                modEventBus.addListener(ModBlockEntities::registerCapabilities);

                // Register Create stress values once the blocks exist
                modEventBus.addListener(this::commonSetup);
        }

        private void commonSetup(final FMLCommonSetupEvent event) {
                event.enqueueWork(() -> {
                        // Tell Create how much stress our machine consumes.
                        // 12 SU is a bit more than Create's mechanical press (4 SU at 1 rpm? see guide).
                        com.simibubi.create.api.stress.BlockStressValues.IMPACTS
                                .register(ModBlocks.CHIP_FABRICATOR.get(), () -> 12.0);
                });
        }

        /** Shorthand for namespaced resource locations, e.g. createexample:microchip */
        public static ResourceLocation id(String path) {
                return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
        }
}
