package applications;

import manager.ProductManager;
import model.Cart;
import services.DiscountService;
import services.MenuController;
import services.OrderPersistenceService;
import services.OrderProcessor;

import java.util.Scanner;

/**
 * Główna klasa aplikacji sklepu internetowego.
 * Inicjalizuje komponenty i przekazuje je do kontrolera odpowiedzialnego za obsługę menu.
 */
public class OnlineShop {

    Scanner scanner;
    Cart cart;
    OrderProcessor orderProcessor;
    ProductManager productManager;
    OrderPersistenceService orderPersistenceService;
    DiscountService discountService;
    MenuController menuController;

    /**
     * Konstruktor inicjalizujący komponenty, ale bez uruchamiania logiki aplikacji.
     */
    public OnlineShop() {
        scanner = new Scanner(System.in);
        cart = new Cart();
        orderProcessor = new OrderProcessor();
        productManager = new ProductManager();
        orderPersistenceService = new OrderPersistenceService();
        discountService = new DiscountService();

        menuController = new MenuController(scanner, cart, productManager, orderProcessor, discountService);
    }

    /**
     * Uruchamia aplikację: wczytuje dane i startuje interfejs użytkownika.
     */
    public void start() {
        productManager.loadInitialProducts();
        menuController.runMenu();
        orderProcessor.shutdown();
        scanner.close();
    }
}