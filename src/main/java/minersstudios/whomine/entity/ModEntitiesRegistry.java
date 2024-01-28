package minersstudios.whomine.entity;

import minersstudios.whomine.WhoMineMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntitiesRegistry {
    public static final EntityType<SitEntity> SIT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(WhoMineMod.MOD_ID, SitEntity.ENTITY_ID),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, SitEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

    public static void register() {
        WhoMineMod.LOGGER.info("Registering Entities for " + WhoMineMod.MOD_ID);
    }
}
