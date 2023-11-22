package mod.rozbrajaczpoziomow.testing.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;
import static mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister.*;
import static mod.rozbrajaczpoziomow.testing.a_registers.TagRegister.*;
import static net.minecraft.tags.BlockTags.LEAVES;
import static net.minecraftforge.common.Tags.Blocks.GLASS;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(DataGenerator gen, @Nullable ExistingFileHelper fileHelper) {
        super(gen, MOD_ID, fileHelper);
    }

    @Override
    protected void addTags() {
        tag(LEAVES).add(IBAIndustrialLeaves.get(), IBAMansionLeaves.get());
        tag(GLASS).add(GarawaGlass.get(), RelicGlass.get(), USPGlass.get());
        tag(TagCorbiBlocks).add(KasuliBlock.get(), AsuliBlock.get(), TemponariumBlock.get(), TemmiraopBlock.get(), JungleCorbi.get(), WhiteCorbi.get(), GreyCorbi.get(), IcyCorbi.get(), OceanCorbi.get(), LeafCorbi.get(), BlueCorbi.get(), ErrorCorbi.get(), BlackCorbi.get(), OtherBlueCorbi.get(), NotCorbi.get(), BigCorbi.get(), LookingCorbi.get(), WitherCorbi.get());
        tag(TagCorbleBlocks).add(StoneCorble.get(), MossyCorble.get(), DeadCorble.get(), ReverbedMossyCorble.get(), ReverbedMossyDeadCorble.get(), ReverbedAgainMossyCorble.get(), MossyFullCorble.get(), GrassCorble.get(), WeirdCorble.get(), LightCorble.get(), GrassusCorble.get(), BlackGrassCorble.get());
        tag(TagGlowdustableBlocks).add(IBAIndustrialBricks.get(), IBAIndustrialBricks2.get(), IBAIndustrialBricks3.get(), IBAIndustrialBricks4.get(), IBAIndustrialBricks5.get(), IBAIndustrialBricks6.get(), IBAIndustrialBricks7.get(), IBAIndustrialLeaves.get(), IBAMansionLeaves.get(), IBAMansionFancyBricks.get(), IBAMansionBricks.get(), IBAMansionBricks2.get(), IBAMansionBlock.get(), IBAGrasso.get(), IBAGarararaso.get(), IBAToxicGrass.get());
    }
}
