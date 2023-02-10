package mod.rozbrajaczpoziomow.testing.a_registers;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.entities.AuraDiamondI;
import mod.rozbrajaczpoziomow.testing.entities.AuraDiamondII;
import mod.rozbrajaczpoziomow.testing.entities.Projectile;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegister {
	public static final DeferredRegister<EntityType<?>> EntityRegistry = DeferredRegister.create(ForgeRegistries.ENTITIES, TestingMod.MOD_ID);

	public static final RegistryObject<EntityType<Projectile>> Projectile = EntityRegistry.register("projectile", () -> EntityType.Builder.of(Projectile::new, EntityClassification.MISC).sized(.5f, .5f).build(new ResourceLocation(TestingMod.MOD_ID, "projectile").toString()));
	public static final RegistryObject<EntityType<AuraDiamondI>> AuraDiamondI = EntityRegistry.register("aura_diamond_i", () -> EntityType.Builder.of(AuraDiamondI::new, EntityClassification.MISC).sized(.5f, .5f).build(new ResourceLocation(TestingMod.MOD_ID, "aura_diamond_i").toString()));
	public static final RegistryObject<EntityType<AuraDiamondII>> AuraDiamondII = EntityRegistry.register("aura_diamond_ii", () -> EntityType.Builder.of(AuraDiamondII::new, EntityClassification.MISC).sized(.5f, .5f).build(new ResourceLocation(TestingMod.MOD_ID, "aura_diamond_ii").toString()));

	public static void register(IEventBus bus) {
		EntityRegistry.register(bus);
	}
}
