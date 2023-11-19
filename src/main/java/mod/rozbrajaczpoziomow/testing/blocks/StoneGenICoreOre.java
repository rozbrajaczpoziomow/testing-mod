package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.rng;
import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.StoneGenICore;
import static net.minecraft.enchantment.Enchantments.SILK_TOUCH;
import static net.minecraft.item.Items.*;
import static net.minecraft.util.text.TextFormatting.BLUE;

public class StoneGenICoreOre extends Block {
	public StoneGenICoreOre(Properties properties) {
		super(properties);
	}

	@Override
	public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		if(world.isClientSide) return;

		Vector3d mid = Vector3d.atCenterOf(new Vector3i(pos.getX(), pos.getY(), pos.getZ()));
		world.addFreshEntity(new ItemEntity(world, mid.x, mid.y, mid.z, STONE.getDefaultInstance()));
		boolean drop = EnchantmentHelper.getEnchantments(stack).containsKey(SILK_TOUCH);
		if(stack.getItem() == STONE_PICKAXE && rng(0, 101) <= 15) drop = true;
		if(stack.getItem() == IRON_PICKAXE && rng(0, 101) <= 20) drop = true;
		if((stack.getItem() == DIAMOND_PICKAXE || stack.getItem() == NETHERITE_PICKAXE) && rng(0, 101) <= 25) drop = true;
		if(drop) world.addFreshEntity(new ItemEntity(world, mid.x, mid.y, mid.z, StoneGenICore.get().getDefaultInstance()));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("It's a delicate ore I think, you should try to pick it up with si-... Wait this ore is unobtainable you creative player.", BLUE));
	}
}