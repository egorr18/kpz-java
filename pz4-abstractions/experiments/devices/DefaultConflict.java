package devices;

/** Дослід 2: обидва інтерфейси мають default describe(), а клас конфлікт не розв'язує. */
public class DefaultConflict implements Switchable, Adjustable {
    private boolean on;
    private int level;

    @Override public void turnOn() { on = true; }
    @Override public void turnOff() { on = false; }
    @Override public boolean isOn() { return on; }
    @Override public void setLevel(int level) { this.level = level; }
    @Override public int getLevel() { return level; }
}
