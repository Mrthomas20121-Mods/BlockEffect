package mrthomas20121.block_effect.data.json;

import mrthomas20121.block_effect.BlockEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JsonEffect extends JsonData<MobEffectInstance> {

    private final ResourceLocation potionName;
    private final int duration;
    private final int amplifier;
    private final boolean visible;

    public JsonEffect(@NotNull ResourceLocation potionName, int duration, int amplifier, boolean visible) {
        this.potionName = potionName;
        this.duration = duration;
        this.amplifier = amplifier;
        this.visible = visible;
    }

    public @NotNull ResourceLocation getPotionName() {
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

    @Override
    public @NotNull MobEffectInstance getValue() {
        MobEffect potion = ForgeRegistries.MOB_EFFECTS.getValue(potionName);

        if(potion == null) {
            BlockEffect.EFFECT_LOG.warn("Potion with %s name does not exist!".formatted(this.potionName.toString()));
            throw new NullPointerException("Potion with %s name does not exist!".formatted(this.potionName.toString()));
        }

        return new MobEffectInstance(potion, this.getDuration(), this.getAmplifier(), false, this.isVisible());
    }
}
