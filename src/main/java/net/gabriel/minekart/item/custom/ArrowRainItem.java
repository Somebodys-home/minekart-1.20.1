package net.gabriel.minekart.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.gabriel.minekart.Minekart;
import net.gabriel.minekart.entity.client.ReticleRenderer;
import net.gabriel.minekart.entity.custom.ReticleEntity;
import net.gabriel.minekart.util.ArrowRainArrow;
import net.gabriel.minekart.util.ServerScheduler;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
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
        ReticleEntity reticle = new ReticleEntity(Minekart.RETICLE, world);

        if (!world.isClient()) {
            world.spawnEntity(reticle);
            reticle.teleport(player.getX(), player.getY() + 1, player.getZ());
            summonArrowRain(world, player, 10, 50);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

            ServerScheduler.schedule(() -> {
                reticle.remove(Entity.RemovalReason.DISCARDED);
            }, 30);
        }

        stack.decrement(1);
        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }

    private void summonArrowRain(World world, PlayerEntity player, double radius, int numArrows) {
        for (int j = 0; j < 20; j++) { // layers of arrows
            for (int i = 0; i < numArrows; i++) { // each arrow in each layer
                double d0 = (RANDOM.nextDouble() * radius * 2) - radius;
                double d2 = (RANDOM.nextDouble() * radius * 2) - radius;
                ArrowRainArrow arrow = new ArrowRainArrow(player, world);

                arrow.setDamage(.0000001);
                arrow.refreshPositionAndAngles(player.getX() + d0, player.getY() + 85 + (j * 5), player.getZ() + d2, 0, 0);
                arrow.setVelocity(0, -2, 0);
                world.spawnEntity(arrow);

                ServerScheduler.schedule(() -> {
                    arrow.setRemoved(Entity.RemovalReason.DISCARDED);
                }, 150);
            }
        }
    }
}
