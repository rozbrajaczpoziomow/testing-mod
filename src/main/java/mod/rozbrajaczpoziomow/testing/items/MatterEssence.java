package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.item.Items.DIAMOND;
import static net.minecraft.item.Items.GLASS_BOTTLE;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.Hand.OFF_HAND;
import static net.minecraft.util.text.TextFormatting.DARK_GREEN;

public class MatterEssence extends Item {
	public MatterEssence(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entity) {
		if (worldIn.isClientSide)
			return stack;

		entity.addEffect(new EffectInstance(POISON, 20 * 60 * 30, 0));
		entity.addEffect(new EffectInstance(BLINDNESS, 20 * 60 * 40, 0));
		entity.addEffect(new EffectInstance(CONFUSION, 20 * 60 * 40, 0));
		entity.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 30, 1));
		stack.shrink(1);

		return stack;
	}

	@Override
	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		final String holdingTickNBT = "testing:hold_tick";
		final String tickNBT = "testing:tick";

		if(!(pEntity instanceof PlayerEntity)) { pEntity.kill(); return; }
		PlayerEntity player = (PlayerEntity) pEntity;
		int holdingTick = pStack.getOrCreateTag().getInt(holdingTickNBT);
		int tick = pStack.getTag().getInt(tickNBT);

		if(pIsSelected) {
			if(++holdingTick == 20 * 30)
				player.addEffect(new EffectInstance(CONFUSION, 20 * 60 * 10, 0));
			if(holdingTick == 20 * 35)
				player.addEffect(new EffectInstance(POISON, 20 * 60 * 10, 0));
		} else holdingTick = 0;

		if(++tick % 20 * 60 == 0) {
			player.addEffect(new EffectInstance(BLINDNESS, 20 * 15, 0));
			player.addEffect(new EffectInstance(CONFUSION, 20 * 15, 1));
		}
		if(tick % 20 * 70 == 0)
			player.addEffect(new EffectInstance(POISON, 20 * 10, 0));
		if(tick % 20 * 90 == 0) {
			player.addEffect(new EffectInstance(CONFUSION, 20 * 10, 0));
			player.addEffect(new EffectInstance(POISON, 20 * 30, 0));
			player.addEffect(new EffectInstance(WITHER, 20 * 10, 0));
			player.addEffect(new EffectInstance(BLINDNESS, 20 * 10, 0));
		}

		pStack.getTag().putInt(holdingTickNBT, holdingTick);
		pStack.getTag().putInt(tickNBT, tick);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text("This disgusting liquid is really poisoning, don't hold it for too long, and don't even try to drink it.", DARK_GREEN));
		pTooltip.add(text("The safest way to storage it is to just leave it inside of a chest.", DARK_GREEN));
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.DRINK;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if(world.isClientSide) return super.use(world, player, hand);
		if(hand != MAIN_HAND) return super.use(world, player, hand);

		ItemStack diamond = player.getOffhandItem();
		if(diamond.getItem() != DIAMOND) return super.use(world, player, hand);
		if(diamond.getCount() > 1) { sendMessage(player, "You can only paint one at a time.", DARK_GREEN); return super.use(world, player, hand); }

		player.setItemInHand(hand, GLASS_BOTTLE.getDefaultInstance());
		player.setItemInHand(OFF_HAND, ItemRegister.RadiantedDiamond.get().getDefaultInstance());

		player.addEffect(new EffectInstance(CONFUSION, 20 * 10, 0));
		player.addEffect(new EffectInstance(POISON, 20 * 3, 0));
		player.addEffect(new EffectInstance(BLINDNESS, 20 * 3, 0));

		sendMessage(player, "You transferred the power of the liquid into the diamond...", DARK_GREEN);

		return ActionResult.success(GLASS_BOTTLE.getDefaultInstance());
	}
}
