package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.GluedPaper;
import static net.minecraft.item.Items.PAPER;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.Hand.OFF_HAND;
import static net.minecraft.util.text.TextFormatting.YELLOW;

public class Glue extends Item {
	public static final int paperLimit = 3;
	private final String[] messages = new String[] { "It's glue, you can glue things with it...", "Glue taken straight from school!" };
	public Glue(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if(world.isClientSide) return ActionResult.pass(stack);
		if(hand != MAIN_HAND) return ActionResult.pass(stack);
		ItemStack paper = player.getOffhandItem();
		if(paper.getItem() != PAPER) return ActionResult.pass(stack);
		if(paper.getCount() > paperLimit || stack.getMaxDamage() - stack.getDamageValue() < paper.getCount()) { sendMessage(player, "You have too many things to glue...", YELLOW); return ActionResult.pass(stack); }

		stack.hurtAndBreak(paper.getCount(), player, p -> p.broadcastBreakEvent(MAIN_HAND));
		player.setItemInHand(OFF_HAND, new ItemStack(GluedPaper.get(), paper.getCount()));
		sendMessage(player, "You glued the paper...", YELLOW);

		return ActionResult.success(stack);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text(messages[pStack.getOrCreateTag().getInt("testing:msg")], YELLOW));
	}

	@Override
	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		int tick = pStack.getOrCreateTag().getInt("testing:tick");
		int message = pStack.getTag().getInt("testing:msg");
		if(++tick % 100 == 0) {
			message = (message + 1) % messages.length;
			tick = 0;
		}
		pStack.getTag().putInt("testing:tick", tick);
		pStack.getTag().putInt("testing:msg", message);
	}
}
