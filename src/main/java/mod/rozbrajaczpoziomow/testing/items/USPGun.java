package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.a_registers.EntityRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import mod.rozbrajaczpoziomow.testing.entities.Projectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class USPGun extends Item {
	public USPGun(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if(!player.isCreative()) player.getCooldowns().addCooldown(this, 20);
		if(hand != Hand.MAIN_HAND || !hasAmmo(player)) return super.use(world, player, hand);
		Projectile proj = new Projectile(EntityRegister.Projectile.get(), world);
		proj.setPosRaw(player.getX(), player.getEyeY(), player.getZ());
		proj.shootFromRotation(proj, player.xRot, player.yHeadRot, 0f, 1.3f, .1f);
		proj.owner = player;
		world.addFreshEntity(proj);
		return super.use(world, player, hand);
	}

	private boolean hasAmmo(PlayerEntity player) {
		for(ItemStack stack : player.inventory.items)
			if(stack.getItem() == ItemRegister.USPAmmo.get()) {
				stack.hurtAndBreak(1, player, p -> {});
				return true;
			}
		return false;
	}

	//	@Override
//	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
//		if(!context.getWorld().isClientSide) {
//			assert context.getPlayer() != null;
//			context.getPlayer().inventory.dropAllItems();
//		}
//		return ActionResultType.SUCCESS;
//	}
//
//	@Override
//	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
//		if(!stack.getItem().equals(this)) return false;
//		if(!target.getEntityWorld().isClientSide) {
//			attacker.hurt(DamageSource.STARVE, Float.MAX_VALUE);
//			target.heal(target.getMaxHealth() - target.getHealth());
//		}
//		return true;
//	}
//
//	@Override
//	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
//		if(!itemstack.getItem().equals(this)) return false;
//		if(!player.getEntityWorld().isClientSide) {
//			player.getEntityWorld().setBlock(pos, Blocks.BEDROCK.defaultBlockState());
//			itemstack.setCount(itemstack.getCount() - 1);
//		}
//		return true;
//	}
}
