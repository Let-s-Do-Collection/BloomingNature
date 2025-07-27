package satisfy.bloomingnature.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class DeerModel<T extends Entity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BloomingNatureIdentifier.of( "deer"), "main");
    private final ModelPart deer;
    private final ModelPart head;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public DeerModel(ModelPart root) {
        this.deer = root.getChild("deer");
        this.head = this.deer.getChild("body").getChild("head");
        this.leftHindLeg = this.deer.getChild("leftHindLeg");
        this.rightHindLeg = this.deer.getChild("rightHindLeg");
        this.rightFrontLeg = this.deer.getChild("rightFrontLeg");
        this.leftFrontLeg = this.deer.getChild("leftFrontLeg");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition deer = partdefinition.addOrReplaceChild("deer", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = deer.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, 7.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-5.0F,
                -4.0F, -9.0F, 10.0F, 9.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(18, 27).addBox(-3.0F, -12.0F, -7.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 9)
                        .addBox(-2.0F, -10.0F, -10.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 27).addBox(-2.0F, -7.0F, -5.0F, 4.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, -7.0F));

        head.addOrReplaceChild("horn_right_r1", CubeListBuilder.create().texOffs(19, 28).addBox(-3.09F, -37.0F, -14.0F, 0.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 15.0F, 7.0F, 0.0F, 0.0F, 0.2182F));

        head.addOrReplaceChild("horn_left_r1", CubeListBuilder.create().texOffs(19, 28).addBox(2.93F, -37.0F, -14.0F, 0.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 15.0F, 7.0F, 0.0F, 0.0F, -0.2182F));

        head.addOrReplaceChild("left_ear_r1", CubeListBuilder.create().texOffs(38, 0).mirror().addBox(-7.0F, -26.0F, -9.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(0.0F, 15.0F, 7.0F, 0.0F, 0.0F, 0.0436F));

        head.addOrReplaceChild("right_ear_r1", CubeListBuilder.create().texOffs(38, 0).addBox(4.0F, -26.0F, -9.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 15.0F, 7.0F, 0.0F, 0.0F, -0.0436F));

        deer.addOrReplaceChild("leftHindLeg", CubeListBuilder.create().texOffs(38, 5).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -10.0F, 7.5F));

        deer.addOrReplaceChild("rightHindLeg", CubeListBuilder.create().texOffs(38, 5).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offset(3.5F, -10.0F, 7.5F));

        deer.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(38, 5).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offset(3.5F, -10.0F, -7.5F));

        deer.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(38, 5).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.5F, -10.0F, -7.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.head.yRot = netHeadYaw * 0.0089453292F;
        this.head.xRot = headPitch * 0.0047453292F;

        this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 0.5F * limbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 0.5F * limbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int k) {
        deer.render(poseStack, vertexConsumer, i, j, k);
    }

    @Override
    public ModelPart root() {
        return deer;
    }
}