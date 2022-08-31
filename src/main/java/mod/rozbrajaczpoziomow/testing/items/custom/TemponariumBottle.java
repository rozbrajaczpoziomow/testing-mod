package mod.rozbrajaczpoziomow.testing.items.custom;

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
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entity) {
		if(!stack.getItem().equals(this)) return stack;
		if(!worldIn.isRemote) {
			entity.addPotionEffect(new EffectInstance(Effects.SPEED, 30 * 20, 3));
			entity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 20, 0));
			entity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 20, 0));
			stack.damageItem(1, entity, p -> p.sendBreakAnimation(Hand.MAIN_HAND));
		}
		return stack;
	}
}
