package com.james.tinkerscalibration.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class DashingStar extends Projectile {
    public float damage;
    public int ticks = 40;

    public void setDamage(float value) {
        this.damage = value;
    }

    public float getDamage() {
        return this.damage;
    }

    public DashingStar(EntityType<? extends DashingStar> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public void onHitEntity(EntityHitResult result) {
        Entity entityHit = result.getEntity();
        Entity shootingEntity = this.getOwner();
        if (entityHit == shootingEntity || this.level.isClientSide) {
            return;
        }
        entityHit.invulnerableTime = 0;
        entityHit.setSecondsOnFire(2);
        entityHit.hurt(DamageSource.ON_FIRE, damage);
    }

    @Override
    public void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos blockpos = result.getBlockPos().relative(result.getDirection());
        if (this.level.isEmptyBlock(blockpos)) {
            this.level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level, blockpos));
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void updateRotation() {
        ticks--;
        if (ticks < 0) {
            this.discard();
        }
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.add(0, -0.01f, 0));
    }
}
