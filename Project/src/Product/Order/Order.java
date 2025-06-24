package Product.Order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final Person client;
    private final List<ClientItems> orderedItems;
    private final BigDecimal totalPrice;
    private final Instant orderDate;

    public Order(Person client, List<ClientItems> orderedItems, BigDecimal totalPrice) {
        if (client == null) {
            throw new IllegalArgumentException("Klient nie może być pusty.");
        }
        if (orderedItems == null || orderedItems.isEmpty()) {
            throw new IllegalArgumentException("Lista produktów nie może być pusta.");
        }
        if (totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cena po rabacie nie może być ujemna.");
        }
        this.orderId = UUID.randomUUID().toString();
        this.client = client;
        this.orderedItems = List.copyOf(orderedItems);
        this.totalPrice = totalPrice;
        this.orderDate = Instant.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public Person getClient() {
        return client;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public List<ClientItems> getOrderedItems() {
        return orderedItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
