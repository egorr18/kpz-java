package delivery.method;

import delivery.model.Order;

/** Перша змінна залежність сервісу — спосіб доставки. Лише те, що вміють УСІ способи. */
public interface DeliveryMethod {

    String name();

    /** Орієнтовна кількість днів до отримання. */
    int estimatedDays(Order order);

    /** Виконати доставку і повернути опис того, що відбулося. */
    String deliver(Order order);

    /** Чи можна доставити замовлення цим способом (напр. чи є потрібна адреса). */
    default boolean canDeliver(Order order) {
        return true;
    }
}
