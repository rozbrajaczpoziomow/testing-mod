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

import static mod.rozbrajaczpoziomow.testing.Utils.text;

public class MrClean extends Item {
	public MrClean(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(entity instanceof PlayerEntity)
			((PlayerEntity) entity).inventory.setItem(itemSlot, ItemRegister.Augustus.get().getDefaultInstance());
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("Mr. Clean is a brand name and mascot, owned by the American company Procter & Gamble, used for an all-purpose cleaner and later also for a melamine foam abrasive sponge."));
		tooltip.add(text("The all-purpose cleaner was originally formulated by Linwood Burton, a marine ship cleaning businessman with accounts throughout the east coast of the United States and his friend, Mathusan Chandramohan,[1] a rich entrepreneur from Sri Lanka.[2]"));
		tooltip.add(text("Mr. Clean made his television commercial debut in 1958, initially portrayed in the live-action versions by character actor House Peters Jr.[3]"));
		tooltip.add(text(""));
		tooltip.add(text("- International versions"));
		tooltip.add(text("Don Limpio, in Spain; originally Mr. Proper"));
		tooltip.add(text("Maestro Limpio, in Mexico"));
		tooltip.add(text("Mastro Lindo, in Italy"));
		tooltip.add(text("Meister Proper, in Germany"));
		tooltip.add(text("Mr. Proper, in Eastern Europe, including Bulgaria,[5] Kazakhstan,[6] Russia,[7] and Ukraine.[8]"));
		tooltip.add(text("M. Net, in French Canada"));
		tooltip.add(text("Monsieur Propre, in France"));
		tooltip.add(text(""));
		tooltip.add(text("In the UK and Ireland, the product is sold under the brand name Flash;[9] this is because a company exists that uses the \"Mr. Clean\" name.[10] Furthermore, Flash does not use a mascot, unlike Mr. Clean.[11] For many years Flash was advertised on UK television by Scottish actress Molly Weir, with the catchphrase \"Flash cleans floors WITHOUT scratching\". Since 2016, adverts for Flash have included parodies of the song Flash by Queen.[12][13]"));
	}
}
