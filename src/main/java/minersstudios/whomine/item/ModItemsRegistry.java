package minersstudios.whomine.item;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.block.properties.WoodType;
import minersstudios.whomine.item.items.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModItemsRegistry {
    public static final ArrayList<Item> DyeableItems = new ArrayList();

    //CHAIRS
    public static final Item BAR_CHAIR = registerDyeableItem("bar_chair", new DyeableBlockItem(ModBlocksRegistry.BAR_CHAIR, new Item.Settings().maxCount(16)));
    public static final Item COOL_CHAIR = registerDyeableItem("cool_chair", new DyeableBlockItem(ModBlocksRegistry.COOL_CHAIR, new Item.Settings().maxCount(16)));
    public static final Item[] BIG_ARMCHAIR_ITEMS = registerWoodItems(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), true);
    public static final Item[] BIG_CHAIR_ITEMS = registerWoodItems(ModBlocksRegistry.BIG_CHAIR, new Item.Settings().maxCount(16), true);
    public static final Item[] SMALL_ARMCHAIR_ITEMS = registerWoodItems(ModBlocksRegistry.SMALL_ARMCHAIR, new Item.Settings().maxCount(16), true);
    public static final Item[] SMALL_CHAIR_ITEMS = registerWoodItems(ModBlocksRegistry.SMALL_CHAIR, new Item.Settings().maxCount(16), true);

//    public static final Item[] COUCH_ITEMS = getWoodItems(ModBlocksRegistry.COUCH, new Item.Settings().maxCount(16), true);

    public static final Item[] CARVED_PLANKS_ITEMS = registerWoodItems(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(),false);

    public static final Item COLOR_BLOCK = registerDyeableItem("color_block", new DyeableBlockItem(ModBlocksRegistry.COLOR_BLOCK, new Item.Settings()));

//    public static final Block CARVED_PLANKS = registerItem("carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.OAK);
//    public static final Block FRAMED_PLANKS = registerItem("framed_planks", );
//    public static final Block VERTICAL_PLANKS = registerItem("vertical_planks", );

    public static final Item RAW_PLUMBUM = registerItem("raw_plumbum", new Item(new Item.Settings()));
    public static final Item PLUMBUM_INGOT = registerItem("plumbum_ingot", new Item(new Item.Settings()));

    public static final Item WRENCH = registerItem("wrench", new WrenchItem(new Item.Settings().maxCount(1)));

    public static final Item LEATHER_HAT = registerDyeableItem("leather_hat", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item SANTA_HAT = registerDyeableItem("santa_hat", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item GAS_MASK = registerItem("gas_mask", new HatItem(new Item.Settings().maxCount(1)));
    public static final Item GLASSES_3D = registerItem("3d_glasses", new HatItem(new Item.Settings().maxCount(1)));
    public static final Item[] BEE = registerHatItems(new String[] {"bee", "bee_nectar", "bee_angry", "bee_angry_nectar"}, false);
    public static final Item PHANTOM = registerItem("phantom", new HatItem(new Item.Settings().maxCount(1)));
    public static final Item BERET = registerDyeableItem("beret", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item CAP = registerDyeableItem("cap", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item CARDBOARD_CROWN = registerDyeableItem("cardboard_crown", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item CHEF_HAT = registerDyeableItem("chef_hat", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item POVAR_HAT = registerDyeableItem("povar_hat", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item ENCHANTER_HAT = registerItem("enchanter_hat", new HatItem(new Item.Settings().maxCount(1)));
    public static final Item GOAT_HEAD = registerItem("goat_head", new HatItem(new Item.Settings().maxCount(1)));
    public static final Item MEDICINE_MASK = registerItem("medicine_mask", new HatItem(new Item.Settings().maxCount(1)));

    public static final Item[] NEADER_MASK = registerHatItems(new String[] {"neader_mask", "neader_mask_o", "neader_mask_leaves", "neader_mask_leaves_o"}, true);
    public static final Item[] GOLDEN_PROTHESIS = registerHatItems(new String[] {"golden_prothesis_left", "golden_prothesis_right"}, true);
    public static final Item[] SILVER_PROTHESIS = registerHatItems(new String[] {"silver_prothesis_left", "silver_prothesis_right"}, true);

    public static final Item[] SAMURAI = registerHatItems(new String[] {"samurai", "samurai_mask"}, true);

    public static final Item RACCOON_HAT = registerItem("raccoon_hat", new HatItem(new Item.Settings().maxCount(1)));
    public static final Item SCULK = registerItem("sculk", new HatItem(new Item.Settings().maxCount(1)));
    public static final Item SUN_HAT = registerDyeableItem("sun_hat", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item WAITER_CAP = registerDyeableItem("waiter_cap", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item WESTERN_HAT = registerDyeableItem("western_hat", new DyeableHatItem(new Item.Settings().maxCount(1)));
    public static final Item WOLF_HEAD = registerDyeableItem("wolf_head", new DyeableHatItem(new Item.Settings().maxCount(1)));

    public static final Item[] VILLAGER_PROFESSION_HAT = registerHatItems(new String[] {"armorer_hat", "butcher_hat", "cartographer_hat", "farmer_hat", "fisherman_hat", "fletcher_hat", "librarian_hat", "shepherd_hat"}, false);
    public static final Item[] VILLAGER_TYPE_HAT = registerHatItems(new String[] {"desert_hat", "savanna_hat", "snow_hat", "swamp_hat"}, false);

    public static final Item WITCH_HAT = registerItem("witch_hat", new HatItem(new Item.Settings().maxCount(1)));

    public static final Item CIGARETTE = registerItem("cigarette", new HatItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(WhoMineMod.MOD_ID, name), item);
    }

    private static Item registerDyeableItem(String name, Item item) {
        Item newItem = Registry.register(Registries.ITEM, new Identifier(WhoMineMod.MOD_ID, name), item);
        DyeableItems.add(newItem);
        return newItem;
    }

    private static Item[] registerHatItems(String[] idItems, boolean isDyeable) {
        Item[] newItems = new Item[idItems.length];
        for (int i = 0; i < idItems.length; i++) {
            Item item;
            if (isDyeable) {
                item = new DyeableHatItem(new Item.Settings().maxCount(1));
                DyeableItems.add(item);
            } else {
                item = new HatItem(new Item.Settings().maxCount(1));
            }
            newItems[i] = registerItem(idItems[i], item);
        }
        return newItems;
    }

    private static Item[] registerWoodItems(Block block, Item.Settings settings, boolean isDyeable) {
        Item[] woodItems = new Item[WoodType.values().length];
        for (WoodType woodType : WoodType.values()) {
            int i = woodType.getId();
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
