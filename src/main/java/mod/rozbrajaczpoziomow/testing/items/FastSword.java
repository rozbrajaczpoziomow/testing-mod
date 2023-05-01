package mod.rozbrajaczpoziomow.testing.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static net.minecraft.util.ActionResult.success;
import static net.minecraft.util.DamageSource.playerAttack;
import static net.minecraft.util.Hand.MAIN_HAND;

public class FastSword extends Item {
	public FastSword(Properties properties) {
		super(properties);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(!(attacker instanceof PlayerEntity)) { attacker.kill(); return false; }
		attacker.swing(MAIN_HAND);
		target.hurt(playerAttack((PlayerEntity) attacker), .03f);
		target.invulnerableTime = 0;
		stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(MAIN_HAND));
		return false;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		if(slot != EquipmentSlotType.MAINHAND) return ImmutableMultimap.of();
		ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes = ImmutableMultimap.builder();
		attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", .03f, AttributeModifier.Operation.ADDITION));
		attributes.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", 69f, AttributeModifier.Operation.ADDITION));
		return attributes.build();
	}

	@Override
	public boolean canAttackBlock(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer) {
		return !pPlayer.isCreative();
	}

	@Override
	public ActionResult<ItemStack> use(World pLevel, PlayerEntity pPlayer, Hand pHand) {
		if(!AugustusMode.enabled || !pPlayer.getGameProfile().getName().equals("Dev") || pHand != MAIN_HAND || pLevel.isClientSide)
			return super.use(pLevel, pPlayer, pHand);

		AugustusMode.Storage.print(pPlayer.getStringUUID()).forEach(text -> sendMessage(pPlayer, text));

		return success(pPlayer.getMainHandItem());
	}
}
