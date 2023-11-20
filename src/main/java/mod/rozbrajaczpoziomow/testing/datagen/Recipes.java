package mod.rozbrajaczpoziomow.testing.datagen;

import mod.rozbrajaczpoziomow.testing.datagen.struct.Ingredients;
import mod.rozbrajaczpoziomow.testing.datagen.struct.ItemMapping;
import net.minecraft.data.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;
import static mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister.*;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.*;
import static mod.rozbrajaczpoziomow.testing.datagen.struct.Ingredients.ing;
import static mod.rozbrajaczpoziomow.testing.datagen.struct.ItemMapping.map;
import static net.minecraft.item.Items.*;

public class Recipes extends RecipeProvider {
	private Consumer<IFinishedRecipe> consumer;

	public Recipes(DataGenerator gen) {
		super(gen);
	}

	@Override
	public void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		this.consumer = consumer;

		// -- Blocks --

		// Corbi
		makeShapeless("kasuli_block", KasuliBlock.get(), 64, ing().add(KasuliIngot).add(CoalIngot).add(IRON_INGOT));
		makeShapeless("asuli_block", AsuliBlock.get(), 64, ing().add(AsuliIngot).add(CoalIngot).add(IRON_INGOT));
		makeShapeless("temmiraop_block", TemmiraopBlock.get(), 64, ing().add(TemmiraopIngot).add(CoalIngot).add(IRON_INGOT));
		makeShapeless("temponarium_block", TemponariumBlock.get(), 64, ing().add(TemponariumIngot).add(CoalIngot).add(IRON_INGOT));
		makeShapeless("jungle_corbi", JungleCorbi.get(), 5, ing().add(ItemTags.LOGS).add(DIRT).add(WHEAT_SEEDS));
		makeShapeless("white_corbi", WhiteCorbi.get(), 5, ing().add(ItemTags.LOGS).add(DIRT).add(WHEAT_SEEDS, 2));
		makeShapeless("grey_corbi", GreyCorbi.get(), 5, ing().add(ItemTags.LOGS).add(DIRT).add(WHEAT_SEEDS, 3));
		makeShapeless("icy_corbi", IcyCorbi.get(), 32, ing().add(ItemTags.LOGS).add(DIRT).add(ICE));
		makeShapeless("ocean_corbi", OceanCorbi.get(), 32, ing().add(WATER_BUCKET).add(JungleCorbi));
		makeShapeless("leaf_corbi", LeafCorbi.get(), 32, ing().add(ItemTags.LEAVES).add(JungleCorbi));
		makeShapeless("blue_corbi", BlueCorbi.get(), 64, ing().add(JungleCorbi).add(IcyCorbi));
		makeShapeless("error_corbi", ErrorCorbi.get(), 64, ing().add(JungleCorbi).add(AltCoreIngot).add(AltCore));
		makeShapeless("black_corbi", BlackCorbi.get(), 64, ing().add(JungleCorbi).add(INK_SAC));
		makeShaped("other_blue_corbi", OtherBlueCorbi.get(), 64, "---/-|-/---", map().add('-', BlueCorbi).add('|', BLUE_DYE));
		makeShapeless("not_corbi", NotCorbi.get(), ing().add(STONE).add(OtherBlueCorbi));
		makeShapeless("big_corbi", BigCorbi.get(), ing().add(DIAMOND_ORE).add(GOLD_ORE).add(EMERALD_ORE).add(DIRT).add(NETHERITE_INGOT).add(JungleCorbi));

