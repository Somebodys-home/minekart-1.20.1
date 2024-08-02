package net.gabriel.minekart.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;

public class MyArrowEntity extends ArrowEntity {
    public MyArrowEntity(PlayerEntity player, World world) {
        super(world, player);
    }

    @Override
    public boolean tryPickup(PlayerEntity player) {
        return false;
    }
}
