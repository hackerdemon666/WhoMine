package minersstudios.whomine.block;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.StringIdentifiable;

public enum WoodType implements StringIdentifiable {
    OAK("oak", 1,BlockSoundGroup.WOOD),
    SPRUCE("spruce", 2, BlockSoundGroup.WOOD),
    BIRCH("birch", 3, BlockSoundGroup.WOOD),
    ACACIA("acacia", 4, BlockSoundGroup.WOOD),
    CHERRY("cherry", 5, BlockSoundGroup.CHERRY_WOOD),
    JUNGLE("jungle", 6, BlockSoundGroup.WOOD),
    DARK_OAK("dark_oak", 7, BlockSoundGroup.WOOD),
    CRIMSON("crimson", 8, BlockSoundGroup.NETHER_WOOD),
    WARPED("warped", 9, BlockSoundGroup.NETHER_WOOD),
    MANGROVE("mangrove", 10, BlockSoundGroup.WOOD),
    BAMBOO("bamboo", 11, BlockSoundGroup.BAMBOO_WOOD);

    private final String name;
    private final Integer id;
    private final BlockSoundGroup soundGroup;

    WoodType(String name, int id, BlockSoundGroup soundGroup) {
        this.name = name;
        this.id = id;
        this.soundGroup = soundGroup;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public BlockSoundGroup getSoundGroup() {
        return soundGroup;
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
