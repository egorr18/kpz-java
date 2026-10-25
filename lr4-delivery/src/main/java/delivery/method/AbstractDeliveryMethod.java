package delivery.method;

import delivery.model.Order;

/**
 * Спільна частина всіх способів доставки (без дублювання в реалізаціях):
 * зберігання назви, перевірка адреси і шаблон тексту доставки.
 * Абстрактний клас, а не інтерфейс, бо тут є стан (назва) і готова логіка.
 */
public abstract class AbstractDeliveryMethod implements DeliveryMethod {

    private final String name;

    protected AbstractDeliveryMethod(String name) {
        this.name = name;
    }

    @Override
    public final String name() {
        return name;
    }

    /** Шаблонний метод: однаковий порядок дій, специфіку дає {@link #handOver(Order)}. */
    @Override
    public final String deliver(Order order) {
        if (!canDeliver(order)) {
            return name + ": не вказано адресу для замовлення " + order.id();
        }
        int days = estimatedDays(order);
        return name + ": " + handOver(order) + (days == 0 ? " (готово сьогодні)" : " (≈ " + days + " дн.)");
    }

    @Override
    public boolean canDeliver(Order order) {
        return !(requiresAddress() && order.address().isEmpty());
    }

    /** Чи потрібна для способу адреса отримувача. */
    protected boolean requiresAddress() {
        return true;
    }

    /** Специфічна частина: як саме товар потрапляє до клієнта. */
    protected abstract String handOver(Order order);

    /** Спільний генератор номерів відстеження — використовують лише ті, хто реалізує Trackable. */
    protected final String makeTrackingNumber(String prefix, Order order) {
        int hash = Math.abs((order.id() + order.city()).hashCode()) % 1_000_000;
        return String.format("%s-%06d", prefix, hash);
    }
}
