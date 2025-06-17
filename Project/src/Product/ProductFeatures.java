package Product;

public enum ProductFeatures {
    RAM("Pamięć RAM"),
    PROCESSOR("Procesor"),
    COLOR("Kolor"),
    BATTERYCAPACITY("Pojemność baterii"),
    ADDITIONALACCRESSORIES("Dodatkowe akcesoria");

    private final String label;

    ProductFeatures(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
