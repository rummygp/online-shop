package manager;

import exceptions.ProductAlreadyExistException;
import exceptions.ProductNotExistException;
import model.Product;
import utility.Storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Klasa odpowiedzialna za zarządzanie produktami w sklepie internetowym.
 * Umożliwia dodawanie, usuwanie, modyfikowanie oraz pobieranie produktów z magazynu.
 */
public class ProductManager {
    private final Map<Integer, Product> productMap = new HashMap<>();

    /**
     * Ładuje początkowe produkty do magazynu na podstawie danych ze Storage.
     */
    public void loadInitialProducts() {
        Storage.getInitialProducts().forEach(this::addProduct);
    }

    /**
     * Dodaje nowy produkt do magazynu.
     *
     * @param product produkt do dodania
     * @throws ProductAlreadyExistException jeśli produkt o danym ID już istnieje
     */
    public void addProduct(Product product) {
        if (productMap.containsKey(product.getId())) {
            throw new ProductAlreadyExistException("Produkt o wprowadzonym ID, już istnieje");
        }
        productMap.put(product.getId(), product);
    }

    /**
     * Usuwa produkt o podanym ID z magazynu.
     *
     * @param id identyfikator produktu do usunięcia
     * @throws ProductNotExistException jeśli produkt o podanym ID nie istnieje
     */
    public void removeProduct (Integer id) {
        if (!productMap.containsKey(id)) {
            throw new ProductNotExistException("Produkt o podanym ID, nie istnieje");
        } productMap.remove(id);
    }

    /**
     * Aktualizuje istniejący produkt w magazynie.
     *
     * @param updatedProduct produkt z nowymi danymi
     * @throws ProductNotExistException jeśli produkt o podanym ID nie istnieje
     */
    public void updateProduct(Product updatedProduct) {
        Integer id = updatedProduct.getId();
        if (!productMap.containsKey(id)) {
            throw new ProductNotExistException("Produkt o podanym ID, nie istnieje");
        }
        productMap.put(id, updatedProduct);
    }

    /**
     * Zmniejsza ilość sztuk danego produktu w magazynie.
     *
     * @param productId identyfikator produktu
     * @param quantity  ilość do odjęcia
     * @throws IllegalArgumentException jeśli produkt nie istnieje
     */
    public void reduceProductQuantity(int productId, int quantity) {
        Product product = getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Produkt o podanym ID nie istnieje."));

        product.setQuantity(product.getQuantity() - quantity);
    }

    /**
     * Zwraca listę wszystkich dostępnych produktów w magazynie.
     *
     * @return lista produktów
     */
    public List<Product> getAllProducts() {
        return List.copyOf(productMap.values());
    }

    /**
     * Pobiera produkt o podanym ID, jeśli istnieje.
     *
     * @param id identyfikator produktu
     * @return Optional zawierający produkt lub pusty, jeśli nie znaleziono produktu
     */
    public Optional<Product> getProductById(Integer id) {
        return Optional.ofNullable(productMap.get(id));
    }
}
