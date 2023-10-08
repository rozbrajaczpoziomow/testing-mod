package mod.rozbrajaczpoziomow.testing.tile_entities;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import static mod.rozbrajaczpoziomow.testing.a_registers.TileEntityRegister.LifeLimiterTE;
import static net.minecraft.block.Blocks.*;

public class LifeLimiterTile extends TileEntity implements ITickableTileEntity {
    public static final int delay = 20 * 3;
    public static final int blockLimit = 10;
    public static final HashMap<Block, Block> transformations = new HashMap<>();
    public static HashSet<Block> _from;
    public static HashSet<Block> _to;
    private final HashMap<Integer, Pair<Block, AtomicInteger>> blocks = new HashMap<>(blockLimit);

    static {
        transformations.put(LAPIS_BLOCK, ACACIA_LOG);
        _from = new HashSet<>(transformations.keySet());
        _to = new HashSet<>(transformations.values());
    }

    public LifeLimiterTile() {
        super(LifeLimiterTE.get());

        for(int i = 0; i < blockLimit; ++i)
            blocks.put(i, Pair.of(AIR, new AtomicInteger(0)));
    }

    @Override
    public void tick() {
        assert level != null;
        for(int i = 0; i < blockLimit; ++i) {
            BlockPos pos = worldPosition.above(i + 1);
            Block block = level.getBlockState(pos).getBlock();

            if(block == Blocks.AIR || _to.contains(block)) continue;
            if(!_from.contains(block)) {
                level.destroyBlock(pos, true);
                continue;
            }

            Pair<Block, AtomicInteger> expected = blocks.get(i);

            if(block != expected.getFirst())
                blocks.put(i, Pair.of(block, new AtomicInteger(0)));
            else {
                if(expected.getSecond().incrementAndGet() == delay) {
                    level.setBlock(pos, transformations.get(block).defaultBlockState(), 2 | 8);
                    blocks.put(i, Pair.of(AIR, new AtomicInteger(0)));
                }
            }
        }
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        // TODO
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        // TODO
        return nbt;
    }
}
