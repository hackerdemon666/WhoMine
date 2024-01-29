package minersstudios.whomine.block;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.minecraft.state.property.EnumProperty;

public class WoodTypeProperty
        extends EnumProperty<WoodType> {
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
}
