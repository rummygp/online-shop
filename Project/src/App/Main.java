package App;

import Configuration.ConfigurationInterfaces.ConfigurableOptions;
import Product.Core.ConfiguratedProduct;
import Product.Core.Product;
import Product.Elements.ProductFeatures;
import Product.Manager.ProductManager;
import Product.Order.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();
        OrderProcessor orderProcessor = new OrderProcessor();
        ProductManager productManager = new ProductManager();

        productManager.loadInitialProducts();

        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Przeglądaj produkty");
            System.out.println("2. Dodaj produkt do koszyka");
            System.out.println("3. Pokaż koszyk");
            System.out.println("4. Złóż zamówienie");
            System.out.println("5. Wyjście");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> displayProducts(productManager);
                case "2" -> addProductToCart(scanner, cart, productManager);
                case "3" -> showCart(cart);
                case "4" -> placeOrder(scanner, cart, orderProcessor, productManager);
                case "5" -> {
                    System.out.println("Do widzenia!");
                    running = false;
                }
                default -> System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
            }
        }
    }

    private static void displayProducts(ProductManager productManager) {
        System.out.println("\n=== Lista produktów ===");
        for (Product product : productManager.getAllProducts()) {
            System.out.print(product);
        }
    }

    private static void addProductToCart(Scanner scanner, Cart cart, ProductManager productManager) {
        try {
            displayProducts(productManager);
            System.out.println("Wpisz ID produktu, który chcesz dodać:");

            int id = Integer.parseInt(scanner.nextLine());
            Product selectedProduct = productManager.getProductById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Produkt o podanym ID nie istnieje."));

            if (selectedProduct.getQuantity() <= 0) {
                System.out.println("Produkt niedostępny w magazynie.");
                return;
            }

            ConfiguratedProduct config = new ConfiguratedProduct(selectedProduct);

            Optional.ofNullable(selectedProduct.getConfigurableFeatures())
                    .ifPresent(features -> {
                        for (ProductFeatures feature : features) {
                            List<ConfigurableOptions> options = config.getOptionsForFeature(feature);
                            System.out.println("Wybierz opcję dla: " + feature.getLabel());
                            for (int i = 0; i < options.size(); i++) {
                                System.out.println((i + 1) + ". " + options.get(i).getLabel() + " (" + options.get(i).getAdditionalPrice() + "zł)");
                            }

                            int choice = Integer.parseInt(scanner.nextLine());
                            config.selectOption(feature, options.get(choice - 1));
                        }
                    });

            System.out.println("Podaj ilość:");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (quantity <= 0 || quantity > selectedProduct.getQuantity()) {
                System.out.println("Nieprawidłowa ilość.");
                return;
            }

            cart.addToCart(config, quantity);
            System.out.println("Produkt został dodany do koszyka.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showCart(Cart cart) {
        System.out.println("\n=== Zawartość koszyka ===");
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Koszyk jest pusty.");
            return;
        }

        for (ClientItems item : cart.getCartItems()) {
            System.out.println("- " + item.getFinalProduct().getProduct().getName() +
                    ", ilość: " + item.getQuantity() +
                    ", cena końcowa: " + item.getTotalPrice() + " zł");
        }
        System.out.println("Łączna wartość koszyka: " + cart.getTotalValue() + " zł");
    }

    private static void placeOrder(Scanner scanner, Cart cart, OrderProcessor orderProcessor, ProductManager productManager) {
        try {
            System.out.println("\n=== Składanie zamówienia ===");
            System.out.println("Podaj imię:");
            String name = scanner.nextLine();
            System.out.println("Podaj nazwisko:");
            String lastName = scanner.nextLine();
            System.out.println("Podaj adres:");
            String address = scanner.nextLine();
            System.out.println("Podaj e-mail:");
            String email = scanner.nextLine();
            System.out.println("Podaj wiek:");
            int age = Integer.parseInt(scanner.nextLine());

            Person person = new Person(name, lastName, address, email, age);
            Order order = new Order(person, cart.getCartItems());
            String invoice = orderProcessor.processOrder(order);

            System.out.println("\n=== Twoja faktura ===");
            System.out.println(invoice);

            for (ClientItems item : cart.getCartItems()) {
                int productId = item.getFinalProduct().getProduct().getId();
                int quantity = item.getQuantity();
                productManager.reduceProductQuantity(productId, quantity);
            }

            System.out.println(cart.placeOrder());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}