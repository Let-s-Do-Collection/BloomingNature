package net.satisfy.bloomingnature.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.satisfy.bloomingnature.core.util.BloomingNatureIdentifier;
import org.jetbrains.annotations.NotNull;

public class TermiteModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BloomingNatureIdentifier.of("termite"), "main");
    private final ModelPart body;
    private final ModelPart head;

    public TermiteModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    @SuppressWarnings("unused")
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -2.4F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0F, -3.0F, -5.4F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(1.0F, -3.0F, -6.4F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-1.0F, -3.0F, -6.4F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public @NotNull ModelPart root() {
        return body;
    }

    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {
        float rotation = Mth.cos(h * 0.9F) * 3.1415927F * 0.05F;

        this.body.yRot = rotation;
        this.head.yRot = rotation;
    }
}
