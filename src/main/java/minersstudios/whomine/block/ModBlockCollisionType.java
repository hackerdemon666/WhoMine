package minersstudios.whomine.block;

import minersstudios.whomine.block.properties.ChessType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.*;

public enum ModBlockCollisionType {
    DEFAULT_BLOCK() {
        @Override
        public VoxelShape getOutlineShape(BlockState state) {
            return VoxelShapes.fullCube();
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state) {
            return this.getOutlineShape(state);
        }
    },
    TEST_BLOCK_1() {
        @Override
        public VoxelShape getOutlineShape(BlockState state) {
            return VoxelShapes.union(
                    VoxelShapes.cuboid(0.05f, 0.0f, 0.05f, 0.95f, 0.5f, 0.95f),
                    VoxelShapes.cuboid(0.0f, 0.5f, 0.0f, 1.0f, 0.75f, 1.0f)
            );
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state) {
            return this.getOutlineShape(state);
        }
    },
    BAR_CHAIR() {
        @Override
        public VoxelShape getOutlineShape(BlockState state) {
            return VoxelShapes.union(
                    VoxelShapes.cuboid(0.09375f, 0.78125f, 0.09375f, 0.90625f, 1.0625f, 0.90625f),
                    VoxelShapes.cuboid(0.40625f, 0.09375f, 0.40625f, 0.59375f, 0.78125f, 0.59375f),
                    VoxelShapes.cuboid(0.21875f, 0.0f, 0.21875f, 0.78125f, 0.09375f, 0.78125f)
            );
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state) {
            return this.getOutlineShape(state);
        }
    },
    BIG_ARMCHAIR() {
        @Override
        public VoxelShape getOutlineShape(BlockState state) {
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

        @Override
        public VoxelShape getCollisionShape(BlockState state) {
            Direction dir = state.get(HorizontalFacingBlock.FACING);
            return switch (dir) {
                case NORTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.625f, 0.1875f, 0.125f, 0.9375f, 1.0f),
                        VoxelShapes.cuboid(-0.0625f, 0.0f, 0.1875f, 1.0625f, 0.625f, 1.0f),
                        VoxelShapes.cuboid(0.875f, 0.625f, 0.1875f, 1.0625f, 0.9375f, 1.0f),
                        VoxelShapes.cuboid(-0.0625f, 0.0f, 0.0f, 1.0625f, 1.625f, 0.1875f)
                );

                case SOUTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.875f, 0.625f, 0.0f, 1.0625f, 0.9375f, 0.8125f),
                        VoxelShapes.cuboid(-0.0625f, 0.0f, 0.0f, 1.0625f, 0.625f, 0.8125f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, 0.0f, 0.125f, 0.9375f, 0.8125f),
                        VoxelShapes.cuboid(-0.0625f, 0.0f, 0.8125f, 1.0625f, 1.625f, 1.0f)
                );

