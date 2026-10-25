package staff;

import java.math.BigDecimal;

/** Розробник: оклад + понаднормові години за ставкою ×1.5 (ставка = оклад / 168 год). */
public class Developer extends Employee {

    private static final BigDecimal HOURS_PER_MONTH = new BigDecimal("168");
    private static final BigDecimal OVERTIME_RATE = new BigDecimal("1.5");

    private final int overtimeHours;

    public Developer(String name, BigDecimal baseSalary, int overtimeHours) {
        super(name, baseSalary);
        if (overtimeHours < 0) {
            throw new IllegalArgumentException("понаднормові години не можуть бути від'ємними");
        }
        this.overtimeHours = overtimeHours;
    }

    @Override
    public String position() {
        return "Розробник";
    }

    private BigDecimal overtimePay() {
        BigDecimal hourly = getBaseSalary().divide(HOURS_PER_MONTH, 6, java.math.RoundingMode.HALF_UP);
        return money(hourly.multiply(OVERTIME_RATE).multiply(BigDecimal.valueOf(overtimeHours)));
    }

    @Override
    public BigDecimal monthlyPay() {
        return money(super.monthlyPay().add(overtimePay()));
    }

    @Override
    protected String payDetails() {
        return super.payDetails() + " + понаднормові " + overtimeHours + " год " + overtimePay();
    }
}
