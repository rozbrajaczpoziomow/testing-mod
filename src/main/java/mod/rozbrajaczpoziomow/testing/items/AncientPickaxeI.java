package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.block.material.Material.*;
import static net.minecraft.item.ItemTier.NETHERITE;
import static net.minecraft.potion.Effects.MOVEMENT_SLOWDOWN;
import static net.minecraft.util.text.TextFormatting.RED;

public class AncientPickaxeI extends Item {
	private static final int tier = NETHERITE.getLevel() + 1;

	public AncientPickaxeI(Properties properties) {
		super(properties.addToolType(ToolType.PICKAXE, tier));
	}

	@Override
	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		if(!(pEntity instanceof PlayerEntity)) { pEntity.kill(); return; }
		PlayerEntity player = (PlayerEntity) pEntity;

		if(pIsSelected && player.tickCount % 20 * 5 == 0)
			player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 3, 1));
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(withColor("This pickaxe is made out of Ancient Diamonds and Solid Sticks, so it's really good...", RED));
	}

	@Override
	public boolean mineBlock(ItemStack pStack, World pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
		pStack.hurtAndBreak(1, pEntityLiving, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
		return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
	}

	@Override
	public boolean isCorrectToolForDrops(BlockState pBlock) {
		if(pBlock.getHarvestTool() == ToolType.PICKAXE) {
			return tier >= pBlock.getHarvestLevel();
		}
		Material material = pBlock.getMaterial();
		return material == STONE || material == METAL || material == HEAVY_METAL;
	}

	@Override
	public float getDestroySpeed(ItemStack pStack, BlockState pState) {
		Material mat = pState.getMaterial();
		return (mat != METAL && mat != HEAVY_METAL && mat != STONE ? super.getDestroySpeed(pStack, pState) : NETHERITE.getSpeed()) * 1.5f;
	}
}
