package staff;

import java.math.BigDecimal;

/**
 * Стажер: стипендія — 60 % окладу посади. Нарахування менше, ніж у штатних, але контракт
 * базового типу виконано: результат ≥ 0, без винятків, без зміни стану.
 */
public class Intern extends Employee {

    private static final BigDecimal STIPEND_SHARE = new BigDecimal("0.60");

    private final String mentor;

    public Intern(String name, BigDecimal baseSalary, String mentor) {
        super(name, baseSalary);
        this.mentor = (mentor == null || mentor.isBlank()) ? "не призначено" : mentor.strip();
    }

    @Override
    public String position() {
        return "Стажер";
    }

    @Override
    public BigDecimal monthlyPay() {
        return money(super.monthlyPay().multiply(STIPEND_SHARE));
    }

    @Override
    protected String payDetails() {
        return "60 % від окладу " + getBaseSalary() + ", ментор: " + mentor;
    }
}
