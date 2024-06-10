package net.gabriel.minekart.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.vehicle.BoatEntity;
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

public class FireworkCrossbowItem extends Item {

    public FireworkCrossbowItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return use(context.getWorld(), context.getPlayer(), context.getHand()).getResult();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            if (hand == Hand.MAIN_HAND) {
                FireworkRocketEntity firework = new FireworkRocketEntity(world, stack, player);
                firework.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(firework);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
                player.sendMessage(Text.literal("Firework shot!"), true);
            } else if (hand == Hand.OFF_HAND) {
                if (player.hasVehicle() && player.getVehicle() instanceof BoatEntity) {
                    BoatEntity boat = (BoatEntity) player.getVehicle();
                    boat.setVelocity(0, 0, 0);
                    boat.velocityModified = true;
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BOAT_PADDLE_WATER, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    player.sendMessage(Text.literal("Firework boost applied to boat!"), true);
                }
            }
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }
}
