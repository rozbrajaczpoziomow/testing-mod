package mod.rozbrajaczpoziomow.testing.a_registers;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.items.*;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static mod.rozbrajaczpoziomow.testing.a_registers.CreativeTabs.*;
import static mod.rozbrajaczpoziomow.testing.items.ToolTier.CROWBAR;
import static mod.rozbrajaczpoziomow.testing.items.ToolTier.MACHETE;
import static net.minecraft.item.Item.Properties;
import static net.minecraft.item.ItemTier.IRON;
import static net.minecraft.item.ItemTier.NETHERITE;
import static net.minecraft.item.Items.BREAD;
import static net.minecraft.item.Items.IRON_HOE;
import static net.minecraft.item.Rarity.EPIC;
import static net.minecraft.potion.Effects.*;
import static net.minecraftforge.common.ToolType.HOE;

@SuppressWarnings("unused")
public class ItemRegister {
	public static final DeferredRegister<Item> ItemsConstruct = DeferredRegister.create(ForgeRegistries.ITEMS, TestingMod.MOD_ID);

	public static final RegistryObject<Item> Machete = r("machete", new SwordItem(MACHETE, 11, -2.7f, new Properties().tab(Rybkek).food(new Food.Builder().alwaysEat().nutrition(2).fast().build())));
	public static final RegistryObject<Item> CoalIngot = r("coal_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> Core = r("core", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltCore = r("alt_core", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltHoe = r("alt_hoe", new AltHoe(new Properties().tab(Rybkek).durability(IRON_HOE.getDefaultInstance().getMaxDamage()/2)));
	public static final RegistryObject<Item> CoreIngot = r("core_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltCoreIngot = r("alt_core_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> AltBread = r("alt_bread", new Item(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(-Objects.requireNonNull(BREAD.getFoodProperties()).getNutrition()/2).build())));
	public static final RegistryObject<Item> AltPaper = r("alt_paper", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> Crowbar = r("crowbar", new HoeItem(CROWBAR, 6, Float.MAX_VALUE, new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> USPBroken = r("usp_broken", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> USPIngot = r("usp_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> USPGun = r("usp_gun", new USPGun(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> USPAmmo = r("usp_ammo", new Item(new Properties().tab(Sniwek).durability(20)));
	public static final RegistryObject<Item> DemonicCoreIngot = r("demonic_core_ingot", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> Loabin = r("loabin", new Item(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(-4).effect(() -> new EffectInstance(WITHER, 60*20, 0), 1f).effect(() -> new EffectInstance(REGENERATION, 75*20, 1), 1f).effect(() -> new EffectInstance(BLINDNESS, 20*20, 0), 1f).effect(() -> new EffectInstance(CONFUSION, 35*20, 1), 1f).build())));
	public static final RegistryObject<Item> AltBottle = r("alt_bottle", new AltBottle(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().build()).durability(100).setNoRepair()));
	public static final RegistryObject<Item> DemonicBottle = r("demonic_bottle", new DemonicBottle(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().build()).durability(125).setNoRepair()));
	public static final RegistryObject<Item> AltSword = r("alt_sword", new AltSword(new Properties().tab(Sniwek).durability(IRON.getUses()/2)));
	public static final RegistryObject<Item> KasuliIngot = r("kasuli_ingot", new Item(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(-20).effect(() -> new EffectInstance(BLINDNESS, 20*60, 0), 1f).effect(() -> new EffectInstance(REGENERATION, 45*20, 2), 1f).build())));
	public static final RegistryObject<Item> AsuliIngot = r("asuli_ingot", new Item(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(-20).effect(() -> new EffectInstance(CONFUSION, 20*35, 0), 1f).effect(() -> new EffectInstance(BLINDNESS, 35*20, 0), 1f).effect(() -> new EffectInstance(HEAL, 20, 1), 1f).build())));
	public static final RegistryObject<Item> TemponariumIngot = r("temponarium_ingot", new Item(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().effect(() -> new EffectInstance(MOVEMENT_SLOWDOWN, 20*30, 3), 1f).effect(() -> new EffectInstance(BLINDNESS, 20, 0), 1f).effect(() -> new EffectInstance(HARM, 20, 1), 1f).build())));
	public static final RegistryObject<Item> TemmiraopIngot = r("temmiraop_ingot", new Item(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().effect(() -> new EffectInstance(REGENERATION, 20*120, 4), 1f).effect(() -> new EffectInstance(BLINDNESS, 20, 0), 1f).effect(() -> new EffectInstance(MOVEMENT_SPEED, 30*20, 1), 1f).effect(() -> new EffectInstance(JUMP, 30*20, 1), 1f).effect(() -> new EffectInstance(HEALTH_BOOST, 150*20, 1), 1f).build())));
	public static final RegistryObject<Item> TemmiraopChunk = r("temmiraop_chunk", new Item(new Properties().tab(Sniwek).food(new Food.Builder().nutrition(0).alwaysEat().effect(() -> new EffectInstance(REGENERATION, 20*10, 2), 1f).effect(() -> new EffectInstance(BLINDNESS, 20*3, 0), 1f).effect(() -> new EffectInstance(MOVEMENT_SPEED, 20*10, 0), 1f).effect(() -> new EffectInstance(HEALTH_BOOST, 20*120, 0), 1f).build())));
	public static final RegistryObject<Item> KasuliBottle = r("kasuli_bottle", new KasuliBottle(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(10).build()).durability(125).setNoRepair()));
	public static final RegistryObject<Item> TemponariumBottle = r("temponarium_bottle", new TemponariumBottle(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(10).build()).durability(125).setNoRepair()));
	public static final RegistryObject<Item> AsuliBottle = r("asuli_bottle", new AsuliBottle(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(0).build()).durability(125).setNoRepair()));
	public static final RegistryObject<Item> LitaniumBottle = r("litanium_bottle", new LitaniumBottle(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(0).build()).durability(95).setNoRepair()));
	public static final RegistryObject<Item> Vodka = r("vodka", new Vodka(new Properties().tab(Sniwek).food(new Food.Builder().alwaysEat().nutrition(0).build()).durability(2).setNoRepair()));
	public static final RegistryObject<Item> GlitchedSword = r("glitched_sword", new GlitchedSword(new Properties().tab(Sniwek).durability(393)));
	public static final RegistryObject<Item> WitherOnAStick = r("wither_on_a_stick", new WitherOnAStick(new Properties().tab(Rybkek).durability(1500)));
	public static final RegistryObject<Item> VoidCore = r("void_core", new VoidCore(new Properties().tab(Sniwek).durability(20 * 60 * 20)));
	public static final RegistryObject<Item> VoidIngot = r("void_ingot", new VoidIngot(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> CorbiCore = r("corbi_core", new Item(new Properties().tab(Sniwek)));
	public static final RegistryObject<Item> Sniw = r("sniw", new Sniw(new Properties().tab(Rybkek)));
	public static final RegistryObject<Item> Rybek = r("rybek", new Rybek(new Properties().tab(Rybkek).stacksTo(1).rarity(EPIC).addToolType(HOE, NETHERITE.getLevel())));
	public static final RegistryObject<Item> Yeetr = r("yeetr", new Yeetr(new Properties().tab(Rybkek).durability(1).setNoRepair())); // TODO: Fully implement
	public static final RegistryObject<Item> YeetrX = r("yeetr_x", new Yeetr(new Properties().tab(Rybkek).stacksTo(0))); // TODO: Fully implement
	public static final RegistryObject<Item> RecipeBook = r("recipe_book", new RecipeBook(new Properties().tab(Rybkek).stacksTo(1).rarity(EPIC)));

	public static final RegistryObject<Item> Shop = r("shop", new Shop(new Properties().tab(Shitpost).stacksTo(1)));
	public static final RegistryObject<Item> AltShop = r("alt_shop", new AltShop(new Properties().tab(Shitpost).stacksTo(1)));

	public static void register(IEventBus eventBus) {
		ItemsConstruct.register(eventBus);
	}
	private static RegistryObject<Item> r(String n, Item item) {
		return ItemsConstruct.register(n, () -> item);
	}
}
