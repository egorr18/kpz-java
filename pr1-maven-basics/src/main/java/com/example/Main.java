package com.example;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;

public class Main {

    public static void main(String[] args) {
        // Явно задаємо UTF-8 для виводу, щоб кирилиця не залежала від налаштувань консолі
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.println("JVM: " + System.getProperty("java.version")
                + " (" + System.getProperty("java.vendor") + ")");
        out.println("OS: " + System.getProperty("os.name")
                + " " + System.getProperty("os.version")
                + " (" + System.getProperty("os.arch") + ")");
        out.println("Reversed (Commons Lang): " + StringUtils.reverse("Java"));
        out.println("Capitalized (Commons Lang): " + StringUtils.capitalize("король єгор"));
        out.println(greeting());
    }

    static String greeting() {
        return "Привіт зі збірки!";
    }
}
