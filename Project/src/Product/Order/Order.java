package Product.Order;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    String orderId;
    Person client;
    List<ClientItems> orderedItems;
    BigDecimal totalPrice;

    public Order(String orderId, Person client, List<ClientItems> orderedItems) {
        if (client == null) {
            throw new IllegalArgumentException("Klient nie może być pusty.");
        }
        if (orderedItems == null || orderedItems.isEmpty()) {
            throw new IllegalArgumentException("Lista produktów nie może być pusta.");
        }
        this.orderId = orderId;
        this.client = client;
        this.orderedItems = orderedItems;
        this.totalPrice = calculateTotalPrice(orderedItems);
    }

    private BigDecimal calculateTotalPrice(List<ClientItems> items) {
        return items.stream()
                .map(ClientItems::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public List<ClientItems> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<ClientItems> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
