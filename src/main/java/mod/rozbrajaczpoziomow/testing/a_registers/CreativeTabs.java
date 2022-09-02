package mod.rozbrajaczpoziomow.testing.a_registers;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.*;

public class CreativeTabs {
	public static final ItemGroup Sniwek = new ItemGroup("sniwek_tab") {
		@Override
		public ItemStack makeIcon() {
			return Sniw.get().getDefaultInstance();
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	};

	public static final ItemGroup Rybkek = new ItemGroup("rybkek_tab") {
		@Override
		public ItemStack makeIcon() {
			return Rybek.get().getDefaultInstance();
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	};

	public static final ItemGroup Shitpost = new ItemGroup("shitpost_tab") {
		@Override
		public ItemStack makeIcon() {
			return Shop.get().getDefaultInstance();
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	};
}
