package net.gabriel.minekart.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class InvisappleItem extends Item {
    public InvisappleItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200)); // Apply invisibility effect for 200 ticks (10 seconds)
            player.sendMessage(Text.literal("You ate the Invisapple! You are now invisible!"), true);
            if (player.hasVehicle() && player.getVehicle() instanceof BoatEntity) {
                BoatEntity boat = (BoatEntity) player.getVehicle();
                boat.setInvisible(true);
                player.sendMessage(Text.literal("Your boat is now invisible!"), true);
                System.out.println("Boat invisibility set to true");
            } else {
                System.out.println("Player is not on a boat or vehicle is not a boat");
            }
        }

        stack.decrement(1); // Decrease the item stack by 1 after use
        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (player.hasStatusEffect(StatusEffects.INVISIBILITY)) {
                if (player.hasVehicle() && player.getVehicle() instanceof BoatEntity) {
                    BoatEntity boat = (BoatEntity) player.getVehicle();
                    if (!boat.isInvisible()) {
                        boat.setInvisible(true); // Ensure the boat remains invisible
                        System.out.println("Boat invisibility maintained");
                    }
                }
            } else {
                // Make the boat visible again if the player is no longer invisible
                if (player.hasVehicle() && player.getVehicle() instanceof BoatEntity) {
                    BoatEntity boat = (BoatEntity) player.getVehicle();
                    if (boat.isInvisible()) {
                        boat.setInvisible(false);
                        System.out.println("Boat visibility reset to false");
                    }
                }
            }
        }
    }
}