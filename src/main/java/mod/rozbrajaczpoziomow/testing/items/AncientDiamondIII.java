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
import static net.minecraft.potion.Effects.MOVEMENT_SLOWDOWN;
import static net.minecraft.util.text.TextFormatting.DARK_GRAY;

public class AncientDiamondIII extends Item {
	public AncientDiamondIII(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		if(!(pEntity instanceof PlayerEntity)) { pEntity.kill(); return; }
		PlayerEntity player = (PlayerEntity) pEntity;

		int tick = pStack.getOrCreateTag().getInt("testing:tick");
		int holdingTick = pStack.getTag().getInt("testing:holding_tick");

		if(++tick >= 20 * 5) {
			player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 2, 0));
			tick = 0;
		}

		if(pIsSelected)
			if(++holdingTick >= 20) {
				player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 3, 2));
				holdingTick = 0;
			}
		else holdingTick = 0;

		pStack.getTag().putInt("testing:tick", tick);
		pStack.getTag().putInt("testing:holding_tick", holdingTick);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(withColor("It's really heavy and beautiful... I love this one...", DARK_GRAY));
	}
}
