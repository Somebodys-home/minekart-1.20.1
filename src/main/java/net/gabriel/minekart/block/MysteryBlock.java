package net.gabriel.minekart.block;

import net.gabriel.minekart.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Random;

public class MysteryBlock extends Block {
    public static final BooleanProperty ACTIVATED = Properties.POWERED;

    public MysteryBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ACTIVATED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    public VoxelShape getCollisionShape(BlockState state, World world, BlockPos pos, Entity entity) {
        return VoxelShapes.empty();
    }


    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            if (!state.get(ACTIVATED)) {
                // Grant a random ability item to the player
                getRandomAbilityItem((ServerWorld) world, player);

                // Remove the block and set it as activated
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);


                new Thread(() -> {
                    try {
                        Thread.sleep(3000); // Wait for 3 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    world.setBlockState(pos, this.getDefaultState().with(ACTIVATED, false), 3);
                }).start();



            }
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Place a new Mystery Block at the same position
        world.setBlockState(pos, this.getDefaultState().with(ACTIVATED, false), 3);
    }


    private void getRandomAbilityItem(ServerWorld world, PlayerEntity player) {
        Random random = new Random();
        int num = random.nextInt(5);
        ItemStack item;

        if (num == 0) {
            item = new ItemStack(ModItems.FIREWORK);
        } else if (num== 1) {
            item = new ItemStack(ModItems.VANILLA_ICE_CREAM);
        } else if (num == 2) {
            item = new ItemStack(ModItems.WIND_BURST);
        } else if (num == 3) {
            item = new ItemStack(ModItems.ARROW_RAIN);
        } else {
            item = new ItemStack(ModItems.INVISAPPLE);
        }

        if (!player.getInventory().insertStack(item)) {
            player.dropItem(item, false);
        }
    }

}

/*
{
	"credit": "Made with Blockbench",
	"parent": "block/cube_all",
	"texture_size": [64, 64],
	"textures": {
		"0": "minekart:block/item_box",
		"particle": "minekart:item/item_box"
	},
	"elements": [
		{
			"from": [0, 0, 0],
			"to": [16, 16, 16],
			"faces": {
				"north": {"uv": [0, 0, 4, 4], "texture": "#0"},
				"east": {"uv": [0, 4, 4, 8], "texture": "#0"},
				"south": {"uv": [4, 0, 8, 4], "texture": "#0"},
				"west": {"uv": [4, 4, 8, 8], "texture": "#0"},
				"up": {"uv": [4, 12, 0, 8], "texture": "#0"},
				"down": {"uv": [12, 0, 8, 4], "texture": "#0"}
			}
		}
	]
}

 */
