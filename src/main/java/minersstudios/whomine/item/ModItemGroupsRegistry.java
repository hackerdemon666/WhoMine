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
    public static final ItemGroup WHOMINE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(WhoMineMod.MOD_ID, "whomine"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.whomine"))
                    .icon(() -> new ItemStack(ModItemsRegistry.PLUMBUM_INGOT)).entries((displayContext, entries) -> {
                        entries.add(ModItemsRegistry.WRENCH);
                        entries.add(ModItemsRegistry.HAT);
                        entries.add(ModItemsRegistry.LEATHER_HAT);

                        entries.add(ModItemsRegistry.SMALL_CHAIR);

                        entries.add(ModItemsRegistry.OAK_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.SPRUCE_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.BIRCH_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.ACACIA_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.CHERRY_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.JUNGLE_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.DARK_OAK_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.CRIMSON_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.WARPED_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.MANGROVE_BIG_ARMCHAIR);
                        entries.add(ModItemsRegistry.BAMBOO_BIG_ARMCHAIR);

                        entries.add(ModItemsRegistry.PLUMBUM_INGOT);
                        entries.add(ModItemsRegistry.RAW_PLUMBUM);
                        entries.add(ModBlocksRegistry.PLUMBUM_ORE);
                        entries.add(ModBlocksRegistry.PLUMBUM_BLOCK);
                        entries.add(ModBlocksRegistry.RAW_PLUMBUM_BLOCK);
                    }).build()
    );

    public static void register() {}
}
