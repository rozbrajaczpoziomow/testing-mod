package mod.rozbrajaczpoziomow.testing;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.Random;

public class Utils {
	public static StringTextComponent text(String txt) {
		return new StringTextComponent(txt);
	}
	public static IFormattableTextComponent text(String txt, TextFormatting color) {
		return new StringTextComponent(txt).withStyle(color);
	}

	@Deprecated
	public static void spawnParticles(IParticleData particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int amount, boolean allowRNG) {
		if(FMLEnvironment.dist == Dist.CLIENT)
			while(amount-- > 0)
				if(allowRNG)
					Minecraft.getInstance().particleEngine.createParticle(particleData, x + rng(), y + rng(), z + rng(), xSpeed + rng() - .4, ySpeed + rng() - .4, zSpeed + rng() - .4);
				else
					Minecraft.getInstance().particleEngine.createParticle(particleData, x, y, z, xSpeed, ySpeed, zSpeed);
	}

	public static void spawnParticlesTest(World world, IParticleData particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int amount, boolean RNG) {
		while(amount-- > 0)
			if(RNG)
				world.addParticle(particleData, x + rng(), y + rng(), z + rng(), xSpeed + rng() - .4, ySpeed + rng() - .4, zSpeed + rng() - .4);
			else
				world.addParticle(particleData, x, y, z, xSpeed, ySpeed, zSpeed);
	}

	public static void sendMessage(Entity entity, String text) {
		sendMessage(entity, text(text));
	}

	public static void sendMessage(Entity entity, String text, TextFormatting color) {
		sendMessage(entity, text(text, color));
	}

	public static void sendMessage(Entity entity, ITextComponent component) {
		// noinspection ConstantConditions
		if(entity == null) return;
		entity.sendMessage(component, entity.getUUID());
	}

	public static double rng() {
		return new Random().nextDouble();
	}

	public static int rng(int min, int max) { // From min to max - 1
		return new Random().nextInt(max - min) + min;
	}

	public static int rng(int max) { // From 0 to max - 1
		return new Random().nextInt(max);
	}
}
