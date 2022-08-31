package mod.rozbrajaczpoziomow.testing.entities.renderer;

import mcp.MethodsReturnNonnullByDefault;
import mod.rozbrajaczpoziomow.testing.TestingMod;
import mod.rozbrajaczpoziomow.testing.entities.Projectile;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ProjectileRenderer extends EntityRenderer<Projectile> {
	public static final ResourceLocation texture = new ResourceLocation(TestingMod.MOD_ID, "textures/entity/projectile.png");

	public ProjectileRenderer(EntityRendererManager renderer) {
		super(renderer);
	}

	@Override
	public ResourceLocation getEntityTexture(Projectile entity) {
		return texture;
	}
}
