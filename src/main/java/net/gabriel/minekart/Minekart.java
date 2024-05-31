package net.gabriel.minekart;

import net.fabricmc.api.ModInitializer;
import net.gabriel.minekart.item.ModItemGroups;
import net.gabriel.minekart.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.minecraft.server.command.CommandManager.*;

public class Minekart implements ModInitializer {
	public static final String MOD_ID = "minekart";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
	}

}