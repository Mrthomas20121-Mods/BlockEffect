package mrthomas20121.block_effect;

import com.google.gson.*;
import mrthomas20121.block_effect.data.*;
import mrthomas20121.block_effect.data.json.JsonEffect;
import mrthomas20121.block_effect.data.match.Match;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EffectAdapter extends SimpleJsonResourceReloadListener {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    public static final List<String> locations = new ArrayList<>();

    public static List<EffectData> block_break_effects = new ArrayList<>();
    public static List<EffectData> block_place_effects = new ArrayList<>();
    public static List<EffectData> is_on_block_effects = new ArrayList<>();
    public static List<EffectData> block_right_click_effects = new ArrayList<>();

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
                BlockEffect.LOGGER.warn("Skipped {} because it's disabled.", loc.toString());
                BlockEffect.EFFECT_LOG.warn("Skipped {} because it's disabled.", loc.toString());
                return;
            }

            List<JsonEffect> effects = new ArrayList<>();
            for(JsonElement element: object.get("potion_effects").getAsJsonArray()) {
                JsonObject obj = element.getAsJsonObject();
                ResourceLocation name = new ResourceLocation(obj.get("effect").getAsString());
                int amplifier = obj.get("level").getAsInt();
                int duration = obj.get("duration").getAsInt();
                boolean hasParticle = true;

                if(obj.has("particle")) {
                    hasParticle = obj.get("particle").getAsBoolean();
                }

                if(ForgeRegistries.MOB_EFFECTS.containsKey(name)) {
                    effects.add(new JsonEffect(name, duration, amplifier, hasParticle));
                }
                else {
                    BlockEffect.EFFECT_LOG.warn("Potion with %s name is null! please fix your BlockEffect file!".formatted(name));
                }
            }
            Match match = BlockType.getMatchFromJson(object.getAsJsonObject("match"));
            if(object.get("action").isJsonArray()) {
                GsonHelper.getAsJsonArray(object, "action").forEach(element -> {
                    insert(loc.toString(), element.getAsString(), new EffectData(effects, match));
                });
            }
            else {
                insert(loc.toString(), GsonHelper.getAsString(object, "action"), new EffectData(effects, match));
            }
        });
    }

    /**
     * Insert the effect in the right list
     * @param location the file name
     * @param action the action to take
     * @param effect the data
     */
    private void insert(String location, String action, EffectData effect) {
        if(!locations.contains(location)) {
            // add the location to the list if the action is valid and return true if the location was added.
            boolean bool = locationActionCheck(location, action);
            if(bool) {
                BlockActionType blockActionType = BlockActionType.valueOf(action);

                switch (blockActionType) {
                    case BLOCK_BREAK -> block_break_effects.add(effect);
                    case BLOCK_PLACE -> block_place_effects.add(effect);
                    case ON_BLOCK -> is_on_block_effects.add(effect);
                    case RIGHT_CLICK_BLOCK -> block_right_click_effects.add(effect);
                }
            }
        }
    }

    private boolean locationActionCheck(String location, String action) {

        boolean bool = false;

        if(BlockActionType.values.contains(action)) {
            locations.add(location);
            bool = true;
        }

        return bool;
    }
}
