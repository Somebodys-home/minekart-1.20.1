package net.gabriel.minekart.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WindBurstItem extends Item {

    public WindBurstItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.minekart.wind_burst.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        return use(context.getWorld(), context.getPlayer(), context.getHand()).getResult();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (world.isClient) {
            if (player.hasVehicle() && player.getVehicle() instanceof BoatEntity) {
                BoatEntity boat = (BoatEntity) player.getVehicle();
                boat.addVelocity(0, .7, 0);
                boat.velocityModified = true;
            }
        }

        stack.decrement(1);
        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }
}
