package Product;

public enum ProductType {
    COMPUTER("Komputer"),
    SMARTPHONE("Smartphone"),
    ELECTRONICS("Elektronika");

    private final String productType;

    ProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }
}
