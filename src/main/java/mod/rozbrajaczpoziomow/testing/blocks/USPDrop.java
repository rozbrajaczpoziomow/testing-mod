package mod.rozbrajaczpoziomow.testing.blocks;

import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class USPDrop extends Block {

	public USPDrop(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
		return player.getMainHandItem().getItem().equals(ItemRegister.Crowbar.get());
	}

	@Override
	public void playerDestroy(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		if(stack.getItem().equals(ItemRegister.Crowbar.get())) super.playerDestroy(worldIn, player, pos, state, te, stack);
		else player.awardStat(Stats.BLOCK_MINED.get(this));
	}
}
