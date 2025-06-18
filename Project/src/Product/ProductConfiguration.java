package Product;

import Configuration.ConfigurationInterfaces.ConfigurableOptions;
import Product.Elements.ProductFeatures;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductConfiguration {
    private final Product product;
    private final Map<ProductFeatures, ConfigurableOptions> selectedOptions;

    public ProductConfiguration(Product product) {
        this.product = product;
        this.selectedOptions = new HashMap<>();
    }

    public void selectOption(ProductFeatures feature, ConfigurableOptions value) {
        selectedOptions.put(feature, value);
    }

    public BigDecimal getFinalPrice() {
        BigDecimal optionsPrice = selectedOptions.values().stream()
                .map(ConfigurableOptions::getAdditionalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return product.getPrice().add(optionsPrice);
    }

    public List<ConfigurableOptions> getOptionsForFeature(ProductFeatures feature) {
        Class<? extends ConfigurableOptions> enumClass = FeatureOption.getOptionClass(feature);
        return List.of(enumClass.getEnumConstants());
    }

    public Product getProduct() {
        return product;
    }

    public Map<ProductFeatures, ConfigurableOptions> getSelectedOptions() {
        return selectedOptions;
    }

    @Override
    public String toString() {
        return product.getName() + " " + selectedOptions.values();
    }
}

