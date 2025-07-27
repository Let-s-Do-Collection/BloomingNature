package satisfy.bloomingnature.client.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.entity.SquirrelEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

@Environment(EnvType.CLIENT)
public class SquirrelModel extends AgeableListModel<SquirrelEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BloomingNatureIdentifier.of( "squirrel"), "main");
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart leftThigh;
    private final ModelPart leftFoot;
    private final ModelPart rightLeg;
    private final ModelPart rightThigh;
    private final ModelPart rightFoot;
    private final ModelPart tail;

    public SquirrelModel(ModelPart root) {
        super(true, 0.0f, 0.0f);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftArm = this.body.getChild("leftArm");
        this.rightArm = this.body.getChild("rightArm");
        this.leftLeg = this.body.getChild("leftLeg");
        this.leftThigh = this.leftLeg.getChild("leftThigh");
        this.leftFoot = this.leftThigh.getChild("leftFoot");
        this.rightLeg = this.body.getChild("rightLeg");
        this.rightThigh = this.rightLeg.getChild("rightThigh");
        this.rightFoot = this.rightThigh.getChild("rightFoot");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(18, 13).addBox(-1.5F, -6.0F, -9.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(12, 13).addBox(1.5F, -7.0F, -6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-2.5F, -7.0F, -6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 7).addBox(-0.5F, -2.0F, -9.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));


        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -4.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -4.7929F, 5.7071F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-1.0F, -10.7929F, 5.7071F, 3.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -3.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(12, 28).addBox(-3.0F, -4.0F, -3.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -4.0F, -3.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition rightThigh = rightLeg.addOrReplaceChild("rightThigh", CubeListBuilder.create().texOffs(24, 25).addBox(-5.0F, -3.0F, 3.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -1.0F, -1.0F));

        PartDefinition rightFoot = rightThigh.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(13, 22).addBox(-12.0F, 1.0F, 2.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -1.0F, -1.0F));

        PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition leftThigh = leftLeg.addOrReplaceChild("leftThigh", CubeListBuilder.create().texOffs(26, 6).addBox(-5.0F, -3.0F, 2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.0F, 0.0F));

        PartDefinition leftFoot = leftThigh.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(18, 0).addBox(-7.0F, 1.0F, 0.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

// Squirrel Animation by SameDifferent: https://github.com/samedifferent/Ecologics/blob/1.19-crossplatform/LICENSE
    @Override
    public void setupAnim(SquirrelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.getAllParts().forEach(ModelPart::resetPose);
        this.head.resetPose();

        float swingCorrectionFactor = 0.1F;

        this.head.xRot = headPitch * Mth.DEG_TO_RAD;
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.head.xRot += Mth.cos((float) Math.toRadians(-45) + limbSwing) * (swingCorrectionFactor * 0.4F) * limbSwingAmount;
        this.head.y += (-1 - Mth.cos(limbSwing)) * 0 * limbSwingAmount;
        this.head.z += 1 * limbSwingAmount;

        if (entity.isBaby()) {
            this.head.y -= -10.0F;
            this.head.z -= -1.5F;
        }

        this.body.xRot += (float) ((Math.toRadians(10) - Mth.cos((float) Math.toRadians(-30) + limbSwing)) * (swingCorrectionFactor * 3.5F) * limbSwingAmount);
        this.body.y += (-1 - Mth.cos(limbSwing)) * 2.0f * limbSwingAmount;

        this.leftArm.xRot += (float) ((Math.toRadians(-15) - Mth.cos((float) Math.toRadians(-35) + limbSwing)) * (swingCorrectionFactor * 7.5F) * limbSwingAmount);
        this.leftArm.yRot += (float) Math.toRadians(-5);
        this.leftArm.zRot += (float) ((Math.toRadians(-15) - Mth.cos(limbSwing)) * (swingCorrectionFactor * 2.0F) * limbSwingAmount);
        leftArm.z += (0.5F + Mth.cos(limbSwing)) * (swingCorrectionFactor * 0.05F) * limbSwingAmount;

        this.rightArm.xRot += (float) ((Math.toRadians(-15) - Mth.cos((float) Math.toRadians(-45) + limbSwing)) * (swingCorrectionFactor * 7.5F) * limbSwingAmount);
        this.rightArm.yRot += (float) Math.toRadians(-5);
        this.rightArm.zRot += (float) ((Math.toRadians(15) - Mth.cos(limbSwing)) * (swingCorrectionFactor * 2.0F) * limbSwingAmount);
        rightArm.z += (0.5F + Mth.cos((float) Math.toRadians(-25) + limbSwing)) * (swingCorrectionFactor * 0.05F) * limbSwingAmount;

        this.leftThigh.xRot += (float) ((Math.toRadians(45) - Mth.cos((float) Math.toRadians(-45) + limbSwing)) * (swingCorrectionFactor * 6.5F) * limbSwingAmount);
        this.leftThigh.y += -3 * (swingCorrectionFactor * 7) * limbSwingAmount;
        this.leftThigh.z += Mth.cos((float) Math.toRadians(-35) + limbSwing) * (swingCorrectionFactor * 0.1F) * limbSwingAmount;

        this.leftFoot.xRot += (float) ((Math.toRadians(25) + Mth.cos((float) Math.toRadians(-125) + limbSwing)) * (swingCorrectionFactor * 6.0F) * limbSwingAmount);
        this.leftFoot.y += -1 * (swingCorrectionFactor * 2.0F) * limbSwingAmount;
        this.leftFoot.z += (-0.5F + Mth.cos(limbSwing)) * (swingCorrectionFactor * 0.025F) * limbSwingAmount;

        this.rightLeg.y += -1 * swingCorrectionFactor * limbSwingAmount;

        this.rightThigh.xRot += (float) ((Math.toRadians(45) - Mth.cos((float) Math.toRadians(-25) + limbSwing)) * (swingCorrectionFactor * 6.5F) * limbSwingAmount);
        this.rightThigh.y += -2 * (swingCorrectionFactor * 7) * limbSwingAmount;
        this.rightThigh.z += Mth.cos((float) Math.toRadians(-35) + limbSwing) * (swingCorrectionFactor * 0.1F) * limbSwingAmount;

        this.rightFoot.xRot += (float) ((Math.toRadians(15) + Mth.cos((float) Math.toRadians(-85) + limbSwing)) * (swingCorrectionFactor * 6.0F) * limbSwingAmount);
        this.rightFoot.y += -1 * (swingCorrectionFactor * 2.0F) * limbSwingAmount;
        this.rightFoot.z += (-0.5F + Mth.cos(limbSwing)) * (swingCorrectionFactor * 0.025F) * limbSwingAmount;

        this.tail.xRot += (float) ((Math.toRadians(-40) - Mth.cos((float) Math.toRadians(-120) + limbSwing)) * (swingCorrectionFactor * 1.8F) * limbSwingAmount);
        this.tail.z += Mth.cos((float) Math.toRadians(-100) + limbSwing) * (swingCorrectionFactor * 0.03F) * limbSwingAmount;
    }

    @Override
    protected @NotNull Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected @NotNull Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body);
    }
}