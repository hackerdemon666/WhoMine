package minersstudios.whomine.block.properties;

import net.minecraft.util.StringIdentifiable;

public enum TypeConnecting implements StringIdentifiable {
    SINGLE("single"),
    RIGHT("right"),
    LEFT("left"),
    MIDDLE_RIGHT("middle_right"),
    MIDDLE_LEFT("middle_left"),
    MIDDLE("middle"),
    BOTTOM("bottom");

    private final String name;

    TypeConnecting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String asString() {
        return getName();
    }

    public static TypeConnecting get(String name) {
        for (TypeConnecting type : values()) if (type.getName().equals(name)) return type;
        return SINGLE;
    }
}
