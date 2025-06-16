package Configuration;

import java.math.BigDecimal;

public enum RamConfiguration {
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

    public BigDecimal getPrice() {
        return price;
    }
}
