package staff;

import java.math.BigDecimal;

/**
 * Новий різновид, доданий після готової ієрархії: підрядник без окладу, оплата погодинно.
 * Payroll і код обробки масиву для нього не змінювались.
 */
public class Contractor extends Employee {

    private final int hours;
    private final BigDecimal hourlyRate;

    public Contractor(String name, int hours, BigDecimal hourlyRate) {
        super(name, BigDecimal.ZERO);
        if (hours < 0 || hourlyRate == null || hourlyRate.signum() < 0) {
            throw new IllegalArgumentException("години й ставка мають бути невід'ємними");
        }
        this.hours = hours;
        this.hourlyRate = money(hourlyRate);
    }

    @Override
    public String position() {
        return "Підрядник";
    }

    @Override
    public BigDecimal monthlyPay() {
        return money(super.monthlyPay().add(hourlyRate.multiply(BigDecimal.valueOf(hours))));
    }

    @Override
    protected String payDetails() {
        return hours + " год × " + hourlyRate + " грн/год";
    }
}
