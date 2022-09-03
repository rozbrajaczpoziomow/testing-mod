package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemWithDescription extends Item {
	private final List<ITextComponent> description;

	public ItemWithDescription(Properties properties) {
		super(properties);
		description = Collections.emptyList();
	}

	public ItemWithDescription(Properties properties, ITextComponent... description) {
		super(properties);
		this.description = Arrays.asList(description);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.addAll(description);
	}
}
