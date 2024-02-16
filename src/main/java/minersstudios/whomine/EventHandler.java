package minersstudios.whomine;

public class EventHandler {
    public static void register() {
        WhoMineMod.LOGGER.debug("Registering Events, for: " + WhoMineMod.MOD_NAME);

        SitOnUseBlock.register();
    }
}