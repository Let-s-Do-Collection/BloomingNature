package satisfy.bloomingnature.client.render.entity;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.client.BloomingNatureClient;
import satisfy.bloomingnature.entity.SheepEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class MossySheepRenderer extends MobRenderer<SheepEntity, SheepModel<SheepEntity>> {

    private static final ResourceLocation TEXTURE = BloomingNatureIdentifier.of( "textures/entity/mossy_sheep.png");

    public MossySheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(BloomingNatureClient.MOSSY_SHEEP_MODEL_LAYER)), 0.7f);
        this.addLayer(new MossySheepWoolFeature<>(this, context.getModelSet(), "mossy", BloomingNatureClient.MOSSY_SHEEP_FUR));
    }

    @Override
    public ResourceLocation getTextureLocation(SheepEntity entity) {
        return TEXTURE;
    }
}