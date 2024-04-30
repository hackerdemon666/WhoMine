package minersstudios.whomine.item.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.event.GameEvent;

public class SaddleItem extends Item {
    public SaddleItem(Item.Settings settings) {
        super(settings);
    }

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
        } else if (entity.canEquip(stack)) {
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
