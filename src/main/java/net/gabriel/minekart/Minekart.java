package net.gabriel.minekart;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gabriel.minekart.item.ModItemGroups;
import net.gabriel.minekart.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.minecraft.server.command.CommandManager.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;

public class Minekart implements ModInitializer {
	public static final String MOD_ID = "minekart";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public PassiveAbilitySelectScreen passiveAbilitySelectScreen = new PassiveAbilitySelectScreen();

	//tracking when the player breaks a dirt block for testing purposes
	public static final Identifier DIRT_BROKEN = new Identifier(MOD_ID, "dirt_broken");

	private Integer totalDirtBlocksBroken = 0;


	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("selectpassive")
				// make this so that you have to be in a boat .requires(source -> source.)
				.executes(context -> {
					// For versions below 1.19, replace "Text.literal" with "new LiteralText".
					// For versions below 1.20, remode "() ->" directly.
//					context.getSource().sendFeedback(() -> MinecraftClient.getInstance().setScreen(new PassiveAbilitySelectScreen()));

					return 1;
				})));

		// tracking when the player breaks a dirt block for testing purposes
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			if (state.getBlock() == Blocks.GRASS_BLOCK || state.getBlock() == Blocks.DIRT) {
				// Increment the amount of dirt blocks that have been broken
				totalDirtBlocksBroken += 1;

				// Send a packet to the client
				MinecraftServer server = world.getServer();

				PacketByteBuf data = PacketByteBufs.create();
				data.writeInt(totalDirtBlocksBroken);

				ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(player.getUuid());
				server.execute(() -> {
					ServerPlayNetworking.send(playerEntity, DIRT_BROKEN, data);
				});
			}
		});

		// sends a msg whenever a dirt block is broken
		ClientPlayNetworking.registerGlobalReceiver(Minekart.DIRT_BROKEN, (client, handler, buf, responseSender) -> {
			int totalDirtBlocksBroken = buf.readInt();
			client.execute(() -> {
				client.player.sendMessage(Text.literal("Total dirt blocks broken: " + totalDirtBlocksBroken));
			});
		});
	}
}