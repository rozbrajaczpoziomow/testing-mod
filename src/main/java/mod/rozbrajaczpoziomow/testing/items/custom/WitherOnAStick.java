package mod.rozbrajaczpoziomow.testing.items.custom;

import mod.rozbrajaczpoziomow.testing.entities.WitherOnAStickEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static net.minecraft.potion.Effects.*;

public class WitherOnAStick extends Item {

	public WitherOnAStick(Properties builderIn) {
		super(builderIn);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if(!player.isCreative()) player.getCooldowns().addCooldown(this, 10);
		ItemStack stack = player.getItemInHand(hand);
		if(hand != Hand.MAIN_HAND) return ActionResult.pass(stack);

		WitherOnAStickEntity skull = new WitherOnAStickEntity(world, player);
		skull.shootFromRotation(player, player.xRot, player.yHeadRot, 0f, 1.3f, 0f);
		world.addFreshEntity(skull);

		stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
		return ActionResult.pass(stack);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if((attacker instanceof PlayerEntity player) && !player.isCreative()) player.getCooldowns().addCooldown(this, 20);

		target.addEffect(new EffectInstance(CONFUSION, 20 * 15, 0));
		target.addEffect(new EffectInstance(WITHER, 20 * 15, 0));
		target.addEffect(new EffectInstance(POISON, 20 * 15, 0));
		attacker.setDeltaMovement(0d, 25d, 0d);
		stack.hurtAndBreak(1, attacker, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
		return true;
	}
}
