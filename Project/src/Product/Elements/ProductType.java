package Product.Elements;

public enum ProductType {
    COMPUTER("Komputer"),
    SMARTPHONE("Smartphone"),
    ELECTRONICS("Elektronika");

    private final String productType;

    ProductType(String productType) {
        this.productType = productType;
    }
}
