package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class HorizontallyRotatableBlockWithDescription extends HorizontallyRotatableBlock {
	private final List<ITextComponent> description;

	public HorizontallyRotatableBlockWithDescription(Properties properties, ITextComponent... description) {
		super(properties);
		this.description = Arrays.asList(description);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.addAll(description);
	}
}
