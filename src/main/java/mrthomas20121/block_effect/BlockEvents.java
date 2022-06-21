package mrthomas20121.block_effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = BlockEffect.mod_id)
public class BlockEvents {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void blockBreak(BlockEvent.BreakEvent event) {
        Block block = event.getState().getBlock();
        ResourceLocation registryName = ForgeRegistries.BLOCKS.getKey(block);
        if(registryName != null && !event.isCanceled() && !event.getPlayer().isCreative()) {
            if(!EffectAdapter.potionEffects.isEmpty()) {
                for(EffectData effectData: EffectAdapter.potionEffects) {
                    List<MobEffectInstance> effectInstanceList = effectData.getEffectInstanceList();
                    if(effectInstanceList.isEmpty()) {
                        return;
                    }
                    else {
                        if(effectData.isTag()) {
                            if(effectData.tagContainBlock(block)) {
                                Player player = event.getPlayer();
                                for(MobEffectInstance effect: effectInstanceList) {
                                    player.addEffect(effect);
                                }
                            }
                        }
                        else {
                            ResourceLocation blockName = new ResourceLocation(effectData.getName());
                            if(registryName.equals(blockName)) {
                                Player player = event.getPlayer();
                                for(MobEffectInstance effect: effectInstanceList) {
                                    player.addEffect(effect);
                                }
                            }
                        }
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
