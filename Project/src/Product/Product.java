package Product;

import Product.Elements.ProductFeatures;
import Product.Elements.ProductType;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private final int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private ProductType productType;
    private List<ProductFeatures> configurableFeatures;

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

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuanity() {
        return quantity;
    }

    public void setQuanity(int quanity) {
        this.quantity = quanity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<ProductFeatures> getConfigurableFeatures() {
        return configurableFeatures;
    }

    public void setConfigurableFeatures(List<ProductFeatures> configurableFeatures) {
        this.configurableFeatures = configurableFeatures;
    }

    @Override
    public String toString() {
        return "Id: "+ id + " " + name + " " + price + "zł\n";
    }
}
