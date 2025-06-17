package Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Storage {
    public List<Product> productsList = List.of(
    new Product(1, "Lenovo", new BigDecimal("1999.99"), 10, ProductType.COMPUTER, Set.of(ProductFeatures.RAM,
            ProductFeatures.PROCESSOR)),
    new Product(2, "iPhone", new BigDecimal("2999.99"), 10, ProductType.SMARTPHONE, Set.of(ProductFeatures.COLOR,
            ProductFeatures.ADDITIONALACCRESSORIES,ProductFeatures.BATTERYCAPACITY)),
    new Product(3, "airpods", new BigDecimal("599.99"), 10, ProductType.ELECTRONICS, null));
}
