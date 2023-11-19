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

import static mod.rozbrajaczpoziomow.testing.Utils.rng;
import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.item.Items.BUCKET;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.DARK_AQUA;

public class GMDNaturalMilk extends Item {
	public GMDNaturalMilk(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		switch(rng(4)) {
			case 0:
				entity.addEffect(new EffectInstance(REGENERATION, 20 * 10, 0));
				entity.addEffect(new EffectInstance(POISON, 20 * 10, 0));
				break;

			case 1:
				entity.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 60, 0));
				break;

			case 2:
				entity.addEffect(new EffectInstance(POISON, 20 * 13, 0));
				break;

			case 3:
				entity.addEffect(new EffectInstance(BLINDNESS, 20 * 4, 0));
				entity.addEffect(new EffectInstance(POISON, 20 * 30, 0));
				entity.addEffect(new EffectInstance(REGENERATION, 20 * 50, 0));
				break;
		}
		return BUCKET.getDefaultInstance();
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text("This milk looks uh... in-teresting.. Anyways, it has a description on the back that says: This is GMD 100% natural miilk with no chemicals or any bad food for cows... ^.^ This milk is made so you can smile!", DARK_AQUA));
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.DRINK;
	}
}
