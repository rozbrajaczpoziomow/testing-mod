package mod.rozbrajaczpoziomow.testing.items.custom;

import mcp.MethodsReturnNonnullByDefault;
import mod.rozbrajaczpoziomow.testing.entities.WitherOnAStickEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

import static net.minecraft.potion.Effects.*;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class WitherOnAStick extends Item {

	public WitherOnAStick(Properties builderIn) {
		super(builderIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if(!player.isCreative()) player.getCooldownTracker().setCooldown(this, 10);
		ItemStack stack = player.getHeldItem(hand);
		if(hand != Hand.MAIN_HAND) return ActionResult.resultPass(stack);

		WitherOnAStickEntity skull = new WitherOnAStickEntity(world, player);
		skull.setDirectionAndMovement(player, player.rotationPitch, player.rotationYawHead, 0f, 1.3f, 0f);
		world.addEntity(skull);

		stack.damageItem(1, player, p -> p.sendBreakAnimation(Hand.MAIN_HAND));
		return ActionResult.resultPass(stack);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if((attacker instanceof PlayerEntity player) && !player.isCreative()) player.getCooldownTracker().setCooldown(this, 20);

		target.addPotionEffect(new EffectInstance(NAUSEA, 20 * 15, 0));
		target.addPotionEffect(new EffectInstance(WITHER, 20 * 15, 0));
		target.addPotionEffect(new EffectInstance(POISON, 20 * 15, 0));
		attacker.setVelocity(0d, 25d, 0d);
		stack.damageItem(1, attacker, p -> p.sendBreakAnimation(Hand.MAIN_HAND));
		return true;
	}
}
