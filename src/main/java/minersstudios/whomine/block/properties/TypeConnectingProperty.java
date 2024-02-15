package minersstudios.whomine.block.properties;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.minecraft.state.property.EnumProperty;

public class TypeConnectingProperty
        extends EnumProperty<TypeConnecting> {
    public TypeConnectingProperty(String name, Collection<TypeConnecting> values) {
        super(name, TypeConnecting.class, values);
    }

    public static TypeConnectingProperty of(String name) {
        return TypeConnectingProperty.of(name, (TypeConnecting type) -> true);
    }

    public static TypeConnectingProperty of(String name, Predicate<TypeConnecting> filter) {
        return TypeConnectingProperty.of(name, Arrays.stream(TypeConnecting.values()).filter(filter).collect(Collectors.toList()));
    }

    public static TypeConnectingProperty of(String name, TypeConnecting... values) {
        return TypeConnectingProperty.of(name, Lists.newArrayList(values));
    }

    public static TypeConnectingProperty of(String name, Collection<TypeConnecting> values) {
        return new TypeConnectingProperty(name, values);
    }
}