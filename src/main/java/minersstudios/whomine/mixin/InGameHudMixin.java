package minersstudios.whomine.mixin;

import minersstudios.whomine.effect.ModHeartType;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private final Random random = Random.create();

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void renderHealthBar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking) {
        ModHeartType heartType = ModHeartType.fromPlayerState(player);
        boolean bl = player.getWorld().getLevelProperties().isHardcore();
        int i = MathHelper.ceil((double)maxHealth / 2.0);
        int j = MathHelper.ceil((double)absorption / 2.0);
        int k = i * 2;

        for(int l = i + j - 1; l >= 0; --l) {
            int m = l / 10;
            int n = l % 10;
            int o = x + n * 8;
            int p = y - m * lines;
            if (lastHealth + absorption <= 4) {
                p += this.random.nextInt(2);
            }

            if (l < i && l == regeneratingHeartIndex) {
                p -= 2;
            }

            this.drawHeart(context, ModHeartType.CONTAINER, o, p, bl, blinking, false);
            int q = l * 2;
            boolean bl2 = l >= i;
            if (bl2) {
                int r = q - k;
                if (r < absorption) {
                    boolean bl3 = r + 1 == absorption;
                    this.drawHeart(context, heartType == ModHeartType.WITHERED ? heartType : ModHeartType.ABSORBING, o, p, bl, false, bl3);
                }
            }

            boolean bl4;
            if (blinking && q < health) {
                bl4 = q + 1 == health;
                this.drawHeart(context, heartType, o, p, bl, true, bl4);
            }

            if (q < lastHealth) {
                bl4 = q + 1 == lastHealth;
                this.drawHeart(context, heartType, o, p, bl, false, bl4);
            }
        }
    }

    private void drawHeart(DrawContext context, ModHeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half) {
        context.drawGuiTexture(type.getTexture(hardcore, half, blinking), x, y, 9, 9);
    }
}