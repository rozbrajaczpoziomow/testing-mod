package mod.rozbrajaczpoziomow.testing.datagen.struct;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;

import static net.minecraft.item.crafting.Ingredient.of;

public class Ingredients {
	private final ArrayList<Ingredient> list = new ArrayList<>();

	public static Ingredients ing() { return new Ingredients(); }

	public Ingredients add(Ingredient ing) {
		list.add(ing);
		return this;
	}

	public Ingredients add(IItemProvider item, int n) {
		for(int i = 0; i < n; i++)
			add(of(item));
		return this;
	}
	public Ingredients add(ITag<Item> tag, int n) {
		for(int i = 0; i < n; i++)
			add(of(tag));
		return this;
	}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Ingredients add(RegistryObject reg, int n) {
		try {
			return add(((RegistryObject<Item>) reg).get(), n);
		} catch(Exception ignored) {
			return add(((RegistryObject<Block>) reg).get(), n);
		}
	}

	public Ingredients add(IItemProvider item) {
		return add(of(item));
	}
	public Ingredients add(ITag<Item> tag) {
		return add(of(tag));
	}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Ingredients add(RegistryObject reg) {
		try {
			return add(((RegistryObject<Item>) reg).get());
		} catch(Exception ignored) {
			try {
				return add(((RegistryObject<Block>) reg).get());
			} catch(Exception ignored1) {
				throw new IllegalArgumentException();
			}
		}
	}

	public ArrayList<Ingredient> get() {
		return list;
	}
}
