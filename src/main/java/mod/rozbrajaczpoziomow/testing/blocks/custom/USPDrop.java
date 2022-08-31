package mod.rozbrajaczpoziomow.testing.blocks.custom;

import mod.rozbrajaczpoziomow.testing.items.ItemRegister;
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
		return player.getHeldItemMainhand().getItem().equals(ItemRegister.Crowbar.get());
	}

	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		if(stack.getItem().equals(ItemRegister.Crowbar.get())) super.harvestBlock(worldIn, player, pos, state, te, stack);
		else player.addStat(Stats.BLOCK_MINED.get(this));
	}
}
