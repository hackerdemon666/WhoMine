package minersstudios.whomine.block;

import com.mojang.serialization.MapCodec;
import minersstudios.whomine.item.DyeableBlockItem;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DyeableHorizontalFacingBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    public static final WoodTypeProperty WOOD_TYPE = WoodTypeProperty.of("wood_type");
    public ModBlockCollisionType CollisionType;
    public DyeableHorizontalFacingBlock(Settings settings, ModBlockCollisionType CollisionType) {
        super(settings);
        this.CollisionType = CollisionType;
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WOOD_TYPE, WoodType.OAK));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(WOOD_TYPE);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing());
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
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
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
