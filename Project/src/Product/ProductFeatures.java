package Product;

public enum ProductFeatures {
    RAM("Pamięć RAM"),
    PROCESSOR("Procesor"),
    COLOR("Kolor"),
    BATTERYCAPACITY("Pojemność baterii"),
    ADDITIONALACCRESSORIES("Dodatkowe akcesoria");

    private final String productFeatures;

    ProductFeatures(String productFeature) {
        this.productFeatures = productFeature;
    }

    public String getProductFeatures() {
        return productFeatures;
    }
}
