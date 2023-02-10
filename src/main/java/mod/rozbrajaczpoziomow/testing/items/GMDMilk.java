package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.item.Items.BUCKET;
import static net.minecraft.potion.Effects.*;

public class GMDMilk extends Item {
	public GMDMilk(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack pStack, World pLevel, LivingEntity pEntityLiving) {
		pEntityLiving.addEffect(new EffectInstance(NIGHT_VISION, 20 * 30, 0));
		pEntityLiving.addEffect(new EffectInstance(REGENERATION, 20 * 4, 0));
		pEntityLiving.addEffect(new EffectInstance(BLINDNESS, 20 * 3, 0));
		pEntityLiving.addEffect(new EffectInstance(DAMAGE_RESISTANCE, 20 * 90, 0));
		return BUCKET.getDefaultInstance();
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text("It's description on the back says: This milk was made in the GMD factory with 120% natural milk! It's our great and classic product that you all love!"));
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.DRINK;
	}
}
