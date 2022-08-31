package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.items.custom.*;
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

import static mod.rozbrajaczpoziomow.testing.group.Group.Rybkek;
import static mod.rozbrajaczpoziomow.testing.group.Group.Sniwek;
import static mod.rozbrajaczpoziomow.testing.items.custom.ToolTier.CROWBAR;
import static mod.rozbrajaczpoziomow.testing.items.custom.ToolTier.MACHETE;
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

	public static final RegistryObject<Item> Machete = r("machete", new SwordItem(MACHETE, 11, -2.7f, new Properties().group(Rybkek).food(new Food.Builder().setAlwaysEdible().hunger(2).fastToEat().build())));
	public static final RegistryObject<Item> CoalIngot = r("coal_ingot", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> Core = r("core", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> AltCore = r("alt_core", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> AltHoe = r("alt_hoe", new AltHoe(new Properties().group(Rybkek).maxDamage(IRON_HOE.getDefaultInstance().getMaxDamage()/2)));
	public static final RegistryObject<Item> CoreIngot = r("core_ingot", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> AltCoreIngot = r("alt_core_ingot", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> AltBread = r("alt_bread", new Item(new Properties().group(Sniwek).food(new Food.Builder().setAlwaysEdible().hunger(-Objects.requireNonNull(BREAD.getFood()).getHealing()/2).build())));
	public static final RegistryObject<Item> AltPaper = r("alt_paper", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> Crowbar = r("crowbar", new HoeItem(CROWBAR, 6, Float.MAX_VALUE, new Properties().group(Sniwek)));
	public static final RegistryObject<Item> USPBroken = r("usp_broken", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> USPIngot = r("usp_ingot", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> USPGun = r("usp_gun", new USPGun(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> USPAmmo = r("usp_ammo", new Item(new Properties().group(Sniwek).maxDamage(20)));
	public static final RegistryObject<Item> DemonicCoreIngot = r("demonic_core_ingot", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> Loabin = r("loabin", new Item(new Properties().group(Sniwek).food(new Food.Builder().setAlwaysEdible().hunger(-4).effect(() -> new EffectInstance(WITHER, 60*20, 0), 1f).effect(() -> new EffectInstance(REGENERATION, 75*20, 1), 1f).effect(() -> new EffectInstance(BLINDNESS, 20*20, 0), 1f).effect(() -> new EffectInstance(NAUSEA, 35*20, 1), 1f).build())));
	public static final RegistryObject<Item> AltBottle = r("alt_bottle", new AltBottle(new Properties().group(Sniwek).food(new Food.Builder().hunger(0).setAlwaysEdible().build()).maxDamage(100).setNoRepair()));
	public static final RegistryObject<Item> DemonicBottle = r("demonic_bottle", new DemonicBottle(new Properties().group(Sniwek).food(new Food.Builder().hunger(0).setAlwaysEdible().build()).maxDamage(125).setNoRepair()));
	public static final RegistryObject<Item> AltSword = r("alt_sword", new AltSword(new Properties().group(Sniwek).maxDamage(IRON.getMaxUses()/2)));
	public static final RegistryObject<Item> KasuliIngot = r("kasuli_ingot", new Item(new Properties().group(Sniwek).food(new Food.Builder().setAlwaysEdible().hunger(-20).effect(() -> new EffectInstance(BLINDNESS, 20*60, 0), 1f).effect(() -> new EffectInstance(REGENERATION, 45*20, 2), 1f).build())));
	public static final RegistryObject<Item> AsuliIngot = r("asuli_ingot", new Item(new Properties().group(Sniwek).food(new Food.Builder().setAlwaysEdible().hunger(-20).effect(() -> new EffectInstance(NAUSEA, 20*35, 0), 1f).effect(() -> new EffectInstance(BLINDNESS, 35*20, 0), 1f).effect(() -> new EffectInstance(INSTANT_HEALTH, 20, 1), 1f).build())));
	public static final RegistryObject<Item> TemponariumIngot = r("temponarium_ingot", new Item(new Properties().group(Sniwek).food(new Food.Builder().hunger(0).setAlwaysEdible().effect(() -> new EffectInstance(SLOWNESS, 20*30, 3), 1f).effect(() -> new EffectInstance(BLINDNESS, 20, 0), 1f).effect(() -> new EffectInstance(INSTANT_DAMAGE, 20, 1), 1f).build())));
	public static final RegistryObject<Item> TemmiraopIngot = r("temmiraop_ingot", new Item(new Properties().group(Sniwek).food(new Food.Builder().hunger(0).setAlwaysEdible().effect(() -> new EffectInstance(REGENERATION, 20*120, 4), 1f).effect(() -> new EffectInstance(BLINDNESS, 20, 0), 1f).effect(() -> new EffectInstance(SPEED, 30*20, 1), 1f).effect(() -> new EffectInstance(JUMP_BOOST, 30*20, 1), 1f).effect(() -> new EffectInstance(HEALTH_BOOST, 150*20, 1), 1f).build())));
	public static final RegistryObject<Item> TemmiraopChunk = r("temmiraop_chunk", new Item(new Properties().group(Sniwek).food(new Food.Builder().hunger(0).setAlwaysEdible().effect(() -> new EffectInstance(REGENERATION, 20*10, 2), 1f).effect(() -> new EffectInstance(BLINDNESS, 20*3, 0), 1f).effect(() -> new EffectInstance(SPEED, 20*10, 0), 1f).effect(() -> new EffectInstance(HEALTH_BOOST, 20*120, 0), 1f).build())));
	public static final RegistryObject<Item> KasuliBottle = r("kasuli_bottle", new KasuliBottle(new Properties().group(Sniwek).food(new Food.Builder().setAlwaysEdible().hunger(10).build()).maxDamage(125).setNoRepair()));
	public static final RegistryObject<Item> TemponariumBottle = r("temponarium_bottle", new TemponariumBottle(new Properties().group(Sniwek).food(new Food.Builder().setAlwaysEdible().hunger(10).build()).maxDamage(125).setNoRepair()));
	public static final RegistryObject<Item> AsuliBottle = r("asuli_bottle", new AsuliBottle(new Properties().group(Sniwek).food(new Food.Builder().setAlwaysEdible().hunger(0).build()).maxDamage(125).setNoRepair()));
	public static final RegistryObject<Item> LitaniumBottle = r("litanium_bottle", new LitaniumBottle(new Properties().group(Sniwek).food(new Food.Builder().setAlwaysEdible().hunger(0).build()).maxDamage(95).setNoRepair()));
	public static final RegistryObject<Item> Vodka = r("vodka", new Vodka(new Properties().group(Sniwek).food(new Food.Builder().setAlwaysEdible().hunger(0).build()).maxDamage(2).setNoRepair()));
	public static final RegistryObject<Item> GlitchedSword = r("glitched_sword", new GlitchedSword(new Properties().group(Sniwek).maxDamage(393)));
	public static final RegistryObject<Item> WitherOnAStick = r("wither_on_a_stick", new WitherOnAStick(new Properties().group(Rybkek).maxDamage(1500)));
	public static final RegistryObject<Item> VoidCore = r("void_core", new VoidCore(new Properties().group(Sniwek).maxDamage(20 * 60 * 20)));
	public static final RegistryObject<Item> VoidIngot = r("void_ingot", new VoidIngot(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> CorbiCore = r("corbi_core", new Item(new Properties().group(Sniwek)));
	public static final RegistryObject<Item> Sniw = r("sniw", new Sniw(new Properties().group(Rybkek)));
	public static final RegistryObject<Item> Rybek = r("rybek", new Rybek(new Properties().group(Rybkek).maxStackSize(1).rarity(EPIC).addToolType(HOE, NETHERITE.getHarvestLevel())));
	public static final RegistryObject<Item> Yeetr = r("yeetr", new Yeetr(new Properties().group(Rybkek).maxDamage(1).setNoRepair()));
	public static final RegistryObject<Item> YeetrX = r("yeetr_x", new Yeetr(new Properties().group(Rybkek).maxStackSize(0)));


	public static void register(IEventBus eventBus) {
		ItemsConstruct.register(eventBus);
	}
	private static RegistryObject<Item> r(String n, Item item) {
		return ItemsConstruct.register(n, () -> item);
	}
}
