package mod.rozbrajaczpoziomow.testing.items;

import com.mojang.datafixers.util.Pair;
import mod.rozbrajaczpoziomow.testing.datagen.struct.Ingredients;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static mod.rozbrajaczpoziomow.testing.Utils.rng;
import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.Core;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.CoreIngot;
import static mod.rozbrajaczpoziomow.testing.blocks.UncraftingTable.IUncraftingDifferentResult;
import static net.minecraft.item.Items.*;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.RED;

public class RedMilk extends Item implements IUncraftingDifferentResult {
	public RedMilk(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack pStack, World pLevel, LivingEntity entity) {
		switch(rng(4)) {
			case 0 -> {
				entity.addEffect(new EffectInstance(BLINDNESS, 20 * 26, 0));
				entity.addEffect(new EffectInstance(POISON, 20 * 26, 0));
				entity.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 26, 0));
			}
			case 1 -> {
				entity.addEffect(new EffectInstance(REGENERATION, 20 * 30, 0));
				entity.addEffect(new EffectInstance(HARM, 5, 0));
				entity.addEffect(new EffectInstance(NIGHT_VISION, 20 * 30, 0));
			}
			case 2 -> {
				entity.addEffect(new EffectInstance(REGENERATION, 20 * 20, 0));
				entity.addEffect(new EffectInstance(POISON, 20 * 20, 0));
			}
			case 3 -> {
				entity.addEffect(new EffectInstance(MOVEMENT_SPEED, 20 * 60 * 8, 0));
				entity.addEffect(new EffectInstance(JUMP, 20 * 60 * 8, 0));
				entity.addEffect(new EffectInstance(BLINDNESS, 20 * 60 * 8, 0));
			}
		}
		return BUCKET.getDefaultInstance();
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(withColor("This milk looks kinda disturbing... I'm not sure if I wanna drink it...", RED));
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.DRINK;
	}

	@Override
	public Pair<Integer, Optional<Ingredients>> getResult(ItemStack stack) {
		return Pair.of(1, Optional.of(Ingredients.ing().add(MILK_BUCKET).add(SPIDER_EYE).add(ROTTEN_FLESH).add(Core).add(CoreIngot)));
	}
}
