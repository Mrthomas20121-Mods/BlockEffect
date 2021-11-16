package mrthomas20121.block_effect;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BlockEffect.mod_id)
public class BlockEffect {

	public static final String mod_id = "block_effect";
	public static final Logger LOGGER = LogManager.getLogger();

	public BlockEffect() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BlockConfig.config);
	}
}
