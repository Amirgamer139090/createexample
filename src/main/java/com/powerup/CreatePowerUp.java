package com.powerup;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(CreatePowerUp.MOD_ID)
public final class CreatePowerUp {
    public static final String MOD_ID = "create_powerup";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreatePowerUp(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Create: Power up! initialized");
    }
}
