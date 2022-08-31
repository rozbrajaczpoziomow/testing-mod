package mod.rozbrajaczpoziomow.testing.entities.models;

// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ProjectileEntityModel extends EntityModel<Entity> {
	private final ModelRenderer texture;

	public ProjectileEntityModel() {
		textureWidth = 16;
		textureHeight = 16;

		texture = new ModelRenderer(this);
		texture.setRotationPoint(0.0F, 23.0F, 0.0F);
		texture.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		texture.setTextureOffset(6, 2).addBox(-2.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		texture.setTextureOffset(6, 2).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		texture.setTextureOffset(6, 2).addBox(1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		texture.setTextureOffset(0, 7).addBox(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		texture.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}