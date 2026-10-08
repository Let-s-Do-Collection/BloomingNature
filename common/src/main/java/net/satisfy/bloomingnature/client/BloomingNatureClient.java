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
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.network.chat.Component;
import net.satisfy.bloomingnature.core.registry.FloraRegistry;
import net.satisfy.bloomingnature.core.registry.TabRegistry;
import net.satisfy.foundation.client.creative.CreativeSideTabs;
import net.satisfy.bloomingnature.client.model.WanderingGardenerModel;
import net.satisfy.foundation.ambient.FireflyAmbience;
import net.satisfy.foundation.banner.CompletionistBannerRenderer;
import net.satisfy.foundation.client.wood.WoodBoatRenderer;
import net.satisfy.foundation.client.wood.WoodClient;
import net.satisfy.foundation.wood.BoatWood;
import net.satisfy.bloomingnature.core.config.BloomingNatureConfig;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;
import net.satisfy.bloomingnature.core.registry.WoodSet;
import net.satisfy.bloomingnature.core.util.BloomingNatureWoodType;
import net.satisfy.bloomingnature.client.renderer.entity.WanderingGardenerRenderer;
import net.satisfy.bloomingnature.core.registry.EntityTypeRegistry;

import static net.satisfy.bloomingnature.core.registry.ObjectRegistry.*;

