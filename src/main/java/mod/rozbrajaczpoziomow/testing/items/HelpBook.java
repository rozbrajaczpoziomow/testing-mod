package mod.rozbrajaczpoziomow.testing.items;

import com.google.common.collect.ImmutableList;
import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import mod.rozbrajaczpoziomow.testing.tile_entities.LifeLimiterTile;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.ImmutableList.of;
import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.item.Items.REDSTONE;
import static net.minecraft.util.ActionResult.pass;
import static net.minecraft.util.ActionResult.success;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.text.TextFormatting.*;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE, modid = TestingMod.MOD_ID)
public class HelpBook extends Item {
	public static class HelpTopic {
		public static final HelpTopic INVALID = new HelpTopic(text("Invalid", DARK_RED), of(text("This is an invalid help topic", RED)));
		public final ITextComponent displayName;
		public final ImmutableList<ITextComponent> description;

		public HelpTopic(ITextComponent displayName, ImmutableList<ITextComponent> description) {
			this.displayName = displayName;
			this.description = description;
		}
	}

	public static ImmutableList<Supplier<HelpTopic>> topics = copyOf(new Supplier[] {
			() -> new HelpTopic(text("Obtaining " + name(ItemRegister.GluedPaper.get()), YELLOW), of(
					text("Whilst holding " + name(ItemRegister.Glue.get()) + " in your main hand, and upto " + Glue.paperLimit + " pieces of paper in your off hand, right-click.", YELLOW)
			)),
			() -> new HelpTopic(text("Obtaining a " + name(ItemRegister.BrokenIronIngot.get()), GRAY), of(
					text("Have an Alt Core in your inventory.", GRAY),
					text("Hold an Iron Pickaxe in your main hand, and an Iron Ingot in your off hand, then right-click.", GRAY)
			)),
			() -> new HelpTopic(text("Using a " + name(BlockRegister.UncraftingTable.get().asItem()), AQUA), of(
					text("Place a " + name(BlockRegister.BigCorbi.get().asItem()) + " below it", AQUA),
					text("Place a chest above it", AQUA),
					text("If you place items into the chest, they will automatically be decrafted", AQUA),
					text("If you right-click the " + name(BlockRegister.UncraftingTable.get().asItem()) + ", it will show it's stored contents", AQUA),
					text("If you want to retrieve the stored contents, right-click the " + name(BlockRegister.UncraftingTable.get().asItem()) + " with " + name(ItemRegister.AltMilk.get()), AQUA)
			)),
			() -> new HelpTopic(text("Obtaining a " + name(ItemRegister.ReshiftedEmerald.get()) + " / " + name(ItemRegister.ReshiftedDiamond.get()), LIGHT_PURPLE), of(
					text("Use a " + name(BlockRegister.Reshifter.get().asItem()), LIGHT_PURPLE)
			)),
			() -> new HelpTopic(text("Using " + name(BlockRegister.LifeLimiter.get().asItem()), GOLD), of(
					text("Place upto " + LifeLimiterTile.blockLimit + " blocks above it and wait " + LifeLimiterTile.delay / 20 + " seconds for the blocks to transform.", GOLD)
			)),
			() -> new HelpTopic(text(name(ItemRegister.Glowdust.get()) + " usage", GOLD), of(
					text("By right-clicking certain blocks (listed below), you can make them glow.", GOLD),
					text("To make them no longer glow, right-click on them whilst having " + name(ItemRegister.Glowdust.get()) + " in your offhand.", YELLOW),
					text("[Click to see the list of blocks]", GRAY).withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "$mod:testing/view_glowdust_blocks")))
			)),
			() -> new HelpTopic(text("Obtaining a " + name(ItemRegister.TLACoreReactive.get()), RED), of(
					text("Shift+right-click some " + name(REDSTONE) + " with a " + name(ItemRegister.TLACore.get()), RED)
			))
	});

	public HelpBook(Properties properties) {
		super(properties);
	}

	private static String name(Item item) {
		return item.getName(item.getDefaultInstance()).getString();
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack origItem = player.getItemInHand(hand);

		if(hand != MAIN_HAND)
			return pass(origItem);

		if(!world.isClientSide)
			return pass(origItem);

		sendMessage(player, "Help for:", GOLD);
		topics.forEach(topic -> sendMessage(player, text("  ").append(topic.get().displayName).withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "$mod:testing/view_topic/" + topics.indexOf(topic))))));
		sendMessage(player, text(""));

		return success(origItem);
	}

	@OnlyIn(value = Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onChat(final ClientChatEvent event) {
		// $mod:testing/view_glowdust_blocks
		if(!event.getOriginalMessage().startsWith("$mod:testing/"))
			return;

		event.setCanceled(true);
		final ClientPlayerEntity player = requireNonNull(Minecraft.getInstance().player);

		switch(event.getOriginalMessage().split("/")[1]) {
			case "view_topic":
				if(player.getMainHandItem().getItem() != ItemRegister.HelpBook.get()) {
					sendMessage(player, text("You need to be holding the Help Book in order to view help topics!", RED));
					return;
				}

				final String message = event.getOriginalMessage();
				HelpTopic topic;
				try {
					final String uuid = message.split("/")[2];
					topic = topics.get(parseInt(uuid)).get();
				} catch(Exception e) {
					topic = HelpTopic.INVALID;
				}
				sendMessage(player, text("Help for ", GOLD).append(topic.displayName));
				topic.description.forEach(text -> sendMessage(player, text("  ").append(text)));
				sendMessage(player, text(""));
				break;
			case "view_glowdust_blocks":
				int c = 0;
				for(Block block : Glowdust.getSupported())
					sendMessage(player, "- " + name(block.asItem()), ++c % 2 == 0? YELLOW : GOLD);
				break;
		}
	}
}
