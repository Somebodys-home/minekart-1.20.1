package net.gabriel.minekart.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gabriel.minekart.Minekart;
import net.gabriel.minekart.item.custom.SpeedBumpItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item VANILLA_ICE_CREAM = registerItem("vanilla_ice_cream", new Item(new FabricItemSettings().food(ModFoodComponents.VANILLA_ICE_CREAM)));
    public static final Item SPEED_BUMP = registerItem("speed_bump", new SpeedBumpItem(new FabricItemSettings()));

    private final String id;
    private final Item item;

    public ModItems(String id, Item item) {
        this.id = id;
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public static void addItemsToFoodAndDrinksItemGroup(FabricItemGroupEntries entries) {
        entries.add(VANILLA_ICE_CREAM);
    }

    public static void addItemsToToolItemGroup(FabricItemGroupEntries entries) {
        entries.add(SPEED_BUMP);
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
