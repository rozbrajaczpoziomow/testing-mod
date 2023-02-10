package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class KasuliBottle extends Item {
	public KasuliBottle(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if(!stack.getItem().equals(this)) return stack;
		if(!worldIn.isClientSide) {
			entityLiving.addEffect(new EffectInstance(Effects.NIGHT_VISION, 60 * 20, 0));
			entityLiving.addEffect(new EffectInstance(Effects.CONFUSION, 25 * 20, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 30 * 20, 0));
			stack.hurtAndBreak(1, entityLiving, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
		}
		return stack;
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.DRINK;
	}
}
