package satisfy.bloomingnature.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;


public class SinkInSandBlock extends SinkInBlock {
    public SinkInSandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getInBlockState().is(this)) {
            entity.makeStuckInBlock(blockState, new Vec3(0.8999999761581421, 1.5, 0.8999999761581421));
            if (level.isClientSide) {
                RandomSource randomSource = level.getRandom();
                boolean bl = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
                if (bl && randomSource.nextBoolean()) {
                    level.addParticle(ParticleTypes.CRIT, entity.getX(), (double) (blockPos.getY() + 1), entity.getZ(), (double) (Mth.randomBetween(randomSource, -1.0F, 1.0F) * 0.083333336F), 0.05000000074505806, (double) (Mth.randomBetween(randomSource, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }
        if (entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().inWall(), 1.f);
        }

        if (!level.isClientSide) {
            if (entity.isOnFire() && (level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) || entity instanceof Player) && entity.mayInteract(level, blockPos)) {
                level.destroyBlock(blockPos, false);
            }

            entity.setSharedFlagOnFire(false);
        }
    }
}