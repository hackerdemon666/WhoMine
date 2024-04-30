package minersstudios.whomine.mixin;

import minersstudios.whomine.WhoMineMod;
import minersstudios.whomine.item.ModArmorMaterials;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.item.trim.ArmorTrim;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

    @Mixin(ArmorFeatureRenderer.class)
    public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
        @Shadow @Final
        private static Map<String, Identifier> ARMOR_TEXTURE_CACHE;
        @Shadow @Final
        private A innerModel;
        @Shadow @Final
        private A outerModel;
        @Shadow @Final
        private SpriteAtlasTexture armorTrimsAtlas;

        public ArmorFeatureRendererMixin(FeatureRendererContext<T, M> context) {
            super(context);
        }

        /**
         * @author
         * @reason
         */
        @Overwrite
        private void renderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model) {
            ItemStack itemStack = entity.getEquippedStack(armorSlot);
            Item var9 = itemStack.getItem();
            if (var9 instanceof ArmorItem armorItem) {
                if (armorItem.getSlotType() == armorSlot) {
                    ((BipedEntityModel)this.getContextModel()).copyBipedStateTo(model);
                    this.setVisible(model, armorSlot);
                    boolean bl = this.usesInnerModel(armorSlot);
                    if (armorItem instanceof DyeableArmorItem) {
                        DyeableArmorItem dyeableArmorItem = (DyeableArmorItem)armorItem;
                        int i = dyeableArmorItem.getColor(itemStack);
                        float f = (float)(i >> 16 & 255) / 255.0F;
                        float g = (float)(i >> 8 & 255) / 255.0F;
                        float h = (float)(i & 255) / 255.0F;
                        this.renderArmorParts(matrices, vertexConsumers, light, armorItem, model, bl, f, g, h, (String)null);
                        this.renderArmorParts(matrices, vertexConsumers, light, armorItem, model, bl, 1.0F, 1.0F, 1.0F, "overlay");
                    } else {
                        this.renderArmorParts(matrices, vertexConsumers, light, armorItem, model, bl, 1.0F, 1.0F, 1.0F, (String)null);
                    }

                    ArmorTrim.getTrim(entity.getWorld().getRegistryManager(), itemStack, true).ifPresent((trim) -> {
                        this.renderTrim(armorItem.getMaterial(), matrices, vertexConsumers, light, trim, model, bl);
                    });
                    if (itemStack.hasGlint()) {
                        this.renderGlint(matrices, vertexConsumers, light, model);
                    }

                }
            } else if (var9 instanceof SaddleItem) {
                ((BipedEntityModel) this.getContextModel()).copyBipedStateTo(model);
                this.setVisible(model, armorSlot);
                boolean bl = this.usesInnerModel(armorSlot);

                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull((Identifier)ARMOR_TEXTURE_CACHE.computeIfAbsent("whomine:textures/models/armor/saddle_layer_1.png", Identifier::new)));
                model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

                ArmorTrim.getTrim(entity.getWorld().getRegistryManager(), itemStack, true).ifPresent((trim) -> {
//                    this.renderTrim(ModArmorMaterials.SADDLE, matrices, vertexConsumers, light, trim, model, bl);
                    this.renderTrim(new ArmorMaterial() {
                        @Override
                        public int getDurability(ArmorItem.Type type) {
                            return 5;
                        }

                        @Override
                        public int getProtection(ArmorItem.Type type) {
                            return 1;
                        }

                        @Override
                        public int getEnchantability() {
                            return 0;
                        }

                        @Override
                        public SoundEvent getEquipSound() {
                            return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
                        }

                        @Override
                        public Ingredient getRepairIngredient() {
                            return null;
                        }

                        @Override
                        public String getName() {
                            return WhoMineMod.MOD_ID + ":" + "saddle";
                        }

                        @Override
                        public float getToughness() {
                            return 0;
                        }

                        @Override
                        public float getKnockbackResistance() {
                            return 0;
                        }
                    }, matrices, vertexConsumers, light, trim, model, bl);
                });
                if (itemStack.hasGlint()) {
                    this.renderGlint(matrices, vertexConsumers, light, model);
                }
            }
        }

        @Shadow
        protected abstract void renderArmorParts(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorItem item, A model, boolean secondTextureLayer, float red, float green, float blue, @Nullable String overlay);

        @Shadow
        public abstract void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l);

        @Shadow
        protected abstract void setVisible(A bipedModel, EquipmentSlot slot);
        @Shadow
        protected abstract void renderTrim(ArmorMaterial material, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorTrim trim, A model, boolean leggings);

        @Shadow
        protected abstract void renderGlint(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, A model);

        @Shadow
        protected abstract A getModel(EquipmentSlot slot);

        @Shadow
        protected abstract boolean usesInnerModel(EquipmentSlot slot);

        /**
         * @author
         * @reason
         */
        @Overwrite
        private Identifier getArmorTexture(ArmorItem item, boolean secondLayer, @Nullable String overlay) {
            String var10000 = item.getMaterial().getName();
            String string = "textures/models/armor/" + var10000 + "_layer_" + (secondLayer ? 2 : 1) + (overlay == null ? "" : "_" + overlay) + ".png";
            return (Identifier)ARMOR_TEXTURE_CACHE.computeIfAbsent(string, Identifier::new);
        }
    }
