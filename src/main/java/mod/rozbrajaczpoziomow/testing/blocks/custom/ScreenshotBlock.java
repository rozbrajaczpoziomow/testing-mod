package mod.rozbrajaczpoziomow.testing.blocks.custom;

import mcp.MethodsReturnNonnullByDefault;
import mod.rozbrajaczpoziomow.testing.sounds.iSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeSoundType;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static net.minecraft.block.Blocks.BARRIER;
import static net.minecraft.potion.Effects.*;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ScreenshotBlock extends Block {
	public ScreenshotBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(world.isRemote) return;
		if(placer == null) { world.setBlockState(pos, BARRIER.getDefaultState()); return; }
		placer.addPotionEffect(new EffectInstance(SPEED, 20 * 45, 1));
		placer.addPotionEffect(new EffectInstance(NAUSEA, 20 * 45, 2));
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if(world.isRemote || !(entity instanceof LivingEntity livingEntity)) return;
		livingEntity.addPotionEffect(new EffectInstance(REGENERATION, 20 * 3, 0));
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		player.addPotionEffect(new EffectInstance(POISON, 20 * 10, 0));
	}
}
