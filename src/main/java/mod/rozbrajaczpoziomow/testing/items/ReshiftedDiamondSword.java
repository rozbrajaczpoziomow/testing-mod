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
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister.reshifted_diamond_sword;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.text.TextFormatting.LIGHT_PURPLE;

public class ReshiftedDiamondSword extends Item {
	public ReshiftedDiamondSword(Properties properties) {
		super(properties.food(new Food.Builder().alwaysEat().build()));
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(!(attacker instanceof PlayerEntity)) { attacker.kill(); return false; }
		PlayerEntity player = (PlayerEntity) attacker;
		if(player.getCooldowns().isOnCooldown(this)) return false;

		target.addEffect(new EffectInstance(NIGHT_VISION, 20 * 3, 0));
		target.addEffect(new EffectInstance(BLINDNESS, 20 * 3, 0));
		stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(MAIN_HAND));

		return true;
	}

	@Override
	public void releaseUsing(ItemStack pStack, World pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
		pStack.getTag().putInt("tick", 0);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity living) {
		stack.hurtAndBreak(getMaxDamage(getDefaultInstance()) / 10 / 2, living, p -> p.broadcastBreakEvent(MAIN_HAND));
		living.addEffect(new EffectInstance(MOVEMENT_SPEED, 20 * 2, 1));
		living.addEffect(new EffectInstance(NIGHT_VISION, 20 * 2, 0));
		living.addEffect(new EffectInstance(REGENERATION, 20 * 2, 0));
		if(living instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) living;
			player.getCooldowns().addCooldown(this, 20 * 10);
			player.playNotifySound(reshifted_diamond_sword.get(), SoundCategory.MASTER, 1f, 1f);
		}


		return stack;
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.BOW;
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return 20 * 2;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		if(slot != EquipmentSlotType.MAINHAND) return ImmutableMultimap.of();
		ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes = ImmutableMultimap.builder();
		attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 6f, AttributeModifier.Operation.ADDITION));
		attributes.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.4f, AttributeModifier.Operation.ADDITION));
		return attributes.build();
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text("Ten miecz nie jest wyjątkowy, po prostu miecz jest ostry i fajny, ale czy jest naprawdę dobry? Nie sądzę... Może się do czegoś przyda...", LIGHT_PURPLE));
	}

	@Override
	public boolean canAttackBlock(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer) {
		return !pPlayer.isCreative();
	}
}
