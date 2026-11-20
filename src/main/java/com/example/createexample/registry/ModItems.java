package com.example.createexample.registry;

import com.example.createexample.CreateExampleAddon;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * All items of the addon.
 *
 * A few building blocks for electronics, then PC components, storage
 * devices, peripherals and finally the assembled devices.
 */
public class ModItems {

        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateExampleAddon.MOD_ID);

        // ---- Materials ----
        public static final DeferredItem<Item> SILICON_INGOT = ITEMS.registerSimpleItem("silicon_ingot");
        public static final DeferredItem<Item> SILICON_WAFER = ITEMS.registerSimpleItem("silicon_wafer");
        public static final DeferredItem<Item> MICROCHIP = ITEMS.registerSimpleItem("microchip");
        public static final DeferredItem<Item> PCB = ITEMS.registerSimpleItem("pcb");

        // ---- Core components ----
        public static final DeferredItem<Item> CPU = ITEMS.registerSimpleItem("cpu");
        public static final DeferredItem<Item> RAM_STICK = ITEMS.registerSimpleItem("ram_stick");
        public static final DeferredItem<Item> MOTHERBOARD = ITEMS.registerSimpleItem("motherboard");
        public static final DeferredItem<Item> GPU = ITEMS.registerSimpleItem("gpu");
        public static final DeferredItem<Item> SOUND_CARD = ITEMS.registerSimpleItem("sound_card");
        public static final DeferredItem<Item> NETWORK_CARD = ITEMS.registerSimpleItem("network_card");
        public static final DeferredItem<Item> PSU = ITEMS.registerSimpleItem("psu");

        // ---- Storage ----
        public static final DeferredItem<Item> HDD = ITEMS.registerSimpleItem("hdd");
        public static final DeferredItem<Item> SSD = ITEMS.registerSimpleItem("ssd");
        public static final DeferredItem<Item> M2_SSD = ITEMS.registerSimpleItem("m2_ssd");
        public static final DeferredItem<Item> FLOPPY_DISK = ITEMS.registerSimpleItem("floppy_disk");
        public static final DeferredItem<Item> USB_FLASH = ITEMS.registerSimpleItem("usb_flash");
        public static final DeferredItem<Item> EXTERNAL_HDD = ITEMS.registerSimpleItem("external_hdd");

        // ---- Mobile parts ----
        public static final DeferredItem<Item> VIBRATION_MOTOR = ITEMS.registerSimpleItem("vibration_motor");
        public static final DeferredItem<Item> LCD_SCREEN = ITEMS.registerSimpleItem("lcd_screen");
        public static final DeferredItem<Item> PHONE_SHELL = ITEMS.registerSimpleItem("phone_shell");

        // ---- Peripherals ----
        public static final DeferredItem<Item> KEYBOARD = ITEMS.registerSimpleItem("keyboard");
        public static final DeferredItem<Item> MOUSE = ITEMS.registerSimpleItem("mouse");
        public static final DeferredItem<Item> HEADPHONES = ITEMS.registerSimpleItem("headphones");
        public static final DeferredItem<Item> MICROPHONE = ITEMS.registerSimpleItem("microphone");
        public static final DeferredItem<Item> WEBCAM = ITEMS.registerSimpleItem("webcam");

        // ---- Devices ----
        public static final DeferredItem<Item> SMARTPHONE = ITEMS.registerSimpleItem("smartphone");
        public static final DeferredItem<Item> TABLET = ITEMS.registerSimpleItem("tablet");

        // ---- Block items ----
        public static final DeferredItem<BlockItem> CHIP_FABRICATOR_ITEM = ITEMS.registerSimpleBlockItem("chip_fabricator",
                ModBlocks.CHIP_FABRICATOR);
        public static final DeferredItem<BlockItem> COMPUTER_CASE_ITEM = ITEMS.registerSimpleBlockItem("computer_case",
                ModBlocks.COMPUTER_CASE);
        public static final DeferredItem<BlockItem> MONITOR_ITEM = ITEMS.registerSimpleBlockItem("monitor", ModBlocks.MONITOR);
        public static final DeferredItem<BlockItem> SPEAKERS_ITEM = ITEMS.registerSimpleBlockItem("speakers",
                ModBlocks.SPEAKERS);

        private ModItems() {}
}
