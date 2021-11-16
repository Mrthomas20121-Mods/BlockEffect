package mrthomas20121.block_effect;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BlockConfig {
    private static Type type = new TypeToken<Map<String, EffectWrapper>>() {}.getType();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(type, new EffectJsonDeserializer()).serializeNulls().create();
    public static Map<String, EffectWrapper> config = new HashMap<>();

    public static void createJson() {
        Path jsonPath = Paths.get(FMLPaths.CONFIGDIR.get().toString(), "block_effects.json");
        File jsonFile = jsonPath.toFile();
        if(!jsonFile.exists()) {
            config.put("minecraft:soul_sand", new EffectWrapper("minecraft:wither", 1, 100));
            String data = gson.toJson(config);
            try {
                FileWriter writer = new FileWriter(jsonFile);
                writer.write(data);
                writer.close();
            } catch (IOException e) {
                BlockEffect.LOGGER.catching(e);
            }
        }
    }

    public static void readJson() {
        Path jsonPath = Paths.get(FMLPaths.CONFIGDIR.get().toString(), "block_effects.json");
        File jsonFile = jsonPath.toFile();
        if(jsonFile.exists()) {
            try {
                FileReader reader = new FileReader(jsonFile);
                config = gson.fromJson(reader, type);
                reader.close();
            } catch (IOException e) {
                BlockEffect.LOGGER.catching(e);
            }
        }
    }
}
