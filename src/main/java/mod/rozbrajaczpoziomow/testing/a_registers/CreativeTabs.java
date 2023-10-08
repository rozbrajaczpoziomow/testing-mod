package mod.rozbrajaczpoziomow.testing.a_registers;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister.IBAIndustrialBricks;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.*;

public class CreativeTabs {
	public static final ItemGroup Sniwek = new ItemGroup("testing_sniwek") {
		@Override
		public ItemStack makeIcon() {
			return Sniw.get().getDefaultInstance();
		}
	};

	public static final ItemGroup Rybkek = new ItemGroup("testing_rybkek") {
		@Override
		public ItemStack makeIcon() {
			return Rybek.get().getDefaultInstance();
		}
	};

	public static final ItemGroup Shitpost = new ItemGroup("testing_shitpost") {
		@Override
		public ItemStack makeIcon() {
			return Shop.get().getDefaultInstance();
		}
	};

	public static final ItemGroup TabBlocks = new ItemGroup("testing_blocks") {
		@Override
		public ItemStack makeIcon() {
			return IBAIndustrialBricks.get().asItem().getDefaultInstance();
		}
	};
}
