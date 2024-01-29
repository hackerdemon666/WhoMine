package minersstudios.whomine;

import minersstudios.whomine.entity.ModEntitiesRegistry;
import minersstudios.whomine.entity.SitEntity;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class SitOnUseBlock {
    public static void onUseBlock() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockPos pos = new BlockPos(hitResult.getBlockPos());
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            Vec3d position = new Vec3d(0, 0, 0);

            if(block instanceof StairsBlock && state.get(StairsBlock.HALF) == BlockHalf.BOTTOM) {
                switch (state.get(StairsBlock.SHAPE)) {
                    case STRAIGHT:
                        switch (state.get(HorizontalFacingBlock.FACING)) {
                            case NORTH -> position = new Vec3d(0,-0.3, 0.25);
                            case SOUTH -> position = new Vec3d(0, -0.3, -0.25);
                            case EAST -> position = new Vec3d(-0.25, -0.3, 0);
                            case WEST -> position = new Vec3d(0.25, -0.3, 0);
                        }
                        break;
                    case INNER_LEFT, OUTER_LEFT:
                        switch (state.get(HorizontalFacingBlock.FACING)) {
                            case NORTH -> position = new Vec3d(0.25, -0.3, 0.25);
                            case SOUTH -> position = new Vec3d(-0.25, -0.3, -0.25);
                            case EAST -> position = new Vec3d(-0.25, -0.3, 0.25);
                            case WEST -> position = new Vec3d(0.25, -0.3, -0.25);
                        }
                        break;
                    case INNER_RIGHT, OUTER_RIGHT:
                        switch (state.get(HorizontalFacingBlock.FACING)) {
                            case NORTH -> position = new Vec3d(-0.25, -0.3, 0.25);
                            case SOUTH -> position = new Vec3d(0.25, -0.3, -0.25);
                            case EAST -> position = new Vec3d(-0.25, -0.3, -0.25);
                            case WEST -> position = new Vec3d(0.25, -0.3, 0.25);
                        }
                        break;
                }
            } else if (block instanceof SlabBlock && state.get(SlabBlock.TYPE) == SlabType.BOTTOM) {
                position = new Vec3d(0.5D, -0.45D, 0.5D);
            }

            if(block instanceof SlabBlock || block instanceof StairsBlock && state.get(StairsBlock.HALF) == BlockHalf.BOTTOM) {
                if(!world.isClient && hand == Hand.MAIN_HAND && player.getMainHandStack().isEmpty()){
                    if (block instanceof StairsBlock && state.get(StairsBlock.HALF) == BlockHalf.BOTTOM) {
                        if (hitResult.getSide() == Direction.DOWN || hitResult.getSide() == state.get(HorizontalFacingBlock.FACING)) {
                            player.sendMessage(Text.of("Хуйня, а не ступенька!"));
                        }
                    } else if(block instanceof SlabBlock && hitResult.getSide() == Direction.DOWN) {
                        player.sendMessage(Text.of("Хуйня, а не полублок!"));
                    }
                    SitEntity sit = ModEntitiesRegistry.SIT.create(world);
                    assert sit != null;
                    if (block instanceof StairsBlock && state.get(StairsBlock.HALF) == BlockHalf.BOTTOM) {
                        sit.setPos(pos.getX() + 0.5 + position.x, pos.getY() + -0.15 + position.y, pos.getZ() + 0.5 + position.z);
                    } else if (block instanceof SlabBlock) {
                        sit.setPos(pos.getX() + position.x, pos.getY() + position.y, pos.getZ() + position.z);
                    }
                    world.spawnEntity(sit);

                    player.startRiding(sit);
                }
            }

            return ActionResult.PASS;
        });
    }

    public static void register() {
        onUseBlock();
    }
}