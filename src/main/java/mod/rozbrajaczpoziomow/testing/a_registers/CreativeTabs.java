package mod.rozbrajaczpoziomow.testing.a_registers;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTabs {
	public static final ItemGroup Sniwek = new ItemGroup("sniwek_tab") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.Sniw.get());
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	};

	public static final ItemGroup Rybkek = new ItemGroup("rybkek_tab") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegister.Rybek.get());
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	};

	public static final ItemGroup Shitpost = new ItemGroup("shitpost_tab") {
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
