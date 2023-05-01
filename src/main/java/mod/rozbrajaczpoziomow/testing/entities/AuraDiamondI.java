package mod.rozbrajaczpoziomow.testing.entities;

import mod.rozbrajaczpoziomow.testing.a_registers.EntityRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static mod.rozbrajaczpoziomow.testing.Utils.spawnParticles;
import static mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister.aura_diamond_i;
import static net.minecraft.potion.Effects.REGENERATION;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class AuraDiamondI extends DamagingProjectileEntity implements IRendersAsItem {
	public PlayerEntity owner;

	public AuraDiamondI(EntityType<? extends Entity> type, World world) {
		super(EntityRegister.AuraDiamondI.get(), world);
	}

	public static AuraDiamondI shoot(World world, LivingEntity shooter) {
		AuraDiamondI ret = new AuraDiamondI(EntityRegister.AuraDiamondI.get(), world);
		ret.setPosRaw(shooter.getX(), shooter.getEyeY(), shooter.getZ());
		ret.shootFromRotation(shooter, shooter.xRot, shooter.yHeadRot, 0f, 1.3f, 0f);
		world.addFreshEntity(ret);
		if(shooter instanceof PlayerEntity) ((PlayerEntity) shooter).playNotifySound(aura_diamond_i.get(), SoundCategory.MASTER, 1f, 1f);
		return ret;
	}

	@Override
	protected void onHitBlock(BlockRayTraceResult pResult) {
		if(level.isClientSide) return;

		BlockPos pos = pResult.getBlockPos();
		Explosion boom = level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 8f, false, Explosion.Mode.NONE);
		boom.getHitPlayers().keySet().forEach(p -> {
			p.hurt(DamageSource.explosion(boom), 2f);
			p.addEffect(new EffectInstance(REGENERATION, 20 * 30, 1));
		});

		remove();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void tick() {
		super.tick();
		spawnParticles(RedstoneParticleData.REDSTONE, getX(), getY(), getZ(), .1d, .1d, .1d, 2, false);
	}

	@Override
	protected float getInertia() {
		return 1f;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return ItemRegister.DemonicDiamond.get().getDefaultInstance();
	}
}
