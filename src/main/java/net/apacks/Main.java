package net.apacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main implements ModInitializer , ClientTickEvents.StartTick{
	public static final Logger LOGGER = LoggerFactory.getLogger("apacks");
	public static boolean flyIsEnabled=false;
	public static boolean noFallIsEnabled=false;


	public static void init(){
		Main.LOGGER.info("(Initializer) onTick Loaded");
	}
	@Override
	public void onStartTick(MinecraftClient client) {


	}

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized Apacks");
	}
}

