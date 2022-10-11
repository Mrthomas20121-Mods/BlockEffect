package mrthomas20121.block_effect.data;

import mrthomas20121.block_effect.data.block.Match;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class EffectData {

    private final List<JsonEffect> jsonEffects;
    private final Match match;

    public EffectData(List<JsonEffect> jsonEffects, Match match) {
        this.jsonEffects = jsonEffects;
        this.match = match;
    }

    public List<MobEffectInstance> getEffectInstanceList() {
        return jsonEffects.stream().map(jsonEffect -> {
            MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(jsonEffect.getPotionName());
            return new MobEffectInstance(effect, jsonEffect.getDuration(), jsonEffect.getAmplifier(), false, jsonEffect.isVisible());
        }).toList();
    }

    public Match getMatch() {
        return match;
    }
}
