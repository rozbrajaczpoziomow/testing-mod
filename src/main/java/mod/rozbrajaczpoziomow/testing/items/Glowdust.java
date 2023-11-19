package mod.rozbrajaczpoziomow.testing.items;

import com.google.common.collect.ImmutableList;
import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
import mod.rozbrajaczpoziomow.testing.blocks.BaseIBABlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.TagRegister.TagIBAGlowdustableBlocks;
import static net.minecraft.util.text.TextFormatting.GOLD;

public class Glowdust extends Item {
	public Glowdust(Properties properties) {
		super(properties);
	}

	// ! Doesn't automatically update, code doesn't use it, just for HelpBook.
	public static ImmutableList<Block> getSupported() {
		final ArrayList<Block> temp = new ArrayList<>(TagIBAGlowdustableBlocks.getValues());
		temp.add(Blocks.RED_NETHER_BRICKS);
		temp.add(Blocks.STONE_BRICKS);
		return ImmutableList.copyOf(temp);
	}

	@Override
	public ActionResultType useOn(ItemUseContext ctx) {
		BlockState block = ctx.getLevel().getBlockState(ctx.getClickedPos());
		Hand hand = ctx.getHand();

		// TODO: this is absolutely terrible code
		if(hand == Hand.MAIN_HAND) {
			if(block.is(TagIBAGlowdustableBlocks))
				if(block.getValue(BaseIBABlock.lightLevel) == 0)
					ctx.getLevel().setBlock(ctx.getClickedPos(), block.setValue(BaseIBABlock.lightLevel, 15), 2 | 16 | 32);
				else return ActionResultType.FAIL;
			else if (block.getBlock() == Blocks.RED_NETHER_BRICKS)
				ctx.getLevel().setBlock(ctx.getClickedPos(), BlockRegister.GlowingRedNetherBricks.get().defaultBlockState(), 2 | 16 | 32);
			else if (block.getBlock() == Blocks.STONE_BRICKS)
				ctx.getLevel().setBlock(ctx.getClickedPos(), BlockRegister.GlowingStoneBricks.get().defaultBlockState(), 2 | 16 | 32);
			else
				return ActionResultType.FAIL;

			ctx.getItemInHand().setCount(ctx.getItemInHand().getCount() - 1);
		} else {
			if(block.is(TagIBAGlowdustableBlocks))
				if(block.getValue(BaseIBABlock.lightLevel) == 15)
					ctx.getLevel().setBlock(ctx.getClickedPos(), block.setValue(BaseIBABlock.lightLevel, 0), 2 | 16 | 32);
				else return ActionResultType.FAIL;
			else if(block.getBlock() == BlockRegister.GlowingRedNetherBricks.get())
				ctx.getLevel().setBlock(ctx.getClickedPos(), Blocks.RED_NETHER_BRICKS.defaultBlockState(), 2 | 16 | 32);
			else if(block.getBlock() == BlockRegister.GlowingStoneBricks.get())
				ctx.getLevel().setBlock(ctx.getClickedPos(), Blocks.STONE_BRICKS.defaultBlockState(), 2 | 16 | 32);
			else
				return ActionResultType.FAIL;

			ctx.getItemInHand().setCount(ctx.getItemInHand().getCount() + 1);
		}

		return ActionResultType.SUCCESS;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(isSelected && entity instanceof LivingEntity)
			((LivingEntity) entity).addEffect(new EffectInstance(Effects.GLOWING, 5, 0, false, false));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("Used to make certain blocks glow.", GOLD));
		tooltip.add(text("For a list of supported blocks, open the help book.", GOLD));
	}
}
