package net.gabriel.minekart.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VanillaIceCreamItem extends net.minecraft.item.Item {
    private FoodComponent foodComponent;
    private int cooldown;

    public VanillaIceCreamItem(FoodComponent foodComponent) {
        super(new FabricItemSettings().maxCount(1).food(foodComponent));
        this.foodComponent = foodComponent;
        cooldown = 20;

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
