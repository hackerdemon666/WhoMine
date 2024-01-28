package minersstudios.whomine.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntitiesRegistry {
    public static final BlockEntityType<DyeableBlockEntity> DYEABLE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier("whomine", "dyeable_block_entity"),
            FabricBlockEntityTypeBuilder.create(DyeableBlockEntity::new).build()
    );

    public static void register() {}
}