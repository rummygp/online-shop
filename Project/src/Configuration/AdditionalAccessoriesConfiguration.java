package Configuration;

import java.math.BigDecimal;

public enum AdditionalAccessoriesConfiguration {
    CHARGER("Ładowarka", new BigDecimal("100")),
    CASE("Etui", new BigDecimal("200")),
    HEADPHONES("Słuchawki", new BigDecimal("300"));

    private final String label;
    private final BigDecimal price;

    AdditionalAccessoriesConfiguration(String label, BigDecimal price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
