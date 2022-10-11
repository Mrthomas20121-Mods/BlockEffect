package mrthomas20121.block_effect.data;

import mrthomas20121.block_effect.data.block.Match;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.List;

public class EffectData {

    private final List<MobEffectInstance> effectInstanceList;
    private final Match match;

    public EffectData(List<MobEffectInstance> effectInstanceList, Match match) {
        this.effectInstanceList = effectInstanceList;
        this.match = match;
    }

    public List<MobEffectInstance> getEffectInstanceList() {
        return effectInstanceList;
    }

    public Match getMatch() {
        return match;
    }
}
