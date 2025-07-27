package satisfy.bloomingnature.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import satisfy.bloomingnature.client.model.RaccoonModel;
import satisfy.bloomingnature.entity.RaccoonEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;


@Environment(value = EnvType.CLIENT)
public class RaccoonRenderer extends MobRenderer<RaccoonEntity, RaccoonModel<RaccoonEntity>> {
    private static final ResourceLocation RACCOON_TEXTURE = BloomingNatureIdentifier.of("textures/entity/raccoon.png");
    private static final ResourceLocation RACOON_SLEEP_TEXTURE = BloomingNatureIdentifier.of("textures/entity/raccoon.png");

    public RaccoonRenderer(EntityRendererProvider.Context context) {
        super(context, new RaccoonModel(context.bakeLayer(RaccoonModel.LAYER_LOCATION)), 0.7f);
    }

    @Override
    protected void setupRotations(RaccoonEntity livingEntity, PoseStack poseStack, float f, float g, float h, float i) {
        super.setupRotations(livingEntity, poseStack, f, g, h, i);
    }

    protected float getBob(RaccoonEntity RaccoonEntity, float f) {
        return RaccoonEntity.getTailAngle();
    }


    public @NotNull ResourceLocation getTextureLocation(RaccoonEntity entity) {
            return entity.isSleeping() ? RACOON_SLEEP_TEXTURE : RACCOON_TEXTURE;
              }

    @Override
    public void render(RaccoonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}

