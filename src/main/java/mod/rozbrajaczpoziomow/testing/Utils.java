package mod.rozbrajaczpoziomow.testing;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Random;

import static net.minecraft.util.text.Color.fromLegacyFormat;
import static net.minecraft.util.text.Style.DEFAULT_FONT;
import static net.minecraft.util.text.Style.EMPTY;

public class Utils {
	public static IFormattableTextComponent withColor(String txt, TextFormatting color) {
		return text(txt).setStyle(EMPTY.withColor(fromLegacyFormat(color)).withFont(DEFAULT_FONT));
	}

	public static StringTextComponent text(String txt) {
		return new StringTextComponent(txt);
	}

	public static void spawnParticles(IParticleData particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int amount, boolean allowRNG) {
		while(amount-- > 0)
			if(allowRNG)
				Minecraft.getInstance().particleEngine.createParticle(particleData, x + rng(), y + rng(), z + rng(), xSpeed + rng() - .4, ySpeed + rng() - .4, zSpeed + rng() - .4);
			else
				Minecraft.getInstance().particleEngine.createParticle(particleData, x, y, z, xSpeed, ySpeed, zSpeed);
	}

	public static double rng() {
		return new Random().nextDouble();
	}
}
