package minersstudios.whomine.event;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.event.events.SitOnUseBlock;

public class EventHandler {
    public static void register() {
        WhoMineMod.LOGGER.debug("Registering Events, for: " + WhoMineMod.MOD_NAME);

        SitOnUseBlock.register();
    }
}