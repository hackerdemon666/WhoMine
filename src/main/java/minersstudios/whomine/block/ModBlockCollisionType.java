package minersstudios.whomine.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static minersstudios.whomine.block.VoxelUtils.voxelizeAll;

public enum ModBlockCollisionType {
    DEFAULT_BLOCK(){
        @Override
        public VoxelShape getBlockCollision(BlockState state){
            return VoxelShapes.fullCube();
        }
    },
    TEST_BLOCK(){
        @Override
        public VoxelShape getBlockCollision(BlockState state){
            VoxelShape center = Block.createCuboidShape(7, 0, 7, 9, 16, 9);

            VoxelShape south = Block.createCuboidShape(7, 0, 7, 9, 16, 16);
            VoxelShape west = Block.createCuboidShape(0, 0, 7, 9, 16, 9);
            VoxelShape north = Block.createCuboidShape(7, 0, 0, 9, 16, 9);
            VoxelShape east = Block.createCuboidShape(7, 0, 7, 16, 16, 9);

            List<Box> westSouthCollisionBoxes = Arrays.asList(
                    new Box(0, 0, 8, 1, 16, 9),
                    new Box(1, 0, 9, 2, 16, 10),
                    new Box(2, 0, 10, 3, 16, 11),
                    new Box(3, 0, 11, 4, 16, 12),
                    new Box(4, 0, 12, 5, 16, 13),
                    new Box(5, 0, 13, 6, 16, 14),
                    new Box(6, 0, 14, 7, 16, 15),
                    new Box(7, 0, 15, 8, 16, 16)
            );
            List<Box> westSouthBoundsBoxes = Arrays.asList(
                    new Box(0, 0, 8, 2, 16, 10),
                    new Box(1, 0, 9, 3, 16, 11),
                    new Box(2, 0, 10, 4, 16, 12),
                    new Box(3, 0, 11, 5, 16, 13),
                    new Box(4, 0, 12, 6, 16, 14),
                    new Box(5, 0, 13, 7, 16, 15),
                    new Box(6, 0, 14, 8, 16, 16)
            );

            VoxelShape westSouthCollision = voxelizeAll(westSouthCollisionBoxes, null);
            VoxelShape westSouthBounds = voxelizeAll(westSouthBoundsBoxes, null);

            VoxelShape northWestCollision = voxelizeAll(westSouthCollisionBoxes, VoxelUtils::flipZ);
            VoxelShape northWestBounds = voxelizeAll(westSouthBoundsBoxes, VoxelUtils::flipZ);
            VoxelShape eastSouthCollision = voxelizeAll(westSouthCollisionBoxes, VoxelUtils::flipX);
            VoxelShape eastSouthBounds = voxelizeAll(westSouthBoundsBoxes, VoxelUtils::flipX);
            VoxelShape eastNorthCollision = voxelizeAll(westSouthBoundsBoxes, VoxelUtils::flipBoth);
            VoxelShape eastNorthBounds = voxelizeAll(westSouthBoundsBoxes, VoxelUtils::flipBoth);

            /*
             * Bitmask for each direction
             * 0000
             * ^^^^_ SOUTH
             * |||__ WEST
             * ||___ NORTH
             * |____ EAST
             */

            VoxelShape[] collisionShapes = new VoxelShape[]{
                    // 0000
                    VoxelShapes.union(center),
                    // 0001
                    VoxelShapes.union(center, south),
                    // 0010
                    VoxelShapes.union(center, west),
                    // 0011
                    westSouthCollision,

                    // 0100
                    VoxelShapes.union(center, north),
                    // 0101
                    VoxelShapes.union(center, north, south),
                    // 0110
                    northWestCollision,
                    // 0111
                    VoxelShapes.union(center, north, west, south),

                    // 1000
                    VoxelShapes.union(center, east),
                    // 1001
                    eastSouthCollision,
                    // 1010
                    VoxelShapes.union(center, east, west),
                    // 1011
                    VoxelShapes.union(center, east, west, south),

                    // 1100
                    eastNorthCollision,
                    // 1101
                    VoxelShapes.union(center, east, north, south),
                    // 1110
                    VoxelShapes.union(center, east, north, west),
                    // 1111
                    VoxelShapes.union(center, east, north, west, south),
            };

            // Non-voxel bounding box setup:

            VoxelShape[] boundingShapes = Arrays.copyOf(collisionShapes, collisionShapes.length);
            // 0.9558058261758 0.0441941738242
            // 0.5441941738242 0.4558058261758
            Vec3d p1 = new Vec3d(-0.0441941738242, 0, 0.5441941738242);
            Vec3d p2 = new Vec3d(0.4558058261758, 0, 1.0441941738242);
            Vec3d p3 = new Vec3d(0.5441941738242, 0, 0.9558058261758);
            Vec3d p4 = new Vec3d(0.0441941738242, 0, 0.4558058261758);
            Vec3d p1u16 = p1.add(0, 1, 0);
            Vec3d p2u16 = p2.add(0, 1, 0);
            Vec3d p3u16 = p3.add(0, 1, 0);
            Vec3d p4u16 = p4.add(0, 1, 0);

            List<Vec3d> westSouthBoundingEdges = Arrays.asList(
                    // Bottom
                    p1, p2,
                    p2, p3,
                    p3, p4,
                    p4, p1,

                    // Sides
                    p1, p1u16,
                    p2, p2u16,
                    p3, p3u16,
                    p4, p4u16,

                    // Top
                    p1u16, p2u16,
                    p2u16, p3u16,
                    p3u16, p4u16,
                    p4u16, p1u16
            );
//            List<Vec3d> northWestBoundingEdges = westSouthBoundingEdges.stream().map(VoxelUtils::flipZ).collect(Collectors.toList());
//            List<Vec3d> eastSouthBoundingEdges = westSouthBoundingEdges.stream().map(VoxelUtils::flipX).collect(Collectors.toList());
//            List<Vec3d> eastNorthBoundingEdges = westSouthBoundingEdges.stream().map(VoxelUtils::flipBoth).collect(Collectors.toList());

//            boundingShapes[3] = new NotVoxelShape(westSouthBounds, westSouthBoundingEdges);
//            boundingShapes[6] = new NotVoxelShape(northWestBounds, northWestBoundingEdges);
//            boundingShapes[9] = new NotVoxelShape(eastSouthBounds, eastSouthBoundingEdges);
//            boundingShapes[12] = new NotVoxelShape(eastNorthBounds, eastNorthBoundingEdges);

            return new NotVoxelShape(westSouthBounds, westSouthBoundingEdges); // Возвращаем форму коллизии для запад-юг
        }
    },
    TEST_BLOCK_1(){
        @Override
        public VoxelShape getBlockCollision(BlockState state){
            return VoxelShapes.union(
                    VoxelShapes.cuboid(0.05f, 0.0f, 0.05f, 0.95f, 0.5f, 0.95f),
                    VoxelShapes.cuboid(0.0f, 0.5f, 0.0f, 1.0f, 0.75f, 1.0f)
            );
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
