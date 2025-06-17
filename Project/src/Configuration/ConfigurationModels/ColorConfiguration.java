package Configuration.ConfigurationModels;

import Configuration.ConfigurationInterfaces.GetLabel;
import Configuration.ConfigurationInterfaces.HasAdditionalPrice;

import java.math.BigDecimal;

public enum ColorConfiguration implements HasAdditionalPrice, GetLabel {
    RED("Czerwony", new BigDecimal("0")),
    BLACK("Czarny", new BigDecimal("0")),
    GOLD("Złoty", new BigDecimal("100"));

    private final String label;
    private final BigDecimal price;

    ColorConfiguration(String label, BigDecimal price) {
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
