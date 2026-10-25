package delivery.pricing;

import delivery.model.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;

/** База + плата за кілограм + плата за кілометр. */
public class WeightDistanceCalculator implements CostCalculator {

    private final BigDecimal base;
    private final BigDecimal perKg;
    private final BigDecimal perKm;

    public WeightDistanceCalculator(BigDecimal base, BigDecimal perKg, BigDecimal perKm) {
        this.base = base;
        this.perKg = perKg;
        this.perKm = perKm;
    }

    @Override
    public String description() {
        return "база " + base + " + " + perKg + "/кг + " + perKm + "/км";
    }

    @Override
    public BigDecimal cost(Order order) {
        return base
                .add(perKg.multiply(BigDecimal.valueOf(order.weightKg())))
                .add(perKm.multiply(BigDecimal.valueOf(order.distanceKm())))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
