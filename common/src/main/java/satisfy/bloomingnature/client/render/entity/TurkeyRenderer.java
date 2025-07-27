package satisfy.bloomingnature.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.client.model.TurkeyModel;
import satisfy.bloomingnature.entity.TurkeyEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class TurkeyRenderer extends MobRenderer<TurkeyEntity, TurkeyModel<TurkeyEntity>> {
    private static final ResourceLocation TEXTURE = BloomingNatureIdentifier.of( "textures/entity/turkey.png");

    public TurkeyRenderer(EntityRendererProvider.Context context) {
        super(context, new TurkeyModel<>(context.bakeLayer(TurkeyModel.LAYER_LOCATION)), 0.7f);
    }

    protected float getBob(TurkeyEntity pelican, float f) {
        float g = Mth.lerp(f, pelican.oFlap, pelican.flap);
        float h = Mth.lerp(f, pelican.oFlapSpeed, pelican.flapSpeed);
        return (Mth.sin(g) + 1.0F) * h;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(TurkeyEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(TurkeyEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
