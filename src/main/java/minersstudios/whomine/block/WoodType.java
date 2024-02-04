package minersstudios.whomine.block;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.item.ModItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public enum WoodType implements StringIdentifiable {
    OAK("oak", BlockSoundGroup.WOOD),
    SPRUCE("spruce", BlockSoundGroup.WOOD),
    BIRCH("birch", BlockSoundGroup.WOOD),
    ACACIA("acacia", BlockSoundGroup.WOOD),
    JUNGLE("jungle", BlockSoundGroup.WOOD),
    DARK_OAK("dark_oak", BlockSoundGroup.WOOD),
    MANGROVE("mangrove", BlockSoundGroup.WOOD),
    CHERRY("cherry", BlockSoundGroup.CHERRY_WOOD),
    BAMBOO("bamboo", BlockSoundGroup.BAMBOO_WOOD),
    CRIMSON("crimson", BlockSoundGroup.NETHER_WOOD),
    WARPED("warped", BlockSoundGroup.NETHER_WOOD);

    private final String name;
    private final BlockSoundGroup soundGroup;

    WoodType(String name, BlockSoundGroup soundGroup) {
        this.name = name;
        this.soundGroup = soundGroup;
    }

    public String getName() {
        return name;
    }

    public BlockSoundGroup getSoundGroup() {
        return soundGroup;
    }

    public static ItemStack getWoodBlockItem(BlockState state) {
        WoodType woodType = state.get(WoodTypeProperty.of("wood_type"));

        String id = WhoMineMod.MOD_ID + ":" + woodType.getName() + "_" + Registries.BLOCK.getId(state.getBlock()).getPath();
        return Registries.ITEM.get(new Identifier(id)).getDefaultStack();
    }

    @Override
    public String asString() {
        return getName();
    }

    public static WoodType get(String name) {
        for (WoodType type : values()) if (type.getName().equals(name)) return type;
        return OAK;
    }
}
