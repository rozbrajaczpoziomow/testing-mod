package mod.rozbrajaczpoziomow.testing.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
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

import static mod.rozbrajaczpoziomow.testing.Utils.rng;
import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.AncientDiamondI;
import static net.minecraft.item.ItemTier.DIAMOND;
import static net.minecraft.potion.Effects.MOVEMENT_SLOWDOWN;
import static net.minecraft.potion.Effects.POISON;
import static net.minecraft.util.text.TextFormatting.GRAY;

public class AncientStoneI extends Block {
	public AncientStoneI(Properties properties) {
		super(properties);
	}

	@Override
	public void playerDestroy(World pLevel, PlayerEntity pPlayer, BlockPos pPos, BlockState pState, @Nullable TileEntity pTe, ItemStack pStack) {
		if(!(pStack.getItem() instanceof PickaxeItem)) return;
		if(((PickaxeItem) pStack.getItem()).getTier().getLevel() < DIAMOND.getLevel()) return;

		Vector3d center = Vector3d.atCenterOf(new Vector3i(pPos.getX(), pPos.getY(), pPos.getZ()));

		ItemEntity item = new ItemEntity(pLevel, center.x, center.y, center.z);
		item.setDefaultPickUpDelay();

		if(EnchantmentHelper.getEnchantments(pStack).containsKey(Enchantments.SILK_TOUCH))
			item.setItem(new ItemStack(this));
		else
			item.setItem(new ItemStack(AncientDiamondI.get(), rng(2) + 1));

		pLevel.addFreshEntity(item);

		pPlayer.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 30, 2));
		pPlayer.addEffect(new EffectInstance(POISON, 20 * 5, 0));
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable IBlockReader pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(withColor("This stone is ancient, and it can contain really interesting old matter", GRAY));
	}
}
