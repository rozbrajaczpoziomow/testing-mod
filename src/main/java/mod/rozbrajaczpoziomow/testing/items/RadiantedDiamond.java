package mod.rozbrajaczpoziomow.testing.items;

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

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.DARK_GREEN;

public class RadiantedDiamond extends Item {
	public RadiantedDiamond(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		final String tickNBT = "testing:tick";
		final String holdingTickNBT = "testing:hold_tick";

		if(!(pEntity instanceof PlayerEntity)) { pEntity.kill(); return; }
		PlayerEntity player = (PlayerEntity) pEntity;
		int holdingTick = pStack.getOrCreateTag().getInt(holdingTickNBT);
		int tick = pStack.getTag().getInt(tickNBT);

		if(pIsSelected) {
			if(++holdingTick >= 20 * 6) {
				player.addEffect(new EffectInstance(CONFUSION, 20 * 10, 0));
				player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 10, 0));
			}
		} else holdingTick = 0;

		if(++tick % 20 * 60 * 4 == 0) {
			player.addEffect(new EffectInstance(POISON, 20 * 60 * 4, 0));
			player.addEffect(new EffectInstance(CONFUSION, 20 * 60 * 4, 1));
			player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 60 * 4, 1));
		}

		pStack.getTag().putInt(holdingTickNBT, holdingTick);
		pStack.getTag().putInt(tickNBT, tick);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(withColor("This diamond has sucked all the power of the liquid, yet it's not as dangerous.", DARK_GREEN));
	}
}
