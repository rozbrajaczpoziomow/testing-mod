package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.*;
import static net.minecraft.item.Items.SUGAR_CANE;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.ActionResult.pass;
import static net.minecraft.util.ActionResult.success;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.Hand.OFF_HAND;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraft.util.text.TextFormatting.RED;

public class HealingDiamond extends Item {
	public HealingDiamond(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		if(!(pEntity instanceof PlayerEntity player)) { pEntity.kill(); return; }
		if(pIsSelected)
			player.addEffect(new EffectInstance(REGENERATION, 20 * 3, 0));
		player.addEffect(new EffectInstance(REGENERATION, 20 * 4, 0));
		if(player.tickCount % 20 == 0)
			player.addEffect(new EffectInstance(HEAL, 1, 0));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(withColor("This warm diamond is healthy, it's the definition of health...", RED));
		tooltip.add(text(" "));
		tooltip.add(withColor("health (hĕlth) - noun", GRAY));
		tooltip.add(withColor("1. The overall condition of an organism at a given time.", GRAY));
		tooltip.add(withColor("2. Soundness, especially of body or mind; freedom from disease or abnormality.", GRAY));
		tooltip.add(withColor("3. A condition of optimal well-being.", GRAY));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack item = player.getItemInHand(hand);
		if(hand != MAIN_HAND)
			return pass(item);

		if(player.getOffhandItem().getItem() != SUGAR_CANE)
			return pass(item);

		if(player.getOffhandItem().getCount() > 10) {
			sendMessage(player, "Fuck you", RED);
			return pass(item);
		}

		player.setItemInHand(OFF_HAND, new ItemStack(ItemRegister.HealingSugarCane.get(), player.getOffhandItem().getCount()));
		player.addEffect(new EffectInstance(BLINDNESS, 20 * 10, 3));
		player.addEffect(new EffectInstance(CONFUSION, 20 * 10, 3));
		return success(item);
	}
}
