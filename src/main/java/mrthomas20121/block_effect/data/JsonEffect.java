package mrthomas20121.block_effect.data;

import net.minecraft.resources.ResourceLocation;

public class JsonEffect {

    private final ResourceLocation potionName;
    private final int duration;
    private final int amplifier;
    private final boolean visible;


    public JsonEffect(ResourceLocation potionName, int duration, int amplifier, boolean visible) {
        this.potionName = potionName;
        this.duration = duration;
        this.amplifier = amplifier;
        this.visible = visible;
    }

    public ResourceLocation getPotionName() {
        return potionName;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public boolean isVisible() {
        return visible;
    }
}
