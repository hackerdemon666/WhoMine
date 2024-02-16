package minersstudios.whomine.effect;

import minersstudios.whomine.WhoMineMod;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypesRegistry {
    public static final RegistryKey<DamageType> RADIATION_DAMAGE_TYPE = registerDamageType("radiation");

    private static RegistryKey registerDamageType(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(WhoMineMod.MOD_ID, name));
    }

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    public static void register() {
        WhoMineMod.LOGGER.info("Registering DamageTypes for " + WhoMineMod.MOD_NAME);
    }
}