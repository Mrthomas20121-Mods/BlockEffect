package mrthomas20121.block_effect.data;

import com.google.gson.JsonElement;
import mrthomas20121.block_effect.BlockEffect;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public enum BlockActionType {

    RIGHT_CLICK_BLOCK,
    BLOCK_BREAK,
    BLOCK_PLACE,
    ON_BLOCK,
    BLOCK_BELOW;

    BlockActionType() {}

    public static final List<String> values = Stream.of(BlockActionType.values()).map(v -> v.name().toLowerCase(Locale.ROOT)).toList();

    public static boolean isBlockActionType(String value) {
        boolean bool = false;
        switch (value) {
            case "right_click_block" :
            case "block_break" :
            case "block_place" :
            case "on_block" :
            case "block_below" :
                bool = true;
        }

        return bool;
    }

    public static BlockActionType fromJson(JsonElement element) {
        String s = element.getAsString();
        if(isBlockActionType(s)) {
            return valueOf(s);
        }

        BlockEffect.LOGGER.warn("[BlockActionType] You have an incorrect action type!");

        return null;
    }
}