		// Corble
		makeShapeless("stone_corble", StoneCorble.get(), 10, ing().add(STONE).add(COBBLESTONE));
		makeShapeless("mossy_corble", MossyCorble.get(), 10, ing().add(STONE).add(VINE));
		makeShapeless("dead_corble", DeadCorble.get(), 10, ing().add(MossyCorble).add(STONE));
		makeShapeless("reverbed_mossy_corble", ReverbedMossyCorble.get(), 10, ing().add(MossyCorble).add(STONE));
		makeShapeless("reverbed_mossy_dead_corble", ReverbedMossyDeadCorble.get(), 10, ing().add(DeadCorble).add(STONE));
		makeShapeless("reverbed_again_mossy_corble", ReverbedAgainMossyCorble.get(), 10, ing().add(ReverbedMossyCorble).add(STONE));
		makeShapeless("mossy_full_corble", MossyFullCorble.get(), 10, ing().add(MossyCorble).add(STONE));
		makeShapeless("grass_corble", GrassCorble.get(), 10, ing().add(GRASS_BLOCK).add(StoneCorble));
		makeShapeless("grassus_corble", GrassusCorble.get(), 10, ing().add(GRASS_BLOCK).add(STONE).add(WHEAT_SEEDS));
		makeShapeless("black_grass_corble", BlackGrassCorble.get(), 64, ing().add(GRASS_BLOCK).add(WHEAT_SEEDS).add(CoalIngot));

		// Glass
		makeShapeless("relic_glass", RelicGlass.get(), 4, ing().add(GLASS).add(STICK));
		makeShapeless("garawa_glass", GarawaGlass.get(), 24, ing().add(GLASS).add(STONE).add(GRASS_BLOCK));
		makeShapeless("usp_glass", USPGlass.get(), 4, ing().add(USPIngot).add(GLASS));

		// IBA
		makeShapeless("iba_industrial_bricks", IBAIndustrialBricks.get(), 4, ing().add(STONE_BRICKS).add(AltCore));
//		makeShaped("iba_industrial_bricks_glowing", IBAIndustrialBricksGlowing.get(), " $ /$#$/ $ ", map().add('$', GLOWSTONE_DUST).add('#', IBAIndustrialBricks));
		makeShapeless("iba_industrial_bricks_2", IBAIndustrialBricks2.get(), ing().add(IBAIndustrialBricks).add(AltCore));
		makeShapeless("iba_industrial_bricks_3", IBAIndustrialBricks3.get(), ing().add(IBAIndustrialBricks2).add(AltCore));
		makeShapeless("iba_industrial_bricks_4", IBAIndustrialBricks4.get(), ing().add(IBAIndustrialBricks3).add(AltCore));
//		makeShaped("iba_industrial_bricks_4_glowing", IBAIndustrialBricks4Glowing.get(), " $ /$#$/ $ ", map().add('$', GLOWSTONE_DUST).add('#', IBAIndustrialBricks4));
		makeShapeless("iba_industrial_bricks_5", IBAIndustrialBricks5.get(), ing().add(IBAIndustrialBricks4).add(AltCore));
		makeShapeless("iba_industrial_bricks_6", IBAIndustrialBricks6.get(), ing().add(IBAIndustrialBricks5).add(AltCore));
		makeShapeless("iba_industrial_brick_pillar", IBAIndustrialBrickPillar.get(), 4, ing().add(IBAIndustrialBricks).add(CorePillar));
		makeShapeless("iba_industrial_brick_pillar_2", IBAIndustrialBrickPillar2.get(), ing().add(IBAIndustrialBrickPillar).add(AltCore));
		makeShapeless("iba_industrial_leaves", IBAIndustrialLeaves.get(), 4, ing().add(IBAIndustrialBricks).add(ItemTags.LEAVES));
		makeShaped("iba_mansion_leaves", IBAMansionLeaves.get(), 4, " _ /$#$/ $ ", map().add('_', MAGENTA_DYE).add('$', GOLD_INGOT).add('#', ItemTags.LEAVES));
		makeShaped("iba_mansion_fancy_bricks", IBAMansionFancyBricks.get(), 4, " _ /$#$/ $ ", map().add('_', MAGENTA_DYE).add('$', GOLD_INGOT).add('#', ItemTags.STONE_BRICKS));
//		makeShaped("iba_mansion_fancy_bricks_glowing", IBAMansionFancyBricksGlowing.get(), " $ /$#$/ $ ", map().add('$', GLOWSTONE_DUST).add('#', IBAMansionFancyBricks));
		makeShaped("iba_mansion_bricks", IBAMansionBricks.get(), " _ /$#$/ $ ", map().add('_', AltCore).add('$', MAGENTA_DYE).add('#', IBAMansionFancyBricks));
		makeShaped("iba_mansion_bricks_2", IBAMansionBricks2.get(), " _ /$#$/ $ ", map().add('_', AltCore).add('$', MAGENTA_DYE).add('#', IBAMansionBricks));
		makeShaped("iba_mansion_block", IBAMansionBlock.get(), 4, " _ /$#$/ $ ", map().add('_', MAGENTA_DYE).add('$', GOLD_INGOT).add('#', STONE));
		makeShapeless("iba_grasso", IBAGrasso.get(), ing().add(GRASS_BLOCK).add(GrassCorble));
		makeShapeless("iba_garararaso", IBAGarararaso.get(), ing().add(IBAGrasso).add(GrassCorble));
		makeShapeless("iba_toxic_grass", IBAGrasso.get(), ing().add(GRASS_BLOCK).add(POISONOUS_POTATO));

