package mrthomas20121.block_effect;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BlockConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final BlockEffectConfig blockEffectConfig = new BlockEffectConfig(BUILDER);
    public static ForgeConfigSpec config = BUILDER.build();

    public static class BlockEffectConfig {
        public static ForgeConfigSpec.ConfigValue<List<? extends String>>  blocks;

        private BlockEffectConfig(ForgeConfigSpec.Builder builder) {
            Predicate<Object> validator = o -> o instanceof String;
            builder.push("Blocks config");
            blocks = builder.defineList("List of blocks/effects", Lists.newArrayList("minecraft:soul_sand=minecraft:wither"), validator);
        }
    }
}
