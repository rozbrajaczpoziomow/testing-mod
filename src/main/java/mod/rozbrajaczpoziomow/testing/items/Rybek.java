package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.text;

public class Rybek extends Item {
	public Rybek(Properties properties) {
		super(properties);
	}

	private int current = 0;
	private int tick = 0;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(!(entity instanceof PlayerEntity)) {
			entity.kill();
			return;
		}
		if(++tick % 5 == 0) current++;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		final char chr = ' ';
		final char[] base = StringUtils.repeat(chr, 70).toCharArray();
		int idx = current;
		for(int i = 0; i < base.length / 2; i++) {
			if(++idx > base.length - 1) idx %= base.length - 1;

			int minus = idx - i * 2;
			minus = minus < 0? minus + base.length - 1 : minus;

			base[idx] = '\\';
			base[minus] = '/';
			if(idx == minus) base[idx] = '_';

			tooltip.add(text(String.valueOf(base)));

			base[idx] = chr;
			base[minus] = chr;
		}
	}
}
