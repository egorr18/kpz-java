package logging;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Реалізація 1: друкує події в консоль. */
public class ConsoleEventLog implements EventLog {

    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    @Override
    public void record(String event) {
        out.println("  [консоль " + LocalTime.now().format(TIME) + "] " + event);
    }
}
