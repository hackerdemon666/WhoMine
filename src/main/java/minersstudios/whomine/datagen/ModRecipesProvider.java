package minersstudios.whomine.datagen;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.properties.WoodType;
import minersstudios.whomine.block.properties.WoodTypeProperty;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.item.ModItemsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    private static final List<ItemConvertible> PLUMBUM_SMELTABLES = List.of(ModItemsRegistry.RAW_PLUMBUM,
            ModBlocksRegistry.PLUMBUM_ORE);

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, PLUMBUM_SMELTABLES, RecipeCategory.MISC, ModItemsRegistry.PLUMBUM_INGOT,
                0.7f, 200, "plumbum");
        offerBlasting(exporter, PLUMBUM_SMELTABLES, RecipeCategory.MISC, ModItemsRegistry.PLUMBUM_INGOT,
                0.7f, 100, "plumbum");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItemsRegistry.PLUMBUM_INGOT, RecipeCategory.DECORATIONS,
                ModBlocksRegistry.PLUMBUM_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItemsRegistry.RAW_PLUMBUM, RecipeCategory.DECORATIONS,
                ModBlocksRegistry.RAW_PLUMBUM_BLOCK);

        // Wooden Recipes
        for (int i = 0; i < WoodType.values().length; i++) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItemsRegistry.BIG_ARMCHAIR_ITEMS[i], 1)
                    .pattern("WW ")
                    .pattern("WLW")
                    .pattern("W W")
                    .input('W', getWoodItem("planks", i))
                    .input('L', Items.LEATHER)
                    .criterion(hasItem(getWoodItem("planks", i)), conditionsFromItem(getWoodItem("planks", i)))
                    .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                    .offerTo(exporter, new Identifier(getRecipeName(ModItemsRegistry.BIG_ARMCHAIR_ITEMS[i])));
        }
    }

    private static Item getWoodItem(String block, int woodId) {
        Identifier id = new Identifier(WoodType.getById(woodId).getName() + "_" + block);
        return Registries.ITEM.get(id);
    }
}