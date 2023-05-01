package mod.rozbrajaczpoziomow.testing.items;

import mod.rozbrajaczpoziomow.testing.a_registers.BlockRegister;
import mod.rozbrajaczpoziomow.testing.a_registers.ItemRegister;
import mod.rozbrajaczpoziomow.testing.blocks.StoneGenI;
import mod.rozbrajaczpoziomow.testing.tile_entities.StoneGenITile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.item.Items.*;
import static net.minecraft.potion.Effects.HARM;
import static net.minecraft.util.ActionResultType.PASS;
import static net.minecraft.util.ActionResultType.SUCCESS;

public class StoneGenICore extends Item {
	public StoneGenICore(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(!(entity instanceof PlayerEntity)) return;

		if(stack.getOrCreateTag().contains("drop"))
			stack.hurtAndBreak(1, (PlayerEntity) entity, p -> {
				p.addItem(new ItemStack(STONE, 2));
				p.addItem(COBBLESTONE.getDefaultInstance());
				p.addItem(ItemRegister.AltCore.get().getDefaultInstance());
				p.addItem(GLASS_BOTTLE.getDefaultInstance());
				p.addEffect(new EffectInstance(HARM, 1, 1));
			});
	}

	@Override
	public ActionResultType useOn(ItemUseContext ctx) {
		BlockPos pos = ctx.getClickedPos();
		World world = ctx.getLevel();

		if(world.getBlockState(pos).getBlock() != BlockRegister.StoneGenI.get()) return PASS;
		if(!(world.getBlockEntity(pos) instanceof StoneGenITile)) return PASS;

		((StoneGenITile) world.getBlockEntity(pos)).overclock();
		world.setBlock(pos, world.getBlockState(pos).setValue(StoneGenI.overclocked, true), 2 | 16 | 32 | 64);
		ctx.getItemInHand().getOrCreateTag().putBoolean("drop", true);

		return SUCCESS;
	}
}
