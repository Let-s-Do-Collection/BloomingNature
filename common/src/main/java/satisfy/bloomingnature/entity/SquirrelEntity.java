package satisfy.bloomingnature.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import satisfy.bloomingnature.registry.EntityRegistry;
import satisfy.bloomingnature.registry.SoundRegistry;
import satisfy.bloomingnature.registry.TagsRegistry;
import satisfy.bloomingnature.entity.ai.BetterWallClimberNavigation;


import java.util.EnumSet;
import java.util.List;

// Squirrel by SameDifferent: https://github.com/samedifferent/Ecologics/blob/1.19-crossplatform/LICENSE
public class SquirrelEntity extends Animal {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(SquirrelEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> DATA_TRUSTING = SynchedEntityData.defineId(SquirrelEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient TEMPT_INGREDIENT = Ingredient.of(TagsRegistry.SQUIRREL_HOLDABLE);

    public SquirrelEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.setCanPickUpLoot(true);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.4F);
    }

    @Override
    public int getMaxHeadYRot() {
        return 30;
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, TEMPT_INGREDIENT, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new SquirrelSearchForSaplingsGoal(this));
        this.goalSelector.addGoal(6, new SquirrelPlantSaplingGoal(this, 1.0F, 8, 4));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
        builder.define(DATA_TRUSTING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Trusting", this.isTrusting());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setTrusting(pCompound.getBoolean("Trusting"));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pMob) {
        return EntityRegistry.SQUIRREL.get().create(pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.HONEYCOMB);
    }

    boolean isTrusting() {
        return this.entityData.get(DATA_TRUSTING);
    }

