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
import net.minecraft.item.PickaxeItem;
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
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.AncientDiamondII;
import static net.minecraft.item.ItemTier.NETHERITE;
import static net.minecraft.util.text.TextFormatting.GRAY;

public class AncientStoneII extends Block {
	public AncientStoneII(Properties properties) {
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
			item.setItem(new ItemStack(AncientDiamondII.get(), 1));

		pLevel.addFreshEntity(item);
	}

	@Override
	public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
		if(player.isCreative()) return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
		if(!(player.getMainHandItem().getItem() instanceof PickaxeItem)) return false;
		if(((PickaxeItem) player.getMainHandItem().getItem()).getTier().getLevel() >= NETHERITE.getLevel())
			return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);

		return false;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable IBlockReader pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text("The successor of Ancient Stone I, even harder to see, and it only drops 1 ancient diamond", GRAY));
	}
}
