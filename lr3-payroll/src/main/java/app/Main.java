package app;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import staff.Contractor;
import staff.Developer;
import staff.Employee;
import staff.Intern;
import staff.Manager;
import staff.Payroll;

public class Main {

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        Employee[] staff = {
                new Manager("Олена Коваль", new BigDecimal("60000"), 4),
                new Developer("Андрій Шевчук", new BigDecimal("50400"), 10),
                new Developer("Ірина Бондар", new BigDecimal("42000"), 0),
                new Intern("Максим Ткач", new BigDecimal("30000"), "Андрій Шевчук")
        };
        Payroll.print("Відомість за жовтень", staff, out);

        // Новий різновид: масив розширено, Payroll і цикл обробки не змінювались
        Employee[] withContractor = {
                staff[0], staff[1], staff[2], staff[3],
                new Contractor("Тарас Мельник", 40, new BigDecimal("650"))
        };
        out.println();
        Payroll.print("Відомість за жовтень (з підрядником)", withContractor, out);
    }
}
