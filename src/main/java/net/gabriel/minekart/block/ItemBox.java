package net.gabriel.minekart.block;

import net.gabriel.minekart.item.ModItems;
import net.gabriel.minekart.util.ServerScheduler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class ItemBox extends Block {
    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");


    public ItemBox(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(ACTIVATED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && entity instanceof PlayerEntity) {
            boolean activated = state.get(ACTIVATED);


            if (entity instanceof PlayerEntity player) {
                if (!state.get(ACTIVATED)) {
                    // Grant a random ability item to the player
                    getRandomAbilityItem((ServerWorld) world, player);
                    ServerScheduler.schedule(() -> {world.setBlockState(pos, state.with(ACTIVATED, !activated));}, 20);
                    // Flip the value of activated and save the new blockstate.mo

                    world.setBlockState(pos, this.getDefaultState().with(ACTIVATED, activated));
                }
            }
        }
    }

    public void scheduledTick(BlockState state, World world, BlockPos pos) {
        // Place a new item box at the same position
        world.setBlockState(pos, this.getDefaultState().with(ACTIVATED, false), 3);
    }


    private void getRandomAbilityItem(ServerWorld world, PlayerEntity player) {
        Random random = new Random();
        int num = random.nextInt(4);
        ItemStack item;

        if (num == 0) {
            item = new ItemStack(ModItems.FIREWORK);
        } else if (num == 1) {
            item = new ItemStack(ModItems.WIND_BURST);
        } else {
            item = new ItemStack(ModItems.ARROW_RAIN);
        }

        if (!player.getInventory().insertStack(item)) {
            player.dropItem(item, false);
        }
    }

}