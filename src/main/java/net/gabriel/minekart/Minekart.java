package net.gabriel.minekart;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.gabriel.minekart.item.ModFoodComponents;
import net.gabriel.minekart.item.ModItemGroups;
import net.gabriel.minekart.item.ModItems;
import net.gabriel.minekart.block.ModBlocks;
import net.gabriel.minekart.util.ServerScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Minekart implements ModInitializer {
	public static final String MOD_ID = "minekart";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModFoodComponents.registerModItems();


		ServerTickEvents.END_SERVER_TICK.register(tick -> ServerScheduler.tick());
		ServerWorldEvents.UNLOAD.register((server, world) -> ServerScheduler.runAll());
	}
}