package Product.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final Person client;
    private final List<ClientItems> orderedItems;
    private final BigDecimal totalPrice;
    private final LocalDateTime orderDate;

    public Order(Person client, List<ClientItems> orderedItems) {
        if (client == null) {
            throw new IllegalArgumentException("Klient nie może być pusty.");
        }
        if (orderedItems == null || orderedItems.isEmpty()) {
            throw new IllegalArgumentException("Lista produktów nie może być pusta.");
        }
        this.orderId = UUID.randomUUID().toString();
        this.client = client;
        this.orderedItems = List.copyOf(orderedItems);
        this.totalPrice = calculateTotalPrice(orderedItems);
        this.orderDate = LocalDateTime.now();
    }

    private BigDecimal calculateTotalPrice(List<ClientItems> items) {
        return items.stream()
                .map(ClientItems::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getOrderId() {
        return orderId;
    }

    public Person getClient() {
        return client;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<ClientItems> getOrderedItems() {
        return orderedItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
