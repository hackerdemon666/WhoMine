package minersstudios.whomine.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemStack;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public static EquipmentSlot getPreferredEquipmentSlot(ItemStack stack) {
        Equipment equipment = Equipment.fromStack(stack);
        return equipment != null ? equipment.getSlotType() : EquipmentSlot.HEAD;
    }

    @Shadow
    protected boolean isArmorSlot(EquipmentSlot slot) {
        return true;
    }
}
