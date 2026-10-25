package logging;

/** Абстракція залежності сервісу — журнал подій. */
public interface EventLog {
    void record(String event);
}
