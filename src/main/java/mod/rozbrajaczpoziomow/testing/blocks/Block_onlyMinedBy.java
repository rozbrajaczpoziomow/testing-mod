package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class Block_onlyMinedBy extends Block {
	private final ArrayList<Item> tools;

	public Block_onlyMinedBy(Item[] tools, Properties properties) {
		super(properties);
		this.tools = new ArrayList<>(Arrays.asList(tools));
	}

	@Override
	public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
		return player.isCreative() || tools.contains(player.getMainHandItem().getItem());
	}

	@Override
	public void playerDestroy(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		if(tools.contains(stack.getItem())) super.playerDestroy(worldIn, player, pos, state, te, stack);
		else player.awardStat(Stats.BLOCK_MINED.get(this));
	}
}
