package mrthomas20121.block_effect.data.block;

import net.minecraft.world.level.block.Block;

import java.util.List;

public class BlockListMatch implements Match {

    private final List<Match> matchList;

    public BlockListMatch(List<Match> matchList) {
        this.matchList = matchList;
    }

    @Override
    public boolean match(Block block) {
        boolean result = false;
        for(Match blockMatch: this.matchList) {
            if(blockMatch.match(block)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
