package delivery.method;

import delivery.model.Order;

public class CourierDelivery extends AbstractDeliveryMethod implements Trackable {

    public CourierDelivery() {
        super("Кур'єр");
    }

    @Override
    public int estimatedDays(Order order) {
        return order.distanceKm() <= 30 ? 1 : 2;
    }

    @Override
    protected String handOver(Order order) {
        return "кур'єр привезе замовлення " + order.id() + " на адресу " + order.city() + ", " + order.address();
    }

    @Override
    public String trackingNumber(Order order) {
        return makeTrackingNumber("CR", order);
    }

    @Override
    public String trackingStatus(String trackingNumber) {
        return trackingNumber + ": кур'єр прямує до отримувача";
    }
}
