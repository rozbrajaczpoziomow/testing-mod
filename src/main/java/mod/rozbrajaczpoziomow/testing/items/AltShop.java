package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.item.Items.EMERALD;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.*;

public class AltShop extends Item {
	private final int updateOn = 45;
	private final String secondsNBT = "testing:seconds";
	public AltShop(Properties properties) {
		super(properties);
	}

	@SuppressWarnings("DuplicatedCode") // I'm aware the first 6 lines of code are copy-pasted, but meh.
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(world.isClientSide) return;
		if(!(entity instanceof PlayerEntity)) { entity.kill(); return; }
		PlayerEntity player = (PlayerEntity) entity;
		if(player.tickCount % 20 != 0) return;

		if(!stack.getOrCreateTag().contains(secondsNBT))
			stack.getTag().putInt(secondsNBT, 0);

		int seconds = stack.getTag().getInt(secondsNBT);
		stack.getTag().putInt(secondsNBT, ++seconds);
		if(seconds != updateOn) return;

		seconds -= player.getOffhandItem().getItem() == ItemRegister.Rybek.get()? 5 : updateOn;
		stack.getTag().putInt(secondsNBT, seconds);

		for(ItemStack item : player.inventory.items)
			if (item.getItem() == EMERALD) {
				item.shrink(1);
				player.addEffect(new EffectInstance(HEAL, 1, 1));
				sendMessage(player, "You sold 1 emerald for some HP, lol.", LIGHT_PURPLE);
				return;
			}

		if(!player.addItem(ItemRegister.ReshiftedEmerald.get().getDefaultInstance()))
			player.drop(ItemRegister.ReshiftedEmerald.get().getDefaultInstance(), false);

		player.addEffect(new EffectInstance(BLINDNESS, 5 * 20, 0));
		player.addEffect(new EffectInstance(WITHER, 5 * 20, 0));
		player.addEffect(new EffectInstance(HARM, 1, 0));
		sendMessage(player, ": ?Sh?!@3?!@tu?:\"<> :", DARK_PURPLE);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if(stack.getTag() == null) return;
		final int seconds = stack.getTag().getInt(secondsNBT);
		tooltip.add(withColor(String.format("%d sec / %d sec", seconds, updateOn), GREEN));
	}
}
