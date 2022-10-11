package mrthomas20121.block_effect.data;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import mrthomas20121.block_effect.data.block.BlockListMatch;
import mrthomas20121.block_effect.data.block.BlockMatch;
import mrthomas20121.block_effect.data.block.Match;
import mrthomas20121.block_effect.data.block.TagMatch;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class Action {

    // Action Types
    public static String BLOCK_RIGHT_CLICK = "block_effect:block_right_click";
    public static String BLOCK_BREAK = "block_effect:block_break";
    public static String IS_ON_BLOCK = "block_effect:is_on_block";
    public static String BLOCK_PLACE = "block_effect:block_place";

    public static List<String> actions = Lists.newArrayList(BLOCK_BREAK, BLOCK_PLACE, IS_ON_BLOCK, BLOCK_RIGHT_CLICK);

    public static class BlockType {
        // Block Type
        public static String BLOCK_LIST = "block_effect:block_list";
        public static String BLOCK = "block_effect:block";
        public static String TAG = "block_effect:block_tag";

        public static Match getMatchFromJson(JsonObject object) {
            String type = GsonHelper.getAsString(object, "type");
            if(type.equals(BLOCK)) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "block")));
                return new BlockMatch(block);
            }
            else if(type.equals(TAG)) {
                return new TagMatch(GsonHelper.getAsString(object, "tag"));
            }
            else if(type.equals(BLOCK_LIST)) {
                JsonArray arr = GsonHelper.getAsJsonArray(object, "blocks");
                List<Match> matchList = new ArrayList<>();
                arr.forEach(elem -> matchList.add(getMatchFromJson(elem.getAsJsonObject())));
                return new BlockListMatch(matchList);
            }
            else {
                throw new JsonSyntaxException("Error, Json has wrong types!");
            }
        }
    }

}
