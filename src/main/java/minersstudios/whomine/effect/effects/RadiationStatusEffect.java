package minersstudios.whomine.effect.effects;

import minersstudios.whomine.effect.ModDamageTypesRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class RadiationStatusEffect extends StatusEffect {
    public RadiationStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x98D982);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        DamageSource source = ModDamageTypesRegistry.of(entity.getWorld(), ModDamageTypesRegistry.RADIATION_DAMAGE_TYPE);
        float damage = amplifier < 16 ? 1.0f : amplifier * 0.05f;
        entity.damage(source, damage);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = (amplifier <= 5 ? 50 : 25) >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }
}