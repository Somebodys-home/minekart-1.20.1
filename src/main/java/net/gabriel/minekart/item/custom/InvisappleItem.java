package net.gabriel.minekart.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class InvisappleItem extends Item {
    public InvisappleItem() {
        super(new FabricItemSettings().maxCount(1));
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            player.setStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200), player); //.setInvisible(true);
            if (player.hasVehicle() && player.getVehicle() instanceof BoatEntity && player.hasStatusEffect(StatusEffects.INVISIBILITY)) {
                BoatEntity boat = (BoatEntity) player.getVehicle();
                boat.setInvisible(true);
            }
            player.sendMessage(Text.literal("You ate the Invisapple! Tastes like nothing."), true);
        }

        stack.decrement(1);
        return super.use(world, player, hand);
    }
}
