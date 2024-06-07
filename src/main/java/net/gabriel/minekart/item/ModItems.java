package net.gabriel.minekart.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gabriel.minekart.Minekart;
import net.gabriel.minekart.item.custom.ArrowRainItem;
import net.gabriel.minekart.item.custom.WindBurstItem;
import net.gabriel.minekart.item.custom.FireworkItem;
import net.gabriel.minekart.item.custom.VanillaIceCreamItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item VANILLA_ICE_CREAM = registerItem("vanilla_ice_cream", new VanillaIceCreamItem(ModFoodComponents.VANILLA_ICE_CREAM, 25));
    public static final Item WIND_BURST = registerItem("wind_burst", new WindBurstItem());
    public static final Item ARROW_RAIN = registerItem("arrow_rain", new ArrowRainItem());
    public static final Item FIREWORK = registerItem("firework", new FireworkItem());

    private final String id;
    private final Item item;

    public ModItems(String id, Item item) {
        this.id = id;
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public static final Item VANILLA_ICE_CREAM = registerItem("vanilla_ice_cream", new VanillaIceCreamItem(ModFoodComponents.VANILLA_ICE_CREAM, 25));

    public static void addItemsToFoodAndDrinksItemGroup(FabricItemGroupEntries entries) {
        entries.add(VANILLA_ICE_CREAM);
    }

    public static void addItemsToToolItemGroup(FabricItemGroupEntries entries) {
        entries.add(WIND_BURST);
        entries.add(FIREWORK);
        entries.add(ARROW_RAIN);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Minekart.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Minekart.LOGGER.info("Registering Mod Items for " + Minekart.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodAndDrinksItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToToolItemGroup);
    }
}
