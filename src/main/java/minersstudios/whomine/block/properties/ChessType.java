package minersstudios.whomine.block.properties;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.ModProperties;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

public enum ChessType implements StringIdentifiable {
    KING("king",0 ,"♔"),
    QUEEN("queen",1 , "♕"),
    ROOK("rook",2 , "♖"),
    BISHOP("bishop",3 , "♗"),
    KNIGHT("knight",4 , "♘"),
    PAWN("pawn",5 , "♙");

    private final String name;
    private final String icon;
    private final int id;

    ChessType(String name, int id, String icon) {
        this.name = name;
        this.icon = icon;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static ItemStack getChessBlockItem(BlockState state) {
        ChessType chessType = state.get(ModProperties.CHESS_TYPE);
        Identifier id = new Identifier(WhoMineMod.MOD_ID, "piece_" + chessType.getName());
        return Registries.ITEM.get(id).getDefaultStack();
    }

    @Override
    public String asString() {
        return name;
    }
}
