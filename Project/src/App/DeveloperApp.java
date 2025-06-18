package App;

import Product.Manager.ProductManager;
import Product.Product;
import Product.Storage;

import java.util.List;

public class DeveloperApp {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();

        List<Product> products = Storage.productsList();
        products.forEach(productManager::addProduct);

        System.out.println("Lista dostępnych produktów");
        productManager.getAllProducts().forEach(System.out::println);
    }
}
