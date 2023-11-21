package mod.rozbrajaczpoziomow.testing.effects;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.extensions.IForgeEffect;

import java.util.stream.Collectors;

import static mod.rozbrajaczpoziomow.testing.Utils.rgba;
import static mod.rozbrajaczpoziomow.testing.Utils.rng;

public class ShuffleInventoryEffect extends Effect implements IForgeEffect {
	public ShuffleInventoryEffect() {
		super(EffectType.HARMFUL, rgba(255, 0, 0, 255));
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		if(!(livingEntity instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) livingEntity;
		NonNullList<ItemStack> inv = player.inventory.items;
		ImmutableList<ItemStack> items = ImmutableList.copyOf(inv.stream().filter(stack -> stack != ItemStack.EMPTY).collect(Collectors.toList())); // why
		inv.clear();
		for(ItemStack stack : items) {
			while(true) {
				int slot = rng(36);

				if(inv.get(slot) != ItemStack.EMPTY)
					continue;

				inv.set(slot, stack);
				break;
			}
		}
	}
}
