package mod.rozbrajaczpoziomow.testing.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		gen.addProvider(new Recipes(gen));
		BlockTagGenerator blockTagGenerator = new BlockTagGenerator(gen, event.getExistingFileHelper());
		gen.addProvider(blockTagGenerator);
		gen.addProvider(new ItemTagGenerator(gen, blockTagGenerator, event.getExistingFileHelper()));
	}
}
