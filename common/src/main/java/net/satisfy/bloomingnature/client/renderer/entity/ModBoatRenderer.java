package net.satisfy.bloomingnature.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.entity.ModBoatEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer<T extends ModBoatEntity> extends EntityRenderer<T> {
    private final Map<ModBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public ModBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context);
        this.shadowRadius = 0.8f;
        this.boatResources = Stream.of(ModBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type, type ->
                Pair.of(type.getTexture(hasChest), this.createBoatModel(context, type, hasChest))));
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, ModBoatEntity.Type type, boolean hasChest) {
        ModelLayerLocation modelLayerLocation = hasChest
                ? new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, type.getChestModelLocation()), "main")
                : new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, type.getModelLocation()), "main");
        ModelPart modelPart = context.bakeLayer(modelLayerLocation);
        return hasChest ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        matrixStack.pushPose();
        matrixStack.translate(0.0f, 0.375f, 0.0f);
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0f - entityYaw));
        float hurtTime = (float) entity.getHurtTime() - partialTicks;
        float damage = entity.getDamage() - partialTicks;
        if (damage < 0.0f) {
            damage = 0.0f;
        }
        if (hurtTime > 0.0f) {
            matrixStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(hurtTime) * hurtTime * damage / 10.0f * (float) entity.getHurtDir()));
        }
        if (!Mth.equal(entity.getBubbleAngle(partialTicks), 0.0f)) {
            matrixStack.mulPose(new Quaternionf().setAngleAxis(entity.getBubbleAngle(partialTicks) * ((float) Math.PI / 180.0f), 1.0f, 0.0f, 1.0f));
        }
        Pair<ResourceLocation, ListModel<Boat>> boatResource = this.boatResources.get(entity.getWoodType());
        ResourceLocation textureLocation = boatResource.getFirst();
        ListModel<Boat> boatModel = boatResource.getSecond();
        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        matrixStack.mulPose(Axis.YP.rotationDegrees(90.0f));
        boatModel.setupAnim(entity, partialTicks, 0.0f, -0.1f, 0.0f, 0.0f);
        VertexConsumer vertexConsumer = buffer.getBuffer(boatModel.renderType(textureLocation));
        boatModel.renderToBuffer(matrixStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        if (!entity.isUnderWater()) {
            VertexConsumer waterMaskBuffer = buffer.getBuffer(RenderType.waterMask());
            if (boatModel instanceof WaterPatchModel waterPatchModel) {
                waterPatchModel.waterPatch().render(matrixStack, waterMaskBuffer, packedLight, OverlayTexture.NO_OVERLAY);
            }
        }
        matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ModBoatEntity boat) {
        return this.boatResources.get(boat.getWoodType()).getFirst();
    }
}