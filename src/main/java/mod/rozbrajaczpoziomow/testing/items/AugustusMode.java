package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

import java.util.*;

import static java.lang.Long.parseLong;
import static mod.rozbrajaczpoziomow.testing.TestingMod.LOGGER;
import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;
import static mod.rozbrajaczpoziomow.testing.Utils.*;
import static mod.rozbrajaczpoziomow.testing.a_registers.DamageSources.NIGHTMARES;
import static mod.rozbrajaczpoziomow.testing.a_registers.DamageSources.TIMER;
import static mod.rozbrajaczpoziomow.testing.blocks.UncraftingTable.IUncraftingDisallowed;
import static net.minecraft.item.Items.AIR;
import static net.minecraft.item.Items.COAL;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.ActionResult.success;
import static net.minecraft.util.text.TextFormatting.*;

@IUncraftingDisallowed
@Mod.EventBusSubscriber(modid = MOD_ID)
public class AugustusMode extends Item {
	public static boolean enabled = false;

	public AugustusMode(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if(world.isClientSide || hand != Hand.MAIN_HAND) return super.use(world, player, hand);

		if(enabled) {
			Storage.print(player.getStringUUID());
			return success(player.getMainHandItem());
		}

		enabled = true;

		ItemStack item = getDefaultInstance();
		item.enchant(Enchantments.BINDING_CURSE, 1);

		for(ServerPlayerEntity everyPlayer : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
			sendMessage(everyPlayer, withColor("Augustus mode has been enabled. Have fun!", RED));
			everyPlayer.addItem(item);
		}

		player.setItemInHand(hand, AIR.getDefaultInstance());
		return success(AIR.getDefaultInstance());
	}

	@SubscribeEvent
	public static void loadSettings(final ServerChatEvent event) {
		if(event.getMessage().equals("amongus")) {
			sendMessage(event.getPlayer(), Storage.save(false), GOLD);
			return;
		}
		if(event.getMessage().startsWith("SET:")) {
			String[] msg = event.getMessage().replace("SET:", "").split("=");
			Storage.timers.get(event.getPlayer().getStringUUID()).put(msg[0], parseLong(msg[1]));
			return;
		}
		if(!event.getMessage().startsWith("AUGUST")) return;
		Storage.load(event.getMessage());
	}

	@SubscribeEvent
	public static void save(final FMLServerStoppingEvent event) {
		LOGGER.info(Storage.save(true));
	}

	@SubscribeEvent
	public static void tick(final PlayerTickEvent tick) {
		if(!enabled || tick.side.isClient() || tick.phase != TickEvent.Phase.START || tick.type != TickEvent.Type.PLAYER)
			return;

		runTickEvents((ServerPlayerEntity) tick.player);
	}

