package minersstudios.whomine.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.item.ModItemsRegistry;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.*;
import net.minecraft.recipe.book.RecipeCategory;
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

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItemsRegistry.BIG_ARMCHAIR, 1)
                .pattern("WW ")
                .pattern("WLW")
                .pattern("W W")
                .input('W', Items.OAK_PLANKS)
                .input('L', Items.LEATHER)
                .criterion(hasItem(Items.OAK_PLANKS), conditionsFromItem(Items.OAK_PLANKS))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItemsRegistry.BIG_ARMCHAIR)));
    }
}