package delivery.method;

import delivery.model.Order;

public class PostDelivery extends AbstractDeliveryMethod implements Trackable {

    public PostDelivery() {
        super("Пошта");
    }

    @Override
    public int estimatedDays(Order order) {
        return 1 + order.distanceKm() / 300;
    }

    @Override
    protected String handOver(Order order) {
        return "посилку " + order.id() + " відправлено у відділення, м. " + order.city();
    }

    @Override
    protected boolean requiresAddress() {
        return false;              // достатньо міста: отримувач забирає у відділенні
    }

    @Override
    public String trackingNumber(Order order) {
        return makeTrackingNumber("UA", order);
    }

    @Override
    public String trackingStatus(String trackingNumber) {
        return trackingNumber + ": прямує до відділення отримувача";
    }
}
