package minersstudios.whomine.block.blocks;

import com.mojang.serialization.MapCodec;
import minersstudios.whomine.block.ModBlockCollisionType;
import minersstudios.whomine.block.ModProperties;
import minersstudios.whomine.block.properties.TypeConnecting;
import minersstudios.whomine.block.properties.WoodType;
import minersstudios.whomine.item.items.DyeableWoodBlockItem;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DyeableConnectingBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    public static final EnumProperty<WoodType> WOOD_TYPE = ModProperties.WOOD_TYPE;
    public static final EnumProperty<TypeConnecting> TYPE = ModProperties.TYPE;

    public ModBlockCollisionType collisionType;

    public DyeableConnectingBlock(Settings settings, ModBlockCollisionType collisionType) {
        super(settings);
        this.collisionType = collisionType;
        setDefaultState(getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
                .with(WOOD_TYPE, WoodType.OAK)
                .with(TYPE, TypeConnecting.SINGLE)
        );
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return WoodType.getWoodBlockItem(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, WOOD_TYPE, TYPE);
    }

//    @Override
//    public BlockState getPlacementState(ItemPlacementContext ctx) {
//        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing());
//    }

//    TypeConnecting type = TypeConnecting.SINGLE;
//    if (type == TypeConnecting.SINGLE) {
//        if (direction == this.getNeighborChestDirection(ctx, direction.rotateYClockwise()) && direction == this.getNeighborChestDirection(ctx, direction.rotateYCounterclockwise())) {
//            type = TypeConnecting.MIDDLE;
//        } else if (direction == this.getNeighborChestDirection(ctx, direction.rotateYClockwise())) {
//            type = TypeConnecting.LEFT;
//        } else if (direction == this.getNeighborChestDirection(ctx, direction.rotateYCounterclockwise())) {
//            type = TypeConnecting.RIGHT;
//        }
//    }

//    BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(dir));
//    if (blockState.isOf(this) && blockState.get(TYPE) == TypeConnecting.SINGLE) {
//        return (Direction)blockState.get(Properties.HORIZONTAL_FACING);
//    } else {
//        return null;
//    }

//    @Override
//    public BlockState getPlacementState(ItemPlacementContext ctx) {
//        TypeConnecting type = TypeConnecting.SINGLE;
//        Direction direction = ctx.getHorizontalPlayerFacing();
//        Direction direction2 = ctx.getSide();
//        if (direction2.getAxis().isHorizontal()) {
//            Direction direction3 = this.getNeighborBlockDirection(ctx, direction2.getOpposite());
//            if (direction3 != null && direction3.getAxis() != direction2.getAxis()) {
//                direction = direction3;
//                type = direction3.rotateYCounterclockwise() == direction2.getOpposite() ? TypeConnecting.RIGHT : TypeConnecting.LEFT;
//            }
//        }
//
//        if (type == TypeConnecting.SINGLE) {
//            if (direction == this.getNeighborBlockDirection(ctx, direction.rotateYClockwise())) {
//                type = TypeConnecting.LEFT;
//            } else if (direction == this.getNeighborBlockDirection(ctx, direction.rotateYCounterclockwise())) {
//                type = TypeConnecting.RIGHT;
//            }
//        }
//        System.out.println("Player next: " + ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction)));
//        System.out.println("Player right: " + ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.rotateYClockwise())));
//        System.out.println("Player left: " + ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.rotateYCounterclockwise())));
//
//        return getDefaultState()
//                .with(Properties.HORIZONTAL_FACING, direction)
//                .with(TYPE, type);
//    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = (BlockState)((BlockState)((BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing())));
        return (BlockState)blockState.with(TYPE, getTypeConnecting(blockState, ctx.getWorld(), blockPos));
    }

    private static TypeConnecting getTypeConnecting(BlockState state, BlockView world, BlockPos pos) {
        Direction direction = (Direction)state.get(FACING);
        BlockState blockState = world.getBlockState(pos.offset(direction));
        if (isConnecting(blockState)) {
            Direction direction2 = (Direction)blockState.get(FACING);
            if (direction2.getAxis() != ((Direction)state.get(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction2.getOpposite())) {
                if (direction2 == direction.rotateYCounterclockwise()) {
                    return TypeConnecting.MIDDLE_LEFT;
                }

                return TypeConnecting.MIDDLE_RIGHT;
            }
        }

        BlockState blockState2 = world.getBlockState(pos.offset(direction.getOpposite()));
        if (isConnecting(blockState2)) {
            Direction direction3 = (Direction)blockState2.get(FACING);
            if (direction3.getAxis() != ((Direction)state.get(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction3)) {
                if (direction3 == direction.rotateYCounterclockwise()) {
                    return TypeConnecting.LEFT;
                }

                return TypeConnecting.RIGHT;
            }
        }

        return TypeConnecting.SINGLE;
    }

    private static boolean isDifferentOrientation(BlockState state, BlockView world, BlockPos pos, Direction dir) {
        BlockState blockState = world.getBlockState(pos.offset(dir));
        return !isConnecting(blockState) || blockState.get(FACING) != state.get(FACING);
    }

    public static boolean isConnecting(BlockState state) {
        return state.getBlock() instanceof DyeableConnectingBlock;
    }

//    SINGLE
//    - RIGHT_BLOCK, LEFT_BLOCK = SINGLE -=> RIGHT, LEFT
//    - RIGHT_BLOCK = RIGHT, MIDDLE && LEFT_BLOCK = LEFT, MIDDLE -=> MIDDLE
//    - BEHIND_BLOCK = RIGHT, MIDDLE, LEFT -=> BOTTOM

    @Nullable
    private Direction getNeighborBlockDirection(ItemPlacementContext ctx, Direction dir) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(dir));
        return blockState.isOf(this) && blockState.get(TYPE) == TypeConnecting.SINGLE ? (Direction)blockState.get(Properties.HORIZONTAL_FACING) : null;
    }

    @Override
    public BlockSoundGroup getSoundGroup(BlockState state) {
        return state.get(WOOD_TYPE).getSoundGroup();
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeableBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return this.collisionType.getOutlineShape(state);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlaced(world, pos, state, placer, stack);
        if (!(stack.getItem() instanceof DyeableWoodBlockItem item)) return;
        int color = stack.getSubNbt("BlockEntityTag") != null ? DyeableWoodBlockItem.getBlockEntityNbt(stack).getCompound("tag").getCompound("display").getInt("color") : item.getColor(stack);

        DyeableBlockEntity blockEntity = (DyeableBlockEntity) world.getBlockEntity(pos);
        if (blockEntity == null) return;
        if (item.isPainted(stack)) blockEntity.setColor(color, placer);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        if (world.isClient) return;
        if (blockEntity == null) return;
        ItemStack dropStack = WoodType.getWoodBlockItem(state);
        if (((DyeableBlockEntity) blockEntity).isPainted()) ((DyeableItem) dropStack.getItem()).setColor(dropStack, ((DyeableBlockEntity) blockEntity).getColor());
        Block.dropStack(world, pos, dropStack);
    }
}