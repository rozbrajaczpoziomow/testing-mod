package mod.rozbrajaczpoziomow.testing.items;

import com.google.common.collect.ImmutableList;
import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.TagRegister.TagIBAGlowdustableBlocks;
import static mod.rozbrajaczpoziomow.testing.blocks.BaseIBABlock.lightLevel;
import static net.minecraft.util.text.TextFormatting.GOLD;

public class Glowdust extends Item {
	private static final HashMap<Block, Block> specialBlockTransformations = new HashMap<>();
	public Glowdust(Properties properties) {
		super(properties);
	}

	static {
		specialBlockTransformations.put(Blocks.RED_NETHER_BRICKS, BlockRegister.GlowingRedNetherBricks.get());
		specialBlockTransformations.put(Blocks.STONE_BRICKS, BlockRegister.GlowingStoneBricks.get());
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

		boolean light = ctx.getHand() == Hand.MAIN_HAND;
		if(!enlighten(ctx.getLevel(), ctx.getClickedPos(), block, light))
			return ActionResultType.FAIL;

		ctx.getItemInHand().setCount(ctx.getItemInHand().getCount() + (light? -1 : 1));
		return ActionResultType.SUCCESS;
	}

	private boolean enlighten(World world, BlockPos pos, BlockState state, boolean light) {
		if(state.is(TagIBAGlowdustableBlocks)) {
			int bef = state.getValue(lightLevel);
			world.setBlock(pos, state.setValue(lightLevel, light? 15 : 0), 2 | 16 | 32);
			return bef == (light? 0 : 15);
		} else if(light && specialBlockTransformations.containsKey(state.getBlock())) {
			return world.setBlock(pos, specialBlockTransformations.get(state.getBlock()).defaultBlockState(), 2 | 16 | 32);
		} else if(!light && specialBlockTransformations.containsValue(state.getBlock())) {
			//noinspection OptionalGetWithoutIsPresent - will always be present
			return world.setBlock(pos, specialBlockTransformations.entrySet().stream().filter(ent -> ent.getValue() == state.getBlock()).findAny().get().getKey().defaultBlockState(), 2 | 16 | 32);
		}

		return false;
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
