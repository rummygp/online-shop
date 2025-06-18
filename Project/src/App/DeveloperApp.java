package App;

import Product.Manager.ProductManager;
import Product.Product;
import Product.Storage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DeveloperApp {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();

        List<Product> products = Storage.productsList();
        products.forEach(productManager::addProduct);

        System.out.println("Lista dostępnych produktów");
        productManager.getAllProducts().forEach(System.out::println);

        Optional<Product> product1 = productManager.getProductById(products.get(2).getId());
        product1.ifPresent(product -> System.out.println("Znaleziony produkt to: " + product.getName()));

        Product smartphone = products.get(1);
        Product updatedSmartphone = new Product(
                smartphone.getId(),
                smartphone.getName() + " model 2",
                smartphone.getPrice().add(new BigDecimal("500")),
                5,
                smartphone.getProductType(),
                smartphone.getConfigurableFeatures()
        );
        productManager.updateProduct(updatedSmartphone);

        Product product2 = products.get(0);
        productManager.removeProduct(product2.getId());

        System.out.println("=== LISTA PRODUKTÓW ===");
        productManager.getAllProducts().forEach(System.out::println);
    }
}
