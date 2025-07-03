package services;

import interfaces.ConfigurableOptions;
import model.*;
import enums.ProductFeatures;
import manager.ProductManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za obsługę menu oraz interakcję z użytkownikiem.
 */
public class MenuController {

    Scanner scanner;
    Cart cart;
    ProductManager productManager;
    OrderProcessor orderProcessor;
    DiscountService discountService;

    public MenuController(Scanner scanner, Cart cart, ProductManager productManager, OrderProcessor orderProcessor, DiscountService discountService) {
        this.scanner = scanner;
        this.cart = cart;
        this.productManager = productManager;
        this.orderProcessor = orderProcessor;
        this.discountService = discountService;
    }

    public void runMenu() {
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
                case "1" -> displayProducts();
                case "2" -> addProductToCart();
                case "3" -> showCart();
                case "4" -> placeOrder();
                case "5" -> running = false;
                default -> System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
            }
        }
    }


    private void displayProducts() {
        System.out.println("\n=== Lista produktów ===");
        for (Product product : productManager.getAllProducts()) {
            System.out.print(product);
        }
    }

    private void addProductToCart() {
        try {
            displayProducts();
            System.out.println("Wpisz ID produktu, który chcesz dodać:");

            int id = Integer.parseInt(scanner.nextLine());
            Product selectedProduct = productManager.getProductById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Produkt o podanym ID nie istnieje."));

            if (selectedProduct.getQuantity() <= 0) {
                System.out.println("Produkt niedostępny w magazynie.");
                return;
            }

            ConfiguratedProduct config = new ConfiguratedProduct(selectedProduct);

            List<ProductFeatures> features = selectedProduct.getConfigurableFeatures();
            if (!features.isEmpty()) {
                for (ProductFeatures feature : features) {
                    List<ConfigurableOptions> options = config.getOptionsForFeature(feature);
                    System.out.println("Wybierz opcję dla: " + feature.getLabel());
                    for (int i = 0; i < options.size(); i++) {
                        System.out.println((i + 1) + ". " + options.get(i).getLabel() + " (" + options.get(i).getAdditionalPrice() + "zł)");
                    }

                            int choice = Integer.parseInt(scanner.nextLine());
                            config.selectOption(feature, options.get(choice - 1));
                        }
                    };

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

    private void showCart() {
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

    private void placeOrder() {
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

            BigDecimal totalPrice = cart.getTotalValue();
            BigDecimal discountedPrice = discountService.applyDiscount(totalPrice);

            if (discountedPrice.compareTo(totalPrice) < 0) {
                System.out.println("Cena przed rabatem: " + totalPrice + " zł");
                System.out.println("Cena po rabacie: " + discountedPrice + " zł");
            } else {
                System.out.println("Cena całkowita: " + discountedPrice + " zł");
            }

            Order order = new Order(person, cart.getCartItems(), discountedPrice);

            orderProcessor.processOrderAsync(order, invoice -> {
                System.out.println("\n=== Twoja faktura ===");
                System.out.println(invoice);
                System.out.println("Zamówienie zostało zapisane do pliku.\n");
            });

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