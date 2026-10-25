package staff;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Базовий тип варіанта 1 — співробітник.
 * <p>Контракт {@link #monthlyPay()} для будь-якого різновиду (основа для LSP):
 * <ul>
 *   <li>повертає не null, значення ≥ 0, округлене до копійок (scale = 2);</li>
 *   <li>не кидає винятків для коректно створеного об'єкта;</li>
 *   <li>не змінює стан об'єкта — повторний виклик дає той самий результат.</li>
 * </ul>
 * Спільні дані (ім'я, оклад) і перевірки зосереджено тут; різновиди лише додають свою частину.
 */
public abstract class Employee {

    protected static final int SCALE = 2;

    private final String name;
    private final BigDecimal baseSalary;

    protected Employee(String name, BigDecimal baseSalary) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("ім'я не може бути порожнім");
        }
        if (baseSalary == null || baseSalary.signum() < 0) {
            throw new IllegalArgumentException("оклад не може бути від'ємним");
        }
        this.name = name.strip();
        this.baseSalary = baseSalary.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public final String getName() {
        return name;
    }

    public final BigDecimal getBaseSalary() {
        return baseSalary;
    }

    /** Посада для звітів — кожен різновид називає себе сам. */
    public abstract String position();

    /** Спільна операція: нарахування за місяць. За замовчуванням — оклад. */
    public BigDecimal monthlyPay() {
        return baseSalary;
    }

    /** Пояснення розрахунку для відомості. */
    protected String payDetails() {
        return "оклад " + baseSalary;
    }

    /** Шаблонний рядок відомості — однаковий для всіх, без дублювання в різновидах. */
    public final String payslipLine() {
        return String.format("%-11s %-22s %10s грн  (%s)", position(), name, monthlyPay(), payDetails());
    }

    protected static BigDecimal money(BigDecimal value) {
        return value.setScale(SCALE, RoundingMode.HALF_UP);
    }
}
