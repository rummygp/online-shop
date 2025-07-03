package services;

import manager.ProductManager;
import model.Cart;

import java.util.Scanner;

/**
 * Klasa reprezentująca indywidualną sesję klienta w sklepie.
 * Każdy klient posiada swój koszyk i interfejs obsługi.
 */
public class ClientSession {

    private final Scanner scanner;
    private final Cart cart;
    private final ProductManager productManager;
    private final OrderProcessor orderProcessor;
    private final DiscountService discountService;
    private final MenuController menuController;

    public ClientSession(ProductManager productManager, OrderProcessor orderProcessor, DiscountService discountService) {
        this.scanner = new Scanner(System.in);
        this.cart = new Cart();
        this.productManager = productManager;
        this.orderProcessor = orderProcessor;
        this.discountService = discountService;

        this.menuController = new MenuController(scanner, cart, productManager, orderProcessor, discountService);
    }

    public void start() {
        menuController.runMenu();
        scanner.close();
    }
}

