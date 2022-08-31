package mod.rozbrajaczpoziomow.testing.blocks.custom;

import mod.rozbrajaczpoziomow.testing.sounds.iSounds;
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

public class Corbi extends Block {
	public static final Properties Settings = of(STONE).strength(.3f);
	public Corbi(Properties properties) {
		super(properties);
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(1f, 1f, iSounds.corbi, iSounds.corbi_step, iSounds.corbi, iSounds.corbi, iSounds.corbi);
	}
}
