package satisfy.bloomingnature.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import satisfy.bloomingnature.entity.OwlEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class OwlModel extends AgeableListModel<OwlEntity> implements HeadedModel {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BloomingNatureIdentifier.of("owl"), "main");
    private final ModelPart leg_right;
    private final ModelPart leg_left;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart wing_right;
    private final ModelPart wing_left;

    public OwlModel(ModelPart root) {
        super(true, 14, 0, 2.0F, 2.0F, 24.0F);

        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.wing_right = body.getChild("wing_right");
        this.wing_left = body.getChild("wing_left");
        this.leg_right = body.getChild("leg_right");
        this.leg_left = body.getChild("leg_left");
    }


    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 16.8532F, -0.6407F));

        PartDefinition torso_r1 = body.addOrReplaceChild("torso_r1", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-3.5F, -2.475F, -3.75F, 7.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(5, 38).addBox(-7.059F, 4.0F, 3.3F, 7.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.559F, 3.2079F, -2.2761F, 0.7854F, 0.0F, 0.0F));

        PartDefinition tailWing = body.addOrReplaceChild("tailWing", CubeListBuilder.create(), PartPose.offset(0.0F, 0.6761F, -1.1119F));

        PartDefinition cube_r2 = tailWing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-3.5F, -2.5858F, 0.2071F, 7.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.4225F, 2.1273F, 0.7854F, 0.0F, 0.0F));

        PartDefinition wing_right = body.addOrReplaceChild("wing_right", CubeListBuilder.create(), PartPose.offset(3.501F, -0.9282F, -2.3335F));

        PartDefinition wing_right_r1 = wing_right.addOrReplaceChild("wing_right_r1", CubeListBuilder.create().texOffs(14, 9).mirror().addBox(-0.025F, -0.3609F, -2.4857F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.074F, 0.2394F, 0.1449F, 0.7854F, 0.0F, 0.0F));

        PartDefinition wing_left = body.addOrReplaceChild("wing_left", CubeListBuilder.create(), PartPose.offset(-3.501F, -0.9282F, -2.3335F));

        PartDefinition wing_left_r1 = wing_left.addOrReplaceChild("wing_left_r1", CubeListBuilder.create().texOffs(14, 9).addBox(0.025F, -0.3609F, -2.4857F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.074F, 0.2394F, 0.1449F, 0.7854F, 0.0F, 0.0F));

        PartDefinition leg_right = body.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(23, 4).addBox(-1.975F, 2.6133F, -2.7904F, 3.95F, 0.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(27, 4).addBox(-0.975F, 0.65F, -0.6F, 1.95F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(20, 0).mirror().addBox(-1.0F, -0.7354F, -1.0251F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 4.4468F, 0.2407F));

        PartDefinition leg_left = body.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(23, 4).mirror().addBox(-1.975F, 2.6133F, -2.7904F, 3.95F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(27, 4).addBox(-0.975F, 0.65F, -0.6F, 1.95F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(20, 0).addBox(-1.0F, -0.7354F, -1.0251F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 4.4468F, 0.2407F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.525F, -4.25F, -3.0833F, 7.05F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(6, 11).addBox(-3.525F, 0.75F, -3.0833F, 7.05F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).mirror().addBox(-0.5F, -1.25F, -4.0833F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 15.2517F, -2.269F));

        PartDefinition right_horn_r1 = head.addOrReplaceChild("right_horn_r1", CubeListBuilder.create().texOffs(0, -1).mirror().addBox(-1.25F, -2.0F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.4089F, -3.3661F, -2.0833F, 0.0F, 0.0F, 0.7854F));

        PartDefinition left_horn_r1 = head.addOrReplaceChild("left_horn_r1", CubeListBuilder.create().texOffs(0, 1).addBox(1.25F, -2.0F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.4089F, -3.3661F, -2.0833F, 0.0F, 0.0F, -0.7854F));

        return LayerDefinition.create(meshdefinition, 48, 48);
    }

    // Animations by Lemonszz: https://github.com/Lemonszz/Biome-Makeover/blob/1.20/LICENCE
    @Override
    public void setupAnim(OwlEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float pi = (float) Math.PI;

        if (entity.getStandingState() != OwlEntity.StandingState.FLYING) {
            this.head.xRot = -0.2618F + (headPitch * 0.0175F);
            this.head.yRot = netHeadYaw * 0.0175F;

            this.wing_left.zRot = 0;
            this.wing_right.zRot = 0;

            this.leg_right.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.leg_left.xRot = Mth.cos(limbSwing * 0.6662F + pi) * 1.4F * limbSwingAmount;

            float wingSwingAmountNonFlying = 0.5F;

            this.wing_right.yRot = Mth.cos(limbSwing * 0.6662F) * wingSwingAmountNonFlying;
            this.wing_left.yRot = -Mth.cos(limbSwing * 0.6662F + pi) * wingSwingAmountNonFlying;

            if (entity.isInSittingPose()) {
                leg_right.xRot = -1.5F;
                leg_left.xRot = 1.5F;
            }
        } else {
            float wingSwingAmountFlying = 1.0F;

            this.wing_left.yRot = Mth.cos(limbSwing / 2F) * wingSwingAmountFlying;
            this.wing_right.yRot = -Mth.cos(limbSwing / 2F) * wingSwingAmountFlying;

            this.wing_left.zRot = -(Mth.sin(limbSwing / 2F) / 4F);
            this.wing_right.zRot = (Mth.sin(limbSwing / 2F) / 4F);

            this.leg_right.xRot = Mth.cos(limbSwing * 0.6662F) * 1F * limbSwingAmount;
            this.leg_left.xRot = Mth.cos(limbSwing * 0.6662F + pi) * 1F * limbSwingAmount;
        }
    }



    @Override
    public void prepareMobModel(OwlEntity entity, float limbAngle, float limbDistance, float delta) {
        super.prepareMobModel(entity, limbAngle, limbDistance, delta);

        switch(entity.getStandingState())
        {
            case STANDING:

                break;
            case FLYING:

                break;
        }

        float rad90 = 1.5708F;
        float rad40 = 0.698132F;

        float leanProgress = entity.getSwimAmount(delta) / 7F;
        setRotationAngle(leg_left, rad90 * leanProgress, 0, 0);
        setRotationAngle(leg_right, rad90 * leanProgress, 0, 0);

        setRotationAngle(head, -rad40 * leanProgress, 0, 0);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(body);
    }

    public void setRotationAngle(ModelPart part, float x, float y, float z)
    {
        part.xRot = x;
        part.yRot = y;
        part.zRot = z;
    }

    @Override
    public ModelPart getHead() {
        return head;
    }
}