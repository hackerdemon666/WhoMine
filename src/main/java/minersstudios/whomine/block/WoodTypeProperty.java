package minersstudios.whomine.block;

import com.google.common.collect.Lists;
import net.minecraft.state.property.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WoodTypeProperty
        extends EnumProperty<WoodType> {
    private static final Map<String, WoodType> keys = new HashMap<>();

    public WoodTypeProperty(String name, Collection<WoodType> values) {
        super(name, WoodType.class, values);
    }

    public static WoodTypeProperty of(String name) {
        return WoodTypeProperty.of(name, (WoodType woodType) -> true);
    }

    public static WoodTypeProperty of(String name, Predicate<WoodType> filter) {
        return WoodTypeProperty.of(name, Arrays.stream(WoodType.values()).filter(filter).collect(Collectors.toList()));
    }

    public static WoodTypeProperty of(String name, WoodType... values) {
        return WoodTypeProperty.of(name, Lists.newArrayList(values));
    }

    public static WoodTypeProperty of(String name, Collection<WoodType> values) {
        return new WoodTypeProperty(name, values);
    }

    public static void addKey(WoodType type) {
        keys.put(type.getName(), type);
    }

    public static Map<String, WoodType> getKeys() {
        return keys;
    }
}
