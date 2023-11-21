package mod.rozbrajaczpoziomow.testing.blocks;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public class BaseIBABlock extends Block {
	public static final IntegerProperty lightLevel = IntegerProperty.create("light_level", 0, 15);
	private final List<ITextComponent> description;

	public BaseIBABlock(Properties properties) {
		super(properties.lightLevel(state -> state.getValue(lightLevel)));
		this.registerDefaultState(stateDefinition.any().setValue(lightLevel, 0));
		description = ImmutableList.of();
	}

	public BaseIBABlock(Properties properties, ITextComponent... description) {
		super(properties.lightLevel(state -> state.getValue(lightLevel)));
		this.registerDefaultState(stateDefinition.any().setValue(lightLevel, 0));
		this.description = ImmutableList.copyOf(description);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(lightLevel);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.addAll(description);
	}
}
