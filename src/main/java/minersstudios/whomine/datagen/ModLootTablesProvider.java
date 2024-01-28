package minersstudios.whomine.datagen;

import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.item.ModItemsRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTablesProvider extends FabricBlockLootTableProvider {
    public ModLootTablesProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocksRegistry.PLUMBUM_ORE, copperLikeOreDrops(ModBlocksRegistry.PLUMBUM_ORE, ModItemsRegistry.RAW_PLUMBUM));
        addDrop(ModBlocksRegistry.PLUMBUM_BLOCK);
        addDrop(ModBlocksRegistry.RAW_PLUMBUM_BLOCK);

        addDrop(ModBlocksRegistry.SMALL_CHAIR, ModItemsRegistry.SMALL_CHAIR);
        addDrop(ModBlocksRegistry.BIG_ARMCHAIR, ModItemsRegistry.BIG_ARMCHAIR);
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop,
                ((LeafEntry.Builder<?>)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider
                                                .create(2.0f, 5.0f))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}