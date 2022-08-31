package mod.rozbrajaczpoziomow.testing.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class LitaniumBottle extends Item {
	public LitaniumBottle(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if(!stack.getItem().equals(this)) return stack;
		if(!worldIn.isRemote) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 5 * 20, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.SPEED, 25 * 20, 1));
			entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 60 * 20, 3));
			entityLiving.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 30 * 20, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 60 * 20, 2));
			stack.damageItem(1, entityLiving, p -> p.sendBreakAnimation(Hand.MAIN_HAND));
		}
		return stack;
	}
}
