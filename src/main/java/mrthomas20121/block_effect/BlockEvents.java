package mrthomas20121.block_effect;

import net.minecraft.block.Block;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = BlockEffect.mod_id)
public class BlockEvents {

    @SubscribeEvent
    public static void blockBreak(BlockEvent.BreakEvent event) {
        Block block = event.getState().getBlock();
        ResourceLocation registryName = block.getRegistryName();
        if(registryName != null && !event.isCanceled() && !event.getPlayer().isCreative()) {
            if(!BlockConfig.BlockEffectConfig.blocks.get().isEmpty()) {
                for(String name: BlockConfig.BlockEffectConfig.blocks.get()) {
                    String[] split = name.split("=");
                    ResourceLocation effectName = new ResourceLocation(split[1]);
                    ResourceLocation blockName = new ResourceLocation(split[0]);
                    if(registryName.equals(blockName)) {
                        event.getPlayer().addEffect(new EffectInstance(Objects.requireNonNull(ForgeRegistries.POTIONS.getValue(effectName)), 100, 1));
                    }
                }
            }
        }
    }
}
