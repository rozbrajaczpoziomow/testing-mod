package mod.rozbrajaczpoziomow.testing.blocks;

import mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.ForgeSoundType;

import javax.annotation.Nullable;

import static net.minecraft.block.AbstractBlock.Properties.of;
import static net.minecraft.block.material.Material.STONE;

public class Corble extends Block {
	public static final Properties Settings = of(STONE).strength(.5f);
	public Corble(Properties properties) {
		super(properties);
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(1f, 1f, SoundRegister.corble, SoundRegister.corble, SoundRegister.corble, SoundRegister.corble, SoundRegister.corble);
	}
}
