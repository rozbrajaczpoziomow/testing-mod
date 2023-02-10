package mod.rozbrajaczpoziomow.testing.worldgen;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TestingMod.MOD_ID)
public class WorldEvents {
	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		USPOreGeneration.gen(event);
		MacheteOreGeneration.gen(event);
		UnknownBlockGeneration.gen(event);
		LoabinOreGeneration.gen(event);
		LoabinOreGeneration.gen(event);
		GMDMilkShelfGeneration.gen(event);
		MilkShelfGeneration.gen(event);
		StoneGenOreGeneration.gen(event);
	}
}
