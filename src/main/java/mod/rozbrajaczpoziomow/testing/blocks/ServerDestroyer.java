package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ServerDestroyer extends Block {
    public ServerDestroyer(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState pState, World pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        @SuppressWarnings({"NumericOverflow", "divzero"}) int a = 1 / 0;
    }

    @Override
    public void tick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRand) {
        @SuppressWarnings({"NumericOverflow", "divzero"}) int a = 1 / 0;
    }

    @Override
    public void animateTick(BlockState pState, World pLevel, BlockPos pPos, Random pRand) {
        @SuppressWarnings({"NumericOverflow", "divzero"}) int a = 1 / 0;
    }
}
