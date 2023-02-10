package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.entities.AuraDiamondI;
import mod.rozbrajaczpoziomow.testing.entities.AuraDiamondII;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.util.text.TextFormatting.RED;

public class AuraDiamond extends Item {
	public AuraDiamond(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if(hand != Hand.MAIN_HAND) return ActionResult.pass(stack);

		stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

		if(player.isShiftKeyDown()) AuraDiamondII.shoot(world, player);
		else AuraDiamondI.shoot(world, player);

		if(!player.isCreative())
			player.getCooldowns().addCooldown(this, 20 * 2);

		return ActionResult.success(stack);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(withColor("This powerful diamond creates unknown diamonds, if one of them touches anything, it'll explode and give effects...", RED));
	}
}
