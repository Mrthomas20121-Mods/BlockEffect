package mrthomas20121.block_effect;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BlockEffect.mod_id)
public class BlockEffect {

	public static final String mod_id = "block_effect";
	public static final Logger LOGGER = LogManager.getLogger();

	public static final Logger EFFECT_LOG = LogManager.getLogger(BlockEffect.class);

	public BlockEffect() {

		MinecraftForge.EVENT_BUS.register(new BlockEvents());
	}
}
