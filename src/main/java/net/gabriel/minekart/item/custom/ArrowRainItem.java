package net.gabriel.minekart.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class ArrowRainItem extends Item {

    private static final Random RANDOM = new Random();

    public ArrowRainItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.minekart.arrow_rain.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return use(context.getWorld(), context.getPlayer(), context.getHand()).getResult();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            double radius = 10.0;
            int numArrows = 100; // Number of arrows to summon

            // Summon arrows around the player
            summonArrowRain(world, player, radius, numArrows);

            // Play sound and send message to player
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            player.sendMessage(Text.literal("Let's rain hell!"), true);
        }

        stack.decrement(1);
        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }

    private void summonArrowRain(World world, PlayerEntity player, double radius, int numArrows) {
        for (int i = 0; i < numArrows; i++) {
            double d0 = (RANDOM.nextDouble() * radius * 2) - radius;
            double d2 = (RANDOM.nextDouble() * radius * 2) - radius;
            double d4 = Math.sqrt(d0 * d0 + d2 * d2);

            ArrowEntity arrow = new ArrowEntity(world, player);
            arrow.refreshPositionAndAngles(player.getX() + d0, player.getY() + 55, player.getZ() + d2, 0, 0);
            arrow.setVelocity(0, -2, 0);
            world.spawnEntity(arrow);
        }
    }
}
