package net.gabriel.minekart;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.gabriel.minekart.entity.client.ReticleRenderer;
import net.gabriel.minekart.entity.custom.ReticleEntity;
import net.gabriel.minekart.item.ModFoodComponents;
import net.gabriel.minekart.item.ModItemGroups;
import net.gabriel.minekart.item.ModItems;
import net.gabriel.minekart.block.ModBlocks;
import net.gabriel.minekart.util.ServerScheduler;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Minekart implements ModInitializer {
	public static final String MOD_ID = "minekart";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityType<ReticleEntity> RETICLE = Registry.register(Registries.ENTITY_TYPE,
			new Identifier(Minekart.MOD_ID, "reticle"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, ReticleEntity::new)
					.dimensions(EntityDimensions.fixed(1f, 1f)).build());

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModFoodComponents.registerModItems();

		EntityRendererRegistry.register(RETICLE, ReticleRenderer::new);
		FabricDefaultAttributeRegistry.register(RETICLE, ReticleEntity.createReticleAttributes());

		ServerTickEvents.END_SERVER_TICK.register(tick -> ServerScheduler.tick());
		ServerWorldEvents.UNLOAD.register((server, world) -> ServerScheduler.runAll());
	}
}