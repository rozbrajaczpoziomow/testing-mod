package mod.rozbrajaczpoziomow.testing.blocks.custom;

import mcp.MethodsReturnNonnullByDefault;
import mod.rozbrajaczpoziomow.testing.items.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ForgeSoundType;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.Random;

import static mod.rozbrajaczpoziomow.testing.Utils.withColor;
import static mod.rozbrajaczpoziomow.testing.sounds.iSounds.*;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.DARK_PURPLE;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class VoidBlock extends Block {
	private int ticksExisted = 0;

	public VoidBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
		return player.getHeldItemMainhand().getItem() == ItemRegister.VoidCore.get();
	}

	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		if(stack.getItem() == ItemRegister.VoidCore.get()) super.harvestBlock(worldIn, player, pos, state, te, stack);
		else player.addStat(Stats.BLOCK_MINED.get(this));
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(world.isRemote) return;
		for(ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
			player.sendMessage(withColor("The void has been openenenenenenenenened...", DARK_PURPLE), player.getUniqueID());
			player.addPotionEffect(new EffectInstance(BLINDNESS, 20 * 25, 0));
			player.addPotionEffect(new EffectInstance(WITHER, 20 * 5, 0));
			player.addPotionEffect(new EffectInstance(SLOWNESS, 20 * 25, 2));
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity breaker) {
		if(world.isRemote) return;
		for(ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
			player.addPotionEffect(new EffectInstance(NIGHT_VISION, 20 * 120, 0));
			player.addPotionEffect(new EffectInstance(REGENERATION, 20 * 30, 0));
		}
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if(world.isRemote) return;
		entity.sendMessage(withColor("*)!#&% if you dont s#)@", DARK_PURPLE), entity.getUniqueID());
		for(ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
			player.addPotionEffect(new EffectInstance(SLOWNESS, 20 * 3, 9));
			player.addPotionEffect(new EffectInstance(BLINDNESS, 20 * 3, 0));
		}
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		ticksExisted += 2;
		if(world.isRemote) return;
		if(ticksExisted % (20 * 30) == 0) {
			for(ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
				player.addPotionEffect(new EffectInstance(SLOWNESS, 20 * 2, 3));
				player.addPotionEffect(new EffectInstance(BLINDNESS, 20 * 3, 0));
			}
		}
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
		return new ForgeSoundType(10f, 1f, void_block_break, void_block_step, void_block_place, SoundType.BAMBOO::getHitSound, SoundType.BAMBOO::getFallSound);
	}
}
