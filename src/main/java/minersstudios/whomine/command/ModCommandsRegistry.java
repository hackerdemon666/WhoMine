package minersstudios.whomine.command;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.entity.ModEntitiesRegistry;
import minersstudios.whomine.entity.entities.SitEntity;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;

import static net.minecraft.server.command.CommandManager.*;

public class ModCommandsRegistry {
    public static void sitCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("sit")
                .executes(context -> {
                    PlayerEntity player = context.getSource().getPlayerOrThrow();
                    Vec3d pos = context.getSource().getPlayer().getPos();
                    World world = player.getWorld();

                    if(!world.isClient && player.fallDistance == 0){
                        if (player.hasVehicle()) {
                            player.stopRiding();
                        } else {
                            SitEntity sit = ModEntitiesRegistry.SIT.create(world);
                            Objects.requireNonNull(sit).setPos(pos.getX(), pos.getY() - 0.95D, pos.getZ());

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
