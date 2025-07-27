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

public class TurkeyModel<T extends Entity> extends AgeableListModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BloomingNatureIdentifier.of("turkey"), "main");
    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart right_wing;
    private final ModelPart left_wing;

    public TurkeyModel(ModelPart root) {
        this.right_leg = root.getChild("right_leg");
        this.left_leg = root.getChild("left_leg");
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.right_wing = root.getChild("right_wing");
        this.left_wing = root.getChild("left_wing");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(19, 2).addBox(-1.0F, -1.0F, -0.6667F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(4, 2).addBox(-0.5F, 2.0F, 0.3333F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 4).addBox(-1.5F, 4.0F, -1.6667F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 20.0F, -1.3333F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(19, 2).addBox(-1.0F, -1.0F, -0.6667F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 4).addBox(-1.5F, 4.0F, -1.6667F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(4, 2).addBox(-0.5F, 2.0F, 0.3333F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 20.0F, -1.3333F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -3.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 24.0F, -0.5F));

        PartDefinition tail_trim_r1 = body.addOrReplaceChild("tail_trim_r1", CubeListBuilder.create().texOffs(27, 0).addBox(-7.5F, -17.5F, 0.0F, 13.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-6.0F, -16.0F, -1.0F, 10.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.5F, -0.48F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, -3.125F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 11).addBox(-2.0F, -7.125F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(6, 23).addBox(-1.0F, -5.125F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-1.0F, -3.125F, -1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.125F, -4.5F));

        PartDefinition right_wing = partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(20, 19).addBox(3.0F, -8.0F, -2.5F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition left_wing = partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(20, 19).addBox(-4.0F, -8.0F, -2.5F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition easteregg = partdefinition.addOrReplaceChild("easteregg", CubeListBuilder.create().texOffs(8, 28).addBox(-0.5F, -10.5F, -3.6667F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(8, 33).addBox(-0.5F, -12.25F, -3.6667F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 20.0F, -2.3333F, -0.0436F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int i, int j, int k) {
        body.render(matrices, vertexConsumer, i, j, k);
        head.render(matrices, vertexConsumer, i, j, k);
        left_leg.render(matrices, vertexConsumer, i, j, k);
        right_leg.render(matrices, vertexConsumer, i, j, k);
        left_wing.render(matrices, vertexConsumer, i, j, k);
        right_wing.render(matrices, vertexConsumer, i, j, k);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.left_leg, this.right_leg, this.left_wing, this.right_wing);
    }

    public void setupAnim(T entity, float f, float g, float h, float i, float j) {
        this.head.xRot = j * 0.017453292F;
        this.head.yRot = i * 0.017453292F;
        this.left_leg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.right_leg.xRot = Mth.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.left_wing.zRot = h;
        this.right_wing.zRot = -h;
    }
}
