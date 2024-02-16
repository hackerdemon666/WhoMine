package minersstudios.whomine.item;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.block.properties.WoodType;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModItemsRegistry {
    public static final ArrayList<Item> DyeableItems = new ArrayList();

    public static final Item[] BIG_ARMCHAIR_ITEMS = getWoodItems(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), true);
    public static final Item[] COUCH_ITEMS = getWoodItems(ModBlocksRegistry.COUCH, new Item.Settings().maxCount(16), true);

    public static final Item[] CARVED_PLANKS_ITEMS = getWoodItems(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(),false);

    public static final Item COLOR_BLOCK = registerDyeableItem("color_block", new DyeableBlockItem(ModBlocksRegistry.COLOR_BLOCK, new Item.Settings()));

//    public static final Block CARVED_PLANKS = registerItem("carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.OAK);
//    public static final Block FRAMED_PLANKS = registerItem("framed_planks", );
//    public static final Block VERTICAL_PLANKS = registerItem("vertical_planks", );

    public static final Item RAW_PLUMBUM = registerItem("raw_plumbum", new Item(new Item.Settings()));
    public static final Item PLUMBUM_INGOT = registerItem("plumbum_ingot", new Item(new Item.Settings()));

    public static final Item WRENCH = registerItem("wrench", new WrenchItem(new Item.Settings().maxCount(1)));

    public static final Item HAT = registerItem("hat", new HatItem(new Item.Settings().maxCount(1)));
    public static final Item LEATHER_HAT = registerDyeableItem("leather_hat", new DyeableHatItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(WhoMineMod.MOD_ID, name), item);
    }

    private static Item registerDyeableItem(String name, Item item) {
        Item newItem = Registry.register(Registries.ITEM, new Identifier(WhoMineMod.MOD_ID, name), item);
        DyeableItems.add(newItem);
        return newItem;
    }

    private static Item[] getWoodItems(Block block, Item.Settings settings, boolean isDyeable) {
        Item[] woodItems = new Item[WoodType.values().length];
        for (int i = 0; i < WoodType.values().length; i++) {
            WoodType woodType = WoodType.getById(i);
            if (isDyeable) {
                woodItems[i] = registerItem(woodType.getName() + "_" + Registries.BLOCK.getId(block).getPath(), new DyeableWoodBlockItem(block, settings, woodType));
                DyeableItems.add(woodItems[i]);
            } else {
                woodItems[i] = registerItem(woodType.getName() + "_" + Registries.BLOCK.getId(block).getPath(), new WoodBlockItem(block, settings, woodType));
            }
        }
        return woodItems;
    }

    public static void register() {
        WhoMineMod.LOGGER.info("Registering Items for " + WhoMineMod.MOD_NAME);
    }
}
