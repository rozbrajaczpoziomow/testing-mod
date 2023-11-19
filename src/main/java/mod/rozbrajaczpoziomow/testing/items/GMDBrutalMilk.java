package mod.rozbrajaczpoziomow.testing.items;

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

import static mod.rozbrajaczpoziomow.testing.Utils.rng;
import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.item.Items.BUCKET;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.RED;

public class GMDBrutalMilk extends Item {
	public GMDBrutalMilk(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		if(rng() < .5) {
			entity.addEffect(new EffectInstance(CONFUSION, 20 * 60 * 10, 2));
			entity.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 30, 0));
			entity.addEffect(new EffectInstance(POISON, 20 * 10, 0));
		} else {
			entity.addEffect(new EffectInstance(REGENERATION, 20 * 60 * 2, 3));
			entity.addEffect(new EffectInstance(CONFUSION, 20 * 30, 0));
			entity.addEffect(new EffectInstance(LEVITATION, 20 * 2, 0));
			entity.addEffect(new EffectInstance(POISON, 20 * 3, 0));
			entity.addEffect(new EffectInstance(HUNGER, 20 * 56, 0));
		}
		return BUCKET.getDefaultInstance();
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
		pTooltip.add(text("There is a note on the back of this bucket and it reads: This milk is obtained 100% without any brutality towards animals! Just for our customers!", RED));
	}

	@Override
	public UseAction getUseAnimation(ItemStack pStack) {
		return UseAction.DRINK;
	}
}
