package mod.rozbrajaczpoziomow.testing.blocks;

import com.mojang.datafixers.util.Pair;
import mod.rozbrajaczpoziomow.testing.datagen.struct.Ingredients;
import mod.rozbrajaczpoziomow.testing.tile_entities.UncraftingTableTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class UncraftingTable extends Block {
	public interface IUncraftingDifferentResult {
		Pair<Integer, Optional<Ingredients>> getResult(ItemStack stack);
	}
	public @interface IUncraftingDisallowed { }
	public UncraftingTable(Properties properties) {
		super(properties);
	}

	@Override
	public void onNeighborChange(BlockState state, IWorldReader worldReader, BlockPos pos, BlockPos neighbor) {
		((UncraftingTableTile) Objects.requireNonNull(worldReader.getBlockEntity(pos))).neighbourUpdate(neighbor);
	}

	@Override
	@SuppressWarnings("deprecation")
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		return ((UncraftingTableTile) Objects.requireNonNull(world.getBlockEntity(pos))).rightClick(player, hand, hit);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new UncraftingTableTile();
	}
}
