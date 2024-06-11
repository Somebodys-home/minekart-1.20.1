package net.gabriel.minekart;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.gabriel.minekart.block.MysteryBlock;
import net.gabriel.minekart.item.ModFoodComponents;
import net.gabriel.minekart.item.ModItemGroups;
import net.gabriel.minekart.item.ModItems;
import net.gabriel.minekart.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Minekart implements ModInitializer {
	public static final String MOD_ID = "minekart";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModFoodComponents.registerModItems();

		Registry.register(Registries.BLOCK, new Identifier("minekart", "mystery_block"), MysteryBlock.MYSTERY_BLOCK);
		Registry.register(Registries.ITEM, new Identifier("minekart", "mystery_block"), new BlockItem(MysteryBlock.MYSTERY_BLOCK, new FabricItemSettings()));
	}
}