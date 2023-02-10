package mod.rozbrajaczpoziomow.testing.tile_entities;

import mod.rozbrajaczpoziomow.testing.blocks.StoneGenI;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.UUID;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.a_registers.TileEntityRegister.StoneGenITE;
import static net.minecraft.item.Items.STONE;
import static net.minecraft.util.ActionResultType.PASS;
import static net.minecraft.util.ActionResultType.SUCCESS;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;
import static net.minecraftforge.eventbus.api.EventPriority.HIGH;

public class StoneGenITile extends TileEntity implements ITickableTileEntity {
	private int tick = 0;
	private int stored = 0;
	private int maxStored = 64;
	private int every = 20 * 15;
	private int each = 1;
	private int overclock = 0;
	private Mode mode = Mode.DROP;
	private UUID ownerUUID;
	private ServerPlayerEntity owner;

	public StoneGenITile() {
		super(StoneGenITE.get());
		EVENT_BUS.addListener(HIGH, this::logIn);
		EVENT_BUS.addListener(HIGH, this::logOut);
	}

	@Override
	public void tick() {
		//noinspection ConstantConditions
		if(level.isClientSide) return;
		if(overclock > 0) decOverclock();
		if(++tick % every != 0) return;
		tick = 0;

		if(mode == Mode.DROP) {
			Vector3d mid = Vector3d.atBottomCenterOf(new Vector3i(worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ()));
			ItemEntity stone = new ItemEntity(level, mid.x, mid.y, mid.z, STONE.getDefaultInstance());
			stone.setDefaultPickUpDelay();
			stone.setDeltaMovement(0f, .5f, 0f);
			level.addFreshEntity(stone);
		} else {
			stored += each;
			if(stored > maxStored) {
				stored -= each;
				if(owner != null)
					sendMessage(owner, "Your Stone Gen I has filled up!", GRAY);
			}
		}
	}

	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, @Nullable BlockRayTraceResult hit) {
		if(world.isClientSide || hand != MAIN_HAND) return PASS;

		if(player.getUUID() == ownerUUID)
			owner = (ServerPlayerEntity) player;

		if(stored > 0) {
			Vector3d mid = Vector3d.atBottomCenterOf(new Vector3i(pos.getX(), pos.getY() + 1, pos.getZ()));

			ItemEntity items = new ItemEntity(world, mid.x, mid.y, mid.z, new ItemStack(STONE, stored));
			items.setDefaultPickUpDelay();
			items.setDeltaMovement(0f, 1f, 0f);
			world.addFreshEntity(items);
			stored = 0;

			if(!player.isShiftKeyDown()) return SUCCESS;
		}

		if(!player.isShiftKeyDown()) return PASS;

		mode = mode == Mode.DROP? Mode.STORE : Mode.DROP;
		sendMessage(player, "Now in " + (mode == Mode.DROP? "drop" : "storing") + " mode...", GRAY);

		return SUCCESS;
	}

	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(!(placer instanceof PlayerEntity player)) {
			world.destroyBlock(pos, true);
			return;
		}

		ownerUUID = player.getUUID();
	}

	@SubscribeEvent
	public void logIn(final PlayerLoggedInEvent event) {
		if(event.getPlayer() instanceof ServerPlayerEntity spe && spe.getUUID() == ownerUUID)
			owner = spe;
	}

	@SubscribeEvent
	public void logOut(final PlayerLoggedOutEvent event) {
		if(event.getPlayer() instanceof ServerPlayerEntity spe && spe.getUUID() == ownerUUID)
			owner = null;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		tick = nbt.getInt("tick");
		stored = nbt.getInt("stored");

		if(nbt.contains("owner"))
			ownerUUID = nbt.getUUID("owner");

		super.load(state, nbt);
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		nbt.putInt("tick", tick);
		nbt.putInt("stored", stored);

		if(ownerUUID != null)
			nbt.putUUID("owner", ownerUUID);

		return super.save(nbt);
	}

	public void overclock() {
		overclock = 200 * 20;
		maxStored = 64 * 6;
		every = 20 * 3;
		each = 4;
		mode = Mode.STORE;
	}

	private void decOverclock() {
		if(--overclock != 0) return;
		assert level != null;

		maxStored = 64;
		every = 20 * 15;
		each = 1;

		level.setBlock(worldPosition, level.getBlockState(worldPosition).setValue(StoneGenI.overclocked, false), 2 | 16 | 32 | 64);
		sendMessage(owner, "Too much stone stored...", GRAY);
		use(level.getBlockState(worldPosition), level, worldPosition, owner, MAIN_HAND, null);
	}

	private enum Mode {
		STORE, DROP
	}
}
