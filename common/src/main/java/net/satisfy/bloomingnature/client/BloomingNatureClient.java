package net.satisfy.bloomingnature.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;
import net.satisfy.bloomingnature.client.model.WanderingGardenerModel;
import net.satisfy.bloomingnature.client.renderer.block.*;
import net.satisfy.bloomingnature.client.renderer.entity.ModBoatRenderer;
import net.satisfy.bloomingnature.client.renderer.entity.WanderingGardenerRenderer;
import net.satisfy.bloomingnature.core.registry.EntityTypeRegistry;

import static net.satisfy.bloomingnature.core.registry.ObjectRegistry.*;

@Environment(EnvType.CLIENT)
public class BloomingNatureClient {
    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(), PEBBLES.get(), TWIGS.get(), CARDINAL.get(), MOUNTAIN_LAUREL.get(), JOE_PYE.get(), HYSSOP.get(), MOUNTAIN_SNOWBELL.get(), CARDINAL.get(), BIRD_OF_PARADISE.get(), WHITE_ORCHID.get(), POTTED_MOUNTAIN_LAUREL.get(), POTTED_JOE_PYE.get(), POTTED_HYSSOP.get(), POTTED_MOUNTAIN_SNOWBELL.get(), POTTED_WHITE_ORCHID.get(), POTTED_BIRD_OF_PARADISE.get(), BEGONIE.get(), GENISTEAE.get(), GOATSBEARD.get(), BLUEBELL.get(), DAPHNE.get(), BOTTLEBRUSHES.get(), FOXGLOVE_WHITE.get(), FOXGLOVE_PINK.get(), FREESIA_YELLOW.get(), FREESIA_PINK.get(), LUPINE_BLUE.get(), LUPINE_PURPLE.get(), LARCH_DOOR.get(), POTTED_BEGONIE.get(), POTTED_GENISTEAE.get(), POTTED_GOATSBEARD.get(), POTTED_BLUEBELL.get(), POTTED_DAPHNE.get(), POTTED_BOTTLEBRUSHES.get(), POTTED_FOXGLOVE_WHITE.get(), POTTED_FOXGLOVE_PINK.get(), POTTED_FREESIA_YELLOW.get(), POTTED_FREESIA_PINK.get(), POTTED_LUPINE_BLUE.get(), POTTED_LUPINE_PURPLE.get(), POTTED_LARCH_SAPLING.get(), LARCH_SAPLING.get(), SWAMP_OAK_TRAPDOOR.get(), SWAMP_OAK_WINDOW.get(), SWAMP_OAK_DOOR.get(), SWAMP_OAK_SAPLING.get(), LARCH_WINDOW.get(), TALL_MOUNTAIN_LAUREL.get(), TALL_LUPINE_BLUE.get(), TALL_LUPINE_PURPLE.get(), DRY_BUSH.get(), DRY_BUSH_TALL.get(), DRY_GRASS.get(), GOLDEN_ROD.get(), WILD_SUNFLOWER.get(), FAN_PALM_SPROUT.get(), FAN_PALM_DOOR.get(), FAN_PALM_TRAPDOOR.get(), FAN_PALM_WINDOW.get(), CATTAIL.get(), REED.get(), POTTED_LARCH_SAPLING.get(), POTTED_FIR_SAPLING.get(), POTTED_SWAMP_CYPRESS_SAPLING.get(), POTTED_SWAMP_OAK_SAPLING.get(), POTTED_FAN_PALM_SPROUT.get(), POTTED_ASPEN_SAPLING.get(), POTTED_BAOBAB_SAPLING.get(), POTTED_GOLDEN_ROD.get(), POTTED_DRY_BUSH.get(), BAOBAB_WINDOW.get(), ASPEN_WINDOW.get(), SWAMP_CYPRESS_WINDOW.get(), FIR_WINDOW.get(), BAOBAB_DOOR.get(), ASPEN_DOOR.get(), SWAMP_CYPRESS_DOOR.get(), ASPEN_TRAPDOOR.get(), SWAMP_CYPRESS_TRAPDOOR.get(), BAOBAB_SAPLING.get(), ASPEN_SAPLING.get(), SWAMP_CYPRESS_SAPLING.get(), FIR_SAPLING.get(), CHESTNUT_DOOR.get(), CHESTNUT_SAPLING.get(), CHESTNUT_WINDOW.get(), CHESTNUT_TRAPDOOR.get(), EBONY_WINDOW.get(), EBONY_DOOR.get(), EBONY_SAPLING.get(), POTTED_EBONY_SAPLING.get(), EBONY_TRAPDOOR.get(), MOSSY_LATERIT.get(), FIR_LEAVES.get(), FLOATING_LEAVES.get(), POTTED_CHESTNUT_SAPLING.get(), SUNGRASS.get(), TALL_SUNGRASS.get(), FORSYTHIA.get(), POTTED_FORSYTHIA.get(), MOSSGRASS.get(), GLADIOLUS.get(), POTTED_GLADIOLUS.get(), AMARYLLIS.get(), POTTED_AMARYLLIS.get(), ANEMONE.get(), POTTED_ANEMONE.get(), RED_OAT_GRASS.get(), TALL_RED_OAT_GRASS.get(), SILKGRASS.get(), TALL_SILKGRASS.get(), SMALL_CACTUS.get(), PRICKLY_PEAR_CACTUS.get(), BARREL_CACTUS.get(), POTTED_BARREL_CACTUS.get(), POTTED_PRICKLY_PEAR_CACTUS.get(), CACTUS_DOOR.get(), CACTUS_WINDOW.get(), CACTUS_TRAPDOOR.get(), BLUFF_GRASS.get(), TALL_BLUFF_GRASS.get(), MUNSTEAD.get(), POTTED_MUNSTEAD.get(), DESERT_LILY.get(), POTTED_DESERT_LILY.get(), MYOSOTIS.get(), POTTED_MYOSOTIS.get(), SUNFLOWER.get());

        RenderTypeRegistry.register(RenderType.translucent(), CYPRESS_WINDOW.get(), SWAMP_OAK_WINDOW.get(), SWAMP_OAK_DOOR.get(), SWAMP_OAK_TRAPDOOR.get());

        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.get(0.5, 1.0), MOSSY_LATERIT.get(), SUNGRASS.get(), TALL_SUNGRASS.get(), BLOOMING_OAK_LEAVES.get());
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return -1;
            }
            return BiomeColors.getAverageFoliageColor(world, pos);
        }, BLOOMING_OAK_LEAVES.get());
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return -1;
            }
            return BiomeColors.getAverageGrassColor(world, pos);
        }, SUNGRASS.get(), TALL_SUNGRASS.get());

        registerBlockEntityRenderer();
    }

    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayer();
    }

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityTypeRegistry.WANDERING_GARDENER, WanderingGardenerRenderer::new);
        EntityRendererRegistry.register(EntityTypeRegistry.MOD_BOAT, context -> new ModBoatRenderer<>(context, false));
        EntityRendererRegistry.register(EntityTypeRegistry.MOD_CHEST_BOAT, context -> new ModBoatRenderer<>(context, true));
    }

    public static void registerEntityModelLayer() {
        EntityModelLayerRegistry.register(WanderingGardenerModel.LAYER_LOCATION, WanderingGardenerModel::getTexturedModelData);
        EntityModelLayerRegistry.register(CompletionistBannerRenderer.LAYER_LOCATION, CompletionistBannerRenderer::createBodyLayer);
    }

    public static void registerBlockEntityRenderer() {
        BlockEntityRendererRegistry.register(EntityTypeRegistry.MOD_SIGN.get(), ModSignRenderer::new);
        BlockEntityRendererRegistry.register(EntityTypeRegistry.MOD_HANGING_SIGN.get(), ModHangingSignRenderer::new);
        BlockEntityRendererRegistry.register(EntityTypeRegistry.BLOOMINGNATURE_BANNER.get(), CompletionistBannerRenderer::new);
    }
}

