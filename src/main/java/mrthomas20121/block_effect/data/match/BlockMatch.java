package mrthomas20121.block_effect.data.match;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockMatch implements Match {

    private final Block block;

    public BlockMatch(Block block) {
       this.block = block;
    }

    @Override
    public boolean match(Block block) {
        ResourceLocation name1 = ForgeRegistries.BLOCKS.getKey(block);
        ResourceLocation name2 = ForgeRegistries.BLOCKS.getKey(this.block);

        return name2 != null && name2.equals(name1);
    }
}
