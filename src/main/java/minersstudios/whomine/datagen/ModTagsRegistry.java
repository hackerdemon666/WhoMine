package minersstudios.whomine.datagen;

import minersstudios.whomine.WhoMineMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTagsRegistry {
    public static class Blocks {
        public static final TagKey<Block> CHAIR_BLOCKS =
                createTag("chair_blocks");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(WhoMineMod.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(WhoMineMod.MOD_ID, name));
        }
    }
}