package Product.Promotion;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountService {

    public BigDecimal applyDiscount(BigDecimal totalPrice) {
        if (totalPrice.compareTo(new BigDecimal("2000")) > 0) {
            BigDecimal discountedPrice = totalPrice.subtract(totalPrice.multiply(new BigDecimal("0.10")));
            return discountedPrice.setScale(2, RoundingMode.HALF_UP);
        }
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
