package mod.rozbrajaczpoziomow.testing.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.minecraft.block.Blocks.BARRIER;
import static net.minecraft.potion.Effects.*;

public class ScreenshotBlock extends Block {
	public ScreenshotBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(world.isClientSide) return;
		if(placer == null) { world.setBlock(pos, BARRIER.defaultBlockState(), 1); return; }
		placer.addEffect(new EffectInstance(MOVEMENT_SPEED, 20 * 45, 1));
		placer.addEffect(new EffectInstance(CONFUSION, 20 * 45, 2));
	}

	@Override
	public void stepOn(World world, BlockPos pos, Entity entity) {
		if(world.isClientSide || !(entity instanceof LivingEntity livingEntity)) return;
		livingEntity.addEffect(new EffectInstance(REGENERATION, 20 * 3, 0));
	}

	@Override
	public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		player.addEffect(new EffectInstance(POISON, 20 * 10, 0));
	}
}
