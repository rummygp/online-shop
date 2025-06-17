package Product;

import Configuration.ConfigurationInterfaces.GetLabel;
import Configuration.ConfigurationInterfaces.HasAdditionalPrice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductConfiguration {
    private final Product product;
    private final Map<ProductFeatures, Enum<?>> selectedOptions;

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

    public static ProductConfiguration configureProduct(Product product, Scanner scanner) {
        ProductConfiguration config = new ProductConfiguration(product);

        if (product.getConfigurableFeatures() != null) {
            for (ProductFeatures feature : product.getConfigurableFeatures()) {
                Class<? extends Enum<?>> enumClass = FeatureOption.getOptionClass(feature);
                Enum<?>[] options = enumClass.getEnumConstants();
                System.out.println("Proszę wybrać: " + feature.getLabel());

                for (int i = 0; i < options.length; i++) {
                    Enum<?> option = options[i];
                    String label = ((GetLabel) option).getLabel();
                    System.out.println((i + 1) + ". " + label);
                }
                int choice2 = Integer.parseInt(scanner.nextLine());
                Enum<?> selectedOption = options[choice2 - 1];
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
