package satisfy.bloomingnature.block;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import satisfy.bloomingnature.entity.TermiteEntity; // Import your TermiteEntity class
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import satisfy.bloomingnature.registry.EntityRegistry;

public class TermiteBlock extends Block {
    private final Block hostBlock;
    private static final Map<Block, Block> BLOCK_BY_HOST_BLOCK = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> HOST_TO_INFESTED_STATES = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> INFESTED_TO_HOST_STATES = Maps.newIdentityHashMap();

    public TermiteBlock(Block block, BlockBehaviour.Properties properties) {
        super(properties.destroyTime(block.defaultDestroyTime() / 2.0F).explosionResistance(0.75F));
        this.hostBlock = block;
        BLOCK_BY_HOST_BLOCK.put(block, this);
    }

    public Block getHostBlock() {
        return this.hostBlock;
    }

    public static boolean isCompatibleHostBlock(BlockState blockState) {
        return BLOCK_BY_HOST_BLOCK.containsKey(blockState.getBlock());
    }

    private void spawnInfestation(ServerLevel serverLevel, BlockPos blockPos) {
        TermiteEntity termiteEntity = new TermiteEntity(EntityRegistry.TERMITE.get(), serverLevel);
        if (termiteEntity != null) {
            termiteEntity.moveTo((double)blockPos.getX() + 0.4, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5, 0.0F, 0.0F);
            serverLevel.addFreshEntity(termiteEntity);
            termiteEntity.spawnAnim();
        }
    }

    public void spawnAfterBreak(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, ItemStack itemStack, boolean bl) {
        super.spawnAfterBreak(blockState, serverLevel, blockPos, itemStack, bl);

        if (serverLevel.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) &&
                EnchantmentHelper.getItemEnchantmentLevel(serverLevel.getServer().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), itemStack) == 0) {

            if (serverLevel.random.nextFloat() < 0.5F) {
                int numTermites = serverLevel.random.nextInt(2) + 3;

                for (int i = 0; i < numTermites; i++) {
                    spawnInfestation(serverLevel, blockPos);
                }
            }
        }
    }


    public static BlockState infestedStateByHost(BlockState blockState) {
        return getNewStateWithProperties(HOST_TO_INFESTED_STATES, blockState, () -> {
            return ((Block)BLOCK_BY_HOST_BLOCK.get(blockState.getBlock())).defaultBlockState();
        });
    }

    public BlockState hostStateByInfested(BlockState blockState) {
        return getNewStateWithProperties(INFESTED_TO_HOST_STATES, blockState, () -> {
            return this.getHostBlock().defaultBlockState();
        });
    }

    private static BlockState getNewStateWithProperties(Map<BlockState, BlockState> map, BlockState blockState, Supplier<BlockState> supplier) {
        return (BlockState)map.computeIfAbsent(blockState, (blockStatex) -> {
            BlockState blockState2 = (BlockState)supplier.get();

            Property property;
            for(Iterator var3 = blockStatex.getProperties().iterator(); var3.hasNext(); blockState2 = blockState2.hasProperty(property) ? (BlockState)blockState2.setValue(property, blockStatex.getValue(property)) : blockState2) {
                property = (Property)var3.next();
            }

            return blockState2;
        });
    }
}
