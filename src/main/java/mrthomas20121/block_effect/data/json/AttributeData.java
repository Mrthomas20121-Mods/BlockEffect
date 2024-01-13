package mrthomas20121.block_effect.data.json;

import org.jetbrains.annotations.NotNull;

public class AttributeData extends JsonData<AttributeData> {

    private final String attribute;
    private final String mod;
    private final double n;

    public AttributeData(String attribute, String mod, double n) {
        this.attribute = attribute;
        this.mod = mod;
        this.n = n;
    }

    @Override
    public @NotNull AttributeData getValue() {
        return this;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getMod() {
        return mod;
    }

    public double getN() {
        return this.n;
    }
}
