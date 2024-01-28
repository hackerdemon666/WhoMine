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

    private int color;
    private boolean hasColor = true;

    public DyeableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitiesRegistry.DYEABLE_BLOCK_ENTITY, pos, state);
        this.color = DEFAULT_COLOR;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int newColor, LivingEntity user) {
        this.color = newColor;

        assert this.world != null;
        this.world.emitGameEvent(GameEvent.BLOCK_CHANGE, this.getPos(), GameEvent.Emitter.of(user, this.getCachedState()));
        this.updateListeners();
    }

    public boolean hasColor() {
        return this.hasColor;
    }

    public void setHasColor(boolean hasColor, LivingEntity user) {
        this.hasColor = hasColor;

        assert this.world != null;
        this.world.emitGameEvent(GameEvent.BLOCK_CHANGE, this.getPos(), GameEvent.Emitter.of(user, this.getCachedState()));
        this.updateListeners();
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        NbtCompound tagCompound = tag.getCompound("tag");
        NbtCompound displayTag = tagCompound.getCompound("display");
        if (this.hasColor) {
            displayTag.putInt("color", this.color);
        }
        displayTag.putBoolean("hasColor", this.hasColor);
        tagCompound.put("display", displayTag);
        tag.put("tag", tagCompound);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        if (tag.contains("tag")) {
            NbtCompound tagCompound = tag.getCompound("tag");
            if (tagCompound.contains("display")) {
                NbtCompound displayTag = tagCompound.getCompound("display");
                if (displayTag.contains("color")) {
                    this.color = displayTag.getInt("color");
                }
                if (displayTag.contains("hasColor")) {
                    this.hasColor = displayTag.getBoolean("hasColor");
                }
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
