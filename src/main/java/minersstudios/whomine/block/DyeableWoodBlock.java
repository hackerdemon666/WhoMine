package minersstudios.whomine.block;

import minersstudios.whomine.item.DyeableBlockItem;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DyeableWoodBlock extends Block implements BlockEntityProvider {
    public static final WoodTypeProperty woodType = WoodTypeProperty.of("wood_type");
    public ModBlockCollisionType collisionType;

    public DyeableWoodBlock(Settings settings, ModBlockCollisionType collisionType) {
        super(settings);
        this.collisionType = collisionType;
        setDefaultState(getDefaultState().with(woodType, WoodType.OAK));
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return WoodType.getWoodBlockItem(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(woodType);
    }

    @Override
    public BlockSoundGroup getSoundGroup(BlockState state) {
        return state.get(woodType).getSoundGroup();
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
        ItemStack dropStack = WoodType.getWoodBlockItem(state);

        if (((DyeableBlockEntity) blockEntity).isPainted()) ((DyeableItem) dropStack.getItem()).setColor(dropStack, ((DyeableBlockEntity) blockEntity).getColor());
        Block.dropStack(world, pos, dropStack);
    }
}