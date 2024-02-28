package minersstudios.whomine.block.blocks;

import com.mojang.serialization.MapCodec;
import minersstudios.whomine.block.ModBlockCollisionType;
import minersstudios.whomine.block.properties.WoodType;
import minersstudios.whomine.block.properties.WoodTypeProperty;
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
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DyeableWoodHorizontalFacingBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    public static final WoodTypeProperty WOOD_TYPE = WoodTypeProperty.of("wood_type");
    public ModBlockCollisionType collisionType;

    public DyeableWoodHorizontalFacingBlock(Settings settings, ModBlockCollisionType collisionType) {
        super(settings);
        this.collisionType = collisionType;
        setDefaultState(getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
                .with(WOOD_TYPE, WoodType.OAK)
        );
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return WoodType.getWoodBlockItem(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, WOOD_TYPE);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing());
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
        return this.collisionType.getBlockCollision(state);
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