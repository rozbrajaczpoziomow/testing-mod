package mod.rozbrajaczpoziomow.testing.a_registers;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

import static mod.rozbrajaczpoziomow.testing.a_registers.CreativeTabs.Rybkek;
import static mod.rozbrajaczpoziomow.testing.a_registers.CreativeTabs.Sniwek;
import static mod.rozbrajaczpoziomow.testing.items.ToolTier.CROWBAR;
import static net.minecraft.block.AbstractBlock.Properties.of;
import static net.minecraft.block.material.Material.*;
import static net.minecraft.block.material.MaterialColor.LAPIS;
import static net.minecraft.item.ItemTier.NETHERITE;
import static net.minecraft.item.Items.DIAMOND_HOE;
import static net.minecraft.item.Items.NETHERITE_HOE;
import static net.minecraftforge.common.ToolType.*;

@SuppressWarnings("unused")
public class BlockRegister {
	private static final DeferredRegister<Block> BlocksConstruct = DeferredRegister.create(ForgeRegistries.BLOCKS, TestingMod.MOD_ID);

	// Ores
	public static final RegistryObject<Block> MacheteOre = r("machete_ore", new Block_onlyMinedBy(new Item[] {DIAMOND_HOE, NETHERITE_HOE}, of(STONE).harvestLevel(3).harvestTool(HOE).requiresCorrectToolForDrops().strength(100f)), Rybkek);
	public static final RegistryObject<Block> USPDrop = r("usp_drop", new USPDrop(of(STONE).harvestLevel(CROWBAR.getLevel()).harvestTool(HOE).requiresCorrectToolForDrops().strength(4f)), Sniwek);
	public static final RegistryObject<Block> UnknownBlock = r("unknown_block", new Block(of(STONE).harvestLevel(2).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(4f)), Sniwek);
	public static final RegistryObject<Block> LoabinOre = r("loabin_ore", new Block(of(STONE).harvestLevel(2).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(4f)), Sniwek);
	public static final RegistryObject<Block> IngotOre = r("ingot_ore", new Block(of(STONE).harvestLevel(3).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(6f)), Sniwek);
	public static final RegistryObject<Block> GMDMilkShelf = r("gmd_milk_shelf", new GMDMilkShelf(of(STONE).strength(10f)), Sniwek);
	public static final RegistryObject<Block> MilkShelf = r("milk_shelf", new MilkShelf(of(STONE).strength(10f)), Sniwek);
	public static final RegistryObject<Block> StoneGenICoreOre = r("stone_gen_i_core_ore", new StoneGenICoreOre(of(STONE).strength(4f).harvestLevel(1).harvestTool(PICKAXE).requiresCorrectToolForDrops()), Sniwek);

