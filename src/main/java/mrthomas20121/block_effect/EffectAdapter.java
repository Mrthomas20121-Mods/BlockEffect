package mrthomas20121.block_effect;

import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EffectAdapter extends SimpleJsonResourceReloadListener {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    public static List<EffectData> potionEffects = new ArrayList<>();

    public static final EffectAdapter INSTANCE = new EffectAdapter();

    public EffectAdapter() {
        super(gson, "effects");
    }
    
    @Override
    protected void apply(@Nonnull Map<ResourceLocation, JsonElement> map, @Nonnull ResourceManager manager, @Nonnull ProfilerFiller profiler) {
        map.forEach((loc, elem) -> {
            JsonObject object = elem.getAsJsonObject();

            boolean enabled = object.get("enabled").getAsBoolean();
            if(!enabled) {
                BlockEffect.LOGGER.warn("Skipped {} because it is not enabled", loc.toString());
                return;
            }

            List<MobEffectInstance> effects = new ArrayList<>();
            for(JsonElement element: object.get("effects").getAsJsonArray()) {
                JsonObject obj = element.getAsJsonObject();
                ResourceLocation name = new ResourceLocation(obj.get("name").getAsString());
                int amplifier = obj.get("amplifier").getAsInt();
                int duration = obj.get("duration").getAsInt();
                MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(name);
                if(effect != null) {
                    effects.add(new MobEffectInstance(effect, duration, amplifier));
                }
                else {
                    BlockEffect.LOGGER.warn("Effect {} is invalid! please provide a valid name", name.toString());
                    return;
                }
            }
            if(object.has("tag")) {
                String tag = object.get("tag").getAsString();
                potionEffects.add(new EffectData(effects, tag, true));
            }
            else {
                String block = object.get("block").getAsString();
                potionEffects.add(new EffectData(effects, block));
            }
        });
    }
}
