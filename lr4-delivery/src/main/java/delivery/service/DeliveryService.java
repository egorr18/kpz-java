package delivery.service;

import delivery.method.DeliveryMethod;
import delivery.method.Trackable;
import delivery.model.Order;
import delivery.pricing.CostCalculator;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Центральний сервіс. Працює лише з абстракціями DeliveryMethod і CostCalculator,
 * отримує їх через конструктор і нічого не створює сам (DIP). Нові способи доставки
 * чи тарифи додаються без жодної зміни цього класу (OCP).
 */
public class DeliveryService {

    private final DeliveryMethod method;
    private final CostCalculator calculator;

    public DeliveryService(DeliveryMethod method, CostCalculator calculator) {
        this.method = method;
        this.calculator = calculator;
    }

    public Receipt process(Order order) {
        if (!method.canDeliver(order)) {
            // Доставка неможлива: не нараховуємо вартість і не видаємо номер відстеження
            return new Receipt(order.id(), method.name(), calculator.description(), new BigDecimal("0.00"),
                    method.deliver(order) + " — замовлення відхилено", Optional.empty());
        }
        BigDecimal cost = calculator.cost(order);
        String result = method.deliver(order);
        // Відстеження — необов'язкова можливість: перевіряємо інтерфейс-можливість, а не конкретний клас
        Optional<String> tracking = Optional.empty();
        if (method instanceof Trackable trackable) {
            String number = trackable.trackingNumber(order);
            tracking = Optional.of(trackable.trackingStatus(number));
        }
        return new Receipt(order.id(), method.name(), calculator.description(), cost, result, tracking);
    }

    public record Receipt(String orderId, String method, String tariff, BigDecimal cost,
                          String result, Optional<String> tracking) {
        @Override
        public String toString() {
            return "Замовлення " + orderId + " | " + method + " | " + tariff + "\n"
                    + "  -> " + result + "\n"
                    + "  -> вартість доставки: " + cost + " грн\n"
                    + "  -> відстеження: " + tracking.orElse("недоступне для цього способу");
        }
    }
}
