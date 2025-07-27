package satisfy.bloomingnature.client.model;


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Pig;
import org.jetbrains.annotations.NotNull;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class BoarModel<T extends Pig> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BloomingNatureIdentifier.of("boar"), "main");
    private final ModelPart body;
    private final ModelPart head;

    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public BoarModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
        this.rightHindLeg = root.getChild("rightHindLeg");
        this.leftHindLeg = root.getChild("leftHindLeg");
        this.rightFrontLeg = root.getChild("rightFrontLeg");
        this.leftFrontLeg = root.getChild("leftFrontLeg");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, -3.0F));

        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -3.0F, -10.0F, 16.0F, 11.0F, 20.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(0.0F, 5.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition mane = body.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(18, 61).addBox(0.0F, -26.0F, -10.0F, 0.0F, 10.0F, 19.0F, new CubeDeformation(0.02F)), PartPose.offset(0.0F, 19.0F, 3.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(52, 0).addBox(-4.0F, -4.3807F, -15.3061F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 31).addBox(-5.0F, -6.3807F, -11.3061F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 60).addBox(-5.0F, -7.3807F, -14.3061F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 31).addBox(3.0F, -7.3807F, -14.3061F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, 0.8727F, 0.0F, 0.0F));

        PartDefinition right_Ear = head.addOrReplaceChild("right_Ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_Ear_r1 = right_Ear.addOrReplaceChild("right_Ear_r1", CubeListBuilder.create().texOffs(52, 9).addBox(0.0346F, -5.3277F, 2.1863F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9769F, 2.4403F, -9.4924F, 0.0F, 0.0F, -0.7854F));

        PartDefinition left_Ear = head.addOrReplaceChild("left_Ear", CubeListBuilder.create(), PartPose.offset(4.9769F, 2.4403F, -9.4924F));

        PartDefinition left_Ear_r1 = left_Ear.addOrReplaceChild("left_Ear_r1", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0346F, -5.3277F, 2.1863F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("rightHindLeg", CubeListBuilder.create().texOffs(48, 48).addBox(-14.0F, -2.0F, -2.0F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 16.0F, 9.0F));

        PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("leftHindLeg", CubeListBuilder.create().texOffs(0, 0).addBox(9.0F, -2.0F, -3.0F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 16.0F, 10.0F));

        PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(24, 48).addBox(-2.0F, -1.0F, -3.0F, 6.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 12.0F, -3.0F));

        PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(0, 41).addBox(-4.0F, -1.0F, -3.0F, 6.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 12.0F, -3.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    private static ModelPart safeGetChild(ModelPart part, String childName) {
        if (part == null) {
            throw new IllegalArgumentException("Part " + childName + " could not be initialized because its parent is null.");
        }
        return part.getChild(childName);
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightFrontLeg, this.leftFrontLeg, this.rightHindLeg, this.leftHindLeg);
    }

    @Override
    public void setupAnim(T mob, float f, float g, float h, float i, float j) {
        this.head.yRot = i * 0.017453292F;

        float l = 1.0F;


        if (mob.isBaby()) {
            this.head.y = Mth.lerp(l, 2.0F, 5.0F);
        } else {
            this.head.y = 2.0F;
        }

        float m = 1.2F;
        this.rightFrontLeg.xRot = Mth.cos(f) * 1.2F * g;
        this.leftFrontLeg.xRot = Mth.cos(f + 3.1415927F) * 1.2F * g;
        this.rightHindLeg.xRot = this.leftFrontLeg.xRot;
        this.leftHindLeg.xRot = this.rightFrontLeg.xRot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int k) {
        body.render(poseStack, vertexConsumer, i, j, k);
        rightHindLeg.render(poseStack, vertexConsumer, i, j, k);
        leftHindLeg.render(poseStack, vertexConsumer, i, j, k);
        rightFrontLeg.render(poseStack, vertexConsumer, i, j, k);
        leftFrontLeg.render(poseStack, vertexConsumer, i, j, k);
    }

    @Override
    public @NotNull ModelPart root() {
        return body;
    }
}