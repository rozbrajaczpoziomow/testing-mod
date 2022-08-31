package mod.rozbrajaczpoziomow.testing.items.custom;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GlitchedSword extends Item {

	public GlitchedSword(Properties builderIn) {
		super(builderIn);
	}


	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.testing.empty"));
		tooltip.add(new TranslationTextComponent("tooltip.testing.mainhand").setStyle(Style.EMPTY.withColor(Color.fromLegacyFormat(TextFormatting.GRAY)).withFont(Style.DEFAULT_FONT)));
		tooltip.add(new TranslationTextComponent("tooltip.testing.glitched_sword.damage").setStyle(Style.EMPTY.withColor(Color.fromLegacyFormat(TextFormatting.DARK_GREEN)).withFont(Style.DEFAULT_FONT)));
		tooltip.add(new TranslationTextComponent("tooltip.testing.alt_sword.attack_speed").setStyle(Style.EMPTY.withColor(Color.fromLegacyFormat(TextFormatting.DARK_GREEN)).withFont(Style.DEFAULT_FONT)));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(!stack.getItem().equals(this)) return false;
		target.setHealth(target.getHealth() + 12f);
		applyEffects(target);
		applyEffects(attacker);
		stack.hurtAndBreak(1, attacker, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
		return true;
	}

	private void applyEffects(LivingEntity entity) {
		entity.addEffect(new EffectInstance(Effects.REGENERATION, 30 * 20, 1));
		entity.addEffect(new EffectInstance(Effects.POISON, 30 * 20, 0));
		entity.addEffect(new EffectInstance(Effects.HEAL, 30 * 20, 0));
		entity.addEffect(new EffectInstance(Effects.HARM, 30 * 20, 0));
		entity.addEffect(new EffectInstance(Effects.WITHER, 30 * 20, 0));
		entity.addEffect(new EffectInstance(Effects.CONFUSION, 30 * 20, 0));
	}
}
