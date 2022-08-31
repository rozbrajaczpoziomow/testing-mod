package mod.rozbrajaczpoziomow.testing.items.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class AltHoe extends Item {
	public AltHoe(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
		World world = context.getWorld();
		if(!world.isRemote) {
			PlayerEntity player = context.getPlayer(); assert player != null;
			BlockState block = world.getBlockState(context.getPos());

			if(block.getBlock() == Blocks.GRASS_BLOCK) world.setBlockState(context.getPos(), Blocks.MYCELIUM.getDefaultState());
			else if(block.getBlock() == Blocks.DIRT) world.setBlockState(context.getPos(), Blocks.GRASS_BLOCK.getDefaultState());
			else if(block.getBlock() == Blocks.FARMLAND) world.setBlockState(context.getPos(), Blocks.DIRT.getDefaultState());
			else if(block.getBlock() == Blocks.BEDROCK) player.teleportKeepLoaded(player.getPosX(), 255d, player.getPosZ());
//			else {
//				if(stack.getDisplayName().getUnformattedComponentText().equals("eoH"))
//					stack.setDisplayName(ITextComponent.getTextComponentOrEmpty("Alternate Hoe"));
//				else
//					stack.setDisplayName(ITextComponent.getTextComponentOrEmpty("eoH"));
//				return ActionResultType.FAIL;
//			}

			stack.damageItem(1, player, p -> p.sendBreakAnimation(context.getHand()));
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
}
