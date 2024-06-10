package net.gabriel.minekart.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gabriel.minekart.Minekart;
import net.gabriel.minekart.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SpeedBumpItem extends Item {

    public SpeedBumpItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            if (player.hasVehicle() && player.getVehicle() instanceof BoatEntity) {
                BoatEntity boat = (BoatEntity) player.getVehicle();
                applySpeedBumpEffect(boat);
                player.sendMessage(Text.literal("You used the Speed Bump while on a boat!"), true);
            } else {
                applySpeedBumpEffect(player);
                player.sendMessage(Text.literal("You used the Speed Bump!"), true);
            }
        }
        //super.use(world, player, hand);

        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (selected && player.hasVehicle() && player.getVehicle() instanceof BoatEntity) {
                BoatEntity boat = (BoatEntity) player.getVehicle();
                applySpeedBumpEffect(boat);
            }
        }
    }

    private void applySpeedBumpEffect(BoatEntity boat) {
        boat.setVelocity(boat.getVelocity().add(0, 1, 0));
        boat.velocityModified = true;
    }

    private void applySpeedBumpEffect(PlayerEntity player) {
        player.setVelocity(player.getVelocity().add(0, 1, 0));
        player.velocityModified = true;
    }
}
