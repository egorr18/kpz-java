package com.example;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

/**
 * Консольний конвертер швидкості. Введення перевіряється методами hasNextInt()/hasNextDouble(),
 * тому некоректні дані не призводять до винятку: користувач отримує повідомлення і повторює запит.
 */
public class Main {

    private static final SpeedUnit[] UNITS = SpeedUnit.values();

    // Явне кодування UTF-8 для введення й виведення (кирилиця незалежно від налаштувань консолі)
    private static final PrintStream OUT = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    private static final Scanner IN = new Scanner(System.in, StandardCharsets.UTF_8).useLocale(Locale.US);

    public static void main(String[] args) {
        OUT.println("=== Конвертер одиниць: Швидкість (варіант 5) ===");
        while (true) {
            OUT.println();
            OUT.println("1) Конвертувати між двома одиницями");
            OUT.println("2) Перевести значення в усі одиниці");
            OUT.println("0) Вихід");
            int choice = readInt("Вибір: ", 0, 2);
            if (choice == 0) {
                OUT.println("-> Вихід. Дякуємо за використання!");
                return;
            }
            double value = readDouble("Значення: ");
            SpeedUnit from = readUnit("З");
            if (choice == 1) {
                SpeedUnit to = readUnit("У");
                OUT.printf("-> %s %s = %s %s%n",
                        format(value), from.symbol(), format(from.convert(value, to)), to.symbol());
            } else {
                for (SpeedUnit to : UNITS) {
                    OUT.printf("   %s %s%n",
                            StringUtils.leftPad(format(from.convert(value, to)), 14), to.symbol());
                }
            }
        }
    }

    private static SpeedUnit readUnit(String label) {
        StringBuilder prompt = new StringBuilder(label).append(" (");
        for (int i = 0; i < UNITS.length; i++) {
            if (i > 0) prompt.append(", ");
            prompt.append(i + 1).append(" - ").append(UNITS[i].symbol());
        }
        prompt.append("): ");
        return UNITS[readInt(prompt.toString(), 1, UNITS.length) - 1];
    }

    /** Ціле число з діапазону [min, max]; некоректне введення відкидається без винятків. */
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            OUT.print(prompt);
            if (!IN.hasNext()) {
                OUT.println();
                OUT.println("-> Введення завершено. До побачення!");
                System.exit(0);
            }
            if (IN.hasNextInt()) {
                int value = IN.nextInt();
                if (value >= min && value <= max) {
                    return value;
                }
                OUT.printf("-> Помилка: введіть число від %d до %d.%n", min, max);
            } else {
                String wrong = IN.next();
                OUT.printf("-> Помилка: \"%s\" не є цілим числом. Спробуйте ще раз.%n", wrong);
            }
        }
    }

    /** Дробове число; від'ємна швидкість не має фізичного змісту для конвертера, тому відхиляється. */
    private static double readDouble(String prompt) {
        while (true) {
            OUT.print(prompt);
            if (!IN.hasNext()) {
                OUT.println();
                OUT.println("-> Введення завершено. До побачення!");
                System.exit(0);
            }
            if (IN.hasNextDouble()) {
                double value = IN.nextDouble();
                if (value >= 0 && !Double.isInfinite(value)) {
                    return value;
                }
                OUT.println("-> Помилка: швидкість не може бути від'ємною.");
            } else {
                String wrong = IN.next();
                OUT.printf("-> Помилка: \"%s\" не є числом. Спробуйте ще раз.%n", wrong);
            }
        }
    }

    private static String format(double value) {
        String text = String.format(Locale.US, "%.4f", value);
        return text.contains(".") ? StringUtils.stripEnd(StringUtils.stripEnd(text, "0"), ".") : text;
    }
}
