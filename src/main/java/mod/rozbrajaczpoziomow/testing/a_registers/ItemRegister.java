package mod.rozbrajaczpoziomow.testing.a_registers;

import com.google.common.base.Suppliers;
import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.items.*;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.CreativeTabs.*;
import static mod.rozbrajaczpoziomow.testing.items.ToolTier.*;
import static net.minecraft.item.Item.Properties;
import static net.minecraft.item.ItemTier.IRON;
import static net.minecraft.item.ItemTier.NETHERITE;
import static net.minecraft.item.Items.BREAD;
import static net.minecraft.item.Items.IRON_HOE;
import static net.minecraft.item.Rarity.EPIC;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.*;
import static net.minecraftforge.common.ToolType.HOE;

@SuppressWarnings("unused")
public class ItemRegister {
	public static final DeferredRegister<Item> ItemsConstruct = DeferredRegister.create(ForgeRegistries.ITEMS, TestingMod.MOD_ID);

	public static final RegistryObject<Item> Machete = r("machete", new SwordItem(MACHETE, 11, -2.7f, new Properties().tab(Rybkek).food(new Food.Builder().alwaysEat().nutrition(2).fast().build())));
	public static final RegistryObject<Item> CoalIngot = r("coal_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> Core = r("core", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltCore = r("alt_core", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltHoe = r("alt_hoe", new AltHoe(new Properties().tab(Rybkek).durability(IRON_HOE.getDefaultInstance().getMaxDamage() / 2)));
	public static final RegistryObject<Item> CoreIngot = r("core_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltCoreIngot = r("alt_core_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltBread = r("alt_bread", new Item(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(-Objects.requireNonNull(BREAD.getFoodProperties()).getNutrition() / 2).build())));
	public static final RegistryObject<Item> AltPaper = r("alt_paper", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> Crowbar = r("crowbar", new HoeItem(CROWBAR, 6, Float.MAX_VALUE, new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> USPBroken = r("usp_broken", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> USPIngot = r("usp_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> USPGun = r("usp_gun", new USPGun(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> USPAmmo = r("usp_ammo", new Item(new Properties().tab(Sniwek).durability(20)));
	public static final RegistryObject<Item> DemonicCoreIngot = r("demonic_core_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> Loabin = r("loabin", new Item(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(-4).effect(() -> new EffectInstance(WITHER, 60 * 20, 0), 1f).effect(() -> new EffectInstance(REGENERATION, 75 * 20, 1), 1f).effect(() -> new EffectInstance(BLINDNESS, 20 * 20, 0), 1f).effect(() -> new EffectInstance(CONFUSION, 35 * 20, 1), 1f).build())));
	public static final RegistryObject<Item> AltBottle = r("alt_bottle", new AltBottle(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().build()).durability(100).setNoRepair()));
	public static final RegistryObject<Item> DemonicBottle = r("demonic_bottle", new DemonicBottle(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().build()).durability(125).setNoRepair()));
	public static final RegistryObject<Item> AltSword = r("alt_sword", new AltSword(new Properties().tab(Sniwek).durability(IRON.getUses() / 2)));
	public static final RegistryObject<Item> KasuliIngot = r("kasuli_ingot", new Item(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(-20).effect(() -> new EffectInstance(BLINDNESS, 20 * 60, 0), 1f).effect(() -> new EffectInstance(REGENERATION, 45 * 20, 2), 1f).build())));
	public static final RegistryObject<Item> AsuliIngot = r("asuli_ingot", new Item(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(-20).effect(() -> new EffectInstance(CONFUSION, 20 * 35, 0), 1f).effect(() -> new EffectInstance(BLINDNESS, 35 * 20, 0), 1f).effect(() -> new EffectInstance(HEAL, 20, 1), 1f).build())));
	public static final RegistryObject<Item> TemponariumIngot = r("temponarium_ingot", new Item(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().effect(() -> new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 30, 3), 1f).effect(() -> new EffectInstance(BLINDNESS, 20, 0), 1f).effect(() -> new EffectInstance(HARM, 20, 1), 1f).build())));
	public static final RegistryObject<Item> TemmiraopIngot = r("temmiraop_ingot", new Item(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().effect(() -> new EffectInstance(REGENERATION, 20 * 120, 4), 1f).effect(() -> new EffectInstance(BLINDNESS, 20, 0), 1f).effect(() -> new EffectInstance(MOVEMENT_SPEED, 30 * 20, 1), 1f).effect(() -> new EffectInstance(JUMP, 30 * 20, 1), 1f).effect(() -> new EffectInstance(HEALTH_BOOST, 150 * 20, 1), 1f).build())));
	public static final RegistryObject<Item> TemmiraopChunk = r("temmiraop_chunk", new Item(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().effect(() -> new EffectInstance(REGENERATION, 20 * 10, 2), 1f).effect(() -> new EffectInstance(BLINDNESS, 20 * 3, 0), 1f).effect(() -> new EffectInstance(MOVEMENT_SPEED, 20 * 10, 0), 1f).effect(() -> new EffectInstance(HEALTH_BOOST, 20 * 120, 0), 1f).build())));
	public static final RegistryObject<Item> KasuliBottle = r("kasuli_bottle", new KasuliBottle(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(10).build()).durability(125).setNoRepair()));
	public static final RegistryObject<Item> TemponariumBottle = r("temponarium_bottle", new TemponariumBottle(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(10).build()).durability(125).setNoRepair()));
	public static final RegistryObject<Item> AsuliBottle = r("asuli_bottle", new AsuliBottle(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(0).build()).durability(125).setNoRepair()));
	public static final RegistryObject<Item> LitaniumBottle = r("litanium_bottle", new LitaniumBottle(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(0).build()).durability(95).setNoRepair()));
	public static final RegistryObject<Item> Vodka = r("vodka", new Vodka(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(0).build()).durability(2).setNoRepair()));
	public static final RegistryObject<Item> GlitchedSword = r("glitched_sword", new GlitchedSword(new Properties().tab(Sniwek).durability(393)));
	public static final RegistryObject<Item> WitherOnAStick = r("wither_on_a_stick", new WitherOnAStick(new Properties().tab(Sniwek).durability(1500)));
	public static final RegistryObject<Item> VoidCore = r("void_core", new VoidCore(new Properties().tab(Sniwek).durability(20 * 60 * 20)));
	public static final RegistryObject<Item> VoidIngot = r("void_ingot", new VoidIngot(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> CorbiCore = r("corbi_core", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> Sniw = r("sniw", new Sniw(new Properties().tab(Rybkek)));
	public static final RegistryObject<Item> Rybek = r("rybek", new Rybek(new Properties().tab(Rybkek).stacksTo(1).rarity(EPIC).addToolType(HOE, NETHERITE.getLevel())));
	public static final RegistryObject<Item> Yeetr = r("yeetr", new Yeetr(new Properties().tab(Rybkek).durability(3).setNoRepair()));
	public static final RegistryObject<Item> YeetrX = r("yeetr_x", new Yeetr(new Properties().tab(Rybkek).stacksTo(1)));
	public static final RegistryObject<Item> RecipeBook = r("recipe_book", new RecipeBook(new Properties().tab(Rybkek).stacksTo(1).rarity(EPIC)));
	public static final RegistryObject<Item> ReshiftedEmerald = r("reshifted_emerald", new ItemWithDescription(new Properties().tab(Sniwek).fireResistant(), text("This is probably void matter, maybe because it's hard to study... But it could be an emerald from another dimension... We will never know.", LIGHT_PURPLE)));
	public static final RegistryObject<Item> ShiftedEmerald = r("shifted_emerald", new ItemWithDescription(new Properties().tab(Sniwek).fireResistant(), text("It's matter that's not natural in this world. It can't be any void matter; it's unstable; but it can be useful...", GRAY)));
	public static final RegistryObject<Item> AltFirework = r("alt_firework", new AltFirework(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltFireworkEmerald = r("alt_firework_emerald", new AltFireworkEmerald(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltFireworkReshiftedEmerald = r("alt_firework_reshifted_emerald", new AltFireworkReshiftedEmerald(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> FastSword = r("fast_sword", new FastSword(new Properties().tab(Rybkek).durability(150)));
	public static final RegistryObject<Item> Glue = r("glue", new Glue(new Properties().tab(Sniwek).durability(9)));
	public static final RegistryObject<Item> GluedPaper = r("glued_paper", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> ReshiftedDiamond = r("reshifted_diamond", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> MatterEssence = r("matter_essence", new MatterEssence(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().build())));
	public static final RegistryObject<Item> RadiantedDiamond = r("radianted_diamond", new RadiantedDiamond(new Properties().tab(Sniwek).stacksTo(1)));
	public static final RegistryObject<Item> DemonicDiamond = r("demonic_diamond", new DemonicDiamond(new Properties().tab(Sniwek).stacksTo(1)));
	public static final RegistryObject<Item> HealingDiamond = r("healing_diamond", new HealingDiamond(new Properties().tab(Sniwek).stacksTo(1)));
	public static final RegistryObject<Item> AncientDiamondI = r("ancient_diamond_i", new ItemWithDescription(new Properties().tab(Sniwek).stacksTo(32), text("This diamond is so old that it's called ancient, but it can be really useful...", DARK_GREEN)));
	public static final RegistryObject<Item> AncientDiamondII = r("ancient_diamond_ii", new AncientDiamondII(new Properties().tab(Sniwek).stacksTo(16)));
	public static final RegistryObject<Item> SolidStick = r("solid_stick", new SolidStick(new Properties().tab(Sniwek).stacksTo(16)));
	public static final RegistryObject<Item> AncientPickaxeI = r("ancient_pickaxe_i", new AncientPickaxeI(new Properties().tab(Sniwek).durability(4557)));
	public static final RegistryObject<Item> AltMilk = r("alt_milk", new AltMilk(new Properties().tab(Rybkek).stacksTo(1).food(new Food.Builder().alwaysEat().build())));
	public static final RegistryObject<Item> AncientDiamondIII = r("ancient_diamond_iii", new AncientDiamondIII(new Properties().tab(Sniwek).stacksTo(1)));
	public static final RegistryObject<Item> AuraDiamond = r("aura_diamond", new AuraDiamond(new Properties().tab(Sniwek).durability(4500)));
	public static final RegistryObject<Item> AugustusMode = r("augustus_mode", new AugustusMode(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> ShiftedEmeraldSword = r("shifted_emerald_sword", new ShiftedEmeraldSword(new Properties().tab(Sniwek).durability(4000)));
	public static final RegistryObject<Item> LoabinBottle = r("loabin_bottle", new LoabinBottle(new Properties().tab(Rybkek).rarity(EPIC).food(new Food.Builder().nutrition(0).alwaysEat().build())));
	public static final RegistryObject<Item> ReshiftedDiamondSword = r("reshifted_diamond_sword", new ReshiftedDiamondSword(new Properties().tab(Sniwek).durability(3500)));
	public static final RegistryObject<Item> RedMilk = r("red_milk", new RedMilk(new Properties().tab(Sniwek).stacksTo(1).food(new Food.Builder().alwaysEat().build())));
	public static final RegistryObject<Item> GMDMilk = r("gmd_milk", new GMDMilk(new Properties().tab(Sniwek).stacksTo(1).food(new Food.Builder().alwaysEat().build())));
	public static final RegistryObject<Item> GMDNaturalMilk = r("gmd_natural_milk", new GMDNaturalMilk(new Properties().tab(Sniwek).stacksTo(1).food(new Food.Builder().alwaysEat().build())));
	public static final RegistryObject<Item> GMDBrutalMilk = r("gmd_brutal_milk", new GMDBrutalMilk(new Properties().tab(Sniwek).stacksTo(1).food(new Food.Builder().alwaysEat().build())));
	public static final RegistryObject<Item> Milkbar = r("milkbar", new HoeItem(MILKBAR, 6, Float.MAX_VALUE / 67, new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> StoneGenICore = r("stone_gen_i_core", new StoneGenICore(new Properties().tab(Sniwek).durability(200 * 20)));
	public static final RegistryObject<Item> BrokenIronIngot = r("broken_iron_ingot", new ItemWithDescription(new Properties().tab(Sniwek), text("It's just a broken Iron Ingot, if you ask about its name... Uh let's just sasy that it has broken in the same time as this ingot ;-;")));
	public static final RegistryObject<Item> TLACore = r("tla_core", new ItemWithDescription(new Properties().tab(Sniwek), text("This is something like Alt Core but it doesn't change the effects of an item, it changes the form of an item. DON'T TRY TO USE IT ON FIRESNIW PASTY!", BLUE)));
	public static final RegistryObject<Item> HealingSugarCane = r("healing_sugar_cane", new HealingSugarCane(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().build())));
	public static final RegistryObject<Item> HSCBandage = r("hsc_bandage", new HSCBandage(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().build())));
	public static final RegistryObject<Item> HelpBook = r("help_book", new HelpBook(new Properties().tab(Rybkek).stacksTo(1)));
	public static final RegistryObject<Item> RainbowFreezer = r("rainbow_freezer", new RainbowFreezer(new Properties().tab(Rybkek).stacksTo(1).durability(8)));
	public static final RegistryObject<Item> MusicDiscBadApple = r("music_disc_badapple", new MusicDiscItem(14, Suppliers.memoize(SoundRegister.bad_apple::get), new Properties().stacksTo(1).rarity(Rarity.RARE).tab(Rybkek)));
	public static final RegistryObject<Item> Glowdust = r("glowdust", new Glowdust(new Properties().tab(Rybkek)));

	public static final RegistryObject<Item> Shop = r("shop", new Shop(new Properties().tab(Shitpost).stacksTo(1)));
	public static final RegistryObject<Item> AltShop = r("alt_shop", new AltShop(new Properties().tab(Shitpost).stacksTo(1)));
	public static final RegistryObject<Item> Augustus = r("augustus", new Augustus(new Properties().tab(Shitpost).stacksTo(1)));
	public static final RegistryObject<Item> DepressedFish = r("depressed_fish", new ItemWithDescription(new Properties().tab(Shitpost).stacksTo(1), text("Draw!", GOLD), text("~ Brawlhalla - 2023", GRAY)));
	public static final RegistryObject<Item> HelpFish = r("help_fish", new ItemWithDescription(new Properties().tab(Shitpost).stacksTo(1), text("When you try but you don't succeed...", BLUE), text("~ ||rozbrajaczpoziomow|| - 2023", GRAY)));
	public static final RegistryObject<Item> MrClean = r("mr_clean", new ItemWithDescription(new Properties().tab(Shitpost).stacksTo(1), text("Mr. Clean is a brand name and mascot, owned by the American company Procter & Gamble, used for an all-purpose cleaner and later also for a melamine foam abrasive sponge."), text("The all-purpose cleaner was originally formulated by Linwood Burton, a marine ship cleaning businessman with accounts throughout the east coast of the United States and his friend, Mathusan Chandramohan,[1] a rich entrepreneur from Sri Lanka.[2]"), text("Mr. Clean made his television commercial debut in 1958, initially portrayed in the live-action versions by character actor House Peters Jr.[3]"), text(""), text("- International versions"), text("Don Limpio, in Spain; originally Mr. Proper"), text("Maestro Limpio, in Mexico"), text("Mastro Lindo, in Italy"), text("Meister Proper, in Germany"), text("Mr. Proper, in Eastern Europe, including Bulgaria,[5] Kazakhstan,[6] Russia,[7] and Ukraine.[8]"), text("M. Net, in French Canada"), text("Monsieur Propre, in France"), text(""), text("In the UK and Ireland, the product is sold under the brand name Flash;[9] this is because a company exists that uses the \"Mr. Clean\" name.[10] Furthermore, Flash does not use a mascot, unlike Mr. Clean.[11] For many years Flash was advertised on UK television by Scottish actress Molly Weir, with the catchphrase \"Flash cleans floors WITHOUT scratching\". Since 2016, adverts for Flash have included parodies of the song Flash by Queen.[12][13]")));

	public static void register(IEventBus eventBus) {
		ItemsConstruct.register(eventBus);
	}

	private static RegistryObject<Item> r(String n, Item item) {
		return ItemsConstruct.register(n, () -> item);
	}
}
