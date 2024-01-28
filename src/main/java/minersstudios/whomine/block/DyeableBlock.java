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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DyeableBlock extends Block implements BlockEntityProvider {
    public ModBlockCollisionType CollisionType;
    public static final WoodTypeProperty WOOD_TYPE = WoodTypeProperty.of("wood_type");

    public DyeableBlock(Settings settings, ModBlockCollisionType CollisionType) {
        super(settings);
        this.CollisionType = CollisionType;
        setDefaultState(getDefaultState().with(WOOD_TYPE, WoodType.OAK));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeableBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WOOD_TYPE);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return this.CollisionType.getBlockCollision(state);
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
