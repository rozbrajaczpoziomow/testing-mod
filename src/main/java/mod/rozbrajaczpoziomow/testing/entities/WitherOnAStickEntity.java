package mod.rozbrajaczpoziomow.testing.entities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import static net.minecraft.potion.Effects.*;

public class WitherOnAStickEntity extends WitherSkullEntity {

	public WitherOnAStickEntity(World worldIn, LivingEntity e) {
		super(worldIn, e, 0f, 0f, 0f);
		setPosRaw(e.getX(), e.getEyeY(), e.getZ());
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult result) {
		if(!level.isClientSide) {
			if(result.getEntity() instanceof LivingEntity) {
				LivingEntity hit = (LivingEntity) result.getEntity();
				hit.hurt(DamageSource.WITHER, 4f);
				hit.addEffect(new EffectInstance(CONFUSION, 20 * 15, 0));
				hit.addEffect(new EffectInstance(WITHER, 20 * 15, 0));
				hit.addEffect(new EffectInstance(POISON, 20 * 15, 0));
			}
//			if(entity instanceof PlayerEntity) {
//				Objects.requireNonNull(entity.getServer()).getCommandManager().handleCommand(entity.getServer().getCommandSource(), "kick " + entity.getName().getUnformattedComponentText());
//			}
			kaboom();
		}
	}

	@Override
	public void playerTouch(PlayerEntity entity) {
		if(tickCount <= 5) return;
		onHitEntity(new EntityRayTraceResult(entity));
	}

	@Override
	protected void onHit(RayTraceResult result) {
		kaboom();
	}

	@Override
	public void tick() {
		if(tickCount <= 40 && tickCount % 2 == 0) setDeltaMovement(getDeltaMovement().multiply(1.03f, 1f, 1.03f));
		super.tick();
		if(tickCount >= 10 * 20) kaboom();
	}

	private void kaboom() {
		level.explode(this, getX(), getY(), getZ(), 1f, false, Explosion.Mode.NONE);
		remove();
	}
}
