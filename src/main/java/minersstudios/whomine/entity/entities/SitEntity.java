package minersstudios.whomine.entity.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SitEntity extends Entity {
    public static final String ENTITY_ID = "sit";

    public SitEntity(EntityType<SitEntity> type, World world) {
        super(type, world);
        this.setNoGravity(true);
        this.setInvulnerable(true);
        this.setInvisible(true);
    }

    @Override
    public void baseTick() {
        if (!this.hasPassengers() && this.age > 20) {
            this.discard();
        }
        if (this.hasPassengers() && !this.getWorld().isClient) {
            BlockPos blockPos = new BlockPos(this.getSteppingPos().getX(), (int) (this.getPos().getY() + 0.85D), this.getSteppingPos().getZ());
            BlockState state = this.getWorld().getBlockState(blockPos);
            if (state.isAir() || (state.getBlock() instanceof TrapdoorBlock && state.get(TrapdoorBlock.OPEN))) {
                this.discard();
            }
        }
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    @Override
    protected void removePassenger(Entity passenger) {
        this.discard();
        super.removePassenger(passenger);
        passenger.setPosition(passenger.getPos().getX(), passenger.getPos().getY() + 0.75D, passenger.getPos().getZ());
    }
}