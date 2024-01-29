package minersstudios.whomine;

import minersstudios.whomine.block.ModBlockEntitiesRegistry;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.command.ModCommandsRegistry;
import minersstudios.whomine.entity.ModEntitiesRegistry;
import minersstudios.whomine.item.ModItemGroupsRegistry;
import minersstudios.whomine.item.ModItemsRegistry;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhoMineMod implements ModInitializer {
	public static final String MOD_ID = "whomine";
    public static final Logger LOGGER = LoggerFactory.getLogger("whomine");


	@Override
	public void onInitialize() {
		ModBlocksRegistry.register();
		ModBlockEntitiesRegistry.register();
		ModCommandsRegistry.register();
		ModEntitiesRegistry.register();
		ModItemsRegistry.register();
		ModItemGroupsRegistry.register();

		SitOnUseBlock.register();
	}
}