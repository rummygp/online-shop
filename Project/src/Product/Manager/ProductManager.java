package Product.Manager;

import Product.Exceptions.ProductAlreadyExistException;
import Product.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductManager {
    private final Map<Integer, Product> productMap = new HashMap<>();

    public void addProduct(Product product) {
        if (productMap.containsKey(product.getId())) {
            throw new ProductAlreadyExistException("Produkt o wprowadzonym ID, już istnieje");
        }
        productMap.put(product.getId(), product);
    }
}
