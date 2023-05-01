package mod.rozbrajaczpoziomow.testing.blocks;

import mod.rozbrajaczpoziomow.testing.tile_entities.RainbowBlockTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RainbowBlock extends Block {
	public static final BooleanProperty update = BooleanProperty.create("update");
	public RainbowBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(update);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new RainbowBlockTile();
	}

	@Override
	public void destroy(IWorld world, BlockPos pos, BlockState state) {
		Vector3d dropPos = Vector3d.atBottomCenterOf(new Vector3i(pos.getX(), pos.getY(), pos.getZ()));
		ItemEntity item = new ItemEntity((World) world, dropPos.x, dropPos.y, dropPos.z, asItem().getDefaultInstance());
		item.setDefaultPickUpDelay();
		item.push(0d, 0.2d, 0d);
		world.addFreshEntity(item);
	}
}
