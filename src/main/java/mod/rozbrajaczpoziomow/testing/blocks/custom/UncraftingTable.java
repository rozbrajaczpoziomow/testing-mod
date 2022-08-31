package mod.rozbrajaczpoziomow.testing.blocks.custom;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.blocks.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;

import static net.minecraft.block.Blocks.CHEST;
import static net.minecraft.item.Items.AIR;

public class UncraftingTable extends Block {
	public UncraftingTable(Properties properties) {
		super(properties);
	}

	@Override
	public void onNeighborChange(BlockState state, IWorldReader iWorldReader, BlockPos pos, BlockPos neighbor) {
		World world = (World) iWorldReader;
		if(world.isClientSide) return;

		Block bl = world.getBlockState(neighbor).getBlock();
		if(bl == CHEST) {
			TestingMod.LOGGER.info("Chest Block Update!");
			BlockState core = world.getBlockState(pos.offset(0, -1, 0));
			if(core.getBlock() != BlockRegister.BigCorbi.get()) {
				world.destroyBlock(neighbor, true);
				return;
			}

			BlockState chest = world.getBlockState(pos.offset(0, 1, 0));
			if(chest.getBlock() != CHEST) {
				world.destroyBlock(neighbor, true);
				return;
			}

			ChestTileEntity cte = (ChestTileEntity) world.getBlockEntity(pos.offset(0, 1, 0));
			IInventory inv = ChestBlock.getContainer((ChestBlock) chest.getBlock(), chest, world, pos.offset(0, 1, 0), true);
			if(inv == null || cte == null) {
				world.destroyBlock(neighbor, true);
				return;
			}

			ItemStack stack = inv.getItem(0).copy();
			if(stack.getItem() == AIR || stack.getCount() <= 0) {
				return;
			}

			Collection<IRecipe<?>> recipes = world.getRecipeManager().getRecipes();
			for(IRecipe<?> recipe : recipes) {
				ItemStack out = recipe.getResultItem();
				if(out.getItem() != stack.getItem()) continue;

				if(stack.getCount() < out.getCount()) continue;

				stack.shrink(out.getCount());
				inv.removeItem(0, out.getCount());

				int next = 2;
				for(Ingredient ing : recipe.getIngredients())
					if(ing.getItems().length > 0)
						inv.setItem(next++, ing.getItems()[0].copy().getItem().getDefaultInstance().copy());
				break;
			}
		}

	}

	@Override
	@SuppressWarnings("deprecation")
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(hand == Hand.MAIN_HAND)
			player.sendMessage(ITextComponent.nullToEmpty(String.format("§belbaT gnitfarC\n§6Place a %s below it, and a chest above it\n§6Put an item in the first slot", BlockRegister.BigCorbi.get().getName().getString())), player.getUUID());
		return ActionResultType.SUCCESS;
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(placer == null) return;
		placer.sendMessage(ITextComponent.nullToEmpty("§bRight-click the block for information on using it"), placer.getUUID());
	}
}
