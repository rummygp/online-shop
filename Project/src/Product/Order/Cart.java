package Product.Order;

import Product.Core.ConfiguratedProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<ClientItems> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public List<ClientItems> getCartItems() {
        return cartItems;
    }

    public void addToCart(ConfiguratedProduct product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Nie można dodać do koszyka: produkt nie istnieje");
        }

        if (product.getProduct().getQuantity() <= 0) {
            throw new IllegalArgumentException("Brak produktu w magazynie");
        }
        ClientItems newClientItems = new ClientItems(product, quantity);

        for (ClientItems clientItems : cartItems) {
            if (clientItems.equals(newClientItems)) {
                clientItems.setQuantity(clientItems.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(newClientItems);
    }

    public String placeOrder() {
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Koszyk jest pusty. Nie można złożyć zamówienia.");
        }
        cartItems.clear();
        return "Zamówienie zostało złożone. Koszyk jest pusty";
    }

    public BigDecimal getTotalValue() {
        return cartItems.stream()
                .map(ClientItems::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
