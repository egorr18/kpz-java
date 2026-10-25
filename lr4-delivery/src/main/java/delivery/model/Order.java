package delivery.model;

import java.math.BigDecimal;

/** Замовлення, яке треба доставити. Незмінний record із перевіркою даних. */
public record Order(String id, String city, String address, double weightKg, int distanceKm, BigDecimal value) {

    public Order {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("порожній номер замовлення");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("порожнє місто");
        if (weightKg <= 0) throw new IllegalArgumentException("вага має бути додатною");
        if (distanceKm < 0) throw new IllegalArgumentException("відстань не може бути від'ємною");
        if (value == null || value.signum() < 0) throw new IllegalArgumentException("вартість товару не може бути від'ємною");
        address = address == null ? "" : address.strip();
    }
}
