package Product;

import java.math.BigDecimal;
import java.util.Set;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int quanity;
    private ProductType productType;
    private Set<ProductFeatures> configurableFeatures;

    public Product(int id, String name, BigDecimal price, int quanity, ProductType productType, Set<ProductFeatures> configurableFeatures) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quanity = quanity;
        this.productType = productType;
        this.configurableFeatures = configurableFeatures;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Set<ProductFeatures> getConfigurableFeatures() {
        return configurableFeatures;
    }

    public void setConfigurableFeatures(Set<ProductFeatures> configurableFeatures) {
        this.configurableFeatures = configurableFeatures;
    }

    @Override
    public String toString() {
        return name + " " + price + "zł\n";
    }
}
