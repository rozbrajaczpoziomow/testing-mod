package mod.rozbrajaczpoziomow.testing.blocks;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static mod.rozbrajaczpoziomow.testing.TestingMod.MOD_ID;
import static mod.rozbrajaczpoziomow.testing.Utils.*;
import static net.minecraft.potion.Effects.GLOWING;
import static net.minecraft.potion.Effects.POISON;
import static net.minecraft.util.text.TextFormatting.YELLOW;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class Cortrap extends Block {
	private static final HashMap<UUID, Pair<Integer, Integer>> timers = new HashMap<>();
	public Cortrap(Properties properties) {
		super(properties);
	}

	@Override
	public void stepOn(World world, BlockPos pos, Entity entity) {
		if(!(entity instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) entity;
		player.addEffect(new EffectInstance(GLOWING, 20 * 4, 0));
		timers.put(player.getUUID(), Pair.of(20 * 60, 20 * 60 * 2 + 30));
	}

	@SubscribeEvent
	public static void playerTick(final TickEvent.PlayerTickEvent tick) {
		if(tick.side.isClient() || tick.phase != TickEvent.Phase.START || tick.type != TickEvent.Type.PLAYER || !timers.containsKey(tick.player.getUUID()))
			return;

		final Pair<Integer, Integer> timer = timers.get(tick.player.getUUID());
		int first = timer.getFirst() - 1;
		int second = timer.getSecond() - 1;

		if(first < 0 && second < 0)
			return;

		if(first == 0) {
			tick.player.addEffect(new EffectInstance(GLOWING, 20 * 20, 0));
			sendMessage(tick.player, "Glowing...", YELLOW);
			--first;
		}

		if(second == 0) {
			if(rng(2) == 0) {
				tick.player.addEffect(new EffectInstance(GLOWING, 20 * 2, 0));
				tick.player.addEffect(new EffectInstance(POISON, 20 * 5, 0));
			} else {
				tick.player.addEffect(new EffectInstance(GLOWING, 20 * 10, 0));
				LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, tick.player.getCommandSenderWorld());
				lightning.moveTo(Vector3d.atCenterOf(new Vector3i(tick.player.getX() + .5, tick.player.getY(), tick.player.getZ() - .5)));
				lightning.setCause((ServerPlayerEntity) tick.player); // this is server-side, this cast should be fine.
				tick.player.getCommandSenderWorld().addFreshEntity(lightning);
			}
			--second;
		}

		timers.put(tick.player.getUUID(), Pair.of(first, second));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> tooltip, ITooltipFlag wtf) {
		tooltip.add(text("A nice looking glowing block!"));
		tooltip.add(text("What could go wrong here?"));
	}
}
