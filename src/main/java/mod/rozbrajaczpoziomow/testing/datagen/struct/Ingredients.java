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
		if(list.size() > 9) throw new IllegalArgumentException("More than 9 ingredients in Ingredients.");
		list.add(ing);
		return this;
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
//	public Ingredients add(RegistryObject<Block> item) {
//		return add(item.get());
//	}

	public ArrayList<Ingredient> get() {
		return list;
	}
}
