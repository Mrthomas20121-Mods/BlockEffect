package mrthomas20121.block_effect.data.modifier;

import mrthomas20121.block_effect.BlockEffect;
import mrthomas20121.block_effect.data.json.AttributeData;
import mrthomas20121.block_effect.data.match.Match;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class AttributeModifier extends Modifier<AttributeData> {

    public AttributeModifier(List<AttributeData> elements, Match match) {
        super(elements, match);
    }

    public AttributeSupplier get() {
        AttributeSupplier.Builder builder = AttributeSupplier.builder();
        this.getList().forEach(attributeData -> {
            Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(attributeData.getAttribute()));

            if(attribute == null) {
                BlockEffect.EFFECT_LOG.warn("Attribute with name %s does not exist!".formatted(attributeData.getAttribute()));
            }
            else {
                builder.add(attribute, attributeData.getN());
            }
        });

        return builder.build();
    }
}
