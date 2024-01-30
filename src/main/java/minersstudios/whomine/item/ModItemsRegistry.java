package minersstudios.whomine.item;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.block.WoodType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModItemsRegistry {
    public static final ArrayList<Item> registry = new ArrayList();
    public static final Item OAK_BIG_ARMCHAIR = registerWoodTypeItem("oak_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.OAK));
    public static final Item SPRUCE_BIG_ARMCHAIR = registerWoodTypeItem("spruce_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.SPRUCE));
    public static final Item BIRCH_BIG_ARMCHAIR = registerWoodTypeItem("birch_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.BIRCH));
    public static final Item ACACIA_BIG_ARMCHAIR = registerWoodTypeItem("acacia_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.ACACIA));
    public static final Item CHERRY_BIG_ARMCHAIR = registerWoodTypeItem("cherry_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.CHERRY));
    public static final Item JUNGLE_BIG_ARMCHAIR = registerWoodTypeItem("jungle_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.JUNGLE));
    public static final Item DARK_OAK_BIG_ARMCHAIR = registerWoodTypeItem("dark_oak_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.DARK_OAK));
    public static final Item CRIMSON_BIG_ARMCHAIR = registerWoodTypeItem("crimson_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.CRIMSON));
    public static final Item WARPED_BIG_ARMCHAIR = registerWoodTypeItem("warped_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.WARPED));
    public static final Item MANGROVE_BIG_ARMCHAIR = registerWoodTypeItem("mangrove_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.MANGROVE));
    public static final Item BAMBOO_BIG_ARMCHAIR = registerWoodTypeItem("bamboo_big_armchair", new DyeableBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.BAMBOO));

    public static final Item SMALL_CHAIR = registerItem("small_chair", new DyeableBlockItem(ModBlocksRegistry.SMALL_CHAIR, new Item.Settings().maxCount(16), WoodType.CHERRY));

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

    private static Item registerWoodTypeItem(String name, Item item) {
        Item newItem = Registry.register(Registries.ITEM, new Identifier(WhoMineMod.MOD_ID, name), item);
        ModItemsRegistry.registry.add(newItem);
        return newItem;
    }

    public static void register() {}
}
