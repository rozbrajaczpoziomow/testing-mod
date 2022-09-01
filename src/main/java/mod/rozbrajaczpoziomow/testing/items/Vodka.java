package mod.rozbrajaczpoziomow.testing.items;

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
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		if(!world.isClientSide) {
			if(entity instanceof PlayerEntity player && !player.isCreative()) player.getCooldowns().addCooldown(this, 5 * 20);
			entity.addEffect(new EffectInstance(Effects.CONFUSION, 30 * 20, 2));
			entity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 5 * 20, 1));
			stack.hurtAndBreak(1, entity, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
		}
		return stack;
	}
}
