package mod.rozbrajaczpoziomow.testing;

import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraft.client.renderer.RenderType.translucent;
import static net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = TestingMod.MOD_ID)
public class Events {
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		for(RegistryObject<Block> glass : BlockRegister.getTranslucent()) {
			RenderTypeLookup.setRenderLayer(glass.get(), translucent());
		}
	}

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

		// event.getEntity().sendMessage(text(BEACON_BASE_BLOCKS.getAllElements().stream().map(block -> (block.getRegistryName() == null? block.getTranslatedName() : block.getRegistryName()).toString()).collect(Collectors.joining(", "))), event.getEntity().getUUID());

		if(!(event.getPlacedBlock().getBlock().is(BEACON_BASE_BLOCKS) && event.getPlacedAgainst().getBlock().is(BEACON_BASE_BLOCKS))) return;

		BlockPos pos = event.getPos();
		ItemEntity item = new ItemEntity(event.getEntity().getCommandSenderWorld(), pos.getX(), pos.getY(), pos.getZ(), ItemRegister.Rybek.get().getDefaultInstance());
		item.setDeltaMovement(.15d, .5d, .15d);
		item.setDefaultPickUpDelay();
		item.setExtendedLifetime();
		item.setInvulnerable(true);
		event.getEntity().getCommandSenderWorld().addFreshEntity(item);
		Utils.spawnParticles(RedstoneParticleData.REDSTONE, pos.getX(), pos.getY(), pos.getZ(), .2d, .5d, .2d, 50, true);
	}

	@SubscribeEvent
	public static void arrowCooldown(ArrowLooseEvent event) {
		event.getPlayer().getCooldowns().addCooldown(event.getBow().getItem(), event.getCharge() * 2);
	}
	//net.minecraftforge.event.entity.player.
}
