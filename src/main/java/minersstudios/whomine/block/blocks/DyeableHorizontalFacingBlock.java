package minersstudios.whomine.block.blocks;

import com.mojang.serialization.MapCodec;
import minersstudios.whomine.block.ModBlockCollisionType;
import minersstudios.whomine.item.items.DyeableBlockItem;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DyeableHorizontalFacingBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    public ModBlockCollisionType collisionType;

    public DyeableHorizontalFacingBlock(Settings settings, ModBlockCollisionType collisionType) {
        super(settings);
        this.collisionType = collisionType;
        setDefaultState(getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
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
        return this.collisionType.getOutlineShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.collisionType.getCollisionShape(state);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlaced(world, pos, state, placer, stack);
        if (!(stack.getItem() instanceof DyeableBlockItem item)) return;
        int color = stack.getSubNbt("BlockEntityTag") != null ? DyeableBlockItem.getBlockEntityNbt(stack).getCompound("tag").getCompound("display").getInt("color") : item.getColor(stack);

        DyeableBlockEntity blockEntity = (DyeableBlockEntity) world.getBlockEntity(pos);
        if (blockEntity == null) return;
        if (item.isPainted(stack)) blockEntity.setColor(color, placer);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        if (world.isClient) return;
        if (blockEntity == null) return;
        ItemStack dropStack = new ItemStack(state.getBlock().asItem());

        if (((DyeableBlockEntity) blockEntity).isPainted()) ((DyeableItem) dropStack.getItem()).setColor(dropStack, ((DyeableBlockEntity) blockEntity).getColor());
        Block.dropStack(world, pos, dropStack);
    }
}