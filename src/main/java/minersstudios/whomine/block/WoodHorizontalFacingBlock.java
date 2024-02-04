package minersstudios.whomine.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import org.jetbrains.annotations.Nullable;

public class WoodHorizontalFacingBlock extends HorizontalFacingBlock {
    public static final WoodTypeProperty WOOD_TYPE = WoodTypeProperty.of("wood_type");
    public ModBlockCollisionType CollisionType;
    public WoodHorizontalFacingBlock(Settings settings, ModBlockCollisionType CollisionType) {
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
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return this.CollisionType.getBlockCollision(state);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        if (world.isClient) return;
        ItemStack dropStack = WoodType.getWoodBlockItem(state);
        Block.dropStack(world, pos, dropStack);
    }
}