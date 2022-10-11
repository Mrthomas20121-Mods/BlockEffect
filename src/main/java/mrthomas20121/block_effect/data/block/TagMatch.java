package mrthomas20121.block_effect.data.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

public class TagMatch implements Match {

    private final String tagName;

    public TagMatch(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean match(Block block) {
        ITagManager<Block> tagManager = ForgeRegistries.BLOCKS.tags();
        if(tagManager != null) {
            TagKey<Block> tagKey = tagManager.createTagKey(new ResourceLocation(this.tagName));
            if(tagManager.isKnownTagName(tagKey)) {
                return tagManager.getTag(tagKey).contains(block);
            }
            return false;
        }
        return false;
    }
}