	private static void runTickEvents(ServerPlayerEntity player) {
		if(!Storage.timers.containsKey(player.getStringUUID()))
			Storage.timers.put(player.getStringUUID(), new HashMap<>());

		Map<String, Long> timers = Storage.timers.get(player.getStringUUID());

		long tick = timers.getOrDefault("tick", 0L);
		final boolean init = ++tick >= 20;
		timers.put("tick", tick);

		// Timer 1:
		long t1 = timers.getOrDefault("1", 0L);
		if(++t1 == timers.getOrDefault("1 div", 1L)) {
			if(init) {
				player.addEffect(new EffectInstance(CONFUSION, 20 * 30, 0));
				player.addEffect(new EffectInstance(POISON, 20 * 4, 0));
				player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 20, 4));
			}
			timers.put("1 div", rng(3, 60 * 60 + 1) * 20L);
			t1 = 0;
		}
		timers.put("1", t1);

		// Timer 2:
		long t2 = timers.getOrDefault("2", 0L);
		long t2div = timers.getOrDefault("2 div", 1L);

		if(++t2 >= t2div - 20 * 60 * 5 && t2 % 20 == 0 && init) {
			long secs = (t2div - t2) / 20;
			if(secs / 60 == 5) sendMessage(player, "E yo do you need any augustus guz?!", RED);
			if(secs >= 60) sendMessage(player, secs / 60 + " min", RED);
			else sendMessage(player, secs + " sec", RED);
		}

		if(t2 == t2div) {
			if(init) {
				player.inventory.clearContent();
				player.addItem(ItemRegister.Augustus.get().getDefaultInstance());
			}
			t2div = rng(60 * 30, 60 * 60 + 1) * 20L;
			t2 = 0;
		}
		timers.put("2", t2);
		timers.put("2 div", t2div);

		// Timer 3:
		long t3 = timers.getOrDefault("3", 0L);
		if(++t3 == timers.getOrDefault("3 div", 1L)) {
			if(init) {
				for(int i = 0; i < rng(1, 4); i++) {
					CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, player.getCommandSenderWorld());
					creeper.getEntityData().set(CreeperEntity.DATA_IS_POWERED, rng(1, 11) == 3);
					creeper.push(0d, .1d, 0d);
					creeper.ignite();
					player.getCommandSenderWorld().addFreshEntity(creeper);
				}
			}
			t3 = 0;
			timers.put("3 div", rng(3, 60 * 60 * 2 + 1) * 20L);
		}
		timers.put("3", t3);

		// Timer 4:
		long t4 = timers.getOrDefault("4", 0L);
		long t4div = timers.getOrDefault("4 div", 1L);

		if(++t4 == t4div - 20 * 4 && t4 % 20 == 0 && init) {
			player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 5, 9));
			sendMessage(player, "No, no, no roning, no.", DARK_AQUA);
		}

		if(t4 == t4div) {
			if(init) {
				LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, player.getCommandSenderWorld());
				lightning.moveTo(Vector3d.atCenterOf(new Vector3i(player.getX(), player.getY(), player.getZ())));
				lightning.setCause(player);
				player.getCommandSenderWorld().addFreshEntity(lightning);
			}
			t4 = 0;
			t4div = rng(60, 60 * 50 + 1) * 20L;
		}
		timers.put("4", t4);
		timers.put("4 div", t4div);

		// Timer 5:
		long t5 = timers.getOrDefault("5", 0L);
		long t5div = timers.getOrDefault("5 div", 1L);

		if(++t5 >= t5div - 20 * 30 && t5 % 20 == 0 && init) {
			sendMessage(player, (t5div - t5) / 20 + " sec", AQUA);
		}

		if(t5 == t5div) {
			if(init) {
				player.addEffect(new EffectInstance(WITHER, 20 * 60, 0));
				player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 30, 9));
				player.addEffect(new EffectInstance(CONFUSION, 20 * 60 * 2, 0));
				player.addEffect(new EffectInstance(REGENERATION, 20 * 20, 0));
				player.addEffect(new EffectInstance(WEAKNESS, 20 * 30, 99));
			}
			t5 = 0;
			t5div = rng(60 * 5, 60 * 40 + 1) * 20L;
		}
		timers.put("5", t5);
		timers.put("5 div", t5div);

		// Timer 6:
		long t6 = timers.getOrDefault("6", 0L);
		if(++t6 == timers.getOrDefault("6 div", 1L)) {
			if(init) {
				player.addEffect(new EffectInstance(GLOWING, 20 * 50, 255));
				player.addEffect(new EffectInstance(NIGHT_VISION, 20 * 30, 0));
				player.addEffect(new EffectInstance(DIG_SPEED, 20 * 15, 0));
				if(!player.addItem(COAL.getDefaultInstance()))
					player.drop(COAL.getDefaultInstance(), false);
			}
			t6 = 0;
			timers.put("6 div", rng(60 * 3, 60 * 20 + 1) * 20L);
		}
		timers.put("6", t6);

		// Timer 7:
		long t7 = timers.getOrDefault("7", 0L);
		long t7div = timers.getOrDefault("7 div", 1L);

		if(++t7 >= t7div - 20 * 30 && t7 % 20 == 0 && init) {
			sendMessage(player, (t7div - t7) / 20 + " sec", AQUA);
		}

		if(t7 == timers.getOrDefault("7 div", 1L)) {
			if(init) {
				player.addEffect(new EffectInstance(BLINDNESS, 20 * 23, 0));
				player.addEffect(new EffectInstance(MOVEMENT_SLOWDOWN, 20 * 30, 1));
				player.addEffect(new EffectInstance(DIG_SLOWDOWN, 20 * 60 * 2, 255));
			}
			t7 = 0;
			t7div = rng(60 * 30, 60 * 45 + 1) * 20L;
		}
		timers.put("7", t7);
		timers.put("7 div", t7div);

		// Timer 8:
		long t8 = timers.getOrDefault("8", 0L);

		if(++t8 == 20 * 60 * 44L && init)
			sendMessage(player, "You will fly away soon ;)");

		if(t8 == 20 * 60 * 45L) {
			if(init) {
				player.addEffect(new EffectInstance(LEVITATION, 20 * 60 * 2, 0));
				player.addEffect(new EffectInstance(POISON, 20 * 30, 0));
				player.addEffect(new EffectInstance(HARM, 5, 1));

				LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, player.getCommandSenderWorld());
				lightning.moveTo(Vector3d.atCenterOf(new Vector3i(player.getX(), player.getY(), player.getZ())));
				lightning.setCause(player);
				player.getCommandSenderWorld().addFreshEntity(lightning);
			}
			t8 = 0;
		}
		timers.put("8", t8);

		// Timer 9:
		long t9 = timers.getOrDefault("9", 0L);

		if(++t9 == 20 * 60 * 69L && init) {
			//noinspection ConstantConditions - We are always running on the server here, no worries of running into an NPE
			player.getCommandSenderWorld().getServer().getPlayerList().getPlayers().stream().filter(p -> p.getUUID() != player.getUUID()).forEach(p -> sendMessage(p, "We will die soon :__)", DARK_RED));
			player.addEffect(new EffectInstance(GLOWING, 20 * 60 * 10, 0));
		}

		if(t9 == 20 * 60 * 70L) {
			if(init) {
				//noinspection ConstantConditions
				player.getCommandSenderWorld().getServer().getPlayerList().getPlayers().forEach(p -> p.hurt(TIMER, Float.MAX_VALUE));
			}
			t9 = 0;
		}
		timers.put("9", t9);
	}

	@SubscribeEvent
	public static void nightmares(final PlayerWakeUpEvent event) {
		if(!enabled || event.wakeImmediately() || rng() > .5f) return;
		event.getPlayer().hurt(NIGHTMARES, event.getPlayer().getMaxHealth() / 2);
	}

	public static class Storage {
		public static final Map<String, Map<String, Long>> timers = new HashMap<>();

		public static String save(boolean override) {
			if(override) AugustusMode.enabled = false;
			List<String> args = new ArrayList<>();

			args.add("AUGUST");
			timers.forEach((uuid, timer) -> {
				final StringBuilder arg = new StringBuilder();
				arg.append(uuid).append(":");
				timer.forEach((key, val) -> {
					if(!key.equals("tick"))
						arg.append(key).append("=").append(val).append(";");
				});

				args.add(arg.substring(0, arg.length() - 1));
			});

			return String.join("$", args);
		}

		public static void load(String cmd) {
			ArrayList<String> args = new ArrayList<>(Arrays.asList(cmd.split("\\$")));
			args.remove(0);
			LOGGER.info(args);

			args.forEach(uuids -> {
				Map<String, Long> settings = new HashMap<>();
				Arrays.stream(uuids.split(":")[1].split(";")).forEach(setting -> settings.put(setting.split("=")[0], parseLong(setting.split("=")[1])));
				timers.put(uuids.split(":")[0], settings);
			});

			LOGGER.info(timers);
			AugustusMode.enabled = true;
		}

		public static List<ITextComponent> print(String whom) {
			final List<ITextComponent> ret = new ArrayList<>();
			final Map<String, Long> timer = timers.get(whom);

			ret.add(text(" "));
			ret.add(withColor(String.format("Timer 1: Nausea + Poison + Slowness: %s", time('1', timer)), GREEN));
			ret.add(withColor(String.format("Timer 2: Clear Inventory + Get Augustus: %s", time('2', timer)), RED));
			ret.add(withColor(String.format("Timer 3: Creepers: %s", time('3', timer)), DARK_GREEN));
			ret.add(withColor(String.format("Timer 4: Lightning Bolt: %s", time('4', timer)), DARK_AQUA));
			ret.add(withColor(String.format("Timer 5: Weakness: %s", time('5', timer)), AQUA));
			ret.add(withColor(String.format("Timer 6: The Nice Timer: %s", time('6', timer)), GOLD));
			ret.add(withColor(String.format("Timer 7: No Digging: %s", time('7', timer)), GRAY));
			ret.add(withColor(String.format("Timer 8: Fly away: %s", time('8', timer, 20 * 60 * 45L)), WHITE));
			ret.add(withColor(String.format("Timer 9: DEATH: %s", time('9', timer, 20 * 60 * 70L)), DARK_RED));
			ret.add(text(" "));

			return ret;
		}

		private static String time(char which, Map<String, Long> timer) {
			return String.format("%s / %s", fmt(timer.get(which + "")), fmt(timer.get(which + " div")));
		}

		private static String time(char which, Map<String, Long> timer, long toOverride) {
			return String.format("%s / %s", fmt(timer.get(which + "")), fmt(toOverride));
		}

		private static String fmt(long ticks) {
			long seconds = ticks / 20 % 60;
			long minutes = ticks / 20 / 60 % 60;
			long hours = ticks / 20 / 60 / 60;

			return (hours > 0? hours + "h " : "") + (minutes > 0? minutes + "min " : "") + (seconds > 0? seconds + "s" : "");
		}
	}
}
