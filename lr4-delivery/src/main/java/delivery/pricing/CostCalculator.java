package delivery.pricing;

import delivery.model.Order;
import java.math.BigDecimal;

/** Друга, незалежна залежність сервісу — розрахунок вартості доставки. */
public interface CostCalculator {

    String description();

    BigDecimal cost(Order order);
}
