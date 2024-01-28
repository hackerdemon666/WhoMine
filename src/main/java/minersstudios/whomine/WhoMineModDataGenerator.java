package minersstudios.whomine;

import minersstudios.whomine.datagen.ModBlockTagsProvider;
import minersstudios.whomine.datagen.ModItemTagsProvider;
import minersstudios.whomine.datagen.ModLootTablesProvider;
import minersstudios.whomine.datagen.ModRecipesProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WhoMineModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModLootTablesProvider::new);
		pack.addProvider(ModRecipesProvider::new);

		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModItemTagsProvider::new);
	}
}
