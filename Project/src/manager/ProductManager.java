package manager;

import exceptions.ProductAlreadyExistException;
import exceptions.ProductNotExistException;
import model.Product;
import utility.Storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductManager {
    private final Map<Integer, Product> productMap = new HashMap<>();

    public void loadInitialProducts() {
        Storage.getInitialProducts().forEach(this::addProduct);
    }

    public void addProduct(Product product) {
        if (productMap.containsKey(product.getId())) {
            throw new ProductAlreadyExistException("Produkt o wprowadzonym ID, już istnieje");
        }
        productMap.put(product.getId(), product);
    }

    public void removeProduct (Integer id) {
        if (!productMap.containsKey(id)) {
            throw new ProductNotExistException("Produkt o podanym ID, nie istnieje");
        } productMap.remove(id);
    }
    public void updateProduct(Product updatedProduct) {
        Integer id = updatedProduct.getId();
        if (!productMap.containsKey(id)) {
            throw new ProductNotExistException("Produkt o podanym ID, nie istnieje");
        }
        productMap.put(id, updatedProduct);
    }

    public void reduceProductQuantity(int productId, int quantity) {
        Product product = getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Produkt o podanym ID nie istnieje."));

        product.setQuantity(product.getQuantity() - quantity);
    }

    public List<Product> getAllProducts() {
        return List.copyOf(productMap.values());
    }

    public Optional<Product> getProductById(Integer id) {
        return Optional.ofNullable(productMap.get(id));
    }
}
