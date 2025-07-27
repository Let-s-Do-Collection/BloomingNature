package satisfy.bloomingnature.client.model;


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
import org.jetbrains.annotations.NotNull;
import satisfy.bloomingnature.entity.RaccoonEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

@Environment(EnvType.CLIENT)
public class RaccoonModel<T extends RaccoonEntity> extends HierarchicalModel<T> {
        public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BloomingNatureIdentifier.of("raccoon"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart tail;
    private float legMotionPos;


    public RaccoonModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftHindLeg = root.getChild("leftHindLeg");
        this.rightHindLeg = root.getChild("rightHindLeg");
        this.leftFrontLeg = root.getChild("leftFrontLeg");
        this.rightFrontLeg = root.getChild("rightFrontLeg");
        this.tail = root.getChild("tail");
    }

    @SuppressWarnings("unused")
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 13).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F, -6.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-4.0F, -3.0F, -2.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(2.0F, -3.0F, -2.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 13).addBox(-2.0F, 3.0F, -9.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -3.0F));

        PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("leftHindLeg", CubeListBuilder.create().texOffs(8, 30).addBox(-0.005F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 18.0F, 6.0F));

        PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("rightHindLeg", CubeListBuilder.create().texOffs(0, 30).addBox(0.005F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 18.0F, 6.0F));

        PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(8, 30).addBox(-0.005F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 18.0F, -1.0F));

        PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(0, 30).addBox(0.005F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 18.0F, -1.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(22, 24).addBox(-3.0F, 1.0F, -4.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 7.0F, 1.5708F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    public void prepareMobModel(T raccoon, float f, float g, float h) {
        this.rightHindLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.leftHindLeg.xRot = Mth.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.rightFrontLeg.xRot = Mth.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.leftFrontLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.rightHindLeg.visible = true;
        this.leftHindLeg.visible = true;
        this.rightFrontLeg.visible = true;
        this.leftFrontLeg.visible = true;
         if (raccoon.isSleeping()) {
             this.body.zRot = -1.5707964F;
             this.body.setPos(0.0F, 21.0F, -6.0F);
             this.tail.xRot = -2.6179938F;
             if (this.young) {
                 this.tail.xRot = -2.1816616F;
                 this.body.setPos(0.0F, 21.0F, -2.0F);
             }

             this.head.setPos(1.0F, 19.49F, -3.0F);
             this.head.xRot = 0.0F;
             this.head.yRot = -2.0943952F;
             this.head.zRot = 0.0F;
             this.rightHindLeg.visible = false;
             this.leftHindLeg.visible = false;
             this.rightFrontLeg.visible = false;
             this.leftFrontLeg.visible = false;
        }
    }

    public void setupAnim(T raccoon, float f, float g, float h, float i, float j) {
        if (!raccoon.isSleeping() && !raccoon.isFaceplanted() && !raccoon.isCrouching()) {
            this.head.xRot = j * 0.017453292F;
            this.head.yRot = i * 0.017453292F;
            this.tail.xRot = h;
            this.tail.yRot = Mth.cos(f * 0.6662F) * 1.4F * g;
            this.tail.setPos(0.0F, 18.0F, 7.0F);

        }
        if (raccoon.isSleeping()) {
            this.head.xRot = 0.0F;
            this.head.yRot = -2.0943952F;
            this.head.zRot = Mth.cos(h * 0.027F) / 22.0F;
        }

        float k;
        if (raccoon.isCrouching()) {
            k = Mth.cos(h) * 0.01F;
            this.body.yRot = k;
            this.rightHindLeg.zRot = k;
            this.leftHindLeg.zRot = k;
            this.rightFrontLeg.zRot = k / 2.0F;
            this.leftFrontLeg.zRot = k / 2.0F;
        }

        if (raccoon.isFaceplanted()) {
            this.legMotionPos += 0.67F;
            this.rightHindLeg.xRot = Mth.cos(this.legMotionPos * 0.4662F) * 0.1F;
            this.leftHindLeg.xRot = Mth.cos(this.legMotionPos * 0.4662F + 3.1415927F) * 0.1F;
            this.rightFrontLeg.xRot = Mth.cos(this.legMotionPos * 0.4662F + 3.1415927F) * 0.1F;
            this.leftFrontLeg.xRot = Mth.cos(this.legMotionPos * 0.4662F) * 0.1F;
        }
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int red) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red);
        leftHindLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red);
        rightHindLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red);
        leftFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red);
        rightFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red);
    }

    @Override
    public @NotNull ModelPart root() {
        return body;
    }
}