	// Corbis
	public static final RegistryObject<Block> KasuliBlock = r("kasuli_block", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> AsuliBlock = r("asuli_block", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> TemponariumBlock = r("temponarium_block", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> TemmiraopBlock = r("temmiraop_block", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> JungleCorbi = r("jungle_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> WhiteCorbi = r("white_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> GreyCorbi = r("grey_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> IcyCorbi = r("icy_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> OceanCorbi = r("ocean_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> LeafCorbi = r("leaf_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> BlueCorbi = r("blue_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> ErrorCorbi = r("error_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> BlackCorbi = r("black_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> OtherBlueCorbi = r("other_blue_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> NotCorbi = r("not_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> BigCorbi = r("big_corbi", new Corbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> LookingCorbi = r("looking_corbi", new LookingCorbi(Corbi.Settings), Sniwek);
	public static final RegistryObject<Block> WitherCorbi = r("wither_corbi", new WitherCorbi(of(STONE).strength(25f).harvestLevel(NETHERITE.getLevel()).harvestTool(PICKAXE).requiresCorrectToolForDrops()), Sniwek);

	// Corbles
	public static final RegistryObject<Block> StoneCorble = r("stone_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> MossyCorble = r("mossy_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> DeadCorble = r("dead_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> ReverbedMossyCorble = r("reverbed_mossy_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> ReverbedMossyDeadCorble = r("reverbed_mossy_dead_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> ReverbedAgainMossyCorble = r("reverbed_again_mossy_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> MossyFullCorble = r("mossy_full_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> GrassCorble = r("grass_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> WeirdCorble = r("weird_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> LightCorble = r("light_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> GrassusCorble = r("grassus_corble", new Corble(Corble.Settings), Sniwek);
	public static final RegistryObject<Block> BlackGrassCorble = r("black_grass_corble", new Corble(Corble.Settings), Sniwek);

	// Glass
	public static final RegistryObject<Block> RelicGlass = r("relic_glass", new Glass(of(GLASS).strength(1f)), Sniwek);
	public static final RegistryObject<Block> GarawaGlass = r("garawa_glass", new Glass(of(GLASS).strength(1f)), Sniwek);
	public static final RegistryObject<Block> USPGlass = r("usp_glass", new Glass(of(GLASS).strength(1f)), Sniwek);

	// Void
	public static final RegistryObject<Block> VoidBlock = r("void_block", new VoidBlock(of(STONE).strength(25f)), Sniwek);
	public static final RegistryObject<Block> AltVoid = r("alt_void", new AltVoid(of(STONE).strength(25f)), Sniwek);

	// Ancient
	public static final RegistryObject<Block> AncientStoneI = r("ancient_stone_i", new AncientStoneI(of(STONE).strength(7f).harvestLevel(3).harvestTool(PICKAXE).requiresCorrectToolForDrops()), Sniwek);
	public static final RegistryObject<Block> AncientStoneII = r("ancient_stone_ii", new AncientStoneII(of(STONE).strength(10f).harvestLevel(NETHERITE.getLevel()).harvestTool(PICKAXE).requiresCorrectToolForDrops()), Sniwek);
	public static final RegistryObject<Block> AncientStoneIII = r("ancient_stone_iii", new AncientStoneIII(of(STONE).strength(10f).harvestLevel(NETHERITE.getLevel() + 1).harvestTool(PICKAXE).requiresCorrectToolForDrops()), Sniwek);

	// Other
	public static final RegistryObject<Block> CorePillar = r("core_pillar", new Glass(of(STONE).harvestLevel(2).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(.7f)), Sniwek);
	public static final RegistryObject<Block> ScreenshotBlock = r("screenshot_block", new ScreenshotBlock(of(STONE)), Sniwek);
	public static final RegistryObject<Block> PaperBlock = r("paper_block", new PaperBlock(of(STONE)), Sniwek);
	public static final RegistryObject<Block> UncraftingTable = r("uncrafting_table", new UncraftingTable(of(WOOD).strength(2.5f).harvestTool(AXE).sound(SoundType.WOOD)), Rybkek);
	public static final RegistryObject<Reshifter> Reshifter = r("reshifter", new Reshifter(of(STONE).strength(3f).harvestTool(PICKAXE)), Sniwek);
	public static final RegistryObject<SnowEradicator> SnowEradicator = r("snow_eradicator", new SnowEradicator(of(STONE, LAPIS).strength(1.5f)), Rybkek);
	public static final RegistryObject<StoneGenI> StoneGenI = r("stone_gen_i", new StoneGenI(of(STONE).strength(2f).harvestTool(PICKAXE)), Sniwek);
	public static final RegistryObject<RainbowBlock> RainbowBlock = r("rainbow_block", new RainbowBlock(of(METAL).strength(1f, 2f)), Rybkek);

	public static <T extends Block> RegistryObject<T> r(String n, T block, ItemGroup tab) {
		RegistryObject<T> ret = BlocksConstruct.register(n, () -> block);
		ItemRegister.ItemsConstruct.register(n, () -> new BlockItem(block, new Item.Properties().tab(tab)));
		return ret;
	}

	public static void register(IEventBus eventBus) {
		BlocksConstruct.register(eventBus);
	}

	public static ArrayList<RegistryObject<? extends Block>> getTranslucent() {
		ArrayList<RegistryObject<? extends Block>> ret = new ArrayList<>();
		ret.add(RelicGlass);
		ret.add(GarawaGlass);
		ret.add(USPGlass);
		ret.add(CorePillar);
		ret.add(SnowEradicator);
		return ret;
	}
}
