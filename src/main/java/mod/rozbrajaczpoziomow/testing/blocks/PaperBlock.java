package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.minecraft.block.Blocks.AIR;
import static net.minecraft.block.Blocks.BARRIER;
import static net.minecraft.potion.Effects.BLINDNESS;

public class PaperBlock extends Block {
	private LivingEntity owner;
	public PaperBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void catchFire(BlockState state, World world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
		if(igniter == null) { owner.setSecondsOnFire(69); return; }
		igniter.addEffect(new EffectInstance(BLINDNESS, 20 * 20, 0));
		igniter.setSecondsOnFire(10);
		world.setBlock(pos, AIR.defaultBlockState(), 1);
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(placer == null) world.setBlock(pos, BARRIER.defaultBlockState(), 1);
		else owner = placer;
	}
}
