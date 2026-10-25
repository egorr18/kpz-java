package app;

import delivery.method.CourierDelivery;
import delivery.method.PickupDelivery;
import delivery.method.PostDelivery;
import delivery.model.Order;
import delivery.pricing.FlatRateCalculator;
import delivery.pricing.FreeAboveThresholdCalculator;
import delivery.pricing.WeightDistanceCalculator;
import delivery.service.DeliveryService;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/** Єдина точка збирання: тут створюються всі об'єкти й передаються сервісу ззовні. */
public class Main {

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        Order laptop = new Order("A-1001", "Київ", "вул. Хрещатик, 22", 2.4, 12, new BigDecimal("38999"));
        Order books = new Order("A-1002", "Львів", "", 1.1, 540, new BigDecimal("640"));

        var weightTariff = new WeightDistanceCalculator(new BigDecimal("45"), new BigDecimal("12"), new BigDecimal("0.15"));
        var flatTariff = new FlatRateCalculator(new BigDecimal("80"));
        var promoTariff = new FreeAboveThresholdCalculator(new BigDecimal("1000"), weightTariff);

        out.println("=== Конфігурація 1: кур'єр + тариф за вагою/відстанню ===");
        out.println(new DeliveryService(new CourierDelivery(), weightTariff).process(laptop));

        out.println();
        out.println("=== Конфігурація 2: та сама доставка, інший тариф (замінено лише калькулятор) ===");
        out.println(new DeliveryService(new CourierDelivery(), promoTariff).process(laptop));

        out.println();
        out.println("=== Конфігурація 3: пошта + той самий тариф (замінено лише спосіб доставки) ===");
        out.println(new DeliveryService(new PostDelivery(), weightTariff).process(books));

        out.println();
        out.println("=== Конфігурація 4: самовивіз + фіксований тариф (без відстеження) ===");
        out.println(new DeliveryService(new PickupDelivery("Rozetka, вул. Сихівська, 16"), flatTariff).process(books));

        out.println();
        out.println("=== Конфігурація 5: кур'єр без адреси ===");
        out.println(new DeliveryService(new CourierDelivery(), flatTariff).process(books));
    }
}
