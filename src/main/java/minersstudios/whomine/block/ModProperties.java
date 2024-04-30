package minersstudios.whomine.block;

import minersstudios.whomine.block.properties.ChessType;
import minersstudios.whomine.block.properties.TypeConnecting;
import minersstudios.whomine.block.properties.WoodType;
import net.minecraft.state.property.EnumProperty;

public class ModProperties {
//    public static final BooleanProperty POWERED = BooleanProperty.of("powered");
//    public static final EnumProperty<Direction.Axis> AXIS = EnumProperty.of("axis", Direction.Axis.class);
//    public static final DirectionProperty FACING = DirectionProperty.of("facing", new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN});
//    public static final int CHARGES_MIN = 0;
//    public static final int CHARGES_MAX = 4;
//    public static final IntProperty CHARGES = IntProperty.of("charges", 0, 4);

    public static final EnumProperty<TypeConnecting> TYPE = EnumProperty.of("type", TypeConnecting.class);
    public static final EnumProperty<WoodType> WOOD_TYPE = EnumProperty.of("wood_type", WoodType.class);
    public static final EnumProperty<ChessType> CHESS_TYPE = EnumProperty.of("chess_type", ChessType.class);
}

