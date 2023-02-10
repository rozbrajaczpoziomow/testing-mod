package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import static mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister.Core;
import static net.minecraft.item.Items.FIREWORK_ROCKET;
import static net.minecraft.util.ActionResultType.SUCCESS;

public class AltFirework extends Item {
	public AltFirework(Properties properties) {
		super(properties);
	}

	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();

		if(world.isClientSide) return SUCCESS;

		BlockPos pos = getPos(context.getClickLocation(), context.getClickedFace());
		spawnFirework(world, pos, context);

		world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY() + 20, pos.getZ(), FIREWORK_ROCKET.getDefaultInstance()));
		world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY() + 20, pos.getZ(), Core.get().getDefaultInstance()));

		return SUCCESS;
	}

	public static void spawnFirework(World world, BlockPos pos, ItemUseContext context) {
		ItemStack itemstack = context.getItemInHand();
		itemstack.shrink(1);

		FireworkRocketEntity firework = new FireworkRocketEntity(world, context.getPlayer(), pos.getX(), pos.getY(), pos.getZ(), itemstack);
		firework.lifetime = 100;
		world.addFreshEntity(firework);
	}

	public static BlockPos getPos(Vector3d vec, Direction dir) {
		return new BlockPos(vec.x + dir.getStepX() * .15d, vec.y + dir.getStepY() * .15d, vec.z + dir.getStepZ() * .15d);
	}
}
