package minersstudios.whomine.item.items;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;

public class DyeableBlockItem extends BlockItem implements DyeableItem {
    public DyeableBlockItem(Block block, Item.Settings settings) {
        super(block, settings);
    }

    public boolean isPainted(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("BlockEntityTag") == null ? stack.getSubNbt("display") : DyeableBlockItem.getBlockEntityNbt(stack).getCompound("tag").getCompound("display");
        return nbtCompound != null && nbtCompound.contains("color", 99);
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("BlockEntityTag") == null ? stack.getSubNbt("display") : DyeableBlockItem.getBlockEntityNbt(stack).getCompound("tag").getCompound("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 10511680;
    }

    @Override
    public String getTranslationKey() {
        return this.getOrCreateTranslationKey();
    }
}