                case EAST -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.0f, 0.625f, -0.0625f, 0.8125f, 0.9375f, 0.125f),
                        VoxelShapes.cuboid(0.0f, 0.0f, -0.0625f, 0.8125f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(0.0f, 0.625f, 0.875f, 0.8125f, 0.9375f, 1.0625f),
                        VoxelShapes.cuboid(0.8125f, 0.0f, -0.0625f, 1.0f, 1.625f, 1.0625f)
                );

                case WEST -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.1875f, 0.625f, 0.875f, 1.0f, 0.9375f, 1.0625f),
                        VoxelShapes.cuboid(0.1875f, 0.0f, -0.0625f, 1.0f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(0.1875f, 0.625f, -0.0625f, 1.0f, 0.9375f, 0.125f),
                        VoxelShapes.cuboid(0.0f, 0.0f, -0.0625f, 0.1875f, 1.625f, 1.0625f)
                );
                default -> VoxelShapes.fullCube();
            };
        }
    },
    BIG_CHAIR() {
        @Override
        public VoxelShape getOutlineShape(BlockState state) {
            Direction dir = state.get(HorizontalFacingBlock.FACING);
            return switch (dir) {
                case NORTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.0625f, 1.0625f, 0.6875f, 1.0625f),
                        VoxelShapes.cuboid(-0.0625f, 0.6875f, -0.0625f, 1.0625f, 1.6875f, 0.1875f)
                );

                case SOUTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.0625f, 1.0625f, 0.6875f, 1.0625f),
                        VoxelShapes.cuboid(-0.0625f, 0.6875f, 0.8125f, 1.0625f, 1.6875f, 1.0625f)
                );

                case EAST -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.0625f, 1.0625f, 0.6875f, 1.0625f),
                        VoxelShapes.cuboid(0.8125f, 0.6875f, -0.0625f, 1.0625f, 1.6875f, 1.0625f)
                );

                case WEST -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.0625f, 1.0625f, 0.6875f, 1.0625f),
                        VoxelShapes.cuboid(-0.0625f, 0.6875f, -0.0625f, 0.1875f, 1.6875f, 1.0625f)
                );
                default -> VoxelShapes.fullCube();
            };
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state) {
            Direction dir = state.get(HorizontalFacingBlock.FACING);
            return switch (dir) {
                case NORTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.0f, 0.0f, 0.1875f, 1.0f, 0.625f, 1.0f),
                        VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 1.625f, 0.1875f)
                );

                case SOUTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 0.625f, 0.8125f),
                        VoxelShapes.cuboid(0.0f, 0.0f, 0.8125f, 1.0f, 1.625f, 1.0f)
                );

                case EAST -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 0.8125f, 0.625f, 1.0f),
                        VoxelShapes.cuboid(0.8125f, 0.0f, 0.0f, 1.0f, 1.625f, 1.0f)
                );

                case WEST -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.1875f, 0.0f, 0.0f, 1.0f, 0.625f, 1.0f),
                        VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 0.1875f, 1.625f, 1.0f)
                );
                default -> VoxelShapes.fullCube();
            };
        }
    },
    SMALL_ARMCHAIR() {
        @Override
        public VoxelShape getOutlineShape(BlockState state) {
            Direction dir = state.get(HorizontalFacingBlock.FACING);
            return switch (dir) {
                case NORTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.0625f, 1.0625f, 0.625f, 1.09375f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, -0.0625f, 1.0625f, 1.4375f, 0.1875f),
                        VoxelShapes.cuboid(-0.125f, 0.625f, -0.0625f, 0.125f, 0.9375f, 1.125f),
                        VoxelShapes.cuboid(0.875f, 0.625f, -0.0625f, 1.125f, 0.9375f, 1.125f)
                );

                case SOUTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.09375f, 1.0625f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, 0.8125f, 1.0625f, 1.4375f, 1.0625f),
                        VoxelShapes.cuboid(0.875f, 0.625f, -0.125f, 1.125f, 0.9375f, 1.0625f),
                        VoxelShapes.cuboid(-0.125f, 0.625f, -0.125f, 0.125f, 0.9375f, 1.0625f)
                );

                case EAST -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.09375f, 0.0f, -0.0625f, 1.0625f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(0.8125f, 0.625f, -0.0625f, 1.0625f, 1.4375f, 1.0625f),
                        VoxelShapes.cuboid(-0.125f, 0.625f, -0.125f, 1.0625f, 0.9375f, 0.125f),
                        VoxelShapes.cuboid(-0.125f, 0.625f, 0.875f, 1.0625f, 0.9375f, 1.125f)
                );

                case WEST -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.0f, -0.0625f, 1.09375f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, -0.0625f, 0.1875f, 1.4375f, 1.0625f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, 0.875f, 1.125f, 0.9375f, 1.125f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, -0.125f, 1.125f, 0.9375f, 0.125f)
                );
                default -> VoxelShapes.fullCube();
            };
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state) {
            Direction dir = state.get(HorizontalFacingBlock.FACING);
            return switch (dir) {
                case NORTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(-0.0625f, 0.625f, 0.1875f, 0.125f, 0.9375f, 1.0f),
                        VoxelShapes.cuboid(-0.0625f, 0.0f, 0.1875f, 1.0625f, 0.625f, 1.0f),
                        VoxelShapes.cuboid(0.875f, 0.625f, 0.1875f, 1.0625f, 0.9375f, 1.0f),
                        VoxelShapes.cuboid(-0.0625f, 0.0f, 0.0f, 1.0625f, 1.4375f, 0.1875f)
                );

                case SOUTH -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.875f, 0.625f, 0.0f, 1.0625f, 0.9375f, 0.8125f),
                        VoxelShapes.cuboid(-0.0625f, 0.0f, 0.0f, 1.0625f, 0.625f, 0.8125f),
                        VoxelShapes.cuboid(-0.0625f, 0.625f, 0.0f, 0.125f, 0.9375f, 0.8125f),
                        VoxelShapes.cuboid(-0.0625f, 0.0f, 0.8125f, 1.0625f, 1.4375f, 1.0f)
                );

                case EAST -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.0f, 0.625f, -0.0625f, 0.8125f, 0.9375f, 0.125f),
                        VoxelShapes.cuboid(0.0f, 0.0f, -0.0625f, 0.8125f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(0.0f, 0.625f, 0.875f, 0.8125f, 0.9375f, 1.0625f),
                        VoxelShapes.cuboid(0.8125f, 0.0f, -0.0625f, 1.0f, 1.4375f, 1.0625f)
                );

                case WEST -> VoxelShapes.union(
                        VoxelShapes.cuboid(0.1875f, 0.625f, 0.875f, 1.0f, 0.9375f, 1.0625f),
                        VoxelShapes.cuboid(0.1875f, 0.0f, -0.0625f, 1.0f, 0.625f, 1.0625f),
                        VoxelShapes.cuboid(0.1875f, 0.625f, -0.0625f, 1.0f, 0.9375f, 0.125f),
                        VoxelShapes.cuboid(0.0f, 0.0f, -0.0625f, 0.1875f, 1.4375f, 1.0625f)
                );
                default -> VoxelShapes.fullCube();
            };
        }
    },
    SMALL_CHAIR() {
        @Override
        public VoxelShape getOutlineShape(BlockState state) {
            return VoxelShapes.union(
                    VoxelShapes.cuboid(0.05f, 0.0f, 0.05f, 0.95f, 0.5f, 0.95f),
                    VoxelShapes.cuboid(0.0f, 0.5f, 0.0f, 1.0f, 0.75f, 1.0f)
            );
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state) {
            return this.getOutlineShape(state);
        }
    },
    COOL_CHAIR() {
        @Override
        public VoxelShape getOutlineShape(BlockState state) {
            return VoxelShapes.union(
                    VoxelShapes.cuboid(0.0625f, 0.125f, 0.0625f, 0.9375f, 0.625f, 0.9375f),
                    VoxelShapes.cuboid(0.15625f, 0.0f, 0.15625f, 0.84375f, 0.125f, 0.84375f)
            );
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state) {
            return this.getOutlineShape(state);
        }
    },
    CHESS_BLOCK() {
        @Override
        public VoxelShape getOutlineShape(BlockState state) {
            ChessType chessType = state.get(ModProperties.CHESS_TYPE);
            return switch (chessType) {
                case KING -> VoxelShapes.fullCube();
                case QUEEN -> VoxelShapes.fullCube();
                case ROOK -> VoxelShapes.fullCube();
                case BISHOP -> VoxelShapes.fullCube();
                case KNIGHT -> VoxelShapes.fullCube();
                case PAWN -> VoxelShapes.fullCube();
                default -> VoxelShapes.fullCube();
            };
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state) {
            return this.getOutlineShape(state);
        }
    };

        public abstract VoxelShape getOutlineShape(BlockState state);

        public abstract VoxelShape getCollisionShape(BlockState state);
}
