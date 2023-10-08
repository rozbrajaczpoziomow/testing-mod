package mod.rozbrajaczpoziomow.testing.blocks;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeSoundType;

import javax.annotation.Nullable;

import static mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister.radiant_zero_break;
import static net.minecraft.block.SoundType.STONE;
import static net.minecraft.potion.Effects.*;

public class RadiantZero extends Block {
	public RadiantZero(Properties properties) {
		super(properties);
	}

	@Override
	public void stepOn(World world, BlockPos pos, Entity entity) {
		if(!(entity instanceof LivingEntity)) return;
		if(world.isClientSide) return;
		((LivingEntity) entity).addEffect(new EffectInstance(CONFUSION, 20 * 15, 2));
		((LivingEntity) entity).addEffect(new EffectInstance(POISON, 20 * 4, 0));
		((LivingEntity) entity).addEffect(new EffectInstance(WITHER, 20, 0));
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(1f, 1f, Suppliers.memoize(radiant_zero_break::get), STONE::getStepSound, STONE::getPlaceSound, STONE::getHitSound, STONE::getFallSound);
	}
}
