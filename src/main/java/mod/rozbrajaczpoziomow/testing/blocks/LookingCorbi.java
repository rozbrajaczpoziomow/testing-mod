package mod.rozbrajaczpoziomow.testing.blocks;

import mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.ForgeSoundType;

import javax.annotation.Nullable;

public class LookingCorbi extends Corbi {
	public LookingCorbi(Properties properties) {
		super(properties);
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(1f, 1f, SoundRegister.looking_corbi, SoundRegister.looking_corbi, SoundRegister.looking_corbi, SoundRegister.looking_corbi, SoundRegister.looking_corbi);
	}
}
