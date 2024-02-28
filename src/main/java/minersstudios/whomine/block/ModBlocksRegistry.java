package minersstudios.whomine.block;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.block.blocks.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.ArrayList;

public class ModBlocksRegistry {
    public static final ArrayList<Block> DyeableBlocks = new ArrayList();
    public static final ArrayList<Block> TransparentBlocks = new ArrayList();
    public static final Block PLUMBUM_ORE = registerBlockWithBlockItem("plumbum_ore", new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE)));
    public static final Block PLUMBUM_BLOCK = registerBlockWithBlockItem("plumbum_block", new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)));
    public static final Block RAW_PLUMBUM_BLOCK = registerBlockWithBlockItem("raw_plumbum_block", new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)));

    public static final Block COLOR_BLOCK = registerDyeableBlock("color_block", new DyeableBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL), ModBlockCollisionType.DEFAULT_BLOCK), false);

    public static final Block CARVED_PLANKS = registerBlock("carved_planks", new WoodBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS), ModBlockCollisionType.DEFAULT_BLOCK));
    public static final Block FRAMED_PLANKS = registerBlock("framed_planks", new WoodBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS), ModBlockCollisionType.DEFAULT_BLOCK));
    public static final Block VERTICAL_PLANKS = registerBlock("vertical_planks", new WoodBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS), ModBlockCollisionType.DEFAULT_BLOCK));

    public static final Block BAR_CHAIR = registerDyeableBlock("bar_chair", new DyeableHorizontalFacingBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque(), ModBlockCollisionType.BAR_CHAIR), true);
    public static final Block COOL_CHAIR = registerDyeableBlock("cool_chair", new DyeableBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(0.75f).nonOpaque(), ModBlockCollisionType.COOL_CHAIR), false);

    public static final Block BIG_ARMCHAIR = registerDyeableBlock("big_armchair", new DyeableWoodHorizontalFacingBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(0.75f).nonOpaque(), ModBlockCollisionType.BIG_ARMCHAIR), false);
    public static final Block BIG_CHAIR = registerDyeableBlock("big_chair", new DyeableWoodHorizontalFacingBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(0.75f).nonOpaque(), ModBlockCollisionType.BIG_CHAIR), false);
    public static final Block SMALL_ARMCHAIR = registerDyeableBlock("small_armchair", new DyeableWoodHorizontalFacingBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(0.75f).nonOpaque(), ModBlockCollisionType.SMALL_ARMCHAIR), false);
    public static final Block SMALL_CHAIR = registerDyeableBlock("small_chair", new DyeableWoodBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(0.75f).nonOpaque(), ModBlockCollisionType.SMALL_CHAIR), true);

    //    public static final Block COUCH = registerDyeableBlock("couch", new DyeableConnectingBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(0.75f).nonOpaque(), ModBlockCollisionType.DEFAULT_BLOCK));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(WhoMineMod.MOD_ID, name), block);
    }

    private static Block registerBlockWithBlockItem(String id, Block block) {
        Registry.register(Registries.ITEM, new Identifier(WhoMineMod.MOD_ID, id), new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registries.BLOCK, new Identifier(WhoMineMod.MOD_ID, id), block);
    }

    private static Block registerDyeableBlock(String name, Block block, boolean transparent) {
        Block newBlock = Registry.register(Registries.BLOCK, new Identifier(WhoMineMod.MOD_ID, name), block);
        DyeableBlocks.add(newBlock);
        if (transparent) TransparentBlocks.add(newBlock);
        return newBlock;
    }

    public static void register() {
        WhoMineMod.LOGGER.info("Registering Blocks for " + WhoMineMod.MOD_NAME);
    }
}