package mod.rozbrajaczpoziomow.testing.blocks;

import mod.rozbrajaczpoziomow.testing.tile_entities.ReshifterTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.minecraft.util.Hand.MAIN_HAND;

public class Reshifter extends Block {
	public static final BooleanProperty active = BooleanProperty.create("active");
	public Reshifter(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(active, false));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(active);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(level.isClientSide) return ActionResultType.SUCCESS;
		if(hand != MAIN_HAND) return ActionResultType.PASS;

		if(level.getBlockEntity(pos) != null && level.getBlockEntity(pos) instanceof ReshifterTile)
			((ReshifterTile) level.getBlockEntity(pos)).rightClick(state, level, pos, player);

		return ActionResultType.SUCCESS;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ReshifterTile();
	}
}
