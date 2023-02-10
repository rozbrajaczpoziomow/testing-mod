package mod.rozbrajaczpoziomow.testing.tile_entities;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static mod.rozbrajaczpoziomow.testing.a_registers.TileEntityRegister.SnowEradicatorTE;
import static net.minecraft.block.Blocks.SNOW;
import static net.minecraft.item.Items.SNOWBALL;
import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class SnowEradicatorTile extends TileEntity implements ITickableTileEntity {
	private List<BlockPos> poses = new ArrayList<>();

	public SnowEradicatorTile() {
		super(SnowEradicatorTE.get());
	}

	@Override
	public void tick() {
		if(level.isClientSide) return;

		if(poses.isEmpty()) {
			scan(worldPosition);
			Collections.shuffle(poses);
		}

		if(poses.isEmpty()) return;

		poses = poses.stream().filter(p -> level.getBlockState(p).getBlock() == SNOW).collect(Collectors.toList());
		if(poses.isEmpty()) return;
		BlockPos pos = poses.get(0);
		poses.remove(0);
		level.destroyBlock(pos, true);
		dropSnowball(pos);
	}

	private void dropSnowball(BlockPos origPos) {
		// If there's a block above that can accept items - we try to put a snowball in
			// If we manage to insert the snowball into a slot, we happily return.
		// If there isn't a block above that can accept items, or we can't put our snowball in - drop the snowball in the position of the broken block
		assert level != null;
		TileEntity te = level.getBlockEntity(worldPosition.above());
		if(te != null) {
			te.getCapability(ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
				for(int i = 0; i < handler.getSlots(); i++)
					if(handler.insertItem(0, SNOWBALL.getDefaultInstance(), false).getItem() == Items.AIR) return;
			});
		}
		Vector3d mid = Vector3d.atBottomCenterOf(new Vector3i(origPos.getX(), origPos.getY(), origPos.getZ()));
		ItemEntity item = new ItemEntity(level, mid.x, mid.y, mid.z, SNOWBALL.getDefaultInstance());
		item.setDefaultPickUpDelay();
		level.addFreshEntity(item);
	}

	private void scan(BlockPos pos) {
		Arrays.stream(new BlockPos[] {pos.above(), pos.north(), pos.east(), pos.south(), pos.west()})
				.filter(p -> !poses.contains(p) && level.getBlockState(p).getBlock() == SNOW)
				.forEach(p -> {
					poses.add(p);
					scan(p);
				});
	}
}
