package mod.rozbrajaczpoziomow.testing.a_registers;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static mod.rozbrajaczpoziomow.testing.a_registers.CreativeTabs.TabBlocks;
import static mod.rozbrajaczpoziomow.testing.items.ToolTier.CROWBAR;
import static net.minecraft.block.AbstractBlock.Properties.of;
import static net.minecraft.block.material.Material.*;
import static net.minecraft.block.material.MaterialColor.LAPIS;
import static net.minecraft.item.ItemTier.NETHERITE;
import static net.minecraft.item.Items.DIAMOND_HOE;
import static net.minecraft.item.Items.NETHERITE_HOE;
import static net.minecraft.util.text.TextFormatting.*;
import static net.minecraftforge.common.ToolType.*;

@SuppressWarnings("unused")
public class BlockRegister {
	private static final DeferredRegister<Block> BlocksConstruct = DeferredRegister.create(ForgeRegistries.BLOCKS, TestingMod.MOD_ID);

	// Ores
	public static final RegistryObject<Block> MacheteOre = r("machete_ore", new Block_onlyMinedBy(new Item[] {DIAMOND_HOE, NETHERITE_HOE}, of(STONE).harvestLevel(3).harvestTool(HOE).requiresCorrectToolForDrops().strength(100f)));
	public static final RegistryObject<Block> USPDrop = r("usp_drop", new USPDrop(of(STONE).harvestLevel(CROWBAR.getLevel()).harvestTool(HOE).requiresCorrectToolForDrops().strength(4f)));
	public static final RegistryObject<Block> UnknownBlock = r("unknown_block", new Block(of(STONE).harvestLevel(2).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(4f)));
	public static final RegistryObject<Block> LoabinOre = r("loabin_ore", new Block(of(STONE).harvestLevel(2).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(4f)));
	public static final RegistryObject<Block> IngotOre = r("ingot_ore", new Block(of(STONE).harvestLevel(3).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(6f)));
	public static final RegistryObject<Block> GMDMilkShelf = r("gmd_milk_shelf", new GMDMilkShelf(of(STONE).strength(10f)));
	public static final RegistryObject<Block> MilkShelf = r("milk_shelf", new MilkShelf(of(STONE).strength(10f)));
	public static final RegistryObject<Block> StoneGenICoreOre = r("stone_gen_i_core_ore", new StoneGenICoreOre(of(STONE).strength(4f).harvestLevel(1).harvestTool(PICKAXE).requiresCorrectToolForDrops()));

