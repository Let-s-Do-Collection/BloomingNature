package satisfy.bloomingnature.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.client.model.PelicanModel;
import satisfy.bloomingnature.entity.PelicanEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class PelicanRenderer extends MobRenderer<PelicanEntity, PelicanModel<PelicanEntity>> {
    private static final ResourceLocation TEXTURE = BloomingNatureIdentifier.of( "textures/entity/pelican.png");

    public PelicanRenderer(EntityRendererProvider.Context context) {
        super(context, new PelicanModel<>(context.bakeLayer(PelicanModel.LAYER_LOCATION)), 0.7f);
    }

    protected float getBob(PelicanEntity pelican, float f) {
        float g = Mth.lerp(f, pelican.oFlap, pelican.flap);
        float h = Mth.lerp(f, pelican.oFlapSpeed, pelican.flapSpeed);
        return (Mth.sin(g) + 1.0F) * h;
    }

    @Override
    public ResourceLocation getTextureLocation(PelicanEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PelicanEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}

