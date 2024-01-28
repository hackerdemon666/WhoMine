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
        this.setColor(new ItemStack(this), 10511680);
    }

    private void createWoodType(WoodType type) {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("type", type.getName());
    }
}
