package net.gabriel.minekart.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gabriel.minekart.Minekart;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFoodComponents {
    public static final FoodComponent VANILLA_ICE_CREAM = new FoodComponent.Builder().hunger(3).saturationModifier(1f).alwaysEdible().build();

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Minekart.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Minekart.LOGGER.info("Registering Mod Items for " + Minekart.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodAndDrinksItemGroup);
    }
}
