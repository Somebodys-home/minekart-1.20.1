package net.gabriel.minekart.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.gabriel.minekart.Minekart;
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

                    }).build());



//    new Identifier(TutorialMod.MOD_ID, "ruby"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
//            .icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
//        entries.add(ModItems.RUBY);
//        entries.add(ModItems.RAW_RUBY);
//
//        entries.add(Items.DIAMOND);
//
//
//    }).build());
    public static void registerItemGroups() {
        Minekart.LOGGER.info("Registering item groups for " + Minekart.MOD_ID);
    }
}

