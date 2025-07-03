package services;

import interfaces.OrderCallback;
import model.Order;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderProcessor {

    private final InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
    private final OrderPersistenceService orderPersistenceService = new OrderPersistenceService();
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    public void processOrderAsync(Order order, OrderCallback callback) {
        executorService.submit(() -> {
            String invoice = invoiceGenerator.generateInvoice(order);
            orderPersistenceService.saveOrderToFile(order);
            callback.onOrderProcessed(invoice);
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
