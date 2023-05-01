package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
import mod.rozbrajaczpoziomow.testing.tile_entities.RainbowBlockTile;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.util.ActionResultType.FAIL;
import static net.minecraft.util.ActionResultType.SUCCESS;
import static net.minecraft.util.text.TextFormatting.*;

public class RainbowFreezer extends Item {
	public RainbowFreezer(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(withColor("Right-click on a Rainbow Block to freeze its color", BLUE));
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		assert player != null;
		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();

		if(world.getBlockState(pos).getBlock() != BlockRegister.RainbowBlock.get())
			return FAIL;

		RainbowBlockTile te = (RainbowBlockTile) world.getBlockEntity(pos);
		assert te != null;

		if(te.frozen)
			sendMessage(player, "You have unfrozen the rainbow block...", AQUA);
		else
			sendMessage(player, "You have frozen the rainbow block...", DARK_BLUE);

		te.frozen = !te.frozen;
		player.getItemInHand(context.getHand()).hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
		return SUCCESS;
	}
}
