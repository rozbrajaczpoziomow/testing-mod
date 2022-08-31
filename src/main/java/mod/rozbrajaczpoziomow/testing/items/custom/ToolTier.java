package mod.rozbrajaczpoziomow.testing.items.custom;

import mod.rozbrajaczpoziomow.testing.items.ItemRegister;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import static net.minecraft.item.ItemTier.IRON;

import java.util.function.Supplier;

public enum ToolTier implements IItemTier {

	MACHETE(0, 50, 0f, 0f, 10, () -> Ingredient.fromItems(ItemRegister.Machete.get())),
	CROWBAR(6, 100, 1.5f, 0f, 0, () -> Ingredient.fromItems(ItemRegister.Crowbar.get()));

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;

	ToolTier(int harvestLevel, int maxUses, float efficiency,
	            float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairMaterial = new LazyValue<>(repairMaterial);
	}

	@Override
	public int getMaxUses() {
		return maxUses;
	}

	@Override
	public float getEfficiency() {
		return efficiency;
	}

	@Override
	public float getAttackDamage() {
		return attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return repairMaterial.getValue();
	}
}
