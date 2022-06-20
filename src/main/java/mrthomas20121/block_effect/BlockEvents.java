package mrthomas20121.block_effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = BlockEffect.mod_id)
public class BlockEvents {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void blockBreak(BlockEvent.BreakEvent event) {
        Block block = event.getState().getBlock();
        ResourceLocation registryName = block.getRegistryName();
        if(registryName != null && !event.isCanceled() && !event.getPlayer().isCreative()) {
            if(!EffectAdapter.potionEffects.isEmpty()) {
                for(Map.Entry<String, EffectWrapper> entry: EffectAdapter.potionEffects.entrySet()) {
                    EffectWrapper effectWrapper = entry.getValue();
                    ResourceLocation blockName = new ResourceLocation(entry.getKey());
                    if(registryName.equals(blockName)) {
                        event.getPlayer().addEffect(new MobEffectInstance(Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(effectWrapper.getPotionRegistryName())), effectWrapper.getDuration(), effectWrapper.getStrength()));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onUtil(AddReloadListenerEvent event) {
        event.addListener(EffectAdapter.INSTANCE);
    }
}
