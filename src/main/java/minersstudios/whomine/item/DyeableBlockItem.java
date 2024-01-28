package minersstudios.whomine.item;

import minersstudios.whomine.block.WoodType;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class DyeableBlockItem extends BlockItem implements DyeableItem {
    public DyeableBlockItem(Block block, Item.Settings settings) {
        super(block, settings);
    }


    public boolean isPainted(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99);
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 16383998;
    }

    public WoodType getType(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("type") ? WoodType.get(nbtCompound.getString("type")) : WoodType.OAK;
    }

    public void setType(ItemStack stack, WoodType type) {
        stack.getOrCreateSubNbt("display").putString("type", type.getName());
    }
}
