package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TemponariumBottle extends Item {
	public TemponariumBottle(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entity) {
		if(!stack.getItem().equals(this)) return stack;
		if(!worldIn.isClientSide) {
			entity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 30 * 20, 3));
			entity.addEffect(new EffectInstance(Effects.NIGHT_VISION, 20, 0));
			entity.addEffect(new EffectInstance(Effects.HEAL, 20, 0));
			stack.hurtAndBreak(1, entity, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
		}
		return stack;
	}
}
