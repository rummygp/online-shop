package Product;

import Configuration.ConfigurationInterfaces.ConfigurableOptions;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public static ProductConfiguration configureProduct(Product product, Scanner scanner) {
        ProductConfiguration config = new ProductConfiguration(product);

        if (product.getConfigurableFeatures() != null) {
            for (ProductFeatures feature : product.getConfigurableFeatures()) {
                Class<? extends ConfigurableOptions> enumClass = FeatureOption.getOptionClass(feature);
                ConfigurableOptions[] options = enumClass.getEnumConstants();
                System.out.println("Proszę wybrać: " + feature.getLabel());

                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i].getLabel() + " (" + options[i].getAdditionalPrice() + "zł)");
                }

                int choice2 = Integer.parseInt(scanner.nextLine());
                ConfigurableOptions selectedOption = options[choice2 - 1];
                config.selectOption(feature, selectedOption);
            }
        }
        return config;
    }

    @Override
    public String toString() {
        return product.getName() + " " + selectedOptions.values();
    }
}

