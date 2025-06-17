package Configuration.ConfigurationModels;

import Configuration.ConfigurationInterfaces.GetLabel;
import Configuration.ConfigurationInterfaces.HasAdditionalPrice;

import java.math.BigDecimal;

public enum ProcessorConfiguration implements HasAdditionalPrice, GetLabel {
    I5("Intel Core i5-12600k", new BigDecimal("0")),
    I7("Intel Core i7-14700k", new BigDecimal("500")),
    I9("Intel Core i9-14900k", new BigDecimal("1000"));

    private final String label;
    private final BigDecimal price;

    ProcessorConfiguration(String label, BigDecimal price) {
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
