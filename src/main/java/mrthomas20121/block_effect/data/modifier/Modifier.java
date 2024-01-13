package mrthomas20121.block_effect.data.modifier;

import mrthomas20121.block_effect.data.match.Match;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Modifier<T> {

    private final List<T> list;
    private final Match match;

    public Modifier(List<T> elements, Match match) {
        this.list = elements;
        this.match = match;
    }

    public List<T> getList() {
        return list;
    }

    public boolean matchBlock(Block block) {
        return this.match.match(block);
    }
}
