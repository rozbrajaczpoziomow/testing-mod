package mod.rozbrajaczpoziomow.testing.blocks;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.ForgeSoundType;

import javax.annotation.Nullable;

import static mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister.strange_cobblestone_break;
import static net.minecraft.block.SoundType.STONE;

public class StrangeCobblestone extends Block {
	public StrangeCobblestone(Properties properties) {
		super(properties);
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(1f, 1f, Suppliers.memoize(strange_cobblestone_break::get), STONE::getStepSound, STONE::getPlaceSound, STONE::getHitSound, STONE::getFallSound);
	}
}