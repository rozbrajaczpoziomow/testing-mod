package mod.rozbrajaczpoziomow.testing.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class KasuliBottle extends Item {
	public KasuliBottle(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if(!stack.getItem().equals(this)) return stack;
		if(!worldIn.isRemote) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 60 * 20, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.NAUSEA, 25 * 20, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 30 * 20, 0));
			stack.damageItem(1, entityLiving, p -> p.sendBreakAnimation(Hand.MAIN_HAND));
		}
		return stack;
	}
}
