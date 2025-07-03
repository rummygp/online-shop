package applications;

import interfaces.ConfigurableOptions;
import model.*;
import enums.ProductFeatures;
import manager.ProductManager;
import services.OrderPersistenceService;
import services.OrderProcessor;
import services.DiscountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Główna klasa aplikacji sklepu internetowego.
 * Odpowiada za obsługę interfejsu tekstowego, zarządzanie koszykiem, zamówieniami i produktami.
 */
public class OnlineShop {
    Scanner scanner;
    Cart cart;
    OrderProcessor orderProcessor;
    ProductManager productManager;
    OrderPersistenceService orderPersistenceService;
    DiscountService discountService;

    /**
     * Konstruktor klasy inicjalizujący niezbędne komponenty oraz uruchamiający pętlę menu.
     */
    public OnlineShop() {
        scanner = new Scanner(System.in);
        cart = new Cart();
        orderProcessor = new OrderProcessor();
        productManager = new ProductManager();
        orderPersistenceService = new OrderPersistenceService();
        discountService = new DiscountService();

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
                case "1" -> displayProducts();
                case "2" -> addProductToCart();
                case "3" -> showCart();
                case "4" -> placeOrder();
                case "5" -> {
                    System.out.println("Do widzenia!");
                    running = false;
                }
                default -> System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
            }
        }
        orderProcessor.shutdown();
        scanner.close();
    }

    /**
     * Wyświetla listę dostępnych produktów w sklepie.
     */
    private void displayProducts() {
        System.out.println("\n=== Lista produktów ===");
        for (Product product : productManager.getAllProducts()) {
            System.out.print(product);
        }
    }

    /**
     * Pozwala dodać produkt do koszyka wraz z konfiguracją oraz wyborem ilości.
     * Weryfikuje dostępność produktu i poprawność danych wejściowych.
     */
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

    /**
     * Wyświetla zawartość koszyka oraz łączną wartość produktów.
     */
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

    /**
     * Umożliwia złożenie zamówienia przez podanie danych klienta.
     * Oblicza cenę końcową z uwzględnieniem ewentualnych rabatów.
     * Przetwarza zamówienie asynchronicznie i aktualizuje stan magazynu.
     */
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
