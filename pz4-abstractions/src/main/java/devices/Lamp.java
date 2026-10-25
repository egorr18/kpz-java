package devices;

/** Лампа: реалізує обидва інтерфейси; рівень — яскравість 0..100 %. */
public class Lamp extends Device implements Switchable, Adjustable {

    private boolean on;
    private int brightness = 100;

    public Lamp(String name) {
        super(name);
    }

    @Override
    public String status() {
        return on ? "світить на " + brightness + " %" : "вимкнена";
    }

    @Override public void turnOn() { on = true; }
    @Override public void turnOff() { on = false; }
    @Override public boolean isOn() { return on; }

    @Override
    public void setLevel(int level) {
        brightness = Math.max(0, Math.min(100, level));
    }

    @Override
    public int getLevel() {
        return brightness;
    }

    /** Розв'язання конфлікту default-методів: явно обираємо й поєднуємо обидві версії. */
    @Override
    public String describe() {
        return "Лампа: " + Switchable.super.describe() + "; " + Adjustable.super.describe();
    }
}
