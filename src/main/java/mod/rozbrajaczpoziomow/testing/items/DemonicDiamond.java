package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.potion.Effects.BLINDNESS;
import static net.minecraft.util.text.TextFormatting.RED;

public class DemonicDiamond extends Item {
	public DemonicDiamond(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack pStack, World pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		if(!(pEntity instanceof PlayerEntity)) { pEntity.kill(); return; }
		if(!pIsSelected) return;
		PlayerEntity player = (PlayerEntity) pEntity;

		if(!player.isOnFire()) // To prevent overriding other fire sources, like lava
			player.setSecondsOnFire(1);
		player.addEffect(new EffectInstance(BLINDNESS, 20 * 2, 0));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("This really hot diamond burns you the instant you touch it...", RED));
	}
}
