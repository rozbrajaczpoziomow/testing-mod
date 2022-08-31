package mod.rozbrajaczpoziomow.testing.items.custom;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static net.minecraft.potion.Effects.BLINDNESS;
import static net.minecraft.util.text.TextFormatting.LIGHT_PURPLE;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class VoidCore extends Item {
	public VoidCore(Properties builderIn) {
		super(builderIn);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		for(ServerPlayerEntity player : Objects.requireNonNull(target.getServer()).getPlayerList().getPlayers())
			player.addPotionEffect(new EffectInstance(BLINDNESS, 20 * 2, 0));
		return true;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(!(entity instanceof LivingEntity livingEntity)) return;
		stack.damageItem(1, livingEntity, e -> e.sendMessage(withColor("Your Void Core broke!", LIGHT_PURPLE), e.getUniqueID()));
	}
}
