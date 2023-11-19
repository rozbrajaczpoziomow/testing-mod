package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.AncientDiamondIII;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.AncientPickaxeI;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.GRAY;

public class AncientStoneIII extends Block {
	public AncientStoneIII(Properties properties) {
		super(properties);
	}

	@Override
	public void playerDestroy(World pLevel, PlayerEntity pPlayer, BlockPos pPos, BlockState pState, @Nullable TileEntity pTe, ItemStack pStack) {
		if(pPlayer.isCreative()) return;

		Vector3d center = Vector3d.atCenterOf(new Vector3i(pPos.getX(), pPos.getY(), pPos.getZ()));

		ItemEntity item = new ItemEntity(pLevel, center.x, center.y, center.z);
		item.setDefaultPickUpDelay();

		if(EnchantmentHelper.getEnchantments(pStack).containsKey(Enchantments.SILK_TOUCH))
			item.setItem(new ItemStack(this));
		else
			item.setItem(new ItemStack(AncientDiamondIII.get(), 1));

		pLevel.addFreshEntity(item);

		pPlayer.addEffect(new EffectInstance(WITHER, 20 * 20, 0));
		pPlayer.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 30, 1));
		pPlayer.addEffect(new EffectInstance(REGENERATION, 20 * 3, 2));
	}

	@Override
	public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
		if(player.isCreative()) return true;
		return player.getMainHandItem().getItem() == AncientPickaxeI.get();
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable IBlockReader pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text("This is one of the oldest stones out there, you'll need to look for on for an eternity...", GRAY));
	}
}
