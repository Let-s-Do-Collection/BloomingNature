package satisfy.bloomingnature.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class PelicanModel<T extends Entity> extends AgeableListModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BloomingNatureIdentifier.of("pelican"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart wing0;
    private final ModelPart wing1;
    private final ModelPart beak;

    public PelicanModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leg0 = root.getChild("leg0");
        this.leg1 = root.getChild("leg1");
        this.wing0 = root.getChild("wing0");
        this.wing1 = root.getChild("wing1");
        this.beak = root.getChild("beak");
    }


    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(17, 1).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 11.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(18, 18).addBox(0.0F, -2.0F, 4.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition head = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(1, 30).addBox(-2.0F, -5.0F, -10.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(1, 22).addBox(-2.0F, -6.0F, -10.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        PartDefinition leg0 = modelPartData.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(6, 45).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 19.0F, 1.0F));

        PartDefinition leg1 = modelPartData.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(6, 45).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 19.0F, 1.0F));

        PartDefinition wing0 = modelPartData.addOrReplaceChild("wing0", CubeListBuilder.create().texOffs(26, 21).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 13.0F, 0.0F));

        PartDefinition wing1 = modelPartData.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(26, 21).addBox(0.0F, -1.0F, -3.0F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 13.0F, 0.0F));

        PartDefinition beak = modelPartData.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -7.0F, -6.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int red) {
        body.render(matrices, vertexConsumer, light, overlay, red);
        head.render(matrices, vertexConsumer, light, overlay, red);
        leg0.render(matrices, vertexConsumer, light, overlay, red);
        leg1.render(matrices, vertexConsumer, light, overlay, red);
        wing0.render(matrices, vertexConsumer, light, overlay, red);
        wing1.render(matrices, vertexConsumer, light, overlay, red);
        beak.render(matrices, vertexConsumer, light, overlay, red);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head, this.beak);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.leg0, this.leg1, this.wing0, this.wing1);
    }

    public void setupAnim(T entity, float f, float g, float h, float i, float j) {
        this.head.xRot = j * 0.017453292F;
        this.head.yRot = i * 0.017453292F;
        this.beak.xRot = this.head.xRot;
        this.beak.yRot = this.head.yRot;
        this.leg0.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.leg1.xRot = Mth.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.wing0.zRot = h;
        this.wing1.zRot = -h;
    }
}
