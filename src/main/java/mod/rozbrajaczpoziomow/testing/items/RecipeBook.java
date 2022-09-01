package mod.rozbrajaczpoziomow.testing.items;

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

public class RecipeBook extends Item {
	private int tick = 0;

	public RecipeBook(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("Unlocks all recipes from this mod in the recipe book."));
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		final String nbt = "testing:unlocked_recipes";

		if(world.isClientSide) return;
		if(!(entity instanceof PlayerEntity player)) { stack.setCount(0); return; }
		if(++tick % 5 != 0) return;
		if(player.getPersistentData().contains(nbt)) return;
		player.getPersistentData().putBoolean(nbt, true);
		player.inventory.removeItem(stack);
	}
}
