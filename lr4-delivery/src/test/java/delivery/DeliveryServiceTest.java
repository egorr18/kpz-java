package delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import delivery.method.CourierDelivery;
import delivery.method.DeliveryMethod;
import delivery.method.PickupDelivery;
import delivery.method.Trackable;
import delivery.model.Order;
import delivery.pricing.CostCalculator;
import delivery.pricing.FlatRateCalculator;
import delivery.pricing.FreeAboveThresholdCalculator;
import delivery.pricing.WeightDistanceCalculator;
import delivery.service.DeliveryService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class DeliveryServiceTest {

    private final Order order = new Order("T-1", "Київ", "вул. Тестова, 1", 2.0, 10, new BigDecimal("500"));

    @Test
    void serviceUsesInjectedCalculator() {
        CostCalculator fixed = new FlatRateCalculator(new BigDecimal("99"));
        var receipt = new DeliveryService(new CourierDelivery(), fixed).process(order);
        assertEquals(new BigDecimal("99.00"), receipt.cost());
    }

    @Test
    void weightAndDistanceTariff() {
        var calc = new WeightDistanceCalculator(new BigDecimal("45"), new BigDecimal("12"), new BigDecimal("0.15"));
        assertEquals(new BigDecimal("70.50"), calc.cost(order));   // 45 + 2*12 + 10*0.15
    }

    @Test
    void freeAboveThresholdWrapsAnyCalculator() {
        var promo = new FreeAboveThresholdCalculator(new BigDecimal("400"), new FlatRateCalculator(new BigDecimal("80")));
        assertEquals(new BigDecimal("0.00"), promo.cost(order));
    }

    @Test
    void trackingOnlyForTrackableMethods() {
        CostCalculator any = new FlatRateCalculator(BigDecimal.TEN);
        assertTrue(new DeliveryService(new CourierDelivery(), any).process(order).tracking().isPresent());
        assertFalse(new DeliveryService(new PickupDelivery("Пункт"), any).process(order).tracking().isPresent());
        assertFalse(Trackable.class.isAssignableFrom(PickupDelivery.class));
    }

    @Test
    void undeliverableOrderIsNotChargedOrTracked() {
        Order noAddress = new Order("T-2", "Львів", "", 1.0, 5, new BigDecimal("100"));
        var receipt = new DeliveryService(new CourierDelivery(), new FlatRateCalculator(new BigDecimal("80"))).process(noAddress);
        assertEquals(new BigDecimal("0.00"), receipt.cost());
        assertFalse(receipt.tracking().isPresent());
    }

    @Test
    void anyNewMethodWorksWithoutChangingService() {
        DeliveryMethod drone = new DeliveryMethod() {          // реалізація «на льоту»
            public String name() { return "Дрон"; }
            public int estimatedDays(Order o) { return 0; }
            public String deliver(Order o) { return "дрон доставив " + o.id(); }
        };
        var receipt = new DeliveryService(drone, new FlatRateCalculator(BigDecimal.ONE)).process(order);
        assertEquals("Дрон", receipt.method());
        assertEquals("дрон доставив T-1", receipt.result());
    }
}
