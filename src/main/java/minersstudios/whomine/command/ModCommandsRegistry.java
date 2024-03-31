package minersstudios.whomine.command;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.entity.ModEntitiesRegistry;
import minersstudios.whomine.entity.entities.SitEntity;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;

import static net.minecraft.server.command.CommandManager.*;

public class ModCommandsRegistry {
    public static void sitCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("sit")
                .executes(context -> {
                    PlayerEntity player = context.getSource().getPlayer();
                    World world = player.getWorld();

                    BlockPos blockPos = new BlockPos(player.getBlockPos().getX(), player.getBlockPos().getY() - 1, player.getBlockPos().getZ());
                    BlockState state = world.getBlockState(blockPos);

                    if (world.getBlockState(blockPos).isAir() || (state.getBlock() instanceof TrapdoorBlock && state.get(TrapdoorBlock.OPEN)) || state.getBlock() instanceof DoorBlock || state.getBlock() instanceof FenceBlock) return 1;

                    if (!world.isClient && player.fallDistance == 0) {
                        if (player.hasVehicle()) {
                            player.stopRiding();
                        } else {
                            SitEntity sit = ModEntitiesRegistry.SIT.create(world);
                            sit.setPos(player.getPos().getX(), player.getPos().getY() - 0.95D, player.getPos().getZ());
                            world.spawnEntity(sit);

                            player.startRiding(sit);
                        }
                    }
                    return 1;
        })));
    }

    public static void register() {
        WhoMineMod.LOGGER.info("Registering Commands for " + WhoMineMod.MOD_NAME);

        sitCommand();
    }
}