package enums;

import interfaces.ConfigurableOptions;
import java.math.BigDecimal;

public enum RamConfiguration implements ConfigurableOptions {
    GB4("4 GB", new BigDecimal("0")),
    GB8("8 GB", new BigDecimal("200")),
    GB16("16 GB", new BigDecimal("600"));

    private final String label;
    private final BigDecimal price;

    RamConfiguration(String label, BigDecimal price) {
        this.label = label;
        this.price = price;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public BigDecimal getAdditionalPrice() {
        return price;
    }
}
