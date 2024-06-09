package net.gabriel.minekart.entity.client;

import net.gabriel.minekart.Minekart;
import net.gabriel.minekart.entity.custom.ReticleEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ReticleRenderer extends MobEntityRenderer<ReticleEntity, ReticleModel<ReticleEntity>> {
    private static final Identifier TEXTURE = new Identifier(Minekart.MOD_ID, "textures/entity/reticle.png");

    public ReticleRenderer(EntityRendererFactory.Context context) {
        super(context, new ReticleModel<>(context.getPart(ModModelLayers.RETICLE)), 0f);
    }

    @Override
    public Identifier getTexture(ReticleEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(ReticleEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(20f,20f, 20f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
