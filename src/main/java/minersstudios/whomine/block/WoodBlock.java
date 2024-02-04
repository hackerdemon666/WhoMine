package minersstudios.whomine.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WoodBlock extends Block {
    public static final WoodTypeProperty woodType = WoodTypeProperty.of("wood_type");
    public ModBlockCollisionType collisionType;

    public WoodBlock(Settings settings, ModBlockCollisionType collisionType) {
        super(settings);
        this.collisionType = collisionType;
        setDefaultState(getDefaultState().with(woodType, WoodType.OAK));
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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return this.collisionType.getBlockCollision(state);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        if (world.isClient) return;


        Block.dropStack(world, pos, stack);
    }
}