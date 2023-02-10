package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.util.text.TextFormatting.DARK_GRAY;

public class SolidStick extends Item {
	public SolidStick(Properties properties) {
		super(properties);
	}

//	@Override
//	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
//		if(!(pEntity instanceof PlayerEntity player)) { pEntity.kill(); return; }
//
//		int tick = pStack.getOrCreateTag().getInt("tick");
//		if(pIsSelected && ++tick == 20) {
//			player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 5, 0));
//			tick = 0;
//		} if(!pIsSelected) tick = 0;
//
//		pStack.getTag().putInt("tick", tick);
//	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(withColor("This stick is really heavy and solid, great for harder crafts...", DARK_GRAY));
	}
}
