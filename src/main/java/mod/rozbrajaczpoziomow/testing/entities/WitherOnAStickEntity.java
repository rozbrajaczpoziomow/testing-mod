package mod.rozbrajaczpoziomow.testing.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

import static net.minecraft.potion.Effects.*;

@ParametersAreNonnullByDefault
public class WitherOnAStickEntity extends WitherSkullEntity {

	public WitherOnAStickEntity(World worldIn, LivingEntity e) {
		super(worldIn, e, 0f, 0f, 0f);
		setRawPosition(e.getPosX(), e.getPosYEye(), e.getPosZ());
	}

	@Override
	protected void onEntityHit(EntityRayTraceResult result) {
		if(!world.isRemote) {
			if(result.getEntity() instanceof LivingEntity hit) {
				hit.attackEntityFrom(DamageSource.WITHER, 4f);
				hit.addPotionEffect(new EffectInstance(NAUSEA, 20 * 15, 0));
				hit.addPotionEffect(new EffectInstance(WITHER, 20 * 15, 0));
				hit.addPotionEffect(new EffectInstance(POISON, 20 * 15, 0));
			}
//			if(entity instanceof PlayerEntity) {
//				Objects.requireNonNull(entity.getServer()).getCommandManager().handleCommand(entity.getServer().getCommandSource(), "kick " + entity.getName().getUnformattedComponentText());
//			}
			kaboom();
		}
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entity) {
		if(ticksExisted <= 5) return;
		onEntityHit(new EntityRayTraceResult(entity));
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		kaboom();
	}

	@Override
	public void tick() {
		if(ticksExisted <= 40 && ticksExisted % 2 == 0) setMotion(getMotion().mul(1.03f, 1f, 1.03f));
		super.tick();
		if(ticksExisted >= 10 * 20) kaboom();
	}

	private void kaboom() {
		world.createExplosion(this, getPosX(), getPosY(), getPosZ(), 1f, false, Explosion.Mode.NONE);
		remove();
	}
}
