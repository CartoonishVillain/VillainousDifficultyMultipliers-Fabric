package com.cartoonishvillain.vdm;

import com.cartoonishvillain.vdm.commands.*;
import com.cartoonishvillain.vdm.config.VDMConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.LevelData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class VDM implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("vdm");
	public static VDMConfig config;
	public static boolean isCalyxLoaded = false;
	public static LevelData levelData = null;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		AutoConfig.register(VDMConfig.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(VDMConfig.class).getConfig();

		CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
			activateMultiplierCommand.register(dispatcher);
			CheckMultiplierCommand.register(dispatcher);
			deactivateMultiplierCommand.register(dispatcher);
			activeListCommand.register(dispatcher);
			CommandManager.register(dispatcher);
		}));

		ServerTickEvents.END_WORLD_TICK.register(WorldTickListener.getInstance());
	}

	public static class WorldTickListener implements ServerTickEvents.EndWorldTick{
		private static final WorldTickListener INSTANCE = new WorldTickListener();

		public static WorldTickListener getInstance() {return INSTANCE;}

		@Override
		public void onEndTick(ServerLevel world) {
			if(levelData == null)
				levelData = world.getLevelData();
		}
	}


}
