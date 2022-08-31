package mod.rozbrajaczpoziomow.testing;

import mod.rozbrajaczpoziomow.testing.blocks.BlockRegister;
import mod.rozbrajaczpoziomow.testing.entities.EntityRegister;
import mod.rozbrajaczpoziomow.testing.entities.renderer.ProjectileRenderer;
import mod.rozbrajaczpoziomow.testing.items.ItemRegister;
import mod.rozbrajaczpoziomow.testing.sounds.iSounds;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraft.client.renderer.RenderType.getTranslucent;

@Mod(TestingMod.MOD_ID)
public class TestingMod {
	public static final String MOD_ID = "testing";
	public static final Logger LOGGER = LogManager.getLogger();

	public TestingMod() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		eventBus.addListener(this::reg);
		iSounds.register(eventBus);
		ItemRegister.register(eventBus);
		BlockRegister.register(eventBus);
		EntityRegister.register(eventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void reg(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityRegister.Projectile.get(), ProjectileRenderer::new);
	}

	@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientEventBusSubscriber {
		@SubscribeEvent
		public static void onStaticClientSetup(FMLClientSetupEvent event) {
			for(RegistryObject<Block> glass : BlockRegister.getTranslucent()) {
				RenderTypeLookup.setRenderLayer(glass.get(), getTranslucent());
			}
		}
	}
}