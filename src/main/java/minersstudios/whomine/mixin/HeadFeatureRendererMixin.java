package minersstudios.whomine.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LimbAnimator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.*;

import java.util.Map;

@Mixin(HeadFeatureRenderer.class)
public class HeadFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T> & ModelWithArms> extends FeatureRenderer<T, M> {
    public HeadFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }
    @Shadow @Final
    private float scaleX;
    @Shadow @Final
    private float scaleY;
    @Shadow @Final
    private float scaleZ;
    @Shadow @Final
    private Map<SkullBlock.SkullType, SkullBlockEntityModel> headModels;
    @Shadow @Final
    private HeldItemRenderer heldItemRenderer;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
        if (!itemStack.isEmpty()) {
            Item item = itemStack.getItem();
            matrixStack.push();
            matrixStack.scale(this.scaleX, this.scaleY, this.scaleZ);
            boolean bl = livingEntity instanceof VillagerEntity || livingEntity instanceof ZombieVillagerEntity;
            float n;
            if (livingEntity.isBaby() && !(livingEntity instanceof VillagerEntity)) {
                float m = 2.0F;
                n = 1.4F;
                matrixStack.translate(0.0F, 0.03125F, 0.0F);
                matrixStack.scale(0.7F, 0.7F, 0.7F);
                matrixStack.translate(0.0F, 1.0F, 0.0F);
            }

            ((ModelWithHead)this.getContextModel()).getHead().rotate(matrixStack);
            if (item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof AbstractSkullBlock) {
                n = 1.1875F;
                matrixStack.scale(1.1875F, -1.1875F, -1.1875F);
                if (bl) {
                    matrixStack.translate(0.0F, 0.0625F, 0.0F);
                }

                GameProfile gameProfile = null;
                if (itemStack.hasNbt()) {
                    NbtCompound nbtCompound = itemStack.getNbt();
                    if (nbtCompound.contains("SkullOwner", 10)) {
                        gameProfile = NbtHelper.toGameProfile(nbtCompound.getCompound("SkullOwner"));
                    }
                }

                matrixStack.translate(-0.5, 0.0, -0.5);
                SkullBlock.SkullType skullType = ((AbstractSkullBlock)((BlockItem)item).getBlock()).getSkullType();
                SkullBlockEntityModel skullBlockEntityModel = (SkullBlockEntityModel)this.headModels.get(skullType);
                RenderLayer renderLayer = SkullBlockEntityRenderer.getRenderLayer(skullType, gameProfile);
                Entity var22 = livingEntity.getVehicle();
                LimbAnimator limbAnimator;
                if (var22 instanceof LivingEntity) {
                    LivingEntity livingEntity2 = (LivingEntity)var22;
                    limbAnimator = livingEntity2.limbAnimator;
                } else {
                    limbAnimator = livingEntity.limbAnimator;
                }

                float o = limbAnimator.getPos(h);
                SkullBlockEntityRenderer.renderSkull((Direction)null, 180.0F, o, matrixStack, vertexConsumerProvider, i, skullBlockEntityModel, renderLayer);
            } else {
                label60: {
                    if (item instanceof ArmorItem) {
                        ArmorItem armorItem = (ArmorItem)item;
                        if (armorItem.getSlotType() == EquipmentSlot.HEAD) {
                            break label60;
                        }
                    } else if (item instanceof SaddleItem) {
                        break label60;
                    }

                    HeadFeatureRenderer.translate(matrixStack, bl);
                    this.heldItemRenderer.renderItem(livingEntity, itemStack, ModelTransformationMode.HEAD, false, matrixStack, vertexConsumerProvider, i);
                }
            }

            matrixStack.pop();
        }
    }
}
