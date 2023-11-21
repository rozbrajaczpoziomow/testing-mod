package mod.rozbrajaczpoziomow.testing.blocks;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public class BlockWithDescription extends Block {
	private final List<ITextComponent> description;

	public BlockWithDescription(Properties properties, ITextComponent... description) {
		super(properties);
		this.description = ImmutableList.copyOf(description);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.addAll(description);
	}
}