		// Sewers
		makeShaped("sewer_clean_brick_junction", SewerCleanBrickJunction.get(), 64, "#-#/---/#-#", map().add('#', DIORITE).add('-', IRON_NUGGET));
		makeShaped("sewer_clean_brick", SewerCleanBrick.get(), 64, "#|#/#|#/#|#", map().add('#', DIORITE).add('|', IRON_NUGGET));

		// Other Blocks
		makeShapeless("core_pillar", CorePillar.get(), ing().add(STONE).add(Core));
		makeShaped("machete_ore", MacheteOre.get(), "###/#_#/###", map().add('#', Machete).add('_', STONE));
		makeShaped("paper_block", PaperBlock.get(), "###/#$#/###", map().add('#', PAPER).add('$', AltPaper));
		makeShapeless("screenshot_block", ScreenshotBlock.get(), ing().add(PAPER).add(AltPaper));
		makeShaped("uncrafting_table", UncraftingTable.get(), "###/#@#/###", map().add('#', AltCoreIngot).add('@', CRAFTING_TABLE));
		makeShaped("reshifter", Reshifter.get(), " R /RER/ S ", map().add('R', ReshiftedEmerald).add('E', EMERALD_BLOCK).add('S', ShiftedEmerald));
		makeShaped("glowing_stone_bricks", GlowingStoneBricks.get(), " $ /$#$/ $ ", map().add('$', GLOWSTONE_DUST).add('#', STONE_BRICKS));
		makeShaped("glowing_red_nether_bricks", GlowingRedNetherBricks.get(), " $ /$#$/ $ ", map().add('$', GLOWSTONE_DUST).add('#', NETHER_BRICKS));
		makeShapeless("sidewalk_bricks", SidewalkBricks.get(), 4, ing().add(GRAVEL).add(STONE).add(COBBLESTONE).add(ANDESITE));
		makeShapeless("mossy_pavement", MossyPavement.get(), 4, ing().add(COBBLESTONE).add(MOSSY_COBBLESTONE).add(GRASS_BLOCK));
		makeShaped("red_cobblestone_pillar", RedCobblestonePillar.get(), 4, "_|_/ | /_|_", map().add('|', COBBLESTONE).add('_', RED_DYE));
		makeShaped("red_blurry_stone_bricks", RedBlurryStoneBricks.get(), 8, " $ /$#$/ $ ", map().add('$', STONE_BRICKS).add('#', RED_CONCRETE_POWDER));
		makeShaped("red_lamp", RedLamp.get(), 4, " | /-#-/ | ", map().add('#', REDSTONE_LAMP).add('-', RED_DYE).add('|', REDSTONE));
		makeShaped("strange_cobblestone", StrangeCobblestone.get(), 4, " ^ /$#$/ _ ", map().add('#', COBBLESTONE).add('$', ANDESITE).add('_', ANDESITE_SLAB).add('^', LIGHT_GRAY_DYE));
		makeShaped("server_destroyer", ServerDestroyer.get(), 64, "$$$/$#$/$$$", map().add('$', DIRT).add('#', AugustusMode));
		makeShaped("lattice_door", LatticeDoor.get(), "## /## /## ", map().add('#', IRON_BARS));

