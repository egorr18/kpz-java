package logging;

import java.util.ArrayList;
import java.util.List;

/** Реалізація 2: накопичує події в буфері пам'яті (зручно для тестів і подальшого аналізу). */
public class InMemoryEventLog implements EventLog {

    private final List<String> buffer = new ArrayList<>();

    @Override
    public void record(String event) {
        buffer.add(event);
    }

    public List<String> events() {
        return List.copyOf(buffer);
    }
}
