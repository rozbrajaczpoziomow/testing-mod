package mod.rozbrajaczpoziomow.testing.effects;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeEffect;

import static mod.rozbrajaczpoziomow.testing.Utils.rgba;

public class PortalEffect extends Effect implements IForgeEffect {
	public PortalEffect() {
		super(EffectType.HARMFUL, rgba(255, 0, 0, 255));
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		if(!livingEntity.getCommandSenderWorld().isClientSide)
			return;

		if(!(livingEntity instanceof ClientPlayerEntity))
			return;

		ClientPlayerEntity player = (ClientPlayerEntity) livingEntity;
		player.portalTime = amplifier;
		player.oPortalTime = amplifier;
	}
}
