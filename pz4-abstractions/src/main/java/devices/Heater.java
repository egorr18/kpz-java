package devices;

/** Обігрівач: рівень — цільова температура 5..30 °C. */
public class Heater extends Device implements Switchable, Adjustable {

    private boolean on;
    private int temperature = 20;

    public Heater(String name) {
        super(name);
    }

    @Override
    public String status() {
        return on ? "гріє до " + temperature + " °C" : "вимкнений";
    }

    @Override public void turnOn() { on = true; }
    @Override public void turnOff() { on = false; }
    @Override public boolean isOn() { return on; }

    @Override
    public void setLevel(int level) {
        temperature = Math.max(5, Math.min(30, level));
    }

    @Override
    public int getLevel() {
        return temperature;
    }

    @Override
    public String describe() {
        return "Обігрівач: " + Adjustable.super.describe() + " °C";
    }
}
