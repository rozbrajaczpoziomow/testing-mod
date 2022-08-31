package mod.rozbrajaczpoziomow.testing.items.custom;

import mcp.MethodsReturnNonnullByDefault;
import mod.rozbrajaczpoziomow.testing.entities.EntityRegister;
import mod.rozbrajaczpoziomow.testing.entities.Projectile;
import mod.rozbrajaczpoziomow.testing.items.ItemRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class USPGun extends Item {
	public USPGun(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if(hand != Hand.MAIN_HAND || !hasAmmo(player)) return super.onItemRightClick(world, player, hand);
		Projectile proj = new Projectile(EntityRegister.Projectile.get(), world);
		proj.setRawPosition(player.getPosX(), player.getPosYEye(), player.getPosZ());
		proj.setDirectionAndMovement(proj, player.rotationPitch, player.rotationYawHead, 0f, 1.3f, .1f);
		proj.owner = player;
		world.addEntity(proj);
		return super.onItemRightClick(world, player, hand);
	}

	private boolean hasAmmo(PlayerEntity player) {
		for(ItemStack stack : player.inventory.mainInventory)
			if(stack.getItem() == ItemRegister.USPAmmo.get()) {
				stack.damageItem(1, player, p -> {});
				return true;
			}
		return false;
	}

	//	@Override
//	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
//		if(!context.getWorld().isRemote) {
//			assert context.getPlayer() != null;
//			context.getPlayer().inventory.dropAllItems();
//		}
//		return ActionResultType.SUCCESS;
//	}
//
//	@Override
//	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
//		if(!stack.getItem().equals(this)) return false;
//		if(!target.getEntityWorld().isRemote) {
//			attacker.attackEntityFrom(DamageSource.STARVE, Float.MAX_VALUE);
//			target.heal(target.getMaxHealth() - target.getHealth());
//		}
//		return true;
//	}
//
//	@Override
//	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
//		if(!itemstack.getItem().equals(this)) return false;
//		if(!player.getEntityWorld().isRemote) {
//			player.getEntityWorld().setBlockState(pos, Blocks.BEDROCK.getDefaultState());
//			itemstack.setCount(itemstack.getCount() - 1);
//		}
//		return true;
//	}
}
