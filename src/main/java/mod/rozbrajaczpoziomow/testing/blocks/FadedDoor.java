package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister.faded_door_close;
import static mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister.faded_door_open;
import static net.minecraft.util.text.TextFormatting.DARK_GRAY;

public class FadedDoor extends DoorBlock {
	public FadedDoor(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		state = state.cycle(OPEN);
		world.setBlock(pos, state, 2 | 8);
		world.playSound(null, pos, (state.getValue(OPEN)? faded_door_open : faded_door_close).get(), SoundCategory.BLOCKS, 1f, 1f);
		return ActionResultType.sidedSuccess(world.isClientSide);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(withColor("Haunted...", DARK_GRAY));
	}
}