		// -- Items --

		// Alternate Items
		makeShapeless("alt_core", AltCore.get(), ing().add(Core).add(CoalIngot));
		makeShapeless("alt_bottle", AltBottle.get(), ing().add(GLASS_BOTTLE).add(AltCore));
		makeShapeless("alt_bread", AltBread.get(), ing().add(AltCore).add(BREAD));
		makeShapeless("alt_core_ingot", AltCoreIngot.get(), ing().add(CoreIngot).add(AltCore));
		makeShapeless("alt_hoe", AltHoe.get(), ing().add(IRON_HOE).add(AltCore));
		makeShapeless("alt_sword", AltSword.get(), ing().add(AltCore).add(CoalIngot).add(IRON_SWORD));
		makeShapeless("alt_paper", AltPaper.get(), ing().add(PAPER).add(AltCore));
		makeShapeless("alt_firework", AltFirework.get(), ing().add(FIREWORK_ROCKET).add(AltCore));
		makeShapeless("alt_firework_emerald", AltFireworkEmerald.get(), ing().add(AltFirework).add(EMERALD));
		makeShapeless("alt_firework_reshifted_emerald", AltFireworkReshiftedEmerald.get(), ing().add(AltFirework).add(ReshiftedEmerald));
		makeShapeless("alt_milk", AltMilk.get(), ing().add(MILK_BUCKET).add(AltCore));

		// Bottles / Eatable
		makeShapeless("demonic_bottle", DemonicBottle.get(), ing().add(DemonicCoreIngot).add(Core).add(AltBottle));
		makeShapeless("kasuli_bottle", KasuliBottle.get(), ing().add(AltBottle).add(KasuliIngot).add(CoalIngot));
		makeShapeless("temponarium_bottle", TemponariumBottle.get(), ing().add(TemponariumIngot).add(AltBottle).add(CoalIngot));
		makeShapeless("asuli_bottle", AsuliBottle.get(), ing().add(AltBottle).add(AsuliIngot).add(CoalIngot));
		makeShapeless("litanium_bottle_with_asuli", LitaniumBottle.get(), ing().add(IRON_INGOT).add(DIAMOND).add(AltCore, 2).add(Core, 2).add(CoalIngot).add(TemmiraopIngot).add(AsuliBottle));
		makeShapeless("litanium_bottle_with_kasuli", LitaniumBottle.get(), ing().add(IRON_INGOT).add(DIAMOND).add(AltCore, 2).add(Core, 2).add(CoalIngot).add(TemmiraopIngot).add(KasuliBottle));
		makeShapeless("litanium_bottle_with_temponarium", LitaniumBottle.get(), ing().add(IRON_INGOT).add(DIAMOND).add(AltCore, 2).add(Core, 2).add(CoalIngot).add(TemponariumIngot).add(AsuliBottle));
		makeShapeless("temmiraop_chunk", TemmiraopChunk.get(), 3, ing().add(DIRT, 2).add(WHEAT_SEEDS, 2).add(CoalIngot, 2).add(DIAMOND, 3));
		makeShaped("vodka", Vodka.get(), "   / # / U ", map().add('#', WHEAT).add('U', BOWL));

		// Ingots
		makeShapeless("coal_ingot", CoalIngot.get(), ing().add(IRON_INGOT).add(COAL));
		makeShapeless("core_ingot", CoreIngot.get(), ing().add(Core).add(AltCore));
		makeShapeless("demonic_core_ingot", DemonicCoreIngot.get(), ing().add(USPIngot).add(CoreIngot).add(CoalIngot));
		makeShapeless("temmiraop_ingot", TemmiraopIngot.get(), ing().add(TemponariumIngot).add(KasuliIngot).add(AsuliIngot));
		makeShaped("tla_core", TLACore.get(), "BID/ARC/   ", map().add('B', AltBottle).add('I', CoreIngot).add('D', DemonicBottle).add('A', AltCore).add('R', BrokenIronIngot).add('C', Core));

