package staff;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

/**
 * Перевірка принципу підстановки: один і той самий набір вимог базового типу
 * запускається для кожного різновиду, включно з крайніми значеннями.
 */
class EmployeeContractTest {

    static Stream<Employee> everyKind() {
        return Stream.of(
                new Manager("М", new BigDecimal("60000"), 4),
                new Manager("М0", BigDecimal.ZERO, 0),
                new Manager("М100", new BigDecimal("10000"), 100),
                new Developer("Р", new BigDecimal("50400"), 10),
                new Developer("Р0", BigDecimal.ZERO, 0),
                new Intern("С", new BigDecimal("30000"), "ментор"),
                new Intern("С0", BigDecimal.ZERO, null));
    }

    @ParameterizedTest
    @MethodSource("everyKind")
    void monthlyPayHonoursBaseContract(Employee e) {
        BigDecimal first = assertDoesNotThrow(e::monthlyPay);
        assertNotNull(first);
        assertTrue(first.signum() >= 0, "нарахування не може бути від'ємним");
        assertEquals(2, first.scale(), "суму округлено до копійок");
        assertEquals(first, e.monthlyPay(), "повторний виклик не змінює результат");
        assertDoesNotThrow(e::payslipLine);
    }

    @Test
    void concreteAmounts() {
        assertEquals(new BigDecimal("72000.00"), new Manager("М", new BigDecimal("60000"), 4).monthlyPay());
        assertEquals(new BigDecimal("15000.00"), new Manager("М", new BigDecimal("10000"), 100).monthlyPay());
        assertEquals(new BigDecimal("54900.00"), new Developer("Р", new BigDecimal("50400"), 10).monthlyPay());
        assertEquals(new BigDecimal("18000.00"), new Intern("С", new BigDecimal("30000"), "x").monthlyPay());
    }
}