    private void setTrusting(boolean pTrusting) {
        this.entityData.set(DATA_TRUSTING, pTrusting);
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!this.isTrusting() && TEMPT_INGREDIENT.test(itemstack)) {
            this.usePlayerItem(pPlayer, pHand, itemstack);
            if (!this.level().isClientSide()) {
                if (this.random.nextInt(3) == 0) {
                    this.setTrusting(true);
                    this.spawnTrustingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte)41);
                } else {
                    this.spawnTrustingParticles(false);
                    this.level().broadcastEntityEvent(this, (byte)40);
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 41) {
            this.spawnTrustingParticles(true);
        } else if (pId == 40) {
            this.spawnTrustingParticles(false);
        } else {
            super.handleEntityEvent(pId);
        }
    }

    private void spawnTrustingParticles(boolean isTrusting) {
        ParticleOptions particleoptions = ParticleTypes.HEART;
        if (!isTrusting) {
            particleoptions = ParticleTypes.SMOKE;
        }

        for(int i = 0; i < 7; ++i) {
            double x = this.random.nextGaussian() * 0.02D;
            double y = this.random.nextGaussian() * 0.02D;
            double z = this.random.nextGaussian() * 0.02D;
            this.level().addParticle(particleoptions, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), x, y, z);
        }
    }

    @Override
    public boolean canTakeItem(ItemStack pItemstack) {
        EquipmentSlot equipmentslot = this.getEquipmentSlotForItem(pItemstack);
        if (!this.getItemBySlot(equipmentslot).isEmpty() || this.isBaby() || !this.isTrusting()) {
            return false;
        } else {
            return equipmentslot == EquipmentSlot.MAINHAND && super.canTakeItem(pItemstack);
        }
    }

    @Override
    public boolean canHoldItem(ItemStack pStack) {
        ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
        return itemstack.isEmpty() && pStack.is(ItemTags.SAPLINGS) && !this.isBaby() && this.isTrusting();
    }

    @Override
    protected void pickUpItem(ItemEntity pItemEntity) {
        ItemStack itemstack = pItemEntity.getItem();
        if (this.canHoldItem(itemstack)) {
            int count = itemstack.getCount();
            if (count > 1) {
                this.dropItemStack(itemstack.split(count - 1));
            }

            this.onItemPickup(pItemEntity);
            this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
            this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
            this.take(pItemEntity, itemstack.getCount());
            pItemEntity.discard();
        }
    }

    private void dropItemStack(ItemStack pStack) {
        ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), pStack);
        this.level().addFreshEntity(itementity);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new BetterWallClimberNavigation(this, pLevel);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            this.setClimbing(this.horizontalCollision);
        }
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean pClimbing) {
        byte flag = this.entityData.get(DATA_FLAGS_ID);
        if (pClimbing) {
            flag = (byte)(flag | 1);
        } else {
            flag = (byte)(flag & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, flag);
    }


    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean canBeLeashed() {
        return this.isTrusting();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.SQUIRREL_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundRegistry.SQUIRREL_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.SQUIRREL_DEATH.get();
    }

    static class SquirrelSearchForSaplingsGoal extends Goal {
        private final SquirrelEntity squirrel;

        public SquirrelSearchForSaplingsGoal(SquirrelEntity squirrel) {
            this.setFlags(EnumSet.of(Flag.MOVE));
            this.squirrel = squirrel;
        }

        @Override
        public boolean canUse() {
            if (!squirrel.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() || squirrel.isBaby() || !squirrel.isTrusting()) {
                return false;
            } else {
                List<ItemEntity> list = squirrel.level().getEntitiesOfClass(ItemEntity.class, squirrel.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getItem().is(ItemTags.SAPLINGS));
                return !list.isEmpty() && squirrel.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            }
        }

        @Override
        public void tick() {
            List<ItemEntity> list = squirrel.level().getEntitiesOfClass(ItemEntity.class, squirrel.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getItem().is(ItemTags.SAPLINGS));
            ItemStack itemstack = squirrel.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty() && !list.isEmpty()) {
                squirrel.getNavigation().moveTo(list.get(0), 1.0F);
            }

        }

        @Override
        public void start() {
            List<ItemEntity> list = squirrel.level().getEntitiesOfClass(ItemEntity.class, squirrel.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), itemEntity -> itemEntity.getItem().is(ItemTags.SAPLINGS));
            if (!list.isEmpty()) {
                squirrel.getNavigation().moveTo(list.get(0), 1.0F);
            }

        }
    }

    static class SquirrelPlantSaplingGoal extends MoveToBlockGoal {
        private final SquirrelEntity squirrel;
        private boolean reachedTarget;

        public SquirrelPlantSaplingGoal(SquirrelEntity squirrel, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(squirrel, pSpeedModifier, pSearchRange, pVerticalSearchRange);
            this.squirrel = squirrel;
        }

        @Override
        public boolean canUse() {
            if (squirrel.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() || squirrel.isBaby() || !squirrel.isTrusting()) {
                return false;
            } else {
                return super.canUse();
            }
        }

        @Override
        public void tick() {
            BlockPos blockpos = this.getMoveToTarget();
            if (!blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance())) {
                this.reachedTarget = false;
                ++this.tryTicks;
                if (this.shouldRecalculatePath()) {
                    this.mob.getNavigation().moveTo((double)((float)blockpos.getX()) + 0.5D, blockpos.getY(), (double)((float)blockpos.getZ()) + 0.5D, this.speedModifier);
                }
            } else {
                this.reachedTarget = true;
                --this.tryTicks;
            }

            if (reachedTarget && !squirrel.getMainHandItem().isEmpty()) {
                Level level = squirrel.level();
                if (level.getBlockState(blockPos).is(BlockTags.DIRT)) {
                    this.onReachedTarget(level);
                    reachedTarget = false;
                }
            }
        }

        @Override
        public double acceptedDistance() {
            return 1.5D;
        }

        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return blockstate.is(BlockTags.DIRT) && pLevel.getBlockState(pPos.above()).isAir();
        }

        protected void onReachedTarget(Level level) {
            if (squirrel.getMainHandItem().getItem() instanceof BlockItem item) {
                level.setBlockAndUpdate(blockPos.above(), item.getBlock().defaultBlockState());
                level.playSound(null, blockPos, SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                squirrel.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            }
        }

        @Override
        protected boolean isReachedTarget() {
            return this.reachedTarget;
        }
    }
}