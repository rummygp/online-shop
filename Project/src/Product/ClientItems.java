package Product;

import java.util.Objects;

public class ClientItems {
    ConfiguratedProduct finalProduct;
    int quantity;

    public ClientItems(ConfiguratedProduct finalProduct, int quantity) {
        this.finalProduct = finalProduct;
        this.quantity = quantity;
    }

    public ConfiguratedProduct getFinalProduct() {
        return finalProduct;
    }

    public void setFinalProduct(ConfiguratedProduct finalProduct) {
        this.finalProduct = finalProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientItems that = (ClientItems) o;
        return quantity == that.quantity && Objects.equals(finalProduct, that.finalProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalProduct, quantity);
    }
}
