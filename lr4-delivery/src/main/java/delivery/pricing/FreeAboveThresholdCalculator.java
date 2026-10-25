package delivery.pricing;

import delivery.model.Order;
import java.math.BigDecimal;

/**
 * Безкоштовна доставка для замовлень від певної суми; інакше — тариф іншого калькулятора.
 * Обгортає будь-який CostCalculator, тож поєднується з наявними без їх зміни.
 */
public class FreeAboveThresholdCalculator implements CostCalculator {

    private final BigDecimal threshold;
    private final CostCalculator fallback;

    public FreeAboveThresholdCalculator(BigDecimal threshold, CostCalculator fallback) {
        this.threshold = threshold;
        this.fallback = fallback;
    }

    @Override
    public String description() {
        return "безкоштовно від " + threshold + " грн, інакше " + fallback.description();
    }

    @Override
    public BigDecimal cost(Order order) {
        return order.value().compareTo(threshold) >= 0 ? new BigDecimal("0.00") : fallback.cost(order);
    }
}
