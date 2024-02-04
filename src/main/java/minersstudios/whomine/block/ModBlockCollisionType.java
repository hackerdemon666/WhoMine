package minersstudios.whomine.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public enum ModBlockCollisionType {
    DEFAULT_BLOCK(){
        @Override
        public VoxelShape getBlockCollision(BlockState state){
            return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1f, 1f, 1f);
        }
    },
    BIG_ARMCHAIR(){
        @Override
        public VoxelShape getBlockCollision(BlockState state){
            Direction dir = state.get(HorizontalFacingBlock.FACING);
            return switch (dir) {
                case NORTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.0625f, 1.0625f, 0.625f, 1.09375f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, -0.0625f, 1.0625f, 1.625f, 0.1875f),
                        VoxelShapes.cuboid(-0.125f, 0.625f, -0.0625f, 0.125f, 0.9375f, 1.125f),
                        VoxelShapes.cuboid(0.875f, 0.625f, -0.0625f, 1.125f, 0.9375f, 1.125f)
                );

                case SOUTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.09375f, 1.0625f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, 0.8125f, 1.0625f, 1.625f, 1.0625f),
                        VoxelShapes.cuboid(0.875f, 0.625f, -0.125f, 1.125f, 0.9375f, 1.0625f),
                        VoxelShapes.cuboid(-0.125f, 0.625f, -0.125f, 0.125f, 0.9375f, 1.0625f)
                );

                case EAST -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.09375f, 0.0f, -0.0625f, 1.0625f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(0.8125f, 0.625f, -0.0625f, 1.0625f, 1.625f, 1.0625f),
                        VoxelShapes.cuboid(-0.125f, 0.625f, -0.125f, 1.0625f, 0.9375f, 0.125f),
                        VoxelShapes.cuboid(-0.125f, 0.625f, 0.875f, 1.0625f, 0.9375f, 1.125f)
                );

                case WEST -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.0625f, 1.09375f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, -0.0625f, 0.1875f, 1.625f, 1.0625f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, 0.875f, 1.125f, 0.9375f, 1.125f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, -0.125f, 1.125f, 0.9375f, 0.125f)
                );
                default -> VoxelShapes.fullCube();
            };
        }
    },
    SMALL_CHAIR(){
        @Override
        public VoxelShape getBlockCollision(BlockState state){
            return VoxelShapes.union(
                    VoxelShapes.cuboid(0.05f, 0.0f, 0.05f, 0.95f, 0.5f, 0.95f),
                    VoxelShapes.cuboid(0.0f, 0.5f, 0.0f, 1.0f, 0.75f, 1.0f)
            );
        }
    };
    public abstract VoxelShape getBlockCollision(BlockState state);
}
