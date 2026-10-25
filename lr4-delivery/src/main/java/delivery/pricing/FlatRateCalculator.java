package delivery.pricing;

import delivery.model.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;

/** Фіксована ціна незалежно від ваги й відстані. */
public class FlatRateCalculator implements CostCalculator {

    private final BigDecimal price;

    public FlatRateCalculator(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String description() {
        return "фіксований тариф " + price + " грн";
    }

    @Override
    public BigDecimal cost(Order order) {
        return price;
    }
}
