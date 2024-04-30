package minersstudios.whomine.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "baseTick", at = @At("TAIL"))
    public void onBaseTick(CallbackInfo callbackInfo) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self.hasPassengers() && !self.getWorld().isClient) {
            if (self.age > 20 && self.getEquippedStack(EquipmentSlot.HEAD).getItem() != Items.SADDLE) {
                self.removeAllPassengers();
            }
        }
    }

    @Inject(method = "drop", at = @At("TAIL"))
    protected void onDrop(CallbackInfo callbackInfo) {
        LivingEntity self = (LivingEntity) (Object) this;
        ItemStack stack = self.getEquippedStack(EquipmentSlot.HEAD);
        if (stack.getItem() == Items.SADDLE) self.dropStack(stack);
    }

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
