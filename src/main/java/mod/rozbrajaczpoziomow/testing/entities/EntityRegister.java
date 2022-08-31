package mod.rozbrajaczpoziomow.testing.entities;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegister {
	public static final DeferredRegister<EntityType<?>> EntityRegistry = DeferredRegister.create(ForgeRegistries.ENTITIES, TestingMod.MOD_ID);

	public static final RegistryObject<EntityType<Projectile>> Projectile = EntityRegistry.register("projectile", () -> EntityType.Builder.create(Projectile::new, EntityClassification.MISC).size(.5f, .5f).build(new ResourceLocation(TestingMod.MOD_ID, "projectile").toString()));

	public static void register(IEventBus bus) {
		EntityRegistry.register(bus);
	}
}
