package mod.rozbrajaczpoziomow.testing.entities;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import static mod.rozbrajaczpoziomow.testing.Utils.spawnParticles;
import static net.minecraft.potion.Effects.*;

public class Projectile extends DamagingProjectileEntity {
	public PlayerEntity owner;

	public Projectile(EntityType<? extends Projectile> type, World world) {
		super(type, world);
	}

	@Override
	public void playerTouch(PlayerEntity player) {
		// 9 dmg, CONFUSION 3 for 15secs, wither 1 for 4secs, regen 3 for 2secs
		if(player == owner) return;
		addEffects(player);
		TestingMod.LOGGER.info(String.format("COLLIDING WITH %s", player.getDisplayName().getString()));
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult result) {
		if(!(result.getEntity() instanceof LivingEntity livingEntity)) return;
		if(result.getEntity() instanceof PlayerEntity player) { playerTouch(player); return; }
		addEffects(livingEntity);
	}

	private void addEffects(LivingEntity e) {
		e.addEffect(new EffectInstance(CONFUSION, 20 * 15, 2));
		e.addEffect(new EffectInstance(WITHER, 20 * 4, 0));
		e.addEffect(new EffectInstance(REGENERATION, 20 * 3, 2));
	}

	@Override
	protected void onHit(RayTraceResult result) {
		// just removes(); also destroys glass
		TestingMod.LOGGER.info("IMPACT - DESTROYING");
		BlockPos pos = new BlockPos(result.getLocation().x, result.getLocation().y, result.getLocation().z);
		Block block = level.getBlockState(pos).getBlock();
		if(block.is(Tags.Blocks.GLASS) || block.is(Tags.Blocks.GLASS_PANES) || block.is(Tags.Blocks.STAINED_GLASS) || block.is(Tags.Blocks.STAINED_GLASS_PANES))
			level.destroyBlock(pos, false, owner);
		spawnParticles(RedstoneParticleData.REDSTONE, pos.getX(), pos.getY(), pos.getZ(), 1d, 1d, 1d, 50, true);
		remove();
	}

	@Override
	public void tick() {
		super.tick();
		spawnParticles(RedstoneParticleData.REDSTONE, getX(), getY(), getZ(), .1d, .1d, .1d, 2, false);

		if(tickCount >= 20 * 5) {
			spawnParticles(RedstoneParticleData.REDSTONE, getX(), getY(), getZ(), 1d, 1d, 1d, 50, true);
			remove();
		}
	}

	@Override
	protected float getInertia() {
		return 1f;
	}
}
