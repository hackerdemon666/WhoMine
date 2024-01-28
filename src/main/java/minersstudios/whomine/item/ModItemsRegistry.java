package minersstudios.whomine.item;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.ModBlocksRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItemsRegistry {
    public static final Item BIG_ARMCHAIR = registerItem("big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16)));
    public static final Item SMALL_CHAIR = registerItem("small_chair", new DyeableBlockItem(ModBlocksRegistry.SMALL_CHAIR, new Item.Settings().maxCount(16)));

    public static final Item PLUMBUM_ORE = registerItem("plumbum_ore", new BlockItem(ModBlocksRegistry.PLUMBUM_ORE, new Item.Settings()));
    public static final Item PLUMBUM_BLOCK = registerItem("plumbum_block", new BlockItem(ModBlocksRegistry.PLUMBUM_BLOCK, new Item.Settings()));
    public static final Item RAW_PLUMBUM_BLOCK = registerItem("raw_plumbum_block", new BlockItem(ModBlocksRegistry.RAW_PLUMBUM_BLOCK, new Item.Settings()));

    public static final Item RAW_PLUMBUM = registerItem("raw_plumbum", new Item(new Item.Settings()));
    public static final Item PLUMBUM_INGOT = registerItem("plumbum_ingot", new Item(new Item.Settings()));

    public static final Item WRENCH = registerItem("wrench", new WrenchItem(new Item.Settings().maxCount(1)));

    public static final Item HAT = registerItem("hat", new HatItem(new Item.Settings().maxCount(1)));
    public static final Item LEATHER_HAT = registerItem("leather_hat", new DyeableHatItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(WhoMineMod.MOD_ID, name), item);
    }

    public static void register() {}
}
