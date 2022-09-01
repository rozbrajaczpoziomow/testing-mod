package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class AltHoe extends Item {
	public AltHoe(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
		World world = context.getLevel();
		if(!world.isClientSide) {
			PlayerEntity player = context.getPlayer(); assert player != null;
			BlockState block = world.getBlockState(context.getClickedPos());

			if(block.getBlock() == Blocks.GRASS_BLOCK) world.setBlock(context.getClickedPos(), Blocks.MYCELIUM.defaultBlockState(), 1);
			else if(block.getBlock() == Blocks.DIRT) world.setBlock(context.getClickedPos(), Blocks.GRASS_BLOCK.defaultBlockState(), 1);
			else if(block.getBlock() == Blocks.FARMLAND) world.setBlock(context.getClickedPos(), Blocks.DIRT.defaultBlockState(), 1);
			else if(block.getBlock() == Blocks.BEDROCK) player.teleportTo(player.getX(), 255d, player.getZ());
//			else {
//				if(stack.getDisplayName().getUnformattedComponentText().equals("eoH"))
//					stack.setDisplayName(ITextComponent.nullToEmpty("Alternate Hoe"));
//				else
//					stack.setDisplayName(ITextComponent.nullToEmpty("eoH"));
//				return ActionResultType.FAIL;
//			}

			stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
}
