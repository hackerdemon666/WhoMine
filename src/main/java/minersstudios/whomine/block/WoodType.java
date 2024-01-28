package minersstudios.whomine.block;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.StringIdentifiable;

public enum WoodType implements StringIdentifiable {
    OAK("oak", BlockSoundGroup.WOOD),
    SPRUCE("spruce", BlockSoundGroup.WOOD),
    BIRCH("birch", BlockSoundGroup.WOOD),
    ACACIA("acacia", BlockSoundGroup.WOOD),
    CHERRY("cherry", BlockSoundGroup.CHERRY_WOOD),
    JUNGLE("jungle", BlockSoundGroup.WOOD),
    DARK_OAK("dark_oak", BlockSoundGroup.WOOD),
    CRIMSON("crimson", BlockSoundGroup.NETHER_WOOD),
    WARPED("warped", BlockSoundGroup.NETHER_WOOD),
    MANGROVE("mangrove", BlockSoundGroup.WOOD),
    BAMBOO("bamboo", BlockSoundGroup.BAMBOO_WOOD);

    private final String name;
    private final BlockSoundGroup soundGroup;


    WoodType(String name, BlockSoundGroup soundGroup) {
        this.name = name;
        this.soundGroup = soundGroup;
        WoodTypeProperty.addKey(this);
    }

    public String getName() {
        return name;
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
