package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static mod.rozbrajaczpoziomow.testing.Utils.*;
import static net.minecraft.util.text.TextFormatting.GOLD;
import static net.minecraft.util.text.TextFormatting.GRAY;

public class LoabinBottle extends Item {
	public LoabinBottle(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if(worldIn.isClientSide) return stack;

		List<Effect> effects = new ArrayList<>(ForgeRegistries.POTIONS.getValues());
		Collections.shuffle(effects, new Random());
		Effect effect = effects.get(0);
		int level = rng(6);
		int time = rng(5, 61) * 20;
		if(effect.isBeneficial()) { time /= 2; level /= 2; }
		else { time *= 2; level *= 2; }

		entityLiving.addEffect(new EffectInstance(effect, time, level));
		sendMessage(entityLiving, text(String.format("%s %d for %ds", effect.getDisplayName().getString(), level + 1, time / 20), GOLD));

		stack.shrink(1);

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text("You probably aren't gonna get much out of this...", GRAY));
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return 8;
	}
}
