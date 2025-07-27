package satisfy.bloomingnature.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import satisfy.bloomingnature.registry.EntityRegistry;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class MossySheepEntity extends SheepEntity {
    public MossySheepEntity(EntityType<? extends Sheep> entityType, Level world) {
        super(entityType, world, Blocks.MOSS_BLOCK, ResourceKey.create(Registries.LOOT_TABLE, BloomingNatureIdentifier.of( "entities/mossy_sheep")));
    }

    @Override
    public Sheep getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return EntityRegistry.MOSSY_SHEEP.get().create(serverWorld);
    }
}