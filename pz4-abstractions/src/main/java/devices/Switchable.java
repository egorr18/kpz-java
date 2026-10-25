package devices;

public interface Switchable {
    void turnOn();

    void turnOff();

    boolean isOn();

    default String describe() {
        return "вмикається/вимикається, зараз " + (isOn() ? "увімкнено" : "вимкнено");
    }
}
