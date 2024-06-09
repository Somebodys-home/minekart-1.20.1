package net.gabriel.minekart;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.gabriel.minekart.entity.client.ModModelLayers;
import net.gabriel.minekart.entity.client.ReticleModel;

public class MinekartClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RETICLE, ReticleModel::getTexturedModelData);
    }
}
