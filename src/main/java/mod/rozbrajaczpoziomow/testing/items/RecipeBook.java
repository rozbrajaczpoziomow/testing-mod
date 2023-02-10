package mod.rozbrajaczpoziomow.testing.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static mod.rozbrajaczpoziomow.testing.Utils.text;
import static net.minecraft.item.Items.AIR;
import static net.minecraft.util.ActionResult.pass;
import static net.minecraft.util.ActionResult.success;
import static net.minecraft.util.Hand.MAIN_HAND;

public class RecipeBook extends Item {
	public RecipeBook(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(text("Unlocks all recipes from this mod in the recipe book."));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack item = player.getItemInHand(hand);

		if(hand != MAIN_HAND)
			return pass(item);

		if(!(player instanceof ServerPlayerEntity))
			return pass(item);

		if(player.awardRecipes(Objects.requireNonNull(player.getServer()).getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getId().getNamespace().equals("testing")).collect(Collectors.toList())) > 0)
			return success(AIR.getDefaultInstance());
		return pass(item);
	}
}
