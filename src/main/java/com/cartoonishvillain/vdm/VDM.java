package com.cartoonishvillain.vdm;

import com.cartoonishvillain.vdm.commands.*;
import com.cartoonishvillain.vdm.components.ComponentTicker;
import com.cartoonishvillain.vdm.components.LevelComponent;
import com.cartoonishvillain.vdm.components.WorldComponent;
import com.cartoonishvillain.vdm.config.VDMConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.LevelData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import static com.cartoonishvillain.vdm.components.ComponentStarter.LEVELINSTANCE;
import static com.cartoonishvillain.vdm.components.ComponentStarter.WORLDINSTANCE;

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

		registerPackets();

		isCalyxLoaded = FabricLoader.getInstance().isModLoaded("immortuoscalyx");


		ServerTickEvents.END_WORLD_TICK.register(WorldTickListener.getInstance());
	}

	public static void registerPackets(){

		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation("vdm", "chatpacket"), (((server, player, handler, buffer, responseSender) -> {
			int length = buffer.readInt();
			String name = (String) buffer.readCharSequence(length, Charset.defaultCharset());
			length = buffer.readInt();
			String message = (String) buffer.readCharSequence(length, Charset.defaultCharset());
			server.getPlayerList().broadcastMessage(new TextComponent(name + message.toUpperCase(Locale.ROOT)), ChatType.CHAT, player.getUUID());
		})));
	}

	public static class WorldTickListener implements ServerTickEvents.EndWorldTick{
		private static final WorldTickListener INSTANCE = new WorldTickListener();

		public static WorldTickListener getInstance() {return INSTANCE;}

		@Override
		public void onEndTick(ServerLevel world) {
			if(levelData == null)
				levelData = world.getLevelData();

			WorldComponent worldComponent = WORLDINSTANCE.get(world);
			LevelComponent levelComponent = LEVELINSTANCE.get(levelData);

			if(world.isDay() && worldComponent.isNight()){
				worldComponent.setisNight(false);
				int chance =  world.getRandom().nextInt(9);
				if (chance <= 1 && levelComponent.isCelebration())  {
					worldComponent.setCelebrationStatus(true);
					ComponentTicker.broadcast(world.getServer(), new TranslatableComponent("info.villainousdifficultymultipliers.party").withStyle(ChatFormatting.LIGHT_PURPLE));
				}
			}else if(!world.isDay() && !worldComponent.isNight()){
				worldComponent.setisNight(true);
				if(worldComponent.getCelebrationStatus()) ComponentTicker.broadcast(world.getServer(), new TranslatableComponent("info.villainousdifficultymultipliers.partyend").withStyle(ChatFormatting.LIGHT_PURPLE));
				worldComponent.setCelebrationStatus(false);
			}

			if(worldComponent.getCelebrationStatus() && !world.isClientSide){
				ArrayList<ServerPlayer> players = (ArrayList<ServerPlayer>) world.players();
				for(Player player : players){
					if (!player.hasEffect(MobEffects.HERO_OF_THE_VILLAGE)){
						player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 20, 0, false, false));
					}
				}
			}


		}
	}




}
