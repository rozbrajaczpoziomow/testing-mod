package mod.rozbrajaczpoziomow.testing.items;

import com.google.common.collect.ImmutableList;
import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.EffectRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.TagRegister;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.google.common.collect.ImmutableList.of;
import static mod.rozbrajaczpoziomow.testing.Utils.*;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.Core;
import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.ReshiftedDiamond;
import static net.minecraft.item.Items.GRASS_BLOCK;
import static net.minecraft.item.Items.WHEAT_SEEDS;
import static net.minecraft.util.text.TextFormatting.LIGHT_PURPLE;

public class Dice extends Item {
	private static final String diceTimer = "testing:dice_timer";
	public Dice(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, World w, Entity ent, int itemSlot, boolean isSelected) {
		if(!(ent instanceof ServerPlayerEntity))
			return;

		final ServerPlayerEntity player = (ServerPlayerEntity) ent;
		final ServerWorld world = (ServerWorld) w;

		if(!stack.getOrCreateTag().contains(diceTimer) || !isSelected)
			stack.getTag().putInt(diceTimer, 0);

		int timer = stack.getTag().getInt(diceTimer);
		stack.getTag().putInt(diceTimer, ++timer);

		if(timer != 20 * 60 * 5)
			return;

		switch(rng(4)) {
			case 0:
				final ImmutableList<Item> itemPool = of(GRASS_BLOCK, Core.get(), WHEAT_SEEDS, ReshiftedDiamond.get(), TagRegister.TagDyesItems.getRandomElement(new Random()));
				final Item item = itemPool.get(rng(itemPool.size()));
				player.addItem(item.getDefaultInstance());
				break;
			case 1:
				switch(rng(4)) {
					case 0:
						LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, player.getCommandSenderWorld());
						lightning.moveTo(Vector3d.atCenterOf(new Vector3i(player.getX(), player.getY(), player.getZ())));
						lightning.setCause(player);
						player.getCommandSenderWorld().addFreshEntity(lightning);
						break;
					case 1:
						final ImmutableList<EntityType<?>> entityPool = of(EntityType.CHICKEN, EntityType.VILLAGER, EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, EntityType.BAT);
						final EntityType<?> entityType = entityPool.get(rng(entityPool.size()));
						entityType.spawn(world, stack, player, player.blockPosition(), SpawnReason.EVENT, false, false);
						break;
					case 2:
						world.setDayTime(rng(2) == 0? 6000 : 18000);
						break;
					case 3:
						final ImmutableList<Block> blockPool = of(Blocks.SAND, Blocks.ANVIL, Blocks.WATER, Blocks.TNT, Blocks.DIRT, Blocks.LAVA, BlockRegister.StoneGenICoreOre.get());
						final Block block = blockPool.get(rng(blockPool.size()));
						final BlockPos pos = player.blockPosition().above(8);

						if(block == Blocks.SAND) {
							world.setBlock(pos, block.defaultBlockState(), 1 | 2);
							world.setBlock(pos.above(), block.defaultBlockState(), 1 | 2);
						} else
							world.setBlock(pos, block.defaultBlockState(), 1 | 2);

						if(block == Blocks.TNT)
							world.getBlockState(pos).catchFire(world, pos, null, null);
				}
				break;
			case 2:
				final ImmutableList<Effect> effectPool = of(Effects.POISON, Effects.CONFUSION, Effects.MOVEMENT_SLOWDOWN, Effects.REGENERATION, Effects.JUMP, EffectRegister.ShuffleInventoryEffect.get());
				final Effect effect = effectPool.get(rng(effectPool.size()));
				player.addEffect(new EffectInstance(effect, 20 * 5, 0));
				break;
			case 3:
				player.addItem((rng(2) == 0? ItemRegister.TLACore.get() : getItem()).getDefaultInstance());
				break;
		}

		stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(Hand.MAIN_HAND));
		stack.getTag().putInt(diceTimer, 0);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if(player.getCommandSenderWorld().isClientSide)
			return super.use(world, player, hand);

		ItemStack stack = player.getItemInHand(hand);
		stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(hand));
		sendMessage(player, text("Chance for: Event, item, effect, mob, another dice, whatever", LIGHT_PURPLE));
		return ActionResult.success(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("This dice is an avatar of random events... Its better to not carry it in hand."));
		tooltip.add(text("May be useful for potions or other craftings."));
		tooltip.add(text("If carried around in hand, can give randome effects or strange items sometimes..."));
	}
}
