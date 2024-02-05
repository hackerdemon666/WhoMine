package minersstudios.whomine.item;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.ModBlockCollisionType;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.block.WoodBlock;
import minersstudios.whomine.block.WoodType;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModItemsRegistry {
    public static final ArrayList<Item> DyeableItems = new ArrayList();
    public static final Item OAK_BIG_ARMCHAIR = registerDyeableItem("oak_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.OAK));
    public static final Item SPRUCE_BIG_ARMCHAIR = registerDyeableItem("spruce_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.SPRUCE));
    public static final Item BIRCH_BIG_ARMCHAIR = registerDyeableItem("birch_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.BIRCH));
    public static final Item ACACIA_BIG_ARMCHAIR = registerDyeableItem("acacia_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.ACACIA));
    public static final Item JUNGLE_BIG_ARMCHAIR = registerDyeableItem("jungle_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.JUNGLE));
    public static final Item DARK_OAK_BIG_ARMCHAIR = registerDyeableItem("dark_oak_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.DARK_OAK));
    public static final Item MANGROVE_BIG_ARMCHAIR = registerDyeableItem("mangrove_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.MANGROVE));
    public static final Item CHERRY_BIG_ARMCHAIR = registerDyeableItem("cherry_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.CHERRY));
    public static final Item BAMBOO_BIG_ARMCHAIR = registerDyeableItem("bamboo_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.BAMBOO));
    public static final Item CRIMSON_BIG_ARMCHAIR = registerDyeableItem("crimson_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.CRIMSON));
    public static final Item WARPED_BIG_ARMCHAIR = registerDyeableItem("warped_big_armchair", new DyeableWoodBlockItem(ModBlocksRegistry.BIG_ARMCHAIR, new Item.Settings().maxCount(16), WoodType.WARPED));

    public static final Item OAK_CARVED_PLANKS = registerDyeableItem("oak_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.OAK));
    public static final Item SPRUCE_CARVED_PLANKS = registerDyeableItem("spruce_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.SPRUCE));
    public static final Item BIRCH_CARVED_PLANKS= registerDyeableItem("birch_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.BIRCH));
    public static final Item ACACIA_CARVED_PLANKS = registerDyeableItem("acacia_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.ACACIA));
    public static final Item JUNGLE_CARVED_PLANKS = registerDyeableItem("jungle_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.JUNGLE));
    public static final Item DARK_OAK_CARVED_PLANKS = registerDyeableItem("dark_oak_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.DARK_OAK));
    public static final Item MANGROVE_CARVED_PLANKS = registerDyeableItem("mangrove_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.MANGROVE));
    public static final Item CHERRY_CARVED_PLANKS = registerDyeableItem("cherry_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.CHERRY));
    public static final Item BAMBOO_CARVED_PLANKS = registerDyeableItem("bamboo_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.BAMBOO));
    public static final Item CRIMSON_CARVED_PLANKS = registerDyeableItem("crimson_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.CRIMSON));
    public static final Item WARPED_CARVED_PLANKS = registerDyeableItem("warped_carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.WARPED));

    public static final Item COLOR_BLOCK = registerDyeableItem("color_block", new DyeableBlockItem(ModBlocksRegistry.COLOR_BLOCK, new Item.Settings()));

//    public static final Block CARVED_PLANKS = registerItem("carved_planks", new WoodBlockItem(ModBlocksRegistry.CARVED_PLANKS, new Item.Settings(), WoodType.OAK);
//    public static final Block FRAMED_PLANKS = registerItem("framed_planks", );
//    public static final Block VERTICAL_PLANKS = registerItem("vertical_planks", );

    public static final Item PLUMBUM_ORE = registerItem("plumbum_ore", new BlockItem(ModBlocksRegistry.PLUMBUM_ORE, new Item.Settings()));
    public static final Item PLUMBUM_BLOCK = registerItem("plumbum_block", new BlockItem(ModBlocksRegistry.PLUMBUM_BLOCK, new Item.Settings()));
    public static final Item RAW_PLUMBUM_BLOCK = registerItem("raw_plumbum_block", new BlockItem(ModBlocksRegistry.RAW_PLUMBUM_BLOCK, new Item.Settings()));

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

    public static void register() {}
}
