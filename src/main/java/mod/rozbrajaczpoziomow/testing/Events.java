package mod.rozbrajaczpoziomow.testing;

import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
import mod.rozbrajaczpoziomow.testing.tile_entities.RainbowBlockTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;
import java.util.function.Function;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.EffectRegister.*;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.*;
import static net.minecraft.client.renderer.RenderType.translucent;
import static net.minecraft.item.Items.*;
import static net.minecraft.item.crafting.Ingredient.of;
import static net.minecraft.potion.Potions.THICK;
import static net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS;
import static net.minecraft.util.Hand.OFF_HAND;
import static net.minecraft.util.SoundCategory.MASTER;
import static net.minecraft.util.SoundEvents.ANVIL_USE;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraft.util.text.TextFormatting.RED;

@SuppressWarnings("unused")
public class Events {
	@Mod.EventBusSubscriber(modid = TestingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ModEvents {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public static void onClientSetup(FMLClientSetupEvent event) {
			for(RegistryObject<? extends Block> glass : BlockRegister.getTranslucent()) {
				RenderTypeLookup.setRenderLayer(glass.get(), translucent());
			}
		}

		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public static void createBlockColorHandlers(final ColorHandlerEvent.Block event) {
			event.getBlockColors().register((BlockState state, @Nullable IBlockDisplayReader reader, @Nullable BlockPos pos, int layer) -> {
				assert pos != null;
				assert reader != null;
				RainbowBlockTile te = (RainbowBlockTile) reader.getBlockEntity(pos);
				assert te != null;
//				TestingMod.LOGGER.info(String.format("[%d, %d, %d] - %X", pos.getX(), pos.getY(), pos.getZ(), color));
				return te.getColor();
			}, BlockRegister.RainbowBlock.get());
		}

		@SubscribeEvent
		public static void brewingRecipes(final FMLCommonSetupEvent event) {
			final Function<Potion, ItemStack> potion = p -> PotionUtils.setPotion(POTION.getDefaultInstance(), p);
			// Because brewing recipes are not part of datagen, they go here.
			// BrewingRecipeRegistry.addRecipe(bottom 3 slots input, brewing item, output);
			BrewingRecipeRegistry.addRecipe(of(potion.apply(THICK)), of(Yeetr.get()), potion.apply(StripperPotion.get()));
			BrewingRecipeRegistry.addRecipe(of(potion.apply(THICK)), of(Dice.get()), potion.apply(ShuffleInventoryPotion.get()));
			BrewingRecipeRegistry.addRecipe(of(potion.apply(THICK)), of(Yeetr.get()), potion.apply(PortalPotion.get()));
		}
	}

	@Mod.EventBusSubscriber(modid = TestingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
	public static class ForgeEvents {
		@SubscribeEvent
		public static void burnInHell(FillBucketEvent event) {
			if(event.getEmptyBucket().getItem() == Items.LAVA_BUCKET)
				event.getPlayer().setSecondsOnFire(5);

			else if(event.getEmptyBucket().getItem() == Items.WATER_BUCKET)
				event.getPlayer().clearFire();
		}

		@SubscribeEvent
		public static void hehe(BlockEvent.EntityPlaceEvent event) {
			if(event.getEntity() == null) return;

			if(!(event.getPlacedBlock().getBlock().is(BEACON_BASE_BLOCKS) && event.getPlacedAgainst().getBlock().is(BEACON_BASE_BLOCKS)))
				return;

			BlockPos pos = event.getPos();
			ItemEntity item = new ItemEntity(event.getEntity().getCommandSenderWorld(), pos.getX(), pos.getY(), pos.getZ(), Rybek.get().getDefaultInstance());
			item.setDefaultPickUpDelay();
			item.setExtendedLifetime();
			item.setInvulnerable(true);
			event.getEntity().getCommandSenderWorld().addFreshEntity(item);
			Utils.spawnParticlesTest(event.getEntity().getCommandSenderWorld(), RedstoneParticleData.REDSTONE, pos.getX(), pos.getY(), pos.getZ(), .2d, .5d, .2d, 50, true);
		}

		@SubscribeEvent
		public static void arrowCooldown(ArrowLooseEvent event) {
			event.getPlayer().getCooldowns().addCooldown(event.getBow().getItem(), event.getCharge() / 2);
		}

		@SubscribeEvent
		public static void brokenIronIngotCrafting(PlayerInteractEvent.RightClickItem event) {
			if(!(event.getPlayer() instanceof ServerPlayerEntity))
				return;

			ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();

			if(player.inventory.items.stream().noneMatch(is -> !is.isEmpty() && is.getItem() == AltCore.get()))
				return;

			if(player.getMainHandItem().getItem() != IRON_PICKAXE)
				return;

			if(player.getOffhandItem().getItem() != IRON_INGOT)
				return;

			if(player.getOffhandItem().getCount() != 1) {
				sendMessage(player, text("... Spadaj nie chce mi się", RED));
				return;
			}

			player.setItemInHand(OFF_HAND, BrokenIronIngot.get().getDefaultInstance());
			sendMessage(player, text("You fucking fucked up the Iron Ingot congrats kurwa, i wtedy Iron Ingot zamieni się na ten item....", GRAY));
			player.playNotifySound(ANVIL_USE, MASTER, 1f, 1f);
		}
	}
}
