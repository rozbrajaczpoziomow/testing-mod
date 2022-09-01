package mod.rozbrajaczpoziomow.testing.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_TOTAL;

public class Rybek extends Item {
	public Rybek(Properties properties) {
		super(properties);
	}
	private int current = 0;
	private int tick = 0;
	private final UUID attr_uuid = UUID.fromString("09706001-6969-4242-8888-694204206969");

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(!(entity instanceof PlayerEntity)) entity.hurt(DamageSource.FLY_INTO_WALL, Float.MAX_VALUE);
		if(++tick % 5 == 0) current++;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		if(slot != EquipmentSlotType.OFFHAND)
			return super.getAttributeModifiers(slot, stack);

		return ImmutableMultimap.of(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(attr_uuid, "testing:rybek", -2, MULTIPLY_TOTAL));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		final char chr = ' ';
		char[] base = StringUtils.repeat(chr, 100).toCharArray();
		int idx = current;
		for(int i = 0; i < 50; i++) {
			if(++idx > base.length - 1) idx %= base.length - 1;
			base[idx] = '\\';
			tooltip.add(text(String.valueOf(base)));
			base[idx] = chr;
		}
	}
}
