package mod.rozbrajaczpoziomow.testing.a_registers;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;

public class TagRegister {
    public static final Tags.IOptionalNamedTag<Block> TagCorbiBlocks = tag("corbi");
    public static final Tags.IOptionalNamedTag<Block> TagCorbleBlocks = tag("corble");
    public static final Tags.IOptionalNamedTag<Block> TagIBAGlowdustableBlocks = tag("glowdustable");

    private static Tags.IOptionalNamedTag<Block> tag(String name) {
        return BlockTags.createOptional(new ResourceLocation(MOD_ID, name));
    }
}
