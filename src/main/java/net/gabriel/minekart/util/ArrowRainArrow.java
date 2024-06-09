package net.gabriel.minekart.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;

public class ArrowRainArrow extends ArrowEntity {
    public ArrowRainArrow(PlayerEntity player, World world) {
        super(world, player);
    }

    @Override
    public boolean tryPickup(PlayerEntity player) {
        return false;
    }
}
