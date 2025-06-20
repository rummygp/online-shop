package Product.Order;

import java.util.List;

public class Order {
    String orderId;
    Person client;
    List<Cart> cartItems;
    Cart orderPrice;

    public Order(String orderId, Person client, List<Cart> cartItems, Cart orderPrice) {
        if (client == null) {
            throw new IllegalArgumentException("Klient nie może być pusty.");
        }
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("Lista produktów nie może być pusta.");
        }
        this.orderId = orderId;
        this.client = client;
        this.cartItems = cartItems;
        this.orderPrice = orderPrice;
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

    public List<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Cart> cartItems) {
        this.cartItems = cartItems;
    }

    public Cart getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Cart orderPrice) {
        this.orderPrice = orderPrice;
    }
}