		// Void
		makeShapeless("void_core", VoidCore.get(), ing().add(AltCore).add(Core).add(VoidIngot));
		makeShapeless("void_ingot", VoidIngot.get(), ing().add(DemonicCoreIngot).add(AltCoreIngot).add(CoreIngot).add(CorbiCore).add(AltCore).add(DIAMOND_SWORD).add(DIAMOND_HOE).add(AltHoe).add(DIAMOND_BLOCK));
		makeShapeless("void_block", VoidBlock.get(), ing().add(AltCoreIngot, 2).add(DemonicCoreIngot).add(AltCore).add(GlitchedSword).add(DIAMOND, 3).add(Core));
		makeShapeless("alt_void", AltVoid.get(), ing().add(VoidIngot).add(VoidCore).add(VoidBlock).add(AltCore).add(DIAMOND_BLOCK).add(IRON_BLOCK).add(GOLD_BLOCK).add(COAL_BLOCK).add(ANCIENT_DEBRIS));

		// USP
		makeShapeless("usp_ammo", USPAmmo.get(), 6, ing().add(USPIngot));
		makeShapeless("usp_gun", USPGun.get(), ing().add(USPBroken).add(USPIngot));

		// Other - Items
		makeShaped("core", Core.get(), "   /___/###", map().add('_', WHEAT_SEEDS).add('#', DIRT));
		makeShaped("crowbar", Crowbar.get(), " ||/ Z / Z ", map().add('Z', IRON_INGOT).add('|', STICK));
		makeShaped("wither_on_a_stick", Crowbar.get(), "_X_/ I / | ", map().add('X', NETHER_STAR).add('_', WITHER_SKELETON_SKULL).add('I', AltCore).add('|', Core));
		makeShapeless("recipe_book", RecipeBook.get(), ing().add(BOOK).add(Core, 3));
		makeShaped("fast_sword", FastSword.get(), "  G/ S /O  ", map().add('G', USPGun).add('O', MacheteOre).add('S', AltSword));
		makeShaped("glue", Glue.get(), "PPP/ U /HHH", map().add('P', PAPER).add('U', BUCKET).add('H', HONEY_BOTTLE));
		makeShapeless("matter_essence", MatterEssence.get(), ing().add(HONEY_BOTTLE, 2).add(EMERALD).add(DIRT).add(SUGAR_CANE, 2).add(CHICKEN).add(STICK).add(FLINT));
		makeShaped("demonic_diamond", DemonicDiamond.get(), "   / D / B ", map().add('D', DIAMOND).add('B', DemonicBottle));
		makeShaped("healing_diamond", HealingDiamond.get(), "G|G/D|D/M|M", map().add('|', TemmiraopChunk).add('D', DIAMOND).add('G', GLOWSTONE_DUST).add('M', GLISTERING_MELON_SLICE));
		makeShaped("solid_stick", SolidStick.get(), "  :/ | /:  ", map().add(':', OBSIDIAN).add('|', AncientDiamondI));
		makeShaped("ancient_pickaxe_i", AncientPickaxeI.get(), " I / I / | ", map().add('I', AncientDiamondI).add('|', SolidStick));
		makeShapeless("aura_diamond", AuraDiamond.get(), ing().add(RadiantedDiamond).add(DemonicDiamond).add(HealingDiamond).add(AncientDiamondIII));
		makeShapeless("augustus_mode", AugustusMode.get(), ing().add(AltCore).add(Augustus, 8));
		makeShapeless("yeetr", Yeetr.get(), ing().add(GOLDEN_SWORD, 9));
		makeShaped("yeetr_x", YeetrX.get(), "#\\P/ # /S^#", map().add('#', Yeetr).add('S', SolidStick).add('P', GOLDEN_PICKAXE).add('^', MatterEssence).add('\\', LitaniumBottle));
		makeShaped("shifted_emerald_sword", ShiftedEmeraldSword.get(), " & / & / | ", map().add('&', ShiftedEmerald).add('|', SolidStick));
		makeShaped("loabin_bottle", LoabinBottle.get(), 4, " ~ / V / ~ ", map().add('~', Loabin).add('V', GLASS_BOTTLE));
		makeShaped("reshifted_diamond_sword", ReshiftedDiamondSword.get(), " & / & /_|_", map().add('&', ShiftedEmerald).add('|', SolidStick).add('_', Core));
		makeShaped("milkbar", Milkbar.get(), "|-|/|@|/|-|", map().add('@', Crowbar).add('-', CoalIngot).add('|', AltMilk));
		makeShapeless("snow_eradicator", SnowEradicator.get(), ing().add(SNOW_BLOCK, 9));
		makeShapeless("stone_generator_i_core", StoneGenICore.get(), ing().add(Core).add(AltCore).add(STONE, 3).add(COBBLESTONE).add(CoreIngot).add(CoalIngot).add(GLASS_BOTTLE));
		makeShaped("hsc_bandage", HSCBandage.get(), "GGG/HHH/PPP", map().add('G', GluedPaper).add('H', HealingSugarCane).add('P', PAPER));
		makeShaped("help_book", HelpBook.get(), "CCC/CBC/CCC", map().add('C', Core).add('B', RecipeBook));
		makeShaped("rainbow_freezer", RainbowFreezer.get(), "|  / #$/ $$", map().add('#', RainbowBlock).add('|', STICK).add('$', ICE));
		makeShaped("music_disc_bad_apple", MusicDiscBadApple.get(), " $ /$#$/ ^ ", map().add('$', ItemTags.MUSIC_DISCS).add('#', TLACore).add('^', JUKEBOX));
		makeShaped("glowdust", Glowdust.get(), 16, "v  / g /  p", map().add('v', SALMON).add('g', GLOWSTONE_DUST).add('p', BAKED_POTATO));

