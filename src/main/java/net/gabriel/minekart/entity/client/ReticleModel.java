package net.gabriel.minekart.entity.client;

import net.gabriel.minekart.entity.animation.ModAnimations;
import net.gabriel.minekart.entity.custom.ReticleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class ReticleModel<T extends ReticleEntity> extends SinglePartEntityModel<T> {
	private final ModelPart reticle;

	public ReticleModel(ModelPart root) {
		this.reticle = root.getChild("reticle");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData reticle = modelPartData.addChild("reticle", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -0.2F, -8.0F, 16.0F, 0.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(ReticleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.RETICLE_IDLE, ageInTicks, 5f);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		reticle.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return reticle;
	}
}