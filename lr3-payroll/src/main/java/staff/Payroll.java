package staff;

import java.io.PrintStream;
import java.math.BigDecimal;

/**
 * Клієнтський код: обробляє масив базового типу і не розрізняє конкретні різновиди
 * (жодних instanceof чи switch за типом). Додавання нового різновиду цей клас не змінює.
 */
public final class Payroll {

    private Payroll() { }

    public static BigDecimal total(Employee[] staff) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Employee e : staff) {
            sum = sum.add(e.monthlyPay());
        }
        return sum;
    }

    public static void print(String title, Employee[] staff, PrintStream out) {
        out.println("=== " + title + " ===");
        for (Employee e : staff) {
            out.println(e.payslipLine());
        }
        out.println("Разом до виплати: " + total(staff) + " грн");
    }
}
