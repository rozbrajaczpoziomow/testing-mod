package mod.rozbrajaczpoziomow.testing.a_registers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;

public class SoundRegister {
	public static final DeferredRegister<SoundEvent> SoundEventsConstruct = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);

	public static final RegistryObject<SoundEvent> corbi = r("corbi");
	public static final RegistryObject<SoundEvent> corbi_step = r("corbi_step");
	public static final RegistryObject<SoundEvent> looking_corbi = r("looking_corbi");
	public static final RegistryObject<SoundEvent> corble = r("corble");
	public static final RegistryObject<SoundEvent> wither_corbi_break = r("wither_corbi_break");
	public static final RegistryObject<SoundEvent> wither_corbi_step = r("wither_corbi_step");
	public static final RegistryObject<SoundEvent> wither_corbi_place = r("wither_corbi_place");
	public static final RegistryObject<SoundEvent> alt_void_place = r("alt_void_place");
	public static final RegistryObject<SoundEvent> alt_void_step = r("alt_void_step");
	public static final RegistryObject<SoundEvent> alt_void_break = r("alt_void_break");
	public static final RegistryObject<SoundEvent> void_block_place = r("void_block_place");
	public static final RegistryObject<SoundEvent> void_block_step = r("void_block_step");
	public static final RegistryObject<SoundEvent> void_block_break = r("void_block_break");
	public static final RegistryObject<SoundEvent> reshifter_shift = r("reshifter_shift");
	public static final RegistryObject<SoundEvent> aura_diamond_i = r("aura_diamond_i");
	public static final RegistryObject<SoundEvent> aura_diamond_ii = r("aura_diamond_ii");
	public static final RegistryObject<SoundEvent> reshifted_diamond_sword = r("reshifted_diamond_sword");

	private static RegistryObject<SoundEvent> r(String key) {
		return SoundEventsConstruct.register(key, () -> new SoundEvent(new ResourceLocation(MOD_ID, key)));
	}

	public static void register(IEventBus eventBus) {
		SoundEventsConstruct.register(eventBus);
	}
}
