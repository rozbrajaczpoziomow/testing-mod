package mod.rozbrajaczpoziomow.testing.items.custom;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AltSword extends Item {

	public AltSword(Properties builderIn) {
		super(builderIn);
	}


	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.testing.empty"));
		tooltip.add(new TranslationTextComponent("tooltip.testing.mainhand").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.GRAY)).setFontId(Style.DEFAULT_FONT)));
		tooltip.add(new TranslationTextComponent("tooltip.testing.alt_sword.damage").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.DARK_GREEN)).setFontId(Style.DEFAULT_FONT)));
		tooltip.add(new TranslationTextComponent("tooltip.testing.alt_sword.attack_speed").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.DARK_GREEN)).setFontId(Style.DEFAULT_FONT)));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(!stack.getItem().equals(this)) return false;
		target.setHealth(target.getHealth() + 7f);
		target.applyKnockback(.3f, .1d, -.1d);
		return true;
	}
}
