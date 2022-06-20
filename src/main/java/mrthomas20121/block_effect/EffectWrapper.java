package mrthomas20121.block_effect;

import net.minecraft.resources.ResourceLocation;

public class EffectWrapper {

    private final String potionName;
    private final int strength;
    private final int duration;

    public EffectWrapper(String potionName, int strength, int duration) {
        this.potionName = potionName;
        this.strength = strength;
        this.duration = duration;
    }

    public String getPotionName() {
        return potionName;
    }

    public ResourceLocation getPotionRegistryName() {
        return new ResourceLocation(this.potionName);
    }

    public int getStrength() {
        return strength;
    }

    public int getDuration() {
        return duration;
    }
}
