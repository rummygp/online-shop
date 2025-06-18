package Product;

import Product.Elements.ProductFeatures;
import Product.Elements.ProductType;

import java.math.BigDecimal;
import java.util.List;

public class Storage {
    public static List<Product> productsList() {
        return List.of(
                new Product(1, "Lenovo", new BigDecimal("1999.99"), 10, ProductType.COMPUTER, List.of(ProductFeatures.RAM,
                        ProductFeatures.PROCESSOR)),
                new Product(2, "iPhone", new BigDecimal("2999.99"), 10, ProductType.SMARTPHONE, List.of(ProductFeatures.COLOR,
                        ProductFeatures.ADDITIONALACCRESSORIES, ProductFeatures.BATTERYCAPACITY)),
                new Product(3, "Airpods", new BigDecimal("599.99"), 10, ProductType.ELECTRONICS, null));
    }
}
