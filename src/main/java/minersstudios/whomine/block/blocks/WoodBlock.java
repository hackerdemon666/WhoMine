package minersstudios.whomine.block.blocks;

import minersstudios.whomine.block.ModBlockCollisionType;
import minersstudios.whomine.block.ModProperties;
import minersstudios.whomine.block.properties.WoodType;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class WoodBlock extends Block {
    public static final EnumProperty<WoodType> WOOD_TYPE = ModProperties.WOOD_TYPE;
    public ModBlockCollisionType collisionType;
    public WoodBlock(Settings settings, ModBlockCollisionType collisionType) {
        super(settings);
        this.collisionType = collisionType;
        setDefaultState(getDefaultState()
                .with(WOOD_TYPE, WoodType.OAK)
        );
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return minersstudios.whomine.block.properties.WoodType.getWoodBlockItem(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WOOD_TYPE);
    }

    @Override
    public BlockSoundGroup getSoundGroup(BlockState state) {
        return state.get(WOOD_TYPE).getSoundGroup();
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
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        if (world.isClient) return;

        ItemStack dropStack = WoodType.getWoodBlockItem(state);
        Block.dropStack(world, pos, dropStack);
    }
}
