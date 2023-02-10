package mod.rozbrajaczpoziomow.testing.items;

import com.google.common.collect.ImmutableList;
import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
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

import java.util.ArrayList;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.ImmutableList.of;
import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static mod.rozbrajaczpoziomow.testing.Utils.*;
import static net.minecraft.util.ActionResult.pass;
import static net.minecraft.util.ActionResult.success;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.text.TextFormatting.*;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE, modid = TestingMod.MOD_ID)
public class HelpBook extends Item {
	public static class HelpTopic {
		public static final HelpTopic WRONG = new HelpTopic(withColor("Incorrect", DARK_RED), of(withColor("This is an incorrect help topic", RED)));
		public final ITextComponent displayName;
		public final ImmutableList<ITextComponent> description;

		public HelpTopic(ITextComponent displayName, ImmutableList<ITextComponent> description) {
			this.displayName = displayName;
			this.description = description;
		}
	}
	public static ImmutableList<HelpTopic> topics = initTopics();

	public HelpBook(Properties properties) {
		super(properties);
	}

	private static ImmutableList<HelpTopic> initTopics() {
		final ArrayList<HelpTopic> topicList = new ArrayList<>();
		// We can't try and use the getName() function because we're before the registers...register.
		topicList.add(new HelpTopic(withColor("Obtaining Glued Paper", YELLOW), of(
				withColor("Whilst holding Glue in your main hand, and upto 3 pieces of paper in your off hand, right-click.", YELLOW)
		)));
		topicList.add(new HelpTopic(withColor("Obtaining a Broken Airon Ingot", GRAY), of(
				withColor("Have an Alt Core in your inventory.", GRAY),
				withColor("Hold an Iron Pickaxe in your main hand, and an Iron Ingot in your off hand, then right-click.", GRAY)
		)));
		topicList.add(new HelpTopic(withColor("Using a elbaT gnitfarC", AQUA), of(
				withColor("Place a BigBudżet.pl Corbi below it", AQUA),
				withColor("Place a chest above it", AQUA),
				withColor("If you place items into the chest, they will automatically be decrafted", AQUA),
				withColor("If you right-click the elbaT gnitfarC, it will show it's stored contents", AQUA),
				withColor("If you want to retrieve the stored contents, right-click the elbaT gnitfarC with an Alternate kliM", AQUA)
		)));
		topicList.add(new HelpTopic(withColor("Obtaining a Reshifted Emerald / Diamond", LIGHT_PURPLE), of(
				withColor("Use a Reshifter", LIGHT_PURPLE)
		)));
		return copyOf(topicList);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack origItem = player.getItemInHand(hand);

		if(hand != MAIN_HAND)
			return pass(origItem);

		if(!world.isClientSide)
			return pass(origItem);

		sendMessage(player, "Help for:", GOLD);
		topics.forEach(topic -> sendMessage(player, text("  ").append(topic.displayName).withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "$view_topic " + topics.indexOf(topic))))));
		sendMessage(player, text(""));

		return success(origItem);
	}

	@OnlyIn(value = Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onChat(final ClientChatEvent event) {
		if(!event.getOriginalMessage().startsWith("$view_topic"))
			return;

		event.setCanceled(true);

		final ClientPlayerEntity player = requireNonNull(Minecraft.getInstance().player);

		if(player.getMainHandItem().getItem() != ItemRegister.HelpBook.get()) {
			sendMessage(player, withColor("You need to be holding the Help Book in order to view help topics!", RED));
			return;
		}

		final String message = event.getOriginalMessage();
		HelpTopic topic;
		try {
			final String uuid = message.split(" ")[1];
			topic = topics.get(parseInt(uuid));
		} catch(Exception e) {
			topic = HelpTopic.WRONG;
		}
		sendMessage(player, withColor("Help for ", GOLD).append(topic.displayName));
		topic.description.forEach(text -> sendMessage(player, text("  ").append(text)));
		sendMessage(player, text(""));
	}
}
