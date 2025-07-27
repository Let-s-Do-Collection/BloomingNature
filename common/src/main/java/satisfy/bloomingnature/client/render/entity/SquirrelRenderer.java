package satisfy.bloomingnature.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.client.model.SquirrelModel;
import satisfy.bloomingnature.entity.SquirrelEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;


@Environment(EnvType.CLIENT)
public class SquirrelRenderer extends MobRenderer<SquirrelEntity, SquirrelModel> {

    public SquirrelRenderer(EntityRendererProvider.Context context) {
        super(context, new SquirrelModel(context.bakeLayer(SquirrelModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(SquirrelEntity entity) {
        return BloomingNatureIdentifier.of( "textures/entity/squirrel.png");
    }
}