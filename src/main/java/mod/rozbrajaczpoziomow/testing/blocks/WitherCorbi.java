package mod.rozbrajaczpoziomow.testing.blocks;

import mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeSoundType;

import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.potion.Effects.*;

public class WitherCorbi extends Block {
	public WitherCorbi(Properties properties) {
		super(properties);
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(world.isClientSide) return;
		for(ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
			player.addEffect(new EffectInstance(WITHER, 20 * 60 * 3, 2));
			player.addEffect(new EffectInstance(CONFUSION, 20 * 60 * 3, 2));
			player.addEffect(new EffectInstance(POISON, 20 * 60 * 3, 2));
		}
	}

	@Override
	public void stepOn(World world, BlockPos pos, Entity entity) {
		if(world.isClientSide || !(entity instanceof LivingEntity livingEntity)) return;
		livingEntity.addEffect(new EffectInstance(WITHER, 20 * 5, 0));
	}

	@Override
	public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		player.addEffect(new EffectInstance(WITHER, 20 * 20, 4));
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(1f, 1f, SoundRegister.wither_corbi_break, SoundRegister.wither_corbi_step, SoundRegister.wither_corbi_place, SoundRegister.corbi, SoundRegister.corbi);
	}
}
