package mod.rozbrajaczpoziomow.testing.items.custom;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

import static net.minecraft.util.Hand.MAIN_HAND;

@MethodsReturnNonnullByDefault @ParametersAreNonnullByDefault
public class Yeetr extends Item {
	public Yeetr(Properties props) {
		super(props);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity tt, LivingEntity attacker) {
		if(!(tt instanceof PlayerEntity target)) return true;

		double x = target.getPosX();
		double y = target.getPosY();
		double z = target.getPosZ();

		target.setPosition(x, 250, z);
		target.inventory.dropAllItems();
		target.setPosition(x, y, z);
		target.setHeadRotation(180f, 180);

		if(stack.getMaxDamage() > 0) stack.damageItem(1, attacker, p -> p.sendBreakAnimation(MAIN_HAND));
		return true;
	}
}
