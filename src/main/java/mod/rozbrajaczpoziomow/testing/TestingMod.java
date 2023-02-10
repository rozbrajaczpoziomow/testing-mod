package mod.rozbrajaczpoziomow.testing;

import mod.rozbrajaczpoziomow.testing.a_registers.*;
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
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		SoundRegister.register(bus);
		BlockRegister.register(bus);
		ItemRegister.register(bus);
		EntityRegister.register(bus);
		TileEntityRegister.register(bus);
	}
}