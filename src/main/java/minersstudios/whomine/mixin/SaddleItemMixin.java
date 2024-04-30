package minersstudios.whomine.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SaddleItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SaddleItem.class)
public class SaddleItemMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof Saddleable saddleable) {
            if (entity.isAlive() && !saddleable.isSaddled() && saddleable.canBeSaddled()) {
                if (!user.getWorld().isClient) {
                    saddleable.saddle(SoundCategory.NEUTRAL);
                    entity.getWorld().emitGameEvent(entity, GameEvent.EQUIP, entity.getPos());
                    stack.decrement(1);
                }

                return ActionResult.success(user.getWorld().isClient);
            }
        } else if (entity instanceof ZombifiedPiglinEntity) {
            if (entity.isAlive() && entity.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
                if (!user.getWorld().isClient) {
                    entity.equipStack(EquipmentSlot.HEAD, stack.copyAndEmpty());
                    entity.getWorld().emitGameEvent(entity, GameEvent.EQUIP, entity.getPos());
                }

                return ActionResult.success(user.getWorld().isClient);
            }
        }

        return ActionResult.PASS;
    }
}
