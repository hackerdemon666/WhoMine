package minersstudios.whomine;

import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.block.blocks.*;
import minersstudios.whomine.entity.ModEntitiesRegistry;
import minersstudios.whomine.entity.entities.SitEntity;
import minersstudios.whomine.item.ModItemsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Environment(value = EnvType.CLIENT)
public class WhoMineModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block block: ModBlocksRegistry.DyeableBlocks) {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> this.getColorNbt(state, view, pos), block);
        }
        for (Block block: ModBlocksRegistry.TransparentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        }
        for (Item item: ModItemsRegistry.DyeableItems) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> this.getColorNbt(stack), item);
        }

        EntityRendererRegistry.register(ModEntitiesRegistry.SIT, EmptyRenderer::new);
    }

    private int getColorNbt(BlockState state, BlockView view, BlockPos pos) {
        BlockEntity blockEntity = view.getBlockEntity(pos);
        if (blockEntity == null) return 10511680;
        Block block = state.getBlock();

        int defaultColor = (block instanceof ChessBlock) ? 16777215 : 10511680;
        int color = ((DyeableBlockEntity) blockEntity).getColor();

        return (blockEntity instanceof DyeableBlockEntity && color != -1) ? color : defaultColor;
    }

    private int getColorNbt(ItemStack stack) {
        return ((DyeableItem) stack.getItem()).getColor(stack);
    }

    private static class EmptyRenderer extends EntityRenderer<SitEntity> {

        protected EmptyRenderer(EntityRendererFactory.Context context) {
            super(context);
        }

        @Override
        public boolean shouldRender(SitEntity entity, Frustum frustum, double d, double e, double f) {
            return false;
        }

        @Override
        public Identifier getTexture(SitEntity entity) {
            return null;
        }
    }
}