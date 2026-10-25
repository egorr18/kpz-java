package delivery.method;

import delivery.model.Order;

/**
 * Додаткова можливість — відстеження відправлення. Окремий інтерфейс (ISP):
 * його реалізують лише способи, які справді можна відстежити; самовивіз його не має
 * і не містить порожніх «заглушок».
 */
public interface Trackable {

    /** Номер для відстеження, присвоєний відправленню. */
    String trackingNumber(Order order);

    /** Поточний статус відправлення за номером. */
    String trackingStatus(String trackingNumber);
}
