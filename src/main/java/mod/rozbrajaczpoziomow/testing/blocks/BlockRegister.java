package mod.rozbrajaczpoziomow.testing.blocks;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.blocks.custom.*;
import mod.rozbrajaczpoziomow.testing.items.ItemRegister;
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

import static mod.rozbrajaczpoziomow.testing.items.custom.ToolTier.CROWBAR;
import static mod.rozbrajaczpoziomow.testing.tab.Tab.Rybkek;
import static mod.rozbrajaczpoziomow.testing.tab.Tab.Sniwek;
import static net.minecraft.block.AbstractBlock.Properties.of;
import static net.minecraft.block.material.Material.*;
import static net.minecraft.item.ItemTier.NETHERITE;
import static net.minecraft.item.Items.DIAMOND_HOE;
import static net.minecraft.item.Items.NETHERITE_HOE;
import static net.minecraftforge.common.ToolType.*;

@SuppressWarnings("unused")
public class BlockRegister {
	private static final DeferredRegister<Block> BlocksConstruct = DeferredRegister.create(ForgeRegistries.BLOCKS, TestingMod.MOD_ID);

	// Other
	public static final RegistryObject<Block> CorePillar = r("core_pillar", new Block(of(STONE).harvestLevel(2).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(.7f).noOcclusion()), Sniwek);
	public static final RegistryObject<Block> ScreenshotBlock = r("screenshot_block", new ScreenshotBlock(of(STONE)), Sniwek);
	public static final RegistryObject<Block> PaperBlock = r("paper_block", new PaperBlock(of(STONE)), Sniwek);
	public static final RegistryObject<Block> UncraftingTable = r("uncrafting_table", new UncraftingTable(of(WOOD).strength(2.5f).harvestTool(AXE).sound(SoundType.WOOD)), Rybkek);

	// Ores
	public static final RegistryObject<Block> MacheteOre = r("machete_ore", new Block_onlyMinedBy(new Item[] {DIAMOND_HOE, NETHERITE_HOE}, of(STONE).harvestLevel(3).harvestTool(HOE).requiresCorrectToolForDrops().strength(100f)), Rybkek);
	public static final RegistryObject<Block> USPDrop = r("usp_drop", new USPDrop(of(STONE).harvestLevel(CROWBAR.getLevel()).harvestTool(HOE).requiresCorrectToolForDrops().strength(4f)), Sniwek);
	public static final RegistryObject<Block> UnknownBlock = r("unknown_block", new Block(of(STONE).harvestLevel(2).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(4f)), Sniwek);
	public static final RegistryObject<Block> LoabinOre = r("loabin_ore", new Block(of(STONE).harvestLevel(2).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(4f)), Sniwek);
	public static final RegistryObject<Block> IngotOre = r("ingot_ore", new Block(of(STONE).harvestLevel(3).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(6f)), Sniwek);

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

	public static <T extends Block>RegistryObject<T> r(String n, T block, ItemGroup tab) {
		RegistryObject<T> ret = BlocksConstruct.register(n, () -> block);
		ItemRegister.ItemsConstruct.register(n, () -> new BlockItem(block, new Item.Properties().tab(tab)));
		return ret;
	}

	public static void register(IEventBus eventBus) {
		BlocksConstruct.register(eventBus);
	}

	public static ArrayList<RegistryObject<Block>> getTranslucent() {
		ArrayList<RegistryObject<Block>> ret = new ArrayList<>();
		ret.add(RelicGlass);
		ret.add(GarawaGlass);
		ret.add(USPGlass);
		ret.add(CorePillar);
		return ret;
	}
}
