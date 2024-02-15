package minersstudios.whomine.effect;

import minersstudios.whomine.WhoMineMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffectsRegistry {
    public static final StatusEffect RADIATION = registerEffect ("radiation", new RadiationStatusEffect());

    private static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(WhoMineMod.MOD_ID, name), effect);
    }

    public static void register() {}
}
