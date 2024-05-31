package net.gabriel.minekart.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gabriel.minekart.Minekart;
import net.gabriel.minekart.item.ModFoodComponents;
import net.gabriel.minekart.item.ModItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VanillaIceCreamItem extends net.minecraft.item.Item {
    private static FoodComponent foodComponent;
    private static int cooldown;

    public VanillaIceCreamItem(FoodComponent foodComponent, int cooldown) {
        super(new FabricItemSettings().maxCount(1).food(foodComponent));
        this.foodComponent = foodComponent;
        this.cooldown = cooldown;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) { // todo: this doesnt work but idrc about that ill do it later
        tooltip.add(Text.translatable("tooltip.minekart.vanilla_ice_cream.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(this, cooldown);
            player.getHungerManager().add(foodComponent.getHunger(), foodComponent.getSaturationModifier());
        }
        return stack;
    }
}
