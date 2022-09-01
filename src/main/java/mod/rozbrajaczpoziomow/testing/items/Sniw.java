package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import static net.minecraft.potion.Effects.MOVEMENT_SLOWDOWN;

public class Sniw extends Item {
	public Sniw(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(!isSelected || !(entity instanceof PlayerEntity player)) return;
		player.abilities.mayfly = false;
		player.abilities.flying = false;
		player.startFallFlying();
		player.push(0d, -.3d, 0d);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(!(target instanceof PlayerEntity player)) return true;
		player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 20, 256));
		stack.setCount(0);
		return true;
	}
}
