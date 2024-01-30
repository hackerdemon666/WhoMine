package minersstudios.whomine;

import minersstudios.whomine.block.DyeableBlockEntity;
import minersstudios.whomine.block.ModBlocksRegistry;
import minersstudios.whomine.block.WoodType;
import minersstudios.whomine.entity.ModEntitiesRegistry;
import minersstudios.whomine.entity.SitEntity;
import minersstudios.whomine.item.ModItemsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Objects;

@Environment(value = EnvType.CLIENT)
public class WhoMineModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> getColorNbt(Objects.requireNonNull(view), pos), ModBlocksRegistry.BIG_ARMCHAIR);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> getColorNbt(Objects.requireNonNull(view), pos), ModBlocksRegistry.SMALL_CHAIR);

        for (Item item: ModItemsRegistry.registry) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem) stack.getItem()).getColor(stack), item);
        }

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem) stack.getItem()).getColor(stack), ModBlocksRegistry.SMALL_CHAIR);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem) stack.getItem()).getColor(stack), ModItemsRegistry.LEATHER_HAT);

        EntityRendererRegistry.register(ModEntitiesRegistry.SIT, EmptyRenderer::new);

//        for (Item item: ModItemsRegistry.registry) {
//            ModelPredicateProviderRegistry.register(item, new Identifier("pull"), (stack, clientWorld, livingEntity) -> {
//                if (livingEntity == null) {
//                    return 0.0F;
//                }
//                return WoodType.
////                return livingEntity.getActiveItem() != stack ? 0.0F : (stack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
//            });
//        }
    }

    private int getColorNbt(BlockView view, BlockPos pos) {
        int color = 16383998;

        BlockEntity blockEntity = view.getBlockEntity(pos);

        if (blockEntity instanceof DyeableBlockEntity)
            color = ((DyeableBlockEntity) blockEntity).getColor();
        return color;
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