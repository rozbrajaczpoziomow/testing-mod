package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
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
import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.ReshiftedEmerald;
import static mod.rozbrajaczpoziomow.testing.items.AltFirework.getPos;
import static mod.rozbrajaczpoziomow.testing.items.AltFirework.spawnFirework;
import static net.minecraft.util.ActionResultType.SUCCESS;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraft.util.text.TextFormatting.LIGHT_PURPLE;

public class AltFireworkEmerald extends Item {
	public AltFireworkEmerald(Properties properties) {
		super(properties);
	}

	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();

		if(world.isClientSide) return SUCCESS;

		BlockPos pos = getPos(context.getClickLocation(), context.getClickedFace());
		spawnFirework(world, pos, context);

		world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY() + 20, pos.getZ(), ReshiftedEmerald.get().getDefaultInstance()));
		if(context.getPlayer() != null)
			sendMessage(context.getPlayer(), "It looks like the emerald has shifted many dimensions...", LIGHT_PURPLE);

		return SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text("It looks stupid, but it can produce interesting results...", GRAY));
	}
}
