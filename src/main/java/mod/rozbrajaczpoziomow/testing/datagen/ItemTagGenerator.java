package mod.rozbrajaczpoziomow.testing.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;
import static mod.rozbrajaczpoziomow.testing.a_registers.TagRegister.TagDyesItems;
import static net.minecraft.item.Items.*;

public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(DataGenerator gen, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, blockTagsProvider, MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TagDyesItems).add(Items.WHITE_DYE, ORANGE_DYE, MAGENTA_DYE, LIGHT_BLUE_DYE, YELLOW_DYE, LIME_DYE, PINK_DYE, GRAY_DYE, LIGHT_GRAY_DYE, CYAN_DYE, PURPLE_DYE, BLUE_DYE, BROWN_DYE, GREEN_DYE, RED_DYE, BLACK_DYE);
    }
}
