package minersstudios.whomine.block;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.item.ModItemsRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.ArrayList;

public class ModBlocksRegistry {
    public static final ArrayList<Block> DyeableBlocks = new ArrayList();
    public static final Block PLUMBUM_ORE = registerBlock("plumbum_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE)));
    public static final Block PLUMBUM_BLOCK = registerBlock("plumbum_block", new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)));
    public static final Block RAW_PLUMBUM_BLOCK = registerBlock("raw_plumbum_block", new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)));

    public static final Block COLOR_BLOCK = registerDyeableBlock("color_block", new DyeableBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).nonOpaque(), ModBlockCollisionType.DEFAULT_BLOCK));

    public static final Block BIG_ARMCHAIR = registerDyeableBlock("big_armchair", new DyeableWoodHorizontalFacingBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(0.75f).nonOpaque(), ModBlockCollisionType.BIG_ARMCHAIR));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(WhoMineMod.MOD_ID, name), block);
    }

    private static Block registerDyeableBlock(String name, Block block) {
        Block newBlock = Registry.register(Registries.BLOCK, new Identifier(WhoMineMod.MOD_ID, name), block);
        DyeableBlocks.add(newBlock);
        return newBlock;
    }

    public static void register() {}
}