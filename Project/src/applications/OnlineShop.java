package applications;

import manager.ProductManager;
import model.ClientSession;
import services.DiscountService;
import services.OrderPersistenceService;
import services.OrderProcessor;

import java.util.Scanner;

/**
 * Klasa reprezentująca ogólny stan sklepu internetowego.
 * Zarządza zasobami globalnymi sklepu i umożliwia wielokrotne uruchamianie sesji klienta.
 */
public class OnlineShop {

    private final Scanner scanner;
    private final ProductManager productManager;
    private final OrderProcessor orderProcessor;
    private final OrderPersistenceService orderPersistenceService;
    private final DiscountService discountService;

    public OnlineShop() {
        scanner = new Scanner(System.in);
        productManager = new ProductManager();
        orderProcessor = new OrderProcessor();
        orderPersistenceService = new OrderPersistenceService();
        discountService = new DiscountService();
    }

    /**
     * Metoda uruchamiająca sklep - ładuje produkty.
     */
    public void start() {
        productManager.loadInitialProducts();
    }

    /**
     * Uruchamia nową sesję klienta.
     */
    public void startClientSession() {
        ClientSession session = new ClientSession(scanner, productManager, orderProcessor, discountService);
        session.start();
    }

    /**
     * Zamyka zasoby globalne aplikacji.
     */
    public void shutdown() {
        orderProcessor.shutdown();
        scanner.close();
    }
}