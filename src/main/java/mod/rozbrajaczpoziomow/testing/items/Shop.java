package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.item.Items.EMERALD;
import static net.minecraft.util.DamageSource.DROWN;
import static net.minecraft.util.text.TextFormatting.GREEN;

public class Shop extends Item {
	private final int updateOn = 15 * 60;
	private final String secondsNBT = "testing:seconds";
	public Shop(Properties properties) {
		super(properties);
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(world.isClientSide) return;
		if(!(entity instanceof PlayerEntity player)) { entity.kill(); return; }
		if(player.tickCount % 20 != 0) return;

		if(!stack.getOrCreateTag().contains(secondsNBT))
			stack.getTag().putInt(secondsNBT, 0);

		int seconds = stack.getTag().getInt(secondsNBT);
		if(++seconds >= updateOn) {
			seconds -= player.getOffhandItem().getItem() == ItemRegister.Rybek.get()? 5 : updateOn;
			if(!player.addItem(EMERALD.getDefaultInstance()))
				player.drop(EMERALD.getDefaultInstance(), false);
			player.hurt(DROWN.bypassInvul().bypassArmor().bypassMagic(), 1f);
			sendMessage(player, "GET SHOPPED lmfao. You sold your life for 1 emerald. Loser.", GREEN);
		}
		stack.getTag().putInt(secondsNBT, seconds);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if(stack.getTag() == null) return;
		final int seconds = stack.getTag().getInt(secondsNBT);
		tooltip.add(withColor(String.format("%d min %d sec / %d min", seconds / 60, seconds % 60, updateOn / 60), GREEN));
	}
}
