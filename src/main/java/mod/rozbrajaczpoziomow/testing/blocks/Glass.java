package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.BreakableBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Glass extends BreakableBlock {
	public Glass(Properties properties) {
		super(properties.noOcclusion());
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getVisualShape(BlockState pState, IBlockReader pReader, BlockPos pPos, ISelectionContext pContext) {
		return VoxelShapes.empty();
	}

	@OnlyIn(Dist.CLIENT)
	@SuppressWarnings("deprecation")
	@Override
	public float getShadeBrightness(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
		return 1f;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState pState, IBlockReader pReader, BlockPos pPos) {
		return true;
	}
}
