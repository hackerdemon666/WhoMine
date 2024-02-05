package minersstudios.whomine.item;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.ModBlocksRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroupsRegistry {
    public static final ItemGroup WHOMINE_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
        new Identifier(WhoMineMod.MOD_ID, "whomine_block_group"),
        FabricItemGroup.builder().displayName(Text.translatable("itemgroup.whomine_block"))
            .icon(() -> new ItemStack(ModItemsRegistry.COLOR_BLOCK)).entries((displayContext, entries) -> {
                entries.add(ModItemsRegistry.OAK_CARVED_PLANKS);
                entries.add(ModItemsRegistry.SPRUCE_CARVED_PLANKS);
                entries.add(ModItemsRegistry.BIRCH_CARVED_PLANKS);
                entries.add(ModItemsRegistry.JUNGLE_CARVED_PLANKS);
                entries.add(ModItemsRegistry.ACACIA_CARVED_PLANKS);
                entries.add(ModItemsRegistry.DARK_OAK_CARVED_PLANKS);
                entries.add(ModItemsRegistry.MANGROVE_CARVED_PLANKS);
                entries.add(ModItemsRegistry.CHERRY_CARVED_PLANKS);
                entries.add(ModItemsRegistry.BAMBOO_CARVED_PLANKS);
                entries.add(ModItemsRegistry.CRIMSON_CARVED_PLANKS);
                entries.add(ModItemsRegistry.WARPED_CARVED_PLANKS);

                entries.add(ModBlocksRegistry.PLUMBUM_ORE);
                entries.add(ModBlocksRegistry.PLUMBUM_BLOCK);
                entries.add(ModBlocksRegistry.RAW_PLUMBUM_BLOCK);

                entries.add(ModItemsRegistry.COLOR_BLOCK);
            }).build()
    );

    public static final ItemGroup WHOMINE_FURNITURE_GROUP = Registry.register(Registries.ITEM_GROUP,
        new Identifier(WhoMineMod.MOD_ID, "whomine_furniture_group_furniture"),
        FabricItemGroup.builder().displayName(Text.translatable("itemgroup.whomine_furniture"))
            .icon(() -> new ItemStack(ModItemsRegistry.OAK_BIG_ARMCHAIR)).entries((displayContext, entries) -> {
                entries.add(ModItemsRegistry.OAK_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.SPRUCE_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.BIRCH_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.JUNGLE_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.ACACIA_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.DARK_OAK_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.MANGROVE_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.CHERRY_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.BAMBOO_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.CRIMSON_BIG_ARMCHAIR);
                entries.add(ModItemsRegistry.WARPED_BIG_ARMCHAIR);
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

    public static void register() {}
}