@Environment(EnvType.CLIENT)
public class BloomingNatureClient {
    public static void initClient() {
        CreativeSideTabs.register(TabRegistry.BLOOMINGNATURE_TAB.getKey(),
                CreativeSideTabs.SideTab.of(Component.translatable("creativetab.bloomingnature.side.timber"), ObjectRegistry.LARCH.log.get(), TabRegistry::acceptTimber),
                CreativeSideTabs.SideTab.of(Component.translatable("creativetab.bloomingnature.side.stonework"), TRAVERTIN_BRICKS.get(), TabRegistry::acceptStone),
                CreativeSideTabs.SideTab.of(Component.translatable("creativetab.bloomingnature.side.meadow"), BLUEBELL.get(), TabRegistry::acceptMeadow));
        RenderTypeRegistry.register(RenderType.cutout(), PEBBLES.get(), TWIGS.get(), CARDINAL.get(), MOUNTAIN_LAUREL.get(), JOE_PYE.get(), HYSSOP.get(), MOUNTAIN_SNOWBELL.get(), CARDINAL.get(), BIRD_OF_PARADISE.get(), WHITE_ORCHID.get(), POTTED_MOUNTAIN_LAUREL.get(), POTTED_JOE_PYE.get(), POTTED_HYSSOP.get(), POTTED_MOUNTAIN_SNOWBELL.get(), POTTED_WHITE_ORCHID.get(), POTTED_BIRD_OF_PARADISE.get(), BEGONIE.get(), GENISTEAE.get(), GOATSBEARD.get(), BLUEBELL.get(), DAPHNE.get(), BOTTLEBRUSHES.get(), FOXGLOVE_WHITE.get(), FOXGLOVE_PINK.get(), FREESIA_YELLOW.get(), FREESIA_PINK.get(), LUPINE_BLUE.get(), LUPINE_PURPLE.get(), POTTED_BEGONIE.get(), POTTED_GENISTEAE.get(), POTTED_GOATSBEARD.get(), POTTED_BLUEBELL.get(), POTTED_DAPHNE.get(), POTTED_BOTTLEBRUSHES.get(), POTTED_FOXGLOVE_WHITE.get(), POTTED_FOXGLOVE_PINK.get(), POTTED_FREESIA_YELLOW.get(), POTTED_FREESIA_PINK.get(), POTTED_LUPINE_BLUE.get(), POTTED_LUPINE_PURPLE.get(), TALL_MOUNTAIN_LAUREL.get(), TALL_LUPINE_BLUE.get(), TALL_LUPINE_PURPLE.get(), DRY_BUSH.get(), DRY_BUSH_TALL.get(), DRY_GRASS.get(), GOLDEN_ROD.get(), WILD_SUNFLOWER.get(), CATTAIL.get(), REED.get(), POTTED_GOLDEN_ROD.get(), POTTED_DRY_BUSH.get(), ObjectRegistry.FIR.leaves.get(), FLOATING_LEAVES.get(), SUNGRASS.get(), TALL_SUNGRASS.get(), FORSYTHIA.get(), POTTED_FORSYTHIA.get(), MOSSGRASS.get(), GLADIOLUS.get(), POTTED_GLADIOLUS.get(), AMARYLLIS.get(), POTTED_AMARYLLIS.get(), ANEMONE.get(), POTTED_ANEMONE.get(), RED_OAT_GRASS.get(), TALL_RED_OAT_GRASS.get(), SILKGRASS.get(), TALL_SILKGRASS.get(), PAMPAS_GRASS.get(), SMALL_CACTUS.get(), PRICKLY_PEAR_CACTUS.get(), BARREL_CACTUS.get(), POTTED_BARREL_CACTUS.get(), POTTED_PRICKLY_PEAR_CACTUS.get(), BLUFF_GRASS.get(), TALL_BLUFF_GRASS.get(), MUNSTEAD.get(), POTTED_MUNSTEAD.get(), DESERT_LILY.get(), POTTED_DESERT_LILY.get(), MYOSOTIS.get(), POTTED_MYOSOTIS.get(), SUNFLOWER.get(), POTTED_BLOOMING_OAK_SAPLING.get(), BLOOMING_OAK_SAPLING.get());

        for (WoodSet set : WoodSet.all()) {
            RenderTypeRegistry.register(RenderType.cutout(), set.door.get(), set.trapdoor.get(), set.window.get());
            if (set.sapling != null) {
                RenderTypeRegistry.register(RenderType.cutout(), set.sapling.get(), set.pottedSapling.get());
            }
        }
        RenderTypeRegistry.register(RenderType.translucent(), ObjectRegistry.CYPRESS.window.get(), ObjectRegistry.SWAMP_OAK.window.get(), ObjectRegistry.SWAMP_OAK.door.get(), ObjectRegistry.SWAMP_OAK.trapdoor.get());

        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.get(0.5, 1.0), SUNGRASS.get(), TALL_SUNGRASS.get(), BLOOMING_OAK_LEAVES.get());
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
            return OasisGrassColor.get(world, pos);
        }, SUNGRASS.get(), TALL_SUNGRASS.get());
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> world == null || pos == null ? GrassColor.getDefaultColor() : OasisGrassColor.get(world, pos), Blocks.GRASS_BLOCK, Blocks.SHORT_GRASS, Blocks.TALL_GRASS, Blocks.FERN, Blocks.LARGE_FERN);

        RenderTypeRegistry.register(RenderType.cutout(), FloraRegistry.JUNGLE_FERN.get(), FloraRegistry.POTTED_JUNGLE_FERN.get(), FloraRegistry.TALL_JUNGLE_FERN.get(), FloraRegistry.WILD_VINES.get(), FloraRegistry.FLOWERING_LILY_PAD.get());
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> world == null || pos == null ? GrassColor.getDefaultColor() : BiomeColors.getAverageGrassColor(world, pos), FloraRegistry.JUNGLE_FERN.get(), FloraRegistry.POTTED_JUNGLE_FERN.get(), FloraRegistry.TALL_JUNGLE_FERN.get());
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> tintIndex != 0 ? -1 : world == null || pos == null ? FoliageColor.getDefaultColor() : BiomeColors.getAverageFoliageColor(world, pos), FloraRegistry.FLOWERING_LILY_PAD.get());
        FireflyAmbience.init(() -> BloomingNatureConfig.firefliesEnabled);
        FallingLeaves.init();
        MistFog.init();

        registerBlockEntityRenderer();
    }

    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayer();
    }

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityTypeRegistry.WANDERING_GARDENER, WanderingGardenerRenderer::new);
        EntityRendererRegistry.register(EntityTypeRegistry.MOD_BOAT, context -> new WoodBoatRenderer(context, false));
        EntityRendererRegistry.register(EntityTypeRegistry.MOD_CHEST_BOAT, context -> new WoodBoatRenderer(context, true));
    }

    public static void registerEntityModelLayer() {
        EntityModelLayerRegistry.register(WanderingGardenerModel.LAYER_LOCATION, WanderingGardenerModel::getTexturedModelData);
        WoodClient.registerBoatLayers(WoodSet.all().stream().map(set -> set.boatWood).toArray(BoatWood[]::new));
    }

    public static void registerBlockEntityRenderer() {
        WoodClient.registerSignMaterials(BloomingNatureWoodType.ASPEN, BloomingNatureWoodType.BAOBAB, BloomingNatureWoodType.LARCH, BloomingNatureWoodType.EBONY, BloomingNatureWoodType.CHESTNUT, BloomingNatureWoodType.SWAMP_OAK, BloomingNatureWoodType.SWAMP_CYPRESS, BloomingNatureWoodType.FAN_PALM, BloomingNatureWoodType.FIR, BloomingNatureWoodType.CACTUS, BloomingNatureWoodType.CYPRESS);
        WoodClient.registerSignRenderers(EntityTypeRegistry.MOD_SIGN.get(), EntityTypeRegistry.MOD_HANGING_SIGN.get());
        BlockEntityRendererRegistry.register(EntityTypeRegistry.BLOOMINGNATURE_BANNER.get(), CompletionistBannerRenderer::new);
    }
}

