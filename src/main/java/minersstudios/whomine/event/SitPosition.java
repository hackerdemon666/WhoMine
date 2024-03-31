package minersstudios.whomine.event;

import minersstudios.whomine.datagen.ModTagsRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.registry.Registries;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public enum SitPosition {
    STAIR("stair") {
        @Override
        public Vec3d getPosition(BlockState state){
            Direction dir = state.get(HorizontalFacingBlock.FACING);
            double x = 0, z = 0;
            final double offSet = 0.25;
            switch (state.get(StairsBlock.SHAPE)) {
                case STRAIGHT -> {
                    switch (dir) {
                        case NORTH -> z = offSet;
                        case SOUTH -> z = -offSet;
                        case EAST -> x = -offSet;
                        case WEST -> x = offSet;
                    }
                }
                case INNER_LEFT, OUTER_LEFT -> {
                    switch (dir) {
                        case NORTH -> x = z = offSet;
                        case SOUTH -> x = z = -offSet;
                        case EAST -> { x = -offSet; z = offSet; }
                        case WEST -> { x = offSet; z = -offSet; }
                    }
                }
                case INNER_RIGHT, OUTER_RIGHT -> {
                    switch (dir) {
                        case NORTH -> { x = -offSet; z = offSet; }
                        case SOUTH -> { x = offSet; z = -offSet; }
                        case EAST -> x = z = -offSet;
                        case WEST -> x = z = offSet;
                    }
                }
            }

            return new Vec3d(x + 0.5, -0.45, z + 0.5);
        }
    },
    SLAB("slab") {
        @Override
        public Vec3d getPosition(BlockState state) {
            return new Vec3d(0.5, -0.45, 0.5);
        }
    },
    BAR_CHAIR("bar_chair") {
        @Override
        public Vec3d getPosition(BlockState state) {
            return new Vec3d(0.5, 0.1, 0.5);
        }
    },
    COOL_CHAIR("cool_chair") {
        @Override
        public Vec3d getPosition(BlockState state) {
            return new Vec3d(0.5, -0.35, 0.5);
        }
    },
    BIG_CHAIR("big_chair") {
        @Override
        public Vec3d getPosition(BlockState state) {
            return new Vec3d(0.5, -0.25, 0.5);
        }
    },
    SMALL_CHAIR("small_chair") {
        @Override
        public Vec3d getPosition(BlockState state) {
            return new Vec3d(0.5, -0.2, 0.5);
        }
    },
    BIG_ARMCHAIR("big_armchair") {
        @Override
        public Vec3d getPosition(BlockState state) {
            return new Vec3d(0.5, -0.3, 0.5);
        }
    },
    SMALL_ARMCHAIR("small_armchair") {
        @Override
        public Vec3d getPosition(BlockState state) {
            return new Vec3d(0.5, -0.3, 0.5);
        }
    };

    private final String name;

    SitPosition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Vec3d getPosition(BlockState state);

    public static boolean canSit(BlockState state, BlockHitResult hitResult) {
        Block block = state.getBlock();
        Direction dir = hitResult.getSide();
        if (block instanceof StairsBlock) {
            Direction blockDir = state.get(HorizontalFacingBlock.FACING);
            return  !(
                (dir == blockDir) ||
                (dir == Direction.DOWN) ||
                (dir == blockDir.rotateYClockwise() && state.get(StairsBlock.SHAPE) == StairShape.INNER_RIGHT) ||
                (dir == blockDir.rotateYCounterclockwise() && state.get(StairsBlock.SHAPE) == StairShape.INNER_LEFT)
            );
        } else if (block instanceof SlabBlock) {
            return (state.get(SlabBlock.TYPE) == SlabType.BOTTOM) && (hitResult.getSide() != Direction.DOWN);
        } else if (state.isIn(ModTagsRegistry.Blocks.CHAIR_BLOCKS)) {
            return (dir != Direction.DOWN) && (!state.contains(HorizontalFacingBlock.FACING) || dir != state.get(HorizontalFacingBlock.FACING));
        }
        return false;
    }

    public static Vec3d getSitPosition(BlockState state) {
        Block block = state.getBlock();
        String id = Registries.BLOCK.getId(block).getPath();
        if (block instanceof StairsBlock) id = "stair";
        else if (block instanceof SlabBlock) id = "slab";
        return get(id).getPosition(state);
    }

    public static SitPosition get(String name) {
        for (SitPosition sitPos : values()) if (sitPos.getName().equals(name)) return sitPos;
        return null;
    }
}
