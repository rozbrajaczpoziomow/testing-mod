package mod.rozbrajaczpoziomow.testing.a_registers;

import mod.rozbrajaczpoziomow.testing.effects.ShuffleInventoryEffect;
import mod.rozbrajaczpoziomow.testing.effects.StripperEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;

public class EffectRegister {
	public static final DeferredRegister<Effect> EffectConstruct = DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

	public static final RegistryObject<ShuffleInventoryEffect> ShuffleInventoryEffect = r("shuffle_inventory", new ShuffleInventoryEffect());
	public static final RegistryObject<StripperEffect> StripperEffect = r("stripper", new StripperEffect()); // TODO: recipe - mundane potion + yeetr x lol

	public static <T extends Effect> RegistryObject<T> r(String n, T effect) {
		return EffectConstruct.register(n, () -> effect);
	}

	public static void register(IEventBus eventBus) {
		EffectConstruct.register(eventBus);
	}
}
