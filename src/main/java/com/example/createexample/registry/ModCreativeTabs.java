package com.example.createexample.registry;

import java.util.List;

import com.example.createexample.CreateExampleAddon;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * The creative tab of the addon, placed next to the other item groups.
 */
public class ModCreativeTabs {

	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
		DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateExampleAddon.MOD_ID);

	/** Items in the order they should appear in the creative tab. */
	public static final List<DeferredItem<Item>> TAB_ITEMS = List.of(
		ModItems.SILICON_INGOT, ModItems.SILICON_WAFER, ModItems.MICROCHIP, ModItems.PCB,
		ModItems.CPU, ModItems.RAM_STICK, ModItems.MOTHERBOARD, ModItems.GPU,
		ModItems.SOUND_CARD, ModItems.NETWORK_CARD, ModItems.PSU,
		ModItems.HDD, ModItems.SSD, ModItems.M2_SSD,
		ModItems.FLOPPY_DISK, ModItems.USB_FLASH, ModItems.EXTERNAL_HDD,
		ModItems.VIBRATION_MOTOR, ModItems.LCD_SCREEN, ModItems.PHONE_SHELL,
		ModItems.KEYBOARD, ModItems.MOUSE, ModItems.HEADPHONES,
		ModItems.MICROPHONE, ModItems.WEBCAM,
		ModItems.SMARTPHONE, ModItems.TABLET,
		ModItems.CHIP_FABRICATOR_ITEM, ModItems.COMPUTER_CASE_ITEM,
		ModItems.MONITOR_ITEM, ModItems.SPEAKERS_ITEM);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
		CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.createexample"))
			.icon(() -> ModItems.CPU.get().getDefaultInstance())
			.displayItems((parameters, output) -> TAB_ITEMS.forEach(item -> output.accept(item.get())))
			.build());

	private ModCreativeTabs() {}
}
