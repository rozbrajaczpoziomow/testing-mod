package mod.rozbrajaczpoziomow.testing.blocks.custom;

import mcp.MethodsReturnNonnullByDefault;
import mod.rozbrajaczpoziomow.testing.blocks.BlockRegister;
import mod.rozbrajaczpoziomow.testing.items.ItemRegister;
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
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static mod.rozbrajaczpoziomow.testing.sounds.iSounds.*;
import static net.minecraft.block.SoundType.BAMBOO;
import static net.minecraft.item.Items.*;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.util.Hand.OFF_HAND;
import static net.minecraft.util.SoundCategory.MASTER;
import static net.minecraft.util.SoundEvents.BLOCK_BELL_USE;
import static net.minecraft.util.text.TextFormatting.BLUE;

@MethodsReturnNonnullByDefault @ParametersAreNonnullByDefault
public class AltVoid extends Block {
	private int ticksExisted = 0;
	private int cooldown = 20 * 60;

	public AltVoid(Properties properties) {
		super(properties);
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if(!(entity instanceof LivingEntity livingEntity)) return;
		if(world.isRemote) return;
		entity.sendMessage(withColor("!od dluohs uoy gniht eht ton s'ti ,potS", BLUE), entity.getUniqueID());
		livingEntity.addPotionEffect(new EffectInstance(NIGHT_VISION, 20 * 5, 0));
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		ticksExisted += 2;
		cooldown -= 2;
		if(world.isRemote) return;
		if(ticksExisted % (20 * 60) == 0) {
			for(ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
				player.addPotionEffect(new EffectInstance(REGENERATION, 20 * 10, 0));
				player.addPotionEffect(new EffectInstance(INSTANT_HEALTH, 5, 0));
				player.addPotionEffect(new EffectInstance(NIGHT_VISION, 20 * 20, 0));
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("...eciohc doog a saw tI"));
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(10f, 1f, alt_void_break, alt_void_step, alt_void_place, BAMBOO::getHitSound, BAMBOO::getFallSound);
	}

	@Override
	public void onLanded(IBlockReader world, Entity entity) {
		if(((World)world).isRemote) return;
		if(!(entity instanceof PlayerEntity player)) return;
		entity.setMotion(entity.getMotion().add(0d, 200d, 0d));
		player.setMotion(entity.getMotion());

		if(cooldown > 0) {
			player.sendMessage(withColor(Integer.valueOf(cooldown / 20).toString() + "s", BLUE), player.getUniqueID());
			return;
		}

		if(player.getHeldItemMainhand().getItem() != AIR || player.getHeldItemOffhand().getItem() == AIR) return;
		ItemStack item = player.getHeldItemOffhand().copy();
		if(!canCopy(item.getItem(), player)) return;
		item.setCount(1);
		player.setHeldItem(MAIN_HAND, item);
		player.playSound(BLOCK_BELL_USE, MASTER, 5f, 1f);
		cooldown = 20 * 60;
	}

	private boolean canCopy(Item item, PlayerEntity dirt) {
		if(item == DIRT) { dirt.addPotionEffect(new EffectInstance(POISON, 20 * 1000000, 255)); dirt.setHeldItem(OFF_HAND, AIR.getDefaultInstance()); return false; }
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
