package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static mod.rozbrajaczpoziomow.testing.Utils.rng;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.*;
import static net.minecraft.enchantment.Enchantments.SILK_TOUCH;
import static net.minecraft.item.Items.MILK_BUCKET;

public class MilkShelf extends Block {
	public MilkShelf(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
		return player.getMainHandItem().getItem() == Milkbar.get();
	}

	@Override
	public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		if(world.isClientSide || stack.getItem() != Milkbar.get()) return;

		Vector3d mid = Vector3d.atCenterOf(new Vector3i(pos.getX(), pos.getY(), pos.getZ()));

		if(EnchantmentHelper.getEnchantments(stack).containsKey(SILK_TOUCH))
			world.addFreshEntity(new ItemEntity(world, mid.x, mid.y, mid.z, new ItemStack(this)));
		else {
			Item[] canDrop = new Item[] {RedMilk.get(), AltMilk.get(), MILK_BUCKET};
			for(int i = 0; i < rng(3) + 1; i++)
				world.addFreshEntity(new ItemEntity(world, mid.x, mid.y, mid.z, canDrop[rng(canDrop.length)].getDefaultInstance()));
		}
	}
}
