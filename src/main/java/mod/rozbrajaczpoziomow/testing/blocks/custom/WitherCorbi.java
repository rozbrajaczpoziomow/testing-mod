package mod.rozbrajaczpoziomow.testing.blocks.custom;

import mcp.MethodsReturnNonnullByDefault;
import mod.rozbrajaczpoziomow.testing.hidden.Tracking;
import mod.rozbrajaczpoziomow.testing.sounds.iSounds;
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
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

import static net.minecraft.potion.Effects.*;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class WitherCorbi extends Block {
	public WitherCorbi(Properties properties) {
		super(properties);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(world.isRemote) return;
		for(ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
			player.addPotionEffect(new EffectInstance(WITHER, 20 * 60 * 3, 2));
			player.addPotionEffect(new EffectInstance(NAUSEA, 20 * 60 * 3, 2));
			player.addPotionEffect(new EffectInstance(POISON, 20 * 60 * 3, 2));
		}
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if(world.isRemote || !(entity instanceof LivingEntity livingEntity)) return;
		livingEntity.addPotionEffect(new EffectInstance(WITHER, 20 * 5, 0));
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		player.addPotionEffect(new EffectInstance(WITHER, 20 * 20, 4));
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(1f, 1f, iSounds.wither_corbi_break, iSounds.wither_corbi_step, iSounds.wither_corbi_place, iSounds.corbi, iSounds.corbi);
	}
}
