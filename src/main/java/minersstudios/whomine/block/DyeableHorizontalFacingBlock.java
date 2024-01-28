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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DyeableHorizontalFacingBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    public static final WoodTypeProperty WOOD_TYPE = WoodTypeProperty.of("wood_type");
    public ModBlockCollisionType collisionType;

    public DyeableHorizontalFacingBlock(Settings settings, ModBlockCollisionType collisionType) {
        super(settings);
        this.collisionType = collisionType;
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WOOD_TYPE, WoodType.OAK));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(WOOD_TYPE);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return Objects.requireNonNull(super.getPlacementState(ctx)).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeableBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return this.collisionType.getBlockCollision(state);
    }


    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlaced(world, pos, state, placer, stack);
        DyeableBlockEntity blockEntity = (DyeableBlockEntity) world.getBlockEntity(pos);
        if (blockEntity == null) return;
        if (!(stack.getItem() instanceof DyeableBlockItem item)) return;
        if (item.isPainted(stack)) blockEntity.setColor(item.getColor(stack), placer);
        world.setBlockState(pos, state.with(WOOD_TYPE, item.getType(stack)));
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        if (world.isClient) return;
        if (blockEntity == null) return;
        Block.getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, null, null)
                .forEach((itemStack) -> {
                    if (itemStack.getItem() instanceof DyeableBlockItem && ((DyeableBlockEntity) blockEntity).isPainted()) {
                        ((DyeableItem) itemStack.getItem()).setColor(itemStack, ((DyeableBlockEntity) blockEntity).getColor());
                    }
                    Block.dropStack(world, pos, itemStack);
                });
        state.onStacksDropped((ServerWorld) world, pos, null, false);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        DyeableBlockEntity blockEntity = (DyeableBlockEntity) world.getBlockEntity(pos);
        ItemStack stack = new ItemStack(this);
        if (blockEntity != null) {
            if (blockEntity.isPainted()) ((DyeableBlockItem) stack.getItem()).setColor(stack, blockEntity.getColor());
            if (state.get(WOOD_TYPE) != WoodType.OAK)
                ((DyeableBlockItem) stack.getItem()).setType(stack, state.get(WOOD_TYPE));
        }
        return stack;
    }
}
