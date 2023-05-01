package mod.rozbrajaczpoziomow.testing.tile_entities;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister.RainbowBlock;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.*;
import static mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister.reshifter_shift;
import static mod.rozbrajaczpoziomow.testing.a_registers.TileEntityRegister.ReshifterTE;
import static mod.rozbrajaczpoziomow.testing.blocks.Reshifter.active;
import static net.minecraft.item.Items.*;
import static net.minecraft.potion.Effects.BLINDNESS;
import static net.minecraft.util.SoundCategory.MASTER;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraft.util.text.TextFormatting.LIGHT_PURPLE;

public class ReshifterTile extends TileEntity implements ITickableTileEntity {
	private int tick = 0;
	private Item get = null;
	private final int update = 20 * 40;
	private final ImmutableMap<Item, Item> getMap = ImmutableMap.of(EMERALD, ReshiftedEmerald.get(), DIAMOND, ReshiftedDiamond.get(), DepressedFish.get(), HelpFish.get(), BLUE_ICE, RainbowBlock.get().asItem());

	public ReshifterTile() {
		super(ReshifterTE.get());
	}

	public void rightClick(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		getGet(player);
		world.setBlock(pos, state.setValue(active, get != null), 1 | 2 | 16 | 32);
		if(tick < update) {
			sendMessage(player, String.format("%d / %d sec", tick / 20, update / 20), LIGHT_PURPLE);
			return;
		}
		if(get == null) return;

		tick -= update;

		ItemEntity item = new ItemEntity(world, pos.getX() +.5f, pos.getY() + 1, pos.getZ() +.5f, get.getDefaultInstance());
		item.setDeltaMovement(0d, .7d, 0d);
		item.setDefaultPickUpDelay();
		world.addFreshEntity(item);

		player.addEffect(new EffectInstance(BLINDNESS, 20, 0));
		player.playNotifySound(reshifter_shift.get(), MASTER, 1.5f, 1f);
	}

	private void getGet(PlayerEntity player) {
		ItemStack stack = player.getMainHandItem();
		// If the player is shifting, or the current item is null, show the message
		if(player.isShiftKeyDown() || get == null) {
			sendMessage(player, "You can activate the Reshifter with one of the following items:", GRAY);
			getMap.keySet().forEach(item -> sendMessage(player, String.format("  %ss to get %ss", item.getName(item.getDefaultInstance()).getString(), getMap.get(item).getName(getMap.get(item).getDefaultInstance()).getString()), GRAY));
		}
		// If the player is not holding a valid item, or the item the player is holding is the same as the current setting (this is done to prevent unnecesarry sucking up of items); return as we don't want to override the current setting.
		if(!getMap.containsKey(stack.getItem()) || stack.getItem() == get)
			return;

		get = getMap.get(stack.getItem());
		stack.shrink(1);
	}

	@Override
	public void tick() {
		if(tick < update * 64) tick++;
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		compound.putInt("testing:tick", tick);
		if(get != null && get.getRegistryName() != null)
			compound.putString("testing:item", get.getRegistryName().toString());
		return super.save(compound);
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		tick = compound.getInt("testing:tick");
		if(compound.contains("testing:item"))
			get = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(compound.getString("testing:item")));
		super.load(state, compound);
	}
}
