package mod.rozbrajaczpoziomow.testing.a_registers;

import mod.rozbrajaczpoziomow.testing.tile_entities.ReshifterTile;
import mod.rozbrajaczpoziomow.testing.tile_entities.SnowEradicatorTile;
import mod.rozbrajaczpoziomow.testing.tile_entities.StoneGenITile;
import mod.rozbrajaczpoziomow.testing.tile_entities.UncraftingTableTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;
import static mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister.*;
import static net.minecraft.tileentity.TileEntityType.Builder.of;

public class TileEntityRegister {
	public static final DeferredRegister<TileEntityType<?>> TileEntityConstruct = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

	public static final RegistryObject<TileEntityType<ReshifterTile>> ReshifterTE = TileEntityConstruct.register("reshifter_tile", () -> of(ReshifterTile::new, Reshifter.get()).build(null));
	public static final RegistryObject<TileEntityType<SnowEradicatorTile>> SnowEradicatorTE = TileEntityConstruct.register("snow_eradicator_tile", () -> of(SnowEradicatorTile::new, SnowEradicator.get()).build(null));
	public static final RegistryObject<TileEntityType<StoneGenITile>> StoneGenITE = TileEntityConstruct.register("stone_gen_i_tile", () -> of(StoneGenITile::new, StoneGenI.get()).build(null));
	public static final RegistryObject<TileEntityType<UncraftingTableTile>> UncraftingTableTE = TileEntityConstruct.register("uncrafting_table_tile", () -> of(UncraftingTableTile::new, UncraftingTable.get()).build(null));

	public static void register(IEventBus eventBus) {
		TileEntityConstruct.register(eventBus);
	}
}
