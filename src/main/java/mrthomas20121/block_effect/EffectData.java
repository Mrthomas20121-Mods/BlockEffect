package mrthomas20121.block_effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.List;

public class EffectData {

    private final List<MobEffectInstance> effectInstanceList;
    private final String name;
    private final boolean tag;

    EffectData(List<MobEffectInstance> effectInstanceList, String name) {
        this(effectInstanceList, name, false);
    }

    public EffectData(List<MobEffectInstance> effectInstanceList, String name, boolean tag) {
        this.effectInstanceList = effectInstanceList;
        this.name = name;
        this.tag = tag;
    }

    public List<MobEffectInstance> getEffectInstanceList() {
        return effectInstanceList;
    }

    public boolean isEmpty() {
        return this.effectInstanceList.isEmpty();
    }

    public String getName() {
        return name;
    }

    public boolean isTag() {
        return tag;
    }

    // check if the tag contain the block.
    public boolean tagContainBlock(Block block) {
        ITagManager<Block> tagManager = ForgeRegistries.BLOCKS.tags();
        if(this.tag && tagManager != null) {
            TagKey<Block> tagKey = tagManager.createTagKey(new ResourceLocation(this.name));
            if(tagManager.isKnownTagName(tagKey)) {
                return tagManager.getTag(tagKey).contains(block);
            }
            return false;
        }
        return false;
    }
}
