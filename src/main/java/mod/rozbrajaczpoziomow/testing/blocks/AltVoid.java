package mod.rozbrajaczpoziomow.testing.blocks;

import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeSoundType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static mod.rozbrajaczpoziomow.testing.Utils.sendMessage;
import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.a_registers.SoundRegister.*;
import static net.minecraft.block.SoundType.BAMBOO;
import static net.minecraft.item.Items.*;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.Hand.OFF_HAND;
import static net.minecraft.util.SoundCategory.MASTER;
import static net.minecraft.util.SoundEvents.BELL_BLOCK;
import static net.minecraft.util.text.TextFormatting.BLUE;

public class AltVoid extends Block {
	private int ticksExisted = 0;
	private int cooldown = 20 * 60;

	public AltVoid(Properties properties) {
		super(properties);
	}

	@Override
	public void stepOn(World world, BlockPos pos, Entity entity) {
		if(!(entity instanceof LivingEntity livingEntity)) return;
		if(world.isClientSide) return;
		sendMessage(entity, "!od dluohs uoy gniht eht ton s'ti ,potS", BLUE);
		livingEntity.addEffect(new EffectInstance(NIGHT_VISION, 20 * 5, 0));
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		ticksExisted += 2;
		cooldown -= 2;
		if(world.isClientSide) return;
		if(ticksExisted % (20 * 60) == 0) {
			for(ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
				player.addEffect(new EffectInstance(REGENERATION, 20 * 10, 0));
				player.addEffect(new EffectInstance(HEAL, 5, 0));
				player.addEffect(new EffectInstance(NIGHT_VISION, 20 * 20, 0));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("...eciohc doog a saw tI"));
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(10f, 1f, alt_void_break, alt_void_step, alt_void_place, BAMBOO::getHitSound, BAMBOO::getFallSound);
	}

	@Override
	public void updateEntityAfterFallOn(IBlockReader world, Entity entity) {
		if(((World)world).isClientSide) return;
		if(!(entity instanceof PlayerEntity player)) return;
		entity.setDeltaMovement(entity.getDeltaMovement().add(0d, 200d, 0d));
		player.setDeltaMovement(entity.getDeltaMovement());

		if(cooldown > 0) {
			sendMessage(player, Integer.valueOf(cooldown / 20).toString() + "s", BLUE);
			return;
		}

		if(player.getMainHandItem().getItem() != AIR || player.getOffhandItem().getItem() == AIR) return;
		ItemStack item = player.getOffhandItem().copy();
		if(!canCopy(item.getItem(), player)) return;
		item.setCount(1);
		player.setItemInHand(MAIN_HAND, item);
		player.playNotifySound(BELL_BLOCK, MASTER, 5f, 1f);
		cooldown = 20 * 60;
	}

	private boolean canCopy(Item item, PlayerEntity dirt) {
		if(item == DIRT) { dirt.addEffect(new EffectInstance(POISON, 20 * 1000000, 255)); dirt.setItemInHand(OFF_HAND, AIR.getDefaultInstance()); return false; }
		ArrayList<Item> no = new ArrayList<>();
		no.add(BlockRegister.AltVoid.get().asItem());
		no.add(BlockRegister.VoidBlock.get().asItem());
		no.add(ItemRegister.VoidIngot.get());
		no.add(ItemRegister.VoidCore.get());
		no.add(ItemRegister.GlitchedSword.get());
		no.add(STICK);
		no.add(AIR);
		return !no.contains(item);
	}
}
