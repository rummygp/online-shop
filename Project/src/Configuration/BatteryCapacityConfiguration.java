package Configuration;

import java.math.BigDecimal;

public enum BatteryCapacityConfiguration implements HasAdditionalPrice{
    mAh_4000(4000, new BigDecimal("0")),
    mAh_5000(5000, new BigDecimal("100")),
    mAh_6000(6000, new BigDecimal("200"));

    private final int label;
    private final BigDecimal price;

    BatteryCapacityConfiguration(int label, BigDecimal price) {
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
