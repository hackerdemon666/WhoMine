package minersstudios.whomine.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

import java.util.Objects;

public class DyeableBlockEntity extends BlockEntity {
    public static final int DEFAULT_COLOR = 10511680;
    private int color = -1;

    public DyeableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitiesRegistry.DYEABLE_BLOCK_ENTITY, pos, state);
    }

    public int getColor() {
        return isPainted() ? this.color : DEFAULT_COLOR;
    }

    public void setColor(int newColor, LivingEntity user) {
        this.color = newColor;

        if (this.world == null) return;
        this.world.emitGameEvent(GameEvent.BLOCK_CHANGE, this.getPos(), GameEvent.Emitter.of(user, this.getCachedState()));
        this.updateListeners();
    }

    public boolean isPainted() {
        return this.color > -1;
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        if (isPainted()) {
            NbtCompound tagCompound = tag.getCompound("tag");
            NbtCompound displayTag = tagCompound.getCompound("display");
            displayTag.putInt("color", this.color);
            tagCompound.put("display", displayTag);
            tag.put("tag", tagCompound);
        }
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        if (tag.contains("tag")) {
            NbtCompound tagCompound = tag.getCompound("tag");
            if (tagCompound.contains("display")) {
                NbtCompound displayTag = tagCompound.getCompound("display");
                this.color = displayTag.contains("color") ? displayTag.getInt("color") : -1;
            }
        }
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        this.writeNbt(nbtCompound);
        return nbtCompound;
    }

    private void updateListeners() {
        this.markDirty();
        Objects.requireNonNull(this.getWorld()).updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
