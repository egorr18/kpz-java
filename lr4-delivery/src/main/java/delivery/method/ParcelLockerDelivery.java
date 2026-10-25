package delivery.method;

import delivery.model.Order;

/** Нова реалізація, додана після готового сервісу: поштомат з відстеженням. DeliveryService не змінювався. */
public class ParcelLockerDelivery extends AbstractDeliveryMethod implements Trackable {

    private final String lockerId;

    public ParcelLockerDelivery(String lockerId) {
        super("Поштомат");
        this.lockerId = lockerId;
    }

    @Override
    public int estimatedDays(Order order) {
        return order.distanceKm() <= 50 ? 1 : 2;
    }

    @Override
    protected boolean requiresAddress() {
        return false;
    }

    @Override
    protected String handOver(Order order) {
        return "замовлення " + order.id() + " покладено в поштомат №" + lockerId + ", м. " + order.city();
    }

    @Override
    public String trackingNumber(Order order) {
        return makeTrackingNumber("PL", order);
    }

    @Override
    public String trackingStatus(String trackingNumber) {
        return trackingNumber + ": очікує в поштоматі №" + lockerId + ", код для отримання надіслано SMS";
    }
}
