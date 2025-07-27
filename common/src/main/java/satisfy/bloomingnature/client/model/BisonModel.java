package satisfy.bloomingnature.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class BisonModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(BloomingNatureIdentifier.of("bison"), "main");
    private final ModelPart legbackleft;
    private final ModelPart legbackright;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart legfrontleft;
    private final ModelPart legfrontright;

    public BisonModel(ModelPart root) {
        this.legbackleft = root.getChild("legbackleft");
        this.legbackright = root.getChild("legbackright");
        this.body = root.getChild("body");
        this.head = body.getChild("head");
        this.legfrontleft = root.getChild("legfrontleft");
        this.legfrontright = root.getChild("legfrontright");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition legbackleft = partdefinition.addOrReplaceChild("legbackleft", CubeListBuilder.create().texOffs(111, 98).addBox(-3.5F, -3.0F, -3.5F, 7.0F, 18.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.6F, 9.0F, 12.5F));

        PartDefinition legbackright = partdefinition.addOrReplaceChild("legbackright", CubeListBuilder.create().texOffs(82, 98).addBox(-3.5F, -3.0F, -3.5F, 7.0F, 18.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.6F, 9.0F, 12.5F));

        PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 46).addBox(-8.5F, -10.88F, -11.15F, 17.0F, 22.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(56, 69).addBox(-8.5F, 11.12F, -11.15F, 17.0F, 7.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-9.5F, -11.88F, -11.4F, 19.0F, 23.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(64, 27).addBox(-7.5F, -8.98F, 7.85F, 15.0F, 20.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(0, 90).addBox(0.0F, 9.12F, -11.15F, 0.0F, 11.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.12F, -7.85F));

        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(65, 120).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 7).addBox(-1.5F, -1.5F, 9.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.5F, -1.5F, 12.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.88F, 25.85F, -0.9599F, 0.0F, 0.0F));

        PartDefinition Head = Body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(83, 0).addBox(-5.5F, -0.9677F, -6.4743F, 11.0F, 13.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(114, 26).addBox(-3.5F, 12.0323F, -2.4743F, 7.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(92, 124).addBox(-0.1F, -0.9677F, 5.5257F, 0.0F, 16.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(43, 98).addBox(-6.5F, -1.9677F, -11.4743F, 13.0F, 15.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(112, 67).addBox(-6.5F, -1.9677F, -5.4743F, 13.0F, 15.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(22, 90).addBox(-9.5F, 3.346F, -13.0429F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-6.5F, 3.346F, -6.0429F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(22, 90).mirror().addBox(6.5F, 3.346F, -13.0429F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 46).mirror().addBox(4.5F, 3.346F, -6.0429F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.12F, -10.15F, -1.2217F, 0.0F, 0.0F));

        PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-0.5F, -1.7498F, -0.4891F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 14).addBox(-15.5F, -1.7498F, -0.4891F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 7.6632F, -2.7214F, 1.6144F, 0.0F, 0.0F));

        PartDefinition legfrontleft = partdefinition.addOrReplaceChild("legfrontleft", CubeListBuilder.create().texOffs(0, 123).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 18.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 9.5F, -12.5F));

        PartDefinition legfrontright = partdefinition.addOrReplaceChild("legfrontright", CubeListBuilder.create().texOffs(36, 120).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 18.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 9.5F, -12.5F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    public void setupAnim(T entity, float f, float g, float h, float i, float j) {
        this.head.yRot = i * 0.017453292F;

        float l = 1.0F;

        this.head.y = 2.0F;

        this.legfrontright.xRot = Mth.cos(f) * 1.2F * g;
        this.legfrontleft.xRot = Mth.cos(f + 3.1415927F) * 1.2F * g;
        this.legbackright.xRot = this.legfrontleft.xRot;
        this.legbackleft.xRot = this.legfrontright.xRot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int k) {
        legbackleft.render(poseStack, vertexConsumer, i, j, k);
        legbackright.render(poseStack, vertexConsumer, i, j, k);
        body.render(poseStack, vertexConsumer, i, j, k);
        legfrontleft.render(poseStack, vertexConsumer, i, j, k);
        legfrontright.render(poseStack, vertexConsumer, i, j, k);
    }

    @Override
    public @NotNull ModelPart root() {
        return body;
    }
}