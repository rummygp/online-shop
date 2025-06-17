package Configuration.ConfigurationModels;

import Configuration.ConfigurationInterfaces.GetLabel;
import Configuration.ConfigurationInterfaces.HasAdditionalPrice;

import java.math.BigDecimal;

public enum RamConfiguration implements HasAdditionalPrice, GetLabel {
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
