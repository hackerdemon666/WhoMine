package minersstudios.whomine.block.properties;

import minersstudios.whomine.WhoMineMod;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

public enum WoodType implements StringIdentifiable {
    OAK("oak", 0, BlockSoundGroup.WOOD),
    SPRUCE("spruce", 1, BlockSoundGroup.WOOD),
    BIRCH("birch", 2, BlockSoundGroup.WOOD),
    ACACIA("acacia", 3, BlockSoundGroup.WOOD),
    JUNGLE("jungle", 4, BlockSoundGroup.WOOD),
    DARK_OAK("dark_oak", 5, BlockSoundGroup.WOOD),
    MANGROVE("mangrove", 6, BlockSoundGroup.WOOD),
    CHERRY("cherry", 7, BlockSoundGroup.CHERRY_WOOD),
    BAMBOO("bamboo", 8, BlockSoundGroup.BAMBOO_WOOD),
    CRIMSON("crimson", 9, BlockSoundGroup.NETHER_WOOD),
    WARPED("warped", 10, BlockSoundGroup.NETHER_WOOD);

    private final String name;
    private final int id;
    private final BlockSoundGroup soundGroup;

    WoodType(String name, int id, BlockSoundGroup soundGroup) {
        this.name = name;
        this.id = id;
        this.soundGroup = soundGroup;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public BlockSoundGroup getSoundGroup() {
        return soundGroup;
    }

    public static WoodType getById(int id) {
        for (WoodType type : values()) if (type.getId() == id) return type;
        return OAK;
    }

    public static ItemStack getWoodBlockItem(BlockState state) {
        WoodType woodType = state.get(WoodTypeProperty.of("wood_type"));
        Identifier id = new Identifier(WhoMineMod.MOD_ID, woodType.getName() + "_" + Registries.BLOCK.getId(state.getBlock()).getPath());
        return Registries.ITEM.get(id).getDefaultStack();
    }

    @Override
    public String asString() {
        return getName();
    }
}