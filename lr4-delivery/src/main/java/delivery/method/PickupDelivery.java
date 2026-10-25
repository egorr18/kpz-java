package delivery.method;

import delivery.model.Order;

/** Самовивіз: відстеження немає — і клас не реалізує Trackable (жодних порожніх методів). */
public class PickupDelivery extends AbstractDeliveryMethod {

    private final String pickupPoint;

    public PickupDelivery(String pickupPoint) {
        super("Самовивіз");
        this.pickupPoint = pickupPoint;
    }

    @Override
    public int estimatedDays(Order order) {
        return 0;
    }

    @Override
    protected boolean requiresAddress() {
        return false;
    }

    @Override
    protected String handOver(Order order) {
        return "замовлення " + order.id() + " чекає у пункті «" + pickupPoint + "»";
    }
}
