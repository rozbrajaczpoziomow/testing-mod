package mod.rozbrajaczpoziomow.testing;

import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.EntityRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TestingMod.MOD_ID)
public class TestingMod {
	public static final String MOD_ID = "testing";
	public static final Logger LOGGER = LogManager.getLogger();

	public TestingMod() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		SoundRegister.register(eventBus);
		ItemRegister.register(eventBus);
		BlockRegister.register(eventBus);
		EntityRegister.register(eventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}
}