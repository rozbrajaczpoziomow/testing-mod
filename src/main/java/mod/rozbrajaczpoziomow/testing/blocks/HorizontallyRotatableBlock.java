package mod.rozbrajaczpoziomow.testing.blocks;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class HorizontallyRotatableBlock extends Block {
	private final List<ITextComponent> description;

	public HorizontallyRotatableBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH));
		this.description = ImmutableList.of();
	}

	public HorizontallyRotatableBlock(Properties properties, ITextComponent... description) {
		super(properties);
		this.registerDefaultState(stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH));
		this.description = ImmutableList.copyOf(description);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.addAll(description);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(HORIZONTAL_FACING, rot.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}
}
