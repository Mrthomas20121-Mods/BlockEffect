package mrthomas20121.block_effect;

import mrthomas20121.block_effect.data.EffectData;
import mrthomas20121.block_effect.data.Action;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Map;

public class BlockEvents {

    public BlockEvents() {}

    @SubscribeEvent(priority = EventPriority.LOW)
    public void blockBreak(BlockEvent.BreakEvent event) {
        final List<EffectData> blockBreakEffects = EffectAdapter.block_break_effects;
        if(!blockBreakEffects.isEmpty()) {
            Block block = event.getState().getBlock();
            if(!event.isCanceled() && !event.getPlayer().isCreative()) {
                if(!blockBreakEffects.isEmpty()) {
                    for(EffectData effectData: blockBreakEffects) {
                        List<MobEffectInstance> effectInstanceList = effectData.getEffectInstanceList();
                        if(!effectInstanceList.isEmpty()) {
                            if(effectData.getMatch().match(block)) {
                                Player player = event.getPlayer();
                                if(effectData.getMatch().match(block)) {
                                    effectInstanceList.forEach(player::addEffect);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        final List<EffectData> blockBreakEffects = EffectAdapter.block_right_click_effects;
        if(!blockBreakEffects.isEmpty()) {
            Block block = event.getEntity().level.getBlockState(event.getPos()).getBlock();
            if(!event.isCanceled() && !event.getEntity().isCreative()) {
                if(!blockBreakEffects.isEmpty()) {
                    for(EffectData effectData: blockBreakEffects) {
                        List<MobEffectInstance> effectInstanceList = effectData.getEffectInstanceList();
                        if(!effectInstanceList.isEmpty()) {
                            if(effectData.getMatch().match(block)) {
                                Player player = event.getEntity();
                                if(effectData.getMatch().match(block)) {
                                    effectInstanceList.forEach(mobEffectInstance -> {
                                        if(!player.hasEffect(mobEffectInstance.getEffect())) {
                                            player.addEffect(mobEffectInstance);
                                        }
                                    });
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void isOnBlock(TickEvent.PlayerTickEvent event) {
        final List<EffectData> isOnBlockEffects = EffectAdapter.is_on_block_effects;
        if(!isOnBlockEffects.isEmpty()) {
            if(!event.isCanceled() && !event.player.isCreative()) {
                if(!isOnBlockEffects.isEmpty()) {
                    for(EffectData effectData: isOnBlockEffects) {
                        List<MobEffectInstance> effectInstanceList = effectData.getEffectInstanceList();
                        if(!effectInstanceList.isEmpty()) {
                            Player player = event.player;
                            BlockPos pos = player.blockPosition();
                            Block block = player.level.getBlockState(pos).getBlock();
                            if(effectData.getMatch().match(block)) {
                                effectInstanceList.forEach(mobEffectInstance -> {
                                    if(!player.hasEffect(mobEffectInstance.getEffect())) {
                                        player.addEffect(mobEffectInstance);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void blockPlace(BlockEvent.EntityPlaceEvent event) {
        final List<EffectData> blockPlaceEffects = EffectAdapter.block_place_effects;
        if(!blockPlaceEffects.isEmpty()) {
            if(!event.isCanceled() && event.getEntity() instanceof Player player) {
                if(player.isCreative()) {
                    return;
                }
                if(!blockPlaceEffects.isEmpty()) {
                    for(EffectData effectData: blockPlaceEffects) {
                        List<MobEffectInstance> effectInstanceList = effectData.getEffectInstanceList();
                        if(!effectInstanceList.isEmpty()) {
                            if(effectData.getMatch().match(event.getPlacedBlock().getBlock())) {
                                effectInstanceList.forEach(mobEffectInstance -> {
                                    if(!player.hasEffect(mobEffectInstance.getEffect())) {
                                        player.addEffect(mobEffectInstance);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void listenerEvent(AddReloadListenerEvent event) {
        event.addListener(EffectAdapter.INSTANCE);
    }
}
