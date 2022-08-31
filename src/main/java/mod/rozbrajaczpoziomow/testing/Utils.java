package mod.rozbrajaczpoziomow.testing;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Random;

import static net.minecraft.util.text.Color.fromTextFormatting;
import static net.minecraft.util.text.Style.DEFAULT_FONT;
import static net.minecraft.util.text.Style.EMPTY;
import static org.apache.logging.log4j.Level.ALL;

public class Utils {
	public static IFormattableTextComponent withColor(String txt, TextFormatting color) {
		return text(txt).setStyle(EMPTY.setColor(fromTextFormatting(color)).setFontId(DEFAULT_FONT));
	}

	public static StringTextComponent text(String txt) {
		return new StringTextComponent(txt);
	}

	public static void spawnParticles(IParticleData particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int amount) {
		while(amount-- > 0)
			Minecraft.getInstance().particles.addParticle(particleData, x + rng(), y + rng(), z + rng(), xSpeed + rng() - .5, ySpeed + rng() - .5, zSpeed + rng() - .5);
	}

	public static double rng() {
		return new Random().nextDouble() * 2.5;
	}
}
