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
import static net.minecraft.util.text.TextFormatting.AQUA;

public class AncientDiamondII extends Item {
	public AncientDiamondII(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		if(!(pEntity instanceof PlayerEntity)) { pEntity.kill(); return; }

		int tick = pStack.getOrCreateTag().getInt("testing:tick");
		if(++tick >= 20 * 15) {
			((PlayerEntity) pEntity).addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 3, 1));
			tick = 0;
		}

		pStack.getTag().putInt("testing:tick", tick);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(withColor("This ancient diamond is really heavy, but at the same time it's a thing you want to have in your inventory...", AQUA));
	}
}
