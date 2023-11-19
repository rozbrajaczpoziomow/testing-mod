package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.potion.Effects.CONFUSION;
import static net.minecraft.potion.Effects.REGENERATION;
import static net.minecraft.util.text.TextFormatting.RED;

public class HealingSugarCane extends Item {
	public HealingSugarCane(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("Ah yes, sugar cane that heals you, sugar cane, my beloved srup for cough and back pain after leaving the mine...", RED));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World _world, LivingEntity livingEntity) {
		if(!(livingEntity instanceof ServerPlayerEntity))
			return stack;

		ServerPlayerEntity player = (ServerPlayerEntity) livingEntity;

		if(player.getCooldowns().isOnCooldown(getItem()))
			return stack;

		player.addEffect(new EffectInstance(REGENERATION, 20 * 30, 0));
		player.addEffect(new EffectInstance(CONFUSION, 20 * 6, 1));
		stack.shrink(1);
		player.getCooldowns().addCooldown(getItem(), 20 * 6);
		return stack;
	}
}
