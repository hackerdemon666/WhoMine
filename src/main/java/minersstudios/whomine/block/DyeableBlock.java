package minersstudios.whomine.block;

import minersstudios.whomine.item.DyeableBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DyeableBlock extends Block implements BlockEntityProvider {
    public ModBlockCollisionType CollisionType;
    public DyeableBlock(Settings settings, ModBlockCollisionType CollisionType) {
        super(settings);
        this.CollisionType = CollisionType;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeableBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return this.CollisionType.getBlockCollision(state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlaced(world, pos, state, placer, stack);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        NbtCompound nbtCompound = stack.getSubNbt("display");
        if (blockEntity instanceof DyeableBlockEntity) {
            if (nbtCompound != null) {
                ((DyeableBlockEntity) blockEntity).setColor(((DyeableBlockItem) stack.getItem()).getColor(stack), placer);
            } else {
                ((DyeableBlockEntity) blockEntity).setHasColor(false, placer);
            }
            blockEntity.markDirty();
        }
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        if (world.isClient) return;
        if (blockEntity == null) return;
        Block.getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, null, null)
                .forEach((itemStack) -> {
                    if (itemStack.getItem() instanceof DyeableBlockItem && ((DyeableBlockEntity) blockEntity).hasColor()) {
                        ((DyeableItem) itemStack.getItem()).setColor(itemStack, ((DyeableBlockEntity) blockEntity).getColor());
                    }
                    Block.dropStack(world, pos, itemStack);
                });
        state.onStacksDropped((ServerWorld) world, pos, null, false);
    }
}
