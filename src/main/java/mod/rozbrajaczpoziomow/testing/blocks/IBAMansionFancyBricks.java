package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.util.text.TextFormatting.LIGHT_PURPLE;

public class IBAMansionFancyBricks extends HorizontallyRotatableBlock {
	public IBAMansionFancyBricks(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(withColor("Brick, but if you were richer.", LIGHT_PURPLE));
	}
}