		// Shitpost items
		makeShaped("shop", Shop.get(), " D /PEP/ P ", map().add('D', DIAMOND).add('P', PAPER).add('E', EMERALD));
		makeShaped("alt_shop", AltShop.get(), " P /P#P/ P ", map().add('P', AltPaper).add('#', Shop));
		makeShaped("augustus", Augustus.get(), " # / # / | ", map().add('#', DIRT).add('|', DIAMOND));
		makeShapeless("depressed_fish", DepressedFish.get(), ing().add(TLACore).add(PUFFERFISH));
		makeShapeless("mr_clean", MrClean.get(), ing().add(MILK_BUCKET, 9));

		// Minecraft Items
	}

	private void makeShapeless(String name, IItemProvider result, int amount, Ingredients ingredients) {
		ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(result, amount);

		ingredients.get().forEach(builder::requires);
		builder.unlockedBy("recipe_book", has(RecipeBook.get()));
		builder.save(consumer, new ResourceLocation(MOD_ID, name));
	}

	private void makeShaped(String name, IItemProvider result, int amount, String pattern, ItemMapping mapping) {
		ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(result, amount);
		String[] pat = pattern.split("/");

		builder.pattern(pat[0]).pattern(pat[1]).pattern(pat[2]);
		mapping.get().forEach(builder::define);
		builder.unlockedBy("recipe_book", has(RecipeBook.get()));
		builder.save(consumer, new ResourceLocation(MOD_ID, name));
	}

	private void makeShapeless(String name, IItemProvider result, Ingredients ingredients) {
		makeShapeless(name, result, 1, ingredients);
	}

	private void makeShaped(String name, IItemProvider result, String pattern, ItemMapping mapping) {
		makeShaped(name, result, 1, pattern, mapping);
	}
}
