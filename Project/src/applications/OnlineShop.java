package applications;

import manager.ProductManager;
import services.ClientSession;
import services.DiscountService;
import services.OrderPersistenceService;
import services.OrderProcessor;

/**
 * Klasa reprezentująca ogólny stan sklepu internetowego.
 * Zarządza zasobami globalnymi sklepu i uruchamia sesje klientów.
 */
public class OnlineShop {

    private final ProductManager productManager;
    private final OrderProcessor orderProcessor;
    private final OrderPersistenceService orderPersistenceService;
    private final DiscountService discountService;

    public OnlineShop() {
        productManager = new ProductManager();
        orderProcessor = new OrderProcessor();
        orderPersistenceService = new OrderPersistenceService();
        discountService = new DiscountService();

        productManager.loadInitialProducts();
    }

    public void startClientSession() {
        ClientSession session = new ClientSession(productManager, orderProcessor, discountService);
        session.start();
    }
}