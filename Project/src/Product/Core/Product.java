package Product.Core;

import Product.Elements.ProductFeatures;
import Product.Elements.ProductType;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private int quantity;
    private final ProductType productType;
    private final List<ProductFeatures> configurableFeatures;

    public Product(int id, String name, BigDecimal price, int quantity, ProductType productType, List<ProductFeatures> configurableFeatures) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productType = productType;
        this.configurableFeatures = configurableFeatures;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public List<ProductFeatures> getConfigurableFeatures() {
        return configurableFeatures;
    }

    @Override
    public String toString() {
        return "Id: "+ id + " " + name + " " + price + "zł | Ilość: " + quantity + " sztuk\n";
    }
}
