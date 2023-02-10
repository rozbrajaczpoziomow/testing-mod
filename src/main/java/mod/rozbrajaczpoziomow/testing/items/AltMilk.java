package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.world.World;

import static net.minecraft.item.Items.AIR;
import static net.minecraft.item.Items.MILK_BUCKET;

public class AltMilk extends Item {
	public AltMilk(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		if(world.isClientSide) return AIR.getDefaultInstance();

		for(Effect effect : entity.getActiveEffectsMap().keySet()) {
			if(effect.isBeneficial())
				entity.removeEffect(effect);
		}

		return AIR.getDefaultInstance();
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return MILK_BUCKET.getUseDuration(MILK_BUCKET.getDefaultInstance()) * 2;
	}
}
