package Configuration;

import java.math.BigDecimal;

public enum RamConfiguration implements HasAdditionalPrice{
    GB4(4, new BigDecimal("0")),
    GB8(8, new BigDecimal("200")),
    GB16(16, new BigDecimal("600"));

    private final int label;
    private final BigDecimal price;

    RamConfiguration(int label, BigDecimal price) {
        this.label = label;
        this.price = price;
    }

    public int getLabel() {
        return label;
    }

    @Override
    public BigDecimal getAdditionalPrice() {
        return price;
    }
}
