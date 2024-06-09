package net.gabriel.minekart.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BoatEntity.class)
public class BoatEntityDataSaver {
    private NbtCompound persistentData;
}
