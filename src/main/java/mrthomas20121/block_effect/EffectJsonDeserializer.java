package mrthomas20121.block_effect;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

public class EffectJsonDeserializer implements JsonDeserializer<Map<String, EffectWrapper>> {

    @Override
    public Map<String, EffectWrapper> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsJsonObject()
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                e -> context.deserialize(e.getValue(), EffectWrapper.class)));
    }
}
