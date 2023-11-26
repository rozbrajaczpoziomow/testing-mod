package mod.rozbrajaczpoziomow.testing.a_registers;

import mod.rozbrajaczpoziomow.testing.effects.PortalEffect;
import mod.rozbrajaczpoziomow.testing.effects.ShuffleInventoryEffect;
import mod.rozbrajaczpoziomow.testing.effects.StripperEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;

public class EffectRegister {
	public static final DeferredRegister<Effect> EffectConstruct = DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

	public static final RegistryObject<ShuffleInventoryEffect> ShuffleInventoryEffect = r("shuffle_inventory", new ShuffleInventoryEffect());
	public static final RegistryObject<StripperEffect> StripperEffect = r("stripper", new StripperEffect());
	public static final RegistryObject<PortalEffect> PortalEffect = r("portal", new PortalEffect());


	public static final DeferredRegister<Potion> PotionConstruct = DeferredRegister.create(ForgeRegistries.POTION_TYPES, MOD_ID);

	public static final RegistryObject<Potion> ShuffleInventoryPotion = p("shuffle_inventory", () -> new Potion(new EffectInstance(ShuffleInventoryEffect.get(), 20 * 60)));
	public static final RegistryObject<Potion> StripperPotion = p("stripper", () -> new Potion(new EffectInstance(StripperEffect.get(), 20 * 10)));
	public static final RegistryObject<Potion> PortalPotion = p("portal", () -> new Potion(new EffectInstance(PortalEffect.get(), 20 * 10, 2)));

	public static <T extends Effect> RegistryObject<T> r(String n, T effect) {
		return EffectConstruct.register(n, () -> effect);
	}

	public static <T extends Potion> RegistryObject<T> p(String n, Supplier<T> potion) {
		return PotionConstruct.register(n, potion);
	}

	public static void register(IEventBus eventBus) {
		EffectConstruct.register(eventBus);
		PotionConstruct.register(eventBus);
	}
}
