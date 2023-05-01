package mod.rozbrajaczpoziomow.testing.tile_entities;

import com.mojang.datafixers.util.Pair;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import mod.rozbrajaczpoziomow.testing.blocks.UncraftingTable.IUncraftingDifferentResult;
import mod.rozbrajaczpoziomow.testing.blocks.UncraftingTable.IUncraftingDisallowed;
import mod.rozbrajaczpoziomow.testing.datagen.struct.Ingredients;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static mod.rozbrajaczpoziomow.testing.TestingMod.LOGGER;
import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister.BigCorbi;
import static mod.rozbrajaczpoziomow.testing.a_registers.TileEntityRegister.UncraftingTableTE;
import static net.minecraft.block.Blocks.CHEST;
import static net.minecraft.item.Items.MILK_BUCKET;
import static net.minecraft.util.ActionResultType.PASS;
import static net.minecraft.util.ActionResultType.SUCCESS;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.TextFormatting.RED;

public class UncraftingTableTile extends TileEntity {
	private final HashMap<ResourceLocation, Integer> stored = new HashMap<>();

	public UncraftingTableTile() {
		super(UncraftingTableTE.get());
	}

	public void neighbourUpdate(BlockPos neighbor) {
		assert level != null;

		LOGGER.info(level.getBlockState(neighbor).getBlock());

		if(level.getBlockState(neighbor).getBlock() != CHEST) return;

		if(level.getBlockState(worldPosition.below()).getBlock() != BigCorbi.get()) {
			level.destroyBlock(neighbor, true);
			return;
		}

		BlockState chest = level.getBlockState(worldPosition.above());
		if(chest.getBlock() != CHEST) {
			level.destroyBlock(neighbor, true);
			return;
		}

		ChestTileEntity cte = (ChestTileEntity) level.getBlockEntity(worldPosition.above());
		IInventory inv = ChestBlock.getContainer((ChestBlock) chest.getBlock(), chest, level, worldPosition.above(), true);
		if(inv == null || cte == null) {
			level.destroyBlock(neighbor, true);
			return;
		}

		for(int c = 0; c < 16; c++) {
			HashMap<Integer, ItemStack> stacks = new HashMap<>();
			for(int i = 0; i < inv.getContainerSize(); i++) {
				ItemStack stack = inv.getItem(i).copy();
				if(!stack.isEmpty() && !stack.isDamaged() && !(stack.getItem() instanceof IUncraftingDisallowed))
					stacks.put(i, inv.getItem(i));
			}

			final NonNullList<Ingredient> ingredients = NonNullList.create();

			stacks.forEach((itemSlot, stack) -> {
				if(stack.getItem() instanceof IUncraftingDifferentResult) {
					Pair<Integer, Optional<Ingredients>> result = ((IUncraftingDifferentResult) stack.getItem()).getResult(stack);
					if(result.getSecond().isPresent()) {
						inv.removeItem(itemSlot, result.getFirst());
						ingredients.addAll(result.getSecond().get().get());
						return;
					}
				}

				for(IRecipe<?> recipe : level.getRecipeManager().getRecipes()) {
					ItemStack out = recipe.getResultItem();

					if(out.getItem() != stack.getItem()) continue;
					if(stack.getCount() < out.getCount()) continue;

					inv.removeItem(itemSlot, out.getCount());
					ingredients.addAll(recipe.getIngredients());
					break;
				}
			});

			ingredients.forEach(ingredient -> {
				// When Ingredient is air, it has no item candidates, thus we're skipping it
				if(ingredient.getItems().length == 0) return;
				Item item = ingredient.getItems()[0].getItem();
				stored.put(item.getRegistryName(), stored.getOrDefault(item.getRegistryName(), 0) + 1);
			});
		}
	}

	public ActionResultType rightClick(PlayerEntity _player, Hand hand, BlockRayTraceResult hit) {
		if(!(_player instanceof ServerPlayerEntity))
			return PASS;

		ServerPlayerEntity player = (ServerPlayerEntity) _player;

		if(hand != MAIN_HAND)
			return PASS;

		assert level != null;

		if(player.getMainHandItem().getItem() == ItemRegister.AltMilk.get() && stored.keySet().size() > 0) {
			player.setItemInHand(MAIN_HAND, MILK_BUCKET.getDefaultInstance());
			level.destroyBlock(worldPosition.above(), true);
			Vector3d dropPos = Vector3d.atBottomCenterOf(new Vector3i(worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ()));
			stored.forEach((loc, count) -> {
				Item item = ForgeRegistries.ITEMS.getEntries().stream().filter(ent -> Objects.requireNonNull(ent.getValue().getRegistryName()).toString().equals(loc.toString())).findFirst().orElseThrow(() -> {
					sendMessage(player, String.format("Couldn't parse ResourceLocation -> Item for %s", loc.toString()), RED);
					return new RuntimeException("whalefuuck");
				}).getValue();
				while(count > 0) {
					int drop = Math.min(count, item.getItemStackLimit(item.getDefaultInstance()));
					count -= drop;
					ItemEntity entity = new ItemEntity(level, dropPos.x, dropPos.y, dropPos.z, new ItemStack(item, drop));
					entity.setDefaultPickUpDelay();
					entity.setExtendedLifetime();
					entity.push(0d, .3d, 0d);
					level.addFreshEntity(entity);
				}
			});
			stored.clear();
		} else {
			sendMessage(player, "Stored items:", AQUA);
			if(stored.keySet().size() < 1)
				sendMessage(player, "  None...", AQUA);
			else
				stored.forEach((item, count) -> sendMessage(player, withColor(String.format("  %dx %s", count, item), AQUA)));

			stored.forEach((item, count) -> {
				LOGGER.error(item);
				LOGGER.error(count);
			});
		}
		return SUCCESS;
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		CompoundNBT compound = new CompoundNBT();
		stored.forEach((regName, count) -> compound.putInt(regName.toString(), count));
		nbt.put("StoredItems", compound);
		return super.save(nbt);
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		CompoundNBT compound = nbt.getCompound("StoredItems");
		Set<String> keys = compound.getAllKeys();
		keys.forEach(key -> stored.put(new ResourceLocation(key), compound.getInt(key)));

		super.load(state, nbt);
	}
}
