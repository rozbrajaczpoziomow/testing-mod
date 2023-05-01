package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.*;
import static mod.rozbrajaczpoziomow.testing.blocks.UncraftingTable.IUncraftingDisallowed;
import static net.minecraft.potion.Effects.*;

@IUncraftingDisallowed
public class Augustus extends Item {
	public Augustus(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(entity.tickCount % 20 != 0) return;
		if(!(entity instanceof LivingEntity)) return;
		LivingEntity living = (LivingEntity) entity;

		living.addEffect(new EffectInstance(HARM, 1, 0));
		living.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20, 19));
		living.addEffect(new EffectInstance(MOVEMENT_SPEED, 20, 9));
		sendMessage(living, "AUGUSTUS GUZ HAS ARRIVED LAKUMBA LAKAKA", TextFormatting.values()[rng(TextFormatting.values().length)]);

		if(entity.tickCount % 40 != 0) return;
		if(!(living instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity) living;

		if(!player.addItem(getDefaultInstance()))
			player.drop(getDefaultInstance(), false);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("AUGUSTUS GUZ IV was a aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA- AUGUSTUS GUZ IV in some case had cancer but he AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA beceded dyntkacjantosus megustalakaka."));
		tooltip.add(text("He was born in -1 year he was an certifikation of AAAAAAAAAAAAAGUSTA LAKAAAAAAAAAKAAAAAAAAAA................................."));
	}
}
