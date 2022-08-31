package mod.rozbrajaczpoziomow.testing.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Vodka extends Item {
	public Vodka(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
		if(!world.isRemote) {
			if(entity instanceof PlayerEntity player && !player.isCreative()) player.getCooldownTracker().setCooldown(this, 5 * 20);
			entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 30 * 20, 2));
			entity.addPotionEffect(new EffectInstance(Effects.SPEED, 5 * 20, 1));
			stack.damageItem(1, entity, p -> p.sendBreakAnimation(Hand.MAIN_HAND));
		}
		return stack;
	}
}
