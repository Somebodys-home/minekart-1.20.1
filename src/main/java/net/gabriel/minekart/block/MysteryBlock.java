package net.gabriel.minekart.block;

import net.gabriel.minekart.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
        return VoxelShapes.empty(); // No collision shape to allow entities to pass through
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            if (!state.get(ACTIVATED)) {
                world.setBlockState(pos, state.with(ACTIVATED, true), 3);

                // Grant a random ability item to the player
                giveRandomAbilityItem((ServerWorld) world, player);


                // Reset the block after a short delay
                world.scheduleBlockTick(pos, this, 100);
            }
        }
    }


    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(ACTIVATED)) {
            world.setBlockState(pos, state.with(ACTIVATED, false), 3);
        }
    }

    private void giveRandomAbilityItem(ServerWorld world, PlayerEntity player) {
        Random random = new Random();
        ItemStack item;

        if (random.nextInt(2) == 0) {
            item = new ItemStack(ModItems.SPEED_BUMP);
        } else {
            item = new ItemStack(ModItems.FIREWORK_CROSSBOW);
        }

        if (!player.getInventory().insertStack(item)) {
            player.dropItem(item, false);
        }
    }
}
