package satisfy.bloomingnature.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.client.model.OwlModel;
import satisfy.bloomingnature.entity.OwlEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class OwlRenderer extends MobRenderer<OwlEntity, OwlModel>
{
    private static final ResourceLocation TEXTURE = BloomingNatureIdentifier.of( "textures/entity/owl.png");

    public OwlRenderer(EntityRendererProvider.Context context) {
        super(context, new OwlModel(context.bakeLayer(OwlModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(OwlEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void renderNameTag(OwlEntity entity, Component component, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, float f) {
        poseStack.pushPose();
        if(entity.isBaby())
            poseStack.translate(0, 0.5F, 0);
        super.renderNameTag(entity, component, poseStack, multiBufferSource, i, f);
        poseStack.popPose();
    }

    @Override
    protected void setupRotations(OwlEntity livingEntity, PoseStack poseStack, float f, float g, float h, float i) {
        super.setupRotations(livingEntity, poseStack, f, g, h, i);
        if(livingEntity.isInSittingPose()) poseStack.translate(0, 0F, 0);

        float k = livingEntity.getSwimAmount(h);

        poseStack.translate(0, (k / 7F) / 2F, (k / 7F) / 2F);
        if(k > 0.0F)
        {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(k, 0, -7.0F)));
        }
        poseStack.scale(0.75F, 0.75F, 0.75F);
    }

}
