package mod.rozbrajaczpoziomow.testing.blocks.custom;

import mcp.MethodsReturnNonnullByDefault;
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
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;

import static net.minecraft.block.Blocks.CHEST;
import static net.minecraft.item.Items.AIR;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class UncraftingTable extends Block {
//	private MinecraftServer mcs;
//	private World wrld;
	public UncraftingTable(Properties properties) {
		super(properties);
	}

	@Override
	public void onNeighborChange(BlockState state, IWorldReader iWorldReader, BlockPos pos, BlockPos neighbor) {
		World world = (World) iWorldReader;
		if(world.isRemote) return;

		Block bl = world.getBlockState(neighbor).getBlock();
		if(bl == CHEST) {
			TestingMod.LOGGER.info("Chest Block Update!");
			BlockState core = world.getBlockState(pos.add(0, -1, 0));
			if(core.getBlock() != BlockRegister.BigCorbi.get()) {
				world.destroyBlock(neighbor, true);
				return;
			}

			BlockState chest = world.getBlockState(pos.add(0, 1, 0));
			if(chest.getBlock() != CHEST) {
				world.destroyBlock(neighbor, true);
				return;
			}

			ChestTileEntity cte = (ChestTileEntity) world.getTileEntity(pos.add(0, 1, 0));
			IInventory inv = ChestBlock.getChestInventory((ChestBlock) chest.getBlock(), chest, world, pos.add(0, 1, 0), true);
			if(inv == null || cte == null) {
				world.destroyBlock(neighbor, true);
				return;
			}

			ItemStack stack = inv.getStackInSlot(0).copy();
			if(stack.getItem() == AIR || stack.getCount() <= 0) {
				return;
			}

			Collection<IRecipe<?>> recipes = world.getRecipeManager().getRecipes();
			for(IRecipe<?> recipe : recipes) {
				if(recipe.getRecipeOutput().getItem() != stack.getItem()) continue;
				ItemStack out = recipe.getRecipeOutput();

				if(stack.getCount() < out.getCount()) continue;

				stack.shrink(out.getCount());
				inv.decrStackSize(0, out.getCount());

				int next = 2;
				for(Ingredient ing : recipe.getIngredients())
					if(ing.getMatchingStacks().length > 0)
						inv.setInventorySlotContents(next++, ing.getMatchingStacks()[0].copy().getItem().getDefaultInstance().copy());
				break;
			}
			// Since we can't delete the item, just break the chest ;-;
//			world.destroyBlock(pos.add(0, 1, 0), true);

		}

	}

	@Override
	@SuppressWarnings("deprecation")
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(hand == Hand.MAIN_HAND)
			player.sendMessage(ITextComponent.getTextComponentOrEmpty(String.format("§belbaT gnitfarC\n§6Place a %s below it, and a chest above it\n§6Put an item in the first slot", BlockRegister.BigCorbi.get().getTranslatedName().getString())), player.getUniqueID());
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(placer == null) return;
		placer.sendMessage(ITextComponent.getTextComponentOrEmpty("§bRight-click the block for information on using it"), placer.getUniqueID());
	}
}