	// Corbis
	public static final RegistryObject<Block> KasuliBlock = r("kasuli_block", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> AsuliBlock = r("asuli_block", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> TemponariumBlock = r("temponarium_block", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> TemmiraopBlock = r("temmiraop_block", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> JungleCorbi = r("jungle_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> WhiteCorbi = r("white_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> GreyCorbi = r("grey_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> IcyCorbi = r("icy_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> OceanCorbi = r("ocean_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> LeafCorbi = r("leaf_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> BlueCorbi = r("blue_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> ErrorCorbi = r("error_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> BlackCorbi = r("black_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> OtherBlueCorbi = r("other_blue_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> NotCorbi = r("not_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> BigCorbi = r("big_corbi", new Corbi(Corbi.Settings));
	public static final RegistryObject<Block> LookingCorbi = r("looking_corbi", new LookingCorbi(Corbi.Settings));
	public static final RegistryObject<Block> WitherCorbi = r("wither_corbi", new WitherCorbi(of(STONE).strength(25f).harvestLevel(NETHERITE.getLevel()).harvestTool(PICKAXE).requiresCorrectToolForDrops()));

	// Corbles
	public static final RegistryObject<Block> StoneCorble = r("stone_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> MossyCorble = r("mossy_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> DeadCorble = r("dead_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> ReverbedMossyCorble = r("reverbed_mossy_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> ReverbedMossyDeadCorble = r("reverbed_mossy_dead_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> ReverbedAgainMossyCorble = r("reverbed_again_mossy_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> MossyFullCorble = r("mossy_full_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> GrassCorble = r("grass_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> WeirdCorble = r("weird_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> LightCorble = r("light_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> GrassusCorble = r("grassus_corble", new Corble(Corble.Settings));
	public static final RegistryObject<Block> BlackGrassCorble = r("black_grass_corble", new Corble(Corble.Settings));

	// Glass
	public static final RegistryObject<Block> RelicGlass = r("relic_glass", new Glass(of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((a, b, c, d) -> false).isRedstoneConductor((a, b, c) -> false).isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false)));
	public static final RegistryObject<Block> GarawaGlass = r("garawa_glass", new Glass(of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((a, b, c, d) -> false).isRedstoneConductor((a, b, c) -> false).isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false)));
	public static final RegistryObject<Block> USPGlass = r("usp_glass", new Glass(of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion().isValidSpawn((a, b, c, d) -> false).isRedstoneConductor((a, b, c) -> false).isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false)));

	// Void
	public static final RegistryObject<Block> VoidBlock = r("void_block", new VoidBlock(of(STONE).strength(25f)));
	public static final RegistryObject<Block> AltVoid = r("alt_void", new AltVoid(of(STONE).strength(25f)));

	// Ancient
	public static final RegistryObject<Block> AncientStoneI = r("ancient_stone_i", new AncientStoneI(of(STONE).strength(7f).harvestLevel(3).harvestTool(PICKAXE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AncientStoneII = r("ancient_stone_ii", new AncientStoneII(of(STONE).strength(10f).harvestLevel(NETHERITE.getLevel()).harvestTool(PICKAXE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AncientStoneIII = r("ancient_stone_iii", new AncientStoneIII(of(STONE).strength(10f).harvestLevel(NETHERITE.getLevel() + 1).harvestTool(PICKAXE).requiresCorrectToolForDrops()));

	// IBA
	public static final RegistryObject<Block> IBAIndustrialBricks = r("iba_industrial_bricks", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<Block> IBAIndustrialBricksGlowing = r("iba_industrial_bricks_glowing", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f).lightLevel(state -> 15)));
	public static final RegistryObject<Block> IBAIndustrialBricks2 = r("iba_industrial_bricks_2", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<Block> IBAIndustrialBricks3 = r("iba_industrial_bricks_3", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<Block> IBAIndustrialBricks4 = r("iba_industrial_bricks_4", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<Block> IBAIndustrialBricks4Glowing = r("iba_industrial_bricks_4_glowing", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f).lightLevel(state -> 15)));
	public static final RegistryObject<Block> IBAIndustrialBricks5 = r("iba_industrial_bricks_5", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<Block> IBAIndustrialBricks6 = r("iba_industrial_bricks_6", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<Block> IBAIndustrialBricks7 = r("iba_industrial_bricks_7", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<HorizontallyRotatableBlock> IBAIndustrialBrickPillar = r("iba_industrial_brick_pillar", new HorizontallyRotatableBlock(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<HorizontallyRotatableBlock> IBAIndustrialBrickPillar2 = r("iba_industrial_brick_pillar_2", new HorizontallyRotatableBlock(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<Block> IBAIndustrialLeaves = r("iba_industrial_leaves", new Block(of(LEAVES).strength(0.2f).sound(SoundType.GRASS).noOcclusion().isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false)));
	public static final RegistryObject<BlockWithDescription> IBAMansionLeaves = r("iba_mansion_leaves", new BlockWithDescription(of(LEAVES).strength(0.2f).sound(SoundType.GRASS).noOcclusion().isSuffocating((a, b, c) -> false).isViewBlocking((a, b, c) -> false), withColor("Leaves, but if you were richer.", LIGHT_PURPLE)));
	public static final RegistryObject<IBAMansionFancyBricks> IBAMansionFancyBricks = r("iba_mansion_fancy_bricks", new IBAMansionFancyBricks(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<IBAMansionFancyBricks> IBAMansionFancyBricksGlowing = r("iba_mansion_fancy_bricks_glowing", new IBAMansionFancyBricks(of(STONE).requiresCorrectToolForDrops().strength(1.5f).lightLevel(state -> 15)));
	public static final RegistryObject<BlockWithDescription> IBAMansionBricks = r("iba_mansion_bricks", new BlockWithDescription(of(STONE).requiresCorrectToolForDrops().strength(1.5f), withColor("gEtM aNsIOnEd", LIGHT_PURPLE)));
	public static final RegistryObject<BlockWithDescription> IBAMansionBricks2 = r("iba_mansion_bricks_2", new BlockWithDescription(of(STONE).requiresCorrectToolForDrops().strength(1.5f), withColor("gEtM aNsIOnEd", LIGHT_PURPLE)));
	public static final RegistryObject<BlockWithDescription> IBAMansionBricks2Glowing = r("iba_mansion_bricks_2_glowing", new BlockWithDescription(of(STONE).requiresCorrectToolForDrops().strength(1.5f).lightLevel(state -> 15), withColor("gEtM aNsIOnEd", LIGHT_PURPLE)));
	public static final RegistryObject<BlockWithDescription> IBAMansionBlock = r("iba_mansion_block", new BlockWithDescription(of(STONE).requiresCorrectToolForDrops().strength(1.5f), withColor("Stone, but fancier™", LIGHT_PURPLE)));
	public static final RegistryObject<BlockWithDescription> IBAGrasso = r("iba_grasso", new BlockWithDescription(of(GRASS).strength(0.6f).sound(SoundType.GRASS), withColor("GRASP", GREEN)));
	public static final RegistryObject<BlockWithDescription> IBAGarararaso = r("iba_garararaso", new BlockWithDescription(of(GRASS).strength(0.6f).sound(SoundType.GRASS), withColor("GARARARASP", GREEN)));
	public static final RegistryObject<BlockWithDescription> IBAToxicGrass = r("iba_toxic_grass", new BlockWithDescription(of(GRASS).strength(0.6f).sound(SoundType.GRASS), withColor("Never been toxic before? Oh, really?", GREEN)));

	// Sewers
	public static final RegistryObject<HorizontallyRotatableBlockWithDescription> SewerCleanBrickJunction = r("sewer_clean_brick_junction", new HorizontallyRotatableBlockWithDescription(of(STONE).requiresCorrectToolForDrops().strength(1.5f).lightLevel(state -> 4), text("Have you ever been in sewers? Probably not, well now you have a chance to build your own, but this time clean...why...")));
	public static final RegistryObject<HorizontallyRotatableBlockWithDescription> SewerCleanBrick = r("sewer_clean_brick", new HorizontallyRotatableBlockWithDescription(of(STONE).requiresCorrectToolForDrops().strength(1.5f).lightLevel(state -> 2), text("Mr. Clean enough...why...")));

	// Other
	public static final RegistryObject<Block> CorePillar = r("core_pillar", new Glass(of(STONE).harvestLevel(2).harvestTool(PICKAXE).requiresCorrectToolForDrops().strength(.7f)));
	public static final RegistryObject<Block> ScreenshotBlock = r("screenshot_block", new ScreenshotBlock(of(STONE)));
	public static final RegistryObject<Block> PaperBlock = r("paper_block", new PaperBlock(of(STONE)));
	public static final RegistryObject<Block> UncraftingTable = r("uncrafting_table", new UncraftingTable(of(WOOD).strength(2.5f).harvestTool(AXE).sound(SoundType.WOOD)));
	public static final RegistryObject<Reshifter> Reshifter = r("reshifter", new Reshifter(of(STONE).strength(3f).harvestTool(PICKAXE)));
	public static final RegistryObject<SnowEradicator> SnowEradicator = r("snow_eradicator", new SnowEradicator(of(STONE, LAPIS).strength(1.5f)));
	public static final RegistryObject<StoneGenI> StoneGenI = r("stone_gen_i", new StoneGenI(of(STONE).strength(2f).harvestTool(PICKAXE)));
	public static final RegistryObject<RainbowBlock> RainbowBlock = r("rainbow_block", new RainbowBlock(of(METAL).strength(1f, 2f)));
	public static final RegistryObject<Block> GlowingStoneBricks = r("glowing_stone_bricks", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f, 6f).lightLevel(state -> 15)));
	public static final RegistryObject<Block> GlowingRedNetherBricks = r("glowing_red_nether_bricks", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f, 6f).lightLevel(state -> 15)));
	public static final RegistryObject<Block> SidewalkBricks = r("sidewalk_bricks", new Block(of(STONE).requiresCorrectToolForDrops().strength(1.5f)));
	public static final RegistryObject<BlockWithDescription> MossyPavement = r("mossy_pavement", new BlockWithDescription(of(STONE).requiresCorrectToolForDrops().strength(1.5f), withColor("Description? Nah man what the hell, do you think we are in Ohio?", GREEN)));
	public static final RegistryObject<HorizontallyRotatableBlockWithDescription> RedCobblestonePillar = r("red_cobblestone_pillar", new HorizontallyRotatableBlockWithDescription(of(STONE).requiresCorrectToolForDrops().harvestTool(PICKAXE).strength(2f, 6f), withColor("po co ci opis?", RED)));
	public static final RegistryObject<BlockWithDescription> RedBlurryStoneBricks = r("red_blurry_stone_bricks", new BlockWithDescription(of(STONE).requiresCorrectToolForDrops().harvestTool(PICKAXE).strength(2f, 6f), text("Tu wstaw 0 znaków").withStyle(RED, ITALIC)));
	public static final RegistryObject<BlockWithDescription> RedLamp = r("red_lamp", new BlockWithDescription(of(BUILDABLE_GLASS).lightLevel(state -> 15).strength(.3f).sound(SoundType.GLASS), withColor("It could glow red but... Thanks to mojang, it will not.", RED)));
	public static final RegistryObject<RadiantZero> RadiantZero = r("radiant_zero", new RadiantZero(of(STONE).requiresCorrectToolForDrops().harvestTool(PICKAXE).strength(2f, 6f)));
	public static final RegistryObject<StrangeCobblestone> StrangeCobblestone = r("strange_cobblestone", new StrangeCobblestone(of(STONE).requiresCorrectToolForDrops().harvestTool(PICKAXE).strength(2f, 6f)));
	public static final RegistryObject<ServerDestroyer> ServerDestroyer = r("server_destroyer", new ServerDestroyer(of(GLASS).strength(0f, 0f)));
	public static final RegistryObject<FadedDoor> FadedDoor = r("faded_door", new FadedDoor(of(WOOD).strength(3f).noOcclusion()));
	public static final RegistryObject<LifeLimiter> LifeLimiter = r("life_limiter", new LifeLimiter(of(WOOD).strength(3f).harvestTool(AXE).sound(SoundType.WOOD)));
	public static final RegistryObject<LatticeDoor> LatticeDoor = r("lattice_door", new LatticeDoor(of(METAL).strength(3f).noOcclusion()));


	public static <T extends Block> RegistryObject<T> r(String n, T block) {
		RegistryObject<T> ret = BlocksConstruct.register(n, () -> block);
		ItemRegister.ItemsConstruct.register(n, () -> new BlockItem(block, new Item.Properties().tab(TabBlocks)));
		return ret;
	}

	public static void register(IEventBus eventBus) {
		BlocksConstruct.register(eventBus);
	}

	public static RegistryObject<? extends Block>[] getTranslucent() {
		return new RegistryObject[] { RelicGlass, GarawaGlass, USPGlass, CorePillar, SnowEradicator, IBAIndustrialLeaves, IBAMansionLeaves, FadedDoor, LatticeDoor };
	}
}
