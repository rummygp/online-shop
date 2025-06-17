package App;

import Product.Product;
import Product.ProductConfiguration;
import Product.Storage;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(storage.productsList);
            System.out.println("Proszę wybrać produkt:");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                Product selectedProduct = storage.productsList.get(choice - 1);
                ProductConfiguration config = ProductConfiguration.configureProduct(selectedProduct, scanner);

                BigDecimal finalPrice = config.getFinalPrice();
                System.out.println("Cena końcowa: " + finalPrice + " zł, za " + config);
                running = false;
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.err.println("Podano nieprawidłową wartość");
            }
        }
    }
}