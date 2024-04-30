package minersstudios.whomine.item;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.block.properties.WoodType;
import minersstudios.whomine.item.items.DyeableHatItem;
import minersstudios.whomine.item.items.HatItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
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
                for (Item item : ModItemsRegistry.CARVED_PLANKS_ITEMS) {
                    entries.add(item);
                }
                for (Item item : ModItemsRegistry.FRAMED_PLANKS_ITEMS) {
                    entries.add(item);
                }
                for (Item item : ModItemsRegistry.VERTICAL_PLANKS_ITEMS) {
                    entries.add(item);
                }

                entries.add(ModBlocksRegistry.PLUMBUM_ORE);
                entries.add(ModBlocksRegistry.PLUMBUM_BLOCK);
                entries.add(ModBlocksRegistry.RAW_PLUMBUM_BLOCK);

                entries.add(ModItemsRegistry.COLOR_BLOCK);
            }).build()
    );

    public static final ItemGroup WHOMINE_FURNITURE_GROUP = Registry.register(Registries.ITEM_GROUP,
        new Identifier(WhoMineMod.MOD_ID, "whomine_furniture_group"),
        FabricItemGroup.builder().displayName(Text.translatable("itemgroup.whomine_furniture"))
            .icon(() -> new ItemStack(ModItemsRegistry.BIG_ARMCHAIR_ITEMS[0])).entries((displayContext, entries) -> {
                entries.add(ModItemsRegistry.COOL_CHAIR);
                entries.add(ModItemsRegistry.BAR_CHAIR);

                for (Item item : ModItemsRegistry.BIG_ARMCHAIR_ITEMS) {
                    entries.add(item);
                }
                for (Item item : ModItemsRegistry.BIG_CHAIR_ITEMS) {
                    entries.add(item);
                }
                for (Item item : ModItemsRegistry.SMALL_ARMCHAIR_ITEMS) {
                    entries.add(item);
                }
                for (Item item : ModItemsRegistry.SMALL_CHAIR_ITEMS) {
                    entries.add(item);
                }

                for (Item item : ModItemsRegistry.CHESS_ITEMS) {
                    entries.add(item);
                }
            }).build()
    );

    public static final ItemGroup WHOMINE_HAT_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(WhoMineMod.MOD_ID, "whomine_hat_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.whomine_hat"))
                    .icon(() -> new ItemStack(ModItemsRegistry.LEATHER_HAT)).entries((displayContext, entries) -> {
                        entries.add(ModItemsRegistry.LEATHER_HAT);
                        entries.add(ModItemsRegistry.SANTA_HAT);
                        entries.add(ModItemsRegistry.GAS_MASK);
                        entries.add(ModItemsRegistry.GLASSES_3D);
                        for (Item item : ModItemsRegistry.BEE) {
                            entries.add(item);
                        }
                        entries.add(ModItemsRegistry.PHANTOM);
                        entries.add(ModItemsRegistry.BERET);
                        entries.add(ModItemsRegistry.CAP);
                        entries.add(ModItemsRegistry.CARDBOARD_CROWN);
                        entries.add(ModItemsRegistry.CHEF_HAT);
                        entries.add(ModItemsRegistry.POVAR_HAT);
                        entries.add(ModItemsRegistry.ENCHANTER_HAT);
                        entries.add(ModItemsRegistry.GOAT_HEAD);
                        entries.add(ModItemsRegistry.MEDICINE_MASK);

                        for (Item item : ModItemsRegistry.NEADER_MASK) {
                            entries.add(item);
                        }
                        for (Item item : ModItemsRegistry.GOLDEN_PROTHESIS) {
                            entries.add(item);
                        }
                        for (Item item : ModItemsRegistry.SILVER_PROTHESIS) {
                            entries.add(item);
                        }
                        for (Item item : ModItemsRegistry.SAMURAI) {
                            entries.add(item);
                        }

                        entries.add(ModItemsRegistry.RACCOON_HAT);
                        entries.add(ModItemsRegistry.SCULK);
                        entries.add(ModItemsRegistry.SUN_HAT);
                        entries.add(ModItemsRegistry.WAITER_CAP);
                        entries.add(ModItemsRegistry.WESTERN_HAT);
                        entries.add(ModItemsRegistry.WOLF_HEAD);

                        for (Item item : ModItemsRegistry.VILLAGER_PROFESSION_HAT) {
                            entries.add(item);
                        }
                        for (Item item : ModItemsRegistry.VILLAGER_TYPE_HAT) {
                            entries.add(item);
                        }

                        entries.add(ModItemsRegistry.WITCH_HAT);
                        entries.add(ModItemsRegistry.CIGARETTE);
                    }).build()
    );

    public static final ItemGroup WHOMINE_OTHER_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(WhoMineMod.MOD_ID, "whomine_other_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.whomine_other"))
                    .icon(() -> new ItemStack(ModItemsRegistry.PLUMBUM_INGOT)).entries((displayContext, entries) -> {
                        entries.add(ModItemsRegistry.WRENCH);

                        entries.add(ModItemsRegistry.PLUMBUM_INGOT);
                        entries.add(ModItemsRegistry.RAW_PLUMBUM);
                        entries.add(ModItemsRegistry.SADDLE);
                    }).build()
    );

    public static void register() {
        WhoMineMod.LOGGER.info("Registering ItemGroups for " + WhoMineMod.MOD_NAME);
    }
}
