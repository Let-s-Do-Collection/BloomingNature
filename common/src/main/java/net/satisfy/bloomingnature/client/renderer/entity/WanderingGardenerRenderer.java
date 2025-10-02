package net.satisfy.bloomingnature.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.client.model.WanderingGardenerModel;
import net.satisfy.bloomingnature.core.entity.WanderingGardenerEntity;
import org.jetbrains.annotations.NotNull;


@Environment(value = EnvType.CLIENT)
public class WanderingGardenerRenderer<T extends WanderingGardenerEntity> extends MobRenderer<T, WanderingGardenerModel<T>> {
    private static final ResourceLocation TEXTURE = BloomingNature.identifier("textures/entity/wandering_gardener.png");

    public WanderingGardenerRenderer(EntityRendererProvider.Context context) {
        super(context, new WanderingGardenerModel<>(context.bakeLayer(WanderingGardenerModel.LAYER_LOCATION)), 0.7f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(WanderingGardenerEntity entity) {
        return TEXTURE;
    }

}
