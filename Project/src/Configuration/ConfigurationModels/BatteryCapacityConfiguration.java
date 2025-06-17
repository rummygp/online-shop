package Configuration.ConfigurationModels;

import Configuration.ConfigurationInterfaces.ConfigurableOptions;
import java.math.BigDecimal;

public enum BatteryCapacityConfiguration implements ConfigurableOptions {
    mAh_4000("4000 mAh", new BigDecimal("0")),
    mAh_5000("5000 mAh", new BigDecimal("100")),
    mAh_6000("6000 mAh", new BigDecimal("200"));

    private final String label;
    private final BigDecimal price;

    BatteryCapacityConfiguration(String label, BigDecimal price) {
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
