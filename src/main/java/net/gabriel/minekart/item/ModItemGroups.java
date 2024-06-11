package net.gabriel.minekart.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.gabriel.minekart.Minekart;
import net.gabriel.minekart.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MINEKART_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Minekart.MOD_ID, "minekart"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.minekart"))
                    .icon(() -> new ItemStack(Items.BLUE_ICE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.VANILLA_ICE_CREAM);
                        entries.add(ModItems.INVISAPPLE);
                        // entries.add(MysteryBox.MYSTERY_BLOCK);
                        entries.add(ModItems.WIND_BURST);
                        entries.add(ModItems.FIREWORK);
                        entries.add(ModItems.ARROW_RAIN);
                        entries.add(ModBlocks.MYSTERY_BLOCK);

                    }).build());

    public static void registerItemGroups() {
        Minekart.LOGGER.info("Registering item groups for " + Minekart.MOD_ID);
    }
}

