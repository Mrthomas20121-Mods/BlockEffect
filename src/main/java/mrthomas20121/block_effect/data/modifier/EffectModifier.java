package mrthomas20121.block_effect.data.modifier;

import mrthomas20121.block_effect.data.json.JsonEffect;
import mrthomas20121.block_effect.data.match.Match;

import java.util.List;

public class EffectModifier extends Modifier<JsonEffect> {

    public EffectModifier(List<JsonEffect> elements, Match match) {
        super(elements, match);
    }
}
