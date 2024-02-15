package minersstudios.whomine.item;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.block.properties.WoodType;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModItemGroupsRegistry {
    public static final ItemGroup WHOMINE_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
        new Identifier(WhoMineMod.MOD_ID, "whomine_block_group"),
        FabricItemGroup.builder().displayName(Text.translatable("itemgroup.whomine_block"))
            .icon(() -> new ItemStack(ModItemsRegistry.COLOR_BLOCK)).entries((displayContext, entries) -> {
                for (int i = 0; i < WoodType.values().length; i++) {
                    entries.add(ModItemsRegistry.CARVED_PLANKS_ITEMS[i]);
                }

                entries.add(ModBlocksRegistry.PLUMBUM_ORE);
                entries.add(ModBlocksRegistry.PLUMBUM_BLOCK);
                entries.add(ModBlocksRegistry.RAW_PLUMBUM_BLOCK);

                entries.add(ModItemsRegistry.COLOR_BLOCK);
            }).build()
    );

    public static final ItemGroup WHOMINE_FURNITURE_GROUP = Registry.register(Registries.ITEM_GROUP,
        new Identifier(WhoMineMod.MOD_ID, "whomine_furniture_group_furniture"),
        FabricItemGroup.builder().displayName(Text.translatable("itemgroup.whomine_furniture"))
            .icon(() -> new ItemStack(ModItemsRegistry.BIG_ARMCHAIR_ITEMS[0])).entries((displayContext, entries) -> {
                for (int i = 0; i < WoodType.values().length; i++) {
                    entries.add(ModItemsRegistry.BIG_ARMCHAIR_ITEMS[i]);
                }
                for (int i = 0; i < WoodType.values().length; i++) {
                    entries.add(ModItemsRegistry.COUCH_ITEMS[i]);
                }
            }).build()
    );

    public static final ItemGroup WHOMINE_OTHER_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(WhoMineMod.MOD_ID, "whomine_other_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.whomine_other"))
                    .icon(() -> new ItemStack(ModItemsRegistry.PLUMBUM_INGOT)).entries((displayContext, entries) -> {
                        entries.add(ModItemsRegistry.WRENCH);
                        entries.add(ModItemsRegistry.HAT);
                        entries.add(ModItemsRegistry.LEATHER_HAT);

                        entries.add(ModItemsRegistry.PLUMBUM_INGOT);
                        entries.add(ModItemsRegistry.RAW_PLUMBUM);
                    }).build()
    );

//    public static ArrayList<Item> getWoodItems(Block block){
//        ArrayList<Item> WoodItems = new ArrayList();
//        for (int i = 0; i < WoodType.values().length; i++) {
//            WoodType woodType = WoodType.getById(i);
//            Identifier id = new Identifier(WhoMineMod.MOD_ID, woodType.getName() + "_" + Registries.BLOCK.getId(block).getPath());
//            WoodItems.add(Registries.ITEM.get(id));
//        }
//        return WoodItems;
//    }

    public static void register() {}
}
