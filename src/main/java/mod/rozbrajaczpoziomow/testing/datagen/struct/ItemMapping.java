package mod.rozbrajaczpoziomow.testing.datagen.struct;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashMap;

import static net.minecraft.item.crafting.Ingredient.of;

public class ItemMapping {
	private final HashMap<Character, Ingredient> map = new HashMap<>();

	public static ItemMapping map() { return new ItemMapping(); }

	public ItemMapping add(char c, Ingredient ing) {
		if(map.keySet().size() > 9) throw new IllegalArgumentException("More than 9 ingredients in ItemMapping.");
		map.put(c, ing);
		return this;
	}

	public ItemMapping add(char c, IItemProvider item) {
		return add(c, of(item));
	}
	public ItemMapping add(char c, ITag<Item> tag) {
		return add(c, of(tag));
	}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ItemMapping add(char c, RegistryObject reg)  {
		try {
			return add(c, ((RegistryObject<Item>) reg).get());
		} catch(Exception ignored) {
			try {
				return add(c, ((RegistryObject<Block>) reg).get());
			} catch(Exception ignored1) {
				throw new IllegalArgumentException();
			}
		}
	}

	public HashMap<Character, Ingredient> get() {
		return map;
	}
}
