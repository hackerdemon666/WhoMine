package minersstudios.whomine.block;

import com.mojang.serialization.MapCodec;
import minersstudios.whomine.item.DyeableBlockItem;
import minersstudios.whomine.item.ModItemsRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
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
    public BlockSoundGroup getSoundGroup(BlockState state) {
        return state.get(WOOD_TYPE).getSoundGroup();
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
        switch (state.get(WOOD_TYPE)) {
            case OAK -> stack = new ItemStack(ModItemsRegistry.OAK_BIG_ARMCHAIR);
            case SPRUCE -> stack = new ItemStack(ModItemsRegistry.SPRUCE_BIG_ARMCHAIR);
            case BIRCH -> stack = new ItemStack(ModItemsRegistry.BIRCH_BIG_ARMCHAIR);
            case ACACIA -> stack = new ItemStack(ModItemsRegistry.ACACIA_BIG_ARMCHAIR);
            case CHERRY -> stack = new ItemStack(ModItemsRegistry.CHERRY_BIG_ARMCHAIR);
            case JUNGLE -> stack = new ItemStack(ModItemsRegistry.JUNGLE_BIG_ARMCHAIR);
            case DARK_OAK -> stack = new ItemStack(ModItemsRegistry.DARK_OAK_BIG_ARMCHAIR);
            case CRIMSON -> stack = new ItemStack(ModItemsRegistry.CRIMSON_BIG_ARMCHAIR);
            case WARPED -> stack = new ItemStack(ModItemsRegistry.WARPED_BIG_ARMCHAIR);
            case MANGROVE -> stack = new ItemStack(ModItemsRegistry.MANGROVE_BIG_ARMCHAIR);
            case BAMBOO -> stack = new ItemStack(ModItemsRegistry.BAMBOO_BIG_ARMCHAIR);
        }
        if (stack.getItem() instanceof DyeableBlockItem && ((DyeableBlockEntity) blockEntity).hasColor()) {
            ((DyeableItem) stack.getItem()).setColor(stack, ((DyeableBlockEntity) blockEntity).getColor());
        }
        Block.dropStack(world, pos, stack);
    }
}
