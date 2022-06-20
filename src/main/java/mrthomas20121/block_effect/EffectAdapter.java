package mrthomas20121.block_effect;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import javax.annotation.Nonnull;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class EffectAdapter extends SimpleJsonResourceReloadListener {

    private static final Type type = new TypeToken<Map<String, EffectWrapper>>() {}.getType();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(type, new EffectJsonDeserializer()).serializeNulls().create();

    public Map<String, EffectWrapper> potionEffects = new HashMap<>();

    public static final EffectAdapter INSTANCE = new EffectAdapter();

    public EffectAdapter() {
        super(gson, "effects");
    }



    @Override
    protected void apply(@Nonnull Map<ResourceLocation, JsonElement> map, @Nonnull ResourceManager manager, @Nonnull ProfilerFiller profiler) {
        map.forEach((loc, elem) -> {
            JsonObject object = elem.getAsJsonObject();

            if(!object.has("block")) {
                BlockEffect.LOGGER.warn("Invalid Block effect {}, missing block property.", loc.toString());
                return;
            }
            if(object.has("enabled")) {
                boolean enabled = object.get("enabled").getAsBoolean();
                if(!enabled) {
                    BlockEffect.LOGGER.warn("Disabled {}", loc.toString());
                    return;
                }
            }

            String block = object.get("block").getAsString();
            EffectWrapper wrapper = gson.fromJson(object.get("effect"), EffectWrapper.class);
            potionEffects.put(block, wrapper);
        });
    }
}