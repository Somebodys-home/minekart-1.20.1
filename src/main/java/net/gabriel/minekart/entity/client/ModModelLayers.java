package net.gabriel.minekart.entity.client;

import net.gabriel.minekart.Minekart;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer RETICLE =
            new EntityModelLayer(new Identifier(Minekart.MOD_ID, "reticle"), "main");
}
