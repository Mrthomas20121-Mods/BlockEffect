package mrthomas20121.block_effect.data;

import mrthomas20121.block_effect.data.json.JsonEffect;
import mrthomas20121.block_effect.data.match.Match;
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
        return jsonEffects.stream().map(JsonEffect::getValue).toList();
    }

    public Match getMatch() {
        return match;
    }
}
