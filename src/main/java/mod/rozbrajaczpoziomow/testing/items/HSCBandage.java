package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.RED;

public class HSCBandage extends Item {
	public HSCBandage(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(withColor("This bandage can heal you fast, I guess there are chances that it heals deep wounds too... But you'll find out later...", RED));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World _world, LivingEntity livingEntity) {
		if(!(livingEntity instanceof ServerPlayerEntity))
			return stack;

		ServerPlayerEntity player = (ServerPlayerEntity) livingEntity;

		if(player.getCooldowns().isOnCooldown(getItem()))
			return stack;

		player.addEffect(new EffectInstance(REGENERATION, 20 * 5, 0));
		player.addEffect(new EffectInstance(HEAL, 1, 0));
		player.addEffect(new EffectInstance(DAMAGE_RESISTANCE, 20 * 60 * 2, 0));
		player.getCooldowns().addCooldown(getItem(), 20 * 4);
		stack.shrink(1);
		return stack;
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.CROSSBOW;
	}
}
