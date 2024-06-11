package net.gabriel.minekart.mixin;

import net.gabriel.minekart.item.custom.InvisappleItem;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InvisappleItem.class)
public class ModEntityDataSaver {
    private NbtCompound persistantData;

}
