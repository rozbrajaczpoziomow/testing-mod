package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.util.text.TextFormatting.BLUE;

public class TLACore extends Item {
	public TLACore(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		if(context.getPlayer() == null || context.getLevel().getBlockState(context.getClickedPos()).getBlock() != Blocks.REDSTONE_WIRE)
			return ActionResultType.PASS;

		context.getLevel().setBlock(context.getClickedPos(), Blocks.AIR.defaultBlockState(), 1 | 2);
		context.getItemInHand().setCount(context.getItemInHand().getCount() - 1);
		context.getPlayer().addItem(ItemRegister.TLACoreReactive.get().getDefaultInstance());

		return ActionResultType.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag wtf) {
		tooltip.add(text("This is something like Alt Core but it doesn't change the effects of an item, it changes the form of an item. DON'T TRY TO USE IT ON FIRESNIW PASTY!", BLUE));
	}
}
