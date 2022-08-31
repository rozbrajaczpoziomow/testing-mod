package mod.rozbrajaczpoziomow.testing.blocks.custom;

import net.minecraft.block.Block;

public class Glass extends Block {
	public Glass(Properties properties) {
		super(properties.noOcclusion());
	}
}
