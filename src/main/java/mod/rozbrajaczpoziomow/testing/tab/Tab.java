package mod.rozbrajaczpoziomow.testing.tab;

import mod.rozbrajaczpoziomow.testing.items.ItemRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Tab {
	public static final ItemGroup Sniwek = new ItemGroup("sniwekTab") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.Sniw.get());
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	};

	public static final ItemGroup Rybkek = new ItemGroup("rybkekTab") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.Rybek.get());
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	};
}
