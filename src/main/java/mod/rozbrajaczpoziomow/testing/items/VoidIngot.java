package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.Objects;

import static net.minecraft.potion.Effects.*;

public class VoidIngot extends Item {
	private int timer = 0;
	public VoidIngot(Properties builderIn) {
		super(builderIn);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(world.isClientSide) return;
		if(isSelected) {
			if(timer % (20 * 2) == 0) {
				for(ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
					player.addEffect(new EffectInstance(HARM, 5, 0));
					player.addEffect(new EffectInstance(BLINDNESS, 20 * 5, 0));
				}

				if(entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					livingEntity.addEffect(new EffectInstance(WITHER, 20 * 60 * 20, 1));
					livingEntity.addEffect(new EffectInstance(CONFUSION, 20 * 60, 0));
				}
			}
			timer++;
		} else timer = 0;
	}
}
