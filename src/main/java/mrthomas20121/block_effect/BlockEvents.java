package mrthomas20121.block_effect;

import mrthomas20121.block_effect.data.EffectData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class BlockEvents {

    public BlockEvents() {}

    @SubscribeEvent(priority = EventPriority.LOW)
    public void blockBreak(BlockEvent.BreakEvent event) {
        final List<EffectData> blockBreakEffects = EffectAdapter.block_break_effects;
        if(!blockBreakEffects.isEmpty()) {
            Block block = event.getState().getBlock();
            if(!event.isCanceled() && event.getPlayer() instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
                if(event.getPlayer().level.isClientSide) {
                    return;
                }
                for(EffectData effectData: blockBreakEffects) {
                    if(effectData.getMatch().match(block)) {
                        List<MobEffectInstance> effectInstanceList = effectData.getEffectInstanceList();
                        if(!effectInstanceList.isEmpty()) {
                            effectInstanceList.forEach(mobEffectInstance -> {
                                if(!serverPlayer.hasEffect(mobEffectInstance.getEffect())) {
                                    serverPlayer.addEffect(mobEffectInstance);
                                }
                            });
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
            if(!event.isCanceled() && event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
                if(event.getEntity().level.isClientSide) {
                    return;
                }
                for(EffectData effectData: blockBreakEffects) {
                    if(effectData.getMatch().match(block)) {
                        List<MobEffectInstance> effectInstanceList = effectData.getEffectInstanceList();
                        if(!effectInstanceList.isEmpty()) {
                            effectInstanceList.forEach(mobEffectInstance -> {
                                if(!serverPlayer.hasEffect(mobEffectInstance.getEffect())) {
                                    serverPlayer.addEffect(mobEffectInstance);
                                }
                            });
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
            if(!event.isCanceled() && event.player instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
                if(event.player.level.isClientSide) {
                    return;
                }
                for(EffectData effectData: isOnBlockEffects) {
                    BlockPos pos = serverPlayer.blockPosition();
                    Block block = serverPlayer.level.getBlockState(pos).getBlock();
                    if(effectData.getMatch().match(block)) {
                        List<MobEffectInstance> effectInstanceList = effectData.getEffectInstanceList();
                        if(!effectInstanceList.isEmpty()) {
                            effectInstanceList.forEach(mobEffectInstance -> {
                                if(!serverPlayer.hasEffect(mobEffectInstance.getEffect())) {
                                    serverPlayer.addEffect(mobEffectInstance);
                                }
                            });
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
            if(!event.isCanceled() && event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
                if(event.getEntity().level.isClientSide) {
                    return;
                }
                for(EffectData effectData: blockPlaceEffects) {
                    Block block = event.getState().getBlock();
                    if(effectData.getMatch().match(block)) {
                        List<MobEffectInstance> effectInstanceList = effectData.getEffectInstanceList();
                        if(!effectInstanceList.isEmpty()) {
                            effectInstanceList.forEach(mobEffectInstance -> {
                                if(!serverPlayer.hasEffect(mobEffectInstance.getEffect())) {
                                    serverPlayer.addEffect(mobEffectInstance);
                                }
                            });
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
