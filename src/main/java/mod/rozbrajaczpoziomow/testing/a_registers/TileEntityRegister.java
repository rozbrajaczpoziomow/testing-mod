package mod.rozbrajaczpoziomow.testing.a_registers;

import mod.rozbrajaczpoziomow.testing.tile_entities.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;
import static mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister.*;
import static net.minecraft.tileentity.TileEntityType.Builder.of;
import static net.minecraftforge.registries.ForgeRegistries.TILE_ENTITIES;

public class TileEntityRegister {
	public static final DeferredRegister<TileEntityType<?>> TileEntityConstruct = DeferredRegister.create(TILE_ENTITIES, MOD_ID);

	public static final RegistryObject<TileEntityType<ReshifterTile>> ReshifterTE = TileEntityConstruct.register("reshifter_tile", () -> of(ReshifterTile::new, Reshifter.get()).build(null));
	public static final RegistryObject<TileEntityType<SnowEradicatorTile>> SnowEradicatorTE = TileEntityConstruct.register("snow_eradicator_tile", () -> of(SnowEradicatorTile::new, SnowEradicator.get()).build(null));
	public static final RegistryObject<TileEntityType<StoneGenITile>> StoneGenITE = TileEntityConstruct.register("stone_gen_i_tile", () -> of(StoneGenITile::new, StoneGenI.get()).build(null));
	public static final RegistryObject<TileEntityType<UncraftingTableTile>> UncraftingTableTE = TileEntityConstruct.register("uncrafting_table_tile", () -> of(UncraftingTableTile::new, UncraftingTable.get()).build(null));
	public static final RegistryObject<TileEntityType<RainbowBlockTile>> RainbowBlockTE = TileEntityConstruct.register("rainbow_block_tile", () -> of(RainbowBlockTile::new, RainbowBlock.get()).build(null));

	public static void register(IEventBus eventBus) {
		TileEntityConstruct.register(eventBus);
	}
}
