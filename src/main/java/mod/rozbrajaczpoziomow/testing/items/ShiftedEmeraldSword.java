package mod.rozbrajaczpoziomow.testing.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.ActionResult.success;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.text.TextFormatting.DARK_GRAY;

public class ShiftedEmeraldSword extends Item {
	public ShiftedEmeraldSword(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(!(attacker instanceof PlayerEntity player)) { attacker.kill(); return false; }

		if(player.getCooldowns().isOnCooldown(this)) return false;
		target.addEffect(new EffectInstance(WITHER, 20 * 3, 0));
		target.addEffect(new EffectInstance(BLINDNESS, 20 * 3, 0));
		stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(MAIN_HAND));
		player.getCooldowns().addCooldown(this, 30);

		return true;
	}

	@Override
	public ActionResult<ItemStack> use(World pLevel, PlayerEntity pPlayer, Hand pHand) {
		if(pLevel.isClientSide || pHand != MAIN_HAND) return super.use(pLevel, pPlayer, pHand);

		pPlayer.addEffect(new EffectInstance(NIGHT_VISION, 20 * 4, 0));
		pPlayer.getCooldowns().addCooldown(this, 20 * 5);
		return success(pPlayer.getMainHandItem());
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		if(slot != EquipmentSlotType.MAINHAND) return ImmutableMultimap.of();
		ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes = ImmutableMultimap.builder();
		attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 9f, AttributeModifier.Operation.ADDITION));
		attributes.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3f, AttributeModifier.Operation.ADDITION));
		return attributes.build();
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(withColor("This sword is kinda interesting, it looks like in another dimension there is a possibility to craft an emerald sword... I'm sad there's no emerald sword in our sword, but hey, we have this...", DARK_GRAY));
	}

	@Override
	public boolean canAttackBlock(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer) {
		return !pPlayer.isCreative();
	}
}
