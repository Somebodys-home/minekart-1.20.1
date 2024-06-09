package net.gabriel.minekart.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.gabriel.minekart.util.ServerScheduler;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FireworkItem extends Item {

    public FireworkItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.minekart.firework.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return use(context.getWorld(), context.getPlayer(), context.getHand()).getResult();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BoatEntity boat = (BoatEntity) player.getVehicle();

        if (world.isClient) {
            if (hand == Hand.MAIN_HAND) {
                if (player.hasVehicle() && player.getVehicle() instanceof BoatEntity) {
                    // todo: revert this after testing for particles
                    // boat.addVelocity(boat.getHorizontalFacing().getOffsetX() * 1.5, 0, boat.getHorizontalFacing().getOffsetZ() * 1.5);
                    boat.velocityModified = true;
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);

                    world.addParticle(ParticleTypes.FIREWORK, 0, boat.getY() + 1, boat.getZ() - 1, 0, 0, boat.getHorizontalFacing().getOffsetZ() * -1.2);                }
                    System.out.println(boat.getHorizontalFacing().getOffsetX() + " " + boat.getHorizontalFacing().getOffsetZ() + 1);
            }
        }

        stack.decrement(1);
        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }
}
