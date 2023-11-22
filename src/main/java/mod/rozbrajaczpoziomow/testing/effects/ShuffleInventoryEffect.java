package mod.rozbrajaczpoziomow.testing.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.extensions.IForgeEffect;

import java.util.Random;

import static mod.rozbrajaczpoziomow.testing.Utils.rgba;

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
		if(livingEntity.getCommandSenderWorld().isClientSide)
			return;

		if(!(livingEntity instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) livingEntity;
		NonNullList<ItemStack> inv = player.inventory.items;
		Random rand = new Random(player.tickCount);
		int c = 2; // happens c-1 times
		while(--c != 0) {
			int from = rand.nextInt(inv.size());
			int to = rand.nextInt(inv.size());
			ItemStack tmp = inv.get(to);
			inv.set(to, inv.get(from));
			inv.set(from, tmp);
		}
//		ItemStack last = inv.get(inv.size() - 1);
//		int i = inv.size() - 1;
//		while(i != 0)
//			inv.set(i--, inv.get(i));
//		inv.set(0, last);
	}
}
