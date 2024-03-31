package minersstudios.whomine.event;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.entity.ModEntitiesRegistry;
import minersstudios.whomine.entity.entities.SitEntity;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class EventHandler {
    public static void onUseBlock() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (!SitPosition.canSit(state, hitResult)) return ActionResult.PASS;

            if (!world.isClient() && hand == Hand.MAIN_HAND && player.getMainHandStack().isEmpty()){
                SitEntity sit = ModEntitiesRegistry.SIT.create(world);
                if (world.getEntitiesByType(player.getType(), new Box(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 0.05, pos.getZ() + 1).expand(-0.25), Entity::isAlive).isEmpty()) {
                    Vec3d position = SitPosition.getSitPosition(state);
                    sit.setPos(pos.getX() + position.x, pos.getY() + position.y, pos.getZ() + position.z);
                    world.spawnEntity(sit);
                    player.startRiding(sit);
                } else if (sit.hasPassenger(player)) {
                    player.sendMessage(Text.of("Занято, братанчик"), true);
                }
            }

            return ActionResult.PASS;
        });
    }

    public static void register() {
        WhoMineMod.LOGGER.debug("Registering Events for: " + WhoMineMod.MOD_NAME);

        onUseBlock();
    }
}