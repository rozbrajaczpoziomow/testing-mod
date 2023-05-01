package mod.rozbrajaczpoziomow.testing.blocks;

import mod.rozbrajaczpoziomow.testing.tile_entities.StoneGenITile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

public class StoneGenI extends Block {
	public static final BooleanProperty overclocked = BooleanProperty.create("overclocked");

	public StoneGenI(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(overclocked, false));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new StoneGenITile();
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(overclocked);
	}

	@Override
	@SuppressWarnings("deprecation")
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		return te(world, pos).use(state, world, pos, player, hand, hit);
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		te(world, pos).setPlacedBy(world, pos, state, placer, stack);
	}

	private StoneGenITile te(World world, BlockPos pos) {
		if(!(world.getBlockEntity(pos) instanceof StoneGenITile)) throw new IllegalStateException();
		return ((StoneGenITile) world.getBlockEntity(pos));
	}
}