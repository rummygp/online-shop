package Product;

import Configuration.HasAdditionalPrice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductConfiguration {
    private Product product;
    private Map<ProductFeatures, Enum<?>> selectedOptions;

    public ProductConfiguration(Product product) {
        this.product = product;
        this.selectedOptions = new HashMap<>();
    }

    public void selectOption(ProductFeatures feature, Enum<?> value) {
        selectedOptions.put(feature, value);
    }

    public BigDecimal getFinalPrice() {
        BigDecimal finalPrice = product.getPrice();

        for (Enum<?> option : selectedOptions.values()) {
            if (option instanceof HasAdditionalPrice pricedOption) {
                finalPrice = finalPrice.add(pricedOption.getAdditionalPrice());
            }
        } return finalPrice;
    }
}
