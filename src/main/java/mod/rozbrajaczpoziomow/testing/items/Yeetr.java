package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.minecraft.util.Hand.MAIN_HAND;

public class Yeetr extends Item {
	public Yeetr(Properties props) {
		super(props);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity tt, LivingEntity attacker) {
		if(!(tt instanceof PlayerEntity)) return true;

		PlayerEntity target = (PlayerEntity) tt;

		double x = target.getX();
		double y = target.getY();
		double z = target.getZ();

		target.teleportTo(x, 250, z);
		target.inventory.dropAll();
		target.teleportTo(x, y, z);
		if(target.getCommandSenderWorld().isClientSide)
			target.lerpHeadTo(180f, 180);

		if(stack.getItem().canBeDepleted()) stack.hurtAndBreak(1, attacker, p -> p.broadcastBreakEvent(MAIN_HAND));
		return true;
	}
}
