package Product.Manager;

import Product.Exceptions.ProductAlreadyExistException;
import Product.Exceptions.ProductNotExistException;
import Product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductManager {
    private final Map<Integer, Product> productMap = new HashMap<>();

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

    public List<Product> getAllProducts() {
        return List.copyOf(productMap.values());
    }

    public Optional<Product> getProductById(Integer id) {
        return Optional.ofNullable(productMap.get(id));
    }
}
