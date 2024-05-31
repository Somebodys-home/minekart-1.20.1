package net.gabriel.minekart.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gabriel.minekart.Minekart;
import net.gabriel.minekart.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
public class SpeedBump extends Item {

    public SpeedBump(Settings settings) {
        super(settings);
    }

    public static final Item SPEED_BUMP = registerItem("speed_bump", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Minekart.MOD_ID, name), item);
    }

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(SPEED_BUMP);
    }

    public static void registerModItems() {
        Minekart.LOGGER.info("Registering Mod Items for " + Minekart.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }

}
