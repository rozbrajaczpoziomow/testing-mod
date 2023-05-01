package mod.rozbrajaczpoziomow.testing.tile_entities;

import mod.rozbrajaczpoziomow.testing.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import static com.google.common.math.IntMath.pow;
import static mod.rozbrajaczpoziomow.testing.a_registers.TileEntityRegister.RainbowBlockTE;
import static mod.rozbrajaczpoziomow.testing.blocks.RainbowBlock.update;
import static net.minecraft.util.math.MathHelper.abs;

public class RainbowBlockTile extends TileEntity implements ITickableTileEntity {
	private short r = 1, g = 1, b = 1;
	private byte ri = 1, gi = 1, bi = 1;
	public boolean frozen = false;
	public RainbowBlockTile() {
		super(RainbowBlockTE.get());
	}

	@Override
	public void tick() {
		if(frozen)
			return;

		switch(Utils.rng(3)) {
			case 0: r += ri; break;
			case 1: g += gi; break;
			case 2: b += bi; break;
		}

		if(r == 255) ri = (byte) -abs(ri);
		if(r == 0) ri = (byte) abs(ri);
		if(g == 255) gi = (byte) -abs(gi);
		if(g == 0) gi = (byte) abs(gi);
		if(b == 255) bi = (byte) -abs(bi);
		if(b == 0) bi = (byte) abs(bi);

		assert level != null;
		BlockState state = level.getBlockState(worldPosition);
		level.setBlock(worldPosition, state.setValue(update, !state.getValue(update)), 1 | 2 | 16 | 32);
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		r = nbt.getShort("r");
		g = nbt.getShort("g");
		b = nbt.getShort("b");
		ri = nbt.getByte("ri");
		gi = nbt.getByte("gi");
		bi = nbt.getByte("bi");
		frozen = nbt.getBoolean("frozen");
		super.load(state, nbt);
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		nbt.putShort("r", r);
		nbt.putShort("g", g);
		nbt.putShort("b", b);
		nbt.putByte("ri", ri);
		nbt.putByte("gi", gi);
		nbt.putByte("bi", bi);
		nbt.putBoolean("frozen", frozen);
		return super.save(nbt);
	}

	public int getColor() {
		return r + g * pow(16, 2) + b * pow(16, 4);
	}
}
