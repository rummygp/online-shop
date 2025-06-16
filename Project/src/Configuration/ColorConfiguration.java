package Configuration;

import java.math.BigDecimal;

public enum ColorConfiguration implements HasAdditionalPrice{
    RED("Czerwony", new BigDecimal("0")),
    BLACK("Czarny", new BigDecimal("0")),
    GOLD("Złoty", new BigDecimal("100"));

    private final String label;
    private final BigDecimal price;

    ColorConfiguration(String label, BigDecimal price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public BigDecimal getAdditionalPrice() {
        return price;
    }
}
