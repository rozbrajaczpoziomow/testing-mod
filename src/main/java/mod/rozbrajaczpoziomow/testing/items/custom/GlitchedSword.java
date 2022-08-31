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
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.testing.empty"));
		tooltip.add(new TranslationTextComponent("tooltip.testing.mainhand").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.GRAY)).setFontId(Style.DEFAULT_FONT)));
		tooltip.add(new TranslationTextComponent("tooltip.testing.glitched_sword.damage").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.DARK_GREEN)).setFontId(Style.DEFAULT_FONT)));
		tooltip.add(new TranslationTextComponent("tooltip.testing.alt_sword.attack_speed").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.DARK_GREEN)).setFontId(Style.DEFAULT_FONT)));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(!stack.getItem().equals(this)) return false;
		target.setHealth(target.getHealth() + 12f);
		applyEffects(target);
		applyEffects(attacker);
		stack.damageItem(1, attacker, p -> p.sendBreakAnimation(Hand.MAIN_HAND));
		return true;
	}

	private void applyEffects(LivingEntity entity) {
		entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 30 * 20, 1));
		entity.addPotionEffect(new EffectInstance(Effects.POISON, 30 * 20, 0));
		entity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 30 * 20, 0));
		entity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 30 * 20, 0));
		entity.addPotionEffect(new EffectInstance(Effects.WITHER, 30 * 20, 0));
		entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 30 * 20, 0));
	}
}
