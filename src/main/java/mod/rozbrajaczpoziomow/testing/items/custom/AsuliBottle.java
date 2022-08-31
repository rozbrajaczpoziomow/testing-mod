package mod.rozbrajaczpoziomow.testing.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class AsuliBottle extends Item {
	public AsuliBottle(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if(!stack.getItem().equals(this)) return stack;
		if(!worldIn.isClientSide) {
			entityLiving.addEffect(new EffectInstance(Effects.NIGHT_VISION, 70 * 20, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 20, 1));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 30 * 20, 0));
			stack.hurtAndBreak(1, entityLiving, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
		}
		return stack;
	}
}
