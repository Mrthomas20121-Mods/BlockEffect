package mrthomas20121.block_effect.data.block;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import net.minecraft.world.level.block.Block;

public interface Match {

    boolean match(Block block);
}
