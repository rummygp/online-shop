package App;

import Configuration.ConfigurationInterfaces.ConfigurableOptions;
import Product.Elements.ProductFeatures;
import Product.Product;
import Product.ProductConfiguration;
import Product.Storage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println(storage.productsList);
            try {
                Product selectedProduct = storage.productsList.get(Integer.parseInt(scanner.nextLine()) - 1);
                ProductConfiguration config = new ProductConfiguration(selectedProduct);

                for (ProductFeatures feature : selectedProduct.getConfigurableFeatures()) {
                    List<ConfigurableOptions> options = config.getOptionsForFeature(feature);

                    System.out.println("Proszę wybrać: " + feature.getLabel());
                    for (int i = 0; i < options.size(); i++) {
                        System.out.println((i + 1) + ". " + options.get(i).getLabel() + " (" + options.get(i).getAdditionalPrice() + "zł)");
                    }

                    int choice = Integer.parseInt(scanner.nextLine());
                    config.selectOption(feature, options.get(choice - 1));
                }
                System.out.println("Wybrany produkt: " + config.getProduct().getName());
                System.out.println("Wybrane opcje:");
                for (Map.Entry<ProductFeatures, ConfigurableOptions> entry : config.getSelectedOptions().entrySet()) {
                    System.out.println("- " + entry.getKey().getLabel() + ": " + entry.getValue().getLabel());
                }
                System.out.println("Cena końcowa: " + config.getFinalPrice() + " zł");
                running = false;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Podano złą wartość");
            }
        }
    }
}