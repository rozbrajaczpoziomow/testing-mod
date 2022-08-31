package mod.rozbrajaczpoziomow.testing.entities;

import mcp.MethodsReturnNonnullByDefault;
import mod.rozbrajaczpoziomow.testing.TestingMod;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.ParametersAreNonnullByDefault;

import static net.minecraft.potion.Effects.*;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class Projectile extends DamagingProjectileEntity {
	public PlayerEntity owner;

	public Projectile(EntityType<? extends Projectile> type, World world) {
		super(type, world);
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity player) {
		// 9 dmg, nausea 3 for 15secs, wither 1 for 4secs, regen 3 for 2secs
		if(player == owner) return;
		addEffects(player);
		TestingMod.LOGGER.info(String.format("COLLIDING WITH %s", player.getDisplayName().getString()));
	}

	@Override
	protected void onEntityHit(EntityRayTraceResult result) {
		if(!(result.getEntity() instanceof LivingEntity livingEntity)) return;
		if(result.getEntity() instanceof PlayerEntity player) { onCollideWithPlayer(player); return; }
		addEffects(livingEntity);
	}

	private void addEffects(LivingEntity e) {
		e.addPotionEffect(new EffectInstance(NAUSEA, 20 * 15, 2));
		e.addPotionEffect(new EffectInstance(WITHER, 20 * 4, 0));
		e.addPotionEffect(new EffectInstance(REGENERATION, 20 * 3, 2));
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		// just removes(); also destroys glass
		TestingMod.LOGGER.info("IMPACT - DESTROYING");
		BlockPos pos = new BlockPos(result.getHitVec().x, result.getHitVec().y, result.getHitVec().z);
		Block block = world.getBlockState(pos).getBlock();
		if(block.isIn(Tags.Blocks.GLASS) || block.isIn(Tags.Blocks.GLASS_PANES) || block.isIn(Tags.Blocks.STAINED_GLASS) || block.isIn(Tags.Blocks.STAINED_GLASS_PANES))
			world.destroyBlock(pos, false, owner);
		world.createExplosion(owner, pos.getX(), pos.getY(), pos.getZ(), .5f, Explosion.Mode.NONE);
		remove();
	}

	@Override
	public void tick() {
		super.tick();
		if(ticksExisted % 2 == 0) world.createExplosion(this, getPosX(), getPosY(), getPosZ(), .01f, Explosion.Mode.NONE);
//		if(world.isRemote) world.addParticle(ParticleTypes.HAPPY_VILLAGER, true, getPosX(), getPosY(), getPosZ(), 0d, .1d, 0d);
//		for(int i = 0; i < 1000; i++) world.addParticle(ParticleTypes.HAPPY_VILLAGER, getPosX(), getPosY(), getPosZ(), 0d, .1d, 0d);
//		for(int i = 0; i < 1000; i++) world.addOptionalParticle(ParticleTypes.HAPPY_VILLAGER, getPosX(), getPosY(), getPosZ(), 0d, .1d, 0d);
//		for(int i = 0; i < 1000; i++) world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, getPosX(), getPosY(), getPosZ(), 0d, .1d, 0d);
//		for(int i = 0; i < 1000; i++) world.addOptionalParticle(ParticleTypes.TOTEM_OF_UNDYING, getPosX(), getPosY(), getPosZ(), 0d, .1d, 0d);

		if(ticksExisted >= 20 * 5) {
			world.createExplosion(owner, getPosX(), getPosY(), getPosZ(), .5f, Explosion.Mode.NONE);
			remove();
		}
	}

	@Override
	protected float getMotionFactor() {
		return 1f;
	}
}
