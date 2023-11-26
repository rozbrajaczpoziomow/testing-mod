package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.LadderBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.potion.Effects.MOVEMENT_SLOWDOWN;
import static net.minecraft.util.text.TextFormatting.GRAY;

public class ColdLadder extends LadderBlock {
	public ColdLadder(Properties properties) {
		super(properties);
	}

	@Override
	public void stepOn(World world, BlockPos pos, Entity entity) {
		if(!(entity instanceof PlayerEntity))
			return;

		((PlayerEntity) entity).addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 5));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> tooltip, ITooltipFlag wtf) {
		tooltip.add(text("A ladder made of a strange kind of iron."));
		tooltip.add(text("It's really cold in touch...", GRAY));
	}
}
