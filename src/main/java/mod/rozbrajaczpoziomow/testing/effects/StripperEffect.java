package mod.rozbrajaczpoziomow.testing.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.extensions.IForgeEffect;

import static mod.rozbrajaczpoziomow.testing.Utils.rgba;

public class StripperEffect extends Effect implements IForgeEffect {
	public StripperEffect() {
		super(EffectType.HARMFUL, rgba(0, 127, 30, 255));
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		if(!(livingEntity instanceof PlayerEntity))
			return;

		((PlayerEntity) livingEntity).inventory.dropAll();
	}
}
